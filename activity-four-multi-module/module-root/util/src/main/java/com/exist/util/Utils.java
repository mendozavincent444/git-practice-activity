package com.exist.util;

import com.exist.model.Pair;
import com.exist.service.Service;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils
{
    public static List<List<Pair>> readFromFile(List<String> lines, String fileName, List<List<Pair>> table, int rows) throws IOException {

        int numberOfRows = lines.size();
        table = new ArrayList<>();

        for (int i = 0; i < numberOfRows; i++) {
            List<Pair> newRow = new ArrayList<>();
            table.add(newRow);
        }

        rows = numberOfRows;

        String line;
        int rowCounter = 0;

        while (rowCounter < numberOfRows) {

            String[] rowCells = lines.get(rowCounter).split("   ");

            for (int i = 0; i < rowCells.length; i++) {

                int indexOfDelimiter = rowCells[i].indexOf(",");

                String key = rowCells[i].substring(0, indexOfDelimiter);

                String value = rowCells[i].substring(indexOfDelimiter + 1);

                table.get(rowCounter).add(new Pair(key, value));
            }

            System.out.println();

            rowCounter++;
        }

        return table;
    }




    public static List<List<Pair>> generateNewFile(String fileName, List<List<Pair>> table, Integer rows, Integer columns) {
        Scanner console = new Scanner(System.in);

        try {

            System.out.print("Rows: ");
            int rowsInput = console.nextInt();

            if (rowsInput <= 0) {
                System.out.println("Error: Invalid Input");
            }

            System.out.print("Columns: ");
            int columnsInput = console.nextInt();

            if (columnsInput <= 0) {
                System.out.println("Error: Invalid Input");
            }

            table = new ArrayList<>();

            for (int i = 0; i < rowsInput; i++) {
                List<Pair> newRow = new ArrayList<>();
                table.add(newRow);

            }

            rows = rowsInput;
            columns = columnsInput;

            Service.writeToTable(table, columns);
            writeToFile(fileName, table);
            Service.printTable(table);



        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid: Array Index is out of bounds");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return table;
    }


    public static void writeToFile(String fileName, List<List<Pair>> table) throws IOException {


        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {


                stringBuilder.append(table.get(i).get(j).getKeyValuePair() + "   ");


            }
            stringBuilder.append("\n");
        }


        FileUtils.write(new File(fileName), stringBuilder.toString(), "UTF-8");
    }
}
