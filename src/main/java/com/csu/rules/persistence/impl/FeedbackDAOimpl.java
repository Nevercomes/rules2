package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Feedback;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AbstractDAO;
import com.csu.rules.persistence.FeedbackDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by GF on 2017/7/3.
 */
@Repository
public class FeedbackDAOimpl extends AbstractDAO implements FeedbackDAO {
    public void insertFeedback(Feedback feedback) throws PersistenceException {
            Session session= HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            session.save(feedback);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public List<Feedback> getFeedbackList() throws PersistenceException {
            Session session= HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            String hql="from Feedback";
            Query query=session.createQuery(hql);
            List<Feedback> list=query.list();
            session.flush();
            transaction.commit();
            return list;
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }
}
