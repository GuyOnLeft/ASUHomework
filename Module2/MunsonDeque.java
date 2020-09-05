/*
Name: Jeremy Munson
Class: SER 222
Date: 08/27/20
Version: 1.0
 */

import java.util.NoSuchElementException;

public class MunsonDeque<Item> implements Deque<Item> {

    // attributes

    private Node<Item> head;

    private Node<Item> tail;

    private int size;

    // default constructor to initialize empty deque

    public MunsonDeque() {

        head = null;

        tail = null;

        size = 0;

    }

    @Override

    public void enqueueFront(Item element) {

        // creating a Node object with element

        Node<Item> node = new Node<Item>(element);

        // setting as head and tail if head is null (empty deque)

        if (head == null) {

            head = node;

            tail = node;

        } else {

            // adding before head node, and updating head node

            node.setNext(head);

            head.setPrev(node);

            head = node;

        }

        size++;

    }

    @Override

    public void enqueueBack(Item element) {

        // creating a Node object with element

        Node<Item> node = new Node<Item>(element);

        // setting as head and tail if tail is null (empty deque)

        if (tail == null) {

            head = node;

            tail = node;

        } else {

            // adding after tail node, and updating tail node

            tail.setNext(node);

            node.setPrev(tail);

            tail = node;

        }

        size++;

    }

    @Override

    public Item dequeueFront() throws NoSuchElementException {

        if (head == null) {

            // empty

            throw new NoSuchElementException("Deque is empty!");

        }

        // getting item at head node, and storing it in a variable

        Item item = head.getItem();

        // updating head node

        head = head.getNext();

        // updating tail node to null if deque became empty

        if (head == null) {

            tail = null;

        } else {

            // removing previous node

            head.setPrev(null);

        }

        size--;

        return item;

    }

    @Override

    public Item dequeueBack() throws NoSuchElementException {

        if (tail == null) {

            // empty

            throw new NoSuchElementException("Deque is empty!");

        }

        // getting item at tail node

        Item item = tail.getItem();

        // updating tail node

        tail = tail.getPrev();

        // updating head and tail if deque became null

        if (tail == null) {

            head = null;

        } else {

            // removing next node of tail

            tail.setNext(null);

        }

        size--;

        return item;

    }

    @Override

    public Item first() throws NoSuchElementException {

        if (head == null) {

            throw new NoSuchElementException("Deque is empty!");

        }

        // getting item at head node

        Item item = head.getItem();

        // returning without removing

        return item;

    }

    @Override

    public Item last() throws NoSuchElementException {

        if (tail == null) {

            throw new NoSuchElementException("Deque is empty!");

        }

        // getting item at tail node and returning

        Item item = tail.getItem();

        return item;

    }

    @Override

    public boolean isEmpty() {

        // deque is empty if head or tail is null

        return head == null || tail == null;

    }

    @Override

    public int size() {

        return size;

    }

    @Override

    public String toString() {

        String result = "";

        if (isEmpty()) {

            // empty

            result = "empty";

        } else {

            // getting last node

            Node<Item> node = tail;

            // appending each node's value to result all the way upto the head

            // node

            while (node != null) {

                result += node.getItem() + " ";

                node = node.getPrev();

            }

        }

        return result;

    }

    /**

     * Program entry point for deque.

     *

     * @param args

     *            command line arguments

     */

    public static void main(String[] args) {

        MunsonDeque<Integer> deque = new MunsonDeque<Integer>();

        // standard queue behavior

        deque.enqueueBack(3);

        deque.enqueueBack(7);

        deque.enqueueBack(4);

        deque.dequeueFront();

        deque.enqueueBack(9);

        deque.enqueueBack(8);

        deque.dequeueFront();

        System.out.println("size: " + deque.size());

        System.out.println("contents:\n" + deque.toString());

        // deque features

        System.out.println(deque.dequeueFront());

        deque.enqueueFront(1);

        deque.enqueueFront(11);

        deque.enqueueFront(3);

        deque.enqueueFront(5);

        System.out.println(deque.dequeueBack());

        System.out.println(deque.dequeueBack());

        System.out.println(deque.last());

        deque.dequeueFront();

        deque.dequeueFront();

        System.out.println(deque.first());

        System.out.println("size: " + deque.size());

        System.out.println("contents:\n" + deque.toString());

    }

}

//class to represent a single Node in Deque

class Node<Item> {

    //attributes

    Item item;

    Node<Item> prev;

    Node<Item> next;



    //constructor taking value for item

    public Node(Item item) {

        this.item = item;

        prev = null;

        next = null;

    }



    //getters and setters



    public Item getItem() {

        return item;

    }

    public void setItem(Item item) {

        this.item = item;

    }

    public Node<Item> getPrev() {

        return prev;

    }

    public void setPrev(Node<Item> prev) {

        this.prev = prev;

    }

    public Node<Item> getNext() {

        return next;

    }

    public void setNext(Node<Item> next) {

        this.next = next;

    }

}