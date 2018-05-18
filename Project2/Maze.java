import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;
/**
 * file: Maze.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 2
 * due date: feb 16th 2018
 * version: ??? i lost count
 *
 *
 * This file contains methods and class Maze class 
 */

class Maze {
  private Set<Location> validLocations;
  private Set<String> validLocationWords;
  
  private Location startLocation;
  private Location endLocation;

  private Maze(Maze m) {
    assert (false);
  }

  Maze() { // Constructor 
    validLocations = new TreeSet<>();
    validLocationWords = new TreeSet<>();
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
    return startLocation;
  }
  /**
   * getEndLocation
   *
   * This function returns the location object of the end location
   * 
   * Parameters:
   *   --
   * 
   * Return value: the location object x which is the start location of the maze
   */
  Location getEndLocation() {
    return endLocation;
  }
  /**
   * getVLSize
   *
   * This function returns size of valid locations
   * 
   * Parameters:
   *   -- 
   * 
   * Return value: int of size of valid locations
   */
  int getVLSize() {
    return validLocations.size();
  }
  /**
   * isValidLocation
   *
   * This function checks if the location loc is a valid location
   * 
   * Parameters:
   *   loc: location loc 
   * 
   * Return value: true or false dependent on if loc is a valid location 
   */
  boolean isValidLocation(Location loc) {
    if(validLocations.contains(loc)) {
      return true;
    }
    return false;
  }
  /**
   * isEndLocation
   *
   * This function checks if the location loc is the end location of the maze
   * 
   * Parameters:
   *   loc: location loc
   * 
   * Return value: true or false dependent on if the loc is the end location
   */
  boolean isEndLocation(Location loc) {
    if(loc.isEqual(endLocation)) {
      return true;
    }
    return false;
  }
  /**
   * streamIn
   *
   * This function streams in a maze 
   * 
   * Parameters:
   *   input: scanner 
   * 
   * Return value: creates a maze with the input from scanner 
   */
  void streamIn(Scanner input) {
    int count = input.nextInt();
    validLocations = new TreeSet<Location>();
    for(int i = 0; i < count; i++) {
      Location a = new Location();
      a.streamIn(input);
      validLocations.add(a);
      validLocationWords.add(a.word);
    }  
    startLocation = new Location();
    startLocation.streamIn(input);
    endLocation = new Location();
    endLocation.streamIn(input);
  }
  /*void streamOut() {
  } */ 
}
