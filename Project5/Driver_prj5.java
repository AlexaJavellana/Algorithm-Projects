import java.util.Scanner;

/**
 * file: Driver_prj5.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 5
 * due date: april/may 2018
 * version: ??? i lost count
 *
 *
 * This file contains the logic to solve project 5
 * This project also made me go crazy :)
 */
import java.util.Scanner;
//import java.util.Arrays;

public class Driver_prj5 {
  public static void main(String[] args) {

  ArrayHeap processHeap = new ArrayHeap();
  Scanner input = new Scanner(System.in);
  int currentClock = 0;
  int runProcess = 0;
  int skippedProcess = 0;
  int processID = 0;
  int numOfProcess = input.nextInt();
  int time = input.nextInt();
  int nextTime = -1;
  Process min;

  currentClock = time;

    for(int i = 0; i < numOfProcess; i++) {
    Process newInput = new Process(processID);
    newInput.streamIn(input);
      if(input.hasNextInt())
        nextTime = input.nextInt();
    if (currentClock < nextTime || !input.hasNext()) {
      time = nextTime;
      processHeap.insert(newInput);
      for (int x = processHeap.getNumItems(); x > 0; x--) {
        min = processHeap.getMinItem();
        if (min.canComplete(currentClock)) {
          //System.out.println(time);
          System.out.println("running process id " + min.getId() + " at " + currentClock);
          System.out.println(min.getInformation());
          currentClock = min.run(currentClock);
          processHeap.removeMinItem();
          runProcess++;
        } else if (!min.canComplete(currentClock)) {
          System.out.println("skipping process id " + min.getId() + " at " + currentClock);
          currentClock = currentClock + 1;
          processHeap.removeMinItem();
          skippedProcess++;
        }
      }
      if(currentClock < nextTime) {
        currentClock = nextTime;
      }
    }
    else if (currentClock >= nextTime){
      time = nextTime;
      processHeap.insert(newInput);
    }
    processID++;
  }
  System.out.println("final clock is                 " + currentClock);
  System.out.println("number of processes run is     " + runProcess);
  System.out.println("number of processes skipped is " + skippedProcess);
  }
}