package com.bh.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDemo {
    public static void main(String[] args) {
        DateTimeFormatter isoLocalDate = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate parse = LocalDate.parse("2020-04-22T09:28:01.482Z", isoLocalDate);
        System.out.println("parse = " + parse);
    }
}
