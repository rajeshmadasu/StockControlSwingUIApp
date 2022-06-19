package oop.ica.element2.stockcontrolapp.test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import oop.ica.element2.stockcontrolapp.BuyJFrame;
import oop.ica.element2.stockcontrolapp.SellJFrame;
import oop.ica.element2.stockcontrolapp.StockControlAppFrame;
import static oop.ica.element2.stockcontrolapp.StockControlAppFrame.ASC_STOCK_FILE_PATH;
import oop.ica.element2.stockcontrolapp.adapter.ASMStock;
import oop.ica.element2.stockcontrolapp.adapter.Stock;
import oop.ica.element2.stockcontrolapp.models.OrderTabelModel;
import oop.ica.element2.stockcontrolapp.utils.CSVUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Priyanka
 */
public class StockControlAppUnitTest {

    private StockControlAppFrame stockControlAppFrame;

    public StockControlAppUnitTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

    }
    
    
    private void waitFor2Seconds(){
         try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StockControlAppUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * It will show the Stock control app JFrame
     */

    @Test
    public void showStockControlApp() {
          waitFor2Seconds();
        // Create a frame with the test instance name as the title
        stockControlAppFrame = new StockControlAppFrame();
        stockControlAppFrame.setVisible(true);
        assertTrue(stockControlAppFrame.isVisible());
        assertTrue(stockControlAppFrame.getTitle().equals("Stock Control Application"));
      
    }

    @AfterEach
    public void tearDown() {
        // if (this.testFrame != null) {
        //   this.testFrame.dispose(  );
        //   this.testFrame = null;
        //}
    }

    public JFrame getStockControlAppFrame() {
        if (this.stockControlAppFrame == null) {
            this.stockControlAppFrame = new StockControlAppFrame();
        }
        return this.stockControlAppFrame;
    }

 /*   @Test
    public void doRefreshBtnClickOnMainJFrame() {
        Container container = (Container) getTestFrame().getContentPane().getComponent(1);

        JButton button = (JButton) container.getComponent(0);

        assertTrue(button.getText().equals("Refresh"));
        button.doClick();
    }
*/
    
     
    /**
     * displaying the Buy JFrame and checking is it visible
     */
    @Test
    public void buyButtonsJFrame() {
        waitFor2Seconds();
        OrderTabelModel model = new OrderTabelModel();
        BuyJFrame buyJFrame = new BuyJFrame(model,null);

        assertTrue(buyJFrame.isVisible());

    }

    @Test
    public void buyJFrameWithValidData() {
        waitFor2Seconds();
        OrderTabelModel model = new OrderTabelModel();
        BuyJFrame buyJFrame = new BuyJFrame(model,null);
        assertTrue(buyJFrame.isVisible());

        JTextField codeTextField = (JTextField) buyJFrame.getContentPane().getComponent(1);
        JTextField titleTextField = (JTextField) buyJFrame.getContentPane().getComponent(3);
        JTextField descriptionTextField = (JTextField) buyJFrame.getContentPane().getComponent(5);
        JTextField unitPriceTextField = (JTextField) buyJFrame.getContentPane().getComponent(7);
        
        JTextField unitPriceInPencesTextField = (JTextField) buyJFrame.getContentPane().getComponent(9);

        JTextField quantityTextField = (JTextField) buyJFrame.getContentPane().getComponent(11);

        codeTextField.setText("ASM-FOOTBALL-997");
        titleTextField.setText("Ball Lockers7222");
        descriptionTextField.setText("Football Ball Lockers & Racks74");
        unitPriceTextField.setText("7");
        unitPriceInPencesTextField.setText("50");
        quantityTextField.setText("77");

        ArrayList<String[]> oldDataRows = new CSVUtil().readCSVfile(new File(ASC_STOCK_FILE_PATH));
        System.out.println("oldDataRows records size:" + oldDataRows.size());

        //getting component of Buy button
        Component component = buyJFrame.getContentPane().getComponent(12);
        assertTrue(component instanceof JButton);
        JButton buyButton = (JButton) component;
        buyButton.doClick();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StockControlAppUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<String[]> currentDataRows = new CSVUtil().readCSVfile(new File(ASC_STOCK_FILE_PATH));
        System.out.println("currentDataRows records size:" + currentDataRows.size());
        assertTrue(currentDataRows.size() > oldDataRows.size(), "A new stock will be created");

    }

    @Test
    public void buyJFrameWithInValidData() {
        waitFor2Seconds();
        OrderTabelModel model = new OrderTabelModel();
        BuyJFrame buyJFrame = new BuyJFrame(model,null);
        assertTrue(buyJFrame.isVisible());

        JTextField codeTextField = (JTextField) buyJFrame.getContentPane().getComponent(1);
        JTextField titleTextField = (JTextField) buyJFrame.getContentPane().getComponent(3);
        JTextField descriptionTextField = (JTextField) buyJFrame.getContentPane().getComponent(5);
        JTextField unitPriceTextField = (JTextField) buyJFrame.getContentPane().getComponent(7);
       JTextField unitPriceInPencesTextField = (JTextField) buyJFrame.getContentPane().getComponent(9);

        JTextField quantityTextField = (JTextField) buyJFrame.getContentPane().getComponent(11);

        codeTextField.setText("ASM-FOOTBALL-100");
        titleTextField.setText("Ball Lockers");
        descriptionTextField.setText("Football Ball Lockers & Racks");
        unitPriceTextField.setText("25");
        unitPriceInPencesTextField.setText("50");
        quantityTextField.setText(" ");

        Component component = buyJFrame.getContentPane().getComponent(12);
        assertTrue(component instanceof JButton);
        JButton buyButton = (JButton) component;
        buyButton.doClick();

    }

    @Test
    public void checkingBuyJFrameBuyButton() {
        waitFor2Seconds();
        OrderTabelModel model = new OrderTabelModel();
        BuyJFrame buyJFrame = new BuyJFrame(model,null);
        assertTrue(buyJFrame.isVisible());
        assertTrue(buyJFrame.getTitle().equals("Buy Stock"));
        Component component = buyJFrame.getContentPane().getComponent(12);
        assertTrue(component instanceof JButton);
        JButton buyButton = (JButton) component;
        assertTrue(buyButton.getText().equals("Buy"));

    }

    @Test
    public void doClickBuyJFrameCancelButton() {
        waitFor2Seconds();
        OrderTabelModel model = new OrderTabelModel();
        BuyJFrame buyJFrame = new BuyJFrame(model,null);
        Component component2 = buyJFrame.getContentPane().getComponent(13);
        assertTrue(component2 instanceof JButton);
        JButton cancelBtn = (JButton) component2;
        assertTrue(cancelBtn.getText().equals("Cancel"));
        cancelBtn.doClick();
        assertFalse(buyJFrame.isVisible());

    }

    
    /**
     * displaying the Sell JFrame and checking is it visible
     */
    @Test
    public void doSellBtnClick() {
        waitFor2Seconds();
        String code = "ASM-STOCK-001";
        String title = "Cricket Bat";
        String description = "Cricket Bat";
        String unitPriceInPounds = "20";
        String unitPriceInPences = "0";

        int quantity = 1;
        Stock aSMStock = new ASMStock(code, title, description, unitPriceInPounds, unitPriceInPences, quantity);
        OrderTabelModel model = new OrderTabelModel();
        SellJFrame sellJFrame = new SellJFrame(aSMStock, model, 1);

        assertTrue(sellJFrame.isVisible());

    }

    /*
     */
    @Test
    public void doSellStockWithValidData() {
        waitFor2Seconds();
        String code = "ASM-FOOTBALL-001";

       
        ArrayList<String[]> currentDataRows = new CSVUtil().readCSVfile(new File(ASC_STOCK_FILE_PATH));
            String [] csvRecord = currentDataRows.get(1);
                     // creating ASMStock item object 
        Stock aSMStock = new ASMStock(csvRecord[0].replaceAll("\"", ""), csvRecord[1].replaceAll("\"", ""), csvRecord[2].replaceAll("\"", ""), csvRecord[3].replaceAll("\"", ""), csvRecord[4].replaceAll("\"", ""),
                            Integer.parseInt(csvRecord[5].replaceAll("\"", "")));

        OrderTabelModel model = new OrderTabelModel();
        SellJFrame sellJFrame = new SellJFrame(aSMStock, model, 0);

        assertTrue(sellJFrame.isVisible());

        JTextField codeTextField = (JTextField) sellJFrame.getContentPane().getComponent(1);
        JTextField quantityTextField = (JTextField) sellJFrame.getContentPane().getComponent(3);

        JButton sellButton = (JButton) sellJFrame.getContentPane().getComponent(4);

        assertTrue(codeTextField.getText().equals(code), "Stock code is same");
        quantityTextField.setText("1");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StockControlAppUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        sellButton.doClick();

    }

    /**
     * It will show error pop-up to pass the test
     */
    @Test
    public void doSellStockWithInValidData() {
        waitFor2Seconds();
        String code = "ASM-FOOTBALL-001";
        String title = "Ball Lockers & Racks";
        String description = "Football Ball Lockers & Racks ";
        String unitPriceInPounds = "10";
        String unitPriceInPences = "2";

        int quantity = 6;
        Stock aSMStock = new ASMStock(code, title, description, unitPriceInPounds, unitPriceInPences, quantity);
        OrderTabelModel model = new OrderTabelModel();
        SellJFrame sellJFrame = new SellJFrame(aSMStock, model, 0);

        assertTrue(sellJFrame.isVisible());

        JTextField codeTextField = (JTextField) sellJFrame.getContentPane().getComponent(1);
        JTextField quantityTextField = (JTextField) sellJFrame.getContentPane().getComponent(3);

        JButton sellButton = (JButton) sellJFrame.getContentPane().getComponent(4);

        assertTrue(codeTextField.getText().equals(code), "Stock code is same");
        assertFalse(quantityTextField.getText().equals(quantity), "Stock quantity should not be same");
        quantityTextField.setText("1");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StockControlAppUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        sellButton.doClick();

    }

    /**
     * It will show error pop-up to pass the test
     */
    @Test
    public void doSellStockWithInValidQuantity() {
        waitFor2Seconds();
        String code = "ASM-FOOTBALL-001";
        String title = "Ball Lockers & Racks";
        String description = "Football Ball Lockers & Racks ";
        String unitPriceInPounds = "10";
        String unitPriceInPences = "2";

        int quantity = 7;
        Stock aSMStock = new ASMStock(code, title, description, unitPriceInPounds, unitPriceInPences, quantity);
        OrderTabelModel model = new OrderTabelModel();
        SellJFrame sellJFrame = new SellJFrame(aSMStock, model, 0);

        assertTrue(sellJFrame.isVisible());

        JTextField codeTextField = (JTextField) sellJFrame.getContentPane().getComponent(1);
        JTextField quantityTextField = (JTextField) sellJFrame.getContentPane().getComponent(3);

        JButton sellButton = (JButton) sellJFrame.getContentPane().getComponent(4);

        assertTrue(codeTextField.getText().equals(code), "Stock code is same");
        assertFalse(quantityTextField.getText().equals(quantity), "Stock quantity should not be same");
        quantityTextField.setText("0");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StockControlAppUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        sellButton.doClick();

    }

    public static Component getChildNamed(Component parent, String name) {
        
        if (name.equals(parent.getName())) {
            return parent;
        }
        if (parent instanceof Container) {
            Component[] childern = ((Container) parent).getComponents();
            for (int i = 0; i < childern.length; i++) {
                Component child = getChildNamed(childern[i], name);
                if (child != null) {
                    return child;
                }
            }
        }
        return null;
    }



    public static ArrayList<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        ArrayList<Component> compList = new ArrayList<>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container) {
                compList.addAll(getAllComponents((Container) comp));
            }
        }
        return compList;
    }

}
