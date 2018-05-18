/**
 * file: Maze.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 1
 * due date: feb 2nd 2018
 * version: ??? i lost count
 *
 *
 * This file contains methods and classes LocationStack and LocationStackNode 
 * Defines methods that allow a maze to be created/streamed in, and 
 * defines methods regarding location objects
*/

/**
 * LocationStack
 *
 * This class implements methods for a simple stack that deals with location objects 
 */
class LocationStack {
  private LocationStack(LocationStack s) { assert(false); }
  private LocationStackNode top;

  LocationStack() { //Object constructor. Sets the LocationStackNode top to null
    top = null; 
  }

  /**
   * push
   *
   * This method pushes a new value location loc into the stack as a new node 
   * 
   * Parameters:
   *   loc: location that will act as part of the new top node
   * 
   * Return value: stack now has a new top node with location set to loc 
   */ 
  void push(Location loc) {
    LocationStackNode temp = new LocationStackNode(loc, top); 
    if(top == null)
      temp.setNextNode(null); 
    else
      temp.setNextNode(top); 
      top = temp;
  }

  /**
   * pop
   *
   * This function removes the top node from the stack and sets the new top to the 
   * tops previous nextNode if the stack is not empty
   *
   * Parameters:
   *   --
   * 
   * Return value: a stack with one less node and the new top set to the previous nextNode
   * of top
   */
  void pop() {
    if(top == null) {
      System.out.println("Stack is empty.");
    }
    else {  
      LocationStackNode temp = top;
      top = top.getNextNode(); 
    }
  }

  /**
   * getTop
   *
   * This function returns location (row, col, next direction) of the stack's top 
   * 
   * Parameters:
   *   --
   * 
   * Return value: the location object of the top node
   */
  Location getTop() {
      return top.getLocation();
  }

  /**
   * isEmpty
   *
   * This function returns the boolean value regarding its true or false that the 
   * stack is empty via ==. If the top value is null, meaning there are no location objects 
   * on the stack and therefore no next node, return true, otherwise default boolean false.
   *
   * Parameters:
   *   --
   * 
   * Return value: whether or not the stack is empty
   */
  boolean isEmpty() {
    return (top == null);
  }

  /**
   * isOn
   *
   * This function searches through a stack to see if a location loc is on
   * the stack. It uses a boolean check and returns it's value dependent on 
   * the statements it satisfies. If in any case the stack node being referenced equals
   * to the location loc, that would make check == true therefore the location loc is 
   * on the stack. Else, check == false. 
   * 
   * Parameters:
   *   --
   * 
   * Return value: the location object x which is the start location of the maze
   */
  boolean isOn(Location loc) {
    boolean check = false;
    if(top.getNextNode() == null){
      if(top.getLocation().isEqual(loc)) {
        check = true;
      }
      return check;
    }
    LocationStackNode temp = top;
    while(temp.getNextNode() != null) {
    if(temp.getLocation().isEqual(loc)) {
      check = true;
    }    
    temp = temp.getNextNode();
    }
    if(temp.getLocation().isEqual(loc)) {
      check = true;
    }
    return check;
  }
  
  /**
   * streamOut
   *
   * Traverses through a stack top to bottom, reversing the links of the nodes as 
   * it goes, and then passes again, traversing bottom to top and printing, undoing
   * the reversal of node links.
   *
   * Parameters:
   *   s: a location stack
   * 
   * Return value: prints the locations in path/location stack s from start to end 
   */
  void streamOut(LocationStack s) {
    if(top == null) {
      System.out.println("Stack is empty.");
    }
    else {
    LocationStackNode prev = null;
    LocationStackNode temp = s.top;
    LocationStackNode next = null;
      while(temp != null) {
        next = temp.getNextNode();
        temp.setNextNode(prev);
        prev = temp;
        temp = next;
      }
      s.top = prev;
      LocationStackNode temp2 = s.top;
      while(temp2 != null) {
        temp2.getLocation().streamOut();
        temp2 = temp2.getNextNode();
      }   
    } 
  }
}  

/**
 * LocationStackNode
 *
 * This class encapsulates methods and construction of nodes of the stack
 *
 */
class LocationStackNode {
  private LocationStackNode() { assert(false); }
  private LocationStackNode(LocationStackNode s) { assert(false); }

  private Location location;
  private LocationStackNode nextNode;

  //Object constructor. Location loc is the new location of a node, and Lsn next is the 
  //next node of said lsn. 
  LocationStackNode(Location loc, LocationStackNode next) {
    location = loc;
    nextNode = next; 
  }

  /**
   * getLocation
   *
   * This function returns the location object of the start location
   * 
   * Parameters:
   *   --
   * 
   * Return value: the location object x which is the start location of the maze
   */
  Location getLocation() {
    return location;       
  }

  /**
   * getNextNode
   *
   * This function returns the nextNode of the current node
   * 
   * Parameters:
   *   --
   * 
   * Return value: the values of the next node nextNode
   */
  LocationStackNode getNextNode() {
    return nextNode;
  }

  /**
   * setNextNode
   *
   * This function sets the nextNode of the current node as a location stack node next 
   * 
   * Parameters:
   *   next: the location stack node that will be the nextNode of current node
   * 
   * Return value: nextNode will be set to the value of next 
   */
  void setNextNode(LocationStackNode next) {
    nextNode = next;  
  }
}
