package com.icboluo.designpattern.behavior.memorandum;

/**
 * 备忘录
 *
 * @author icboluo
 */
public class Client {
    public static void main(String[] args) {
        GameRole gameRole = new GameRole();
        gameRole.setVit(100);
        gameRole.setDef(100);
        System.out.println("before");
        gameRole.display();

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(gameRole.createMemento());

        gameRole.setVit(30);
        gameRole.setDef(30);
        gameRole.display();
        gameRole.recoverGameRoleFromMemento(caretaker.getMemento());
        gameRole.display();
    }
}
