package com.example.restapi;

public class UserResponse {
  private String name;
  private String birthday;

  public UserResponse(String name, String birthday) {
    this.name = name;
    this.birthday = birthday;
  }

  public String getName() {
    return name;
  }

  public String getBirthday() {
    return birthday;
  }
}
