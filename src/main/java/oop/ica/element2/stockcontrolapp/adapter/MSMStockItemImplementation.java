package oop.ica.element2.stockcontrolapp.adapter;

/**
 *
 * @author Priyanka
 */
public class MSMStockItemImplementation implements MSMStockItem {

private String code;
@Override
public String toString() {
	return "MSMStockItem [code=" + code + ", titleAndDescription=" + titleAndDescription + ", unitPriceInPences="
			+ unitPriceInPences + ", quantity=" + quantity + "]";
}

private	String titleAndDescription;
private	String unitPriceInPences;
private	int quantity;
	
	public MSMStockItemImplementation(String code,String titleAndDescription,String unitPriceInPences,int quantity) {
		this.code=code;
		this.titleAndDescription=titleAndDescription;
		this.unitPriceInPences=unitPriceInPences;
		this.quantity=quantity;
	}
	
	@Override
	public String getCode() {
		return code;
	}
	@Override
	public String getTitleAndDescription() {
		return this.titleAndDescription;
	}

	@Override
	public String getUnitPriceInPences() {
		return unitPriceInPences;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity=quantity;
		
	}

	

}
