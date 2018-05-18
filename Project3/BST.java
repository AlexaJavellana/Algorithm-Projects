/**
 * file: BST.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 2
 * due date: feb 16th 2018
 * version: ??? i lost count
 *
 *
 * This file contains methods and class BST, BSTNode and EncryptionTre
 * Defines methods that include the use of a binary search tree to encrypt and decrypt 
 * input/output and find, insert and remove elements within the tree.
*/


import java.util.Scanner;

/* A BSTNode represents a node in a binary search tree. Each BSTNode object
 * stores a single item (called "data"). Each object also has left and right
 * pointers, which point to the left and right subtrees.
 *
 * The BST can be seen as superclass of the BSTNode class, so that the BST 
 * may make changes to the internals of a BSTNode.
 *
 * The constructor is provided for you; read it carefully.
 *
 * The getLeft(), getRight(), and getData() methods are useful for the
 * EncryptionTree class (or any class that wants to have read-only access to the
 * nodes of a BST).
 *
 * The printPreorder() traverses this node and its children recursively in
 * pre-order and prints each node it visits to standard output (i.e.
 * System.in). It presumes that "data" can be printed; that is, 
 * "System.out.print(this.data)" is a statement that makes sense. At each 
 * level of the tree it adds two spaces of indentation to show the structure 
 * of the tree. The run-time of printPreorder() is O(n). Can you figure out 
 * why?  Could it be made more efficient?
 *
 * The minNode() and maxNode() methods are useful in verifySearchOrder(). They
 * should find the leftmost and rightmost node at or below the node they are
 * called on. These can be implemented recursively or iteratively.
 *
 * The function verifySearchOrder() can be used to do verifications of the
 * binary search tree's order. It can and should be used for testing purposes.
 * If you implement minNode() and maxNode() efficiently, the run-time of
 * verifySearchOrder() is O(n^2) for this (potentially unbalanced) tree. Can you
 * figure out why?  Could it be made more efficient using different techniques?
 *
 * No one may call the copy constructor on a BSTNode, it is hereby forbidden,
 * so it is protected and will crash the program if called.
 */

class BSTNode {
  protected  BSTNode(BSTNode t) { assert(false); }

  protected  String data;
  protected  BSTNode left;
  protected  BSTNode right;

  //Initializes parameter values for a new BSTNode instance
  public BSTNode(String d, BSTNode l, BSTNode r) {
    data = d; 
    left = l; 
    right = r;
  }

  /**
   * getLeft(), getRight(), getData()
   *
   * This function returns the BSTNode object of either the left or right pointer, or the string 
   * of the node
   * 
   * Parameters:
   *   --
   * 
   * Return value: the BSTNode object or data 
   */
  public BSTNode getLeft()  { return left;  }
  public BSTNode getRight()  { return right; }
  public String getData()    { return data;  }

  /**
   * printPreorder()
   *
   * This function prints out the contents of the BST in preorder order 
   * 
   * Parameters:
   *   --
   * 
   * Return value: visual representation of tree 
   */
  
  public void printPreorder() {
    System.out.println(data);
      if(this.left == null)
        System.out.println("  NULL");
      else 
        this.left.printPreorder("  ");
      if(this.right == null)
        System.out.println("  NULL");
      else 
        this.right.printPreorder("  ");
    }  

  void printPreorder(String indent) {
    System.out.println(indent + data);
      if(left == null)
        System.out.println(indent + "  NULL");
      else 
        left.printPreorder(indent + "  ");
      if(right == null)
        System.out.println(indent + "  NULL");
      else 
        right.printPreorder(indent + "  ");
  }

  /**
   * minNode()
   *
   * This function returns a BSTNode which is the minimum node within the tree  
   * 
   * Parameters:
   *   --
   * 
   * Return value: lowest value BSTNode in tree 
   */
  public BSTNode minNode() { 
    BSTNode minimum;
    minimum = this;
    if(minimum.getLeft() == null) {
      return minimum;  
    }
    while(minimum.getLeft() != null) {
      minimum = minimum.getLeft();
    }
    return minimum;
  }
  /**
   * maxNode()
   *
   * This function returns a BSTNode which is the maximum node within the tree  
   * 
   * Parameters:
   *   --
   * 
   * Return value: highest value BSTNode in tree 
   */
  public BSTNode maxNode() { 
    BSTNode maximum;
    maximum = this;
    if(maximum.getRight() == null)
      return maximum;
    while(maximum.getRight() != null) {
      maximum.getRight();
    }
    return maximum;
  }

  /* professor's implementation of verifySearchOrder(); don't change it */
  public void verifySearchOrder() {
    if (left != null) {
      assert(left.maxNode().data.compareTo(data) == -1);
      left.verifySearchOrder();
    }
    if (right != null) {
      assert(data.compareTo(right.minNode().data) == -1);
      right.verifySearchOrder();
    }
  }
}

/* A BST is a String-based class, but could easily be coded as a generic-type 
 * type class (e.g. with T), that represents a binary search tree. It has one
 * data member, "root", which is a pointer to the root of the tree.
 *
 * The constructor is provided for you.
 *
 * The insert() method places the given item in the tree, unless the item is
 * already in the tree. The insertion should follow the algorithm we discuss in
 * class.
 *
 * The remove() method removes the given item from the tree, if it is in the
 * tree. The remove should follow the algorithm we discuss in class.
 *
 * The printPreorder() and verifySearchOrder() methods simply calls the relevant
 * per-node methods on the root.
 *
 * No one may call the copy constructor on a BST, it is hereby forbidden, so
 * it is protected and will crash the program if called.
 */
class BST {
  protected BST(BST t) { assert(false); }
  protected BST isEqual(BST t) { assert(false); return this; }
  
  protected BSTNode root;

  public BST() {
    root = null; 
  }
  /**
   * getRoot()
   *
   * This function returns the root value of the BST tree 
   * 
   * Parameters:
   *   --
   * 
   * Return value: root value BSTNode in tree 
   */
  public BSTNode getRoot() {
    return root;
  }

  /**
   * insert()
   *
   * This function inserts a node with reference to string item into the tree in correct order
   * 
   * Parameters:
   *   item: string 
   * 
   * Return value: new addition to the BST 
   */
  public void insert(String item) { 
  BSTNode toInsert = new BSTNode(item, null, null);
    if(root == null) {
      root = toInsert;
      return;
    }  
    BSTNode parent = null;
    BSTNode currNode = root;
    while(currNode != null) {
      parent = currNode;
      int compare = toInsert.data.compareTo(currNode.data);
        if(compare < 0) {
        currNode = currNode.left;
          if(currNode == null) {
            parent.left = toInsert;
            return;
          }
        }    
        else if(compare > 0) {
        currNode = currNode.right;
          if(currNode == null) {
            parent.right = toInsert;
            return;
          }
        }    
        else 
          return;
    }
  }
  /**
   * remove()
   *
   * This function removes a node with reference to string item if in the tree 
   * 
   * Parameters:
   *   item: string 
   *   node: BSTNode
   * 
   * Return value: deletion of such node in the tree 
   */
  public BSTNode remove(String item, BSTNode node) {  
    //System.out.println(node);
    if(node == null) 
      return node;
    if(item.compareTo(node.data) < 0)
      node.left = remove(item, node.left);
    else if(item.compareTo(node.data) > 0)
      node.right = remove(item, node.right);
    else if(node.left != null && node.right != null) {
      node.data = node.right.minNode().getData();
      node.right = remove(node.data, node.right);
    }
    else{
      if(node.left != null)
        node = node.left;
      else
        //System.out.println(node.right.getData());
        node = node.right;
        //System.out.println(node);
    }
    //System.out.println(node);
    return node;
  } //end of removal function

  public void remove(String item) {
    root = remove(item, root);
    //System.out.println(root + " a");
  }

  public void printPreorder() { if (root != null) root.printPreorder(); }
  public void verifySearchOrder() { if (root != null) root.verifySearchOrder(); }
}

/* An EncryptionTree is a special type of BST which knows how to encrypt a
 * String object (e.g. word) into a string that represents the path to the 
 * object in the tree, and decrypt a path into the String object (e.g. word) 
 * it leads to.
 *
 * The constructor method is provided for you.
 *
 * The encrypt() method takes a String object and returns a string of the form 
 * "rX" where "r" is a literal letter r, and X is a sequence of 0 and 1 
 * characters (which may be empty). The r stands for "root", and each 0 and 1 
 * represent the left/right path from the root to the given object, with 0 
 * indicating a left-branch and 1 indicating a right-branch. If the object is 
 * not in the dictionary, an empty string (or the string "?") should be 
 * returned.
 *
 * The decrypt() method takes an encrypted string (or path through the tree) in
 * the form provided by encrypt(). It should return a pointer to the associated
 * string for the given path (or NULL if the path is invalid).
 */
class EncryptionTree extends BST {
  public EncryptionTree() {}
  /**
   * encrypt()
   *
   * This function encrypts the string item item according to the position it is in 
   * if it exists within the tree.
   * 
   * Parameters:
   *   item: string 
   * 
   * Return value: the encrypted version of the string item
   */
  public String encrypt(String item) {
  //BSTNode parent;
  BSTNode currNode = this.getRoot();
  int value = 0;
  String outcome= ""; 
  boolean found = false;
  if(this.getRoot() != null)
    outcome += 'r';
  else 
    return "?";
    while(currNode != null) {
    //parent = currNode;
    value = item.compareTo(currNode.getData());
      if(value == 0) {
        found = true;
        break;
      }
      else if(value < 0) {
        currNode = currNode.getLeft();
        if(currNode != null) {
          outcome += '0';
          if(currNode.getData().equals(item)) {
            found = true;
            break;
          }
        }
        else if(currNode == null)
          return "?";  
      }      
      else if(value > 0) {
        currNode = currNode.getRight();
        if(currNode != null) {
          outcome += '1';
            if(currNode.getData().equals(item)) {
            found = true;
            break;
          }
        }
        else if(currNode == null)
          return "?";
      }      
    }
    if(found = true) 
      return outcome;
    else
      return "?";   
  }
  /**
   * decrypt()
   *
   * This function decrypts the string path by reading the path character by character
   * and moving according to fetch the correct node data
   * 
   * Parameters:
   *   path: string 
   * 
   * Return value: the decrypted version of the string path
   */
  public String decrypt(String path) { 
  //BSTNode parent;
  BSTNode currNode = this.getRoot();
  //int value = 0;
  //boolean found = false;
  char[] separated = path.toCharArray();
  for(int j = 0; j<separated.length; j++) {
    if(separated[j] == 'r')
      currNode = currNode; 
    else if(separated[j] == '0')
      currNode = currNode.getLeft();
    else if(separated[j] == '1')
      currNode = currNode.getRight();
    else 
      return"?";
    if(currNode==null)
      return "?";
  }
    return currNode.getData(); 
  }
}