package it.lei.redis.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LamdaTest {
    @Test
    public  void  firstTest(){
        new Thread(()-> System.out.println("线程开始啦")).start();
    }
    @Test
    public void forEachTest(){
        List<String> list =new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.forEach(x-> System.out.println(x));
    }
    @Test
    public void mapTest(){
        List<String> strings = Arrays.asList("6,7,8,9");
        strings.stream();
    }
}
