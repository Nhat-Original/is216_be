package com.github.nhatoriginal.spring.constant;

public final class Endpoint {
  public static final class Auth {
    public static final String BASE = "/auth";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";
    public static final String HELLO = "/hello";
  }

  public static final class MenuItem {
    public static final String BASE = "/menu-item";
    public static final String GET_ALL = "";
    public static final String GET_ONE = "/{id}";
  }
}
