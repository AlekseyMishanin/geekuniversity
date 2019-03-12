package algorithm.lesson3;

/**
 * Класс для разбора строки на наличие парных скобок.
 * */
public class Expression {

    private String str;

    public Expression(String str){
        this.str=str;
    }

    public boolean chackBrackets(){

        MyStackArr<Character> stack = new MyStackArr<>(Character.class);
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch=='{'||ch=='['||ch=='('){
                stack.push(ch);
            }else if(ch=='}'||ch==']'||ch==')'){
                if(stack.isEmpty()){
                    System.out.println("Error in " + i + " position.");
                    return false;
                }
                char locCh = stack.pop();
                if(ch=='}'&&locCh!='{'||
                        ch==']'&&locCh!='['||
                        ch==')'&&locCh!='('){
                    System.out.println("Error in " + i + " position.");
                    return false;
                }
            }
        }
        if(!stack.isEmpty()){
            System.out.println("Error: brackets doesn't match.");
            return false;
        }
        return true;
    }
}
