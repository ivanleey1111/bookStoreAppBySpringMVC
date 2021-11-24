package beans;

public class BookInCartBean {
	private String bid;
	private String title;
	private int price;
	private String category;
	private String img;
	private int itemcount;
	
	public BookInCartBean() {
		// TODO Auto-generated constructor stub
	}
	
	public BookInCartBean(String bid, String title, int price, String category, String img, int itemcount) {
		this.bid = bid;
		this.title = title;
		this.price = price;
		this.category = category;
		this.img = img;
		this.itemcount = itemcount;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getItemcount() {
		return itemcount;
	}

	public void setItemcount(int itemcount) {
		this.itemcount = itemcount;
	}
	
	

}
