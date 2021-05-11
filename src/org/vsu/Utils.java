package org.vsu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {

    public static String readStringFromFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
