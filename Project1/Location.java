import java.util.Scanner;

/**
 * file: Location.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 1
 * due date: feb 2nd 2018
 * version: ??? i lost count
 *
 *
 * This file contains methods for and the Location class 
 *
*/

/**
 * LocationStack
 *
 * This class defines a Location object and methods used to display or manipulate a 
 * location object. 
 */

class Location {
  final int RIGHT = 0;
  final int DOWN  = 1;
  final int LEFT  = 2;
  final int UP    = 3;
  final int DONE  = 4;

  private int row;
  private int col;
  int nextDirection;   // mutable
  
  //Location constructor. Initilizaies the row, col and nextDirection values 
  Location() {
    row = 0;
    col = 0;
    nextDirection = DONE;
  }

  /**
   * start
   *
   * This method sets the nextDirection (set firstly to done) to right in order to 
   * correctly produce a new location object in separate call nextNeighbor
   * 
   * Parameters:
   *   --
   * 
   * Return value: sets nextDirection of location obj to right 
   */
  void start() {  // const
    nextDirection = RIGHT;
  }

  /**
   * nextNeighbor
   *
   * This method produces a new location object based on a referenced location object
   * to produce the correct 'neighbor' aka the location row and cols that satisfies 
   * occupation 1 row or col away from the reference location object in any of the 
   * numbered directions.  
   * 
   * Parameters:
   *   --
   * 
   * Return value: a new location object that is 1 row or col away from the reference loc obj
   */
  Location nextNeighbor() {  
    Location y = new Location();
    y.row = row;
    y.col = col; 
    if(!this.isDone() && nextDirection == RIGHT) {
      y.col++;
    }
    else if(!this.isDone() && nextDirection == LEFT) {
      y.col--;
    }
    else if(!this.isDone() && nextDirection == UP) {
      y.row--;
    }
    else if(!this.isDone() && nextDirection == DOWN) {
      y.row++; 
    }
    nextDirection++;
    return y; 
  }

  /**
   * push
   *
   * This method checks whether or not the nextDirection of a location object 
   * == done. If it does, true, else false. 
   * 
   * Parameters:
   *   --
   * 
   * Return value: true if nd of location isdone, else false
   */
  boolean isDone() {  
    if (nextDirection == DONE) 
      return true;
    else 
      return false;
  }

  /**
   * isEqual
   *
   * This method checks whether or not a location object loc is equal to the location 
   * object being referenced 
   * 
   * Parameters:
   *   loc: location that will be compared to the location referenced prior
   * 
   * Return value: true if loc has the same row and col as prior loc, else false
   */
  boolean isEqual(Location loc) {  
    if(this.row == loc.row && this.col == loc.col)
      return true;
    else
      return false; 
  }

  /**
   * streamOut
   *
   * This method visually displays the row and col of a location object
   * 
   * Parameters:
   *   --
   * 
   * Return value: visual rep of row and col of a location obj
   */
  void streamOut() {
    System.out.print(row);
    System.out.print(" " + col + "\n");
  }

  /**
   * streamIn
   *
   * This method allows a new location obj to be created by the scanner 
   * 
   * Parameters:
   *   input: scanner reading the inputs for the new location
   * 
   * Return value: row as first int input, col as next int input 
   */
  void streamIn(Scanner input) {
    row = input.nextInt();
    col = input.nextInt(); 
  }
}