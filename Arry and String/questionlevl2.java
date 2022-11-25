public class questionlevl2 {
    //Range Addition
    public static int[] getModifiedArray(int length, int[][] queries) {
        // write your code here
        int[]arr=new int[length];
        int sum=0;
        for(int[]val:queries){
            arr[val[0]]+=val[2];
            if(val[1]+1<length)arr[val[1]+1]-=val[2];
            
        }
        for(int i=0;i<arr.length;i++){
           sum+=arr[i];
           arr[i]=sum;
        }
        return arr;
        
    }
    //Container With Most Water
    public int maxArea(int[] height) {
        int si = 0, ei = height.length - 1;
        int max = -(int) 1e9;
        while (si < ei) {
            max = Math.max(max, Math.min(height[si], height[ei]) * (ei - si));
            if (height[ei] < height[si]) {
                ei--;
            } else {
                si++;
            }
        }
        return max;
    }
    //Squares Of A Sorted Array
    //[0,1,9,16,100]
    //Explanation: After squaring, the array becomes [16,1,0,9,100].
    //After sorting, it becomes [0,1,9,16,100].
    public static int[] sortedSquares(int[] nums) {
        // write your code here
        int[]ans=new int[nums.length];
        int idx=nums.length-1;
        int si=0,ei=nums.length-1;
        while(si<=ei){
            int n1=nums[si];
            int n2=nums[ei];
            if(n1*n1>n2*n2){
                ans[idx]=n1*n1;
                si++;
            }else{
                ans[idx]=n2*n2;
                ei--;
            }
            idx--;
        }
        return ans;
    }
    //Majority Element - I
    //Majority element-> if frequency of an element is more than n/2, then that element is majority element.
    public int majorityElement(int[] arr) {
        int count1 = 0;
        int check1 = (int) 1e10;
        for (int i = 0; i < arr.length; i++) {
            if (check1 == arr[i]) {
                count1++;
            } else if (count1 == 0) {
                count1 = 1;
                check1 = arr[i];
            } else {
                count1--;
            }
        }
        count1=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==check1)count1++;
        }
        return count1>arr.length/2?check1:-1;
    }
    //Majority element-11
    public List<Integer> majorityElement(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        int count1 = 0;
        int check1 = (int) 1e10;
        int count2 = 0;
        int check2 = (int) 1e10;
        for (int i = 0; i < arr.length; i++) {
            if (check1 == arr[i]) {
                count1++;
            } else if (check2 == arr[i]) {
                count2++;
            } else if (count1 == 0) {
                count1 = 1;
                check1 = arr[i];
            } else if (count2 == 0) {
                count2 = 1;
                check2 = arr[i];
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == check2) count2++;
            if (arr[i] == check1) count1++;
        }
        if (count1 > arr.length / 3) ans.add(check1);
        if (count2 > arr.length / 3) ans.add(check2);
        return ans;
    }
    // Majority Element General
    //(N/k ho to normal hash se)
    public static ArrayList<Integer> majorityElement(int[] arr, int k) {
        // write yout code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            int key = arr[i];
            if(map.containsKey(key) == true) {
                map.put(key, map.get(key) + 1); 
            } else {
                map.put(key, 1);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int key : map.keySet()) {
            if(map.get(key) > n / k)
                res.add(key);
        }
        Collections.sort(res);
        return res;
    }
    //Product Of Array Except Itself Without Using Division Operator
    //Return an array answer such that answer[i] is equal to the product of all the elements of arr except arr[i].
    public static int[] productExceptSelf(int[] arr) {
        // write your code here
        int[]left=new int[arr.length];
        int[]ans=new int[arr.length];
        int sum=1;
        for(int i=0;i<arr.length;i++){
            sum*=arr[i];
            left[i]=sum;
        }
         sum=1;
         for(int i=arr.length-1;i>0;i--){
           ans[i]=left[i-1]*sum;
           sum*=arr[i];
        }
        ans[0]=sum;
        return ans;
    }
    //Max Chunks To Make Array Sorted
    public static int maxChunksToSorted(int[] arr) {
        // write your code here
        int max = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
          if (max < arr[i])max = arr[i];
          if (max == i)count++;
        }
        return count;
    
      } 
      //Max Chunks To Make Array Sorted 2
      //ss dekho
      public static int maxChunksToSorted2(int[] arr) {
        // write your code here
        int[]right=new int[arr.length+1];
        right[arr.length]=(int)1e9;
        for(int i=arr.length-1;i>=0;i--){
            right[i]=Math.min(arr[i],right[i+1]);
        }
        int count=0;
        int max=0;
        for(int i=0;i<arr.length;i++){
            max=Math.max(max,arr[i]);
            if(max<right[i+1])count++;
        }
        return count;
      }
      //Partition Labels
      public static List<Integer> partitionLabels(String s) {
        // write your code here
        ArrayList<Integer>ans=new ArrayList<>();
        int[]arr=new int[26];
        for(int i=0;i<s.length();i++){
            arr[s.charAt(i)-'a']=i;
        }
        int max=0;
        int prev=-1;
        //prev -1 kuki index zero se ho rha
        for(int i=0;i<s.length();i++){
            if(max<arr[s.charAt(i)-'a']){
                max=arr[s.charAt(i)-'a'];
            }
            if(max==i){
                ans.add(max-prev);
                prev=max;
                
            }
        }
        return ans;
      }
      //Min Jumps With +i -i Moves
      //sun agar phale hi even hai to jump -1;
      //agar odd aya to ak jump or or fir even to jump
      //agar fir bhi odd aya to ab matlb phale a=odd me even add hua tha or ab odd add hoga 
      //or min ans ka poakka is lia hai kuki humne phale hi ak direction me jate rhe
      public static int minJumps(int x) {
        return help(x);
      }
      public static int help(int x) {
        int jump = 1;
        int sum = 0;
        while (sum < x) {
          sum += jump;
          jump++;
        }
        if ((sum - x) % 2 == 0)return jump - 1;
        else if ((sum + jump - x) % 2 == 0)return jump;
        else return jump + 1;
    
      }
      //Max Product Of Three Numbers
      public static int maximumProduct(int[] arr) {
        int min = (int)1e9;
        int smin = min;
        int max = -(int)1e9;
        int smax = max;
        int tmax = max;
        for (int i = 0; i < arr.length; i++) {
          if (arr[i] > max) {
            smax = max;
            tmax = smax;
            max = arr[i];
          } else if (arr[i] > smax) {
            tmax = smax;
            smax = arr[i];
          } else {
            tmax = arr[i];
          }
          if (arr[i] < min) {
            smin = min;
            min = arr[i];
          } else if (arr[i] < smin) {
            smin = arr[i];
          }
        }
        return Math.max(min * smin * max, max * smax * tmax);
    
      }
    
    //Sort Array By Parity
    //Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
    public int[] sortArrayByParity(int[] nums) {
        int si = 0, ei = 0;
        while (ei < nums.length) {
            if (nums[ei] % 2 == 0) {
                swap(si, ei, nums);
                si++;
                ei++;
            } else {
                ei++;
            }
        }
        return nums;
    }

    public static void swap(int si, int ei, int[] nums) {
        if (ei < nums.length) {
            int temp = nums[si];
            nums[si] = nums[ei];
            nums[ei] = temp;
        }
    }
    //Best Meeting Point
    //ss
    //boi hai median is lia kuki sab point se distace small hoga
    public static int minTotalDistance(int[][] grid) {
        ArrayList<Integer>row = new ArrayList<>();
        ArrayList<Integer>col = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
          for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 1)row.add(i);
          }
        }
        for (int i = 0; i < grid[0].length; i++) {
          for (int j = 0; j < grid.length; j++) {
            if (grid[j][i] == 1)col.add(i);
          }
        }
    
        int x = row.get(row.size() / 2);
        int y = col.get(col.size() / 2);
        int ans = 0;
        for (int i = 0; i < row.size(); i++) {
          ans += Math.abs(row.get(i) - x) + Math.abs(col.get(i) - y);
        }
        return ans;
      }
    //prime
    //agar kisi number ko divide hona hoga to bo apne square root tak divade ho jai ga kuki
    //p*q=n
    //p>root(n)
    //q>root(n)
    //not poosible
    //to phala number hi root(n)tak nhi mila to hume number prime hi hai
    public static void printPrimeUsingSieve(int n) {
        // write your code here
        for(int i=2;i<=n;i++){
            if(help(i))System.out.print(i+" ");
        }
    }
    public static boolean help(int n) {
        // write your code here
        int count=0;
        for(int i=2;i*i<=n;i++){
            if(n%i==0)count++;
        }
        return count==1;
    }
    //prime (Sieve Of Eratosthenes)
    public static void printPrimeUsingSieve(int n) {
        boolean[] isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);

        for(int i = 2; i * i <= n; i++) {
            if(isPrime[i] == true) {
                for(int j = i + i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for(int i = 2; i < isPrime.length; i++) {
            if(isPrime[i] == true) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    //Transpose Of Matrix With Dimension M X N
    //n*m hai is lia ak new matrix bana ke hi hoga agar n*n hoti to ussi me ho jata
    public static int[][] transpose(int[][] matrix) {
        // write your code here
        int n = matrix.length;
        int m = matrix[0].length;
    
        int[][] res = new int[m][n];
    
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            res[j][i] = matrix[i][j];
          }
        }
        return res;
      }
    //Transpose Of Matrix With Dimension N X N
    public static void transpose(int[][] matrix) {
        // write your code here
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < i; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
          }
    
        }
      }
      //Shortest Unsorted Continuous Subarray
      public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int end = -1, max = nums[0];
        for (int i = 1; i < n; i++){
            if (nums[i] < max)  
                end = i;
            else    
                max = nums[i];
        }
        int start = 0, min = nums[n - 1];
        for (int i = n - 2; i >= 0; i--){
            if (nums[i] > min)  
                start = i;
            else
                min = nums[i];
        }
        return end - start + 1;
    }
    //Reverse Vowels Of A String
    public static boolean isVowel(char[] arr, int indx) {
        char ch = arr[indx];
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
            ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
          return true;
        }
        return false;
      }
    
      public static void swap(char[] arr, int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
      }
    
      public static String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
    
        while (left < right) {
          while (left < right && isVowel(arr, left) == false) {
            left++;
          }
          while (left < right && isVowel(arr, right) == false) {
            right--;
          }
          swap(arr, left, right);
          left++;
          right--;
        }
    
        String str = "";
        for (char ch : arr) {
          str += ch;
        }
        return str;
      }
      //YA
      public static String reverseVowels(String s) {
        // write your code here
        String ans = "";
        String main="";
    
        for (int i = 0; i < s.length(); i++) {
          if (isVowel(s.charAt(i)))ans += s.charAt(i);
        }
        int idx=ans.length()-1;;
        for (int i = 0; i < s.length(); i++) {
          if (isVowel(s.charAt(i))){
               main+=ans.charAt(idx);
              idx--;
          }
          else{
              main+=s.charAt(i);
          }
        }
        return main;
    }
    public  static boolean isVowel(char c)
      {
        if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' ||     c == 'u' || c == 'U')
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      //Wiggle Sort 1
      //arr[0] <= arr[1] >= arr[2] <= arr[3] . . . 
      //boi even small and odd big
      public static void wiggleSort(int[] arr) {
        int idx=0;
        for(int i=0;i<arr.length-1;i++){
            if(i%2==0 && arr[i]>arr[i+1]){
                swap(arr,i,i+1);
            }else if(i%2==1 && arr[i]<arr[i+1]){
                swap(arr,i,i+1);
            }
            
            
        }
      }
      public static void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
      }
        
      //Add Strings
      //add karna hai 2 string ko
      public static String addStrings(String num1, String num2) {
        // write your code here
        int carry = 0;
        String ans = "";
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
          int ival = i >= 0 ? num1.charAt(i) - '0' : 0;
          int jval = j >= 0 ? num2.charAt(j) - '0' : 0;
          int sum = carry + ival + jval;
          ans = sum % 10 + ans;
          carry = sum / 10;
          i--;
          j--;
        }
        return ans;
    
    
    
      }
      //Complex Number Multiplication
      //num1 = "1+1i", num2 = "1+1i" (boi solve karna)
      public static String complexNumberMultiply(String num1, String num2) {
        // write your code here
        int a=Integer.parseInt(num1.substring(0,num1.indexOf('+')));
        int b=Integer.parseInt(num1.substring(num1.indexOf('+')+1,num1.length()-1));
        int c=Integer.parseInt(num2.substring(0,num2.indexOf('+')));
        int d=Integer.parseInt(num2.substring(num2.indexOf('+')+1,num2.length()-1));
        return ""+(a*c-b*d)+"+"+(a*d+b*c)+"i";
      }
      //Valid Palindrome 2
      public static boolean validPalindrome(String s) {
        // write your code here
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
          if (s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
          } else {
            return help(s, i + 1, j) || help(s, i, j - 1);
          }
    
        }
        return true;
      }
      public static boolean help(String s, int i, int j) {
        while (i <= j) {
          if (s.charAt(i) != s.charAt(j))return false;
          i++;
          j--;
        }
        return true;
      }
      //Minimum Addition to Make Integer Beautiful
      //Input: n = 16, target = 6
      // Output: 4
      // Explanation: Initially n is 16 and its digit sum is 1 + 6 = 7. After adding 4, n becomes 20 and digit sum becomes 2 + 0 = 2. It can be shown that we can not make n beautiful with adding non-negative integer less than 4.
      private int sum(long n) {
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }

        return res;
    }

    public long makeIntegerBeautiful(long n, int target) {
        long n0 = n, base = 1;
        while (sum(n) > target) {
            n = n / 10 + 1;
            base *= 10;
        }
        return n * base - n0;
    }
    //Car Fleet
    //boi hai sort kar dia or end se chala dia ab piche se jo aye ga agar late hoga time me to count 
    //inc hoga hi kuki bo mix kar de ge 
    public int carFleet(int t, int[] position, int[] speed) {
      int[][]arr=new int[position.length][2];
      int n=speed.length;
      for(int i=0;i<position.length;i++){
          arr[i][0]=position[i];
          arr[i][1]=speed[i];
      }
      Arrays.sort(arr, (a, b) -> a[0] - b[0]);
      double prev=-0.1;
      int count=0;
      for(int i=n-1;i>=0;i--){
          double cur=(t-arr[i][0]*1.0)/arr[i][1];
          if(cur>prev){
              prev=cur;
              count++;
          }
      }
      return count;
  }
  //Max Area of Island
  int[][]dir={{0,1},{1,0},{-1,0},{0,-1}};
    public int maxAreaOfIsland(int[][] grid) {
        
        int max=-(int)1e9;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    
                 max=Math.max(max,help(grid,i,j));
                }
            }
        }
        return max==-(int)1e9?0:max;
    }
    public int help(int[][] grid,int sr,int sc) {
         grid[sr][sc]=0;
        int size=0;
        for(int []d:dir){
            int r=sr+d[0];
            int c=sc+d[1];
            if(r>=0 && c>=0 && r<grid.length && c<grid[0].length && grid[r][c]==1){
               
                size+=help(grid,r,c);
            }
        }
        return size+1;
    }
    //Number of subarrays with maximum values in given range
    static long countSubarrays(int arr[], int n, int left, int right)  
    { 
        // Complete the function
            int si = 0, ei = 0, count = 0;
            long res = 0;
            while (ei < arr.length) {
              if ( arr[ei]>=left  && arr[ei] <= right) {
                count = ei - si + 1;
              } else if (arr[ei] < left) {
                count = count;
              } else if (arr[ei] > right) {
                si = ei + 1;
                count = 0;
        
              }
              res += count;
              ei++;
            }
            return res;
    } 
    //Maximum Swap
    public static String maximumSwap(String num) {
      // write your code here
      char[]ch = num.toCharArray();
      int[]arr = new int[10];
      for (int i = 0; i < num.length(); i++) {
        arr[ch[i] - '0'] = i;
      }
      boolean flag = false;
      for (int i = 0; i < num.length(); i++) {
        for (int j = 9; j > (ch[i] - '0'); j--) {
          if (i < arr[j]) {
            swap(ch, i, arr[j]);
            flag = true;
            break;
          }
        }
        if (flag == true) {
          break;
        }
      }
      String res = "";
      for (int i = 0; i < ch.length; i++) {
        res += ch[i];
      }
      return res;
    }
    // Smallest Range Covering Elements from K Lists
    public int[] smallestRange(List<List<Integer>> nums) {
      int[] ans = new int[2];
      ans[0] = -(int) 1e5;
      ans[1] = (int) 1e5;
      int max = -(int) 1e9;
      PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
      for (int i = 0; i < nums.size(); i++) {
          int[] arr = { nums.get(i).get(0), 0, i };
          max = Math.max(max, nums.get(i).get(0));
          pq.add(arr);
      }
      while (true) {
          int[] arr = pq.poll();
          if (ans[1] - ans[0] > max - arr[0]) {
              ans[0] = arr[0];
              ans[1] = max;
          }
          arr[1]++;
          List<Integer> c1 = nums.get(arr[2]);
          if (arr[1] == c1.size()) break; else {
              arr[0] = c1.get(arr[1]);
              max = Math.max(max, c1.get(arr[1]));
              pq.add(arr);
          }
      }
      return ans;
  }
  //Minimum Domino Rotations For Equal Row 
  //boi kisi ak arry ko sae karna hai by swapmusing another arry
  //arr1 wale arry ko kare,arr2 ko kare num1 vali value se,
  //arr2 wale arry ko kare,arr2,arr1 ko kare num2 vali value se,
  public static int minDominoRotations(int[] tops, int[] bottoms) {
    int count1 = 0, count2 = 0, count3 = 0, count4 = 0;

    int num1 = tops[0];
    int num2 = bottoms[0];

    for (int i = 0; i < tops.length; i++) {
      // count 1 is count of rotation if top array have num1
      if (count1 != Integer.MAX_VALUE) {
        if (tops[i] == num1) {
          // nothing to do for count
        } else if (bottoms[i] == num1) {
          count1++;
        } else {
          count1 = Integer.MAX_VALUE;
        }
      }

      // count 2 is count of rotation if bottom array have num1
      if (count2 != Integer.MAX_VALUE) {
        if (bottoms[i] == num1) {
          // nothing to do
        } else if (tops[i] == num1) {
          count2++;
        } else {
          count2 = Integer.MAX_VALUE;
        }
      }

      // count 3 is count of rotation if top array have num2
      if (count3 != Integer.MAX_VALUE) {
        if (tops[i] == num2) {
          // nothing to do for count
        } else if (bottoms[i] == num2) {
          count3++;
        } else {
          count3 = Integer.MAX_VALUE;
        }
      }

      // count 4 is count of rotation if bottom array have num2
      if (count4 != Integer.MAX_VALUE) {
        if (bottoms[i] == num2) {
          // nothing to do
        } else if (tops[i] == num2) {
          count4++;
        } else {
          count4 = Integer.MAX_VALUE;
        }
      }
    }

    int ans = Math.min(Math.min(count1, count2), Math.min(count3, count4));

    return ans == Integer.MAX_VALUE ? -1 : ans;
  }
    
}
//Find All Duplicates In An Array
public static List<Integer> findDuplicates(int[] nums) {
  // write your code here
  ArrayList<Integer>ans = new ArrayList<>();
  for (int i = 0; i < nums.length; i++) {
    int idx = Math.abs(nums[i]) - 1;
    int val = nums[idx];
    if (nums[idx] < 0) {
      ans.add(idx + 1);
    } else {
      nums[idx] *= -1;
    }
  }
  return ans;

}
//Find And Replace Pattern
//boi pattern ke character ko str ke character se 
//ab set is lia kuki agar pattern character different hua
//to bo map kar de ga
//ex
//aaa-->word
//lks-->pattern
//l-a,k-a is lia set ki bache jai is se
public static boolean isMatching(String str, String pat) {
  HashMap<Character, Character> map = new HashMap<>();
  HashSet<Character> set = new HashSet<>();

  for (int i = 0; i < str.length(); i++) {
    char pch = pat.charAt(i); // pattern character
    char sch = str.charAt(i); // string character
    if (map.containsKey(pch)) {
      if (map.get(pch) != sch)
        return false;
    } else {
      if (set.contains(sch)) return false;
      map.put(pch, sch);
      set.add(sch);
    }
  }
  return true;
}

public static List<String> findAndReplacePattern(String[] words, String pattern) {
  List<String> res = new ArrayList<>();
  for (String str : words) {
    if (isMatching(str, pattern)) {
      res.add(str);
    }
  }
  return res;
}
//Consecutive Numbers Sum
//ss hai dekh le
public static int consecutiveNumbersSum(int n) {
  // write your code here
  int count = 0;
  for (int k = 1; 2 * n > k * (k - 1); k++) {
    int num = (n - k * (k - 1) / 2);
    if (num % k == 0)count++;
  }
  return count;
}
//Push Dominoes
//boi road type hai to l r hone se kya hoga boi
//ss dekh
private static void solveConfiguration(char[] arr, int i, int j) {
  if (arr[i] == 'L' && arr[j] == 'L') {
    for (int k = i + 1; k < j; k++)
      arr[k] = 'L';
  } else if (arr[i] == 'R' && arr[j] == 'R') {
    for (int k = i + 1; k < j; k++)
      arr[k] = 'R';
  } else if (arr[i] == 'L' && arr[j] == 'R') {
    // nothing to do
  } else {
    int diff = j - i;
    if (diff % 2 == 0) {
      int mid = (i + j) / 2;
      for (int k = i + 1; k < mid; k++)
        arr[k] = 'R';

      for (int k = mid + 1; k < j; k++)
        arr[k] = 'L';

    } else {
      int mid = (i + j) / 2;
      for (int k = i + 1; k <= mid; k++)
        arr[k] = 'R';

      for (int k = mid + 1; k < j; k++)
        arr[k] = 'L';

    }
  }
}

public static String pushDominoes(String str) {
  char[] arr = new char[str.length() + 2];
  arr[0] = 'L';
  arr[arr.length - 1] = 'R';
  for (int i = 1; i < arr.length - 1; i++) {
    arr[i] = str.charAt(i - 1);
  }
  int j = 0, k = 1;
  while (k < arr.length) {
    while (arr[k] == '.') {
      k++;
    }
    if (k - j > 1)
      solveConfiguration(arr, j, k);
    j = k;
    k++;
  }
  StringBuilder res = new StringBuilder();
  for (int i = 1; i < arr.length - 1; i++) {
    res.append(arr[i]);
  }
  return res.toString();
}

//max product subarray
//ss dekho ki ku allows ans start ya end hi bane ga
public int maxProduct(int[] nums) {
  int max = -(int) 1e9;
  int ans = 1;
  for (int i = 0; i < nums.length; i++) {
      ans *= nums[i];
      max = Math.max(max, ans);
      if (ans == 0) {
          ans = 1;
      }
  }
  ans = 1;
  for (int i = nums.length - 1; i >= 0; i--) {
      ans *= nums[i];
      max = Math.max(max, ans);
      if (ans == 0) {
          ans = 1;
      }
  }
  return max;
}
//Sum Of Subsequence Widths
public static int sumSubseqWidths(int[] A) {
  int MOD = 1_000_000_007;
  int N = A.length;
  Arrays.sort(A);

  long[] pow2 = new long[N];
  pow2[0] = 1;
  for (int i = 1; i < N; ++i)
    pow2[i] = pow2[i - 1] * 2 % MOD;

  long ans = 0;
  for (int i = 0; i < N; ++i)
    ans = (ans + (pow2[i] - pow2[N - 1 - i]) * A[i]) % MOD;

  return (int) ans;
}
// The k-th Lexicographical String of All Happy Strings of Length n
//boi a b c hai ab happy string bo hai jab character same na ho apne se
//sath wale ke
ArrayList<String> ans;
    public String getHappyString(int n, int k) {
        ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        help(n, sb);
        Collections.sort(ans);
        if (ans.size() < k) return "";
        return ans.get(k - 1);
    }

    public void help(int n, StringBuilder sb) {
        if (sb.length() == n) {
            ans.add(sb.toString());
            return;
        }
        for (char ch = 'a'; ch <= 'c'; ch++) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ch) continue;
            sb.append(ch);
            help(n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    //Construct Target Array With Multiple Sums
    public boolean isPossible(int[] target) {
      PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
      long sum = 0;
      for (int i = 0; i < target.length; i++) {
          pq.add((long) target[i]);
          sum += target[i];
      }
      while (pq.peek() != 1) {
          long max = pq.poll();
          sum = sum - max;
          if (sum <= 0 || sum >= max) return false;
          max = max % sum;

          sum += max;
          pq.add(max > 0 ? max : sum);
      }
      return true;
  }
  //Furthest Building You Can Reach
  //boi hai ladder se bada size ho gya to min wala brick se minus
  //and when brick zero se niche ho jai to boi ans hai
  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < heights.length - 1; i++) {
        int diff = heights[i + 1] - heights[i];
        if (diff > 0) {
            pq.add(diff);
        }
        if (pq.size() > ladders) {
            bricks -= pq.poll();
        }
        if (bricks < 0) return i;
    }
    return heights.length - 1;
}
//Ways to Make a Fair Array
//boi josh wala even and odd element ko equal kase kare ak ko remove kar ke
//https://leetcode.com/problems/ways-to-make-a-fair-array/
public int waysToMakeFair(int[] nums) {
  int[] odd = new int[nums.length];
  int[] even = new int[nums.length];
  int oddsum = 0;
  int evensum = 0;
  for (int i = 0; i < nums.length; i++) {
      if (i % 2 == 0) {
          evensum += nums[i];
      } else {
          oddsum += nums[i];
      }
      odd[i] = oddsum;
      even[i] = evensum;
  }
  int ans = 0;
  for (int i = 0; i < nums.length; i++) {
      if (i == 0) {
          int odds = even[nums.length - 1] - nums[0];
          int evens = odd[nums.length - 1];
          if (odds == evens) {
              ans++;
          }
          continue;
      }
      int odds = odd[i - 1] + even[nums.length - 1] - even[i];
      int evens = even[i - 1] + odd[nums.length - 1] - odd[i];
      if (odds == evens) {
          ans++;
      }
  }
  return ans;
}
// Orderly Queue
//boi k==1 to first delete and add at last
//k se bada to ans always sort string hogi
public String orderlyQueue(String s, int k) {
  String ans = s;
  if (k == 1) {
      StringBuilder sb = new StringBuilder(s);
      int n = s.length();
      while (n-- > 0) {
          char ch = sb.charAt(0);
          sb.deleteCharAt(0);
          sb.append(ch);
          String a = sb.toString();
          if (ans.compareTo(a) > 0) {
              ans = a;
          }
      }
      return ans;
  }
  char[] arr = s.toCharArray();
  Arrays.sort(arr);
  String snew = new String(arr);
  return snew;
}
//Sort the Matrix Diagonally
//https://leetcode.com/problems/sort-the-matrix-diagonally/
public int[][] diagonalSort(int[][] mat) {
  int rows = mat.length;
  int cols = mat[0].length;
  //Sorting diagonals of first row completely
  for (int i = 0; i < cols; i++) {
      sortDiagonal(mat, 0, i);
  }
  for (int i = 1; i < rows - 1; i++) {
      sortDiagonal(mat, i, 0);
  }
  return mat;
}
public void sortDiagonal(int[][] mat, int i, int j) {
  List<Integer> a = new ArrayList<>();
  int x = i, y = j;
  while (i < mat.length && j < mat[0].length) {
      a.add(mat[i][j]);
      i++;
      j++;
  }
  Collections.sort(a);
  int count = 0;
  while (x < mat.length && y < mat[0].length) {
      mat[x][y] = a.get(count);
      count++;
      x++;
      y++;
  }
}
//Reorganize String
//boi block and add wala hoga
//Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
//Return any possible rearrangement of s or return "" if not possible.
class Solution {
  class pair {
      char ch;
      int f;

      pair(char ch, int f) {
          this.ch = ch;
          this.f = f;
      }
  }

  public String reorganizeString(String s) {
      int[] arr = new int[26];
      for (int i = 0; i < s.length(); i++) {
          arr[s.charAt(i) - 'a']++;
      }
      PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> (b.f - a.f));
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
          if (arr[s.charAt(i) - 'a'] > 0) {
              pq.add(new pair(s.charAt(i), arr[s.charAt(i) - 'a']));
              arr[s.charAt(i) - 'a'] = 0;
          }
      }
      pair block = pq.poll();
      sb.append(block.ch);
      block.f--;
      while (pq.size() > 0) {
          pair temp = pq.poll();
          sb.append(temp.ch);
          temp.f--;
          if (block.f > 0) {
              pq.add(block);
          }
          block = temp;
      }
      if (block.f > 0) return "";
      return sb.toString();
  }
}
//Bulb Switcher Iv
//boi hai future wala concept karna hai
//ss dekho
int minFlips(string target) {
  int curr = 0;
  int count = 0;
  for (int i = 0; i < target.length(); i++) {
    if (target[i] - '0' != curr) {
      curr = target[i] - '0';
      count++;
    }
  }
  return count;
}
//Maximum Sum of Distinct Subarrays With Length K
public long maximumSubarraySum(int[] nums, int k) {
  HashSet<Integer> set = new HashSet<>();
  int i = 0, j = 0;
  long sum = 0, max = -(int) 1e9;
  while (j < nums.length) {
      if (!set.contains(nums[j])) {
          set.add(nums[j]);
          sum += nums[j];
          if (j - i + 1 == k) {
              max = Math.max(max, sum);
              sum -= nums[i];
              set.remove(nums[i]);
              i++;
          }
      } else {
          i = j;
          set = new HashSet<>();
          sum = nums[j];
          set.add(nums[j]);
          
      }
      j++;
  }
  return max == -(int) 1e9 ? 0 : max;
}
//Total Cost to Hire K Workers
public long totalCost(int[] cost, int k, int candidates) {
  PriorityQueue<Integer> left = new PriorityQueue<>();
  PriorityQueue<Integer> right = new PriorityQueue<>();
  long sum = 0L;
  int i = 0;
  int j = cost.length - 1;
  for (i = 0; i < candidates; i++) {
      left.add(cost[i]);
  }
  for (j = cost.length - 1; j >= cost.length - candidates && j >= i; j--) {
      right.add(cost[j]);
  }
  int round = 0;
  while (i <= j && round < k) {
      if (left.peek() <= right.peek()) {
          sum += left.poll();
          left.add(cost[i]);
          i++;
      } else {
          sum += right.poll();
          right.add(cost[j]);
          j--;
      }
      round++;
  }
  while (round < k) {
      if (left.size() == 0) sum += right.poll(); else if (right.size() == 0) sum += left.poll(); else if (left.peek() <= right.peek()) sum += left.poll(); else sum += right.poll();

      round++;
  }
  return sum;
}
//Maximum Sum of Two Non-Overlapping Subarrays
//boi left and right dia hai or max ans per range wale overlap na ho
public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
  int max1 = getMax(nums, firstLen, secondLen);
  int max2 = getMax(nums, secondLen, firstLen);
  return Math.max(max1, max2);
}

public int getMax(int[] nums, int firstLen, int secondLen) {
  int[] left = new int[nums.length];
  int sum = 0;
  for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (i == firstLen - 1) {
          left[i] = sum;
      } else if (i >= firstLen) {
          sum -= nums[i - firstLen];
          left[i] = Math.max(sum, left[i - 1]);
      }
  }
  int[] right = new int[nums.length];
  sum = 0;
  for (int i = nums.length - 1; i >= 0; i--) {
      sum += nums[i];
      if (i == nums.length - secondLen) {
          right[i] = sum;
      } else if (i < nums.length - secondLen) {
          sum -= nums[i + secondLen];
          right[i] = Math.max(sum, right[i + 1]);
      }
  }
  sum = 0;
  for (int i = 0; i < nums.length - 1; i++) {
      sum = Math.max(sum, left[i] + right[i + 1]);
  }
  return sum;
}
//Implement Stack using Queues
Queue<Integer> q = new LinkedList<>();
public MyStack() {
    q = new LinkedList<>();
}
public void push(int x) {
    q.add(x);
    for (int i = 0; i < q.size() - 1; i++) {
        q.add(q.remove());
    }
}
public int pop() {
    return q.remove();
}
public int top() {
    return q.peek();
}
public boolean empty() {
    return q.size() == 0;
}

//Island Perimeter
int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    for (int[] d : dir) {
                        int r = i + d[0];
                        int c = j + d[1];
                        if (check(r, c, grid.length, grid[0].length)) ans++; else if (grid[r][c] == 0) ans++;
                    }
                }
            }
        }
        return ans;
    }
    public boolean check(int i, int j, int n, int m) {
        if (i == -1 || i == n || j == -1 || j == m) return true; else return false;
    }
//Largest Multiple of Three
//ss dekho
public String largestMultipleOfThree(int[] arr) {
  Arrays.sort(arr);
  long sum = 0;
  for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
  }
  String ans = "";
  if (sum % 3 == 0) {
      if (arr[arr.length - 1] == 0) return "0";
      for (int i = arr.length - 1; i >= 0; i--) {
          ans += arr[i];
      }
      return ans;
  } else if (sum % 3 == 1) {
      boolean e = false;
      for (int i = 0; i < arr.length; i++) {
          if (arr[i] % 3 == 1) {
              arr[i] = -1;
              e = true;
              break;
          }
      }
      if (!e) {
          int[] a = new int[2];
          a[0] = -1;
          a[1] = -1;
          for (int i = 0; i < arr.length; i++) {
              if (arr[i] % 3 == 2) {
                  if (a[0] == -1) {
                      a[0] = i;
                  } else {
                      a[1] = i;
                      break;
                  }
              }
          }
          if (a[0] != -1) {
              arr[a[0]] = -1;
              arr[a[1]] = -1;
          } else {
              return "";
          }
      }
  } else if (sum % 3 == 2) {
      boolean e = false;
      for (int i = 0; i < arr.length; i++) {
          if (arr[i] % 3 == 2) {
              arr[i] = -1;
              e = true;
              break;
          }
      }
      if (!e) {
          int[] a = new int[2];
          a[0] = -1;
          a[1] = -1;
          for (int i = 0; i < arr.length; i++) {
              if (arr[i] % 3 == 1) {
                  if (a[0] == -1) {
                      a[0] = i;
                  } else {
                      a[1] = i;
                      break;
                  }
              }
          }
          if (a[0] != -1) {
              arr[a[0]] = -1;
              arr[a[1]] = -1;
          } else {
              return "";
          }
      }
  }
  ans = "";
  for (int i = arr.length - 1; i >= 0; i--) {
      if (arr[i] != -1) ans += arr[i];
  }
  if (ans.length() > 0 && ans.charAt(0) == '0') return "0";
  return ans;
}
//question ss me hai
public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt();
  int m = sc.nextInt();
  int[][] arr = new int[n][m];
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      arr[i][j] = sc.nextInt();
    }
  }
  int[]wi = new int[n];
  int[]wj = new int[m];
  int[]bi = new int[n];
  int[]bj = new int[m];
  for (int i = 0; i < n; i++) {
    int b = 0;
    int w = 0;
    for (int j = 0; j < m; j++) {
      if (arr[i][j] == 1)b++;
      else w++;
    }
    wi[i] = w;
    bi[i] = b;
  }
  for (int i = 0; i < m; i++) {
    int b = 0;
    int w = 0;
    for (int j = 0; j < n; j++) {
      if (arr[j][i] == 1)b++;
      else w++;
    }
    wj[i] = w;
    bj[i] = b;
  }
  int max = -(int)1e9;
  int diff = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      max = Math.max(max, (bi[i] + bj[j]) - (wi[i] + wj[j]));
    }
  }
  System.out.println(max);

}
//Merge Intervals
public int[][] merge(int[][] intervals) {
  Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
  ArrayList<int[]> ans = new ArrayList<>();
  for (int[] val : intervals) {
      int start = val[0];
      int end = val[1];
      if (ans.size() == 0) {
          ans.add(new int[] { start, end });
      } else {
          int[] arr = ans.get(ans.size() - 1);
          if (val[0] <= arr[1]) {
              arr[1] = Math.max(arr[1], val[1]);
          } else {
              ans.add(val);
          }
      }
  }
  int[][] arr = new int[ans.size()][2];
  for (int i = 0; i < arr.length; i++) {
      int[] a = ans.get(i);
      arr[i][0] = a[0];
      arr[i][1] = a[1];
  }
  return arr;
}
//Minimum Number Of Platform
public static int findPlatform(int[] arr, int[] dep) {
  // write your code here
  Arrays.sort(arr);
  Arrays.sort(dep);
  int i = 1, j = 0, count = 1;
  while (i < arr.length) {
    if (dep[j] > arr[i]) {
      count++;
    } else {
      j++;
    }
    i++;
  }
  return count;
}
//Meeting Rooms
public boolean canAttendMeetings(List<Interval> intervals) {
  Collections.sort(intervals, (a,b)-> {
    return a.start - b.start;
  });
  for(int i=0;i<intervals.size()-1;i++){
      if(intervals.get(i).end>intervals.get(i+1).start)return false;
  }
  return true;
}
// Meeting Rooms II
public int minMeetingRooms(List<Interval> intervals) {
  int count=1;
  PriorityQueue<Integer> pq = new PriorityQueue<>();
  Collections.sort(intervals, (a,b)-> {
          return a.start - b.start;
  });
  for(int i=0;i<intervals.size();i++){
      if(pq.size()==0){
          pq.add(intervals.get(i).end);
      }else if(intervals.get(i).start<pq.peek()){
          pq.add(intervals.get(i).end);
      }else{
          pq.remove();
          pq.add(intervals.get(i).end);
      }
  }
  return pq.size();
 
}
//986. Interval List Intersections
public int[][] intervalIntersection(int[][] intervalList1, int[][] intervalList2) {
  ArrayList<int[]> ans = new ArrayList<>();
  int i = 0;
  int j = 0;
  while (i < intervalList1.length && j < intervalList2.length) {
      int max = Math.max(intervalList1[i][0], intervalList2[j][0]);
      int min = Math.min(intervalList1[i][1], intervalList2[j][1]);
      if (max <= min) {
          ans.add(new int[] { max, min });
      }
      if (intervalList1[i][1] < intervalList2[j][1]) {
          i++;
      } else j++;
  }
  return ans.toArray(new int[ans.size()][]);
}
//Insert Intervals
public static int[][] insertIntervals(int intervalList[][], int newInterval[]) {
  // write your code here
  ArrayList<int[]>ans = new ArrayList<>();
  int idx = 0;
  while (idx < intervalList.length) {
    if (intervalList[idx][0] < newInterval[0]) {
      ans.add(intervalList[idx]);
      idx++;
    } else {
      break;
    }
  }
  if (ans.size() == 0 || ans.get(ans.size() - 1)[1] < newInterval[0]) {
    ans.add(newInterval);
  } else {

    ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], newInterval[1]);
  }
  while (idx < intervalList.length) {
    int[]lastint = ans.get(ans.size() - 1);
    if (lastint[1] >= intervalList[idx][0]) {
      lastint[1] = Math.max(lastint[1], intervalList[idx][1]);

    } else {
      ans.add(intervalList[idx]);
    }
    idx++;
  }
  return ans.toArray(new int[ans.size()][]);

}
//Find Minimum Number Of Arrows Needed To Burst Balloon
public int findMinArrowShots(int[][] coordinates) {
  Arrays.sort(coordinates, (a, b) -> Integer.compare(a[1], b[1]));
  int end = coordinates[0][1], arrow = 1;
  for (int i = 1; i < coordinates.length; i++) {
      if (coordinates[i][0] > end) {
          end = coordinates[i][1];
          arrow++;
      }
  }

  return arrow;
}
//Car Pooling
//range sum me kya tha 1 to 5 me sab me increment tha
//per is me 5 pe dec hai kuki banda uthar gya hai
public static boolean carPooling(int trips[][], int cap) {
  // write your code here
  int max = 0;
  for (int[]val : trips) {
    max = Math.max(max, val[1]);
  }
  int[]arr = new int[max + 2];
  for (int[]val : trips) {
    arr[val[0]] += val[2];
    arr[val[1]] -= val[2];
  }
  int sum = 0;
  for (int i = 0; i < arr.length; i++) {
    sum += arr[i];
    if (sum > cap)return false;
  }
  return true;
}
//Maximum Average Subarray I
public static double solution(int nums[], int k) {
  // write your code here
  int ans = 0;
  for (int i = 0; i < k; i++) {
    ans += nums[i];
  }
  int max = ans;
  for (int i = k; i < nums.length; i++) {
    ans += nums[i];
    ans -= nums[i - k];
    max = Math.max(max, ans);

  }
  return (max * 1.0) / k;
}
//Min Len Of String After Removing Similar Ends
public static int minLen(String s) {
  int l = 0, r = s.length() - 1;
  while (l <= r && s.charAt(l) == s.charAt(r)) {
    char ch = s.charAt(l);
    while (l <= r && ch == s.charAt(l)) {
      l++;
    }
    //r equal karna padhe ga like ase ex -> aabccba
    while (l <= r && ch == s.charAt(r)) {
      r--;
    }
  }
  return r - l + 1;
}
// Set Matrix Zeroes
public void setZeroes(int[][] matrix) {
  int n = matrix.length;
  int m = matrix[0].length;

  boolean row = false, col = false;

  for (int i = 0; i < n; i++) if (matrix[i][0] == 0) col = true;
  for (int j = 0; j < m; j++) if (matrix[0][j] == 0) row = true;

  for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
          if (matrix[i][j] == 0) {
              matrix[0][j] = 0;
              matrix[i][0] = 0;
          }
      }
  }

  for (int i = 1; i < n; i++) {
      if (matrix[i][0] == 0) {
          for (int j = 0; j < m; j++) {
              matrix[i][j] = 0;
          }
      }
  }

  for (int j = 1; j < m; j++) {
      if (matrix[0][j] == 0) {
          for (int i = 0; i < n; i++) {
              matrix[i][j] = 0;
          }
      }
  }

  if (row) {
      for (int j = 0; j < m; j++) {
          matrix[0][j] = 0;
      }
  }
  if (col) {
      for (int i = 0; i < n; i++) {
          matrix[i][0] = 0;
      }
  }
}
//Number of Sub-arrays With Odd Sum
//
public int numOfSubarrays(int[] presum) {
  int even = 0;
  int odd = 0;
  int sum = 0;
  long count = 0;
  for (int i = 0; i < presum.length; i++) {
      sum += presum[i];
      if (sum % 2 == 0) {
        //yha plus one is lia nhi kia kuki even sum tha to bas even hata ke
        //odd count add
          count+= odd;
          even++;
      } else {
        //yha dekh odd element or total bhi odd hai to is lia 1 add hua
          count+= even+1;
          odd++;
      }
  }
  return (int)(count%1000000007);
}
//Top K Frequent Elements
public int[] topKFrequent(int[] nums, int k) {
  HashMap<Integer, Integer> map = new HashMap<>();
  for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
  }
  PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
  for (int n : map.keySet()) {
      pq.add(n);
      if (pq.size() > k) {
          pq.poll();
      }
  }
  int[] ans = new int[pq.size()];
  int i = 0;
  while (pq.size() > 0) {
      ans[i] = pq.poll();
      i++;
  }
  return ans;
}
//Word Search
public boolean exist(char[][] board, String word) {
  boolean[][] visit = new boolean[board.length][board[0].length];
  for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
          if (board[i][j] == word.charAt(0)) {
              if (help(board, word, i, j, 0, visit)) return true;
          }
      }
  }
  return false;
}

public boolean help(char[][] board, String word, int i, int j, int x, boolean[][] visit) {
  if (x == word.length()) return true;
  if (j < 0 || i < 0 || i >= board.length || j >= board[0].length||visit[i][j]) return false;
  if (board[i][j] != word.charAt(x)) return false;
  boolean res = false;
  visit[i][j]=true;
  res = res || help(board, word, i + 1, j, x + 1, visit) || help(board, word, i, j + 1, x + 1, visit) || help(board, word, i - 1, j, x + 1,visit) || help(board, word, i, j - 1, x + 1,visit);
  visit[i][j]=false;
  return res;
}
//2 Sum - Target Sum Unique Pairs
public static List<List<Integer>> twoSum(int[] arr, int target) {
  List<List<Integer>> res = new ArrayList<>();
  int left = 0;
  int right = arr.length - 1;
  Arrays.sort(arr);
  while (left < right) {
    if (left != 0 && arr[left] == arr[left - 1]) {
      left++;
      continue;
    }
    int sum = arr[left] + arr[right];
    if (sum == target) {
      ArrayList<Integer>ans = new ArrayList<>();

      ans.add(arr[left]);
      ans.add(arr[right]);
      res.add(ans);
      left++;
      right--;

    } else if (sum > target) {
      right--;

    } else {
      left++;
    }
  }

  return res;
}
//3Sum
public List<List<Integer>> threeSum(int[] arr) {
  List<List<Integer>> res = new ArrayList<>();
  Arrays.sort(arr);
  for (int i = 0; i <= arr.length - 3; i++) {
      if (i != 0 && arr[i - 1] == arr[i]) continue;
      int v = arr[i];
      int target = 0 - v;
      List<List<Integer>> two = twoSum(arr, target, i + 1, arr.length - 1);
      for (List<Integer> val : two) {
          val.add(v);
          res.add(val);
      }
  }
  return res;
}

public  List<List<Integer>> twoSum(int[] arr, int target, int s, int l) {
  List<List<Integer>> res = new ArrayList<>();
  int left = s;
  int right = l;
  Arrays.sort(arr);
  while (left < right) {
      if (left !=s && arr[left] == arr[left - 1]) {
          left++;
          continue;
      }
      int sum = arr[left] + arr[right];
      if (sum == target) {
          ArrayList<Integer> ans = new ArrayList<>();

          ans.add(arr[left]);
          ans.add(arr[right]);
          res.add(ans);
          left++;
          right--;
      } else if (sum > target) {
          right--;
      } else {
          left++;
      }
  }

  return res;
}
//4 sum





