package kr.co.kst.url.domain;

import jakarta.persistence.*;
import kr.co.kst.url.config.Base62Encoder;
import kr.co.kst.url.config.SnowflakeIdGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String url;

    public ShortUrl(String code, String url) {
        this.code = code;
        this.url = url;
    }

    public static ShortUrl create(SnowflakeIdGenerator idGenerator, String url) {
        long snowflakeId = idGenerator.nextId();
        String code = Base62Encoder.encode(snowflakeId);

        return new ShortUrl(code, url);
    }
}
