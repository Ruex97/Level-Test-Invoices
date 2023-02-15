package domain;

import java.util.ArrayList;

public class Invoice {
    private static long nextId = 1;
    private long invoiceId;
    private String name;
    private String address;
    private String telephone;
    private double totalAmount;
    private ArrayList<InvoiceLine> invoiceLines;

    public Invoice(String name, String address, String telephone) {
        this.invoiceId = nextId++;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.totalAmount = 0.0;
        this.invoiceLines = new ArrayList<>();
    }

    public void addInvoiceLine(String name, String description, int quantity, double price) {
        int lineNumber = invoiceLines.size() + 1;
        InvoiceLine invoiceLine = new InvoiceLine(lineNumber, invoiceId, name, description, quantity, price);
        invoiceLines.add(invoiceLine);
        calculateTotalAmount();
    }

    public double calculateTotalAmount() {
        double total = 0.0;
        for (InvoiceLine invoiceLine : invoiceLines) {
            total += invoiceLine.getQuantity() * invoiceLine.getPrice();
        }
        this.totalAmount = total;
        return total;
    }
    
   
    	


    public long getInvoiceId() {
        return invoiceId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
        
    }

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", name=" + name + ", address=" + address + ", telephone="
				+ telephone + ", totalAmount=" + totalAmount + ", invoiceLines=" + invoiceLines.toString() + "]";
	}
    
    
    
    }