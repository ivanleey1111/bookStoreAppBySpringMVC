package beans;

public class MonthlySold {
	private String title;
	private int count;
	
	public MonthlySold(){
		
	}
	
	public MonthlySold(String title, int count) {
		super();
		this.title = title;
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
	
	
}
