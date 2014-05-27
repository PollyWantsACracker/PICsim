import java.util.*;

public class Steuerung {
  
  private MainFrame mainFrame;
  private Parser parser;
  private DataStorage dataStorage;
  private WRegister wRegister;
  private Stack stack;
  
  public Steuerung() {
    
    mainFrame = new MainFrame("PIC16F84 Simulator", this);
    parser = new Parser(this);
    dataStorage = new DataStorage();
    wRegister = new WRegister();
    stack = new Stack(); 
    
  }
  
  public Parser getParser() {
    
    return parser;
    
  }
  
  public DataStorage getDataStorage() {
  
    return dataStorage;
    
  }
  
  public WRegister getWRegister() {
  
    return wRegister;
    
  }
  
  public Stack getStack() {
  
    return stack;
    
  }  
}