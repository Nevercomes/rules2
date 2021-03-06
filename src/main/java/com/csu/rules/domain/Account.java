package com.csu.rules.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by ltaoj on 17-6-8.
 */
@Entity
@Table(name = "userinfo", schema = "saverulessystem", catalog = "")
public class Account implements Serializable {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "username")
    private String username;
    @Column(name = "sex")
    private byte sex;
    @Column(name = "clazz")
    private String clazz;
    @Column(name = "grade")
    private int grade;
    @Column(name = "major")
    private String major;
    @Column(name = "college")
    private String college;
    @Column(name = "del_flag")
    private int delFlag = 0;

    public Account() {}

    public Account(String studentId, String username, byte sex, String clazz, int grade, String college) {
        this.studentId = studentId;
        this.username = username;
        this.sex = sex;
        this.clazz = clazz;
        this.grade = grade;
        this.college = college;
    }

    public Account(String studentId, String username, byte sex, String clazz, int grade, String major, String college) {
        this.studentId = studentId;
        this.username = username;
        this.sex = sex;
        this.clazz = clazz;
        this.grade = grade;
        this.major = major;
        this.college = college;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Account{" +
                "studentId='" + studentId + '\'' +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", clazz='" + clazz + '\'' +
                ", grade=" + grade +
                ", major='" + major + '\'' +
                ", college='" + college + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}