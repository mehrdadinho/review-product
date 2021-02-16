package com.mehrdad.reviewproduct.model.enums;

/**
 * Created by m.peykari on 2/14/2021.
 */
public enum Currency {
    IRR("IRR","ریال"),
    USD("USD","دلار");

    private final String title;
    private final String persianTitle;

    Currency(String title, String persianTitle) {
        this.title = title;
        this.persianTitle = persianTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getPersianTitle() {
        return persianTitle;
    }
}