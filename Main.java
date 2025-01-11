package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for TicketPool
        System.out.print("Enter maximum ticket capacity for the pool: ");
        int maxTicketCapacity = scanner.nextInt();
        TicketPool ticketPool = new TicketPool(maxTicketCapacity);

        // Input for Vendors
        System.out.print("Enter the number of vendors: ");
        int numVendors = scanner.nextInt();
        Vendor[] vendors = new Vendor[numVendors];

        for (int i = 0; i < numVendors; i++) {
            System.out.println("Vendor " + (i + 1) + ":");
            System.out.print("  Enter total tickets to be provided: ");
            int totalTickets = scanner.nextInt();
            System.out.print("  Enter ticket retrieval rate (in seconds): ");
            int ticketRetrievalRate = scanner.nextInt();
            vendors[i] = new Vendor(totalTickets, ticketRetrievalRate, ticketPool);
            Thread vendorThread = new Thread(vendors[i], "Vendor " + (i + 1));
            vendorThread.start();
        }

        // Input for Customers
        System.out.print("Enter the number of customers: ");
        int numCustomers = scanner.nextInt();
        Customer[] customers = new Customer[numCustomers];

        for (int i = 0; i < numCustomers; i++) {
            System.out.println("Customer " + (i + 1) + ":");
            System.out.print("  Enter the number of tickets to buy: ");
            int quantity = scanner.nextInt();
            System.out.print("  Enter ticket retrieval rate (in seconds): ");
            int customerRetrievalRate = scanner.nextInt();
            customers[i] = new Customer(customerRetrievalRate, ticketPool, quantity);
            Thread customerThread = new Thread(customers[i], "Customer ID-" + i);
            customerThread.start();
        }

        scanner.close();
    }
}