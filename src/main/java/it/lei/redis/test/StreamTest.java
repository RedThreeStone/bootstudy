package it.lei.redis.test;

import it.lei.day1.entity.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    @Test
    public void streamTest(){
        User user =new User();
        user.setPassword("111");
        user.setUserAge(18);
        List<User> users = Arrays.asList(user);
        List<String> strings = Arrays.asList("1", "3", "5", "4", "5", "6");

        //filter
        List<User> collect = users.stream().filter(user1 -> user1.getUserAge() != 18).collect(Collectors.toList());
        System.out.println(collect);

        //forEach
        users.forEach(user1-> System.out.println(user));

        //map
        List<Integer> collect1 = users.stream().map(user1 -> user1.getUserAge()).distinct().collect(Collectors.toList());
        System.out.println(collect1);

        //limit
        List<String> collect2 = strings.stream().limit(2).collect(Collectors.toList());
        System.out.println(collect2);

        //sorted
        List<String> collect3 = strings.stream().sorted().collect(Collectors.toList());
        System.out.println(collect3);

        //合并字符串
        String collect4 = strings.stream().collect(Collectors.joining(","));
        System.out.println(collect4);
        //统计
        List<Integer> integers = Arrays.asList(1, 2, 3, 5, 7, 4, 6);
        IntSummaryStatistics intSummaryStatistics = integers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println(intSummaryStatistics.getSum());
        System.out.println(intSummaryStatistics.getAverage());

    }
}
