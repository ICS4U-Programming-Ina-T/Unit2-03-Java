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
     **
     * The error message for exceptions.
     */
    private static final String ERROR_MESS = "ERROR: Value does not exist.";

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
         int mid = lowIndex + ((highIndex - lowIndex) / 2);
        int returnValue;

        if (lowIndex > highIndex) {
            returnValue = -1;
        }

        if (userArray[mid] == userNumber) {
            returnValue = mid;
        } else if (userNumber < userArray[mid]) {
            returnValue =
                binarySearch(userArray, userNumber, lowIndex, mid - 1);
        } else {
            returnValue =
                binarySearch(userArray, userNumber, mid + 1, highIndex);
        }
        return returnValue;
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
        String userNumString;
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
            userInput.close();
            System.out.println();

            try {
                searchNumber = Integer.parseInt(userNumString);

                // ensuring the user inputs an appropriate integer
                
                for (int cursor: numberArray) {
                    if (cursor != searchNumber) {
                        throw new IllegalArgumentException();
                    } else {
                        break;
                    }
                }
                
                if (searchNumber > MAX || searchNumber < MIN) {
                    throw new IllegalArgumentException();
                } else {
                    /* Using binary search to find the user's
                    chosen number in the array */
                    searchResult =
                        binarySearch(numberArray,
                            searchNumber, 0, numberArray.length - 1);

                    // Outputing the results of the search
                    System.out.println();
                    System.out.println("Your number is in index: " + searchResult);
                }
            } catch (IllegalArgumentException exception) {
                System.out.println();
                System.out.println(ERROR_MESS);
            }

            // Catches and tells the user that an error occured
        } catch (IllegalArgumentException exception) {
            System.out.println();
            System.out.println(ERROR_MESS);
        }
    }
}
