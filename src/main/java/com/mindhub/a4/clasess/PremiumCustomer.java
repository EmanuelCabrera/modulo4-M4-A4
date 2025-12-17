package com.mindhub.a4.clasess;

import com.mindhub.a4.interfaces.ICustomerStrategy;

public class PremiumCustomer implements ICustomerStrategy {
    
    @Override
    public int getSLA() {
        System.out.println("PremiumCustomer: SLA = 2, respuesta rapida");
        return 2;
    }
}
