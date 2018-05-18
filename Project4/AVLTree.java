import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/* An AVLNode represents a node in an AVL-balanced binary search tree. Each
 * AVLNode object stores a single item (called "data"). Each object also has
 * left and right references, which point to the left and right subtrees, and it
 * knows its own height (the path length to its deepest descendant).
 *
 * The AVLTree can be seen as superclass of the AVLNode class, so that the 
 * AVLTree may make changes to the internals of an AVLNode.
 *
 * Many of the methods in this class are virtually identical to those in the
 * BSTNode in the previous project (#3), including the constructor,
 * getLeft(), getRight(), getData(), printPreorder(), verifySearchOrder(),
 * minNode(), maxNode(), and the copy constructor.
 *
 * The function verifyBalance() can be used to do verifications that the AVL
 * balance property holds at each node. It also can and should be used for
 * testing purposes. What is its running time?
 *
 * The singleRotateLeft() and singleRotateRight() methods do a single rotation
 * on the node they are called on, and return a reference to the node that takes
 * its place (so that the node's parent's reference can be changed).  Note that
 * these methods should update the heights of some nodes as necessary.
 *
 * The doubleRotateLeftRight() and doubleRotateRightLeft() methods do a double
 * rotation on the node they are called on. This is really simple if you have
 * implemented the single rotation methods; my double rotation methods are two
 * lines each. These methods return a reference to the node which took the place
 * of the node the method was called on (so that the node's parent's reference 
 * can be changed).
 *
 * The getHeight() method is a static method which takes a reference to a node,
 * and returns the height of that node (or -1 if the reference is NULL). This
 * makes it easy to find the height of any node with a reference, without having
 * to check for NULL.
 *
 * The updateHeight() method calculates and updates the value of the height on
 * the node it's called on. It assumes that the height values for the two
 * children of this node are correct, and uses them.
 */
class AVLNode {
  AVLNode(AVLNode t) { assert(false); }

  AVLNode(String d, AVLNode l, AVLNode r, int h) {
    data = d; left = l; right = r; height = h; 
  }
  
  protected String data;
  protected AVLNode left, right;
  protected int height;

  public AVLNode getLeft()  { return left;  }
  public AVLNode getRight()  { return right; }
  public String getData()    { return data;  }
  
  protected AVLNode singleRotateLeft() {
    AVLNode x;
    AVLNode y;
    x = this.right;
    y = x.left;
    x.left = this;
    this.right = y;
    this.updateHeight();
    x.updateHeight(); 

    return x;
  }

  protected AVLNode singleRotateRight() {
    AVLNode x;
    AVLNode y;
    x = this.left;
    y = x.right;
    x.right = this;
    this.left = y;
    this.updateHeight();
    x.updateHeight(); 

    return x;
  }

  protected AVLNode doubleRotateLeftRight() {
    this.left = left.singleRotateLeft();
    return this.singleRotateRight();
  }

  protected AVLNode doubleRotateRightLeft() {
    this.right = right.singleRotateRight();
    return this.singleRotateLeft();
  }
  
  protected static int getHeight(AVLNode n) { 
    return n != null ? n.height : -1; 
  }

  protected void updateHeight() {
    int lh = getHeight(left);
    int rh = getHeight(right);
    height = (lh > rh ? lh : rh) + 1;
  }

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

  /* professor's implementation of verifyBalance(); don't change it */
  public void verifyBalance() {
    int heightDiff = Math.abs(getHeight(left) - getHeight(right));
    assert(heightDiff <= 1); 
    if (left  != null) left.verifyBalance();
    if (right != null) right.verifyBalance();
  }

  public AVLNode minNode() {
    AVLNode minimum;
    minimum = this;
    if(minimum.getLeft() == null) {
      return minimum;  
    }
    while(minimum.getLeft() != null) {
      minimum = minimum.getLeft();
    }
    return minimum;
  }
  public AVLNode maxNode() { 
    AVLNode maximum;
    maximum = this;
    if(maximum.getRight() == null)
      return maximum;
    while(maximum.getRight() != null) {
      maximum.getRight();
    }
    return maximum;
  }
}

  /*public void getNextNode() {
    if(this.left != null || this.right != null)
      return this;
    else 
      return null;  
  }*/


/* An AVLTree is a String-based class that represents an AVL-balanced binary 
 * search tree. It has one data member, "root", which is a reference to the 
 * root of the tree.
 *
 * Many of the methods in this class are virtually identical to methods in the
 * BST from the previous project (#3), including the constructor,
 * printPreorder(), verifySearchOrder(), and copy constructor.
 *
 * The insert() and remove() methods behave as in the plain BST, but both
 * methods should rebalance the tree as necessary. This is best done by creating
 * an array of references to AVLNode objects as the insert/remove methods search
 * for the place to do their work.  This array of references represents the path
 * taken to get from the root to the place where a change occurs in the tree.
 * Note that for remove(), this path might go deeper than the node removed, in
 * the case of removing a node with two children (think carefully about this).
 * After insert/remove finish updating the tree, they can pass the path to
 * rebalancePathToRoot() which actually does the rebalancing. Think about how
 * large the array of references needs to be, at its largest. An AVL tree with
 * height 30 must have at least 3,524,577 nodes, and if it has height 50, it
 * must have at least 53,316,291,172 nodes -- probably more than we care to put
 * in the tree. These results come from the minimum size of an AVL tree of
 * height h, which is described in your book as: S(h) = S(h - 1) + S(h - 2) + 1
 * (and base cases S(0) = 1, S(1) = 2).
 *
 * The printLevelOrder() method prints out all the nodes in the tree in
 * level-order (root, then the root's children, then their children, etc.). This
 * is like performing a breadth-first search of the tree. The method should put
 * up to 20 nodes on each line, and use multiple lines as necessary. This method
 * should use a Java queue, and it is iterative (not recursive). This method is
 * useful if we want to transmit the information for building exactly the same
 * tree to our correspondent. If we were to take all the non-NULL nodes and
 * insert them in the order printed by this method, we would get the exact same
 * tree. We would not always be able to construct the exact same tree if we were
 * to use printPreorder() instead.
 *
 * The rebalancePathToRoot() method takes an array of references to AVLNode
 * objects, and the number of references that are on the array. This array should
 * represent the path that needs rebalancing after an insert or remove. It's
 * probably best to have the root at the start of the array. This method should
 * walk from the bottom of the path to the root, checking for imbalances, and
 * correcting any it finds by calling rotation methods as necessary to correct
 * imbalances.
 */
class AVLTree {
  protected AVLNode root;
  private static final int imbalance = 1;
  
  AVLTree(AVLTree t) { assert(false); }

  AVLTree() { root = null; }

  public AVLNode getRoot() {
    return root;
  }


  protected void rebalancePathToRoot(AVLNode[] path, int numOnPath) {
    AVLNode n = null;
    for(int x = numOnPath; x >= 0; x--) {
      path[x].updateHeight();
      AVLNode heightTemp = null;
      int lh = heightTemp.getHeight(path[x].left);
      int rh = heightTemp.getHeight(path[x].right);

      if(lh + 1 < rh) {
        int rlh = heightTemp.getHeight(path[x].right.left);
        int rrh = heightTemp.getHeight(path[x].right.right);
          if(rlh > rrh)
            n = path[x].doubleRotateRightLeft();
          else
            n = path[x].singleRotateLeft();
      }
      else if (lh > rh + 1) {
        int llh = heightTemp.getHeight(path[x].left.left);
        int lrh = heightTemp.getHeight(path[x].left.right);
          if(lrh > llh) 
            n = path[x].doubleRotateLeftRight();
          else
            n = path[x].singleRotateRight();
      }

      if(n != null) {
        if(x == 0)
          root = n;
        else if(path[x - 1].left == path[x]) 
          path[x - 1].left = n;
        else 
          path[x - 1].right = n;
      }
    } 
  }
  /**
   * insert()
   *
   * This function inserts a node with reference to string item into the tree in correct order
   * 
   * Parameters:
   *   item: string, node: AVLNode 
   * 
   * Return value: new addition to the AVL tree  
   */  
  public void insert(String item) {
    AVLNode toInsert = new AVLNode(item, null, null, 0);
    AVLNode[] path = new AVLNode[32];
    int top = -1;

    if(root == null) {
      root = toInsert;
      return;
    }  

    AVLNode currNode = root;
    while(currNode != null) {
      path[++top] = currNode;
      int compare = toInsert.data.compareTo(currNode.data);
      if(compare < 0) {
        currNode = currNode.left;
        if (currNode == null) {
          currNode = toInsert;
          path[++top] = currNode;
          break;
        }
      }
      else if(compare > 0) {
        currNode = currNode.right;
        if (currNode == null) {
          currNode = toInsert;
          path[++top] = currNode;
          break;
        }
      }
      else 
        return; //
    }
    rebalancePathToRoot(path, top);
  }

  public void remove(String item) {
    AVLNode toRemove = new AVLNode(item, null, null, 0);
    AVLNode[] path = new AVLNode[32];
    int top = -1;

    if(root == null) {
      root = toRemove;
      return;
    }  

    AVLNode currNode = root;
    while(currNode != null) {
      path[++top] = currNode;
      int compare = toRemove.data.compareTo(currNode.data);
      if(compare < 0) 
        currNode = currNode.left;
      else if(compare > 0)
        currNode = currNode.right;
      else 
        break; //
    }
    if(currNode == null)
      return; //item does not exist within the tree

    //implement the code to rebalance and finally 
    rebalancePathToRoot(path, top);
  }

  /*public void remove(String item) {
    root = remove(item, root);
    //System.out.println(root + " a");
  }*/

  public void printLevelOrder() {
    int count = 0;

    Queue<AVLNode> queue = new LinkedList<AVLNode>();
    queue.add(root);
    while(!queue.isEmpty()) {
      AVLNode tempNode = queue.poll();
      System.out.print(tempNode.data + " ");
      count++;
      if(tempNode.left != null) {
        queue.add(tempNode.left);
      }
      else if(tempNode.right != null) {
        queue.add(tempNode.right);
      }
      else 
        return;
      if(count == 20) {
        if(tempNode.left != null || tempNode.right != null) {
          System.out.println();
          count = 0;
        }
      }
    }
  }

  public void printPreorder() { 
    if (root != null) root.printPreorder(); 
  }

  public void verifySearchOrder() { 
    if (root != null) root.verifySearchOrder(); 
  }

  public void verifyBalance() { 
    if (root != null) root.verifyBalance(); 
  }

}


/* The EncryptionTree for this project is exactly the same as for the previous
 * project, except that it now has an AVLTree as its parent class.
 */
class EncryptionTree extends AVLTree {
  EncryptionTree() {}

  public String encrypt(String item) {
  AVLNode currNode = this.getRoot();
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

  public String decrypt(String path) {
  AVLNode currNode = this.getRoot();
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