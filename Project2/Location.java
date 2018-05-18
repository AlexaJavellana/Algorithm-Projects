/**
 * file: Location.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 2
 * due date: feb 16th 2018
 * version: ??? i lost count
 *
 *
 * This file contains methods and class Location class
 */
import java.util.*;


class Location implements Comparable<Location>{

  final int CHANGE_LETTER = 0;
  final int INSERT_LETTER = 1;
  final int DELETE_LETTER = 2;
  final int DONE          = 3;

  String word;
  int iterationMode;
  int indexToChange;
  char nextLetter;
  String first;

  Location() { // Constructor
    word = null;
    iterationMode = -1;
    indexToChange = -1;
    nextLetter = 'a';
  }

  /**
   * start
   *
   * This function sets the iteration mode of a location object to change letter (0)
   * 
   * Parameters:
   *   --
   * 
   * Return value: location object iteration mode set to 0
   */
  void start() {  
    iterationMode = CHANGE_LETTER;
    indexToChange = 0;
    nextLetter = 'a';
    first = word; //set to 0
  }

  /**
   * start
   *
   * This function creates a new location dependent on a previous location to produce a neighbor
   * 
   * Parameters:
   *   --
   * 
   * Return value: location object that is the neighbor of another 
   */
  Location nextNeighbor() { 
    Location newLoc = new Location();
    StringBuffer str = new StringBuffer(word);
    if (iterationMode == CHANGE_LETTER) {
      if (indexToChange == word.length() - 1 && !(nextLetter >= 'a' && nextLetter <= 'z')) {
        nextLetter = 'a';
        indexToChange = 0;
        iterationMode++;
      }
      else{
        if(!(nextLetter >= 'a' && nextLetter <= 'z')){
          indexToChange++;
          nextLetter = 'a';
        }

        str.setCharAt(indexToChange, nextLetter);
        while(str.toString().equals(word)){
          nextLetter++;
          str.setCharAt(indexToChange, nextLetter);
        }
        newLoc.word = str.toString();
        nextLetter++;
      }
    }
    if(iterationMode == INSERT_LETTER){
      if (indexToChange == word.length() && !(nextLetter >= 'a' && nextLetter <= 'z')) {
        nextLetter = 'a';
        indexToChange = 0;
        iterationMode++;
      }
      else{
        if(!(nextLetter >= 'a' && nextLetter <= 'z')){
          indexToChange++;
          nextLetter = 'a';
        }
        str.insert(indexToChange,nextLetter);
        while(str.toString().equals(word)){
          nextLetter++;
          str.insert(indexToChange, nextLetter);
        }
        newLoc.word = str.toString();
        nextLetter++;
      }
    }
    if(iterationMode == DELETE_LETTER){
      if (indexToChange == word.length()) {
        indexToChange = 0;
        iterationMode++;
      }
      else{
        str.deleteCharAt(indexToChange);
        while(str.toString().equals(word) && indexToChange < word.length()){
          str.deleteCharAt(indexToChange);
          indexToChange++;
        }
        newLoc.word = str.toString();
        indexToChange++;
      }
    }
    return newLoc;
  }
  /**
   * compareTo
   *
   * This function returns -1, 0, 1 dependent on whether or not a location is 
   * the same, less than or more than another location object
   * 
   * Parameters:
   *   loc: location object 
   * 
   * Return value: int value to determine whether or not loc is less than, more or equal
   * to previous location
   */
  public int compareTo(Location loc) {
    return (word.compareTo(loc.word));
  }
  /**
   * isDone
   *
   * This function returns true or false if a location object iteration mode is done 
   * 
   * Parameters:
   *   --
   * 
   * Return value: true or false dependent on whether or no the location is done 
   */
  boolean isDone() {  
    return iterationMode == 3;
  }
  /**
   * isEqual
   *
   * This function compares location loc to another location to see if they are equal
   * 
   * Parameters:
   *   loc: location loc 
   * 
   * Return value: true or false dependent on whether or not loc is the same as 
   * a location object
   */
  boolean isEqual(Location loc) {  
    return this.compareTo(loc) == 0;
  }
  /**
   * streamOut
   *
   * This function prints out the word value for the location object
   * 
   * Parameters:
   *   --
   * 
   * Return value: prints word of location object
   */
  void streamOut() {
    System.out.println(word);
  }
  /**
   * streamIn
   *
   * This function streams in a location object, but just the word
   * 
   * Parameters:
   *   input: scanner
   * 
   * Return value: takes the scanner input to be the new word of a location object
   */
  void streamIn(Scanner input) {
    word = input.next();
  }
  /**
   * isLess
   *
   * This function compares 
   * 
   * Parameters:
   *   --
   * 
   * Return value: location object iteration mode set to 0
   */
  boolean isLess(Location loc) {
    return this.compareTo(loc) < 0;
  }
}
