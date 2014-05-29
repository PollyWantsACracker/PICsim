import java.util.*;
import java.io.*;

public class Parser {
  
  private List<String[]> lineList;
  private List<Command> commandList;
  private Steuerung steuerung;
  private int startAdress;
  
  public Parser(Steuerung aSteuerung) {
    
    lineList = new ArrayList<String[]>();
    commandList = new ArrayList<Command>();
    steuerung = aSteuerung;
    startAdress = 0;
    
  }
  
  public Command getCommand(int programmCounter) {
    
    int index = 0;
    
    for (int i = 0; i < commandList.size(); i++) {
      
      if (commandList.get(i).getProgrammCounterLine() == programmCounter) {
        
        index = i;
        break;
        
      } 
      
    } 
    
    return commandList.get(index);
    
  }
  
  public int getNumberOfLines() {
    
    return lineList.size();
    
  }
  
  public String getString(int row, int column) {
    
    return lineList.get(row)[column];
    
  }
  
  public int getCurrentCommandTableIndex(int programmCounter) {
    
    for (int i = 0; i < lineList.size() ; i++) {
      
      if (lineList.get(i)[0] == "") {
        
        continue;
        
      } 
      
      if (Integer.parseInt(lineList.get(i)[0], 16) == programmCounter) {
        
        programmCounter = i;
        break;
        
      } 
      
      
      
    } 
    
    return programmCounter;
    
  }
  
  private void parseCommands() {
    
    for (int i = 0; i < lineList.size(); i ++ ) {
      
      if (lineList.get(i)[1] == "") { // es werden nur die relevanten Assemblerbefehle erzeugt
        
        continue;
        
      } 
      
      int parameter1 = 0;
      int parameter2 = 0;
      int machineCycles = 0;
      int programmCounterLine = Integer.parseInt(lineList.get(i)[0], 16);
      int commandCode = Integer.parseInt(lineList.get(i)[1], 16);
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
        machineCycles = 1;
        commandList.add(new CommandAddwf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister())); 
        continue;
        
        case 1280: //ANDWF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandAndwf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister())); 
        continue;
        
        case 2304: //COMF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandComf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 768: //DECF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandDecf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 2816: //DECFSZ
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandDecfsz(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 2560: //INCF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandIncf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 3840: //INCFSZ
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandIncfsz(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 1024: //IORWF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandIorwf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 2048: //MOVF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandMovf(programmCounterLine, parameter1, parameter2, 1, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 3328: //RLF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandRlf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 3072: //RRF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandRrf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 512: //SUBWF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandSubwf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 3584: //SWAPF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandSwapf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 1536: //XORWF
        mask = "0080";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandXorwf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 14592: //ANDLW
        mask = "00ff";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandAndlw(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 14336: //IORLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandIorlw(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 14848: //XORLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandXorlw(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        default:
        
      }
      
      mask = "3C00";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue) {
        
        case 13312: //RETLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 2;
        commandList.add(new CommandRetlw(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister(), steuerung.getStack()));
        continue;
        
        case 12288: //MOVLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandMovlw(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 4096: //BCF
        mask = "0380";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandBcf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 5120: //BSF
        mask = "0380";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandBsf(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 6144: //BTFSC
        mask = "0380";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandBtfsc(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 7168: //BTFSS
        mask = "0380";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter2 = maskedValue;
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandBtfss(programmCounterLine, parameter1, parameter2, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        default:
        
      }
      
      mask = "3FFF";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue) {
        
        case 100: //CLRWDT
        machineCycles = 1;
        // fehlt!
        continue;
        
        case 9: //RETFIE
        machineCycles = 2;
        // fehlt!
        continue;
        
        case 8: //RETURN
        machineCycles = 2;
        commandList.add(new CommandReturn(programmCounterLine, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister(), steuerung.getStack()));
        continue;
        
        case 99: //SLEEP
        machineCycles = 1;
        // fehlt!
        continue;
        
        default: 
        
      }
      
      mask = "3F80";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue){
        
        case 256: //CLRW
        machineCycles = 1;
        commandList.add(new CommandClrw(programmCounterLine, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 384: //CLRF
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandClrf(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 128: //MOVWF
        mask = "007F";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandMovwf(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        default:
        
      }
      
      mask = "3800";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue) {
        
        case 10240: //GOTO
        mask = "07FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 2;
        commandList.add(new CommandGoto(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister())); 
        continue;
        
        case 8192: //CALL
        mask = "07FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 2;
        commandList.add(new CommandCall(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister(), steuerung.getStack()));
        continue;
        
        default:
        
      }
      
      mask = "3E00";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      switch (maskedValue) {
        
        case 15872: //ADDLW
        mask = "00FF";
        maskedValue = commandCode & Integer.parseInt(mask, 16);
        parameter1 = maskedValue;
        machineCycles = 1;
        commandList.add(new CommandAddlw(programmCounterLine, parameter1, machineCycles, steuerung.getDataStorage(), steuerung.getWRegister()));
        continue;
        
        case 15360: //SUBLW
        machineCycles = 1;
        // fehlt!
        continue;
        
        default:
        
      }
      
      mask = "3F9F";
      maskedValue = commandCode & Integer.parseInt(mask, 16);
      
      if (maskedValue == 0) { //NOP
        
        machineCycles = 1;
        // fehlt!
        continue;
        
      }
    }
  }
  
  public void parse(File sourceFile) throws FileNotFoundException {
    
    lineList = new ArrayList<String[]>();
    commandList = new ArrayList<Command>();
    
    Scanner scanner = new Scanner(sourceFile);
    
    while (scanner.hasNext()) {
      
      String line = scanner.nextLine();
      lineList.add(parseLine(line));
      
    }
    
    parseCommands();
    
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
          
          parsedLine[4] = string4.substring(0, string4.length() - 1);
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
      
      if (line.charAt(27) != ' ') { // Befehl plus Sprungmaske
        
        line = cutString(line.substring(25), true);
        parsedLine[3] = line.substring(0, line.indexOf(' ', 0));
        line = cutString(line, false);
        line = cutString(line, true);
        
      } else {
        
        parsedLine[3] = "";
        line = cutString(line.substring(25), true);
        
      } 
      
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