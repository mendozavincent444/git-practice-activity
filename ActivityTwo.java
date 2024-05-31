import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;


public class ActivityTwo {
    public static Scanner console = new Scanner(System.in);
    public static List<List<Pair>> table;

    public static int rows;
    public static int columns;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader;

        boolean isFileNameNotFound = false;

        System.out.print("Enter the file name: ");
        String fileName = console.next();

        int choice;

        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));

            table = readFromFile(bufferedReader, fileName);
            printTable(table);


        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            isFileNameNotFound = true;
        }


        if (isFileNameNotFound) {
            fileName = "myFile.txt";
            generateNewFile(fileName);
        }


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
                    printTable(table);
                    break;


                case 2:

                    System.out.print("Search: ");
                    String searchedValue = console.next();

                    searchTable(table, searchedValue);
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

                    editTable(inputRow, inputColumn, keyInput, valueInput);
                    writeToFile(fileName);


                    break;

                case 4:


                    System.out.print("Sort ascending(asc) or descending(desc): ");

                    String sortDirection = console.next();

                    sortTable(sortDirection);

                    writeToFile(fileName);

                    break;


                case 5:

                    System.out.print("Row to add column: ");
                    int rowToAddColumn = console.nextInt();

                    if (rowToAddColumn >= rows) {
                        System.out.println("Error: Invalid input.");
                        break;
                    }

                    addColumnToTable(rowToAddColumn);

                    break;


                case 6:
                    generateNewFile(fileName);
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }


        } while (choice != 0);


    }


    private static List<List<Pair>> readFromFile(BufferedReader bufferedReader, String fileName) throws IOException {

        int numberOfRows = countNumberOfLines(bufferedReader);
        table = new ArrayList<>();

        for (int i = 0; i < numberOfRows; i++) {
            List<Pair> newRow = new ArrayList<>();
            table.add(newRow);
        }

        rows = numberOfRows;

        bufferedReader = new BufferedReader(new FileReader(fileName));

        String line;
        int rowCounter = 0;

        while ((line = bufferedReader.readLine()) != null) {

            String[] rowCells = line.split("   ");

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

    private static int countNumberOfLines(BufferedReader bufferedReader) {

        int counter = 0;

        try {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                counter++;
            }

            bufferedReader.close();
        } catch (IOException e) {

        }

        return counter;


    }

    private static void sortTable(String sortDirection) {


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

    private static String getKey(String keyValuePair) {

        int index = keyValuePair.indexOf("~");

        return keyValuePair.substring(0, index);
    }

    private static String getValue(String keyValuePair) {

        int index = keyValuePair.indexOf("~");

        return keyValuePair.substring(index + 1);

    }

    private static String[] reverseArray(String[] flatArray) {

        String[] reversedArray = new String[flatArray.length];

        int index = 0;
        for (int i = flatArray.length - 1; 0 <= i; i--) {
            reversedArray[index++] = flatArray[i];
        }

        return reversedArray;
    }



    private static void editTable(int row, int column, String keyField, String valueField) {

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

    private static void printTable(List<List<Pair>> table) {

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                System.out.print(table.get(i).get(j).getKeyValuePair() + " ");
            }
            System.out.println();
        }

    }

    private static int getTableSize() {

        int counter = 0;

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                counter++;
            }
        }

        return counter;
    }

    private static void addColumnToTable(int row) {

        try {
            table.get(row).add(new Pair(generateCharacters(), generateCharacters()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Index out of bounds");
        }

    }


    private static String generateCharacters() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i < 3; i++) {
            stringBuilder.append((char) (random.nextInt(127 - 45) + 45));
        }

        return stringBuilder.toString();
    }

    private static void searchTable(List<List<Pair>> table, String searchedValue) {


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

    private static int countOccurrence(String cellValue, String searchedValue) {

        int count = cellValue.length() - cellValue.replaceAll(searchedValue,"").length();

        return count;
    }


    private static void generateNewFile(String fileName) {
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

            writeToTable();
            writeToFile(fileName);
            printTable(table);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid: Array Index is out of bounds");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<List<Pair>> writeToTable() throws IOException {


        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < columns; j++) {

                table.get(i).add(new Pair(generateCharacters(), generateCharacters()));
            }


        }

        return table;
    }

    private static void writeToFile(String fileName) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {

                bufferedWriter.write(table.get(i).get(j).getKeyValuePair() + "   ");

            }
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

}

