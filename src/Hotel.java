
public class Hotel implements Comparable<Hotel> {
	private String city;
	private int id;
	private String room;
	private int price;
	
	public Hotel(String city,int id,String room,int price) {
		this.city = city;
		this.id = id;
		this.room = room;
		this.price = price;
	}

	public String getCity() {
		return city;
	}

	public int getId() {
		return id;
	}

	public String getRoom() {
		return room;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Hotel [city=" + city + ", id=" + id + ", room=" + room + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Hotel o) {
		return this.price-o.price;		
	}
	
	
	
}
