package com;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    public static void main(String... args) throws IOException {

        String[] keywordsArray = {
                "username",
                "surname",
                "company",
                "password"
        };

        String[] arraysOfLines = new String[keywordsArray.length];
        String[] outputArray = new String[keywordsArray.length];

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        File file = new File("parameters.txt");

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int index = 0;
        while ((line = bufferedReader.readLine()) != null) {
            arraysOfLines[index] = line;
            index++;
        }

        bufferedReader.close();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Properties properties = new Properties();

        InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("user_config_data_set.properties");
        properties.load(resourceAsStream);

        for (int i = 0; i < arraysOfLines.length; i++) {

            if ((properties.get(keywordsArray[i])).equals(arraysOfLines[i])) {
                outputArray[i] = arraysOfLines[i];
            } else {
                outputArray[i] = properties.get(keywordsArray[i]) + " NOT AVAILABLE";
            }
        }

        System.out.println();

        showArrayOfLines(outputArray);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String s = Arrays.toString(outputArray);

        File outputFile = new File("output_parameters.txt");
        FileWriter fileWriter = new FileWriter(outputFile, true);

        for (int i = 0; i < outputArray.length; i++){
            fileWriter.append(outputArray[i] + "\n");
        }

        fileWriter.flush();
        fileWriter.close();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void showArrayOfLines(String[] arraysOfLines) {
        for (String value : arraysOfLines) {
            System.out.println(value);
        }
    }
}
