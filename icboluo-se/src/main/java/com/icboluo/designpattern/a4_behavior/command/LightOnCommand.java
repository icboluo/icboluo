package com.icboluo.designpattern.a4_behavior.command;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @since 2020/11/15 21:27
 */
@AllArgsConstructor
public class LightOnCommand implements Command {

    LightReceiver lightReceiver;

    @Override
    public void execute() {
        lightReceiver.on();
    }

    @Override
    public void undo() {
        lightReceiver.off();
    }
}
