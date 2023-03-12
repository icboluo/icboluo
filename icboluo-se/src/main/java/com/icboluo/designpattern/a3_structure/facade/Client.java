package com.icboluo.designpattern.a3_structure.facade;

/**
 * @author icboluo
 * @since 2020/12/26 16:05
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
