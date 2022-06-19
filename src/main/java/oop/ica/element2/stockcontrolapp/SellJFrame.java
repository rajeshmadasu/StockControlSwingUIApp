package oop.ica.element2.stockcontrolapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static oop.ica.element2.stockcontrolapp.StockControlAppFrame.ASC_STOCK_FILE_PATH;
import static oop.ica.element2.stockcontrolapp.StockControlAppFrame.MSM_STOCK_FILE_PATH;
import oop.ica.element2.stockcontrolapp.adapter.ASMStock;
import oop.ica.element2.stockcontrolapp.adapter.Stock;
import oop.ica.element2.stockcontrolapp.models.OrderTabelModel;
import oop.ica.element2.stockcontrolapp.utils.CSVUtil;

/**
 * This JFrame to sell the stock with entered quantity
 *
 * @author Priyanka
 */
public class SellJFrame extends JFrame implements ActionListener {

    private JLabel l1, codeLabel, quantityLabel;
    private JTextField codeTextField, quantityTextField;
    private JButton sellBtn, cancelBtn;

    private Stock stock;
    private OrderTabelModel orderTabelModel;
    private int selectedRow;

    SellJFrame() {

    }

    public SellJFrame(Stock stock, OrderTabelModel orderTabelModel, int selectedRow) {

        this.stock = stock;
        this.orderTabelModel = orderTabelModel;
        this.selectedRow = selectedRow;
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setTitle("Sell Stock");

        codeLabel = new JLabel("Code");
        quantityLabel = new JLabel("Quantity To Sell");

        codeTextField = new JTextField();
        codeTextField.setText(stock.getCode());
        codeTextField.setEditable(false);
        quantityTextField = new JTextField();

        sellBtn = new JButton("Sell");
        cancelBtn = new JButton("Cancel");
        sellBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        codeLabel.setBounds(80, 70, 200, 30);
        quantityLabel.setBounds(80, 110, 200, 30);

        codeTextField.setBounds(300, 70, 200, 30);
        quantityTextField.setBounds(300, 110, 200, 30);

        sellBtn.setBounds(200, 170, 100, 30);
        cancelBtn.setBounds(320, 170, 100, 30);

        add(codeLabel);
        add(codeTextField);
        add(quantityLabel);
        add(quantityTextField);

        add(sellBtn);
        add(cancelBtn);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sellBtn) {

            String quantity = quantityTextField.getText();

            if (quantity.length() > 0) {
                if (Integer.parseInt(quantity) <= 0) {
                    JOptionPane.showMessageDialog(sellBtn, "quantity should be more than zero");
                } else {
                    try {
                        // reducing the no. of quantity, updating into stock model 
                        //and writing the data into CSV file 

                        int updatedQuantity = stock.getQuantity() - Integer.parseInt(quantity);
                        stock.setQuantity(updatedQuantity);
                        System.out.println("after update" + stock.toString());
                        String dataeAndTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
                        System.out.println("dataeAndTime ---" + dataeAndTime);
                        try {
                            
                            if (stock instanceof ASMStock) {
                                                         System.out.println("product code"+stock.getCode());

                                int productRow= StockControlAppFrame.getASCProductIndex(stock.getCode());
                             System.out.println("productrow in asm"+productRow);
                                  new CSVUtil().updateCSV(ASC_STOCK_FILE_PATH, String.valueOf(updatedQuantity), productRow, 5);
                            } else {
                                  int productRow = StockControlAppFrame.getMSMProductIndex(stock.getCode());
                                   System.out.println("productrow in msm"+productRow);

                                  new CSVUtil().updateCSV(MSM_STOCK_FILE_PATH, String.valueOf(updatedQuantity), productRow, 5);
                            }
                        } catch (Exception ex) {
                            System.out.println("Exception in selling the stock" + ex.getMessage());
                            Logger.getLogger(StockControlAppFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        new CSVUtil().addRecordsToSalesCSV("sales.csv", dataeAndTime, stock.getCode(), Integer.parseInt(quantity), Integer.parseInt(stock.getUnitPriceInPounds()));
                        orderTabelModel.update(stock);

                        JOptionPane.showMessageDialog(sellBtn, "Data Saved Successfully");
                        this.dispose();

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(sellBtn, "Quantity field should not be empty");
            }
        } else {

            //Terminates the JFrame 
            this.dispose();
        }
    }

}
