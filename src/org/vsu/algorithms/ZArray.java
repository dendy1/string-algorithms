package org.vsu.algorithms;

import java.util.Arrays;

public class ZArray {

    // abracadabra
    // Z-функция - массив, i-й элемент которого
    // равен длине максимального общего префикса строки s и подстроки s[i:)
    public static void zArrayNaive(String s) {
        int length = s.length();
        int[] z = new int[length];
        z[0] = 0;

        for (int i = 1; i < length; i++) {
            int j = i;
            while (j < length && s.charAt(j) == s.charAt(j - i)) {
                j++;
            }
            z[i] = j - i;
        }
    }

    public static void zArrayEffective(String s) {

        int length = s.length();
        int[] z = new int[length];
        z[0] = 0;

        for (int i = 1, l = 0, r = 0; i < length; i++) {
            if (i >= r) {
                // Позиция i не покрыта ни одним Z-блоком -> просто вычисляем наивным алгоритмом
                z[i] = longestCommonPrefix(s, length, 0, i);
                l = i;
                r = l + z[i];
            } else {
                // Позиция i содержится в Z-блоке s[l; r-1], который совпадает с суффиксом s[0; r-l]
                // | s[0; r-l] | = z[l]
                // Используем r и l для первоначальной инициализации наивного алгоритма

                int j = i - l;
                if (z[j] < r - i) {
                    // Ранее вычисленный Z-блок для позиции j полностью содержится в подстроке s[i; r]
                    // Это значит, что можно скопировать значение из старого блока
                    z[i] = z[j];
                } else {
                    // Ранее вычисленный Z-блок для позиции j выходит за границу подстроки s[i; r]
                    //                                    или примыкает к ней
                    z[i] = r - i + longestCommonPrefix(s, length, r - i, r);
                    l = i;
                    r = l + z[i];
                }
            }
        }

        System.out.printf("INPUT STRING: \"%s\"%n", s);
        System.out.println(Arrays.toString(s.toCharArray()));
        System.out.println(Arrays.toString(z));
    }

    private static int longestCommonPrefix(String s, int length, int i1, int i2) {
        int lcp = 0;
        while (i1 < length && i2 < length && s.charAt(i1++) == s.charAt(i2++)) {
            lcp++;
        }
        return lcp;
    }
}
