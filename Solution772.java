class Solution772 {
    public int calculate(String s) {
        char[] arr = s.toCharArray();
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();
        
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            
            if (Character.isDigit(c)) {
                int num = c - '0';
                int j = i + 1;
                while (j < arr.length && Character.isDigit(arr[j])) {
                    num = 10 * num + (arr[j++] - '0');
                }
                numStack.push(num);
                i = j - 1;
            } else if (c == ')') {
                while (opStack.peek() != '(') {
                    numStack.push(cal(numStack.pop(), numStack.pop(), opStack.pop()));
                }
                opStack.pop();
            } else {
                while (!opStack.isEmpty() && precedence(opStack.peek(), c)) {
                    numStack.push(cal(numStack.pop(), numStack.pop(), opStack.pop()));
                }
                opStack.push(c);
            }
        }
        
        while (!opStack.isEmpty()) {
            numStack.push(cal(numStack.pop(), numStack.pop(), opStack.pop()));
        }
        return numStack.pop();
    }
    
    private int cal(int b, int a, char op) {
        int res = 0;
        switch (op) {
            case '+': res = a + b; break;
            case '-': res = a - b; break;
            case '*': res = a * b; break;
            case '/': res = a / b; break;
            default: throw new IllegalArgumentException("invalid op");
        }
        return res;
    }
    
    private boolean precedence(char op1, char op2) {
        if (op1 == '(' || op2 == '(') {
            return false;
        }
        if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return false;
        }
        return true;
    }
}
