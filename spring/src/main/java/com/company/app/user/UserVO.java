package com.company.app.user;

import lombok.Data;

@Data
public class UserVO {
    private String id;
    private String password;
    private String name;
    private String role;
}
