package kr.co.kst.url.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlMessage implements KafkaBaseMessage {
    private String code;
    private String url;

}
