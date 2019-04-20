package com.csu.rules.pretreament;

import com.csu.rules.domain.Option;


public class MultiOptionTitle extends AbstractTitle {

    protected String answer = null;

    public MultiOptionTitle(String content, String choices, int score) {
        super();
        this.type = MULTI_OPTION_TYPE;
        this.question = content.trim().replace('"', '“');
        this.answer = choices.trim();
        this.score = score;

        printSql();
    }


    public void printSql() {
        String titleSql = String.format("INSERT INTO additiontitle(title_id, name, type, answer, score) VALUES (%d, \"%s\", %d, \"%s\", %d);",
                this.id, this.question, this.type, this.answer, this.score);
        System.out.println(titleSql);
    }
}
