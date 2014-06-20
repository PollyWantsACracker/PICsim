import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Hardwareansteuerung {
  
  private CommPortIdentifier portId;
  private SerialPort port;
  private OutputStreamWriter out;
  private InputStreamReader in;
  private char CR = '\r';
  private char LF = '\n';
  
  private DataStorage dataStorage;
  
  public Hardwareansteuerung(DataStorage aDataStorage) {
    
    dataStorage = aDataStorage;
    
  }
  
  public boolean open(String comportUsed) {
    
    boolean found = false;
    
    try {
      
      Enumeration portList = CommPortIdentifier.getPortIdentifiers();
      
      while (portList.hasMoreElements()) {
        
        portId = (CommPortIdentifier) portList.nextElement();
        
        if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
          
          if (portId.getName().equals(comportUsed)) {
            
            found = true;
            break;
            
          }
        }
      }
      
      if (!found) {
        
        return false;
        
      } 
      
      this.port = (SerialPort) this.portId.open("PICSIM", 2000);
      port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
      in = new InputStreamReader(port.getInputStream());
      out = new OutputStreamWriter(port.getOutputStream());
      
      return true;
      
    } catch (Exception e) {
      
      return false;
      
    }
    
  }
  
  public void sendRS232() throws Exception {
    
    String p_a = encodeData(dataStorage.getValue(5));
    String t_a = encodeData(dataStorage.getValue(0x85));
    String p_b = encodeData(dataStorage.getValue(6));
    String t_b = encodeData(dataStorage.getValue(0x86));
    String send = t_a + p_a + t_b + p_b;
    write(send + CR);
    
  }
  
  public void write(String s) throws Exception {
    
    out.write(s);
    out.flush();
    
  }
  
  private void write(byte s) throws Exception {
    
    out.write(s);
    out.flush();
    
  }
  
  public void write(int s) throws Exception {
    
    out.write(s);
    out.flush();
    
  }
  
  public ArrayList<Integer> read() throws Exception {
    
    int n, i;
    char c = 0;
    String answer = new String("");
    int index = 5;
    
    while (c != CR && index > 0 && in.ready()) {
      
      n = in.read();
      
      if (n != -1) {
        
        c = (char) n;
        answer += c;
        index--;
        
      }
    }
    
    if (index <= 0 && c != CR) {
      
      return null;
      
    }
    
    delay(1);
    
    ArrayList<Integer> decodedValues = new ArrayList<Integer>();
    decodedValues = decodeData(answer);
    
    if (decodedValues.size() > 0) {
      
      return decodedValues;
      
    } else {
      
      return null;
      
    }
  }
  
  private void delay(int a) {
    
    try {
      
      Thread.sleep(a);
      
    } catch (InterruptedException e) {
      
      
    }
  }
  
  public void close() throws Exception {
    
    try {
      
      port.close();
      
    } catch (Exception e) {
      
      
    }
  }
  
  private String encodeData(int b) {
    
    char c1 = (char) (0x30 + ((b & 0xF0) >> 4));
    char c2 = (char) (0x30 + (b & 0x0F));
    
    return "" + c1 + c2;
    
  }
  
  private ArrayList<Integer> decodeData(String s) {
    
    int i0 = s.charAt(0) - 0x30;
    int i1 = s.charAt(1) - 0x30;
    int i2 = s.charAt(2) - 0x30;
    int i3 = s.charAt(3) - 0x30;
    
    ArrayList<Integer> tokens = new ArrayList<Integer>();
    
    if (i0 >= 0 && i1 >= 0 && i2 >= 0 && i3 >= 0 && i0 <= 0xF && i1 <= 0xF&& i2 <= 0xF && i3 <= 0xF) {
      
      int a = (((int) i0 & 0x0F) << 4) | ((int) i1 & 0x0F);
      tokens.add(a);
      int b = (((int) i2 & 0x0F) << 4) | ((int) i3 & 0x0F);
      tokens.add(b);
    }  
    
    if (tokens.size() > 0) {
      
      return tokens;
      
    } else {
      
      return null;
    } 
  }
}