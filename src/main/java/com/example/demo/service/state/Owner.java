package com.example.demo.service.state;

public class Owner implements IState{

    @Override
    public String setMessage() {
        return "Recomendacion: Revisar si el ID esta bien escrito";
    }


}
