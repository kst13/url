package kr.co.kst.url.application;

import kr.co.kst.url.config.SnowflakeIdGenerator;
import kr.co.kst.url.domain.ShortUrl;
import kr.co.kst.url.domain.ShortUrlRepository;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlCommandService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlCommandService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public void create(String url) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1,1);

        ShortUrl shortUrl = ShortUrl.create(idGenerator, url);
        shortUrlRepository.save(shortUrl);
    }
}
