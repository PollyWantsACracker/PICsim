import java.util.*;
import java.util.concurrent.locks.*;

public class Stack {
  
  private List<Integer> stack;
  private Lock threadLock;
  
  public Stack() {
    
    stack = new ArrayList<Integer>();
    threadLock = new ReentrantLock();
    
  }
  
  public void addAddress(int address) {
    
    threadLock.lock();
    
    try {
      
      stack.add(address);
      
    } catch(Exception e) {
      
    } finally {
      
      threadLock.unlock();
      
    }  
  }
  
  public String toString() {
    
    String stackString = "From top of stack:\n";
    int size = stack.size();
    
    
    
    for (int i = size; i > 0 ; i-- ) {
      
      stackString += Integer.toString(stack.get(i - 1), 16) + "\n";
      
    }
    
    return stackString; 
    
  }
  
  public int getLastAddress() {
    
    int address = 0;
    threadLock.lock();
    
    try {
      
      address = stack.get(stack.size() - 1);
      stack.remove(stack.size() - 1);
      
    } catch(Exception e) {
      
    } finally {
      
      threadLock.unlock();
      return address;
      
    } 
  }
}