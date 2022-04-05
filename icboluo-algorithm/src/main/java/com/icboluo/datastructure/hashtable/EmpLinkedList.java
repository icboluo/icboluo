package com.icboluo.datastructure.hashtable;

/**
 * @author icboluo
 * @since 2020/6/14 14:20
 */
public class EmpLinkedList {
    private Employee head;

    public void add(Employee emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Employee curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条前链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表的信息为");
        Employee curEmp = head;
        while (true) {
            System.out.printf("=>id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Employee findById(int id) {
        if (head == null) {
            return null;
        }
        Employee curEmp = head;
        while (curEmp.id != id) {
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
