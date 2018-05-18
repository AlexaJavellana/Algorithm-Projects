/**
 * file: ArrayQueue.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 2
 * due date: feb 16th 2018
 * version: ??? i lost count
 *
 *
 * This file contains methods and class ArrayQueue
 */

class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;
  
  /**
   * doubleCapacity
   *
   * This function doubles the capacity of the data member of an array queue 
   * 
   * Parameters:
   *   --
   * 
   * Return value: a new data array with double the capacity 
   */
  private void doubleCapacity() {
    capacity = capacity * 2;
    Location[] temp = new Location[capacity];
    for(int i = 0; i < length; i++) {
      temp[i] = data[(front + i) % data.length];
    }
    data = temp;
    front = 0;
  }
  
  ArrayQueue() { // Constructor
    capacity = 200;
    data = new Location[capacity];
    length = 0;
    front = 0;
  }
  /**
   * ArrayQueue
   *
   * This function returns a deep copy of the queue 
   * 
   * Parameters:
   *   q: the array queue to copy
   * 
   * Return value: a new array queue with the members from q copied into it
   */
  ArrayQueue(ArrayQueue q) {
    this.data = q.data;
    this.length = q.length;
    this.capacity = q.capacity;
    this.front = q.front;
  }
  /**
   * add
   *
   * This function adds a new location to the location array data
   * 
   * Parameters:
   *   loc: location
   * 
   * Return value: a data array now with a location loc at the back
   */
  void add(Location loc) {
    data[(front + length) % capacity] = loc;
    length++;
  }
  /**
   * remove
   *
   * This function removes the front of the data location array object
   * 
   * Parameters:
   *   --
   * 
   * Return value: takes away location object in the front of the data array
   */
  void remove() {
    if(length == 0){
      System.out.println("Nothing to remove");
    }
    else {
      front++;
      length--;
    }
  }
  /**
   * getFront
   *
   * This function returns the front object in the data array
   * 
   * Parameters:
   *   --
   * 
   * Return value: the location object at the front of data
   */
  Location getFront() {
    return data[front];
  }
  /**
   * getLength
   *
   * This function returns the length of the queue
   * 
   * Parameters:
   *   --
   * 
   * Return value: the length of the queue 
   */
  int getLength()  {
    return length;
  }
  /**
   * copyFrom
   *
   * This function creates a new arrayqueue which is a copy of another array queue  
   * 
   * Parameters:
   *   --
   * 
   * Return value: a new array queue with the same values of q
   */
  ArrayQueue copyFrom(ArrayQueue q) {
    if(this == q){
      return q;
    }
    capacity = q.capacity;
    length = q.length;
    front = q.front;
    data = new Location[capacity];
    for(int i=0; i<q.length; i++){
      data[i] = q.data[i];
    }
    return this;
  }
  /**
   * getCapacity
   *
   * This function returns the value of capacity  
   * 
   * Parameters:
   *   --
   * 
   * Return value: capacity value
   */
  int getCapacity() {return capacity;}
  /**
   * doubleCap
   *
   * This function allows doubling capacity outside of arrayqueue
   * 
   * Parameters:
   *   --
   * 
   * Return value: doubles capacity
   */
  public void doubleCap(){
    doubleCapacity();
  }
}

