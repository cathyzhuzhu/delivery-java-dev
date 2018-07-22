package com.twoyum.login.domain;

import lombok.Data;
import  java.util.List;

@Data
public class TreeMenu {
    private String id;
    private String name;
    private String path;
    private List<ChildrenMenu> children;
}
