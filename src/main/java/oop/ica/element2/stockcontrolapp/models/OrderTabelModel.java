package oop.ica.element2.stockcontrolapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import oop.ica.element2.stockcontrolapp.adapter.Stock;

/**
 *
 * @author Priyanka
 */
public class OrderTabelModel extends AbstractTableModel {

    
	private static final long serialVersionUID = 12364L;
	protected static final String COLUMN_NAMES[] = {"Code", "Title", "Description", "Unit Price(Pounds)", "Unit Price(Pence)","Quanity"};
    protected static final Class COLUMN_TYPES[] = { String.class, String.class, String.class, int.class, int.class,int.class};

    private final List<Stock> orders;

    public OrderTabelModel() {
        orders = new ArrayList<>();
    }

    
    public void refresh(){
        fireTableStructureChanged();
    }
    public void add(Stock order) {
        orders.add(order);
        fireTableRowsInserted(orders.size() - 1, orders.size() - 1);
    }

    public void remove(Stock order) {
        int row = orders.indexOf(order);
        if (row >= 0) {
            orders.remove(order);
            fireTableRowsDeleted(row, row);
        }
    }

    public void update(Stock order) {
        int row = orders.indexOf(order);
        if (row >= 0) {
            fireTableRowsUpdated(row, row);
        }
    }

    public Stock getOrderAt(int row) {
        return orders.get(row);
    }

 
    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Stock order = getOrderAt(rowIndex);
        switch (columnIndex) {
      
            case 0:
                value = order.getCode();
                break;
            case 1:
                value = order.getTitle();
                break;
            case 2:
                value = order.getDescription();
                break;
            case 3:
                value = order.getUnitPriceInPounds();
                break;
            case 4:
                value = order.getUnitPriceInPences();
                break;
            case 5:
                value = order.getQuantity();
                break;
        }
        return value;
    }

}