import java.util.Scanner;
import java.util.Random;

public class ActivityOne {
    public static Scanner keyboard = new Scanner(System.in);
    public static String[][] array;
    public static int rows;
    public static int columns;

    public static void main(String[] args) {
        int choice;

        initializeTable();

        do {

            System.out.println("0: Exit");
            System.out.println("1: Print");
            System.out.println("2: Search");
            System.out.println("3: Edit");
            System.out.println("4: Reset");

            System.out.print("Input: ");
            choice = keyboard.nextInt();


            switch (choice) {
                case 1:
                    printTable(array, rows, columns);
                    break;
                case 2:

                    System.out.print("Search: ");
                    String searchValue = keyboard.next();

                    searchTable(array, rows, columns, searchValue);
                    break;
                case 3:


                    System.out.print("Row: ");
                    int inputRow = keyboard.nextInt();

                    if (inputRow >= rows || inputRow < 0) {
                        System.out.println("Error: Invalid row input.");
                        break;
                    }

                    System.out.print("Column: ");
                    int inputColumn = keyboard.nextInt();

                    if (inputColumn >= columns || inputColumn < 0) {
                        System.out.println("Error: Invalid column input.");
                        break;
                    }

                    System.out.print("New Value: ");
                    String newValue = keyboard.next();

                    editTable(array, inputRow, inputColumn, newValue);

                    break;
                case 4:

                    initializeTable();

                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }


        } while (choice != 0);
    }

    private static void initializeTable() {

        try {

            System.out.print("Enter the number of rows: ");
            int rowsInput = keyboard.nextInt();

            System.out.print("Enter the number of columns: ");
            int columnsInput = keyboard.nextInt();

            array = new String[rowsInput][columnsInput];

            rows = rowsInput;
            columns = columnsInput;

            populateTable(array, rows, columns);

        } catch (NegativeArraySizeException e) {
            System.out.println("Invalid: Array size is negative.");
        }

    }

    private static String generateCharacters() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();


        for (int i = 0; i < 3; i++) {
            stringBuilder.append((char) (random.nextInt(127 - 33) + 33));
        }

        return stringBuilder.toString();
    }

    private static void populateTable(String[][] array, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = generateCharacters();
            }
        }
    }

    private static void printTable(String[][] array, int rows, int columns) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(array[i][j] + "   ");
            }
            System.out.println();
        }

    }

    private static void searchTable(String[][] array, int rows, int columns, String searchedValue) {

        boolean isFound = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                String cellValue = array[i][j];

                if (cellValue.contains(searchedValue)) {
                    isFound = true;

                    int occurrence = countOccurrence(cellValue, searchedValue);

                    System.out.print("[" + i + "," + j + "] - " + occurrence + " Occurrence ");
                }
            }
        }

        if (!isFound) {
            System.out.print("Nothing was found.");
        }

        System.out.println();
    }

    private static int countOccurrence(String cellValue, String searchedValue) {

        int count = cellValue.length() - cellValue.replaceAll(searchedValue,"").length();

        return count;
    }

    private static void editTable(String[][] array, int row, int column, String newValue) {

        try {
            array[row][column] = newValue;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid: Array Index is out of bounds");
        }
    }
}
