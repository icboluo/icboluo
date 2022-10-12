package com.icboluo.plane2.MyDataStructrue;

public class MyArrayList<V> {
    private Object[] objects = {};
    private int size ;
    private  final int deflength = 10;
    private int length ;

    public MyArrayList() {
        init();
    }
    public void  init(){
        objects = new Object[deflength];
        size = 0;
        length = deflength;
    }

    public MyArrayList(int initCapacity){
        if(initCapacity < 0){
            throw new IllegalArgumentException("initCapacity must be greater than 0");
        }

        if(initCapacity<=10){
            init();
        }

        if(initCapacity>10){
            length = initCapacity;
            size = 0;
            objects = new Object[initCapacity];
        }

    }

    public synchronized void add( V  v){
        if(size >= length){
            grow();
        }
        objects[size++] = v;

    }

    public synchronized void grow(){
        int oldlength = length;
        int newlength = oldlength + oldlength/2;
        Object[] newobjects = new Object[newlength];
        for (int i = 0; i < oldlength; i++) {
            newobjects[i] = objects[i];
        }
        length = newlength;
        objects = newobjects; //更新对象数组引用；

    }

    public synchronized boolean remove(V v){
        boolean result = false;
        for (int i = 0; i < size ; i++) {
            if(v.equals(objects[i])){
                result = true;
                remove(i);
            }
        }
        return result;
    }

    public synchronized void remove(int index){
        for (int i = index; i < size-1; i++) {
            objects[i] = objects[i+1];
        }
        if(size>=1){
            objects[size-1] = new Object();
            size--;
        }

    }

    public synchronized V get(int index){
        if(index<0 || index >= size){
            throw new IndexOutOfBoundsException("Index："+ index + ",Size : "+ size);
        }

        return (V)objects[index];
    }

    public synchronized int getIndexOf(V v){
        for (int i = 0; i < size; i++) {
            if(objects[i].equals(v)){
                return i;
            }
        }
        return -1;
    }
    public synchronized int size(){
        return size;
    }
    public synchronized void clear(){
        for (int i = 0; i < size; i++) {
            objects[i] = null;
            size = 0;
        }
    }
}
