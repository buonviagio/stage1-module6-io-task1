package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        StringBuilder resultData = new StringBuilder();
        String line = "";
        try (BufferedReader fileReader = new BufferedReader(new java.io.FileReader(file))) {
            while (line != null) {
                line = fileReader.readLine();
                if (line != null) {
                    resultData.append(line).append(" ");
                }
            }

            String[][] array = stringToMap(resultData.toString().split(" "));

            for (int i = 0; i < array.length; i++) {
                if (array[i][0].equals("Name")) {
                    profile.setName(array[i][1]);
                }
                if (array[i][0].equals("Age")) {
                    profile.setAge(Integer.parseInt(array[i][1]));
                }
                if (array[i][0].equals("Email")) {
                    profile.setEmail(array[i][1]);
                }
                if (array[i][0].equals("Phone")) {
                    profile.setPhone(Long.parseLong(array[i][1]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public String[][] stringToMap(String[] arr) {
        String[][] array = new String[arr.length / 2][2];
        int counter = 0;
        String str;
        int tmp;
        for (int i = 0; i < array.length; i++) {
            str = arr[counter++];
            tmp = str.indexOf(":");
            array[i][0] = str.substring(0, tmp);
            array[i][1] = arr[counter++];
        }
        return array;
    }
}
