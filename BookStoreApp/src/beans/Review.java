package beans;

public class Review {
	
	private String bid;
	
	private String email;
	
	private String comment;
	
	private String rating;
	
	private Member member;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Review(String bid, String email, String comment, String rating) {
		
		this.bid = bid;
		
		this.email = email;
		this.comment = comment;
		
		this.rating = rating;
		
	}
	
	
	
	

	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	
	
}
