package com.mindhub.a4.handler;

import com.mindhub.a4.clasess.Ticket;
import com.mindhub.a4.utils.Severity;

public class Level2SupportHandler extends SupportHandler {
    @Override
    public boolean handleRequest(Ticket ticket) {
        if (ticket.getSeverity() == Severity.MEDIUM) {
            System.out.println("Ticket #" + ticket.getId() + " atendido por: Soporte Nivel 2 (Avanzado). Resuelto.");
            return true;
        }
        System.out.println("Ticket #" + ticket.getId() + " no puede ser atendido por Soporte Nivel 2 (Avanzado). Pasando al siguiente nivel.");
        return passToNext(ticket);
    }
}
