package com.example.todo_api.hw;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    public void myControllerPrint() {
        System.out.println("controller");
        myService.MyServicePrint();
    }
}
