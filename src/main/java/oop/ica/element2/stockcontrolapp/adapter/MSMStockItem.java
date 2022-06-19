package oop.ica.element2.stockcontrolapp.adapter;

/**
 *
 * @author Priyanka
 */
public interface MSMStockItem {
	public String getCode();
	public String getTitleAndDescription();
	public String getUnitPriceInPences();
	public int getQuantity();
	public void setQuantity(int quantity);
}
