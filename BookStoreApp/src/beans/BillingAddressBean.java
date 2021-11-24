package beans;

public class BillingAddressBean {
	
	private String email;
	private String name;
	private String address;
	private String city;
	private String province;
	private String zip;
	
	public BillingAddressBean() {
		// TODO Auto-generated constructor stub
	}
	
	public BillingAddressBean(String email, String name, String address, String city, String province, String zip) {
		this.email = email;
		this.name = name;
		this.address = address;
		this.city = city;
		this.province = province;
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
}
