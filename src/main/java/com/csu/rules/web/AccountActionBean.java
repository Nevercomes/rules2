package com.csu.rules.web;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Signon;
import com.csu.rules.domain.Error;
import com.csu.rules.exception.AccountServiceException;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ltaoj on 17-6-4.
 */
@Controller
@RequestMapping(value = {"/account"})
public class AccountActionBean {

    private AccountService accountService;
    @Autowired
    public AccountActionBean(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Account> login(
            @RequestParam(value = "studentId") long studentId,
            @RequestParam(value = "password") String password){

        try {
            Account account1 = accountService.login(studentId, password);
            return new ResponseEntity<Account>(account1, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @ExceptionHandler(CatchServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Error serviceError(CatchServiceException e) {
        Error error = new Error(e.getServiceException().getErrorCode(), "error");
        return error;
    }
}