package org.vsu;

import org.vsu.algorithms.*;

import java.util.Arrays;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        String text = "ATGTATGTATGT".toLowerCase(Locale.ROOT);
        String sample = "ATGT".toLowerCase(Locale.ROOT);

        System.out.println(Arrays.toString(text.toCharArray()));
        System.out.println(Arrays.toString(KnuthMorrisPratt.execute(text, sample).toArray()));
        System.out.println(Arrays.toString(BoyerMoore.execute(text, sample).toArray()));
        System.out.println(Arrays.toString(KarpRabin.execute(text, sample).toArray()));
        System.out.println(Arrays.toString(ShiftAnd.execute(text, sample).toArray()));
    }
}
