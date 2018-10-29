package it.lei.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisPubSubConfig {
    @Bean
    public MessageListenerAdapter messageListenerAdapter(){
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new NewsRedisListener());
        //设置序列化策略
        messageListenerAdapter.setSerializer(new StringRedisSerializer());
        return messageListenerAdapter;
    }
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
                                                                       MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        //这里可以配置多个adapter 一直add就好了
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter,new PatternTopic("news*"));
        return  redisMessageListenerContainer;
    }
}
