package com.icboluo.designpattern.behavior.memorandum;

import lombok.Setter;

/**
 * 游戏角色
 *
 * @author icboluo
 */
@Setter
public class GameRole {

    private int vit;

    private int def;

    public Memento createMemento() {
        return new Memento(vit, def);
    }

    public void recoverGameRoleFromMemento(Memento memento) {
        this.vit = memento.getVit();
        this.def = memento.getDef();
    }

    public void display() {
        System.out.println(vit + " " + def);
    }
}
