package oop.ica.element2.stockcontrolapp.models;

/**
 *
 * @author Priyanka
 */
public class ASCStockItem {

	
	//private String productId;
	private String code;
	private String title;
	private String description;
	private String unitPriceInPounds;
	private String unitPriceInPence;
	private int quantity;
	
	public ASCStockItem(String code, String title, String description, String unitPriceInPounds,
			String unitPriceInPence, int quantity) {
		super();
		this.code = code;
		this.title = title;
		this.description = description;
		this.unitPriceInPounds = unitPriceInPounds;
		this.unitPriceInPence = unitPriceInPence;
		this.quantity = quantity;
	}
	
	/*
	 * public String getProductId() { return productId; }
	 * 
	 * public void setProductId(String productId) { this.productId = productId; }
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitPriceInPounds() {
		return unitPriceInPounds;
	}

	public void setUnitPriceInPounds(String unitPriceInPounds) {
		this.unitPriceInPounds = unitPriceInPounds;
	}

	public String getUnitPriceInPence() {
		return unitPriceInPence;
	}

	public void setUnitPriceInPence(String unitPriceInPence) {
		this.unitPriceInPence = unitPriceInPence;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	@Override
	public String toString() {
		return "Product [ code=" + code + ", title=" + title + ", description="
				+ description + ", unitPriceInPounds=" + unitPriceInPounds + ", unitPriceInPence=" + unitPriceInPence
				+ ", quantity=" + quantity + "]";
	}


	


}
