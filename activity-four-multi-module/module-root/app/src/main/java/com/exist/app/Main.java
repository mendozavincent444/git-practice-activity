package com.exist.app;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;
import java.util.List;

import com.exist.model.Pair;
import com.exist.service.Service;
import com.exist.util.Utils;
import org.apache.commons.io.FileUtils;

public class Main {
    public static Scanner console = new Scanner(System.in);
    public static List<List<Pair>> table;

    public static Integer rows;
    public static Integer columns;

    public static void main(String[] args) throws IOException {

        boolean noSuchFileException = false;

        System.out.print("Enter the file name: ");
        String fileName = console.next();

        int choice;

        try {
            List<String> lines = FileUtils.readLines(new File(fileName), "UTF-8");

            table = Utils.readFromFile(lines, fileName, table, rows);
            Service.printTable(table);


        } catch (NoSuchFileException e) {
            System.out.println("File not found.");
            noSuchFileException = true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            noSuchFileException = true;
        }



        if (noSuchFileException) {
            fileName = "myFile.txt";
            table = Utils.generateNewFile(fileName, table, rows, columns);
        }

        rows = Service.getTableRows(table);
        columns = Service.getTableColumns(table);


        do {

            System.out.println("0: Exit");
            System.out.println("1: Print");
            System.out.println("2: Search");
            System.out.println("3: Edit");
            System.out.println("4: Sort");
            System.out.println("5: Add Column");
            System.out.println("6: Reset");

            System.out.print("Input: ");
            choice = console.nextInt();


            switch (choice) {
                case 1:
                    Service.printTable(table);
                    break;


                case 2:

                    System.out.print("Search: ");
                    String searchedValue = console.next();

                    Service.searchTable(table, searchedValue);
                    break;

                case 3:

                    System.out.print("Row: ");
                    int inputRow = console.nextInt();

                    if (inputRow >= rows || inputRow < 0) {
                        System.out.println("Error: Invalid row input.");
                        break;
                    }

                    System.out.print("Column: ");
                    int inputColumn = console.nextInt();


                    System.out.print("Change key(Enter key) or retain(r): ");
                    String keyInput = console.next();

                    if (keyInput.equals("r")) {
                        keyInput = null;
                    }

                    System.out.print("Change value(Enter value) or retain(r): ");
                    String valueInput = console.next();

                    if (valueInput.equals("r")) {
                        valueInput = null;
                    }

                    Service.editTable(inputRow, inputColumn, keyInput, valueInput, table);
                    Utils.writeToFile(fileName, table);


                    break;

                case 4:


                    System.out.print("Sort ascending(asc) or descending(desc): ");

                    String sortDirection = console.next();

                    Service.sortTable(sortDirection, table);

                    Utils.writeToFile(fileName, table);

                    break;


                case 5:

                    System.out.print("Row to add column: ");
                    int rowToAddColumn = console.nextInt();

                    if (rowToAddColumn >= rows) {
                        System.out.println("Error: Invalid input.");
                        break;
                    }

                    Service.addColumnToTable(rowToAddColumn, table);

                    break;


                case 6:
                    Utils.generateNewFile(fileName, table, rows, columns);
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }


        } while (choice != 0);


    }




}


