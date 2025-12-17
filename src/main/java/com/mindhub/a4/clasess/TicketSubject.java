package com.mindhub.a4.clasess;

import java.util.List;
import java.util.ArrayList;

import com.mindhub.a4.interfaces.ITicketObserver;

public class TicketSubject {
    private List<ITicketObserver> observers = new ArrayList<>();

    //registrar un nuevo observador
    public void addObserver(ITicketObserver observer) {
        observers.add(observer);
    }

    //remover un observador
    public void removeObserver(ITicketObserver observer) {
        observers.remove(observer);
    }

    //notificar a todos los observadores que un ticket ha sido resuelto
    public void notifyObservers(Ticket ticket) {
        for (ITicketObserver observer : observers) {
            observer.notifyTicketSolved(ticket);
        }
    }
}
