package oop.ica.element2.stockcontrolapp.adapter;

/**
 *
 * @author Priyanka
 */
public class ASMStock implements Stock {

	private String code;
	@Override
	public String toString() {
		return "ASMStock [code=" + code + ", title=" + title + ", description=" + description + ", unitPriceInPounds="
				+ unitPriceInPounds + ", unitPriceInPences=" + unitPriceInPences + ", quantity=" + quantity + "]";
	}

	private String title;
	private String description;
	private String unitPriceInPounds;
	private String unitPriceInPences;
	private int quantity;
	
	public ASMStock(String code, String title,String description, String unitPriceInPounds, String unitPriceInPences,int quantity) {
			this.code=code;
			this.title=title;
			this.description= description;
			this.unitPriceInPounds=unitPriceInPounds;
			this.unitPriceInPences=unitPriceInPences;
			this.quantity= quantity;
	}
	
	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getUnitPriceInPounds() {
		return this.unitPriceInPounds;
	}

	@Override
	public String getUnitPriceInPences() {
		return this.unitPriceInPences;
	}

	@Override
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity=quantity;		
	}



}
