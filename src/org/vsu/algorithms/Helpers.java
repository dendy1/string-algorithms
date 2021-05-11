package org.vsu.algorithms;

public class Helpers {

    public static int[] prefixBorderArray(String text) {
        int textLength = text.length();
        int[] bp = new int[textLength];
        bp[0] = 0;

        for (int i = 1; i < textLength; i++) {
            // На каждом этапе вычисления префикс-функции она не более чем на единицу превосходит
            // любые предыдущие значения
            int borderRight = bp[i - 1];

            while (borderRight != 0 && text.charAt(i) != text.charAt(borderRight)) {
                borderRight = bp[borderRight - 1];
            }

            bp[i] = (text.charAt(i) == text.charAt(borderRight)) ? borderRight + 1 : 0;
        }

        return bp;
    }

    public static int[] suffixBorderArray(String text) {
        int textLength = text.length();
        int[] bs = new int[textLength];
        bs[textLength - 1] = 0;

        for (int i = textLength - 2; i > -1; i--) {
            int borderLeft = bs[i + 1];

            while (borderLeft != 0 && text.charAt(i) != text.charAt(textLength - borderLeft - 1)) {
                borderLeft = bs[textLength - borderLeft];
            }

            bs[i] = (text.charAt(i) == text.charAt(textLength - borderLeft - 1)) ? borderLeft + 1 : 0;
        }

        return bs;
    }

    public static int[] borderSuffixModified(int[] bs, int textLength) {
        int[] bsm = new int[textLength];
        bsm[0] = bs[0];
        bsm[textLength - 1] = 0;

        for (int i = textLength - 2; i > 0; i--) {

            if (bs[i] != 0 && bs[i] + 1 == bs[i - 1]) {
                bsm[i] = bsm[textLength - bs[i]];
            } else {
                bsm[i] = bs[i];
            }
        }

        return bsm;
    }

    public static int[] borderSuffixToNearestSuffix(int[] bs, int textLength) {
        int[] ns = new int[textLength];
        for (int i = 0; i < textLength; i++) {
            ns[i] = -1;
        }

        for (int i = 0; i < textLength - 1; i++) {
            if (bs[i] > 0) {
                int j = textLength - bs[i] - 1;
                ns[j] = i;
            }
        }

        return ns;
    }

    public static int[] borderSuffixToBR(int[] bs, int textLength) {
        int[] br = new int[textLength];

        int currentBorder = bs[0];
        int k = 0;

        while (currentBorder != 0) {
            for (; k < textLength - currentBorder; k++) {
                br[k] = currentBorder;
            }

            currentBorder = bs[k];
        }

        for (; k < textLength; k++) {
            br[k] = 0;
        }

        return br;
    }
}
