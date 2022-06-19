package oop.ica.element2.stockcontrolapp.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Priyanka
 */
// Method for reading,writing and updating CSV file
public class CSVUtil {

    private String[] OneRow;

    public ArrayList<String[]> readCSVfile(File DataFile) {
        
        ArrayList<String[]> data = new ArrayList<>();

        try {
            try ( BufferedReader brd = new BufferedReader(new FileReader(DataFile))) {
                while (brd.ready()) {
                    String st = brd.readLine();
                    OneRow = st.split(",");
                    data.add(OneRow);

                } // end of while
            }
        } // end of try
        catch (IOException e) {
            String errmsg = e.getMessage();
            System.out.println("File not found:" + errmsg);
        }catch(Exception e){
              String errmsg = e.getMessage();
            System.out.println("Error:" + errmsg);
        }// end of Catch
        return data;
    }// end of ReadFile method

    
   

    public void addStockToCSV(String fileName, String productCode, String title, String description, String unitPrice,String unitPriceInPences,String quantity) {
        CSVWriter writer = null;
      
        try {
            File file = new File(fileName);
           
            String[] record = new String[]{productCode, title, description,unitPrice,unitPriceInPences,quantity};

            if (file.exists()) {
                  writer = new CSVWriter(new FileWriter(fileName, true));

                writer.writeNext(record);
            }
            

            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CSVUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addRecordsToSalesCSV(String fileName, String dateAndTime, String productCode, int quantitySold, int unitPrice) {
        CSVWriter writer;
        final String COLUMN_NAMES[] = {"Date and Time", "Product Code", "Quantity Sold", "Unit Price"};

        try {
            File file = new File(fileName);
            String[] record = new String[]{dateAndTime, productCode, String.valueOf(quantitySold), String.valueOf(unitPrice)};

            if (file.exists()) {
                  writer = new CSVWriter(new FileWriter(fileName, true));

                writer.writeNext(record);
            } else {
                  writer = new CSVWriter(new FileWriter(fileName));
                 writer.writeNext(COLUMN_NAMES);
                  writer.writeNext(record);
            }

            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CSVUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Update CSV by row and column
     *
     * @param fileToUpdate CSV file path to update e.g.
     * @param replace Replacement for your cell value
     * @param row Row for which need to update
     * @param col Column for which you need to update
     * @throws IOException
     */
    public void updateCSV(String fileToUpdate, String replace,
            int row, int col) throws IOException {

        File inputFile = new File(fileToUpdate);
        System.out.println("fileToUpdate" + fileToUpdate);

// Read existing file 
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();
// get CSV row column  and replace with by using row and column
        csvBody.get(row)[col] = replace;
        reader.close();

// Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }
}// end of CSVFile class
