package oop.ica.element2.stockcontrolapp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import oop.ica.element2.stockcontrolapp.adapter.ASMStock;
import oop.ica.element2.stockcontrolapp.adapter.MSMStockItemImplementation;
import oop.ica.element2.stockcontrolapp.adapter.Stock;
import oop.ica.element2.stockcontrolapp.adapter.StockAdapter;
import oop.ica.element2.stockcontrolapp.models.OrderTabelModel;
import oop.ica.element2.stockcontrolapp.utils.CSVUtil;

/**
 *
 * @author Priyanka
 */
public class StockControlAppFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final ArrayList<Stock> msmStockList = new ArrayList<>();
    public static final ArrayList<Stock> asmStockList = new ArrayList<>();

    private final JTable jTable1;
    private int selectedRow = -1;
    public static final String ASC_STOCK_FILE_PATH = "files\\ASCStock.csv";
    public static final String MSM_STOCK_FILE_PATH = "files\\MSMStock.csv";

    @SuppressWarnings("serial")
    public StockControlAppFrame() {
        System.out.println("=============Stock Control with low stock reporting=================");

        setTitle("Stock Control Application");
        setSize(500, 500);
        //exit the program after window is closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        // Panel Code
        JPanel jPanel = new JPanel();
        BoxLayout verticalLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(verticalLayout);
        add(jPanel);

        // Showing Csv Data in Table
        // Label
        JLabel csv_data_label = new JLabel("Stock Control with low stock reporting");
        csv_data_label.setSize(350, 100);

        csv_data_label.setAlignmentX(JLabel.CENTER);
        csv_data_label.setForeground(Color.BLACK);
        csv_data_label.setOpaque(true);
        EmptyBorder emptyBorder3 = new EmptyBorder(20, 20, 20, 20);
        csv_data_label.setBorder(emptyBorder3);
        jPanel.add(csv_data_label, BorderLayout.BEFORE_LINE_BEGINS);

        OrderTabelModel csv_data = new OrderTabelModel();
        try {

            // Parsing CSV Data
            ArrayList<String[]> dataRow = new CSVUtil().readCSVfile(new File(ASC_STOCK_FILE_PATH));
            ArrayList<String[]> msmDataRows = new CSVUtil().readCSVfile(new File(MSM_STOCK_FILE_PATH));

            // adding records to Table Model to display in JTable
            addRecordsToTableModel(csv_data, dataRow, false);
            addRecordsToTableModel(csv_data, msmDataRows, true);

        } catch (NumberFormatException e) {

            System.out.println("Error in Parsing CSV File" + e.getMessage());
        }

        jTable1 = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        jTable1.setModel(csv_data);
        jTable1.setRowSelectionAllowed(true);

        JScrollPane jScrollPane2 = new JScrollPane();
        jScrollPane2.getViewport().add(jTable1);

        jPanel.add(jScrollPane2);
        jTable1.getSelectionModel().addListSelectionListener(new RowSelectionListener());

        //      Font Object
        Font font = new Font("serif", Font.BOLD, 16);

        // Buy button
        JButton buyButton = new JButton("Buy");

        // buyButton.setFocusPainted(false);
        buyButton.setFont(font);
        buyButton.addActionListener((ActionEvent actionEvent) -> {
            System.out.println("selected row" + selectedRow);
            BuyJFrame buyJFrame;
            if(selectedRow == -1){
              buyJFrame = new BuyJFrame(csv_data,null);
            }else{
            Stock product = csv_data.getOrderAt(selectedRow);
             buyJFrame = new BuyJFrame(csv_data,product);
            }
        });

        // Refresh button
        JButton refreshButton = new JButton("Refresh");

        // buyButton.setFocusPainted(false);
        refreshButton.setFont(font);
        refreshButton.addActionListener((ActionEvent actionEvent) -> {

            csv_data.refresh();

        });

        // Sell button
        JButton sellButton = new JButton("Sell");
        // sellButton.setFocusPainted(false);
        sellButton.setFont(font);
        sellButton.addActionListener((ActionEvent actionEvent) -> {
            System.out.println("selected row" + selectedRow);

            if (selectedRow != -1) {
                Stock product = csv_data.getOrderAt(selectedRow);

                System.out.println(product.toString());

                if (product.getQuantity() <= 2) {
                    JOptionPane.showMessageDialog(StockControlAppFrame.this, product.getCode() + " Quantity Left:" + product.getQuantity(), "Low Stock Alert", JOptionPane.WARNING_MESSAGE);
                    System.out.println("Low Stock Alert:" + product.getCode() + " Quantity Left:" + product.getQuantity());
                }
                if (product.getQuantity() >= 1) {
                    SellJFrame sellJFrame = new SellJFrame(product, csv_data, selectedRow);

                } else {
                    JOptionPane.showMessageDialog(StockControlAppFrame.this, product.getCode() + " Quantity Left:" + product.getQuantity(), "Low Stock Alert", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Low Stock Alert:" + product.getCode() + " Quantity Left:" + product.getQuantity());

                }

            }else{
              JOptionPane.showMessageDialog(StockControlAppFrame.this, "Please select row", "Info", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        JPanel pane = new JPanel();
        pane.setBorder(emptyBorder3);
        pane.setLayout(new FlowLayout(FlowLayout.TRAILING));
        // below refresh button adding to Jpanel
        //pane.add(refreshButton, BorderLayout.LINE_END);
        pane.add(buyButton, BorderLayout.LINE_END);
        pane.add(sellButton, BorderLayout.LINE_END);
        add(pane, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public static int getMSMProductIndex(String productCode) {
        int i = 0;
        for (Stock stock : msmStockList) {
          
            if (stock.getCode().equalsIgnoreCase(productCode)) {
                  
                return i;
            }
               i++;
        }

        
        
        return -1;
    }
       
    public static int getASCProductIndex(String productCode) {
            int i = 0;
            
     //  ArrayList<String[]> dataRow = new CSVUtil().readCSVfile(new File(ASC_STOCK_FILE_PATH));

     
         for (Stock stock : asmStockList) {
             i++;
            if (stock.getCode().equalsIgnoreCase(productCode)) {
                
                return i;
            }
        }

        return -1;
    }
    public static void addRecordsToTableModel(OrderTabelModel csv_data, ArrayList<String[]> dataRow, boolean isMSMData) {
        int start = 0;
        for (String[] csvRecord : dataRow) {
            if (start == 0) {
                start = 1;
            } else {
                Stock stockItem;
                if (isMSMData) {

                    MSMStockItemImplementation mStockItemImplementation = new MSMStockItemImplementation(csvRecord[2].replaceAll("\"", ""), csvRecord[3].replaceAll("\"", ""), csvRecord[4].replaceAll("\"", ""),
                            Integer.parseInt(csvRecord[5].replaceAll("\"", "")));

                    stockItem = new StockAdapter(mStockItemImplementation);
                    msmStockList.add(stockItem);

                } else {

                    // creating ASMStock item object 
                    stockItem = new ASMStock(csvRecord[0].replaceAll("\"", ""), csvRecord[1].replaceAll("\"", ""), csvRecord[2].replaceAll("\"", ""), csvRecord[3].replaceAll("\"", ""), csvRecord[4].replaceAll("\"", ""),
                            Integer.parseInt(csvRecord[5].replaceAll("\"", "")));
                    asmStockList.add(stockItem);
                }
     //   System.out.println("asm stock list size"+asmStockList.size());
                csv_data.add(stockItem);
            }

        }
    }

    public class RowSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {

            int viewRow = jTable1.getSelectedRow();
            //getValueIsAdjusting -Returns whether or not this is one in a series of multiple events,
            // where changes are still being made
            if (!event.getValueIsAdjusting() && viewRow != -1) {
                selectedRow = viewRow;

            }

        }

    }

}
