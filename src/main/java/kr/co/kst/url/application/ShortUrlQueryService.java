package kr.co.kst.url.application;

import kr.co.kst.url.domain.ShortUrl;
import kr.co.kst.url.domain.ShortUrlRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ShortUrlQueryService {


    private static final long CACHE_TTL = 3600L;

    private final ShortUrlRepository shortUrlRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public ShortUrlQueryService(ShortUrlRepository shortUrlRepository, RedisTemplate<String, String> redisTemplate) {
        this.shortUrlRepository = shortUrlRepository;
        this.redisTemplate = redisTemplate;
    }

    public ShortUrl getUrlByCode(String code) {
        String url = redisTemplate.opsForValue().get(code);

        if(url != null ) {
            return new ShortUrl(code, url);
        }
        ShortUrl shortUrl =  shortUrlRepository.findByCode(code)
                .orElseThrow(IllegalArgumentException::new);
        redisTemplate.opsForValue().set(code, shortUrl.getUrl(), CACHE_TTL, TimeUnit.SECONDS);
        return shortUrl;

    }
}
