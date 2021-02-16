package com.mehrdad.reviewproduct.util;

import com.mehrdad.reviewproduct.exception.BadRequestException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by m.peykari on 7/18/2020.
 */
@Component
public class Utility {

    public Date stringToDate(String inputDate, boolean endOfDay) throws BadRequestException {
        if (inputDate == null || inputDate.isEmpty()) {
            return null;
        } else {
            if (endOfDay) {
                inputDate = inputDate + "-23-59-59";
            }else{
                inputDate = inputDate + "-00-00-00";
            }
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").parse(inputDate);
            } catch (ParseException e) {
                throw new BadRequestException("Problem with parseing date: "+ inputDate);
            }
            return date;
        }
    }

}
