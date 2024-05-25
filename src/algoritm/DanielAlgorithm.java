package algoritm;

import java.util.Stack;


public class DanielAlgorithm {

    public static int cont=0;
    
    public NFA regexToNFA(String regex, boolean turn) {
        Stack<NFA> stack = new Stack<>();
        stack.push(new NFA());

        
        for (int i = cont; i < regex.length(); i++) {
            cont++;
            char c = regex.charAt(i);
            switch (c) {
                case '(':

                    stack.peek().concatenate(regexToNFA(regex, true));
                    i=cont;
                    break;

                case ')':

                    return stack.peek();
                    
                case '|':

                    NFA newNFA=regexToNFA(regex, true);
                    stack.peek().alternate(newNFA);
                    i=cont;
                    break;

                case '*':

                    stack.peek().kleeneClosure();
                    break;

                default:

                    NFA basicNFA = new NFA();
                    basicNFA.addTransition(c);

                    if (stack.size()==2) {  
                        stack.peek().concatenate(basicNFA);
                    }else{
                        stack.push(basicNFA);
                    }
                    if(turn){
                        cont++;
                        return stack.peek();
                    }
            }
        }

        return stack.pop();
    }


}
