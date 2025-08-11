package com.lsxp;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Slf4j
public class UUIDTest {

    @Test
    public void testUUID() {
        for (int i = 0; i < 10; i++) {
            System.out.println(UUID.randomUUID());
        }
    }

    @Test
    public void testSubString(){
        String name = "1.jpg";
        String extension = name.substring(name.lastIndexOf("."));
        System.out.println("extension: " + extension);
    }
}
