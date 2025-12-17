package com.mindhub.a4.clasess;

import com.mindhub.a4.interfaces.ICustomerStrategy;
import com.mindhub.a4.utils.Severity;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data 
@Builder
@AllArgsConstructor 
@NoArgsConstructor    
public class Ticket {
    private int id;
    private Severity severity;
    private ICustomerStrategy customerStrategy;
}
