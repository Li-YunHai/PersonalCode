package com.learn.notes.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue：由数组结构组成的有界阻塞队列。
 * LinkedBlockingQueue：由链表结构组成的有界（但大小默认值为Integer.MAX_VALUE）阻塞队列。
 * PriorityBlockingQueue：支持优先级排序的无界阻塞队列。
 * DelayQueue：使用优先级队列实现妁延迟无界阻塞队列。
 * SynchronousQueue：不存储元素的阻塞队列。
 * LinkedTransferQueue：由链表结构绒成的无界阻塞队列。
 * LinkedBlockingDeque：由链表结构组成的双向阻塞队列。
 */
public class BlockingQueueDemo {

    /**
     * 当阻塞队列满时：在往队列中add插入元素会抛出 IIIegalStateException：Queue full
     * 当阻塞队列空时：再往队列中remove移除元素，会抛出NoSuchException
     */
    @Test
    public void testAddAndRemove() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        try {
            //抛出 java.lang.IllegalStateException: Queue full
            System.out.println(blockingQueue.add("XXX"));
        } catch (Exception e) {
            System.err.println(e);
        }

        System.out.println(blockingQueue.element());


        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        try {
            //抛出 java.util.NoSuchElementException
            System.out.println(blockingQueue.remove());
        } catch (Exception e) {
            System.err.println(e);
        }

        try {
            //element()相当于peek(),但element()会抛NoSuchElementException
            System.out.println(blockingQueue.element());
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     * 插入方法，成功true，失败false
     * 移除方法：成功返回出队列元素，队列没有就返回空
     */
    @Test
    public void testOfferAndPoll() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 当阻塞队列满时，生产者继续往队列里put元素，队列会一直阻塞生产线程直到put数据or响应中断退出。
     * 当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用。
     */
    @Test
    public void testPutAndTake() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        new Thread(()->{
            try {
                blockingQueue.put("a");
                blockingQueue.put("b");
                blockingQueue.put("c");
                blockingQueue.put("c");//将会阻塞,直到主线程take()
                System.out.println("it was blocked.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {

            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();

            System.out.println("Blocking...");
            blockingQueue.take();//将会阻塞

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
