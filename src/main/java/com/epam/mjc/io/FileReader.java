package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReader {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        File file = new File("src/main/resources/Profile.txt");
        System.out.println(fileReader.getDataFromFile(file));
    }

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        String resultData = "";
        String line = "";
        try (BufferedReader fileReader = new BufferedReader(new java.io.FileReader(file))) {
            while (line != null){
                line = fileReader.readLine();
                if(line != null) {
                    resultData += line+ " ";
                }
            }

            String [][] array = stringToMap(resultData.split(" "));

            for (int i = 0; i < array.length ; i++) {
                if(array[i][0].equals("Name")){
                    profile.setName(array[i][1]);
                }
                if(array[i][0].equals("Age")){
                    profile.setAge(Integer.parseInt(array[i][1]));
                }
                if(array[i][0].equals("Email")){
                    profile.setEmail(array[i][1]);
                }
                if(array[i][0].equals("Phone")){
                    profile.setPhone(Long.parseLong(array[i][1]));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return profile;
    }

    public String [][] stringToMap (String [] arr){
        String [][] array = new String[arr.length/2][2];
        int counter = 0;
        String str;
        int tmp;
        for (int i = 0; i < array.length; i++) {
            str= arr[counter++];
            tmp= str.indexOf(":");
            array[i][0] = str.substring(0, tmp);
            array[i][1]= arr[counter++];
        }
        /*for (String [] s : array){
            for (String g : s){
                System.out.print(g);
            }
            System.out.println();
        }*/
        return array;
    }
}
