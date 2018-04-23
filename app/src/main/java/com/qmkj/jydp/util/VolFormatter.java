package com.qmkj.jydp.util;


import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

import java.text.DecimalFormat;

public class VolFormatter implements YAxisValueFormatter {

    public static final int LEFT = 1;
    public static final int RIGTH = 2;
    private final int unit;
    private final int type;
    private DecimalFormat mFormat;
    private String u;
    private float preValue;

    public VolFormatter(int unit, int type) {
        if (unit == 1) {
            mFormat = new DecimalFormat("#0");
        } else {
            mFormat = new DecimalFormat("#0.00");
        }
        this.unit = unit;
        this.u = CommonUtil.getVolUnit(unit);
        this.type = type;
    }


    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        String str = "";
        switch (type) {
            case RIGTH:
                float detalValue = value - preValue;
                if (yAxis.getAxisMaximum() - value < detalValue) {
                    str = "成交量" + "(" + u + ")";
                }
                preValue = value;
                break;
            case LEFT:
                float uValue = value / unit;
                str = mFormat.format(uValue);
                break;
        }

        return str;
    }
}
