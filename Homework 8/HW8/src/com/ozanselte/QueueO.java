package com.ozanselte;

public class QueueO {

    private Node head;
    private Node bottom;

    static private class Node {

        private Integer num;
        private Node next;

        public Node(Integer num) {
            this(num, null);
        }

        public Node(Integer num, Node next) {
            setNum(num);
            setNext(next);
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public QueueO() {
        head = null;
        bottom = null;
    }

    public void add(Integer num) {
        Node temp = new Node(num);
        if(null == head) {
            head = temp;
        }
        if(null == bottom) {
            bottom = temp;
        }
        else {
            bottom.setNext(temp);
            bottom = temp;
        }
    }

    public Integer remove() {
        if(null == head) {
            return null;
        }
        int num = head.getNum();
        head = head.getNext();
        if(null == head) {
            bottom = null;
        }
        return num;
    }

    public Integer peek() {
        if(null == head) {
            return null;
        }
        return head.getNum();
    }
}
