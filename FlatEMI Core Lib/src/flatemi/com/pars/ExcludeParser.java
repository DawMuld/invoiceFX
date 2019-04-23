/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.pars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author daan-
 */
public class ExcludeParser {

    public static boolean contains(File excludeFile, int key) {
        Set<Integer> excludeSet = parseSet(excludeFile);
        return excludeSet.contains(key);

    }

    public static Set<Integer> parseSet(File file) {
        Set<Integer> set = new HashSet<>();
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                for (line = reader.readLine(); null != line; line = reader.readLine()) {
                    int[] keys = parseLine(line);
                    if (null != keys && keys.length > 0) {
                        for (int key : keys) {
                            set.add(key);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return set;
    }

    private static int[] parseLine(String line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
