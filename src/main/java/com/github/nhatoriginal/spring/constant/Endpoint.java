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
    public static final String CREATE = "";
    public static final String UPDATE = "/{id}";
    public static final String DELETE = "/{id}";

  }
  public  static final class Menu {
    public static final String BASE = "/menu";
    public static final String GET_ALL = "";
    public static final String GET_ONE = "/{id}";
    public static final String CREATE = "";
    public static final String DELETE = "/{id}";
  }

  public static final class User {
    public static final String BASE = "/user";
    public static final String GET_ALL = "";
    public static final String GET_ONE = "/{id}";
  }
  public static final class Eatery {
    public static final String BASE = "/eatery";
    public static final String GET_ONE = "/{id}";
    public static final String CREATE = "";
    public static final String DELETE = "/{id}";


  }
  public static final class Cart {
    public static final String BASE = "/cart";
    public static final String GET_BY_USER_ID = "/user/{userId}";
    public static final String CREATE = "";
    public static final String DELETE = "/{userId}/{menuItemOptionId}";
    public static final String UPDATE_QUANTITY = "/{userId}/{menuItemOptionId}";
  }

  public static final class Review {
    public static final String BASE = "/review";
    public static final String CREATE = "";
  }

  public static final class Payment {
    public static final String BASE = "/payment/user/{id}";
  }

  public static final class Order {
    public static final String BASE = "/order";
    public static final String CREATE = "";
    public static final String GET_BY_USER_ID = "/user/{userId}";
    public static final String GET_ONE = "/{id}";
  }

  public static final class Address {
    public static final String BASE = "/address";
    public static final String GET_BY_USER_ID = "/user/{userId}";
    public static final String CREATE = "";
    public static final String GET_ONE = "/{id}";
    public static final String DELETE = "/{id}";
  }
}
