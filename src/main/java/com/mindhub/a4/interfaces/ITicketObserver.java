package com.mindhub.a4.interfaces;

import com.mindhub.a4.clasess.Ticket;

public interface ITicketObserver {
    void notifyTicketSolved(Ticket ticket);
}
