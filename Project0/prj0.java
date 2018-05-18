/* CMPT 435
 * Project 0 -- Program trace verification
 * Filename: Driver_prj0.java
 * Student name: PUT YOUR NAME HERE
 *
 * PUT YOUR COMMENTS FOR THIS FILE HERE
 */

import java.util.Scanner;
import java.util.Stack;

public class Driver_prj0 {

  /* main
   *  parameters:
   *      args -- the array of command line argument values
   *  return value: nothing
   * 
   *  PUT YOUR COMMENTS FOR THIS FUNCTION HERE
   */
  public static void main(String[] args) {
    // Here we initialize the scaner variable to read lines of input
    Scanner input = new Scanner(System.in);
    String line;

    // the callStack is used for storing the names of functions that have been
    // called and not yet returned
    Stack<String> callStack = new Stack<String>();

    // Each time we go through this while loop, we read a line of input.
    // The function hasNext() returns a boolean, which is checked by the while 
    // condition. If System.in has reached the end of the file, it will return 
    // false and the loop will exit.  Otherwise, it will return true and the 
    // loop will continue.
    int lineNumber = 0;
    int maximum_depth = 0;
    boolean falseReturn = false;
    while (input.hasNext()) {
      line = input.nextLine();
      lineNumber++;
      //System.out.println(line);
      // PUT YOUR CODE HERE
      String lineContent[] = line.split(" ", 2);
      if(lineContent[0].equals("call") && callStack.isEmpty()) {
        callStack.push(lineContent[1]);
      }
      else if(lineContent[0].equals("call") && !callStack.isEmpty()) {
        callStack.push(lineContent[1]);
        maximum_depth++;
      }
      else if(lineContent[0].equals("return") && callStack.isEmpty()) {
        System.out.println("Invalid trace at line number " + lineNumber);
        System.out.println("Returning from " + lineContent[1] + " which was not called.");
        falseReturn = true;
        break;
      }
      else if(lineContent[0].equals("return") && !callStack.isEmpty()) {
        String function = lineContent[1];
        if(callStack.search(function) == 1 && callStack.size() == 2)
          callStack.pop();
        else if(callStack.search(function) == 1 && callStack.size() == 1)
          callStack.pop();
        else if(callStack.peek() == function && callStack.size() >= 2)
          callStack.pop();
        else if(callStack.peek() != function && callStack.size() >= 2) {
          System.out.println("Invalid trace at line number " + lineNumber);
          System.out.println("Returning from " + function + " instead of " + callStack.peek());
          System.out.println("Stack trace: \n" + callStack.peek() + "\n" + function);
          break;
        }
      }  
    }
    if(callStack.size() == 0 && falseReturn == false) {
      System.out.println("Valid trace");
      System.out.println("Maximum call depth was " + maximum_depth;
    }
    if(callStack.size() == 1) {
      System.out.println("Invalid trace at line number " + lineNumber);
      System.out.println("Not all functions returned");
      System.out.println("Stack trace: ");
      System.out.println(callStack.peek());
    }








  }
}