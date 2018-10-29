package it.lei.redis.test;

import com.alibaba.fastjson.JSONObject;
import it.lei.day1.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RedisTest extends BaseTest{
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void  jedisTest(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        HashMap<String,String> userTable=new HashMap<String, String>();
        ArrayList<String> userTable20 = new ArrayList<>();
        ArrayList<String> userTableWoman = new ArrayList<>();
        Integer userid1=1;
        User user1 =new User(userid1,19,"wang1","1","man");
        userTable.put(userid1.toString(), JSONObject.toJSONString(user1));
        Integer userid2=2;
        User user2 =new User(userid2,20,"wang2","1","woman");
        userTable.put(userid2.toString(), JSONObject.toJSONString(user2));
        userTable20.add(userid2.toString());
        userTableWoman.add(userid2.toString());
        Integer userid3=1;
        User user3 =new User(userid3,20,"wang3","1","man");
        userTable.put(userid3.toString(), JSONObject.toJSONString(user3));
        userTable20.add(userid3.toString());
        Integer userid4=1;
        User user4 =new User(userid4,21,"wang4","1","man");
        userTable.put(userid4.toString(), JSONObject.toJSONString(user4));
        Integer userid5=1;
        User user5 =new User(userid5,22,"wang5","1","man");
        userTable.put(userid5.toString(), JSONObject.toJSONString(user5));

        jedis.hmset("user_table",userTable);
        for(String userid:userTable20){
            jedis.sadd("user_table_20",userid);

        }
        for(String userid:userTableWoman){
            jedis.sadd("user_table_woman",userid);
        }
        Set<String> ids = jedis.sinter("user_table_20", "user_table_woman");
        for(String id:ids){
            String user_table = jedis.hget("user_table", id);
            System.out.println(user_table);
        }

    }
    @Test
    public void JedisPoolTest(){
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        HostAndPort hostAndPort1 = new HostAndPort("127.0.0.1", 7001);
        HostAndPort hostAndPort2 = new HostAndPort("127.0.0.1", 7002);
        HostAndPort hostAndPort3 = new HostAndPort("127.0.0.1", 7003);
        HostAndPort hostAndPort4 = new HostAndPort("127.0.0.1", 7004);
        hostAndPorts.add(hostAndPort1);
        hostAndPorts.add(hostAndPort2);
        hostAndPorts.add(hostAndPort3);
        hostAndPorts.add(hostAndPort4);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxWaitMillis(-1);
        jedisPoolConfig.setTestOnBorrow(true);
        JedisCluster jedisCluster = new JedisCluster(hostAndPorts,6000,100,jedisPoolConfig);
        jedisCluster.get("name");
    }
    @Test
    public void  redistTemplateTest(){
        BoundHashOperations<String, Object, Object> person = redisTemplate.boundHashOps("firstPerson");
        person.put("age","100000");
        person.put("name","亚当");
        person.put("sex","woman");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name","夏娃");
        hashMap.put("age","10000");
        hashMap.put("sex","man");
        redisTemplate.opsForHash().putAll("secondPerson",hashMap);
    }
    @Test
    public void redisConnectionTest(){
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set("connectTest".getBytes(),"成功啦".getBytes());
                return "success";
            }
        });
    }
    @Test
    public void redisPublisTest(){
        redisTemplate.convertAndSend("news","来来来 发消息啦!!!");
    }
}
