package com.icboluo.designpattern.structure.facade;

/**
 * @author icboluo
 * @date 2020/12/26 16:05
 */
public class Client {
    public static void main(String[] args) {
        HomeTheateFacade homeTheateFacade = new HomeTheateFacade();
        homeTheateFacade.ready();
        homeTheateFacade.play();
        homeTheateFacade.pause();
        homeTheateFacade.end();
    }
}
