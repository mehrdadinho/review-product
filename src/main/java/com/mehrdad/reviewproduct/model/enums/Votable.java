package com.mehrdad.reviewproduct.model.enums;

/**
 * Created by m.peykari on 2/14/2021.
 */
public enum Votable {
    UNVOTABLE(0,"unvotable","غیر قابل امتیازدهی"),
    VOTABLE(1,"votable","قابل امتیازدهی"),
    BUYERS(2,"ForBuyers","برای خریداران");

    private final Integer index;
    private final String title;
    private final String persianTitle;

    Votable(Integer index, String title, String persianTitle) {
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