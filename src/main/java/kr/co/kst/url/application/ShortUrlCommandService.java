package kr.co.kst.url.application;

import kr.co.kst.url.config.SnowflakeIdGenerator;
import kr.co.kst.url.domain.ShortUrl;
import kr.co.kst.url.domain.ShortUrlRepository;
import kr.co.kst.url.dto.ShortUrlResponse;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlCommandService {

    private static final String DOMAIN = "https://short.ly";

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlCommandService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrlResponse create(String url) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1,1);

        ShortUrl shortUrl = ShortUrl.create(idGenerator, url);
        shortUrlRepository.save(shortUrl);

        String result = DOMAIN + "/" + shortUrl.getCode();

        return new ShortUrlResponse(shortUrl.getCode(), result, shortUrl.getUrl());
    }
}
