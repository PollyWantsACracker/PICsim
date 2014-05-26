import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.beans.*;
import java.io.*;
import javax.swing.filechooser.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;

public class MainFrame extends JFrame {
  
  private Steuerung steuerung;
  
  private JMenuBar jMenuBar;
  
  private JMenu jMenuFile;
  private JMenu jMenuInfo;
  private JMenu jMenuHelp;
  
  private JMenuItem jMenuItemFileOpen;
  private JMenuItem jMenuItemInfoView;
  private JMenuItem jMenuItemHelpView;
  
  private JScrollPane jScrollPaneDataStorage;
  private JScrollPane jScrollPaneSourceCode;
  
  private JPanel jPanelDataStorage;
  private JPanel jPanelSteuerpult;
  
  private JLabel[] jLabelDataStorage;
  private JTextField[] jTextFieldDataStorage;
  
  private JButton jButtonReset = new JButton();
  private JButton jButtonStart = new JButton();
  private JButton jButtonStop = new JButton();
  
  private JTable jTableSourceCode;
  
  private String[] columnHeaders;
  private Object[][] tableData;
  
  public MainFrame(String title, Steuerung s) { 
    
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 800; 
    int frameHeight = 600;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(true);
    Container cp = getContentPane();
    cp.setLayout(null);
    
    steuerung = s;
    
    this.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/favicon.png"));
    
    jMenuBar = new JMenuBar();
    
    jMenuFile = new JMenu();
    jMenuFile.setText("Datei");
    
    jMenuItemFileOpen = new JMenuItem();
    jMenuItemFileOpen.setText("Öffnen");
    jMenuItemFileOpen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jMenuItemFileOpenActionPerformed(evt);
      }
    });
    
    jMenuFile.add(jMenuItemFileOpen);
    jMenuBar.add(jMenuFile);
    
    jMenuInfo = new JMenu();
    jMenuInfo.setText("Info");
    
    jMenuItemInfoView = new JMenuItem();
    jMenuItemInfoView.setText("Info Anzeigen");
    jMenuItemInfoView.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jMenuItemInfoViewActionPerformed(evt);
      }
    });
    
    jMenuInfo.add(jMenuItemInfoView);
    jMenuBar.add(jMenuInfo);
    
    jMenuHelp = new JMenu();
    jMenuHelp.setText("Hilfe");
    
    jMenuItemHelpView = new JMenuItem();
    jMenuItemHelpView.setText("Hilfe Anzeigen");
    jMenuItemHelpView.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jMenuItemHelpViewActionPerformed(evt);
      }
    });
    
    jMenuHelp.add(jMenuItemHelpView);
    jMenuBar.add(jMenuHelp);
    
    setJMenuBar(jMenuBar);
    
    jScrollPaneDataStorage = new JScrollPane();
    jScrollPaneDataStorage.setBounds(0, 0, 307, 315);
    jScrollPaneDataStorage.setBorder(BorderFactory.createTitledBorder("Speicher"));
    jScrollPaneDataStorage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPaneDataStorage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    cp.add(jScrollPaneDataStorage);
    
    jScrollPaneSourceCode = new JScrollPane();
    jScrollPaneSourceCode.setBounds(307, 0, 475, 315);
    jScrollPaneSourceCode.setBorder(BorderFactory.createTitledBorder("Quellcode"));
    jScrollPaneSourceCode.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPaneSourceCode.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    cp.add(jScrollPaneSourceCode);
    
    jPanelDataStorage = new JPanel();
    jPanelDataStorage.setPreferredSize(new Dimension(525, 520));
    
    jPanelSteuerpult = new JPanel();
    jPanelSteuerpult.setBounds(0, 400, 115, 125);
    jPanelSteuerpult.setBorder(BorderFactory.createTitledBorder("Steuerpult"));
    cp.add(jPanelSteuerpult);
    
    jScrollPaneDataStorage.setViewportView(jPanelDataStorage); 
    
    jLabelDataStorage = new JLabel[48];
    jTextFieldDataStorage = new JTextField[256];
    
    jButtonReset.setPreferredSize(new Dimension(80, 25));
    jButtonReset.setText("Reset");
    jButtonReset.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonReset_ActionPerformed(evt);
      }
    });
    jPanelSteuerpult.add(jButtonReset);
    
    jButtonStart.setPreferredSize(new Dimension(80, 25));
    jButtonStart.setText("Start");
    jButtonStart.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonStart_ActionPerformed(evt);
      }
    });
    jPanelSteuerpult.add(jButtonStart);
    
    jButtonStop.setPreferredSize(new Dimension(80, 25));
    jButtonStop.setText("Stop");
    jButtonStop.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonStop_ActionPerformed(evt);
      }
    });
    jPanelSteuerpult.add(jButtonStop);
    
    initSpeicher();
    
    setVisible(true);
    
  }
  
  public void jButtonReset_ActionPerformed(ActionEvent evt) {
    
    
    
  }
  
  public void jButtonStart_ActionPerformed(ActionEvent evt) {
    
    
    
  }
  
  public void jButtonStop_ActionPerformed(ActionEvent evt) {
    
    
    
  }
  
  
  private void initSpeicher() {
    
    for (int i = 0; i < 17; i++ ) {
      
      for (int j = 0; j < 18; j++) {
        
        if (i == 0 && j == 0) { // 1. Platzhalter links oben
          
          jLabelDataStorage[j] = new JLabel();
          jLabelDataStorage[j].setPreferredSize(new Dimension(25, 25));
          jPanelDataStorage.add(jLabelDataStorage[j]);
          
        } else if(i == 0 && j != 0 && j != 17) { // 1. Zeile Beschriftung
          
          jLabelDataStorage[j] = new JLabel();
          jLabelDataStorage[j].setPreferredSize(new Dimension(25, 25));
          jLabelDataStorage[j].setText(Integer.toHexString(j - 1));
          jLabelDataStorage[j].setHorizontalAlignment(SwingConstants.CENTER);
          jPanelDataStorage.add(jLabelDataStorage[j]);
          
        } else if (i == 0 && j == 17) { // 2. Platzhalter rechts oben
          
          jLabelDataStorage[j] = new JLabel();
          jLabelDataStorage[j].setPreferredSize(new Dimension(25, 25));
          jPanelDataStorage.add(jLabelDataStorage[j]);
          
        } else if (i != 0 && j == 0) { // 1. Spalte Beschriftung
          
          jLabelDataStorage[i + 17] = new JLabel();
          jLabelDataStorage[j + 17].setPreferredSize(new Dimension(25, 25));
          jLabelDataStorage[j + 17].setText(Integer.toHexString(i - 1) + "x");
          jLabelDataStorage[j + 17].setHorizontalAlignment(SwingConstants.CENTER);
          jPanelDataStorage.add(jLabelDataStorage[j + 17]);
          
        } else if (i != 0 && j != 0 && j != 17) { // Alle Textfelder (DataStorage)
          
          jTextFieldDataStorage[(i - 1) * 15 + (j - 1)] = new JTextField();
          jTextFieldDataStorage[(i - 1) * 15 + (j - 1)].setPreferredSize(new Dimension(25, 25));
          jTextFieldDataStorage[(i - 1) * 15 + (j - 1)].setText("00");
          jTextFieldDataStorage[(i - 1) * 15 + (j - 1)].setHorizontalAlignment(SwingConstants.CENTER);
          jTextFieldDataStorage[(i - 1) * 15 + (j - 1)].setEditable(false);
          jPanelDataStorage.add(jTextFieldDataStorage[(i - 1) * 15 + (j - 1)]);
          
        } else if (i != 0 && j == 17) { // Platzhalter jeweils am ende einer Zeile
          
          jLabelDataStorage[j] = new JLabel();
          jLabelDataStorage[j].setPreferredSize(new Dimension(25, 25));
          jPanelDataStorage.add(jLabelDataStorage[j]);
          
        } 
      }
    }
  }
  
  public void updateDataTable(){
    
    for (int i = 0;i<256;i++) {
      
      jTextFieldDataStorage[i].setText(String.valueOf(steuerung.getDataStorage().getValue(i)));
          
    } // end of for
    
  }
  
  private void initColumns() {
    
    columnHeaders = new String[7];
    columnHeaders[0] = "B";
    columnHeaders[1] = "PC";
    columnHeaders[2] = "Hex";
    columnHeaders[3] = "Line";
    columnHeaders[4] = "Label";
    columnHeaders[5] = "Command";
    columnHeaders[6] = "Comment";   
    
  }
  
  private void initRows(int numberOfRows, int numberOfColumns) {
    
    tableData = new Object[numberOfRows][numberOfColumns];
    
    for (int row = 0; row < numberOfRows; row++ ) {
      
      for (int column = 0; column < numberOfColumns; column++) {
        
        if (column == 0) {
          
          tableData[row][column] = false;
          
        } else {
          
          tableData[row][column] = steuerung.getParser().getString(row, column - 1);
          
        }
      } 
    }  
  }
  
  private void layoutTable() {
    
    jTableSourceCode.getTableHeader().setResizingAllowed(false);
    jTableSourceCode.getTableHeader().setReorderingAllowed(false);
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    
    jScrollPaneSourceCode.setColumnHeader(new JViewport() {
      
      public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.height = 25;
        return d;
      }
      
    });
    
    jScrollPaneSourceCode.setViewportView(jTableSourceCode);
    
    jTableSourceCode.getColumnModel().getColumn(0).setPreferredWidth(20);
    jTableSourceCode.getColumnModel().getColumn(1).setPreferredWidth(50);
    jTableSourceCode.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    jTableSourceCode.getColumnModel().getColumn(2).setPreferredWidth(50);
    jTableSourceCode.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    jTableSourceCode.getColumnModel().getColumn(3).setPreferredWidth(55);
    jTableSourceCode.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    jTableSourceCode.getColumnModel().getColumn(4).setPreferredWidth(75);
    jTableSourceCode.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    jTableSourceCode.getColumnModel().getColumn(5).setPreferredWidth(100);
    jTableSourceCode.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);    
    jTableSourceCode.getColumnModel().getColumn(6).setPreferredWidth(400);
    
    
    jTableSourceCode.setRowHeight(22);
    jTableSourceCode.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    
    jTableSourceCode.setFont(new Font("Arial", Font.BOLD, 12));
    
    jTableSourceCode.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
  }
  
  private void createTable() {
    
    initColumns();
    int numberOfRows = steuerung.getParser().getNumberOfLines();
    int numberOfColumns = columnHeaders.length;
    initRows(numberOfRows, numberOfColumns);
    
    jTableSourceCode = new JTable(new MyTableModel(tableData, columnHeaders)) {
      
      public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {  
        
        Component cell = super.prepareRenderer(renderer, row, column);  
        String selectedCellValue = getValueAt(row, column).toString();  
        
        if( column != 0 ) {
          
          JLabel cellLabel = (JLabel)cell;  
          boolean showRow = (Boolean)jTableSourceCode.getModel().getValueAt(row, 0); 
          
          if( showRow == false ) {  
            
            cellLabel.setBackground( Color.WHITE );
            
          }  
          
          else {  
            
            cellLabel.setBackground( Color.YELLOW );  
          }  
          
          repaint();
          return cellLabel;  
        }  
        
        return cell;  
      }  
      
    };
    
    layoutTable();
    
  }
  
  private void jMenuItemFileOpenActionPerformed(ActionEvent evt) {                                                  
    
    final JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".lst", "lst");
    chooser.setFileFilter(filter);
    chooser.setAcceptAllFileFilterUsed(false); 
    chooser.setDialogType(JFileChooser.OPEN_DIALOG); 
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    
    final File file = new File("/"); 
    
    chooser.setCurrentDirectory(file); 
    
    chooser.addPropertyChangeListener(new PropertyChangeListener() { ;
      public void propertyChange(PropertyChangeEvent e) { 
        if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) 
        || e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) { 
          final File f = (File) e.getNewValue(); 
        } 
      } 
    }); 
    
    chooser.setVisible(true); 
    final int result = chooser.showOpenDialog(null); 
    
    if (result == JFileChooser.APPROVE_OPTION) { 
      
      File inputVerzFile = chooser.getSelectedFile();
      
      try {
        
        steuerung.getParser().parse(inputVerzFile);
        createTable(); 
        
      } catch(Exception e) {
        
        JOptionPane.showMessageDialog(this, "Fehler beim Parsen der lst-Datei!\n" + e, "Fehler", JOptionPane.ERROR_MESSAGE);
        
      } finally {
        
        
        
      } 
      
      
    } else {
      
    } 
    
    chooser.setVisible(false);   
    
  }
  
  private void jMenuItemInfoViewActionPerformed(ActionEvent evt) {                                                  
    
    JOptionPane.showMessageDialog(this, "Name:                    " + this.getTitle() + "\nMotivation:            Der Simulator ist ein Testat für die Vorlesung Rechnerarchitektur an der DHBW Karlsruhe\nBeschreibung:     Dieser Simulator liest .lst-Dateien (in Form von .txt-Dateien) ein und simmluiert den darin enthalten Assemblercode\nAutoren:                Christopher Heß und Alexander Burkhardt (TINF12B5)", "Info", JOptionPane.INFORMATION_MESSAGE); 
    
  }
  
  private void jMenuItemHelpViewActionPerformed(ActionEvent evt) {                                                  
    
    try {
      
      File pdfFile = new File("Hilfe.pdf"); 
      if (pdfFile.exists()) {
        
        if (Desktop.isDesktopSupported()) {
          
          Desktop.getDesktop().open(pdfFile);
          
        }
        
      } else {
        
        JOptionPane.showMessageDialog(this, "Die Datei Hilfe.pdf ist nicht vorhanden!", "Fehler", JOptionPane.ERROR_MESSAGE);
        
      }
      
    } catch (Exception ex) {
      
      ex.printStackTrace();
      
    }
  }
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
      
      return getValueAt(0, c).getClass();
      
    }
    
    public boolean isCellEditable(int row, int col) {
      
      if (col == 0 && data[row][col + 1] != "") {
        
        return true ;  
        
      } else {
        
        return false;
        
      }
    }
    /*
    * Don't need to implement this method unless your table's
    * data can change.
    */
    public void setValueAt(Object value, int row, int col) {
      
      data[row][col] = value;
      fireTableCellUpdated(row, col);
      
    }
  }
}
