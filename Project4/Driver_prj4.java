/**
 * file: Driver_prj4.java
 * author: Alexa Javellana
 * course: CMPT 435
 * assignment: project 4
 * due date: april 9th 2018
 * version: ??? i lost count
 *
 *
 * This file contains the logic to solve project 4, AVL tree encryption, codebook, etc.
 * Incorporates methods from AVLTree.java class 
 *
 *i x — insert word x into the codebook (here x represents any word)
 *r x — remove word x from the codebook (here x represents any word)
 *e 'cleartext message' — encrypt the given cleartext message. 
 *d 'encrypted message' — decrypt the given encrypted message. 
 *p — print the codebook in preorder format
 *q — quit the program (stop processing input)
*/
import java.util.Scanner;
import java.util.Arrays;

public class Driver_prj4 {
  public static void main(String[] args) {

  Scanner input = new Scanner(System.in);

  EncryptionTree tree = new EncryptionTree();
  //tree.insert(a.getData());

  //Strings and string arrays to read the input, store values for decryption and 
  //encryption
  String x;
  String[] word;
  String message, decryptedMessage, encryptedMessage;
  String[] splitMessage;
  String outcome = "";
  String decoded, encoded;
  while(input.hasNextLine()) {
    x = input.nextLine();
  //If the line indicates an insert
  if(x.charAt(0) == 'i') {
    word = x.split(" ");
    tree.insert(word[1]);
  } 
  //If the line indicates a remove
  else if(x.charAt(0) == 'r') {
    //BSTNode a = tree.getRoot();
    word = x.split(" ");
    tree.remove(word[1]);
    //System.out.println(tree.getRoot());
  }
  //If the line indicates an encryption call
  else if(x.charAt(0) == 'e') {
    outcome = "";
    word = x.split("'");
    message = word[1];
    splitMessage = message.split(" ");
    for(int i = 0; i < splitMessage.length; i++) {
      encoded = tree.encrypt(splitMessage[i]);
      outcome += encoded + " "; 
    }
    System.out.println(outcome);
  }
  //If the line indicates a decryption call   
  else if(x.charAt(0) == 'd') {
    outcome = "";
    word = x.split("'");
    message = word[1];
    splitMessage = message.split(" ");
    for(int i = 0; i<splitMessage.length; i++) {
      decoded = tree.decrypt(splitMessage[i]);
      outcome += decoded + " ";
    }
    System.out.println(outcome);
  }
  //If the line indicates a print call
  else if(x.charAt(0) == 'p') {
    if(tree.getRoot() != null)
      tree.getRoot().printPreorder(); 
  } 
  else if(x.charAt(0) == 'l') {
    if(tree.getRoot() != null)
      tree.getRoot().printLevelOrder(); 
  }   
  //If the line indicates the end of commands/quit of program
  else if(x.charAt(0) == 'q') {
    return;
  }
  }
  }
}  