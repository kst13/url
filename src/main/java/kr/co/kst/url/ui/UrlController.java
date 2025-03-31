package kr.co.kst.url.ui;

import kr.co.kst.url.application.ShortUrlCommandService;
import kr.co.kst.url.application.ShortUrlQueryService;
import kr.co.kst.url.domain.ShortUrl;
import kr.co.kst.url.dto.ShortUrlResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UrlController {
    private final ShortUrlCommandService commandService;
    private final ShortUrlQueryService queryService;

    public UrlController(ShortUrlCommandService commandService, ShortUrlQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping("text")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("success");
    }

    @GetMapping("get")
    public ResponseEntity<String> get(@RequestParam String code) {
        ShortUrl shortUrl = queryService.getUrlByCode(code);
        return ResponseEntity.ok(shortUrl.getUrl());
    }


    @PostMapping("create")
    public ResponseEntity<ShortUrlResponse> create(@RequestBody String url) {
        ShortUrlResponse response = commandService.create(url);
        return ResponseEntity.ok(response);
    }

}
