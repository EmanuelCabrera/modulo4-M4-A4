package com.mindhub.a4.clasess;

import com.mindhub.a4.interfaces.ITicketObserver;

public class EmailObserver implements ITicketObserver {
    @Override
    public void notifyTicketSolved(Ticket ticket) {
        System.out.println("EmailObserver: Notificando al cliente por email que el ticket #" + ticket.getId() + " ha sido resuelto.");
    }
}
