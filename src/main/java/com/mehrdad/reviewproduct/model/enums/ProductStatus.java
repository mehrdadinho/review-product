package com.mehrdad.reviewproduct.model.enums;

/**
 * Created by m.peykari on 2/14/2021.
 */
public enum ProductStatus {
    INVISIBLE(0,"invisible","غیر قابل مشاهده"),
    VISIBLE(1,"visible","قابل مشاهده");

    private final Integer index;
    private final String title;
    private final String persianTitle;

    ProductStatus(Integer index, String title, String persianTitle) {
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