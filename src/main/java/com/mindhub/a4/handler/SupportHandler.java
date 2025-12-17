package com.mindhub.a4.handler;

import com.mindhub.a4.clasess.Ticket;

public abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public SupportHandler setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public abstract boolean handleRequest(Ticket ticket);

    protected boolean passToNext(Ticket ticket) {
        if (nextHandler != null) {
            return nextHandler.handleRequest(ticket);
        }
        return false;
    }
}
