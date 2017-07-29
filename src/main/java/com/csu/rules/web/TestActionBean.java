package com.csu.rules.web;

import com.csu.rules.domain.*;
import com.csu.rules.exception.AccountServiceException;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.exception.TestServiceException;
import com.csu.rules.exception.TitleServiceException;
import com.csu.rules.service.AccountService;
import com.csu.rules.service.LearnService;
import com.csu.rules.service.TestService;
import com.csu.rules.service.TitleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by GF on 2017/6/12.
 */
@Controller
@RequestMapping(value = {"/test"})
public class TestActionBean extends AbstractActionBean {
    private TestService testService;
    private TitleService titleService;
    private AccountService accountService;

    @Autowired
    public TestActionBean(TestService testService, TitleService titleService,AccountService accountService) {
        this.testService = testService;
        this.titleService = titleService;
        this.accountService=accountService;
    }

    //获取考试信息
    @RequestMapping(value = "/getTestInfo", method = RequestMethod.GET)
    public ResponseEntity<Testinfo> getTestInfo() {
        try {
            List<Testinfo> testinfoList=testService.getTestInfoList();
            if(testinfoList.size()!=0) {
                Testinfo testinfo = testService.getTestInfoList().get(0);
                return new ResponseEntity<Testinfo>(testinfo, HttpStatus.OK);
            }else{
                return new ResponseEntity<Testinfo>(new Testinfo(), HttpStatus.OK);
            }
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //获取竞赛信息
    @RequestMapping(value = "/getContestInfo", method = RequestMethod.GET)
    public ResponseEntity<Testinfo> getContestInfo() {
        try {
            List<Testinfo> contestinfoList=testService.getContestInfoList();
            if(contestinfoList.size()!=0) {
                Testinfo testinfo = contestinfoList.get(0);
                return new ResponseEntity<Testinfo>(testinfo, HttpStatus.OK);
            }else {
                return new ResponseEntity<Testinfo>(new Testinfo(), HttpStatus.OK);
            }
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //开始考试 插入考试时间信息 判断剩余时间

    @RequestMapping(value = "/startTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Paper> getTestTitle(@RequestBody Testrecord testrecord) {
        try {
            Testrecord testrecord1 = testService.getTestRecord(testrecord);
            if (testrecord1 == null) {
                String choiceFormatRandomId=titleService.getRandomIntegerList(2);
                String blankFormatRandomId = titleService.getRandomIntegerListByType(2,1);
                String judgeFormatRandomId = titleService.getRandomIntegerListByType(2,2);
                String shortFormatRandomId = titleService.getRandomIntegerListByType(2,3);
                String caseFormatRandomId = titleService.getRandomIntegerListByType(2,4);
                String discussFormatRandomId = titleService.getRandomIntegerListByType(2,5);
                testrecord.setStartTime(new Timestamp(System.currentTimeMillis()));
                testService.insertTestRecord(testrecord);
                testService.insertTesttitle(testrecord,choiceFormatRandomId, blankFormatRandomId,judgeFormatRandomId,shortFormatRandomId,caseFormatRandomId,discussFormatRandomId);
            }
            Testtitle testtitle = testService.getTesttitleByTestrecord(testrecord);
            List<Title> choiceList=titleService.getTitleListByFormatString(testtitle.getTitleIds());
            List<Additiontitle> blankList = titleService.getAdditiontitleListByFormatString(testtitle.getBlankIds());
            List<Additiontitle> judgeList = titleService.getAdditiontitleListByFormatString(testtitle.getJudgeIds());
            List<Additiontitle> shortList = titleService.getAdditiontitleListByFormatString(testtitle.getShortIds());
            List<Additiontitle> caseList = titleService.getAdditiontitleListByFormatString(testtitle.getCaseIds());
            List<Additiontitle> discussList = titleService.getAdditiontitleListByFormatString(testtitle.getDiscussIds());
            Paper paper=new Paper();
            paper.setTitleList(choiceList);
            paper.setBlanksList(blankList);
            paper.setJudgeList(judgeList);
            paper.setShortList(shortList);
            paper.setCaseList(caseList);
            paper.setDiscussList(discussList);
            return new ResponseEntity<Paper>(paper, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        } catch (TitleServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    /**
     * 根据有无成绩查看是否考过试
     * 若中途退出考试 查看开始考试的时间
     * 显示考试成绩
     *
     * @param testrecord
     * @return
     */
    @RequestMapping(value = "/testRecordInfo", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Testrecord> getTestRecord(@RequestBody Testrecord testrecord) {
        try {
            Testrecord testrecord1 = testService.getTestRecord(testrecord);
            if (testrecord1 == null) {
                return new ResponseEntity<Testrecord>(new Testrecord(), HttpStatus.OK);
            }
            return new ResponseEntity<Testrecord>(testrecord1, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //插入考试成绩的submitTime 判断考试剩余时间
    @RequestMapping(value = "/insertSubmitTime", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> insertSubmitTime(@RequestBody Testrecord testrecord) {
        try {
            Testrecord testrecord1 = testService.getTestRecord(testrecord);
            if(testrecord1==null){
                return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            }else {
                testrecord1.setSubmitTime(new Timestamp(System.currentTimeMillis()));
                testService.updateTestRecord(testrecord1);
                return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            }
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //提交考试 获取考试成绩 更新testRecord
    @RequestMapping(value = "/submitTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Testrecord> submitTest(@RequestBody RecordTitles recordTitles) {
        try {
            Testrecord testrecord=testService.getTestRecord(recordTitles.getTestrecord());
            testrecord.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            testrecord.setScore(new Integer(-1));//表示考试已经完成
            testService.updateTestRecord(testrecord);
            int choiceScore = titleService.getTitlePageScore(recordTitles.getSubmitPaper().getTitleList());//选择题分数
            System.out.println(recordTitles.getSubmitPaper().getBlanksList().get(0).getTitleId());
            int blankScore=titleService.getAdditiontitlePageScore(recordTitles.getSubmitPaper().getBlanksList());//填空题分数
            Paperrecord paperrecord=new Paperrecord();
            paperrecord.setStudentId(recordTitles.getTestrecord().getStudentId());
            paperrecord.setTestId(recordTitles.getTestrecord().getTestId());
            paperrecord.setChoiceScore(choiceScore);
            paperrecord.setBlankScore(blankScore);
            paperrecord.setShortAnswer(recordTitles.getSubmitPaper().getShortAnswer());
            paperrecord.setCaseAnswer(recordTitles.getSubmitPaper().getCaseAnswer());
            paperrecord.setDiscussAnswer(recordTitles.getSubmitPaper().getDiscussAnswer());
            testService.insertPaperrecord(paperrecord);

            return new ResponseEntity<Testrecord>(testrecord, HttpStatus.OK);
        } catch (TitleServiceException te) {
            throw new CatchServiceException(te);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //获取竞赛排名
    @RequestMapping(value = "/getContsetRank", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Integer> getContsetRank(@RequestBody Testrecord testrecord) {
        try {
            int count = testService.getContestRank(testrecord);
            return new ResponseEntity<Integer>(count, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //是否报名竞赛
    @RequestMapping(value = "/isRegisted", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Contestregistion> isRegisted(@RequestBody Contestregistion contestregistion) {
        try {
            Contestregistion contestregistion1 = testService.isRegistedContest(contestregistion);
            return new ResponseEntity<Contestregistion>(contestregistion1, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //报名竞赛
    @RequestMapping(value = "/registContest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> registContest(@RequestBody Contestregistion contestregistion) {
        try {
            testService.registContest(contestregistion);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, contestregistion), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //获取竞赛状态
    @RequestMapping(value = "/getContestStatus", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Contestregistion> getContestStatus(@RequestBody Contestregistion contestregistion) {
        try {
            Contestregistion contestregistion2 = testService.isRegistedContest(contestregistion);
            if (contestregistion2.getStudentId() == contestregistion.getStudentId()) {
                Contestregistion contestregistion1 = testService.changeContestStatus(contestregistion);
                return new ResponseEntity<Contestregistion>(contestregistion1, HttpStatus.OK);
            } else {
                Contestregistion contestregistion1 = new Contestregistion();
                return new ResponseEntity<Contestregistion>(contestregistion1, HttpStatus.OK);
            }
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //判断竞赛时间
    @RequestMapping(value = "/getContestTime", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> getContestTime(@RequestBody Testinfo testinfo) {
        try {
                Testinfo testinfo1=testService.getTestInfo(testinfo.getTestId());
                if(testinfo1.getEndTime().before(new Timestamp(System.currentTimeMillis()))){
                    return new ResponseEntity<Result>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
                }else{
                    return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
                }
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //点击X 删除考试成绩信息 if判断是为了防止删除竞赛信息
    @RequestMapping(value = "/deleteTestRecord", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> deleteTestRecord(@RequestBody Testrecord testrecord) {
        try {
            testService.deleteTestRecord(testrecord);
            Testtitle testtitle=testService.getTesttitleByTestrecord(testrecord);
            if(testtitle!=null) {
                testService.deleteTestTitle(testrecord);
            }
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //开始竞赛 插入时间信息 返回确定竞赛试题
    @RequestMapping(value="/startContest",method=RequestMethod.POST,consumes="application/json")
    public ResponseEntity<Paper> getContestTitle(@RequestBody Testrecord testrecord){
        try {
            Testrecord testrecord1 = testService.getTestRecord(testrecord);
            if (testrecord1 == null) {
                testrecord.setStartTime(new Timestamp(System.currentTimeMillis()));
                testService.insertTestRecord(testrecord);
            }
            Contesttitle contesttitle=testService.getContesttitle(testrecord.getTestId());
            List<Title> choiceList=titleService.getTitleListByFormatString(contesttitle.getTitleIds());
            List<Additiontitle> blankList = titleService.getAdditiontitleListByFormatString(contesttitle.getBlankIds());
            List<Additiontitle> judgeList = titleService.getAdditiontitleListByFormatString(contesttitle.getJudgeIds());
            List<Additiontitle> shortList = titleService.getAdditiontitleListByFormatString(contesttitle.getShortIds());
            List<Additiontitle> caseList = titleService.getAdditiontitleListByFormatString(contesttitle.getCaseIds());
            List<Additiontitle> discussList = titleService.getAdditiontitleListByFormatString(contesttitle.getDiscussIds());
            Paper paper=new Paper();
            paper.setTitleList(choiceList);
            paper.setBlanksList(blankList);
            paper.setJudgeList(judgeList);
            paper.setShortList(shortList);
            paper.setCaseList(caseList);
            paper.setDiscussList(discussList);
            return new ResponseEntity<Paper>(paper, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        } catch (TitleServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //判断考试时间未开始还是已结束
    @RequestMapping(value = "/testTimeStatus", method = RequestMethod.GET)
    public ResponseEntity<Result> testTimeStatus() {
        try {
            List<Testinfo> list=testService.getTestInfoList();
            if(list.size()!=0){
                Testinfo testinfo=list.get(0);
                if(new Timestamp(System.currentTimeMillis()).before(testinfo.getStartTime())){
                    return new ResponseEntity<Result>(new Result("未开始"), HttpStatus.OK);
                }else if(new Timestamp(System.currentTimeMillis()).after(testinfo.getEndTime())){
                    return new ResponseEntity<Result>(new Result("已结束"), HttpStatus.OK);
                }else{
                    return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<Result>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
            }
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }


    /**************************************************管理员****************************************************/
    //插入考试信息
    @RequestMapping(value = "/insertTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> insertTest(@RequestBody Testinfo testinfo) {
        try {
            testService.insertTestInfo(testinfo);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, testinfo), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //删除考试信息
    @RequestMapping(value = "/deleteTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> deleteTest(@RequestBody Testinfo testinfo) {
        try {
            Testinfo testinfo1=testService.getTestInfo(testinfo.getTestId());
            testService.deleteTestInfo(testinfo1.getTestId());
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //修改考试信息
    @RequestMapping(value = "/updateTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> updateTest(@RequestBody Testinfo testinfo) {
        try {
            Testinfo testinfo1=testService.getTestInfo(testinfo.getTestId());
            if(testinfo1!=null) {
                testService.updateTestInfo(testinfo);
            }
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, testinfo), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //查看考试信息
    @RequestMapping(value = "/getTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Testinfo> insertTest(@RequestBody Integer testId) {
        try {
            Testinfo testinfo = testService.getTestInfo(testId);
            return new ResponseEntity<Testinfo>(testinfo, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //查看学生考试成绩
    @RequestMapping(value = "/getAllTestRecord", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<Testrecord>> getAllTestRecord(@RequestBody Integer testId) {
        try {
            List<Testrecord> recordList = testService.getTestRecordList(testId);
            return new ResponseEntity<List<Testrecord>>(recordList, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }


    //管理员发布竞赛题目(自己挑选试题)
//    @RequestMapping(value = "/insertContestTitle", method = RequestMethod.POST, consumes = "application/json")
//    public ResponseEntity<Result> insertContestTitle(@RequestBody Contesttitle contesttitle) {
//        try {
//            testService.insertContesttitle(contesttitle);
//            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
//        } catch (TestServiceException e) {
//            throw new CatchServiceException(e);
//        }
//    }

    //管理员查看系统提供的随机竞赛试题
    @RequestMapping(value = "/getContestRandomTitle", method = RequestMethod.GET)
    public ResponseEntity<Paper> getContestRandomTitle() {
        try {
            Testinfo contestInfo=testService.getContestInfoList().get(0);
            String choiceRandomIds = titleService.getRandomIntegerList(2);
            String blankRandomIds = titleService.getRandomIntegerListByType(2,1);
            String judgeRandomIds = titleService.getRandomIntegerListByType(2,2);
            String shortRandomIds = titleService.getRandomIntegerListByType(2,3);
            String caseRandomIds = titleService.getRandomIntegerListByType(2,4);
            String discussRandomIds = titleService.getRandomIntegerListByType(2,5);
            Contesttitle contesttitle=new Contesttitle();
            contesttitle.setTestId(contestInfo.getTestId());
            contesttitle.setTitleIds(choiceRandomIds);
            contesttitle.setBlankIds(blankRandomIds);
            contesttitle.setJudgeIds(judgeRandomIds);
            contesttitle.setShortIds(shortRandomIds);
            contesttitle.setCaseIds(caseRandomIds);
            contesttitle.setDiscussIds(discussRandomIds);
            testService.insertContesttitle(contesttitle);
            List<Title> choiceList=titleService.getTitleListByFormatString(choiceRandomIds);
            List<Additiontitle> blankList = titleService.getAdditiontitleListByFormatString(blankRandomIds);
            List<Additiontitle> judgeList = titleService.getAdditiontitleListByFormatString(judgeRandomIds);
            List<Additiontitle> shortList = titleService.getAdditiontitleListByFormatString(shortRandomIds);
            List<Additiontitle> caseList = titleService.getAdditiontitleListByFormatString(caseRandomIds);
            List<Additiontitle> discussList = titleService.getAdditiontitleListByFormatString(discussRandomIds);
            Paper paper=new Paper();
            paper.setTitleList(choiceList);
            paper.setBlanksList(blankList);
            paper.setJudgeList(judgeList);
            paper.setShortList(shortList);
            paper.setCaseList(caseList);
            paper.setDiscussList(discussList);
            return new ResponseEntity<Paper>(paper, HttpStatus.OK);
        } catch (TitleServiceException e) {
            throw new CatchServiceException(e);
        }catch (TestServiceException te){
            throw new CatchServiceException(te);
        }
    }

    //管理员删除竞赛试题
    @RequestMapping(value = "/deleteContestTitle", method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<Result> deleteContestTitle(@RequestBody Testinfo testinfo) {
        try {
            Contesttitle contesttitle=testService.getContesttitle(testinfo);
            if(contesttitle!=null) {
                testService.deleteContesttitle(testinfo);
            }
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //管理员查看竞赛试题
    @RequestMapping(value = "/getContesttitle", method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<Result> getContesttitle(@RequestBody Testinfo testinfo) {
        try {
            Contesttitle contesttitle=testService.getContesttitle(testinfo);
            if(contesttitle==null){
                return new ResponseEntity<Result>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
            }else {
                return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            }
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/recordListByCondition", method = RequestMethod.GET)
    public ResponseEntity<List<AccountTestRecord>> getTestRecordByCondition(
            @RequestParam(value = "clazz", defaultValue = "") String clazz,
            @RequestParam(value = "grade", defaultValue = "0") int grade,
            @RequestParam(value = "major", defaultValue = "") String major,
            @RequestParam(value = "college", defaultValue = "") String college,
            @RequestParam(value = "level", defaultValue = "0") int level) {

        try {
            List<AccountTestRecord> testrecordList = testService.getTestRecordByCondition(clazz, grade, major, college, level);
            return new ResponseEntity<List<AccountTestRecord>>(testrecordList, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //管理员查看竞赛报名人数
    @RequestMapping(value = "/getContestregistionCount", method = RequestMethod.GET)
    public ResponseEntity<Integer> getContestregistionCount() {
        try {
            int count=0;
            List<Testinfo> contestInfoList=testService.getContestInfoList();
            if(contestInfoList.size()!=0) {
                count = testService.getContestRegistionList(contestInfoList.get(0)).size();
            }
            return new ResponseEntity<Integer>(count, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //根据考试Id显示考试试卷供教师批阅
    @RequestMapping(value = "/getPaperrecordByTestId", method = RequestMethod.GET)
    public ResponseEntity<List<AccountPaperRecord>> getPaperrecordByTestId() {
        try {
            int testId=testService.getTestInfoList().get(0).getTestId();
            List<Testtitle> testtitleList=testService.getTesttitleList(testId);
            List<AccountPaperRecord> list=new ArrayList<AccountPaperRecord>();

            for(int i=0;i<testtitleList.size();i++){
                Testtitle testtitle=testtitleList.get(i);
                AccountPaperRecord accountPaperRecord=new AccountPaperRecord();
                Paper paper=new Paper();
                Paperrecord paperrecord=testService.getPaperrecordByStudentIdAndTestId(testtitle.getStudentId(),testId);
                //考生信息
                Account account=new Account();
                account.setStudentId(testtitle.getStudentId());
                String[] shortAnswers=paperrecord.getShortAnswer().split("#");
                String[] caseAnswers=paperrecord.getCaseAnswer().split("#");
                String[] discussAnswers=paperrecord.getDiscussAnswer().split("#");
                List<Additiontitle> shortList=titleService.getAdditiontitleListByFormatString(testtitle.getShortIds());
                List<Additiontitle> caseList=titleService.getAdditiontitleListByFormatString(testtitle.getCaseIds());
                List<Additiontitle> discussList=titleService.getAdditiontitleListByFormatString(testtitle.getDiscussIds());
                for (int j=0;j<shortList.size();j++){
                    Additiontitle additiontitle=shortList.get(j);
                    additiontitle.setAnswer(shortAnswers[j]);
                }
                for (int j=0;j<caseList.size();j++){
                    Additiontitle additiontitle=caseList.get(j);
                    additiontitle.setAnswer(caseAnswers[j]);
                }
                for (int j=0;j<discussList.size();j++){
                    Additiontitle additiontitle=discussList.get(j);
                    additiontitle.setAnswer(discussAnswers[j]);
                }
                paper.setShortList(shortList);
                paper.setCaseList(caseList);
                paper.setDiscussList(discussList);
                accountPaperRecord.setPaper(paper);
                accountPaperRecord.setPaperrecord(paperrecord);
                accountPaperRecord.setAccount(accountService.getUserInfo(account));
                list.add(accountPaperRecord);
            }
            return new ResponseEntity<List<AccountPaperRecord>>(list, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }catch (TitleServiceException e){
            throw new CatchServiceException(e);
        }catch (AccountServiceException e){
            throw new CatchServiceException(e);
        }
    }
    //根据学生学号显示考试试卷供教师批阅
    @RequestMapping(value = "/getPaperrecordByStudentId", method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<AccountPaperRecord> getPaperrecordByStudentId(@RequestBody Testrecord testrecord) {
        try {
            Testtitle testtitle=testService.getTesttitleByTestrecord(testrecord);
            AccountPaperRecord accountPaperRecord=new AccountPaperRecord();
            Paper paper=new Paper();
            Paperrecord paperrecord=testService.getPaperrecordByStudentIdAndTestId(testrecord.getStudentId(),testrecord.getTestId());
            //考生信息
            Account account=new Account();
            account.setStudentId(testrecord.getStudentId());
            String[] shortAnswers=paperrecord.getShortAnswer().split("#");
            String[] caseAnswers=paperrecord.getCaseAnswer().split("#");
            String[] discussAnswers=paperrecord.getDiscussAnswer().split("#");
            List<Additiontitle> shortList=titleService.getAdditiontitleListByFormatString(testtitle.getShortIds());
            List<Additiontitle> caseList=titleService.getAdditiontitleListByFormatString(testtitle.getCaseIds());
            List<Additiontitle> discussList=titleService.getAdditiontitleListByFormatString(testtitle.getDiscussIds());
            for (int j=0;j<shortList.size();j++){
                Additiontitle additiontitle=shortList.get(j);
                additiontitle.setAnswer(shortAnswers[j]);
            }
            for (int j=0;j<caseList.size();j++){
                Additiontitle additiontitle=caseList.get(j);
                additiontitle.setAnswer(caseAnswers[j]);
            }
            for (int j=0;j<discussList.size();j++){
                Additiontitle additiontitle=discussList.get(j);
                additiontitle.setAnswer(discussAnswers[j]);
            }
            paper.setShortList(shortList);
            paper.setCaseList(caseList);
            paper.setDiscussList(discussList);
            accountPaperRecord.setPaper(paper);
            accountPaperRecord.setPaperrecord(paperrecord);
            accountPaperRecord.setAccount(accountService.getUserInfo(account));
            return new ResponseEntity<AccountPaperRecord>(accountPaperRecord, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }catch (TitleServiceException e){
            throw new CatchServiceException(e);
        }catch (AccountServiceException e){
            throw new CatchServiceException(e);
        }
    }
}
