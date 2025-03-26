package kr.co.kst.url.config;

import kr.co.kst.url.domain.ShortUrl;
import kr.co.kst.url.domain.ShortUrlMessage;
import kr.co.kst.url.domain.ShortUrlRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlConsumer {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlConsumer(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @KafkaListener(topics = "short-url-topic", groupId = "short-url-group")
    public void consume(ShortUrlMessage shortUrlMessage) {
        ShortUrl shortUrl = new ShortUrl(shortUrlMessage.getCode(), shortUrlMessage.getUrl());
        shortUrlRepository.save(shortUrl);

    }

}
