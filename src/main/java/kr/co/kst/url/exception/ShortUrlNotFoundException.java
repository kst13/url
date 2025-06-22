package kr.co.kst.url.exception;

public class ShortUrlNotFoundException extends RuntimeException {
    public ShortUrlNotFoundException(String code) {
        super("Short URL not found for code : "+ code);
    }
}
