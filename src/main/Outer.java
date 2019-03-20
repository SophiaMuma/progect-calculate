package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//题目输出
public class Outer {

    public static final String NAME = "孙文欣";
    public static final String ID = "2017012236\r\n ";
    //定义两个全局常量姓名和学号

    //Outer数据域
    private String[] boxs;
    private main.Maker maker = new main.Maker();
    private int times;

    //Outer构造器
    public Outer(int times){
        this.times = times;
        this.boxs = new String[times+1];
        boxs[0] = ID;
        for (int  i = 1;i <= times;){
            maker.makeQuestion();
            maker.testQuestion();
            if(!maker.getQuestion().isUseful())
                continue;
            String box = maker.getQuestion().toString();
            boxs[i] = box;
            i++;
        }
        //在这里写入"result.txt"文件
        outQuestion(boxs);
    }

    //产生“result.txt”的方法。。看不懂的现盗现卖QAQ
    private boolean outQuestion(String[] list){
        File file = new File("result.txt");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            for (int i = 0;i <= times;i++) {
                outputStream.write(list[i].getBytes());
            }
            outputStream.close();
            return true;
        } catch (IOException e) {
            System.out.println("I/O错误");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("输出模块错误");
            e.printStackTrace();
            return false;
        }
    }
}
