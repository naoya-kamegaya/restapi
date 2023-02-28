package com.example.restapi;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
public class NameController {

  @GetMapping("/names")
  public List<String> getNames(@RequestParam(required = false) String name, @RequestParam(required = false) @DateTimeFormat(pattern = "[yyyy-MM-dd][yyyyMMdd]") LocalDate birthday) {
    return List.of(name, birthday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
  }

  @PostMapping("/names")
  public ResponseEntity<Map<String, String>> createName(@RequestBody NameCreateForm nameCreateForm) {

    // 登録処理は割愛
    URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
            .path("/names/id")
            .build()
            .toUri();

    return ResponseEntity.created(url).body(Map.of("message", "name successfully created"));
  }

  @PatchMapping("/names/{id}")
  public ResponseEntity<Map<String, String>> updateName(@PathVariable("id") int id, @RequestBody NameUpdateForm nameUpdateForm) {
    // 更新処理は省略
    return ResponseEntity.ok(Map.of("message", "name successfully updated"));
  }

  @DeleteMapping("/names/{id}")
  public ResponseEntity<Map<String, String>> deleteName(@PathVariable("id") int id) {
    // 削除処理は省略
    return ResponseEntity.ok(Map.of("message", "name successfully deleted"));
  }

}
