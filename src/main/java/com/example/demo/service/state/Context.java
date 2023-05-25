package com.example.demo.service.state;

import lombok.Generated;
import org.springframework.stereotype.Component;

@Component
@Generated
public class Context {
    private IState state;
    public void setState(IState state)
    {
        this.state=state;
    }
    public void runState()
    {
        System.out.println("\u001B[31m"+state.setMessage()+"\u001B[0m");
    }

}
