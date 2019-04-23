/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.pars;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author daan-
 */
public class TaglistParser {

    public static String fromTagList(String[] tags) {
        if (tags == null || tags.length <= 0) {
            return "";
        }
        String tagline = tags[0];
        if (tags.length > 1) {
            for (int i = 1; i < tags.length; i++) {
                tagline += "," + tags[i];
            }
        }
        return tagline;
    }

    public static String[] toTagList(String tagline) {
        if (null != tagline) {
            tagline = tagline.trim();
            if (tagline.length() > 0 && !tagline.contains(",")) {
                return new String[]{tagline};
            }
            if (tagline.length() > 0) {
                return tagline.split(",");
            }

        }
        return new String[]{};
    }

    public static Set<String> toTagSet(String tagline) {
        Set<String> set = new HashSet<>();
        if (null != tagline && !tagline.trim().isEmpty()) {
            if (!tagline.contains(",")) {
                set.add(tagline.trim());
            } else {
                String[] tags = tagline.split(",");
                for (String tag : tags) {
                    if (tag != null) {
                        set.add(tag);
                    }
                }
            }
        }
        return set;
    }

    public static boolean contains(String tagline, String tag) {
        if (null != tagline && !tagline.contains(",")) {
            return tagline.equalsIgnoreCase(tag);
        }
        if (null != tagline && tagline.contains(",")) {
            String[] tags = tagline.split(",");
            for (String lineTag : tags) {
                if (contains(lineTag, tag)) {
                    return true;
                }
            }
        }
        return false;
    }
}
