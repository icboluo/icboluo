package com.icboluo.designpattern.behavior.memorandum;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 守护者对象，保持游戏角色的状态
 * @author icboluo
 */
@Data
public class Caretaker {
    /**
     * 如果只保存一次
     */
    private Memento memento;
    /**
     * 保存多次
     */
    private List<Memento> mementos;
    /**
     * 保存多个角色的多个状态
     */
    private Map<String, List<Memento>> roleMementos;


}
