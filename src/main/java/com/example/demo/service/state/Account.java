package com.example.demo.service.state;

public class Account implements IState{
    @Override
    public String setMessage() {
        return "Recomendacion: Revisar si el ID de la cuenta esta bien escrito";
    }
}
