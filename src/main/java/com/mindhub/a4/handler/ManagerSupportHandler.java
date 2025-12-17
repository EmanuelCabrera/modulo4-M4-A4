package com.mindhub.a4.handler;

import com.mindhub.a4.clasess.Ticket;
import com.mindhub.a4.utils.Severity;

public class ManagerSupportHandler extends SupportHandler {
    @Override
    public boolean handleRequest(Ticket ticket) {
        if (ticket.getSeverity() == Severity.CRITICAL) {
            System.out.println("Ticket #" + ticket.getId() + " atendido por: Gerente de Soporte. Resuelto.");
            return true;
        }
        System.out.println("Ticket #" + ticket.getId() + " no puede ser atendido por Gerente de Soporte. Pasando al siguiente nivel.");
        return passToNext(ticket);
    }
}
