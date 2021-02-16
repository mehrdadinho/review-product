package com.mehrdad.reviewproduct.model.enums;

/**
 * Created by m.peykari on 2/14/2021.
 */
public enum CommentStatus {
    WAITING(0,"waiting","منتظر تایید"),
    CONFIRMED(1,"confirmed","تایید شده");

    private final Integer index;
    private final String title;
    private final String persianTitle;

    CommentStatus(Integer index, String title, String persianTitle) {
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