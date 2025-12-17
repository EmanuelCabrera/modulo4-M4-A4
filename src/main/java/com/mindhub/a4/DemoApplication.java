package com.mindhub.a4;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mindhub.a4.services.HelpDeskService;
import com.mindhub.a4.clasess.Ticket;
import com.mindhub.a4.utils.Severity;
import com.mindhub.a4.clasess.StandardCustomer;
import com.mindhub.a4.clasess.PremiumCustomer;
import com.mindhub.a4.clasess.EnterpriseCustomer;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("Iniciando aplicacion Help Desk...");
        System.out.println("================================================");
        System.out.println("Aplicacion Help Desk iniciada correctamente");
        System.out.println("================================================");
        Ticket t1 = Ticket.builder().id(1).severity(Severity.BASIC).customerStrategy(new StandardCustomer()).build();
        Ticket t2 = Ticket.builder().id(2).severity(Severity.MEDIUM).customerStrategy(new PremiumCustomer()).build();
        Ticket t3 = Ticket.builder().id(3).severity(Severity.CRITICAL).customerStrategy(new EnterpriseCustomer()).build();
        Ticket t4 = Ticket.builder().id(4).severity(Severity.UNRESOLVED).customerStrategy(new StandardCustomer()).build();

        HelpDeskService helpDeskService = new HelpDeskService();
        helpDeskService.processTicket(t1);
        helpDeskService.processTicket(t2);
        helpDeskService.processTicket(t3);
        helpDeskService.processTicket(t4);

        System.out.println("================================================");
        System.out.println("Aplicacion Help Desk finalizada correctamente");
        System.out.println("================================================");
    }
}

