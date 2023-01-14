//739. Daily Temperatures
//Input: temperatures = [73,74,75,71,69,72,76,73]
//Output: [1,1,4,2,1,1,0,0]
//while loop ki is lia nhi count kar rhe kuki for loop n time chale rha or while 
//bas n chal rha jab for loop pura chalta hia tab is lia n hi hui
//n2 tab hoti hai jan 1 iternation ke lia n chale to n2
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        LinkedList<Integer>s=new LinkedList<>();
        int[]ans=new int[temperatures.length];
        s.addFirst(0);
        for(int i=1;i<temperatures.length;i++){
            while(s.size()>0 && temperatures[s.peek()]<temperatures[i]){
                ans[s.peek()]=i-s.peek();//diff is lia kuki question me bola tha
                s.removeFirst();
            }
            s.addFirst(i);
        }
        return ans;
    }
}
//Maximum Difference
// Input : arr[] = {2, 1, 8}
// Output : 1
// Left smaller  LS[] {0, 0, 1}
// Right smaller RS[] {1, 0, 0}
// Maximum Diff of abs(LS[i] - RS[i]) = 1 

class Solution
{
    int findMaxDiff(int temperatures[], int n)
    {
        int max=-(int)1e9;
	   LinkedList<Integer>s=new LinkedList<>();
       int[]ans=new int[temperatures.length];
        s.addFirst(0);
        for(int i=1;i<temperatures.length;i++){
            while(s.size()>0 && temperatures[s.peek()]>temperatures[i]){
                ans[s.peek()]=temperatures[i];
                s.removeFirst();
            }
            s.addFirst(i);
        }
      s=new LinkedList<>();
      int[]a=new int[temperatures.length];
        s.addFirst(temperatures.length-1);
        for(int i=temperatures.length-2;i>=0;i--){
            while(s.size()>0 && temperatures[s.peek()]>temperatures[i]){
                a[s.peek()]=temperatures[i];
                s.removeFirst();
            }
            s.addFirst(i);
        }
        for(int i=0;i<ans.length;i++){
            max=Math.max(max,Math.abs(ans[i]-a[i]));
        }
        return max;
    }
}
// Largest Rectangle in Histogram
class Solution {

    public int largestRectangleArea(int[] temperatures) {
        int max = -(int) 1e9;
        LinkedList<Integer> s = new LinkedList<>();
        int[] ans = new int[temperatures.length];
        Arrays.fill(ans,temperatures.length);
        s.addFirst(0);
        for (int i = 1; i < temperatures.length; i++) {
            while (s.size() > 0 && temperatures[s.peek()] > temperatures[i]) {
                ans[s.peek()] = i;
                s.removeFirst();
            }
            s.addFirst(i);
        }
        s = new LinkedList<>();
        int[] a = new int[temperatures.length];
        Arrays.fill(a,-1);
        s.addFirst(temperatures.length - 1);
        for (int i = temperatures.length - 2; i >= 0; i--) {
            while (s.size() > 0 && temperatures[s.peek()] > temperatures[i]) {
                a[s.peek()] = i;
                s.removeFirst();
            }
            s.addFirst(i);
        }
        for (int i = 0; i < ans.length; i++) {
            max = Math.max(max,Math.abs(ans[i]-a[i]-1)*temperatures[i]);
            
        }
        return max;
    }
}

//85. Maximal Rectangle
//Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
//Output: 6
//is me recusion se is lia nhi kia kuki rectangle mention karna hai is lia
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int[][] ans = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans[i][j] = Character.getNumericValue(matrix[i][j]);
            }
        }
        int[] h = new int[matrix[0].length];
        for (int i = 0; i < ans[0].length; i++) {
            h[i] = ans[0][i];
        }
        int ans1 = largestRectangleArea(h);
        int main = ans1;
        for (int i = 1; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                if (ans[i][j] == 1) {
                    h[j]++;
                } else {
                    h[j] = 0;
                }
            }
            int ans2 = largestRectangleArea(h);
            main = Math.max(main, ans2);
        }
        return main;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> s = new Stack<>();
        int[] l = new int[heights.length];
        int[] r = new int[heights.length];
        s.push(heights.length - 1);
        l[l.length - 1] = heights.length;
        for (int i = l.length - 2; i >= 0; i--) {
            while (s.size() > 0 && heights[i] <= heights[s.peek()]) {
                s.pop();
            }
            if (s.size() == 0) {
                l[i] = heights.length;
            } else {
                l[i] = s.peek();
            }
            s.push(i);
        }
        s = new Stack<>();
        s.push(0);
        r[0] = -1;
        for (int i = 1; i < heights.length; i++) {
            while (s.size() > 0 && heights[i] <= heights[s.peek()]) {
                s.pop();
            }
            if (s.size() == 0) {
                r[i] = -1;
            } else {
                r[i] = s.peek();
            }
            s.push(i);
        }
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int w = l[i] - r[i] - 1;
            int h = heights[i];
            int ans = h * w;
            if (max < ans) {
                max = ans;
            }
        }
        return max;
    }
}
// Valid Parentheses
class Solution {
    public boolean isValid(String str) {
        Stack<Character> st = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if (ch == ')') {
                if (st.size() == 0) {
                    return false;
                } else if (st.peek() != '(') {
                    return false;
                } else {
                    st.pop();
                }
            } else if (ch == '}') {
                if (st.size() == 0) {
                    return false;
                } else if (st.peek() != '{') {
                    return false;
                } else {
                    st.pop();
                }
            } else if (ch == ']') {
                if (st.size() == 0) {
                    return false;
                } else if (st.peek() != '[') {
                    return false;
                } else {
                    st.pop();
                }
            }
        }

        if (st.size() > 0) {
            return false;
        }
        return true;
    }
}
// Minimum Add to Make Parentheses Valid
// Input: s = "())"
// Output: 1
class Solution {
    public int minAddToMakeValid(String s) {
        LinkedList<Character> st = new LinkedList<>();
        st.addFirst(s.charAt(0));
        int i = 1;
        while (i < s.length()) {
            if (st.size() > 0 && st.peek() == '(' && s.charAt(i) == ')') {
                st.removeFirst();
            } else if (s.charAt(i) == ')' || s.charAt(i) == '(') {
                st.addFirst(s.charAt(i));
            }
            i++;
        }
        return st.size();
    }
}
//Count the Reversals
// Input:
// S = "}{{}}{{{"
// Output: 3
// Explanation: One way to balance is:
// "{{{}}{}}". There is no balanced sequence
// that can be formed in lesser reversals.

class Sol
{
    int countRev (String s)
    {
        if(s.length()%2==1)return -1;
        LinkedList<Character> st = new LinkedList<>();
        st.addFirst(s.charAt(0));
        int i = 1;
        while (i < s.length()) {
            if (st.size() > 0 && st.peek() == '{' && s.charAt(i) == '}') {
                st.removeFirst();
            } else if (s.charAt(i) == '}' || s.charAt(i) == '{') {
                st.addFirst(s.charAt(i));
            }
            i++;
        }
        int close =0;
        int open=0;
        while(st.size()>0){
            if(st.peek()=='}'){
                close++;
            }else{
                open++;
            }
            st.removeFirst();
        }
        return (open+1)/2+(close+1)/2;
    }
}
//IPL 2021 - Final
//Input: S = "(()("
// Output: 2
// Explanation: The longest valid 
// substring is "()". Length = 2.
class Solution {
    static int findMaxLen(String S) {
       LinkedList<Integer>s=new LinkedList<>();
       s.addFirst(-1);
       int len=0;
       for(int i=0;i<S.length();i++){
           if(S.charAt(i)=='('){
               s.addFirst(i);
           }else{
              s.removeFirst();
              if(s.size()>0){
                  len=Math.max(len,i-s.peek());
              }else{
                  s.addFirst(i);
              }
           }
       }
       return len;
    }
};

//Min Stack
//https://leetcode.com/problems/min-stack/
class MinStack {
    LinkedList<Integer> data = new LinkedList<>();
    LinkedList<Integer> min = new LinkedList<>();

    public MinStack() {
        data = new LinkedList<>();
        min = new LinkedList<>();
    }

    public void push(int val) {
        data.addFirst(val);
        if (min.size() == 0) {
            min.addFirst(val);
        } else if (val <= min.peek()) {
            min.addFirst(val);
        }
    }

    public void pop() {
        int val = data.removeFirst();
        if (min.size() > 0 && min.peek().equals(val)) {
            min.removeFirst();
        }
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return min.size() > 0 ? min.peek() : 0;
    }
}
// Backspace String Compare
//https://leetcode.com/problems/backspace-string-compare/
class Solution {
    public boolean backspaceCompare(String str, String t) {
        LinkedList<Character> s = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '#') {
                s.addFirst(str.charAt(i));
            } else {
                if (s.size() > 0) s.removeFirst();
            }
        }
        String ans1 = "";
        String ans2 = "";
        while (s.size() > 0) {
            ans1 += s.removeFirst();
        }
        s = new LinkedList<>();
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) != '#') {
                s.addFirst(t.charAt(i));
            } else {
                if (s.size() > 0) s.removeFirst();
            }
        }
        while (s.size() > 0) {
            ans2 += s.removeFirst();
        }
        return ans1.equals(ans2);
    }
}
//Reverse First K elements of Queue
class GfG {
    // Function to reverse first k elements of a queue.
    public Queue<Integer> modifyQueue(Queue<Integer> q, int k) {
        Stack<Integer>st=new Stack<>();
        Queue<Integer> qe = new LinkedList<>();
        int count=0;
        while(count<k){
            st.push(q.poll());
            count++;
        }
        while(q.size()>0){
            int val=q.poll();
            qe.add(val);
        }
        while(st.size()>0){
            q.add(st.pop());
        }
        while(qe.size()>0){
            int val=qe.poll();
            q.add(val);
        }
        return q;
        
    }
}
//Validate Stack Sequences
//https://leetcode.com/problems/validate-stack-sequences/
// Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
// Output: true
// Explanation: We might do the following sequence:
// push(1), push(2), push(3), push(4),
// pop() -> 4,
// push(5),
// pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();
        int idx = 0;
        int y = 0;
        while (idx < pushed.length) {
            s.push(pushed[idx]);
            while (s.size() > 0 && popped[y] == s.peek()) {
                s.pop();
                y++;
            }
            idx++;
        }

        return s.size() == 0 ? true : false;
    }
//Generate Binary Numbers
//way hai dekh ye
//1 10 11 matlb ji rem uske aaage phale 0 and fir 1 karo to uske aage ki baan jati hai
class solve{
    
    //Function to generate binary numbers from 1 to N using a queue.
    static ArrayList<String> generate(int N)
    {
        ArrayList<String>ans=new ArrayList<>();
        LinkedList<String>q=new LinkedList<>();
        q.add("1");
        for(int i=1;i<=N;i++){
            String rem=q.removeFirst();
            ans.add(rem);
            q.addLast(rem+"0");
             q.addLast(rem+"1");
        }
        return ans;
    }
    
}






