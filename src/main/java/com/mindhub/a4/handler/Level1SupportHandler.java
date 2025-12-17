package com.mindhub.a4.handler;

import com.mindhub.a4.clasess.Ticket;
import com.mindhub.a4.utils.Severity;

public class Level1SupportHandler extends SupportHandler {
    @Override
    public boolean handleRequest(Ticket ticket) {
        if (ticket.getSeverity() == Severity.BASIC) {
            System.out.println("Ticket #" + ticket.getId() + " atendido por: Soporte Nivel 1 (Básico). Resuelto.");
            return true;
        }
        System.out.println("Ticket #" + ticket.getId() + " no puede ser atendido por Soporte Nivel 1 (Básico). Pasando al siguiente nivel.");
        return passToNext(ticket);
    }
}
