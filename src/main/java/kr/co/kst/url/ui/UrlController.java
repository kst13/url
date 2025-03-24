package kr.co.kst.url.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UrlController {

    @GetMapping("get")
    public ResponseEntity<String> get(@RequestParam String code) {
        return ResponseEntity.ok("");
    }


    @PostMapping("create")
    public ResponseEntity<Void> create(@RequestBody String url) {
        return ResponseEntity.ok().build();
    }

}
