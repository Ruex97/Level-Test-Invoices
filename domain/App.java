// Temps 3:12h
// paron 20minuts - paron 1h
//

package domain;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings(value = { "deprecation" })
public class App {

	public static void main(String[] args) throws CustomerNotFoundException {
		
		ArrayList<Invoice> invoices = new ArrayList<>();
		
	
		int option;
        do {
	            option = Keyboard.getInt("Choose an option: \n1- Create an invoice \n2- Create invoice lines for an existing invoice"
	        			+ "\n3- Calculate the amount of an invoice \n4- Calculate the amount of all invoices"
	        			+ "\n5- Show a customer's invoice amount \n6- Show all customer invoices"
	        			+ "\n7- Show the invoices that have a certain article \n8- Delete an outdated invoice"
	        			+ "\n9- Write invoices and invoice lines in two different files (.csv) \n0- Exit\"");

	            switch (option) {
	                case 1:
	                    createInvoice(invoices);
	                    break;
	                case 2:
	                    createInvoiceLine(invoices);
	                    break;
	                case 3:
	                    calculateInvoiceAmount(invoices);
	                    break;
	                case 4:
	                    calculateAllInvoiceAmount(invoices);
	                    break;
	                case 5:
	                    showCustomerInvoiceAmount(invoices);
	                    break;
	                case 6:
	                    showAllCustomerInvoices(invoices);
	                    break;
	                case 7:
	                    showInvoicesByArticle(invoices);
	                    break;
	                case 8:
	                    deleteInvoice(invoices);
	                    break;
	                case 9:
	//                    writeInvoicesToFile();
	                    break; 
	                case 0:
	                    System.out.println("Goodbye!");
	                    break;
	                default:
	                    System.out.println("Invalid option."); 
	            }
	        } while (option != 0);
	    }

	    private static void createInvoice(ArrayList<Invoice> invoices) {
	        // Prompt the user for invoice details
	        
	    	String name = Keyboard.getString("Enter name:");
	        String address = Keyboard.getString("Enter address:");
	        String telephone = Keyboard.getString("Enter telephone:");

	        // Create a new invoice and add it to the list
	        Invoice invoice = new Invoice(name, address, telephone);
	        invoices.add(invoice);
	        System.out.println("Invoice created with ID " + invoice.getInvoiceId() + ".");
	    }
	    
	    private static void createInvoiceLine(ArrayList<Invoice> invoices) {
	        // Prompt the user for the invoice number 
	  
	    	long invoiceId = Keyboard.getLong("Enter invoice ID:");
	    	    
	    	    // Search for invoice in the array
	    	    Invoice invoice = null;
	    	    boolean invoiceFound = false;
	    	    int i = 0;
	    	    while (!invoiceFound && i < invoices.size()) {
	    	        if (invoices.get(i).getInvoiceId() == invoiceId) {
	    	            invoice = invoices.get(i);
	    	            invoiceFound = true;
	    	        }
	    	        i++;
	    	    }
	    	    
	    	    if (invoiceFound) {
	    	        invoice.addInvoiceLine(null, null, i, invoiceId);
	    	    	
	    	    	// Invoice found, create new invoice line
	    	   
	    	        String name = Keyboard.getString("Enter name:");
	    	        String description = Keyboard.getString("Enter description:");
	    	        int quantity = Keyboard.getInt("Enter quantity:");
	    	        double price = Keyboard.getDouble("Enter price:");
	    	        invoice.addInvoiceLine(name, description, quantity, price);
	    	        System.out.println("Invoice line added successfully.");
	    	        
	    	    } else {
	    	        // Invoice not found, prompt user to try again
	    	        System.out.println("Invoice not found. Please try again.");
	    	        createInvoiceLine(invoices);
	    	    }
	    	}
	    
	    private static void calculateInvoiceAmount(ArrayList<Invoice> invoices) {
	        long invoiceId = Keyboard.getLong("Enter invoice ID:");

	        // Search for invoice in the array
	        for (Invoice invoice : invoices) {
	            if (invoice.getInvoiceId() == invoiceId) {
	                double total = invoice.calculateTotalAmount();
	                System.out.println("Total invoice amount: " + total);
	                return;
	            } else {
	            	System.out.println("Invoice not found.");
	            }
	        }

	       
	    }
	    
	    private static void calculateAllInvoiceAmount(ArrayList<Invoice> invoices) {
	        double total = invoices.stream()
	                .mapToDouble(Invoice::calculateTotalAmount)
	                .sum();

	        System.out.println("Total amount of all invoices: " + total);
	    }
	    
	    private static void showCustomerInvoiceAmount(ArrayList<Invoice> invoices) throws CustomerNotFoundException {
	  
	        String name = Keyboard.getString("Enter customer name:");

	        Optional<Invoice> invoice = invoices.stream()
	                .filter(i -> i.getName().equals(name))
	                .findFirst();

	        if (invoice.isPresent()) {
	            double total = invoice.get().calculateTotalAmount();
	            System.out.println("Invoice amount for customer " + name + ": " + total);
	        } else {
	            throw new CustomerNotFoundException("Customer " + name + " does not have an invoice.");
	        }
	    }
	    
	    public static void showAllCustomerInvoices(ArrayList<Invoice> invoices) {
	        String customerName = Keyboard.getString("Enter customer name: ");

	        List<Invoice> customerInvoices = invoices.stream()
	            .filter(i -> i.getName().equals(customerName))
	            .collect(Collectors.toList());

	        if (customerInvoices.isEmpty()) {
	            System.out.println("No invoices found for customer " + customerName);
	        } else {
	            System.out.println("Invoices for customer " + customerName + ":");
	            customerInvoices.forEach(i -> System.out.println(i.toString()));
	        }
	    }
	    
	    private static void showInvoicesByArticle(ArrayList<Invoice> invoices) {
	       String product = Keyboard.getString("Enter the article number: ");
	        
	       List<Invoice> matchingInvoices = new ArrayList<>();
	       
	        for (Invoice invoice : invoices) {
	            List<InvoiceLine> invoiceLines = invoice.getInvoiceLines();
	            
	            boolean hasArticle = invoiceLines.stream()
	                    .anyMatch(line -> line.getName().equalsIgnoreCase(product));
	            
	            if (hasArticle) {
	                matchingInvoices.add(invoice);
	            }
	            
	        }

	        if (matchingInvoices.isEmpty()) {
	            System.out.println("No invoices found with article: " + product);
	        } else {
	            matchingInvoices.forEach(System.out::println);
	        }
	    }

		/**
	   	 * @deprecated: This method should not be used.  
	   	 */
	    @Deprecated
	    private static void deleteInvoice(ArrayList<Invoice> invoices) {
	    	long invoiceId = Keyboard.getLong("Enter invoice ID you want to delete:");
	    	
	    	for (int i = 0; i < invoices.size(); i++) {
	    		if (invoices.get(i).getInvoiceId() == invoiceId) {
	    			invoices.remove(i);
	    	        System.out.println("Invoice " + invoiceId + " has been deleted.");
	    	        
	    	      } else {
	    	    	System.out.println("Invoice " + invoiceId + " not found."); 
	    	      }
	    	}
	    	   
	    }

	    	
	    

	    
	    


}
