package ZionDatabaseApplication;

public class AuthorReport {
	
	private int id;
	private String firstName;
	private String lastName;
	private int totalNumberOfBooks;
	
	public AuthorReport(int id, String firstName, String lastName, int totalNumberOfBooks)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalNumberOfBooks = totalNumberOfBooks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getTotalNumberOfBooks() {
		return totalNumberOfBooks;
	}

	public void setTotalNumberOfBooks(int totalNumberOfBooks) {
		this.totalNumberOfBooks = totalNumberOfBooks;
	}

	@Override
	public String toString() {
		return "AuthorReport [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", totalNumberOfBooks=" + totalNumberOfBooks + "]";
	}
}