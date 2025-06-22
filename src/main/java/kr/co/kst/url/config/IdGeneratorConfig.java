package kr.co.kst.url.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class IdGeneratorConfig {

    @Bean
    @Primary
    public SnowflakeIdGenerator snowflakeIdGenerator(
            @Value("${app.snowflake.worker-id:1}") long workerId,
            @Value("${app.snowflake.datacenter-id:1}") long datacenterId
    ) {
        return new SnowflakeIdGenerator(workerId, datacenterId);
    }

}
