import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The RecBinarySearch program implements an application that
 * generates 250 random numbers in an array and allows the
 * user to search the array for a number.
 *
 * @author  Ina Tolo
 * @version 1.0
 * @since   2022-04-04
 */

class RecBinarySearch {
    /**
     * The min number for array.
     */
    private static final int MIN = 0;
    /**
     * The max number for array.
     */
    private static final int MAX = 999;
    /**
     * The number of elements in the array.
     */
    private static final int ARRAY_SIZE = 250;

    /**
     * Creating private constructor.
     *
     * @throws IllegalStateException when called.
     */
    private RecBinarySearch() {
        // Prevent instantiation
        // Optional: throw an exception e.g. AssertionError
        // if this ever *is* called
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
     * Function finds the index of a number, using Binary Search recursively.
     *
     * @param userArray accepted to function
     * @param userNumber accepted to function
     * @param lowIndex accepted to function
     * @param highIndex accepted to function
     * @return binarySearch copied to main function
     */
    static int binarySearch(int[] userArray, int userNumber,
        int lowIndex, int highIndex) {
        // declaring variables
        final int mid = lowIndex + ((highIndex - lowIndex) / 2);

        if (lowIndex > highIndex) {
            return -1;
        }

        // checks the index of where the user's number is
        if (userArray[mid] == userNumber) {
            return mid;
        } else if (userNumber < userArray[mid]) {
            return binarySearch(userArray, userNumber, lowIndex, mid - 1);
        } else {
            return binarySearch(userArray, userNumber, mid + 1, highIndex);
        }
    }

    /**
     * Main entry into the program.
     *
     * @param args nothing passed in
     */
    public static void main(String[] args) {
        // declaring variables
        final Scanner userInput = new Scanner(System.in);
        final Random randNumber = new Random();
        final int[] randomNumberArray = new int[ARRAY_SIZE];
        final int[] numberArray;
        final int searchNumber;
        final int searchResult;
        final String userNumString;
        String padded;

        System.out.println("Binary Search Program");

        try {
            // adding numbers to the array
            for (int counter = 0; counter
                < randomNumberArray.length; counter++) {
                randomNumberArray[counter] = randNumber.nextInt(MAX) + 1;
            }

            // sorting the array
            numberArray = randomNumberArray;
            Arrays.sort(numberArray);

            System.out.print("\nSorted list of numbers:\n");
            for (int element: numberArray) {
                padded = String.format("%03d", element);
                System.out.print(padded + ", ");
            }
            System.out.print("\n\n");

            // getting user input as to what number they wish to search for
            System.out.print("What number are you searching for in the array");
            System.out.print(" (integer between 0 and 999): ");
            userNumString = userInput.nextLine();
            // searchNumber = userInput.nextInt();
            userInput.close();
            System.out.println();

            final int tempVar = Integer.parseInt(userNumString);
            final int numInt = Integer.parseInt(userNumString);

            if (numInt == tempVar) {
                searchNumber = Integer.parseInt(userNumString);
            } else {
                throw new IllegalArgumentException();
            }

            // ensuring the user inputs an appropriate integer
            if (searchNumber > MAX || searchNumber < MIN) {
                throw new IllegalArgumentException();
            } else {
                /* Using binary search to find the user's
                chosen number in the array */
                searchResult =
                    binarySearch(numberArray,
                        searchNumber, 0, numberArray.length - 1);

                // outputting the results of the search
                if (searchResult == -1) {
                    System.out.println("Value does not exist in the array.");
                } else {
                    System.out.println("Your number is in index: " + searchResult);
                }
            }
            // catches and tells the user that an error occurred
        } catch (IllegalArgumentException exception) {
            System.out.println("ERROR: Invalid Input");
        }
    }
}
