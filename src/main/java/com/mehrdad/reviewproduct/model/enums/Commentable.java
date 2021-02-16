package com.mehrdad.reviewproduct.model.enums;

/**
 * Created by m.peykari on 2/14/2021.
 */
public enum Commentable {
    UNCOMMENTABLE(0,"uncommentable","غیر قابل نظردهی"),
    COMMENTABLE(1,"Commentable","قابل نظردهی"),
    BUYERS(2,"ForBuyers","برای خریداران");

    private final Integer index;
    private final String title;
    private final String persianTitle;

    Commentable(Integer index, String title, String persianTitle) {
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