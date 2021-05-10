package com.revature.project0.util;

/**
 * A simple implementation of a doubly linked-list structure that
 * does not accept null data.
 *
 * @param <T>
 */

public class LinkedList<T> implements Queue<T>, List<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    /**
     * Add generic type into linked list, adding to end of list.
     *
     */
    @Override
    public void add(T data) throws IllegalArgumentException{

        if (data == null){
            throw new IllegalArgumentException("This linked list does not accept null values.");
        }

        Node<T> newNode = new Node<T>(data);

        if (head == null) {
            tail = head = newNode; // sets both the head and tail equal to the new node
        } else {
            tail.nextNode = newNode;
            newNode.prevNode = tail;
            tail = newNode;
        }

        size++;

    }


    /**
     * Get generic data from node in linked list.
     *
     * @return T
     */
    @Override
    public T get(int index) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("The provided index would be out of bounds.");
        }

        Node<T> runner = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return runner.data;
            }
            runner = runner.nextNode;
        }

        return null;

    }


    /**
     * Add generic type into linked list.
     *
     * @return boolean
     */
    @Override
    public boolean contains(T data)  throws IllegalArgumentException {

        if (data == null){
            throw new IllegalArgumentException("This linked list does not accept null values.");
        }

        Node<T> runner = head;
        for (int i = 0; i < size; i++) {
            if(i == 0){
                runner = head;
            }else{
                runner = runner.nextNode;
            }

            if (runner.data.equals(data)){
                return true;
            }
        }
        return false;

    }


    /**
     * Get size of linked list.
     *
     * @return int
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Returns and removes the head node's data or else returns null.
     *
     * @return T
     */
    @Override
    public T poll() {

        if (head == null) {
            return null;
        }

        T soughtData = head.data;
        head = head.nextNode;

        if (head != null) {
            head.prevNode = null;
        } else {
            tail = null;
        }

        size--;

        return soughtData;

    }


    /**
     * Returns generic data in head of linked list.
     *
     * @return T
     */
    @Override
    public T peek() {

        return head.data;

    }


    /**
     * Retrieves and removes the argument data in linked list.
     * Returns null if no data found.
     *
     * @return T
     */
    @Override
    public T remove(T data)  throws IllegalArgumentException {

        if (data == null){
            throw new IllegalArgumentException("This linked list does not accept null values.");
        }

        if (size == 0){
            throw new IllegalArgumentException("This linked list does not have any data.");
        }


        for(Node<T> runner = head; !runner.equals(null); runner = runner.nextNode){
            if(runner.data.equals(data)){
                if(runner.equals(head)){
                    head = head.nextNode;
                    head.prevNode = null;
                    size--;
                    return head.data;
                } else if(runner.equals(tail)){
                    tail = tail.prevNode;
                    tail.nextNode = null;
                    size--;
                    return tail.data;
                } else {//make nodes adjacent to current node (runner) link to each other
                    //this will make the runner node null when this function ends
                    runner.nextNode.prevNode = runner.prevNode;
                    runner.prevNode.nextNode = runner.nextNode;
                    size--;
                    return runner.data;
                }
            }
        }//end for loop

        return null;//no data removed


        /*
        Node<T> runner = new Node<> (data);
        for(Node<T> temp = head; temp != null; temp = temp.nextNode){
            if(temp.getData() == runner.getData()){
                if(temp == head){
                    head=temp.nextNode;
                    head.prevNode = null;
                }
                else if(temp == tail){
                    tail=temp.prevNode;
                    temp.nextNode = null;
                }
                else {
                    temp.nextNode.prevNode = temp.prevNode;
                    temp.prevNode.nextNode = temp.nextNode;
                }
                size--;
                break;
            }
        }

         */
    }


    private static class Node<T> {

        T data;
        Node<T> nextNode; // defaults to null
        Node<T> prevNode; // defaults to null

        Node(T data) {
            this.data = data;
        }

        Node(T data, Node<T> nextNode, Node<T> prevNode) {
            this.data = data;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }

    }

}
