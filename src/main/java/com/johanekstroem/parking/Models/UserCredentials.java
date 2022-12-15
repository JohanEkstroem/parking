package com.johanekstroem.parking.Models;

public class UserCredentials {
  private String userName;
  private String password;
  private String scope;

  public String getUserName() {
    return userName;
  }
  public void setUserName(String name) {
    this.userName = name;
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
  }
  
}
