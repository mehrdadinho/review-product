package com.mehrdad.reviewproduct.model.enums;

/**
 * Created by m.peykari on 2/14/2021.
 */
public enum OrderStatus {
    registered(0,"registered","ثبت شده"),
    confirmed(1,"confirmed","تایید شده"),
    pending(2,"pending","در حال انتظار"),
    delivered(3,"delivered","تحویل شده"),
    canceled(4,"canceled","کنسل شده");

    private final Integer index;
    private final String title;
    private final String persianTitle;

    OrderStatus(Integer index, String title, String persianTitle) {
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