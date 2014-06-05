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

public class MyTable extends JTable {
  
  private Steuerung steuerung; // wird benötigt, um den aktuellen ProgrammCounter zu bekommen
  
  public MyTable(MyTableModel myTableModel, Steuerung aSteuerung) {
    
    super(myTableModel);
    steuerung = aSteuerung;
    
  }
  
  public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {  
    
    Component cell = super.prepareRenderer(renderer, row, column);   
    
    if( column != 0 ) { // die ganze Tabelle ohne Breakpointspalte
      
      JLabel cellLabel = (JLabel)cell;  
      boolean breakPoint = (Boolean)this.getModel().getValueAt(row, 0); // Checkbox-Wert
      String cellText = (String)this.getModel().getValueAt(row, 1); // Befehls-ProgrammCounter
      
      if (!cellText.equals("") && !breakPoint) { // Färbung der Zeile von aktuellem ProgrammCounter
        
        int hexValue = Integer.parseInt(cellText, 16);
        
        if (hexValue == steuerung.getDataStorage().getProgrammCounter()) {
          
          cellLabel.setBackground(Color.YELLOW);
          cellLabel.setForeground(Color.BLACK);
          repaint(); // unbedingt notwendig, da sonst Fehler in der Darstellung der Tabelle auftreten
          return cellLabel;
          
        } 
        
      } else if (!cellText.equals("") && breakPoint) {
        
        int hexValue = Integer.parseInt(cellText, 16);
        
        if (hexValue == steuerung.getDataStorage().getProgrammCounter()) {
          
          cellLabel.setBackground(Color.GREEN);
          cellLabel.setForeground(Color.BLACK);
          repaint(); // unbedingt notwendig, da sonst Fehler in der Darstellung der Tabelle auftreten
          return cellLabel;
          
        } 
        
      }
      if( breakPoint == false ) { // Breakpointdarstellung 
        
        cellLabel.setForeground(Color.BLACK);
        cellLabel.setBackground( Color.WHITE );
        
      }  
      
      else {  
        
        cellLabel.setForeground(Color.WHITE);
        cellLabel.setBackground( Color.RED );
        
      }  
      
      repaint(); // unbedingt notwendig, da sonst Fehler in der Darstellung der Tabelle auftreten
      return cellLabel; 
      
    }  
    
    return cell;
    
  }
}