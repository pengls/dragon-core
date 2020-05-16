package com.dragon.core.algorithm.queue;

/**
 * @ClassName: CircularQueue
 * @Description: 环形队列
 * @Author: pengl
 * @Date: 2020/5/16 8:43
 * @Version V1.0
 */
public class CircularQueue<E> {
    /**
     * 存放队列元素
     */
    final Object[] eles;
    /**
     * 队首索引
     */
    int head;
    /**
     * 队尾索引
     */
    int tail;
    /**
     * 最大容量
     */
    int capacity;
    /**
     * 当前队列元素数量
     */
    int count;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.tail = 0;
        this.head = 0;
        this.count = 0;
        this.eles = new Object[capacity];
    }

    public boolean offer(E ele) {
        if (ele == null) {
            throw new NullPointerException();
        }
        if (count == capacity) {
            System.out.println("队列已满");
            return false;
        }
        eles[tail] = ele;
        tail = (tail + 1) % capacity;
        count++;
        return true;
    }

    public E poll() {
        if (count == 0) {
            System.out.println("队列中没有数据");
            return null;
        }
        E e = (E) eles[head];
        eles[head] = null;
        head = (head + 1) % capacity;
        count--;
        return e;
    }
}
