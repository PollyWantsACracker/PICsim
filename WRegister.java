import java.util.concurrent.locks.*;

public class WRegister {
  
  private int value;
  private Lock threadLock;
  
  public WRegister () {
    
    value = 0;
    threadLock = new ReentrantLock();
    
  }
  
  public int getValue() {
    
    int aValue = 0;
    threadLock.lock();
    
    try {
      
      aValue = value;
      
    } catch(Exception e) {
      
    } finally {
      
      threadLock.unlock();
      return aValue;
      
    } 
    
    
  }
  
  public void setValue(int newValue) {
    
    threadLock.lock();
    
    try {
      
      value = newValue;
      
    } catch(Exception e) {
      
    } finally {
      
      threadLock.unlock();
      
    } 
  }
}