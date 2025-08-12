package com.lsxp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


@Slf4j
public class Streamtest {


    @Test
    public void StreamTest(){
        List<String> strings = Arrays.asList("a","b","c","  ","asdasd","123");
        Stream<String> stream = strings.stream();

    }
}
