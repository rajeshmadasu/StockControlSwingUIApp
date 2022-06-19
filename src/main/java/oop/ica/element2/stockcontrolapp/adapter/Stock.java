package oop.ica.element2.stockcontrolapp.adapter;

/**
 *
 * @author Priyanka
 */
public interface Stock {

	public String getCode();
	public String getTitle();
	public String getDescription();
	public String getUnitPriceInPounds();
	public String getUnitPriceInPences();
	public int getQuantity();
	public void setQuantity(int i);
}
