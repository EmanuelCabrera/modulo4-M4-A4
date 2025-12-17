package com.mindhub.a4.services;

import com.mindhub.a4.clasess.Ticket;
import com.mindhub.a4.handler.SupportHandler;
import com.mindhub.a4.handler.Level1SupportHandler;
import com.mindhub.a4.handler.Level2SupportHandler;
import com.mindhub.a4.handler.ManagerSupportHandler;
import com.mindhub.a4.handler.UnresolverSupportHandler;

public class HelpDeskService {
    private SupportHandler supportChain;

    public HelpDeskService() {
        // Construir la cadena de responsabilidad
        SupportHandler level1 = new Level1SupportHandler();
        SupportHandler level2 = new Level2SupportHandler();
        SupportHandler manager = new ManagerSupportHandler();
        SupportHandler unresolver = new UnresolverSupportHandler();

        level1.setNextHandler(level2)
        .setNextHandler(manager)
        .setNextHandler(unresolver);

        supportChain = level1;
    }

    public void processTicket(Ticket ticket) {
        // Solucion problema 1: utilizando el patron de diseno Strategy
        System.out.println("\n>>> Procesando Ticket #" + ticket.getId() + " (" + ticket.getSeverity() + ")");
        int estimatedHours = ticket.getCustomerStrategy().getSLA();
        System.out.println("Tiempo estimado de resolución: " + estimatedHours + " horas");

        // Solucion problema 2: utilizando el patron de diseno Chain of Responsibility
        System.out.println("\n>>> Asignando ticket #" + ticket.getId() + " a un soporte...");
        boolean solved = supportChain.handleRequest(ticket);
        if (!solved) {
            System.out.println("Ticket #" + ticket.getId() + " no pudo ser resuelto. Perdido en el limbo.");
        }
    }
}
