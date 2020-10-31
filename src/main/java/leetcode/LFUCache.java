package leetcode;

import java.util.*;

//https://leetcode.com/problems/lfu-cache/submissions/
/*
corner cases:
insert under the same key doesnt increase size
keys per frequence could be multiple
need to remove the node when keys empty
tail might not exist
 */

class LFUCache {

    private Map<Integer, CacheItem> cache = new HashMap<>();
    private int size = 0;
    private final int cap;
    private Node tail;

    public LFUCache(int capacity) {
        cap = capacity;
    }

    public int get(int key) {
        CacheItem item = cache.get(key);
        if ((item) != null) {
            CacheItem newItem = increaseFrequency(item.value, item.key, item.node);
            cache.put(key, newItem);
            return item.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cap == 0) return;

        CacheItem item = cache.get(key);
        CacheItem newItem;
        if (item != null) {
            newItem = increaseFrequency(value, item.key, item.node);
        } else {
            if (size == cap) {
                evict();
            }
            newItem = increaseFrequency(value, key);
            size++;
        }
        cache.put(key, newItem);
    }

    private CacheItem increaseFrequency(int value, int key, Node node) {
        Node previous = node.previous;
        Node newNode;
        if (previous == null) {
            newNode = new Node(node.frequency + 1);
            node.previous = newNode;
            newNode.next = node;
        } else if (previous.frequency == node.frequency + 1) {
            newNode = previous;
        } else {
            newNode = new Node(node.frequency + 1);
            newNode.next = node;
            newNode.previous = previous;

            node.previous = newNode;
            previous.next = newNode;
        }

        newNode.keys.add(key);
        node.keys.remove(key);
        if (node.keys.isEmpty()) removeNode(node);
        return new CacheItem(value, key, newNode);
    }

    private CacheItem increaseFrequency(int value, int key) {
        Node newNode;
        if (tail != null && tail.frequency == 0) {
            tail.keys.add(key);
            newNode = tail;
        } else {
            newNode = new Node(0);
            newNode.keys.add(key);
            if (tail != null) {
                newNode.previous = tail;
                tail.next = newNode;
            }
            tail = newNode;
        }
        return new CacheItem(value, key, newNode);
    }

    private void removeNode(Node node) {
        if (tail == node) tail = node.previous;
        Node pr = node.previous;
        Node nx = node.next;
        if (pr != null) pr.next = nx;
        if (nx != null) nx.previous = pr;
    }

    private void evict() {
        int key;
        if (tail.keys.size() > 1) {
            Iterator<Integer> it = tail.keys.iterator();
            key = it.next();
            it.remove();
        } else {
            key = tail.keys.iterator().next();
            tail = tail.previous;
        }
        cache.remove(key);
        size--;
    }

    class CacheItem {
        int value;
        int key;
        Node node;

        public CacheItem(int value, int key, Node node) {
            this.value = value;
            this.key = key;
            this.node = node;
        }
    }

    class Node {
        int frequency;
        Set<Integer> keys;
        Node previous;
        Node next;

        public Node(int frequency) {
            this.frequency = frequency;
            keys = new LinkedHashSet<>();
        }
    }
}

//frequencies are stored in heads separate map
class LFUCache2 {

    int size = 0;
    int capacity = 0;
    int minFreq = 0;
    Map<Integer,Node> mapOfNodes = new HashMap<>();
    Map<Integer,DoublyLinkedList> mapOfHeads = new HashMap<>();

    public LFUCache2(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!mapOfNodes.containsKey(key))
            return -1;
        Node keyNode = mapOfNodes.get(key);
        handleRemoval(keyNode);
        handleInsertion(keyNode);
        return keyNode.val;

    }

    public void put(int key, int value) {
        if(capacity != 0){
            if(mapOfNodes.containsKey(key)){
                Node curr = mapOfNodes.get(key);
                curr.val = value;
                handleRemoval(curr);
                handleInsertion(curr);
                return;
            }
            Node keyNode = new Node(key,value);
            if(size == capacity){
                DoublyLinkedList curr = mapOfHeads.get(minFreq);
                handleRemoval(curr.head); //removing the least recently used among the least frequency used
            }
            handleInsertion(keyNode);
        }

    }

    private void handleRemoval(Node curr){
        if(curr.prev == null && curr.next == null){ //handles if this is the only node in the doubly linked list
            mapOfHeads.remove(curr.freq);
            minFreq = curr.freq == minFreq ? curr.freq+1 : minFreq; //update the minima if the curr node freq is the minima and is last in it's list
        }
        else if(curr.prev == null){ //hanldes if it's the head node
            mapOfHeads.get(curr.freq).head = curr.next;
            curr.next.prev = null;
        }
        else if(curr.next == null){ //handles if it's the tail node
            mapOfHeads.get(curr.freq).tail = curr.prev;
            curr.prev.next = null;
        }
        else{//handles if it's a node anywhere in the middle
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
        mapOfNodes.remove(curr.key);
        size--;
    }

    private void handleInsertion(Node insert){

        insert.freq+=1;
        minFreq = minFreq == 0 ? insert.freq : Math.min(insert.freq,minFreq);

        if(!mapOfHeads.containsKey(insert.freq)){
            DoublyLinkedList dll = new DoublyLinkedList(insert,insert);
            mapOfHeads.put(insert.freq,dll);
            insert.prev = null;
            insert.next = null;
        }
        else{
            DoublyLinkedList curr = mapOfHeads.get(insert.freq);
            curr.tail.next = insert;
            insert.prev = curr.tail;
            insert.next = null;
            curr.tail = insert;
        }
        mapOfNodes.put(insert.key,insert);
        size++;
    }


    /*****************Classes*********************/
    class Node{
        Node prev;
        Node next;
        int key;
        int val;
        int freq = 0;
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }
    class DoublyLinkedList{
        Node head; //least recently used will be the head
        Node tail;
        public DoublyLinkedList(Node head,Node tail){
            this.head = head;
            this.tail = tail;
        }

    }
}