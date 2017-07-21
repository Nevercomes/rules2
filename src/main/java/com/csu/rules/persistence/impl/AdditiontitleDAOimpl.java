package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Additiontitle;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AdditiontitleDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ltaoj on 17-7-17.
 */
@Repository
public class AdditiontitleDAOimpl implements AdditiontitleDAO {
    public Integer addTitle(Additiontitle title) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Integer titleId = (Integer) session.save(title);
            transaction.commit();
            session.close();
            return titleId;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void addTitleList(List<Additiontitle> titleList) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            for (int i = 0;i < titleList.size();i++) {
                session.save(titleList.get(i));
            }
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Additiontitle getTitle(int titleId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Additiontitle title = session.get(Additiontitle.class, titleId);
            transaction.commit();
            session.close();
            return title;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Additiontitle> getTitleListByType(int type) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Additiontitle as additiontitle where type=" + type;
            List<Additiontitle> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Additiontitle> getTitleListByTypeAndPage(int type, int offset, int count) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Additiontitle as additiontitle where type=" + type;
            Query query = session.createQuery(hql);
            query.setFirstResult(offset);
            query.setFetchSize(count);
            List<Additiontitle> list = query.list();
            transaction.commit();
            session.close();
            return list;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Additiontitle> getRandomTitleListByType(int count, int type) throws PersistenceException {
        try {
            Set<Integer> set = randomIntegerList(count, type);
            return getTitleListByTitleIds(set);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Additiontitle> getTitleListByTitleIds(Set<Integer> titleIds) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Additiontitle as additiontitle where additiontitle.titleId in (" + formatSet(titleIds) + ")";
            List<Additiontitle> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Long getTotalTitleSizeByType(int type) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "select count(*) from Additiontitle as additiontitle where type=" + type;
            Long count = (Long) session.createQuery(hql).uniqueResult();
            transaction.commit();
            session.close();
            return count;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Set<Integer> randomIntegerList(int count, int type) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "select additiontitle.titleId from Additiontitle as additiontitle where type=" + type;
            List<Integer> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return listToSet(list, count);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public String formatSet(Set set) throws PersistenceException{
        return formatSet(set, ",");
    }

    public String formatSet(Set set, String split) throws PersistenceException {
        StringBuilder sb = new StringBuilder();
        Iterator values = set.iterator();
        while (values.hasNext()) {
            sb.append(split + values.next());
        }
        return sb.subSequence(1, sb.length()).toString();
    }

    public Set parseString(String formatString) throws PersistenceException{
        return parseString(formatString, ",");
    }

    public Set parseString(String formatString, String split) throws PersistenceException {
        String[] strIds = formatString.split(split);
        if (strIds == null || strIds.length == 0) throw new PersistenceException();
        Set<Integer> integerIds = new HashSet<Integer>();
        for (int i = 0;i < strIds.length;i++) {
            integerIds.add(Integer.parseInt(strIds[i]));
        }
        return integerIds;
    }

    /**
     * 从List中随机选取count个Id，以Set形式返回
     * @param list
     * @param count
     * @return
     */
    private Set<Integer> listToSet(List<Integer> list, int count) {
        Set<Integer> set = new HashSet<Integer>();
        if (list.size() <= count) {
            for (Integer integer:list) {
                set.add(integer);
            }
        }else {
            while (set.size() < count) {
                set.add(list.get(((int) (Math.random()*list.size()))));
            }
        }
        return set;
    }
}
