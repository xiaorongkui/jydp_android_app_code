package com.qmkj.jydp.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TestNumberFormat {

    public static void main(String[] args) {
        System.out.println(format2Point(1));
        System.out.println(format2Point(1.01));
        System.out.println(format2Point(1.42));
        System.out.println(format2Point(1.8564));
        System.out.println(format2Point(0.8564));
        System.out.println(format2Point(0.5));
        System.out.println(format2Point(100.464));
    }

    public static String format2Point(double str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.setRoundingMode(RoundingMode.FLOOR);
        myformat.applyPattern("#0.00");
        return myformat.format(str);
    }
}
