package com.bilibili.unsafe;

//不安全的取钱
//两个人去银行取钱
public class UnsafeBank {
    public static void main(String[] args) {
        //创建账户
        Account account = new Account(100, "旅行基金");

        new Drawing(account, 50, "你").start();
        new Drawing(account, 100, "wife").start();
    }
}

//账户
class Account{
    int money;
    String name;//卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行：模拟取款
class Drawing extends Thread{

    //账户
    private Account account;
    //取了多少钱
    private int drawingMoney;
    //手里有多少钱
    private int nowMoney;

//    private String name;//取款人
    //name表示线程名称，此例子中即为取款人
    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        if(this.account.money - this.drawingMoney < 0) {
            System.out.println(this.getName() + "钱不够，取不了！");
            return;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.account.money = this.account.money - this.drawingMoney;

        this.nowMoney = this.nowMoney + this.drawingMoney;

        System.out.println(this.account.name + "余额为" + this.account.money );
        System.out.println(this.getName() + "手里有" + this.nowMoney);
    }
}


