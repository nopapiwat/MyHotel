import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;

public class Search {
	
	private Dao dao;
	List<Hotel> data;
	
	public Search(){
		dao = new Dao();
		try {
			data = dao.getData();
		} catch (Exception e) {			
			
		}
	}
	
	public String search(String city, String order){
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		String res = "";
		for(int i = 0; i < data.size(); i++){			
			if(city == null || data.get(i).getCity().equals(city)){
				hotels.add(data.get(i));				
			}
		}
		Collections.sort(hotels);
		if(order != null && order.equals("DESC")){
			Collections.reverse(hotels);
		}
		for(int i = 0; i < hotels.size(); i++){			
			res += hotels.get(i).toString()+"\n";
		}
		return res;
	}
	
}
