package org.vsu.algorithms;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt {

    public static List<Integer> execute(String text, String sample) {
        List<Integer> results = new ArrayList<>();

        int[] bp = Helpers.prefixBorderArray(sample);

        int textLength = text.length();
        int sampleLength = sample.length();
        int i = 0, j = 0; // Текущий индекс в образце

        // ABOBOBAABOBOBAABOBA
        //  BOBOB
        //  00123

        // Пока "указатель" i не дойдет до конца текста
        while (i < textLength) {

            // Сравниваем посимвольно текст и образец
            if (text.charAt(i) == sample.charAt(j)) {
                i++;
                j++;

                // Дошли до конца образца - найдено вхождение
                if (j == sampleLength) {
                    results.add(i - j);
                    j = bp[j - 1];
                }
            } else {
                // Найдено несовпадение
                // Возможно 2 случая
                if (j > 0) {
                    // "Сдвигаем" образец на максимально возможную длину, чтобы не пропустить вхождения
                    j = bp[j - 1];
                } else {
                    // "Сдвигаем" образец на одну позицию
                    i++;
                }
            }
        }

        return results;
    }

}
