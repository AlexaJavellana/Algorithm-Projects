/**
 * file: Maze.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 1
 * due date: feb 2nd 2018
 * version: ??? i lost count
 *
 *
 * This file contains methods and class Maze class 
 * Defines methods that allow a maze to be created/streamed in, and 
 * defines methods regarding location objects
*/

import java.util.Scanner;

class Maze {
  private Maze(Maze m) { assert(false); }

  private int validLocationCount;
  private Location[] validLocations;

  private Location startLocation;
  private Location endLocation;

  Maze() { // Object constructor. Contains 4 things, which are instances of the declared variables above.
    validLocationCount = 0;
    validLocations =  null;
    startLocation = null;
    endLocation = null; 
  }

  /**
   * getStartLocation
   *
   * This function returns the location object of the start location
   * 
   * Parameters:
   *   --
   * 
   * Return value: the location object x which is the start location of the maze
   */
  Location getStartLocation() {  
    Location x = new Location();
    x = startLocation;
    return x; 
  } 

  /**
   * isValidLocation
   *
   * This function checks location loc to see if it is a valid location /
   * part of the validLocations[] of the maze 
   *
   * Parameters:
   *   loc: the location being tested against validLocations[]
   * 
   * Return value: boolean check t if is a valid loc on stack, f if not 
   */
  boolean isValidLocation(Location loc) { // Iterates through the stack to check if given location 
    boolean check = false;
    for(int i = 0; i < validLocationCount; i++) {
      if(validLocations[i].isEqual(loc)) {
        check = true;
        break;
      }
    }
    return check;
  }

  /**
   * isEndLocation
   *
   * This function checks location loc to see if it is a valid location 
   * part of the validLocations[] of the maze acting as the end location
   *
   * Parameters:
   *   loc: the location being tested to equal the end location
   * 
   * Return value: true if loc is endLocation, false if not 
   */
  boolean isEndLocation(Location loc) {
    if(loc.isEqual(endLocation)) 
      return true;
    else 
      return false;
  }

  /**
   * streamIn
   *
   * This method uses the scanner to create a maze based on input
   *
   * Parameters:
   *   input: the scanner variable 
   * 
   * Return value: replaces the null or 0 values for the next inputs 
   */
  void streamIn(Scanner input) {
    validLocationCount = input.nextInt();
    validLocations = new Location[validLocationCount];
    for(int i = 0; i < validLocationCount; i++) {
      Location a = new Location();
      a.streamIn(input);
      validLocations[i] = a;
    }  
    startLocation = new Location();
    startLocation.streamIn(input);
    endLocation = new Location();
    endLocation.streamIn(input);
  }
}