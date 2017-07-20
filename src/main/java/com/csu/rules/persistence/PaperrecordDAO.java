package com.csu.rules.persistence;

import com.csu.rules.domain.Paperrecord;
import com.csu.rules.exception.PersistenceException;

import java.util.List;

/**
 * Created by ltaoj on 17-7-17.
 */
public interface PaperrecordDAO {

    /**
     * 将提交试卷写进数据库
     * @param paperrecord
     * @return
     * @throws PersistenceException
     */
    void insertPaperrecord(Paperrecord paperrecord) throws PersistenceException;

    /**
     * 通过试卷编号删除提交的试卷
     * @param paperId
     * @throws PersistenceException
     */
    void deletePaperrecord(int paperId) throws PersistenceException;

    /**
     * 通过学号删除所有试卷
     * @param studentId
     * @throws PersistenceException
     */
    void deletePaperrecordByStudentId(long studentId) throws PersistenceException;

    /**
     * 通过测试编号删除所有试卷
     * @param testId
     * @throws PersistenceException
     */
    void deletePaperrecordByTestId(int testId) throws PersistenceException;

    /**
     * 通过学号和测试编号删除试卷
     * @param studentId
     * @param testId
     * @throws PersistenceException
     */
    void deletePaperrecordByStudentIdAndTestId(long studentId, int testId) throws PersistenceException;

    /**
     * 通过试卷编号获取试卷
     * @param paperId
     * @return
     * @throws PersistenceException
     */
    Paperrecord getPaperrecord(int paperId) throws PersistenceException;

    /**
     * 通过学号和测试编号获取试卷
     * @param studentId
     * @param testId
     * @return
     * @throws PersistenceException
     */
    Paperrecord getPaperrecordByStudentIdAndTestId(long studentId, int testId) throws PersistenceException;

    /**
     * 通过学号获取试卷
     * @param studentId
     * @return
     * @throws PersistenceException
     */
    List<Paperrecord> getPaperrecordByStudentId(long studentId) throws PersistenceException;

    /**
     * 通过试卷编号更新提交的试卷
     * @param paperrecord
     * @throws PersistenceException
     */
    void updatePaperrecord(int paperId,Paperrecord paperrecord) throws PersistenceException;

    /**
     * 通过学号和测试编号更新试卷
     * @param studentId
     * @param testId
     * @param paperrecord
     * @throws PersistenceException
     */
    void updatePaperrecordBy(long studentId, int testId, Paperrecord paperrecord) throws PersistenceException;
}
