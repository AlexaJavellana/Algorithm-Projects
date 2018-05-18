/**
 * file: Driver_prj2.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 2
 * due date: march 5th 2018
 * version: ??? i lost count
 *
 *
 * This file contains the logic to solve the word melt solver project.
 * Incorporates methods from Maze, ArrayQueue and Location classes 
*/
import java.util.*;

public class Driver_prj2 {
  
  public static void main(String[] args) {
  Scanner input = new Scanner(System.in);
  Maze maze = new Maze();
  Map<String, String> mapLoc = new TreeMap<>();
  maze.streamIn(input); // Streams in the maze
  Location start = maze.getStartLocation(); //Gets the start location of the maze
  Stack<String> stack = new Stack<String>();
  ArrayQueue queue = new ArrayQueue(); //Creates a new array queue
  queue.add(start); //Adds the start location to the queue 
  start.start(); //Sets iterationMode of start to CHANGE_LETTER
  mapLoc.put(start.word, start.word);
  Location front = queue.getFront();

  while(queue.getCapacity() <= maze.getVLSize()){
      queue.doubleCap();
    }
    while (queue.getLength() > 0 && !maze.isEndLocation(queue.getFront())) {
      Location neighbor = queue.getFront().nextNeighbor();
      if (!queue.getFront().isDone()) {
        if (maze.isValidLocation(neighbor) && !mapLoc.containsKey(neighbor.word)) {
          queue.add(neighbor);
          mapLoc.put(neighbor.word, queue.getFront().word);
          neighbor.start();
        }
      } else {
        queue.remove();
      }
    }
    try {
      if (maze.isEndLocation(queue.getFront())) {
        stack.push(queue.getFront().word);
        while (!stack.peek().equals(start.word)) {
          String wordKey = mapLoc.get(stack.peek().toString()).toString();
          stack.push(wordKey);
        }
        System.out.println("Solution found:");
        while (!stack.isEmpty()) {
          System.out.println(stack.pop());
        }
      } else {
        System.out.println("No solution found");
      }
    } catch (NullPointerException e) {
      System.out.println("No solution found");
    }
  }
}