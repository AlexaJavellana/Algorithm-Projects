import java.util.*;
/* CMPT 435
 * file: ArrayHeap.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 5
 * due date: april/may 2018
 * version: ??? i lost count
 *
 * Implementation of the ArrayHeap data structure
 */

class ArrayHeap {
  private Process[] data;
  private int[] heapAndFreeStack;

  private int numItems;
  private int capacity;
  /**
   * getCapacity
   *
   * This function returns the integer value of the capacity of the array heap
   *
   * Parameters:
   *   --
   *
   * Return value: capacity
   */
  public int getCapacity(){
    return capacity;
  }
  /**
   * getNumItems
   *
   * This function returns the number of items in the array heap class
   *
   * Parameters:
   *   --
   *
   * Return value: number of items
   */
  public int getNumItems() {
    return numItems;
  }
  /**
   * doubleCapacity
   *
   * This function doubles the capacity of the hfs and data arrays
   *
   * Parameters:
   *   --
   *
   * Return value:
   */
  private void doubleCapacity() {
    capacity = capacity * 2;
    Process[] dataTemp = new Process[capacity];
    int[] hfsTemp = new int[capacity];
    for(int i = 0; i < data.length; i++) {
      dataTemp[i] = data[i];
      hfsTemp[i] = heapAndFreeStack[i];
    }
    data = dataTemp;
    heapAndFreeStack = hfsTemp;

    for(int h = numItems; h < capacity; h++) {
      heapAndFreeStack[h] = h;
    }
  }
  /**
   * bubbleUp
   *
   * This function performs the bubbleUp operation on the array heap structure
   *
   * Parameters:
   *   --
   *
   * Return value: bubbled up array heap
   */
  private void bubbleUp(int ndx) {
    if(ndx/2 == 1 || ndx/2 == 0) {
      Process parent = data[heapAndFreeStack[0]];
      if (data[heapAndFreeStack[ndx]].isLess(parent)) {
        int tmp = heapAndFreeStack[ndx];
        heapAndFreeStack[ndx] = heapAndFreeStack[0];
        heapAndFreeStack[0] = tmp;
      }
      else
        return;
    }
    else { //if the inserted item is at place 3 or more
      Process parent = data[heapAndFreeStack[ndx / 2]];
      if (data[heapAndFreeStack[ndx]].isLess(parent)) {
        int tmp = heapAndFreeStack[ndx];
        heapAndFreeStack[ndx] = heapAndFreeStack[ndx / 2];
        heapAndFreeStack[ndx / 2] = tmp;
        ndx = ndx / 2;
        bubbleUp(ndx);
      }
      else
        return;
      }
    }
  /**
   * bubbleDown
   *
   * This function performs the bubbleUp operation on the array heap structure
   *
   * Parameters:
   *   --
   *
   * Return value: bubbled down array heap
   */
  private void bubbleDown(int ndx) {
    int leftChild = (2*ndx) + 1;
    int rightChild = (2*ndx) + 2;
    int lessIndex = 0;
    Process lessChild;

    if(rightChild >= numItems || leftChild >= numItems) {
      return;
    }

    if(data[heapAndFreeStack[leftChild]].isLess(data[heapAndFreeStack[rightChild]])) {
      lessIndex = leftChild;
      lessChild = data[heapAndFreeStack[leftChild]];
    }
    else {
      lessIndex = rightChild;
      lessChild = data[heapAndFreeStack[rightChild]];
    }

    if(lessChild.isLess(data[heapAndFreeStack[ndx]])) {
      int temp = heapAndFreeStack[ndx];
      heapAndFreeStack[ndx] = heapAndFreeStack[lessIndex];
      heapAndFreeStack[lessIndex] = temp;
      bubbleDown(lessIndex);
    }
  }
  /**
   * Constructor
   *
   * This function builds the parameters of the array heap structure
   *
   * Parameters:
   *   --
   *
   * Return value:
   */
  public ArrayHeap() {
    capacity = 100;
    data = new Process[capacity];
    heapAndFreeStack = new int[capacity];
      for(int i = 0; i < heapAndFreeStack.length; i++) {
        heapAndFreeStack[i] = i;
      }
    numItems = 0;
  }
  /**
   * ArrayHeap(ArrayHeap h)
   *
   * This function creates a copy of the arrayheap h
   *
   * Parameters:
   *   h: array heap
   *
   * Return value: copies contents of h into this array heap
   */
  public ArrayHeap(ArrayHeap h) {
    this.data = new Process[h.capacity];
    this.heapAndFreeStack = new int[h.capacity];
    for(int i = 0; i < data.length; i++) {
      this.data[i] = h.data[i];
      this.heapAndFreeStack[i] = h.heapAndFreeStack[i];
    }
    this.numItems = h.getNumItems();
    this.capacity = h.getCapacity();
  }
  /**
   * insert
   *
   * This function inserts the process item into the array heap
   *
   * Parameters:
   *   item: process
   *
   * Return value: array heap with item
   */
  public void insert(Process item) {
    if(numItems >= data.length)
      doubleCapacity();

    data[numItems] = item;
    heapAndFreeStack[numItems] = numItems;
    bubbleUp(numItems);
    numItems++;
  }
  /**
   * removeMinItem
   *
   * This function removes the minimum item of the array heap
   *
   * Parameters:
   *   --
   *
   * Return value:
   */
  public void removeMinItem() {
    numItems--;
    int tmp = heapAndFreeStack[numItems];
    heapAndFreeStack[numItems] = heapAndFreeStack[0];
    heapAndFreeStack[0] = tmp;
    //System.out.println(numItems);
    bubbleDown(0);
    //numItems--;
  }
  /**
   * getMinItem
   *
   * This function performs the bubbleUp operation on the array heap structure
   *
   * Parameters:
   *   --
   *
   * Return value: minimum item on the arrayheap
   */
  public Process getMinItem() {
    if(this.numItems >= 1)
      return data[heapAndFreeStack[0]];
    else
      return null;
  }
  /**
   * getMinProcessID
   *
   * This function returns the int of the minimum processes id
   *
   * Parameters:
   *   --
   *
   * Return value: min process ID
   */
  public int getMinProcessID() {
    return this.getMinItem().getId();
  }

}