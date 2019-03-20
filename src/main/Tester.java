package main;

import java.util.ArrayList;
import java.util.List;


public class Tester {

    //Tester的数据域
    private main.Question questionTest;
    private Box box;

    //Tester构造器
    Tester(main.Question question){
        this.questionTest = question;
        int[] number = questionTest.getNumber();
        char[] chars = questionTest.getChars();
        List<Integer> listN = new ArrayList<>();
        List<Character> listC = new ArrayList<>();

        for (int i = 0;i < questionTest.getNums()+1;i++){
            listN.add(number[i]);
        }
        for (int i = 0;i < questionTest.getNums();i++){
            listC.add(chars[i]);
        }
        box = new Box(listN,listC);
    }

    //策略模式的方法
    private Box function(Function f, Box box){
        return f.run(box);
    }

    //计算题目的答案
    private int getResult(Box box){
        //用于判断优先级的部分
        if(box.listC.indexOf('*')>box.listC.indexOf('÷'))
            box = function(new Multiplication(),box);
        else if(!(box.listC.indexOf('÷')==(-1)))
            box = function(new Division(),box);
        else if(box.listC.get(0) == '+')
            box = function(new Plus(),box);
        else if(box.listC.get(0) == '-'){
            box = function(new Subtraction(),box);
        }
        //实现递归的部分
        if(box.listC.isEmpty()){
            if(box.listN.get(0)==-1)
                questionTest.setUseful(false);
            return box.listN.get(0);
        }
        else {
            return getResult(box);
        }
    }

    //测试题目是否可用并更改题目的可用状态
    boolean testQuestion(){
        Box box1 = this.box;
        questionTest.setResults(getResult(box1));
        if(questionTest.getResults()>0&&!(questionTest.getResults()==20180323)){
            questionTest.getResults();
            questionTest.setUseful(true);
        }
        return questionTest.isUseful();
    }

    //获取运算后的答案
    int getResult(){
        return questionTest.getResults();
    }

    //遗留的未实现方法
    public main.Question fixQuestion(){
        return questionTest;
    }
}

//策略模式--------------------------------------------|
class Function{
    Box run(Box box){
        return box;
    }
}

//乘法运算部分
class Multiplication extends Function{
    @Override
    Box run(Box box){
        box.listN.set(box.listC.indexOf('*'),box.listN.get(box.listC.indexOf('*'))*box.listN.get(box.listC.indexOf('*')+1));
        box.listN.remove(box.listC.indexOf('*')+1);
        box.listC.remove(box.listC.indexOf('*'));
        return box;
    }
}

//除法运算部分
class Division extends Function{
    @Override
    Box run(Box box) {
        if(!(box.listN.get(box.listC.indexOf('÷')+1)==0)&&box.listN.get(box.listC.indexOf('÷'))%box.listN.get(box.listC.indexOf('÷')+1) == 0){
            box.listN.set(box.listC.indexOf('÷'),box.listN.get(box.listC.indexOf('÷'))/box.listN.get(box.listC.indexOf('÷')+1));
            box.listN.remove(box.listC.indexOf('÷')+1);
            box.listC.remove(box.listC.indexOf('÷'));
        }
        else{
            //答案非法，直接停止递归
            box.listC.clear();
            box.listN.set(0,-1);
            return box;
        }
        return box;
    }
}

//加法部分
class Plus extends Function{
    @Override
    Box run(Box box) {
        box.listN.set(box.listC.indexOf('+'),box.listN.get(box.listC.indexOf('+'))+box.listN.get(box.listC.indexOf('+')+1));
        box.listN.remove(box.listC.indexOf('+')+1);
        box.listC.remove(box.listC.indexOf('+'));
        return box;
    }
}

//减法运算部分
class Subtraction extends Function{
    @Override
    Box run(Box box) {
        if(box.listN.get(box.listC.indexOf('-'))-box.listN.get(box.listC.indexOf('-')+1) > 0) {
            box.listN.set(box.listC.indexOf('-'), box.listN.get(box.listC.indexOf('-')) - box.listN.get(box.listC.indexOf('-') + 1));
            box.listN.remove(box.listC.indexOf('-') + 1);
            box.listC.remove(box.listC.indexOf('-'));
        }
        else {
            //答案非法，直接停止递归
            box.listC.clear();
            box.listN.set(0,-1);
            return box;
        }
        return box;
    }
}
//策略模式结束----------------------------------------|

//封装数字与运算符的盒子（Box...）
class Box{
    List<Integer> listN = new ArrayList<>();
    List<Character> listC = new ArrayList<>();

    Box(List<Integer> listN,List<Character> listC){
        this.listC = listC;
        this.listN = listN;
    }
}
