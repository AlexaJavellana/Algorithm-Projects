/* CMPT 435
 * Project 0 -- Program trace verification
 * Filename: Driver_prj0.java
 * Student name: Alexa Javellana
 *
 * This driver file contains the logic to complete a program trace verification
 */

import java.util.Scanner;
import java.util.Stack;

public class Driver_prj0 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String line;

    Stack<String> callStack = new Stack<String>();

    int lineNumber = 0;
    int maximum_depth = 0;
    int callCount = 0;
    while (input.hasNext()) {
      line = input.nextLine();
      lineNumber++;
      String lineContent[] = line.split(" ", 2);

      //If the input line is a call statement, it pushes the second half into an empty callStack
      if(lineContent[0].equals("call") && callStack.isEmpty()) {
        callStack.push(lineContent[1]);
        callCount++;
        if(callCount >= maximum_depth)
          maximum_depth = callCount;
      }
      //If the call stack is not empty and the line is a call statement, it pushes the second half into
      //the not-empty Stack, while increases the number of calls called within a call
      else if(lineContent[0].equals("call") && !callStack.isEmpty()) {
        callStack.push(lineContent[1]);
        callCount++;
        if(callCount >= maximum_depth)
          maximum_depth = callCount;
      }
      //If the line is a return statement and the call stack is empty, this is an invalid 
      //trace because there was no call statement called. Uses the while loop to show the 
      //elements in the stack, which should be empty. 
      else if(lineContent[0].equals("return") && callStack.isEmpty()) {
        System.out.println("Invalid trace at line " + lineNumber);
        System.out.println("Returning from " + lineContent[1] + " which was not called");
        System.out.println("Stack trace:");
        while(!callStack.isEmpty()) {
          System.out.println(callStack.pop());
        }
        System.exit(1);
      }
      //If the call stack isn't empty and the line is a return statement,
      else if(lineContent[0].equals("return") && !callStack.isEmpty()) {
        String function = lineContent[1];
        callCount = 0;
        //and the second half of the return statement equals something in the stack,
        //it pops the value on the stack to represent it as a returned call
        if(function.equals(callStack.peek())) {
          callStack.pop();
        }
        //and the second half of the return statement equals nothing in the stack,
        //then it is returning from another type of possible call instead of what 
        //was called before, thus an invalid trace.
        else {
          System.out.println("Invalid trace at line " + lineNumber);
          System.out.println("Returning from " + function + " instead of " + callStack.peek());
          System.out.println("Stack trace:");
            while(!callStack.isEmpty()) {
              System.out.println(callStack.pop());
            } 
          System.exit(1);
        }  
      }  
    }
    //Two if statements compare the size of the callStack. If == 0, all calls were 
    //returned and there is a valid trace. If not, and no other exiting statements were 
    //triggered, this represents that there are some functions that were called and not 
    //returned. 
    if(callStack.size() == 0) {
      System.out.println("Valid trace");
      System.out.println("Maximum call depth was " + maximum_depth);
    }
    if(callStack.size() >= 1) {
      System.out.println("Invalid trace at line " + lineNumber);
      System.out.println("Not all functions returned");
      System.out.println("Stack trace: ");
      while(!callStack.isEmpty()) {
        System.out.println(callStack.pop());
      }
    }
  }
}