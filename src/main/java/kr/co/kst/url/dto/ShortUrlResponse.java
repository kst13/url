package kr.co.kst.url.dto;

public record ShortUrlResponse(
        String code,
        String shortUrl,
        String url
){
}
