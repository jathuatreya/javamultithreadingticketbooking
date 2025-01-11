package org.example;

import java.math.BigDecimal;

public class Vendor implements Runnable {
    private int totalTickets;
    private int ticketRetrievalRate;
    private TicketPool ticketPool;

    public Vendor(int totalTickets, int ticketRetrievalRate, TicketPool ticketPool) {
        this.totalTickets = totalTickets;
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket = new Ticket(i, "Event " + i, new BigDecimal(10000));
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep(ticketRetrievalRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Vendor thread interrupted: " + e);
            }
        }
    }
}