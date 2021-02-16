package com.mehrdad.reviewproduct.model.enums;

/**
 * Created by m.peykari on 2/14/2021.
 */
public enum VoteScore {
    ONE(1,"one","یک"),
    TWO(2,"two","دو"),
    THREE(3,"three","سه"),
    FOUR(4,"four","چهار"),
    FIVE(5,"five","پنج");

    private final Integer index;
    private final String title;
    private final String persianTitle;

    VoteScore(Integer index, String title, String persianTitle) {
        this.index = index;
        this.title = title;
        this.persianTitle = persianTitle;
    }

    public Integer getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public String getPersianTitle() {
        return persianTitle;
    }
}