package org.vsu.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Naive {

    public static List<Integer> search(String text, String sample) {

//        System.out.println(String.format("Text: \"%s\"", text));
//        System.out.println(String.format("Sample: \"%s\"", sample));

        int textLength = text.length();
        int sampleLength = sample.length();

        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < textLength - sampleLength + 1; i++) {

            int j = 0;

            while (j < sampleLength && sample.charAt(j) == text.charAt(i + j)) {
                j++;
            }

            if (j == sampleLength) {
                indexes.add(i);
            }
        }

        return indexes;

//        if (indexes.isEmpty()) {
//            System.out.println("No Samples Found");
//        } else {
//            System.out.println(String.format("%d samples found", indexes.size()));
//            System.out.println("Sample Indexes");
//            for (Integer index: indexes) {
//                System.out.println(String.format("[%d]-[%d]", index, index + sampleLength - 1));
//            }
//        }
    }

    public static void maxBorder(String text) {

        System.out.println(String.format("Text: \"%s\"", text));

        int length = text.length();
        for (int i = length - 1; i > 0; i--) {

            int j = 0;

            while (j < i && text.charAt(j) == text.charAt(length - i + j)) {
                j++;
            }

            if (j == i) {
                String border = text.substring(0, j);
                System.out.println(String.format("Max Border Found => \"%s\", Size: %d", border, border.length()));
                return;
            }
        }

        System.out.println(String.format("No Borders Found"));
    }


}
