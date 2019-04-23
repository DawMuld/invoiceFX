/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.pars;

import java.text.DecimalFormat;

/**
 *
 * @author daan-
 */
public class PriceParser {

    public static final DecimalFormat FORMAT = new DecimalFormat("#0.00");

    public static double toPrice(String value) {
        if (value.contains(",")) {
            value = value.replace(",", ".");
        }
        return Double.parseDouble(value);
    }

    public static double toPrice(double value) {
        String num = FORMAT.format(value);
        return Double.parseDouble(num);
    }

    public static String fromPrice(double value) {
        return FORMAT.format(value);
    }
}
