package domain;

public class InvoiceLine {
    private int lineNumber;
    private long invoiceId;
    private String name;
    private String description;
    private int quantity;
    private double price;

    public InvoiceLine(int lineNumber, long invoiceId, String name, String description, int quantity, double price) {
        this.invoiceId = invoiceId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }


    @Override
	public String toString() {
		return "InvoiceLine [lineNumber=" + lineNumber + ", invoiceId=" + invoiceId + ", name=" + name
				+ ", description=" + description + ", quantity=" + quantity + ", price=" + price + "]";
	}


	public int getLineNumber() {
        return lineNumber;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}