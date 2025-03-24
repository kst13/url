package kr.co.kst.url.application;

import kr.co.kst.url.domain.ShortUrl;
import kr.co.kst.url.domain.ShortUrlRepository;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlQueryService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlQueryService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrl getUrlByCode(String code) {
        return shortUrlRepository.findByCode(code)
                .orElseThrow(IllegalArgumentException::new);
    }
}
