package com.example.todo_api.hw;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class MyService {

    private final MyRepository myRepository;

    public void MyServicePrint() {
        System.out.println("service");
        myRepository.myRepositoryPrint();
    }
}
