package com.mindhub.a4.clasess;

import com.mindhub.a4.interfaces.ITicketObserver;

public class SlackObserver implements ITicketObserver {
    @Override
    public void notifyTicketSolved(Ticket ticket) {
        System.out.println("SlackObserver: Notificando al equipo de soporte por Slack que el ticket #" + ticket.getId() + " ha sido resuelto.");
    }
}
