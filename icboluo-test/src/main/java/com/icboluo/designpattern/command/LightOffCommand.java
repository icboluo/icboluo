package com.icboluo.designpattern.command;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @date 2020/11/15 21:27
 */
@AllArgsConstructor
public class LightOffCommand implements Command {

    LightReceiver lightReceiver;

    @Override
    public void execute() {
        lightReceiver.off();
    }

    @Override
    public void undo() {
        lightReceiver.on();
    }
}
