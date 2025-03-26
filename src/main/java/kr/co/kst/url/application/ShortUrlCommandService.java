package kr.co.kst.url.application;

import kr.co.kst.url.config.SnowflakeIdGenerator;
import kr.co.kst.url.domain.ShortUrl;
import kr.co.kst.url.domain.ShortUrlMessage;
import kr.co.kst.url.domain.KafkaBaseMessage;
import kr.co.kst.url.dto.ShortUrlResponse;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlCommandService {

    private static final String DOMAIN = "https://short.ly";


    private final KafkaTemplate<String, KafkaBaseMessage> kafkaTemplate;

    public ShortUrlCommandService(KafkaTemplate<String, KafkaBaseMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ShortUrlResponse create(String url) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1,1);

        ShortUrl shortUrl = ShortUrl.create(idGenerator, url);

        kafkaTemplate.send("short-url-topic", new ShortUrlMessage(shortUrl.getCode(), url));
        String result = DOMAIN + "/" + shortUrl.getCode();

        return new ShortUrlResponse(shortUrl.getCode(), result, shortUrl.getUrl());
    }
}
