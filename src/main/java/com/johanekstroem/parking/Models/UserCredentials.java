package com.johanekstroem.parking.Models;

public class UserCredentials {
  private String username;
  private String password;
  private String scope;

  public String getUsername() {
    return username;
  }
  public void setUsername(String name) {
    this.username = name;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getScope() {
    return scope;
  }
  public void setScope(String scope) {
    this.scope = scope;
  }
  public UserCredentials() {
    this.scope = "read write";
  }
  
}
