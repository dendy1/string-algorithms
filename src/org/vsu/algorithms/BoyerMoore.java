package org.vsu.algorithms;

import java.util.*;

public class BoyerMoore {

    private static Map<Character, List<Integer>> badCharacterShiftMapStrong(String text) {
        Map<Character, List<Integer>> positions = new HashMap<>();
        int textLength = text.length();

        for (int i = textLength - 1; i > -1; i--) {
            char ch = text.charAt(i);

            if (!positions.containsKey(ch)) {
                positions.put(ch, new ArrayList<>());
            }

            positions.get(ch).add(i);
        }

        return positions;
    }

    private static int badCharacterShift(Map<Character, List<Integer>> shifts, char bad, int posBad) {

        if (posBad < 0)
            return 1;

        int pos = -1;
        // Поиск ближнего слева от позиции текущего "плохого символа"
        if (shifts.containsKey(bad)) {
            List<Integer> positions = shifts.get(bad);
            for (Integer position : positions) {
                if (position < posBad) {
                    pos = position;
                    break;
                }
            }
        }

        return posBad - pos;
    }

    private static Map<Character, Integer> bestSuffixShiftMap(String text) {
        Map<Character, Integer> positions = new HashMap<>();
        int textLength = text.length();

        // ABOBA
        // 4321*
        for (int i = textLength - 1; i > 0; i--) {
            char ch = text.charAt(i);

            if (!positions.containsKey(ch)) {
                positions.put(ch, i);
            }
        }

        return positions;
    }

    private static int goodSuffixShift(int[] nearestSuffix, int[] br, int posBad, int sampleLength) {
        if (posBad == sampleLength - 1) {
            // Хорошего суффикса нет
            return 1;
        }

        if (posBad < 0) {
            // Совпадение образца -> сдвиг по наибольшей грани
            return sampleLength - br[0];
        }

        // Вхождение левой ближней копии суффикса
        int suffixCopyPos = nearestSuffix[posBad];
        if (suffixCopyPos > -1) {
            return posBad - suffixCopyPos + 1;
        } else {
            // Сдвиг по огранниченной наибольшей грани
            return sampleLength - br[posBad];
        }
    }

    public static List<Integer> execute(String text, String sample) {

        List<Integer> results = new ArrayList<>();

        int textLength = text.length();
        int sampleLength = sample.length();

        // Bad Character preprocessing
        Map<Character, List<Integer>> badCharacterShifts = badCharacterShiftMapStrong(sample);

        // Good Suffix preprocessing
        int[] borderSuffix = Helpers.suffixBorderArray(sample);
        int[] br = Helpers.borderSuffixToBR(borderSuffix, sampleLength);
        borderSuffix = Helpers.borderSuffixModified(borderSuffix, sampleLength);
        int[] nearestSuffix = Helpers.borderSuffixToNearestSuffix(borderSuffix, sampleLength);

        int textRightBorder = sampleLength;
        while (textRightBorder <= textLength) {

            // Сравнение образца с текстом справа налево
            int i = textRightBorder - 1;    // Правая позиция в тексте
            int j = sampleLength - 1;       // Правая позиция в образце

            // Сравниваем символы образца и текста
            while (j >= 0 && text.charAt(i) == sample.charAt(j)) {
                // Если нашли несовпадение - останавливаем сравнение
                i--; j--;
            }

            // После несовпадения сдвигаем образец так, чтобы совместить
            // несовпавший символ текста с таким же символом образца.
            // (Если такого символа в образце нет, сдвигаем на всю длину образца.)

            // Если дошли до начальной позиции образца - найдена подстрока
            if (j < 0) {
                results.add(i + 1);
                textRightBorder += 1;
            } else {
                // Иначе сдвигаем образец на максимально возможную длину
                int shift = Math.max(
                        badCharacterShift(badCharacterShifts, text.charAt(i), j),
                        goodSuffixShift(nearestSuffix, br, j, sampleLength)
                );
                textRightBorder += shift;
            }
        }

        return results;
    }
}
