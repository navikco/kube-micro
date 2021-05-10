package net.playdoh.algo;

import java.util.Stack;

//TC - Linear - o(n)
//SC - Linear - o(n)
//Purpose - Check if Paranthesis are balanced
public class BalancedParanthesis {

    public static void main(String[] args) {

        BalancedParanthesis balancedParanthesis = new BalancedParanthesis();
        System.out.println("Paranthesis Balanced? }()[({})] :::>>> " + balancedParanthesis.isValid("}()[({})]"));
        System.out.println("Paranthesis Balanced? ()[({})] :::>>> " + balancedParanthesis.isValid("()[({})]"));
        System.out.println("Paranthesis Balanced? [({)}] :::>>> " + balancedParanthesis.isValid("[({)}]"));
    }

    public boolean isValid(String s) {

        if (s.length() % 2 != 0)
            return false;

        Stack<String> openStack = new Stack<>();

        for(int i=0; i<s.length(); i++) {

            switch(s.substring(i, i+1)) {
                case "(" :
                    openStack.push("(");
                    break;
                case ")" :
                    if(openStack.empty() || !"(".equals(openStack.pop()))
                        return false;
                    break;
                case "[" :
                    openStack.push("[");
                    break;
                case "]" :
                    if(openStack.empty() || !"[".equals(openStack.pop()))
                        return false;
                    break;
                case "{" :
                    openStack.push("{");
                    break;
                case "}" :
                    if(openStack.empty() || !"{".equals(openStack.pop()))
                        return false;
                    break;
            }
        }

        return openStack.size() == 0;
    }
}
