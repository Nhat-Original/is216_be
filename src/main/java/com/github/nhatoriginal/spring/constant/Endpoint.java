package com.github.nhatoriginal.spring.constant;

public final class Endpoint {
  public static final String BASE = "";

  public static final class Auth {
    public static final String BASE = "/auth";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";
  }

  public static final class MenuItem {
    public static final String BASE = "/menu-item";
    public static final String GET_ALL = "";
    public static final String GET_ONE = "/{id}";
  }
  public static final class User {
    public static final String BASE = "/user";
    public static final String GET_ALL = "";
    public static final String GET_ONE = "/{id}";

  }

  public static final class Cart {
    public static final String BASE  = "/cart" ;
    public static final String GET_ALL = "/user/{id}";
    public static final String DELETE = "/user/{id}";
  }

  public static final class Payment {
    public static final String BASE = "/payment/user/{id}";
  }

}
