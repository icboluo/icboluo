package com.icboluo.datastructure.hashtable;

/**
 * @author icboluo
 * @date 2020/6/14 14:31
 */
public class HashTab {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Employee emp) {
        int empLinkedListNo = hashFun(emp.id);
        empLinkedLists[empLinkedListNo].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public void findById(int id) {
        int empLinkedListNo = hashFun(id);
        Employee emp = empLinkedLists[empLinkedListNo].findById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到雇员id=%d\n", empLinkedListNo + 1, id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    private int hashFun(int id) {
        return id % size;
    }
}
