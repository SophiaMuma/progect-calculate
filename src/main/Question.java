package main;


import java.util.Arrays;

public class Question {

    //Question实体类数据域
    private int nums;
    private int[] number = new int[nums+1];
    private char[] chars = new char[nums];
    private int results = 20180323;
    private boolean useful = false;

    public Question(){
    }
    //普通的set/get方法
    public void setNumber(int[] number) {
        this.number = number;
    }

    public void setChars(char[] chars) {
        this.chars = chars;
    }

    public int[] getNumber() {
        return number;
    }

    public char[] getChars() {
        return chars;
    }

    private char numToChar(int num){
        String string = String.valueOf(num);
        return string.charAt(0);
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public boolean isUseful() {
        return useful;
    }

    public void setUseful(boolean useful) {
        this.useful = useful;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    //规范化输出题目字符串
    @Override
    public String toString() {
        Object[] box = new Object[nums*2+4];
        box[nums*2+1] = '=';
        box[nums*2+2] = results;
        box[nums*2+3] = "\r\n";
        for(int i = 0; i < nums*2+1;i=i+2){
            box[i] = number[i/2];
        }
        for(int i = 1; i < nums*2;i=i+2){
            box[i] = chars[i/2];
        }
        return Arrays.toString(box).replace('[',' ').replace(']',' ').replace(',',' ');
    }

}
