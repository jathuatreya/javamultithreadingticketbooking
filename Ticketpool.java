package org.example;// TicketPool.java
import java.util.LinkedList;
import java.util.Queue;

class TicketPool {
    private int maxTicketCapacity;
    private Queue<Ticket> ticketQueue;

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.ticketQueue = new LinkedList<>();
    }

    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maxTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while adding ticket: " + e);
            }
        }
        ticketQueue.add(ticket);
        notifyAll();
        System.out.println("Ticket added by " + Thread.currentThread().getName() + ", queue size: " + ticketQueue.size());
    }

    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while buying ticket: " + e);
            }
        }
        Ticket ticket = ticketQueue.poll();
        notifyAll();
        System.out.println("Ticket bought by " + Thread.currentThread().getName() + ", queue size: " + ticketQueue.size());
        return ticket;
    }
}
