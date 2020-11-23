package com.icboluo.designpattern.command;

/**
 * @author icboluo
 * @date 2020/11/15 21:34
 */
public class RemoteController {
    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;

    public RemoteController() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    public void setCommand(int no, Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    /**
     * 按下对应的开按钮
     *
     * @param no
     */
    public void onButtonWasPushed(int no) {
        onCommands[no].execute();
//        记录用于撤销
        undoCommand = onCommands[no];
    }

    public void offButtonWasPushed(int no) {
        offCommands[no].execute();
//        记录用于撤销
        undoCommand = offCommands[no];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }
}
