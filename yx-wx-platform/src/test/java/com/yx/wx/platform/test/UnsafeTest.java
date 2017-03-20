package com.yx.wx.platform.test;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.PrivilegedAction;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntUnaryOperator;

/**
 * Created by weiwang207 on 2017/3/14.
 */
public class UnsafeTest<E> {

    private Node<E> head;
    private Node<E> last;

    public UnsafeTest() {
        head = last = new Node<E>(null);
    }

    static class Node<E> {

        E itme;
        Node next;

        public Node(E itme) {
            this.itme = itme;
        }
    }

    public void enqueue(Node<E> node) {
        last = last.next = node;
    }

    public void Show() {
        for (Node<E> node = head.next; node!= null; node = node.next) {
            System.out.println(node.itme);
        }
    }

    public E dequeue() {
        Node<E> first = head.next;
        head.next = null;
        head = first;
        E value = first.itme;
        head = first;
        first.itme = null;
        return value;

//        Node<E> h = head;
//        Node<E> first = h.next;
//        h.next = h; // help GC
//        head = first;
//        E x = first.item;
//        first.item = null;
//        return x;
    }

    public static void main(String[] args) throws Exception {
        UnsafeTest<Integer> test = new UnsafeTest<>();
        test.enqueue(new Node<Integer>(1));
        test.enqueue(new Node<Integer>(2));
        test.enqueue(new Node<Integer>(3));
        test.enqueue(new Node<Integer>(4));

        test.Show();
        test.dequeue();
        test.dequeue();
        test.Show();

    }
}
