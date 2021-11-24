package rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import beans.BookBean;
import beans.PoByBidBeam;
import dao.BillDao;
import dao.ContentDao;

@Path("ThirdParty")
public class ThirdParty {
	
	@GET
	@Path("/getProductInfo/")
	@Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
	public String getProductInfo(@QueryParam("productId") String productId) {
		String result = null;
		ContentDao cd = new ContentDao();
		BookBean bb = null;
		try {
			bb = cd.getByBookId(productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JsonObjectBuilder doc = Json.createObjectBuilder();
		//doc.add("credit_taken", namePrefix).add("namePrefix", namePrefix);
		JsonObjectBuilder book = Json.createObjectBuilder();
		book.add("bid", bb.getBid())
				.add("title", bb.getBid())
				.add("price", bb.getPrice())
				.add("category", bb.getCategory())
				.add("img", bb.getImg())
				.add("count", bb.getCount());
		JsonObject value = book.build();
		String serializedJson = value.toString();
		System.out.println(serializedJson);
		return serializedJson;
		
	}
	
	
	@GET
	@Path("/getOrdersByPartNumber/")
	@Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
	public String getOrdersByPartNumber(@QueryParam("productId") String productId) {
		String result = null;
		BillDao bd = new BillDao();
		List<PoByBidBeam> bb = new ArrayList<PoByBidBeam>();
		try {
			bb = bd.getOrdersbyBid(productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonArrayBuilder orders = Json.createArrayBuilder();
		int count = bb.size();
		for(int i = 0;i<count;i++) {
			orders.add(Json.createObjectBuilder().add("orderId", bb.get(i).getOrderId())
					.add("BID", bb.get(i).getBid())
					.add("COUNT", bb.get(i).getCount())
					.add("DAY", bb.get(i).getDay())
					);
		}
		
		JsonObjectBuilder doc = Json.createObjectBuilder();
		doc.add("orders", orders);
		JsonObject value = doc.build();
		String serializedJson = value.toString();
		System.out.println(serializedJson);
		return serializedJson;
		
		
		
	}
	
}
