package com.twoyum.login.domain;
import lombok.Data;

import  java.util.*;
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private List<String> roles;
}
