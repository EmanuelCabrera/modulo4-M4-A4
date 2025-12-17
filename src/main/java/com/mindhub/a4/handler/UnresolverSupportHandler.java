package com.mindhub.a4.handler;

import com.mindhub.a4.clasess.Ticket;

public class UnresolverSupportHandler extends SupportHandler {
    @Override
    public boolean handleRequest(Ticket ticket) {
        System.out.println("Ticket #" + ticket.getId() + " no puede ser atendido. Perdido en el limbo.");
        return false;
    }
}
