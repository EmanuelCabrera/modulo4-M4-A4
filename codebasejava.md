import java.util.ArrayList;
import java.util.List;

enum Severity { BASIC, MEDIUM, CRITICAL }

class Ticket {
    public int id;
    public Severity severity;
    public String customerType; // "Premium", "Enterprise", "Standard"

    public Ticket(int id, Severity severity, String customerType) {
        this.id = id;
        this.severity = severity;
        this.customerType = customerType;
    }
}

class HelpDesk {

    public void processTicket(Ticket ticket) {
        System.out.println("\n>>> Procesando Ticket #" + ticket.id + " (" + ticket.customerType + " - " + ticket.severity + ")");

        // ---------------------------------------------------------
        // PROBLEMA 1: STRATEGY
        // Cálculo de SLA (Service Level Agreement) rígido
        // ---------------------------------------------------------
        int estimatedHours = 0;
        if (ticket.customerType.equals("Premium")) {
            estimatedHours = 2; // Respuesta rápida
        } else if (ticket.customerType.equals("Enterprise")) {
            estimatedHours = 1; // Respuesta inmediata
        } else {
            estimatedHours = 24; // Standard
        }
        System.out.println("Tiempo estimado de resolución: " + estimatedHours + "h");

        // ---------------------------------------------------------
        // PROBLEMA 2: CHAIN OF RESPONSIBILITY
        // Lógica de asignación hardcodeada
        // ---------------------------------------------------------
        boolean solved = false;
        
        // Intenta Soporte Nivel 1
        if (ticket.severity == Severity.BASIC) {
            System.out.println("Atendido por: Soporte Nivel 1 (Básico). Resuelto.");
            solved = true;
        } 
        
        // Si no, intenta Soporte Nivel 2
        if (!solved && ticket.severity == Severity.MEDIUM) {
            System.out.println("Atendido por: Soporte Nivel 2 (Avanzado). Resuelto.");
            solved = true;
        }

        // Si no, Gerente
        if (!solved && ticket.severity == Severity.CRITICAL) {
            System.out.println("Atendido por: Gerente de Soporte. Resuelto.");
            solved = true;
        }

        if (!solved) {
            System.out.println("Nadie pudo resolver el ticket. Perdido en el limbo.");
        }

        // ---------------------------------------------------------
        // PROBLEMA 3: OBSERVER
        // Notificaciones acopladas directamente
        // ---------------------------------------------------------
        if (solved) {
            System.out.println("-- Notificando --");
            // Email Service
            System.out.println("Email: Enviando confirmación al cliente.");
            
            // Slack Service (para el equipo)
            System.out.println("Slack: Nuevo ticket resuelto #" + ticket.id);
            
            // Billing Service (si aplica)
            if (ticket.customerType.equals("Premium") || ticket.customerType.equals("Enterprise")) {
                System.out.println("Billing: Registrando cargo por soporte.");
            }
        }
    }
}

public class HelpDeskSystem {
    public static void main(String[] args) {
        HelpDesk helpDesk = new HelpDesk();

        Ticket t1 = new Ticket(101, Severity.BASIC, "Standard");
        Ticket t2 = new Ticket(102, Severity.CRITICAL, "Enterprise");
        Ticket t3 = new Ticket(103, Severity.MEDIUM, "Premium");

        helpDesk.processTicket(t1);
        helpDesk.processTicket(t2);
        helpDesk.processTicket(t3);
    }
}