package com.example.PayslipDeductionService.Util;

public class MathUtil {

    public static double setToTwoDecimalPlaces(double value) {
        int intValue = (int) (value * 100);
        return intValue / 100.0;
    }
}
