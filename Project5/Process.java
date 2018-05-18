import java.util.Scanner;
/* CMPT 435
 * file: Process.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 5
 * due date: april/may 2018
 * version: ??? i lost count
 *
 * Implementation of the Process class
 */
class Process {
    private int id;
    private int deadline;
    private int requiredTime;
    private String information;

  /**
   * Constructor
   *
   * This function builds the parameters for a new process at process(theId)
   *
   * Parameters:
   *   --
   *
   * Return value: bubbled down array heap
   */
    public Process(int theId) {
      this.id = theId;
      deadline = 30;
      requiredTime = 10;
      information = "Loading process..";
    }
  /**
   * run
   *
   * This function returns the current system time + required time inorder to change the current system time on
   * driver
   *
   * Parameters:
   *   currentSystemTime: int
   *
   * Return value: currentsystime of driver + reqtime of process
   */
    public int run(int currentSystemTime) {
      return currentSystemTime + requiredTime;
    }
  /**
   * canComplete
   *
   * This function checks whether or not the current process can finish within it's deadline
   * if it runs
   *
   * Parameters:
   *   currentSystemTime: int
   *
   * Return value: true or false
   */
    public boolean canComplete(int currentSystemTime) {
      if(deadline >= currentSystemTime + requiredTime)
        return true;
      else
        return false;
    }
  /**
   * getId
   *
   * This function returns the id of the process
   *
   * Parameters:
   *   --
   *
   * Return value: id int
   */
    public int getId() {
      return id;
    }
  /**
   * isLess
   *
   * This function checks whether or not this process is less than process p dependent on deadline, req time
   * or id number
   *
   * Parameters:
   *   p: process
   *
   * Return value: true or false
   */
    public boolean isLess(Process p) {
      boolean outcome = false;
      if(this.deadline < p.deadline) {
        outcome = true;
      }
      else if(this.deadline == p.deadline) {
        if(this.requiredTime < p.requiredTime) {
          outcome = true;
        }
      }
      else if(this.deadline == p.deadline && this.requiredTime == p.requiredTime) {
        if (this.id < p.id)
          outcome = true;
      }
      else
        outcome = false;
      return outcome;
    }
  /**
   * streamIn
   *
   * This function streams in input to read for a process
   *
   * Parameters:
   *   input: scanner
   *
   * Return value:
   */
    public void streamIn(Scanner input) {
      this.deadline = input.nextInt();
      this.requiredTime = input.nextInt();
      this.information = input.nextLine().substring(1);
    }
  /**
   * getRequiredTime, getDeadline, getInformation
   *
   * This function returns ints for req time, deadline or the string for information
   *
   * Parameters:
   *   --
   *
   * Return value: int: reqtime, deadline or string: information
   */
    public int getRequiredTime() {
      return requiredTime;
    }

  public int getDeadline() {
    return deadline;
  }

  public String getInformation() {
    return information;
  }
}