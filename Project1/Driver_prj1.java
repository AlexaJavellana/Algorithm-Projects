/**
 * file: Driver_prj1.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 1
 * due date: feb 2nd 2018
 * version: ??? i lost count
 *
 *
 * This file contains the logic to solve the maze solver project.
 * Incorporates methods from Maze, LocationStack and Location classes 
*/
import java.util.Scanner;

public class Driver_prj1 {
  
  public static void main(String[] args) {

  Scanner input = new Scanner(System.in);
  
  Maze maze = new Maze();
  maze.streamIn(input); // Streams in the maze

  Location start = maze.getStartLocation(); //Gets the start location of the maze
  LocationStack solution = new LocationStack();
   
  start.start(); //Sets nextDirection = right (row=startRow, col=startEnd)
  solution.push(start); 
 
  while(!solution.isEmpty() && !maze.isEndLocation(solution.getTop())) {
  	if(solution.getTop().isDone()) {
  	  solution.pop();
  	} 
  	else {
  	  Location loc = solution.getTop().nextNeighbor();
  	  if(maze.isValidLocation(loc)) {
        if(!solution.isOn(loc)) {
          loc.start();
          solution.push(loc);
        }
        else if(solution.isOn(loc) && solution.getTop().isDone())
          solution.pop();
      }
    }
  }
  
  if(!solution.isEmpty()) {
    System.out.println("Solution found:");
    solution.streamOut(solution); 
  }
  else
    System.out.println("No solution found");

 }
}
