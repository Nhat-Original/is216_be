package com.github.nhatoriginal.spring.config;

import com.github.nhatoriginal.spring.model.MenuItemOption;
import com.github.nhatoriginal.spring.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CartIdClassConfig  implements Serializable {
    private User user;
    private MenuItemOption menuItemOption;
}
