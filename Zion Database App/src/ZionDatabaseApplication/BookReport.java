package ZionDatabaseApplication;

//import java.text.DecimalFormat;

public class BookReport {

	private int id;
	private String title;
	private int quantity;
	private float totalSales;

	public BookReport(int id, String title, int quantity, float totalSales)
	{
		super();
		this.id = id;
		this.title = title;
		this.quantity = quantity;
		this.totalSales = totalSales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTotalSales() {
		String formattedSales = String.format("%.2f", totalSales);
		return formattedSales;
	}

	public void setTotalSales(float totalSales) {
		this.totalSales = totalSales;
	}

	@Override
	public String toString() {
		return "BookReport [id=" + id + ", title=" + title + ", quantity=" + quantity + ", totalSales=" + totalSales
				+ "]";
	}
}