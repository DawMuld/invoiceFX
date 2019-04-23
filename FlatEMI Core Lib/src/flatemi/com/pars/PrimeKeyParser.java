/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.pars;

/**
 *
 * @author daan-
 */
public class PrimeKeyParser {

    public static int keyFromLine(String line) {
        String cell = line.split(";")[0];
        if (cell != null && !cell.isEmpty()) {
            String num = cell.replaceAll("\\D", "");
            if (num.length() > 0) {
                return Integer.parseInt(num);
            }
        }
        return -1;
    }

}
