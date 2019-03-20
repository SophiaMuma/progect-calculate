import main.Outer;
import java.util.Scanner;

public class Main {
    static int times;//输入的题目数

    public static void main(String[] args) {
        try {
            times = Integer.valueOf(args[0]);//字符串为整型变量
            times = intNumber(times);//用intNumber判断数的范围
        }catch (Exception e){
            System.out.println("请输入一个1~100的数字。");
            times = inPut();
        }
        //异常处理，如果输入不符合整型和intNumber对数范围的规定，就catch错误，输出提示并调用inPut()重新输入
        Outer outer = new Outer(times);//输出调用
    }

    private static int inPut(){
        try {
            Scanner input = new Scanner(System.in);//获取输入数
            times = input.nextInt();//控制台输入说赋给times
            times = intNumber(times);
        }catch (Exception e){
            System.out.println("输入格式不正确，请输入一个1~100的数字。");
            inPut();
        }//异常处理，用于判断输入是否处于1~100，如果还不符合再调用自己
        return times;
    }
    private static int intNumber(int num){
        if(num >= 1&&num <=100){
            return num;
        }//判断输入数是否符合要求的方法
        else {
            throw new RuntimeException();//异常处理，抛出运行时异常
        }
    }
}
