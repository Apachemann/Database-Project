package ZionDatabaseApplication;

public class Author {
	
	private int id;
	private String lastName;
	private String firstName;
	private String birthDate;
	
	public Author(int id, String lastName, String firstName, String birthDate) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Author [birthDate=" + birthDate + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName
				+ "]";
	}
}