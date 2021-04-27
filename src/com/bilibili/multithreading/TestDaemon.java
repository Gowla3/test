package com.bilibili.multithreading;

/**
 * 守护线程：线程分为用户线程和守护线程
 * 常见的用户线程比如main线程
 * 常见的守护线程比如gc线程
 * 虚拟机在用户线程结束之后，不需要等待守护线程结束即可退出
 * */
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread godThread = new Thread(god);
        godThread.setDaemon(true);//设置上帝线程为守护线程

        godThread.start();//上帝守护线程启动

        new Thread(you).start();//用户线程启动
    }
}
//上帝
class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("上帝一辈子保护你！！！");
        }
    }
}

//你
class You implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都开心的活着");
        }
        System.out.println("=====Good by, world!=====");
    }
}
