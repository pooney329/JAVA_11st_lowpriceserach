package lowpriceserach;

public class OpenApi11stDTO {
	
	private int price;
	private String delivery;
	private String url;
	private String mallname;
	
	
	
	public OpenApi11stDTO(int price, String delivery, String url,String mallname) {
		super();
		this.price = price;
		this.delivery = delivery;
		this.url = url;
		this.mallname = mallname;
	}
	
	public String getMallname() {
		return mallname;
	}

	public void setMallname(String mallname) {
		this.mallname = mallname;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	

}
