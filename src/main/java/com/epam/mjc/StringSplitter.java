package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        List<String> tokens = new ArrayList<>();

        Iterator<String> iterator = delimiters.iterator();

        StringTokenizer stringTokenizer = new StringTokenizer(source, iterator.next());

        StringBuilder regex = new StringBuilder();

        while (iterator.hasNext()) {
            String next = iterator.next();
            regex.append(next);
        }

        regex = new StringBuilder("[").append(regex.append("]"));
        
        while (stringTokenizer.hasMoreTokens()) {

            String x = stringTokenizer.nextToken().strip();

            String[] xs = x.split(regex.toString());

            for (String e : xs) {
                if (e.length() > 0) {
                    tokens.add(e);
                }
            }
        }
        return tokens;
//        throw new UnsupportedOperationException("You should implement this method.");
    }
}
