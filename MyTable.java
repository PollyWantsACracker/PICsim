/*
Die Instanz dieser Klasse stellt die Tabelle zur visualisierung des Sourcecodes da.
Der Grund für eine eigene JTabel-Klasse:
Durch das überschreiben der prepareRenderer-Funktion hat man die Möglichkeit,
die Objekte in der Tabelle individueller zu verändern.
*/

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import java.util.concurrent.locks.*;


public class MyTable extends JTable {
  
  private Steuerung steuerung; // wird benötigt, um den aktuellen ProgrammCounter zu bekommen
  private Lock threadLock;
  
  public MyTable(MyTableModel myTableModel, Steuerung aSteuerung) {
    
    super(myTableModel);
    steuerung = aSteuerung;
    threadLock = new ReentrantLock();
    
  }
  
  public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {  
    
    Component cell = super.prepareRenderer(renderer, row, column); // aktuelle Tabellenzelle
    
    String programmCounterStr = this.getModel().getValueAt(row, 1).toString();
    int programmCounterValue = -1;
    
    if (!programmCounterStr.equals("")) {
      
      programmCounterValue = Integer.parseInt(programmCounterStr, 16);
      
    }
    
    Color background;
    Color foreground; 
    
    if (programmCounterValue == -1) {
      
      background = Color.WHITE;
      foreground = Color.BLACK;
      
      threadLock.lock();
      
      try {
        
        cell.setBackground(background);
        cell.setForeground(foreground);
        
      } catch(Exception e) {
        
      } finally {
        
        threadLock.unlock();
        repaint();
        return cell;
        
      } 
    } 
    
    boolean breakPointGesetzt = (boolean)this.getModel().getValueAt(row, 0);
    int actualProgrammCounter = steuerung.getDataStorage().getProgrammCounter();
    
    if (breakPointGesetzt && (actualProgrammCounter == programmCounterValue)) {
      
      background = Color.GREEN;
      foreground = Color.BLACK;
      
    } else if (breakPointGesetzt && (actualProgrammCounter != programmCounterValue)){
      
      background = Color.RED;
      foreground = Color.WHITE;
      
    } else if (!breakPointGesetzt && (actualProgrammCounter == programmCounterValue)) {
      
      background = Color.YELLOW;
      foreground = Color.BLACK;
      
    } else {
      
      background = Color.WHITE;
      foreground = Color.BLACK;
      
    }
    
    threadLock.lock();
    
    try {
      
      cell.setBackground(background);
      cell.setForeground(foreground);
      
    } catch(Exception e) {
      
    } finally {
      
      threadLock.unlock();
      repaint();
      return cell;
      
    } 
  }
}