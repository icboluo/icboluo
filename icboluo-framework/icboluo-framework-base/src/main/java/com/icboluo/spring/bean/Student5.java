package com.icboluo.spring.bean;

import lombok.ToString;

import java.util.*;

/**
 * @author icboluo
 */
@ToString
public class Student5 {
    private String[] myStrs;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String, Object> myMap;
    private Properties myProps;

    public void setMyStrs(String[] myStrs) {
        this.myStrs = myStrs;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, Object> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }
}
