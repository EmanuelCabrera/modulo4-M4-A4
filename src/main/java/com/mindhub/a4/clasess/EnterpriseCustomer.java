package com.mindhub.a4.clasess;

import com.mindhub.a4.interfaces.ICustomerStrategy;

public class EnterpriseCustomer implements ICustomerStrategy {
    @Override
    public int getSLA() {
        System.out.println("EnterpriseCustomer: SLA = 1, respuesta inmediata");
        return 1;
    }
}
