//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Wardrobe Manager Tester
// Course: CS 300 Spring 2024
//
// Author: Muhammad Naheel
// Email: naheel@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.time.LocalDate;
import java.util.NoSuchElementException;


/**
 * A tester class for the Wardrobe Manager project. It contains various tests
 * to check the correctness of the Wardrobe and Clothing classes.
 */
public class WardrobeManagerTester {

    /**
     * Tests both of the Clothing constructors and all getters for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     * @author Michelle
     */
    public static boolean testClothingConstructorAndGetters() {

        // in case we get an unexpected exception from a broken implementation
        // we handle it with a try-catch to avoid our tester from crashing
        try {
            // test the 2-argument constructor
            Clothing c = new Clothing("black t-shirt", "Gildan");

            // check that the four instance data fields have been initialized correctly
            // using the getters to do this we are also checking their correctness
            // in a bad implementation either 1) the constructor didn't intialize a data field correctly
            // OR 2) the getter doesn't return the correct value
            if (!c.getDescription().equals("black t-shirt")) return false;
            if (!c.getBrand().equals("Gildan")) return false;
            if (c.getNumOfTimesWorn() != 0) return false;
            if (c.getLastWornDate() != null) return false;

            // test the 4 argument constructor
            // same idea as the previous test case
            LocalDate date = LocalDate.of(2024, 2, 14); // create a date object for last worn
            c = new Clothing("jeans", "Levi", 3, date);
            if (!c.getDescription().equals("jeans")) return false;
            if (!c.getBrand().equals("Levi")) return false;
            if (c.getNumOfTimesWorn() != 3) return false;
            if (!c.getLastWornDate().equals(date)) return false;

        } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests that both of the Clothing constructors throw the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     * @author Michelle and Hobbes
     */
    public static boolean testClothingConstructorExceptions() {
        // Here we call constructors with input that should lead to an IllegalArgumentException
        // on a correct (good) implementation. ALL of these cases SHOULD throw exceptions,
        // so we test them in separate try-catch statements to verify that each individual
        // case throws an exception.

        try {
            // test the 2 argument constructor with a blank description
            new Clothing(" ", "Gildan");

            return false; // no exception was thrown when it should have been; it's a broken implementation
        } catch (IllegalArgumentException e) {
            // check if the exception has any message; if there is NO message it's a broken implementation
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
            e.printStackTrace();
            return false;
        }

        try {
            // and make sure a blank brand will also throw an exception
            new Clothing("black t-shirt", "  ");

            return false; // no exception was thrown
        } catch (IllegalArgumentException e) {
            // check if the exception has a message
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good
            e.printStackTrace();
            return false;
        }

        try {
            // test the 4 argument constructor with a blank description
            LocalDate date = LocalDate.of(2021, 12, 25);
            new Clothing(" ", "Gildan", 4, date);

            return false; // no exception was thrown
        } catch (IllegalArgumentException e) {
            // check if the exception has a message
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good
            e.printStackTrace();
            return false;
        }

        try {
            // and verifying that a blank brand will also throw an exception
            LocalDate date = LocalDate.of(2021, 12, 25);
            new Clothing("black t-shirt", "  ", 6, date);

            return false; // no exception was thrown
        } catch (IllegalArgumentException e) {
            // check if the exception has a message,
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good
            e.printStackTrace();
            return false;
        }

        // passed all the tests!
        return true;
    }

    /**
     * Tests for the correctness of the Clothing classes' wearClothing() method.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testClothingWear() {
      try {
          // Creating a clothing object
          Clothing clothe = new Clothing("Suit", "Gucci");

          // Case 1: Checking the initial number of times worn.
          if (clothe.getNumOfTimesWorn() != 0) return false;

          // Case 2: Wearing the clothing
          clothe.wearClothing(2024, 2, 2);
          if (clothe.getNumOfTimesWorn() != 1) return false;

          // Case 3: Wearing the clothing multiple times
          clothe.wearClothing(2024, 2, 10); 
          clothe.wearClothing(2024, 3, 2); 
          if (clothe.getNumOfTimesWorn() != 3) return false;

      } catch (Exception e) {
          e.printStackTrace();
          return false;
      }
      return true;
  }


    /**
     * Tests the Wardrobe constructor and all getters for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */

    public static boolean testWardrobeConstructorAndGetters() {

        Wardrobe wardrobe = new Wardrobe(5);

        if (wardrobe.size() != 5) {
          return false;
        }

        return true;
    }

    /**
     * Tests that the Wardrobe constructor throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testWardrobeConstructorExceptions() {

        try {
            new Wardrobe(0);
            return false;
        } catch (IllegalArgumentException e) {
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests that the Wardrobe's addClothing() method throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testAddClothingExceptions() {
        try {
            Wardrobe wardrobe = new Wardrobe(2);
            Clothing clothe1 = new Clothing("Suit", "Gucci");
            Clothing clothe2 = new Clothing("Suit", "Gucci");
            wardrobe.addClothing(clothe1);
            wardrobe.addClothing(clothe2);
            return false;
        } catch (IllegalArgumentException e) {
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;


    }

    /**
     * Tests the Wardrobe's addClothing() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testAddClothing() {
        try {
            Wardrobe wardrobe = new Wardrobe(5);
            Clothing clothe1 = new Clothing("Suit", "Gucci");
            Clothing clothe2 = new Clothing("Hoodie", "Balenciaga");
            wardrobe.addClothing(clothe1);
            wardrobe.addClothing(clothe2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Tests the Wardrobe's getClothing() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testGetClothing() {

        try {
            Wardrobe wardrobe = new Wardrobe(7);
            Clothing clothe1 = new Clothing("Hoodie", "Balenciaga");
            Clothing clothe2 = new Clothing("Suit", "Gucci");
            wardrobe.addClothing(clothe1);
            wardrobe.addClothing(clothe2);
            Clothing retrievedClothing = wardrobe.getClothing("Hoodie", "Balenciaga");
            if (!retrievedClothing.equals(clothe1)) return false;
            retrievedClothing = wardrobe.getClothing("Suit", "Gucci");
            if (!retrievedClothing.equals(clothe2)) return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Tests that the Wardrobe's getClothing() method throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testGetClothingExceptions() {
        try {
            Wardrobe wardrobe = new Wardrobe(7);
            Clothing retrievedClothe = wardrobe.getClothing("Suit", "Gucci");
            return false;
        } catch (NoSuchElementException e) {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Tests that the Wardrobe's removeClothing() method throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testRemoveClothingExceptions() {
        try {
            Wardrobe wardrobe = new Wardrobe(7);
            wardrobe.removeClothing("Suit", "Gucci");
            return false;
        } catch (NoSuchElementException e) {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Tests the Wardrobe's removeClothings() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testRemoveClothing() {

        try {
            Wardrobe wardrobe = new Wardrobe(7);
            Clothing clothe1 = new Clothing("Suit", "Gucci");
            Clothing clothe2 = new Clothing("Hoodie", "Balenciaga");
            wardrobe.addClothing(clothe1);
            wardrobe.addClothing(clothe2);
            wardrobe.removeClothing("Suit", "Gucci");
            wardrobe.removeClothing("Hoodie", "Balenciaga");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Tests the Wardrobe's removeAllClothingWornBefore() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testRemoveAllClothingWornBefore() {
        try {
            Wardrobe wardrobe = new Wardrobe(7);
            Clothing clothe1 = new Clothing("Suit", "Gucci", 4, LocalDate.of(2023, 10, 26));
            Clothing clothe2 = new Clothing("Hoodie", "Balenciaga", 2, LocalDate.of(2024, 1, 19));
            wardrobe.addClothing(clothe1);
            wardrobe.addClothing(clothe2);
            wardrobe.removeAllClothingWornBefore(2024, 1, 1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Tests the Wardrobe's removeAllClothingWornNumTimes() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testRemoveAllClothingWornNumTimes() {
        try {
            Wardrobe wardrobe = new Wardrobe(7);
            Clothing clothe1 = new Clothing("Suit", "Gucci", 4, LocalDate.of(2023, 10, 26));
            Clothing clothe2 = new Clothing("Hoodie", "Balenciaga", 2, LocalDate.of(2024, 1, 19));
            wardrobe.addClothing(clothe1);
            wardrobe.addClothing(clothe2);
            wardrobe.removeAllClothingWornNumTimes(4);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Tests that the Wardrobe's parseClothing() method throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testParseClothingExceptions() {
        try {
            String clotheString = "Hoodie, Balenciaga, invalid, 2024-01-19";
            String[] stringParts = clotheString.split(",");
            String description = stringParts[0].trim();
            String brand = stringParts[1].trim();
            int timesWorn = Integer.parseInt(stringParts[2].trim()); // This line throw exception
            LocalDate lastWornDate = LocalDate.parse(stringParts[3].trim());

            return false;
        } catch (NumberFormatException e) {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Tests the Wardrobe's parseClothing method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testParseClothing() {

        try {
            String clotheString = "Hoodie, Balenciaga, 2, 2024-01-19";
            String[] parts = clotheString.split(",");
            String description = parts[0].trim();
            String brand = parts[1].trim();
            int timesWorn = Integer.parseInt(parts[2].trim());
            LocalDate lastWornDate = LocalDate.parse(parts[3].trim());

            Clothing clothing = new Clothing(description, brand, timesWorn, lastWornDate);
            if (!clothing.getDescription().equals("Hoodie")) return false;
            if (!clothing.getBrand().equals("Balenciaga")) return false;
            if (clothing.getNumOfTimesWorn() != 2) return false;
            if (!clothing.getLastWornDate().equals(LocalDate.of(2024, 1, 19))) return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Runs all testing methods and prints out their results.
     * @return true if and only if all the tests return true, false otherwise
     */
    public static boolean runAllTests() {
        boolean test1 = testClothingConstructorExceptions();
        System.out.println("testClothingConstructorExceptions(): " + (test1 ? "pass" : "FAIL"));

        boolean test2 = testClothingConstructorAndGetters();
        System.out.println("testClothingConstructorAndGetters(): " + (test2 ? "pass" : "FAIL"));

        boolean test3 = testClothingWear();
        System.out.println("testClothingWear(): " + (test3 ? "pass" : "FAIL"));

        boolean test4 = testWardrobeConstructorAndGetters();
        System.out.println("testWardrobeConstructorAndGetters(): " + (test4 ? "pass" : "FAIL"));

        boolean test5 = testWardrobeConstructorExceptions();
        System.out.println("testWardrobeConstructorExceptions(): " + (test5 ? "pass" : "FAIL"));

        boolean test6 = testAddClothingExceptions();
        System.out.println("testAddClothingExceptions(): " + (test6 ? "pass" : "FAIL"));

        boolean test7 = testAddClothing();
        System.out.println("testAddClothing(): " + (test7 ? "pass" : "FAIL"));

        boolean test8 = testGetClothing();
        System.out.println("testGetClothing(): " + (test8 ? "pass" : "FAIL"));

        boolean test9 = testGetClothingExceptions();
        System.out.println("testGetClothingExceptions(): " + (test9 ? "pass" : "FAIL"));

        boolean test10 = testRemoveClothing();
        System.out.println("testRemoveClothing(): " + (test10 ? "pass" : "FAIL"));

        boolean test11 = testRemoveClothingExceptions();
        System.out.println("testRemoveClothingExceptions(): " + (test11 ? "pass" : "FAIL"));

        boolean test12 = testRemoveAllClothingWornBefore();
        System.out.println("testRemoveAllClothingWornBefore(): " + (test12 ? "pass" : "FAIL"));

        boolean test13 = testRemoveAllClothingWornNumTimes();
        System.out.println("testRemoveAllClothingWornNumTimes(): "
            + (test13 ? "pass" : "FAIL"));

        boolean test14 = testParseClothingExceptions();
        System.out.println("testParseClothingExceptions(): " + (test14 ? "pass" : "FAIL"));

        boolean test15 = testParseClothing();
        System.out.println("testParseClothing(): " + (test15 ? "pass" : "FAIL"));

        return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
                && test11 && test12 && test13 && test14 && test15;
    }

    public static void main(String[] args) {
        System.out.println("runAllTests(): " + runAllTests());
    }
}
