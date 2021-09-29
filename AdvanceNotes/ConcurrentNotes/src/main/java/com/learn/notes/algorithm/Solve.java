package com.learn.notes.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Stack;

public class Solve {
    public static void main(String[] args) {
        //System.out.println(JSON.toJSONString(Solve.solve("(3+4)")));
        //System.out.println(JSON.toJSONString(Solve.solve("(3+4)*(5+(2-3))")));
        System.out.println(JSON.toJSONString(Solve.solve("((10+2)*10-(100-(10+20*10-(2*3)))*10*1*2)-2")));
    }

    public static int solve (String str) {
        // write code here
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('*', 2);
        map.put('/', 2);
        map.put('+', 1);
        map.put('-', 1);

        Stack<Integer> numStack = new Stack<Integer>();
        Stack<Character> flagStack = new Stack<Character>();
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                Integer num = Integer.valueOf(str.charAt(i)-'0');
                if(i > 0 && str.charAt(i-1) >= '0' && str.charAt(i-1) <= '9'){
                    num = numStack.pop() * 10 + num;
                }
                numStack.push(num);
            } else if (str.charAt(i) == ')'){
                while(flagStack.peek() != '('){
                    handlerValue(numStack, flagStack);
                }
                if(flagStack.size() != 0 && flagStack.peek() == '('){
                    flagStack.pop();
                }
            } else if (flagStack.size() == 0 || str.charAt(i) == '(' || flagStack.peek() == '('){
                flagStack.push(str.charAt(i));
            } else if (map.get(str.charAt(i)) > map.get(flagStack.peek())){
                flagStack.push(str.charAt(i));
            } else {
                handlerValue(numStack, flagStack);
                flagStack.push(str.charAt(i));
            }
        }
        while(flagStack.size() != 0){
            handlerValue(numStack, flagStack);
        }
        return numStack.pop();
    }

    public static void handlerValue(Stack<Integer> numStack, Stack<Character> flagStack){
        Integer num = null;
        Integer num2 = numStack.pop();
        Integer num1 = numStack.pop();
        Character flag = flagStack.pop();
        if(flag == '*'){
            num = num1 * num2;
        } else if(flag == '/'){
            num = num1 / num2;
        } else if(flag == '+'){
            num = num1 + num2;
        } else {
            num = num1 - num2;
        }
        numStack.push(num);

    }
}
