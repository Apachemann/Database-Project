package ZionDatabaseApplication;

public class Customer {
	
	private int id;
	private int zip;
	private String lastName;
	private String firstName;
	private String address;
	private String city;
	private String state;
	
	public Customer(int id, int zip, String lastName, String firstName, String address, String city, String state) {
		super();
		this.id = id;
		this.zip = zip;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.city = city;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", zip=" + zip + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", address=" + address + ", city=" + city + ", state=" + state + "]";
	}
}