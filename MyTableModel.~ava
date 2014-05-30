/*
Die Instanz dieser Klasse stellt das Model der SourceCodeTabelle dar.
Durch das Überschreiben einer Methoden ist es Möglich, eine Tabelle zu erzeugen,
die in unterschiedlichen Spalten unterschiedliche Objekte darstellt.
*/

import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel {
  
  private String[] columnNames;
  private Object[][] data;
  
  public MyTableModel (Object[][] tableData, String[] columnHeaders) {
    
    columnNames = columnHeaders;
    data = tableData;
    
  }
  
  public int getColumnCount() {
    
    return columnNames.length;
    
  }
  
  public int getRowCount() {
    
    return data.length;
    
  }
  
  public String getColumnName(int col) {
    
    return columnNames[col];
    
  }
  
  public Object getValueAt(int row, int col) {
    
    return data[row][col];
    
  }
  
  public Class getColumnClass(int c) { 
    
    return getValueAt(0, c).getClass(); // diese Zeile macht es möglich, die Breakpoints als CheckBox einzubinden
    
  }
  
  public boolean isCellEditable(int row, int col) { // nur die Breakpoints darf man setzten, die restliche Tabelle bleibt unveränderbar
    
    if (col == 0 && data[row][col + 1] != "") {
      
      return true ;  
      
    } else {
      
      return false;
      
    }
  }
  
  public void setValueAt(Object value, int row, int col) {
    
    data[row][col] = value;
    fireTableCellUpdated(row, col);
    
  }
}