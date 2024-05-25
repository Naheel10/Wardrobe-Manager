//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Wardrobe
// Course: CS 300 Spring 2024
//
// Author: Muhammad Naheel
// Email: naheel@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: I went to office hours and took some help from the TA regarding a
// few methods such as parseClothing() and a few more.
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.text.ParseException;

/**
 * This class contains the code that represents a Wardrobe. A Wardrobe object contains and manages
 * Clothing. For use in the Wardrobe Manager project.
 */

public class Wardrobe {
  private Clothing[] wardrobe;
  private int wardrobeSize;

  /**
   * This constructor creates a new Wardrobe object that is empty with the given capacity.
   * 
   * @param capacity the number of clothing that the wardrobe can fit.
   */
  public Wardrobe(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("The capacity must be positive");
    }
    wardrobe = new Clothing[capacity];
    wardrobeSize = capacity;
  }



  /**
   * This method finds and returns the piece of clothing with the matching description and brand.
   * The comparisons are case-insensitive.
   * 
   * @param description It is the description of the clothing.
   * @param brand       It is the brand of the clothing.
   * @return the Clothing object in the Wardrobe that matches the given description and brand.
   */
  public Clothing getClothing(String description, String brand) {
    if (description == null || brand == null) {
      throw new IllegalArgumentException("Description and brand must not be null.");
    }
    for (int i = 0; i < wardrobeSize; i++) {
      Clothing item = wardrobe[i];
      if (item != null && item.getDescription().equalsIgnoreCase(description)
          && item.getBrand().equalsIgnoreCase(brand)) {
        return item;
      }
    }
    throw new NoSuchElementException("This clothing was not found in the wardrobe.");
  }


  /**
   * This method adds a piece of clothing at the end of the wardrobe. If the wardrobe does not have
   * room for the piece of clothing, the wardrobe expands by doubling in capacity then adds the new
   * piece of clothing.
   * 
   * @param toAdd the piece of clothing to add to the wardrobe
   * @throws IllegalArgumentException if toAdd is already in the wardrobe
   */
  public void addClothing(Clothing toAdd) throws IllegalArgumentException {
    if (containsClothing(toAdd)) {
      throw new IllegalArgumentException("This clothing already exists in the wardrobe.");
    }
    if (wardrobeSize == wardrobe.length) {
      Clothing[] newWardrobe = new Clothing[wardrobe.length * 2];
      System.arraycopy(wardrobe, 0, newWardrobe, 0, wardrobe.length);
      wardrobe = newWardrobe;
    }
    wardrobe[wardrobeSize] = toAdd;
    wardrobeSize++;
  }


  /**
   * This method wears the piece of Clothing in this Wardrobe equal to the provided Clothing on the
   * given date.
   * 
   * @param toWear the piece of clothing in the Wardrobe that we want to wear.
   * @param year   the year that it will be worn.
   * @param month  the month that it will be worn.
   * @param day    the day that it will be worn.
   */
  public void wearClothing(Clothing toWear, int year, int month, int day) {
    // Validate year and month
    if (year < 1 || month < 1 || month > 12) {
      throw new IllegalArgumentException("Year or Month is Invalid.");
    }

    LocalDate date = LocalDate.of(year, month, day);

    // Find the clothing to wear in the wardrobe
    boolean isFound = false;
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i] != null && wardrobe[i].equals(toWear)) {
        // Update the lastWornDate and timesWorn
        wardrobe[i].wearClothing(year, month, day);
        isFound = true;
        break;
      }
    }


    if (!isFound) {
      throw new NoSuchElementException("This clothing was not found in the wardrobe.");
    }
  }


  // Getter method for capacity
  public int capacity() {
    return wardrobe.length;
  }

  // Getter method for size
  public int size() {
    return wardrobeSize;
  }

  /**
   * This method removes the piece of clothing from the wardrobe that has a matching description and
   * brand.
   * 
   * @param description the description of the piece of clothing to remove.
   * @param brand       the brand of the piece of clothing to remove.
   */
  public void removeClothing(String description, String brand) {
    boolean found = false;
    if (wardrobeSize == 0) {
      throw new IllegalStateException("The wardrobe is empty.");
    }
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i] != null && wardrobe[i].getDescription().equalsIgnoreCase(description)
          && wardrobe[i].getBrand().equalsIgnoreCase(brand)) {
        removeItem(i);
        found = true;
        break;
      }
    }
    if (!found) {
      throw new NoSuchElementException("This clothing was not found in the wardrobe.");
    }
  }

  /**
   * This method removes all pieces of clothing from the wardrobe whose last worn date is BEFORE the
   * given day, month, and year.
   * 
   * @param year  the year of the date to use to remove clothes.
   * @param month the month of the date to use to remove clothes.
   * @param day   the day of the date to use to remove clothes.
   */
  public void removeAllClothingWornBefore(int year, int month, int day) {
    LocalDate date = LocalDate.of(year, month, day);
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i] != null && (wardrobe[i].getLastWornDate() == null
          || wardrobe[i].getLastWornDate().isBefore(date))) {
        removeItem(i);
        i--; // Adjust the index after removal
      }
    }
  }


  /**
   * This method removes all pieces of clothing from the wardrobe who have been worn less times than
   * the given threshold.
   * 
   * @param threshold the upperbound (exclusive) of number of times worn
   */
  public void removeAllClothingWornNumTimes(int threshold) {
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i] != null && wardrobe[i].getNumOfTimesWorn() < threshold) {
        removeItem(i);
        i--;
      }
    }
  }

  /**
   * This method Creates a new Clothing object based on the given String formatted as
   * "description,brand,lastWornDate,timesWorn".
   * 
   * @param str the String parse to make a Clothing object.
   * @return a Clothing object with the pieces of information in the given string.
   */
  public static Clothing parseClothing(String str) throws ParseException {
    String[] parts = str.split(",");
    if (parts.length != 4) {
      throw new ParseException("Invalid format: Missing required information", 0);
    }

    String description = parts[0];
    String brand = parts[1];
    LocalDate lastWornDate;
    try {
      if (parts[2].equalsIgnoreCase("null")) {
        lastWornDate = null;
      } else {
        lastWornDate = LocalDate.parse(parts[2]);
      }
    } catch (DateTimeParseException e) {
      throw new ParseException("Badly formatted date: " + e.getMessage(), 0);
    }

    try {
      int timesWorn = Integer.parseInt(parts[3]);
      return new Clothing(description, brand, timesWorn, lastWornDate);
    } catch (NumberFormatException e) {
      throw new ParseException("Error parsing timesWorn: " + e.getMessage(), 0);
    }
  }


  /**
   * This method Loads all of pieces of clothing into this wardrobe from the designated file. Each
   * piece of clothing in the Wardrobe is written on its own line, formatted as
   * description,brand,lastWornDate,timesWorn.
   * 
   * @param saveFile the File that the information should be read from
   * @return true if ANY of the lines from the file were parsed successfully into Clothing for this
   *         Wardrobe, false otherwise
   */
  public boolean loadFromFile(File saveFile) {
    try (Scanner scanner = new Scanner(saveFile)) {
      boolean isParsed = false;
      while (scanner.hasNextLine()) {
        String input = scanner.nextLine();
        try {
          Clothing clothing = parseClothing(input);
          addClothing(clothing);
          isParsed = true;
        } catch (ParseException e) {
          System.out.println("Error parsing input: " + e.getMessage());
        } catch (IllegalArgumentException e) {
          System.out.println("Cannot parse line to Clothing object");
        }
      }
      return isParsed;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return false;
    }
  }


  /**
   * This method Saves all of pieces of clothing in this wardrobe to the designated file. Each piece
   * of clothing in the Wardrobe is written on its own line, formatted as
   * description,brand,lastWornDate,timesWorn.
   * 
   * @param saveFile the File that the information should be written to.
   * @return true if the file saved successfully, false otherwise.
   */
  public boolean saveToFile(File saveFile) {
    try (PrintWriter writer = new PrintWriter(saveFile)) {
      for (int i = 0; i < wardrobeSize; i++) {
        Clothing clothing = wardrobe[i];
        if (clothing != null) {
          String lastWornDate;
          if (clothing.getLastWornDate() != null) {
            lastWornDate = clothing.getLastWornDate().toString();
          } else {
            lastWornDate = "null";
          }
          String line = clothing.getDescription() + "," + clothing.getBrand() + "," + lastWornDate
              + "," + clothing.getNumOfTimesWorn();
          writer.println(line);
        }
      }
      return true;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Helper method to check if clothing already exists in the wardrobe
   * 
   * @param clothing The clothing item in the wardrobe
   * @return True if it item exists else false.
   */
  private boolean containsClothing(Clothing clothing) {
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i] != null && wardrobe[i].equals(clothing)) {
        return true;
      }
    }
    return false;
  }


  // Helper method to remove item at index
  private void removeItem(int index) {
    for (int i = index; i < wardrobeSize - 1; i++) {
      wardrobe[i] = wardrobe[i + 1];
    }
    wardrobeSize--;
    wardrobe[wardrobeSize] = null;
  }

  /**
   * Gets the array that contains all the Clothing in the wardrobe.
   * 
   * @return the wardrobe array
   */
  protected Clothing[] getArray() {
    return wardrobe;
  }

  // Override toString method
  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder();
    for (int i = 0; i < wardrobeSize; i++) {
      strBuilder.append("[").append(wardrobe[i]).append("]\n");
    }
    return strBuilder.toString();
  }
}
