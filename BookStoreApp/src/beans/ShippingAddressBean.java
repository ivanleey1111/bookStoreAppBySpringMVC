package beans;

public class ShippingAddressBean {
	private String shipemail;
	private String shipname;
	private String shipaddress;
	private String shipcity;
	private String shipprovince;
	private String shipzip;
	private String shipphone;
	
	public ShippingAddressBean() {
		// TODO Auto-generated constructor stub
	}

	public ShippingAddressBean(String shipemail, String shipname, String shipaddress, String shipcity,
			String shipprovince, String shipzip, String shipphone) {
		super();
		this.shipemail = shipemail;
		this.shipname = shipname;
		this.shipaddress = shipaddress;
		this.shipcity = shipcity;
		this.shipprovince = shipprovince;
		this.shipzip = shipzip;
		this.shipphone = shipphone;
	}

	public String getShipemail() {
		return shipemail;
	}

	public void setShipemail(String shipemail) {
		this.shipemail = shipemail;
	}

	public String getShipname() {
		return shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public String getShipaddress() {
		return shipaddress;
	}

	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}

	public String getShipcity() {
		return shipcity;
	}

	public void setShipcity(String shipcity) {
		this.shipcity = shipcity;
	}

	public String getShipprovince() {
		return shipprovince;
	}

	public void setShipprovince(String shipprovince) {
		this.shipprovince = shipprovince;
	}

	public String getShipzip() {
		return shipzip;
	}

	public void setShipzip(String shipzip) {
		this.shipzip = shipzip;
	}

	public String getShipphone() {
		return shipphone;
	}

	public void setShipphone(String shipphone) {
		this.shipphone = shipphone;
	}
	
	
	
	
	
	
}
