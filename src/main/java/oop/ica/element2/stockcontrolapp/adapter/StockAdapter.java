package oop.ica.element2.stockcontrolapp.adapter;

/**
 *
 * @author Priyanka
 */
public class StockAdapter implements Stock {

	@Override
	public String toString() {
		return "StockAdapter [msmStockItem=" + msmStockItem + "]"+"\n"+
	   "[Code :"+getCode()+"\t Title:"+getTitle()+"\t Description:"+getDescription()
	   +"\t UnitPriceInPounds"+getUnitPriceInPounds()+"\t UnitPriceInPences"+getUnitPriceInPences()
	   +"\t Quantity"+getQuantity();
	}

	MSMStockItemImplementation msmStockItem;
	
	public StockAdapter(MSMStockItemImplementation msmStockItem) {
		this.msmStockItem=msmStockItem;
	}
	@Override
	public String getCode() {
		return msmStockItem.getCode();
	}

	@Override
	public String getTitle() {
		String title = null ;
		try {
			
			//System.out.println("title length"+msmStockItem.getTitleAndDescription().length());
                if(msmStockItem.getTitleAndDescription().length()>60) {
		title =  (msmStockItem.getTitleAndDescription().substring(0, 60)).trim();
		}
		}catch (Exception e) {
			System.out.println("error in gettting title"+e.getMessage()+"\n"+title);
		}
		
		return title ;
	}

	@Override
	public String getDescription() {
		String description = null ;
		try {
			
		if(msmStockItem.getTitleAndDescription().length()>60) {
		description =  (msmStockItem.getTitleAndDescription().substring(61, msmStockItem.getTitleAndDescription().length())).trim();
		}
		}catch (Exception e) {
			System.out.println("error"+e.getMessage()+"\n"+description);
		}
		return description;
	}

	@Override
	public String getUnitPriceInPounds() {
		
		float unitPrice =(Float.parseFloat(msmStockItem.getUnitPriceInPences())/100);
		int number = (int)unitPrice;
		return String.valueOf((number));
	}

	@Override
	public String getUnitPriceInPences() {
		 String unitPrice = null;
		try {
		float number  =(Float.parseFloat(msmStockItem.getUnitPriceInPences())/100); 
               unitPrice = String.valueOf(number);
               unitPrice = unitPrice.substring(unitPrice.indexOf(".")).substring(1);
             //System.out.println("unit price pences:"+unitPrice);
		}catch (Exception e) {
		e.printStackTrace();
		}
        return unitPrice;
	}

	@Override
	public int getQuantity() {
		return msmStockItem.getQuantity();
	}
	@Override
	public void setQuantity(int quantity) {
		
		msmStockItem.setQuantity(quantity);
	}
	

}
