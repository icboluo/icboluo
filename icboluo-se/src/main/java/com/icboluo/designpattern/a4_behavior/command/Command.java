package com.icboluo.designpattern.a4_behavior.command;

/**
 * 命令接口
 *
 * @author icboluo
 * @since 2020/11/15 21:09
 */
public interface Command {

    void execute();

    void undo();
}
