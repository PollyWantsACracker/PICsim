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

public class MyTable extends JTable {
  
  private Steuerung steuerung; // wird benötigt, um den aktuellen ProgrammCounter zu bekommen
  
  public MyTable(MyTableModel myTableModel, Steuerung aSteuerung) {
    
    super(myTableModel);
    steuerung = aSteuerung;
    
  }
  
  public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {  
    
    Component cell = super.prepareRenderer(renderer, row, column);
    cell.setFocusable(false);
    
    String programmCounterStr = this.getModel().getValueAt(row, 1).toString();
    int programmCounterValue = -1;
    
    if (!programmCounterStr.equals("")) {
      
      programmCounterValue = Integer.parseInt(programmCounterStr, 16);
      
    } 
    
    if (programmCounterValue == -1) {
      
      cell.setBackground(Color.WHITE);
      cell.setForeground(Color.BLACK);
      repaint();
      return cell;
      
    } 
    
    boolean breakPointGesetzt = (boolean)this.getModel().getValueAt(row, 0);
    int actualProgrammCounter = steuerung.getDataStorage().getProgrammCounter();
    
    if (breakPointGesetzt && (actualProgrammCounter == programmCounterValue)) {
      
      cell.setBackground(Color.GREEN);
      cell.setForeground(Color.BLACK);
      
    } else if (breakPointGesetzt && (actualProgrammCounter != programmCounterValue)){
      
      cell.setBackground(Color.RED);
      cell.setForeground(Color.WHITE);
      
    } else if (!breakPointGesetzt && (actualProgrammCounter == programmCounterValue)) {
      
      cell.setBackground(Color.YELLOW);
      cell.setForeground(Color.BLACK);
      
    } else {
      
      cell.setBackground(Color.WHITE);
      cell.setForeground(Color.BLACK);
      
    } 
    
    repaint();
    return cell;
    
  }
}