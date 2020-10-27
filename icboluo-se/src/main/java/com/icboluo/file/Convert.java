package com.icboluo.file;

class Convert {
    public static void main(String[] args) {
        System.out.println('中');
        System.out.println('中' + 0);
        System.out.println('中' % 256);
        //模运算之后按照ascii码表输出
        System.out.println((char) ('中' % 256));
    }
}
