package com.exist.service;

import com.exist.model.Pair;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Service {

    public static void sortTable(String sortDirection, List<List<Pair>> table) {


        if (sortDirection.equals("asc")) {

            for (int i = 0; i < table.size(); i++) {
                Collections.sort(table.get(i));
            }


        } else if (sortDirection.equals("desc")) {

            for (int i = 0; i < table.size(); i++) {
                Collections.reverse(table.get(i));
            }

        } else {
            return;
        }


    }

    public static int getTableRows(List<List<Pair>> table) {

        return table.size();
    }

    public static int getTableColumns(List<List<Pair>> table) {

        int maxColumns = 0;
        for (List<Pair> row : table) {
            int numberOfColumns = row.size();
            if (numberOfColumns > maxColumns) {
                maxColumns = numberOfColumns;
            }
        }

        return maxColumns;
    }

    public static String getKey(String keyValuePair) {

        int index = keyValuePair.indexOf("~");

        return keyValuePair.substring(0, index);
    }

    public static String getValue(String keyValuePair) {

        int index = keyValuePair.indexOf("~");

        return keyValuePair.substring(index + 1);

    }

    public static String[] reverseArray(String[] flatArray) {

        String[] reversedArray = new String[flatArray.length];

        int index = 0;
        for (int i = flatArray.length - 1; 0 <= i; i--) {
            reversedArray[index++] = flatArray[i];
        }

        return reversedArray;
    }



    public static void editTable(int row, int column, String keyField, String valueField, List<List<Pair>> table) {

        try {

            if (keyField != null) {
                table.get(row).get(column).setKey(keyField);
            }


            if (valueField != null) {
                table.get(row).get(column).setValue(valueField);
            }



        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid: Array Index is out of bounds");
        }
    }

    public static void printTable(List<List<Pair>> table) {

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                System.out.print(table.get(i).get(j).getKeyValuePair() + " ");
            }
            System.out.println();
        }

    }


    public static void addColumnToTable(int row, List<List<Pair>> table) {

        try {
            table.get(row).add(new Pair(generateCharacters(), generateCharacters()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Index out of bounds");
        }

    }


    public static String generateCharacters() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i < 3; i++) {
            stringBuilder.append((char) (random.nextInt(127 - 45) + 45));
        }

        return stringBuilder.toString();
    }

    public static void searchTable(List<List<Pair>> table, String searchedValue) {


        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {

                Pair keyValuePair = table.get(i).get(j);

                if (keyValuePair.getKey().contains(String.valueOf(searchedValue))) {

                    int occurrence = countOccurrence(keyValuePair.getKey(), searchedValue);

                    System.out.println("[" + i + "," + j + "] - " + occurrence + " Occurrence found on the key field ");

                }

                if (keyValuePair.getValue().contains(String.valueOf(searchedValue))) {

                    int occurrence = countOccurrence(keyValuePair.getValue(), searchedValue);

                    System.out.println("[" + i + "," + j + "] - " + occurrence + " Occurrence found on the value field ");
                }

            }

        }


    }

    public static int countOccurrence(String cellValue, String searchedValue) {

        int count = cellValue.length() - cellValue.replaceAll(searchedValue,"").length();

        return count;
    }

    public static List<List<Pair>> writeToTable(List<List<Pair>> table, int columns) throws IOException {


        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < columns; j++) {

                table.get(i).add(new Pair(generateCharacters(), generateCharacters()));
            }


        }

        return table;
    }
}