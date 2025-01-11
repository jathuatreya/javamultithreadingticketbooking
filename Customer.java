package org.example;

public class Customer implements Runnable {
    private int customerRetrievalRate;
    private TicketPool ticketPool;
    private int quantity;

    public Customer(int customerRetrievalRate, TicketPool ticketPool, int quantity) {
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.buyTicket();
            System.out.println("Ticket details: " + ticket + " bought by " + Thread.currentThread().getName());
            try {
                Thread.sleep(customerRetrievalRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Customer thread interrupted: " + e);
            }
        }
    }
}