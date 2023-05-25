package com.example.demo.service.state;

import lombok.Generated;

@Generated
public class Unregistrable implements IState{
    @Override
    public String setMessage() {
        return "Recomendacion: Revisar si el usuario tiene muchas cuentas en su propiedad";
    }
}
