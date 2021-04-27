package com.bilibili.unsafe;

//线程不安全
//多个线程拿到同一张票，并且有负数票出现
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station,"苦逼的你").start();
        new Thread(station,"牛逼的你们").start();
        new Thread(station,"可恶的黄牛党").start();
    }
}

class BuyTicket implements Runnable{

    private int ticketNumber = 10;
    private boolean flag = true;

    @Override
    public void run() {
        while(flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void buy() throws InterruptedException {
        if (ticketNumber <= 0) {
            flag = false;
            return;
        }

        Thread.sleep(100);//模拟网络延时

        System.out.println(Thread.currentThread().getName() +"拿到了" + ticketNumber--);
    }
}
