package com.mindhub.a4.clasess;

import com.mindhub.a4.interfaces.ICustomerStrategy;

public class StandardCustomer implements ICustomerStrategy {
    @Override
    public int getSLA() {
        System.out.println("StandardCustomer: SLA = 24, respuesta tardia");
        return 24;
    }
}
