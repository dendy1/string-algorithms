package org.vsu.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KarpRabin {

    private static final int p = 31;
    private static final int q = (int)Math.pow(2, 8) - 1;

    // Вычисление полиномиальной хеш-функции с помощью схемы Горнера при x = p
    // char[] s - коэффициенты многочлена (цифровые значения символов)
    // m - степень многочлена (длина подстроки)
    // q - модуль для уменьшения числа
    private static int gorner2Mod(String s, int m) {
        int res = 0;
        for (int i = 0; i < m; i++) {
            res = (res * p + s.charAt(i)) % q;
        }
        return res;
    }

    public static List<Integer> execute(String text, String sample) {
        List<Integer> results = new ArrayList<>();

        int textLength = text.length();
        int sampleLength = sample.length();

        // Для вычисления p^(sampleLength - 1) mod q
        int p2m = 1;
        for (int i = 0; i < sampleLength - 1; i++) {
            p2m = (p2m * p) % q;
        }

        // Вычисляем хеш-значение для префикса текста длины образца
        int textHash = gorner2Mod(text, sampleLength);

        // Вычисляем хеш-значение для образца
        int sampleHash = gorner2Mod(sample, sampleLength);

        if (sampleHash == textHash) {
            if (isEqual(text, sample, 0)) {
                results.add(0);
            }
        }

        for (int i = 1; i <= textLength - sampleLength; i++) {
            // Пересчитываем хэш для следующего окна
            // Вычитаем значение предыдущего символа (text.charAt(i))
            // Прибавляем значение для следующего символа (text.charAt(i + sampleLength))
            // Также по схеме Горнера
            textHash = ((textHash - p2m * text.charAt(i - 1)) * p + text.charAt(i + sampleLength - 1)) % q;
            if (textHash < 0) {
                textHash += q;
            }

            // Хеш-значения для строк sample и text[i..i + sampleLength] равны
            if (sampleHash == textHash) {
                // Проверка, равны ли строки, т.к. возможна коллизия
                if (isEqual(text, sample, i)) {
                    results.add(i);
                }
            }
        }

        return results;
    }

    private static boolean isEqual(String text, String sample, int start) {
        int sampleLength = sample.length();

        int k = 0;
        while (k < sampleLength && text.charAt(start + k) == sample.charAt(k)) {
            k++;
        }

        if (k == sampleLength)
            return true;

        return false;
    }
}
