package com.csu.rules.domain;

import javax.persistence.*;

/**
 * Created by ltaoj on 17-6-9.
 */
@Entity
@Table(name = "contestregistion", schema = "saverulessystem", catalog = "")
@IdClass(ContestregistionPK.class)
public class Contestregistion {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Id
    @Column(name = "test_id")
    private int testId;
    @Column(name = "status")
    private int status;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}