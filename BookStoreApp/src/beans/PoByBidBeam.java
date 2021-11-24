package beans;

import javax.management.loading.PrivateClassLoader;

public class PoByBidBeam {
	private int orderId;
	private String bid;
	private int count;
	private String day;
	
	
	public PoByBidBeam() {
	}
	
	public PoByBidBeam(int orderId, String bid, int count, String day) {
		super();
		this.orderId = orderId;
		this.bid = bid;
		this.count = count;
		this.day = day;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
}
