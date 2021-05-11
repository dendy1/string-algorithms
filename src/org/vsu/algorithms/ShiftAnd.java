package org.vsu.algorithms;

import java.util.*;

public class ShiftAnd {

    public static List<Integer> execute(String text, String sample) {
        List<Integer> results = new ArrayList<>();

        char chStart = '0', chEnd = 'z';

        int textLength = text.length();
        int sampleLength = sample.length();

        Map<Character, int[]> bitMap = alphabetMap(chStart, chEnd, sampleLength);
        for (int i = 0; i < sampleLength; i++) {
            char ch = sample.charAt(i);
            int[] bits = bitMap.get(ch);
            bits[i] = 1;
            bitMap.put(sample.charAt(i), bits);
        }

//        for (Map.Entry<Character, int[]> entry: bitMap.entrySet()) {
//            System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
//        }

        int[][] M = new int[textLength][sampleLength];
        M[0] = and(bitMap.get(text.charAt(0)), 1);

        for (int i = 1; i < textLength; i++) {
            M[i] = and(bitMap.get(text.charAt(i)), bitShift(M[i - 1]));

            if (M[i][sampleLength - 1] == 1) {
                results.add(i - sampleLength + 1);
            }
        }

//        System.out.print("   ");
//        for (int i = 0; i < sampleLength; i++) {
//            System.out.print(sample.charAt(i) + "  ");
//        }
//        System.out.println();
//        for (int i = 0; i < textLength; i++) {
//            System.out.println(text.charAt(i) + " " + Arrays.toString(M[i]));
//        }

        return results;
    }

    private static int[] and(int[] vector1, int[] vector2) {
        int[] result = new int[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] & vector2[i];
        }
        return result;
    }

    private static int[] and(int[] vector, int value) {
        int[] result = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] & value;
        }
        return result;
    }

    private static int[] bitShift(int[] vector) {
        int[] result = new int[vector.length];

        for (int i = 0; i < vector.length - 1; i++) {
            result[i + 1] = vector[i];
        }
        result[0] = 1;

        return result;
    }

    private static Map<Character, int[]> alphabetMap(char startChar, char endChar, int length) {
        Map<Character, int[]> map = new HashMap<>();
        for (char ch = startChar; ch < endChar; ch++) {
            map.put(ch, new int[length]);
        }
        return map;
    }
}
