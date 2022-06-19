package oop.ica.element2.stockcontrolapp;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static oop.ica.element2.stockcontrolapp.StockControlAppFrame.ASC_STOCK_FILE_PATH;
import static oop.ica.element2.stockcontrolapp.StockControlAppFrame.asmStockList;
import oop.ica.element2.stockcontrolapp.adapter.ASMStock;
import oop.ica.element2.stockcontrolapp.adapter.Stock;
import oop.ica.element2.stockcontrolapp.models.OrderTabelModel;
import oop.ica.element2.stockcontrolapp.utils.CSVUtil;

/**
 * 
 * @author Priyanka
 */
public class BuyJFrame extends JFrame implements ActionListener   
{  
   private JLabel  codeLabel, titleLabel, descriptionLabel, unitPriceLabel,unitPriceInPencesLabel, quantityLabel;  
   private JTextField codeTextField, titleTextField, descriptionTextField, unitPriceTextField,unitPriceInPencesTextField, quantityTextField;  
   private JButton buyBtn, cancelBtn;  
    private OrderTabelModel orderTabelModel;

    private Stock stock=null;
    public BuyJFrame() {
    }

   public BuyJFrame(OrderTabelModel orderTabelModel,Stock stock)  
    {  
        
        if(stock!=null){
        this.stock = stock;
        }
        this.orderTabelModel=orderTabelModel;
        setVisible(true);  
        setSize(700, 700);  
        setLayout(null);  
        setTitle("Buy Stock");  
       
        codeLabel = new JLabel("Code:");  
        titleLabel = new JLabel("Title:");  
        descriptionLabel = new JLabel("Description:");  
        unitPriceLabel = new JLabel("Unit Price In Pounds:");  
        unitPriceInPencesLabel = new JLabel("Unit Price in Pences:");  

        quantityLabel = new JLabel("Quantity:");  
    
        codeTextField = new JTextField();  
        titleTextField = new JTextField();  
        descriptionTextField = new JTextField();  
        unitPriceTextField = new JTextField();  
       unitPriceInPencesTextField = new JTextField();  

        quantityTextField = new JTextField();  
        
        if(stock!=null){
            // when any stock is selected from JTable
            codeTextField.setText(stock.getCode());
            titleTextField.setText(stock.getTitle());
             descriptionTextField.setText(stock.getDescription());
             unitPriceTextField.setText(stock.getUnitPriceInPounds());
             unitPriceInPencesTextField.setText(stock.getUnitPriceInPences());
             quantityTextField.setText(String.valueOf(stock.getQuantity()));
             
            codeTextField.setEditable(false);
            titleTextField.setEditable(false);
            descriptionTextField.setEditable(false);
            unitPriceTextField.setEditable(false);
            unitPriceInPencesTextField.setEditable(false);
            quantityTextField.setEditable(true);

        }else{
         // when no stock is selected from JTable
            codeTextField.setEditable(true);
            titleTextField.setEditable(true);
            descriptionTextField.setEditable(true);
            unitPriceTextField.setEditable(true);
            unitPriceInPencesTextField.setEditable(true);
            quantityTextField.setEditable(true);
             titleTextField.setText("");
             codeTextField.setText("");
             descriptionTextField.setText("");
               unitPriceTextField.setText("");
                unitPriceInPencesTextField.setText("");
            quantityTextField.setText("");
        }
   
         
        buyBtn = new JButton("Buy");  
        cancelBtn = new JButton("Cancel");  
        buyBtn.addActionListener(this);  
        cancelBtn.addActionListener(this);  
        codeLabel.setBounds(80, 70, 200, 30);  
        titleLabel.setBounds(80, 110, 200, 30);  
        descriptionLabel.setBounds(80, 150, 200, 30);  
        
        unitPriceLabel.setBounds(80, 190, 200, 30); 
        
        unitPriceInPencesLabel.setBounds(80, 230, 200, 30);  
        quantityLabel.setBounds(80, 260, 200, 30);  
     
        codeTextField.setBounds(300, 70, 200, 30);  
        titleTextField.setBounds(300, 110, 200, 30);  
        descriptionTextField.setBounds(300, 150, 200, 30);  
        unitPriceTextField.setBounds(300, 190, 200, 30);  
        unitPriceInPencesTextField.setBounds(300, 230, 200, 30);  
        quantityTextField.setBounds(300, 270, 200, 30);  
        buyBtn.setBounds(200, 310, 100, 30);  
        cancelBtn.setBounds(320, 310, 100, 30);  
        add(codeLabel);  
        add(codeTextField);  
        add(titleLabel);  
        add(titleTextField);  
        add(descriptionLabel);  
        add(descriptionTextField);  
        add(unitPriceLabel);  
        add(unitPriceTextField);  
        add(unitPriceInPencesLabel);
        add(unitPriceInPencesTextField);
        add(quantityLabel);  
        add(quantityTextField);  
       
        add(buyBtn);  
        add(cancelBtn);  
    }  
    public void actionPerformed(ActionEvent e)   
    {  
        if (e.getSource() == buyBtn)  
         {  
     
            String code = codeTextField.getText();  
            String title = titleTextField.getText();  
            String description = descriptionTextField.getText();  
            String unitPrice = unitPriceTextField.getText();  
            String unitPriceInPences = unitPriceInPencesTextField.getText();
            String quantity = quantityTextField.getText().trim();  
           
            if (code.length()>0 && title.length()>0&& description.length()>0&&
                    unitPrice.length()>0 && quantity.length()>0 && unitPriceInPences.length()>0)  
            {  
                try  
                {  
     
                  
                    if(stock!=null){
                       int updateQuantity= stock.getQuantity()+Integer.parseInt(quantity);
                       stock.setQuantity(updateQuantity);
                      
                      
                       Stock stockItem = new ASMStock(code.replaceAll("\"", ""), title.replaceAll("\"", ""), description.replaceAll("\"", ""), unitPrice.replaceAll("\"", ""),  unitPriceInPences.replaceAll("\"", ""),
							updateQuantity);
               
                                int productRow= StockControlAppFrame.getASCProductIndex(stock.getCode());
                             System.out.println("productrow in asm"+productRow);
                                  new CSVUtil().updateCSV(ASC_STOCK_FILE_PATH, String.valueOf(updateQuantity), productRow, 5);
                                      orderTabelModel.update(stock);

                    }else{
                     new CSVUtil().addStockToCSV(ASC_STOCK_FILE_PATH, code, title, description, unitPrice,unitPriceInPences, quantity);
                          Stock stockItem = new ASMStock(code.replaceAll("\"", ""), title.replaceAll("\"", ""), description.replaceAll("\"", ""), unitPrice.replaceAll("\"", ""),  unitPriceInPences.replaceAll("\"", ""),
							Integer.parseInt(quantity.replaceAll("\"", "")));
               
                     asmStockList.add(stockItem);
                         // adding row to JTable
                     orderTabelModel.add(stockItem);
                    }
                 
                    JOptionPane.showMessageDialog(buyBtn, "Data Saved Successfully");  
                    
                    //closing the JFrame
                    this.dispose();
                }  
                catch (NumberFormatException ex)   
                {  
                    System.out.println(ex);  
                } catch (Exception ex)   
                {  
                    System.out.println(ex);  
                }  
            }  
            else  
            {  
                JOptionPane.showMessageDialog(buyBtn, "Fields should not be empty");  
            }   
          }   
          else  
          {  
          //Terminates the JFrame 
              this.dispose();
          }  
    }   

}