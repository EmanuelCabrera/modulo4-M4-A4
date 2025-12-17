package com.mindhub.a4.clasess;

import com.mindhub.a4.interfaces.ITicketObserver;

public class BillingObserver implements ITicketObserver {
    @Override
    public void notifyTicketSolved(Ticket ticket) {
        System.out.println("BillingObserver: Notificando al departamento de facturación que el ticket #" + ticket.getId() + " ha sido resuelto.");
    }
}
