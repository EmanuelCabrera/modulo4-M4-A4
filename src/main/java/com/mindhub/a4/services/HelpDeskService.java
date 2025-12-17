package com.mindhub.a4.services;

import com.mindhub.a4.clasess.Ticket;
import com.mindhub.a4.handler.SupportHandler;
import com.mindhub.a4.handler.Level1SupportHandler;
import com.mindhub.a4.handler.Level2SupportHandler;
import com.mindhub.a4.handler.ManagerSupportHandler;
import com.mindhub.a4.handler.UnresolverSupportHandler;
import com.mindhub.a4.clasess.TicketSubject;
import com.mindhub.a4.clasess.EmailObserver;
import com.mindhub.a4.clasess.SlackObserver;
import com.mindhub.a4.clasess.BillingObserver;



public class HelpDeskService {
    private SupportHandler supportChain;
    private TicketSubject ticketSubject;


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

        // Crear instancias de los observadores para poder referenciarlos

        // Registrar los observadores
        ticketSubject = new TicketSubject();
        ticketSubject.addObserver(new EmailObserver());
        ticketSubject.addObserver(new SlackObserver());
        ticketSubject.addObserver(new BillingObserver());
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


        // Solucion problema 3: utilizando el patron de diseno Observer
        if (solved) {
            System.out.println("-- Notificando --");
            ticketSubject.notifyObservers(ticket);
            System.out.println("Ticket #" + ticket.getId() + " ha sido resuelto. Notificación enviada a todos los observadores.");
        }else{
            System.out.println("Ticket #" + ticket.getId() + " no pudo ser resuelto. No se notificará.");
        }
    }
}
