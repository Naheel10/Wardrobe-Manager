//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Clothing
// Course: CS 300 Spring 2024
//
// Author: Muhammad Naheel
// Email: naheel@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: I went to office hours for the Wear Clothing method and the TA
// explained me how it works because I was having trouble interpreting dates
// and one of my tests was failing.
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class contains the code that represents a piece of Clothing for use in the Wardrobe Manager
 * project.
 */
public class Clothing {
  private LocalDate lastWornDate;
  private String description;
  private String brand;
  private int timesWorn;

  /**
   * This is a constructor that creates a new clothing object with the given description and brand.
   * 
   * @param description It is the description of the clothing.
   * @param brand       It is the brand of the clothing.
   */
  public Clothing(String description, String brand) {
    if (description.isBlank() || brand.isBlank()) {
      throw new IllegalArgumentException("The description or brand cannot be left blank.");
    }
    this.description = description;
    this.brand = brand;
    this.timesWorn = 0;
    this.lastWornDate = null;
  }

  /**
   * This is a constructor that creates a new clothing object with the given description, brand,
   * timesWorn and lastWorn date values.
   * 
   * @param description  It is the description of the clothing.
   * @param brand        It is the brand of the clothing.
   * @param timesWorn    It is the number of times this clothing is worn
   * @param lastWornDate It is the date when this clothing was last worn.
   */
  public Clothing(String description, String brand, int timesWorn, LocalDate lastWornDate) {
    if (description.isBlank() || brand.isBlank()) {
      throw new IllegalArgumentException("The description or brand cannot be left blank.");
    }
    this.description = description;
    this.brand = brand;
    this.timesWorn = timesWorn;
    this.lastWornDate = lastWornDate;
  }

  /**
   * This method updates the number of times this piece of clothing has been worn and the last worn
   * date
   * 
   * @param year  It is the year in a date in int.
   * @param month It is the month in a date in int.
   * @param day   It is the day in a date in int.
   * @throws IllegalArgumentException exception if the date is outside the range.
   */
  public void wearClothing(int year, int month, int day) throws IllegalArgumentException {
    if (year < 1 || month < 1 || month > 12) {
      throw new IllegalArgumentException("The date is invalid as year must be greater than 1"
          + "or the month is outside the range (1,12)");
    }
    this.timesWorn++;
    this.lastWornDate = LocalDate.of(year, month, day);
  }


  // Getter method for description.
  public String getDescription() {
    return description;
  }

  // Getter method for brand.
  public String getBrand() {
    return brand;
  }

  // Getter method for lastWornDate
  public LocalDate getLastWornDate() {
    return lastWornDate;
  }

  // Getter method for timesWorn.
  public int getNumOfTimesWorn() {
    return timesWorn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Clothing clothing = (Clothing) o;
    return brand.equalsIgnoreCase(clothing.brand)
        && description.equalsIgnoreCase(clothing.description);
  }

  @Override
  public String toString() {
    String formattedDate;
    if (lastWornDate != null) {
      formattedDate = lastWornDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    } else {
      formattedDate = "null";
    }
    return String.format("%s,%s,%s,%d", description, brand, formattedDate, timesWorn);
  }


}
