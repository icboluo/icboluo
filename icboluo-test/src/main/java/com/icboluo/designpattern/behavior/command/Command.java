package com.icboluo.designpattern.behavior.command;

/**
 * 命令接口
 *
 * @author icboluo
 * @date 2020/11/15 21:09
 */
public interface Command {

    void execute();

    void undo();
}
