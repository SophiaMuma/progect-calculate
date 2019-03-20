package main;

//产生题目
public class Maker {

    //Maker数据域
    private main.Question question = new main.Question();
    private Tester tester;
    private NumMaker numMaker = new NumMaker();
    private CharMaker charMaker = new CharMaker();

    //Maker构造器
    Maker(){
    }

    //制造题目
    void makeQuestion(){
        this.question.setNums((int)((Math.random()*3)+2));
        this.question.setNumber(numMaker.makeNumber(question.getNums()));
        this.question.setChars(charMaker.makeChar(question.getNums()));
    }

    //调用Tester对象测试题目
    void testQuestion(){
        tester = new Tester(this.question);
        tester.testQuestion();
        question.setResults(tester.getResult());
    }

    //获取制造的题目
    main.Question getQuestion() {
        return this.question;
    }
}

//随机制造数字类
class NumMaker{
    int[] makeNumber(int nums){
        int[] number = new int[nums+1];
        for(int i = 0;i < nums+1;i++){
            number[i] = (int)(Math.random()*99);
        }
        return number;
    }
}

//随机制造算符类
class CharMaker{
    private char[] box = {'+','-','*','÷'};
    char[] makeChar(int nums){
        char[] chars = new char[nums];
        for(int i = 0;i < nums;i++){
            chars[i] = box[(int)(Math.random()*4)];
        }
        return chars;
    }
}