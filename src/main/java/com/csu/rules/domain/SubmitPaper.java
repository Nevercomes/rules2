package com.csu.rules.domain;

import java.util.List;

/**
 * Created by GF on 2017/7/20.
 */
public class SubmitPaper {
    private List<Title> titleList;  // 选择题列表
    private List<Additiontitle> blanksList; // 填空题列表
    private List<Additiontitle> judgeList; // 判断题列表
    private String shortAnswer; //简答题回答内容
    private String caseAnswer; //案例题回答内容
    private String discussAnswer; //论述题回答内容

    public List<Title> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<Title> titleList) {
        this.titleList = titleList;
    }

    public List<Additiontitle> getBlanksList() {
        return blanksList;
    }

    public void setBlanksList(List<Additiontitle> blanksList) {
        this.blanksList = blanksList;
    }

    public List<Additiontitle> getJudgeList() {
        return judgeList;
    }

    public void setJudgeList(List<Additiontitle> judgeList) {
        this.judgeList = judgeList;
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(String shortAnswer) {
        this.shortAnswer = shortAnswer;
    }

    public String getCaseAnswer() {
        return caseAnswer;
    }

    public void setCaseAnswer(String caseAnswer) {
        this.caseAnswer = caseAnswer;
    }

    public String getDiscussAnswer() {
        return discussAnswer;
    }

    public void setDiscussAnswer(String discussAnswer) {
        this.discussAnswer = discussAnswer;
    }
}
