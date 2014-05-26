import java.util.*;
import java.io.*;

public class Parser {
  
  private List<String[]> lineList;
  private List<Command> commandList;
  private Steuerung steuerung;
  
  public Parser(Steuerung aSteuerung) {
    
    lineList = new ArrayList<String[]>();
    commandList = new ArrayList<Command>();
    steuerung = aSteuerung;
    
  }
  
  public int getNumberOfLines() {
    
    return lineList.size();
    
  }
  
  public String getString(int i, int j) {
    
    return lineList.get(i)[j];
    
  }
  /*
  public void parseCommands() {
    
    for (int i = 0; i < lineList.size(); i ++ ) {
      
      if (lineList.get(i)[1] == "") {
        
        continue;
        
      } 
      
      int parameter1 = 0;
      int parameter2 = 0;
      int programmCounterLine = Integer.parseInt(lineList.get(i)[1]);
      int commandCode = Integer.parseInt(lineList.get(i)[2], 16);
      String mask = "3F00";
      int maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue) {
        
        case  1792: //ADDWF
        
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandAddwf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister())); 
        
        break;
        
        case 1280: //ANDWF
        
        case 2304:
        //COMF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandComf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        /*
        case 768:
        //DECF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandDecf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 2816:
        //DECFSZ
        mask = "0080";
        maskedValue = programmCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = programmCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandDecfsz(programmCounterLine, parameter1, parameter2, 2, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 2560:
        //INCF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandIncf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 3840:
        //INCFSZ
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandIncfsz(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 1024:
        //IORWF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandIorwf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 2048:
        //MOVF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandMovf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 3328:
        //RLF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandRlf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 3072:
        //RRF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandRrf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 512:
        //SUBWF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandSubwf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 3584:
        //SWAPF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandSwapf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 1536:
        //XORWF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandXorwf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 14592:
        //ANDLW
        
        case 14336:
        //IORLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandIorlw(programmCounterLine, parameter1, 0, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 14848:
        //XORLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandXorlw(programmCounterLine, parameter1, 0, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        default:
        break;
      }
      
      mask = "3C00";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue) {
        case 13312:
        //RETLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandRetlw(programmCounterLine, parameter1, 0, 2, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 12288:
        //MOVLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandMovlw(programmCounterLine, parameter1, 0, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 4096:
        //BCF
        mask = "0380";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandBcf(programmCounterLine, parameter1, parameter2/128, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 5120:
        //BSF
        mask = "0380";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandBsf(programmCounterLine, parameter1, parameter2/128, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 6144:
        //BTFSC
        mask = "0380";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandBtfsc(programmCounterLine, parameter1, parameter2/128, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 7168:
        //BTFSS
        mask = "0380";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandBtfss(programmCounterLine, parameter1, parameter2/128, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        default:
        break;
      }
      
      mask = "3FFF";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue){
        case 100:
        //CLRWDT
        
        case 9:
        //RETFIE
        
        case 8:
        //RETURN
        commandList.add(new CommandReturn(programmCounterLine, 0, 0, 2, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 99:
        //SLEEP
        
        default:
        break;
      }
      
      mask = "3F80";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue){
        case 256:
        //CLRW
        commandList.add(new CommandClrw(programmCounterLine, 0, 0, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 384:
        //CLRF
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandClrf(programmCounterLine, parameter1, 0, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 128:
        //MOVWF
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandMovwf(programmCounterLine, parameter1, 0, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        default:
        break;
      }
      
      mask = "3800";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue) {
        case 10240:
        //GOTO
        mask = "07FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandGoto(programmCounterLine, parameter1, 0, 2, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 8192:
        //CALL
        mask = "07FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandCall(programmCounterLine, parameter1, 0, 2, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        default:
        break;
      }
      
      mask = "3E00";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue) {
        case 15872:
        //ADDLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        commandList.add(new CommandAddlw(programmCounterLine, parameter1, 0, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        
        case 15360:
        //SUBLW
        
        default:
        break;
      }
      
      mask = "3F9F";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      if (maskedValue == 0) {
        //NOP
        
      }
      
    }
    
  } // end of for
  
        */
  public void parse(File sourceFile) throws FileNotFoundException {
    
    Scanner scanner = new Scanner(sourceFile);
    
    while (scanner.hasNext()) {
      
      String line = scanner.nextLine();
      lineList.add(parseLine(line));
      
    }
  }
  
  private String cutString(String s, Boolean mode) {
    
    if (mode) {
      
      while (s.length() != 0 && s.charAt(0) == ' ') { 
        
        s = s.substring(1);
        
      } 
      
    } else {
      
      while (s.length() != 0 && s.charAt(0) != ' ') { 
        
        s = s.substring(1);
        
      } 
      
    }
    
    return s;
    
  }
  
  private String[] parseLine(String line) { // Diese Funktion basiert auf dem .lst-Dateiformat aus den Testprogrammen!
    
    String[] parsedLine = new String[6];
    
    if (line.charAt(0) == ' ') { // kein Assemblerbefehl
      
      parsedLine[0] = "";
      parsedLine[1] = "";
      parsedLine[2] = line.substring(20, 25);
      
      if (line.charAt(27) == ' ') { // Kommentar, Symbol oder Leerzeile
        
        parsedLine[3] = "";
        line = cutString(line.substring(27), true);
        
        if (line.length() == 0) { // Leerzeile 
          
          parsedLine[4] = "";
          parsedLine[5] = "";
          return parsedLine;
          
        } else if (line.charAt(0) == ';') { // Kommentar
          
          parsedLine[4] = "";
          parsedLine[5] = line.substring(1);
          return parsedLine;
          
        } else { // Symbol mit oder Symbol ohne Kommentar
          
          String string4 = "";
          
          while (line.length() != 0 && line.charAt(0) != ';') { 
          
            int index = line.indexOf(' ', 0);
            
            if (index == -1 ) { // Symbol ohne Kommentar
              
              parsedLine[4] = string4 + line;
              parsedLine[5] = "";
              return parsedLine;
              
            } 
            
            string4 =  string4 + line.substring(0, index) + " ";
            line = cutString(line, false);
            line = cutString(line, true);
            
          }
          
          parsedLine[4] = string4.substring(1);
          parsedLine[5] = line.substring(1);
          return parsedLine;
          
        } 
      } else { // Sprungmaske
        
        line = line.substring(27);
        int index = line.indexOf(' ', 0);
        
        if (index == -1 ) { // Sprungmaske ohne Kommentar
          
          parsedLine[3] = line;
          parsedLine[4] = "";
          parsedLine[5] = "";
          return parsedLine;
          
        } else { // Sprungmaske mit Kommentar
          
          parsedLine[3] = line.substring(0, index);
          
          int commentIndex = line.indexOf(';', 0);
          
          if (commentIndex == -1) {
            
            parsedLine[4] = "";
            parsedLine[5] = "";
            return parsedLine;
          }
          
          line = line.substring(commentIndex);
          parsedLine[4] = "";
          parsedLine[5] = line.substring(1);
          return parsedLine;
          
        } 
      }
      
    } else { // Assemblerbefehl mit Programmcounterline
      
      parsedLine[0] = line.substring(0, 4);
      parsedLine[1] = line.substring(5, 9);
      parsedLine[2] = line.substring(20, 25);
      parsedLine[3] = "";
      
      line = cutString(line.substring(25), true);
      
      String string3 = "";
      
      while (line.length() != 0 && line.charAt(0) != ';') { 
        
        int index = line.indexOf(' ', 0);
        
        if (index == -1 ) { // Befehl ohne Kommentar
          
          if (string3.length() == 0) {
            
            parsedLine[4] = line;
            
          } else {
            
            parsedLine[4] = string3.substring(1) + " " + line;
            
          }
          
          parsedLine[5] = "";
          return parsedLine;
          
        } 
        
        string3 =  string3 + " " + line.substring(0, index);
        line = cutString(line, false);
        line = cutString(line, true);
        
      }
      
      parsedLine[4] = string3.substring(1);
      
      if (line.length() == 0) {
        
        parsedLine[5] = line;
        
      } else {
        
        parsedLine[5] = line.substring(1);
        
      } 
      
      return parsedLine;
      
    }
  }
}