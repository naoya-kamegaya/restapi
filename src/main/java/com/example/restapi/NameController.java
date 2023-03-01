package com.example.restapi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Validated
@RestController
public class NameController {

  @GetMapping("/names")
  public UserResponse getNames(@Valid @NotBlank @Size(max = 19) @RequestParam String name, @RequestParam(required = false) @DateTimeFormat(pattern = "[uuuuMMdd][uuuu-MM-dd]") LocalDate birthday) {
    String birthdayString;
    if (birthday != null) {
      birthdayString = birthday.format(DateTimeFormatter.ofPattern("uuuu/MM/dd"));
    } else {
      birthdayString = "";
    }
    return new UserResponse(name, birthdayString);
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
  public ResponseEntity<Map<String, String>> updateName(@PathVariable("id") int id,
                                                        @RequestBody NameUpdateForm nameUpdateForm) {
    // 更新処理は省略
    return ResponseEntity.ok(Map.of("message", "name successfully updated"));
  }

  @DeleteMapping("/names/{id}")
  public ResponseEntity<Map<String, String>> deleteName(@PathVariable("id") int id) {
    // 削除処理は省略
    return ResponseEntity.ok(Map.of("message", "name successfully deleted"));
  }

}
