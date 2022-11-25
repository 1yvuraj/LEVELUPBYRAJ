//Segregate 0s and 1s
class Solution {
    void segregate0and1(int[] arr, int n) {
        int pt1=-1;
        int itr=0;
        while(itr<arr.length){
            if(arr[itr]==0){
                swap(arr,++pt1,itr);
            }
            itr++;
        }
    }
    void swap(int[] arr,int i,int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

}
//Sort an array of 0s, 1s and 2s
class Solution
{
    public static void sort012(int arr[], int n)
    {
        // code here 
         int pt1=-1;
        int itr=0;
        int ptr2=arr.length-1;
        while(itr<=ptr2){
            if(arr[itr]==0){
                swap(arr,++pt1,itr);
                itr++;
            }else if(arr[itr]==1){
                itr++;
            } else if(arr[itr]==2){
                swap(arr,itr,ptr2);
                ptr2--;
            }
           
            
        }
    }
    static void swap(int[] arr,int i,int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

}
//Max sum in the configuration
class GfG
{
    int max_sum(int A[], int n)
    {
	   int sum=0;
	   int arrsum=0;
	   for(int i=0;i<A.length;i++){
	       sum+=i*A[i];
	       arrsum+=A[i];
	   }
	   int max=sum;
	   for(int i=1;i<A.length;i++){
	      sum=sum-arrsum+A[i-1]*n;
	      max=Math.max(max,sum);
	   }
	   return max;
    }	
}
//Container With Most Water
class Solution {
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int maxarea = 0;

        while (l < r) {
            maxarea = Math.max(maxarea, (r - l) * Math.min(height[l], height[r]));
            if (height[l] < height[r]) l++; else r--;
        }
        return maxarea;
    }
}

// Longest Substring Without Repeating Characters
//first line matlb frequency zero se badi hai matlb phale 1 thi to ab 2 hogi to matlb repeat to count increse
//second matlb agar 1 se badi hai to repeated hai to si increase karte rhe ge or or or frequency decrese or jase
//1 hui matlb unique hai 
//freq[s.charAt(ei++)]++ >0 agar conditiong galte bhi ho  tab bhi increment ya dec hoga hi
class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();

        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        int[] freq = new int[128]; // vector<int> freq(128,0);

        while (ei < n) {
            if (freq[s.charAt(ei)] == 1) {
                count++;
            }
            freq[s.charAt(ei)]++;
            ei++;

            while (count > 0) {
                if (freq[s.charAt(si)] == 2) {
                    count--;
                }
                freq[s.charAt(si)]--;
                si++;
            }

            len = Math.max(len, ei - si);
        }
        return len;
    }
}
//agar upper me string print bhi karni ho to
public int lengthOfLongestSubstring(String s) {
    if (s.length() <= 1)
        return s.length();

    int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
    int[] freq = new int[128]; // vector<int> freq(128,0);

    int gsi = 0, gei = 0;
    while (ei < n) {
        if (freq[s.charAt(ei++)]++ > 0)
            count++;

        while (count > 0)
            if (freq[s.charAt(si++)]-- > 1)
                count--;

        if (ei - si > len) {
            len = ei - si;
            gsi = si;
            gei = ei;
        }
    }

    // System.out.println(s.substring(gsi,gei));

    return len;
}
//Longest Substring with At Most Two Distinct Characters

public class Solution {
    /**
     * @param s: a string
     * @return: the length of the longest substring T that contains at most 2 distinct characters
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // Write your code here
         if (s.length() <= 1) return s.length();

        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        int[] freq = new int[128]; // vector<int> freq(128,0);

        while (ei < n) {
            //kuki distict character dekhne hai to zero kia hai
            if (freq[s.charAt(ei)] == 0) {
                count++;
            }
            freq[s.charAt(ei)]++;
            ei++;
            //kuki do distint hone chia 
            while (count > 2) {
                //matlb 2 distint character bache hai to count decrese
                if (freq[s.charAt(si)] == 1) {
                    count--;
                }
                freq[s.charAt(si)]--;
                si++;
            }

            len = Math.max(len, ei - si);
        }
        return len;
    }
}
//76. Minimum Window Substring
//Input: s = "ADOBECODEBANC", t = "ABC"
// Output: "BANC"
// Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

class Solution {

    public String minWindow(String s, String t) {
        int ns = s.length(), nt = t.length(), si = 0, ei = 0, count = nt, len = (int) 1e9;
        String ans = "";
        int[] freq = new int[128];
        for (int i = 0; i < nt; i++) {
            freq[t.charAt(i)]++;
        }
        while (ei < ns) {
            if (freq[s.charAt(ei)] > 0) count--;
            freq[s.charAt(ei)]--;
            ei++;

            while(count == 0) {
                if (len > ei - si) {
                    ans=s.substring(si,ei);
                    len = ei - si;
                }
                //mtlb ecess me nhi hai is lia count increase kia
                
                if(freq[s.charAt(si)]==0)count++;
                freq[s.charAt(si)]++;
                si++;
            }
        }
        return len == (int) 1e9 ? "" : ans;
    }
}
//Smallest distinct window
class Solution {
    public int findSubString( String s) {
       int ns = s.length(),  si = 0, ei = 0, count=0, len = (int) 1e9;
       
        String ans = "";
        int[] freq = new int[128];
        for (int i = 0; i < ns; i++) {
            if(freq[s.charAt(i)]==0){
                freq[s.charAt(i)]++;
                count++;
            }
        }
        while (ei < ns) {
            if (freq[s.charAt(ei)] >0) count--;
            freq[s.charAt(ei)]--;
            ei++;

            while(count == 0) {
                if (len > ei - si) {
                    ans=s.substring(si,ei);
                    len = ei - si;
                }
                if(freq[s.charAt(si)]==0)count++;
                freq[s.charAt(si)]++;
                si++;
            }
        }
        return len == (int) 1e9 ? 0: len;
    }
}
// Longest Substring with At Most K Distinct Characters

public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if (s.length() <= k) return s.length();

   int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
   int[] freq = new int[128]; // vector<int> freq(128,0);

   while (ei < n) {
       //kuki distict character dekhne hai to zero kia hai
       if (freq[s.charAt(ei)] == 0) {
           count++;
       }
       freq[s.charAt(ei)]++;
       ei++;
       //kuki do distint hone chia 
       while (count>k) {
           //matlb 2 distint character bache hai to count decrese
          
           if (freq[s.charAt(si)] == 1) {
               count--;
           }
           freq[s.charAt(si)]--;
           si++;
       }
       //ye bhar is lia kuki atmost bola hai to 1 se k tak or while me likha mtlb k hi ho to to waha len likhte
        len = Math.max(len, ei - si); 
   }
   return len;
}
//Maximum Number of Vowels in a Substring of Given Length

class Solution {
    public int maxVowels(String s, int k) {
        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        while (ei < n) {
            //kuki distict character dekhne hai to zero kia hai
            if (s.charAt(ei) == 'a' || s.charAt(ei) == 'i' || s.charAt(ei) == 'o' || s.charAt(ei) == 'u' || s.charAt(ei) == 'e') {
                count++;
            }

            ei++;
            //kuki do distint hone chia
            if (ei - si == k) {
                //matlb 2 distint character bache hai to count decrese
                len = Math.max(count, len);
                if (s.charAt(si) == 'a' || s.charAt(si) == 'i' || s.charAt(si) == 'o' || s.charAt(si) == 'u' || s.charAt(si) == 'e') {
                    count--;
                }
                si++;
            }
        }
        return len;
    }
}

//Subarrays with K Different Integers
//dekh isme karna tha k hone chia to hum to ak examle dekh
//k=4 -> 1,2,3,4
//k=3->1,2,3
//to matlb k(4)-k(3)==to exacly k a jai ge

class Solution {

    public int help(int[] s, int k) {
        int n = s.length, si = 0, ei = 0, count = 0, len = 0;
        int[] freq = new int[s.length + 1]; // vector<int> freq(128,0);

        while (ei < n) {
            //kuki distict character dekhne hai to zero kia hai
            if (freq[s[ei]] == 0) {
                count++;
            }
            //len++;
            freq[s[ei]]++;
            ei++;
            //kuki do distint hone chia
            while (count > k) {
                //matlb 2 distint character bache hai to count decrese

                if (freq[s[si]] == 1) {
                    count--;
                }
                freq[s[si]]--;
                si++;
            }
            len += ei - si;
        }
        return len;
    }

    public int subarraysWithKDistinct(int[] s, int k) {
        return help(s, k) - help(s, k - 1);
    }
}
//Count Number of Nice Subarrays
//boi k se k-1 kar do
class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        return help(nums, k) - help(nums, k - 1);
    }

    public int help(int[] nums, int k) {
        int n = nums.length, si = 0, ei = 0, count = 0, len = 0;
        while (ei < n) {
            //kuki distict character dekhne hai to zero kia hai
            if (nums[ei] % 2 == 1) {
                count++;
            }

            ei++;
            //kuki do distint hone chia
            while (count > k) {
                //matlb 2 distint character bache hai to count decrese

                if (nums[si] % 2 == 1) {
                    count--;
                }
                si++;
            }
            len += ei - si;
        }
        return len;
    }
}
//Fruit Into Baskets
//boi 2 bucket hai jis me jo daal dia fruit boi us me dale ka to at most 2 wala ho gya
//sun is me ye bola hai kli bucket me ak jase fruit hoge pr agar ye nhi bola ki dono bucket fill karni hi hai
//agar bola hota to bas len wale me check if(count==2) laga date
class Solution {
    public int totalFruit(int[] s) {
         int n = s.length, si = 0, ei = 0, count = 0, len = 0;
        int[] freq = new int[s.length + 1]; // vector<int> freq(128,0);

        while (ei < n) {
            //kuki distict character dekhne hai to zero kia hai
            if (freq[s[ei]] == 0) {
                count++;
            }
            //len++;
            freq[s[ei]]++;
            ei++;
            //kuki do distint hone chia
            while (count > 2) {
                //matlb 2 distint character bache hai to count decrese
               
                if (freq[s[si]] == 1) {
                    count--;
                }
                freq[s[si]]--;
                si++;
            }
             len=Math.max(len,ei-si);
        }
        return len;
            
    }
}
//Binary Subarrays With Sum boi sum nicklana hai to (sum -sum-1) karo to sum a jai ga
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return help(nums, goal) - (goal != 0 ? help(nums, goal - 1) : 0);
    }

    public int help(int[] nums, int goal) {
        int si = 0, ei = 0, count = 0, len = 0;
        // vector<int> freq(128,0);

        while (ei < nums.length) {
            count += nums[ei];

            ei++;
            //kuki do distint hone chia
            while (count > goal) {
                //matlb 2 distint character bache hai to count decres

                if (nums[si] == 1) {
                    count -= nums[si];
                }

                si++;
            }
            len += ei - si;
        }
        return len;
    }
}
//Max Consecutive Ones
class Solution {
    public int findMaxConsecutiveOnes(int[] s) {
        int n = s.length, si = 0, ei = 0, count = 0, len = 0;
        while (ei < n) {
            //kuki distict character dekhne hai to zero kia hai
            if (s[ei] == 0) {
                count++;
            }
            ei++;
            //kuki do distint hone chia
            while (count == 1) {
                //matlb 2 distint character bache hai to count decrese

                if (s[si] == 0) {
                    count--;
                }
                si++;
            }

            len = Math.max(len, ei - si);
        }
        return len;
    }
}
// Max Consecutive Ones II
public int findMaxConsecutiveOnes(int[] s) {
    int n = s.length, si = 0, ei = 0, count = 0, len = 0;
     while (ei < n) {
         //kuki distict character dekhne hai to zero kia hai
         if (s[ei] == 0) {
             count++;
         }
         ei++;
         //kuki do distint hone chia
         while (count > 1) {
             //matlb 2 distint character bache hai to count decrese

             if (s[si] == 0) {
                 count--;
             }
             si++;
             
         }  
         len = Math.max(len, ei - si);
     }
     return len;
 }
 //Max Consecutive Ones III
 //upper tha 1 ko kar sakte hai yha hai k ko karna hai
 class Solution {

    public int longestOnes(int[] s, int k) {
        int n = s.length, si = 0, ei = 0, count = 0, len = 0;
        while (ei < n) {
            //kuki distict character dekhne hai to zero kia hai
            if (s[ei] == 0) {
                count++;
            }
            ei++;
            //kuki do distint hone chia
            while (count > k) {
                //matlb 2 distint character bache hai to count decrese

                if (s[si] == 0) {
                    count--;
                }
                si++;
            }
            len = Math.max(len, ei - si);
        }
        return len;
    }
}
// Subarray Sums Divisible by K
//dekh matln zero pe 1 lia hai kuki sare subarry count kar rhe per agar length nickalni hai ti hum zero pe -1 
//le ge
//or agar x hai subarry or aagr jaate ak or x to matlb phale wale se ke baad se ab tak ak subarry hai
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = sum % k;
            if (rem < 0) {
                rem += k;
            }
            if (map.containsKey(rem)) {
                ans += map.get(rem);
                map.put(rem, map.get(rem) + 1);
            } else {
                map.put(rem, 1);
            }
        }
        return ans;
    }
}
//arr se
class Solution {
    public int subarraysDivByK(int[] n, int k) {
        int[]nums=new int[30000];
        nums[0]=1;
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < n.length; i++) {
            sum += n[i];
            int rem = sum % k;
            if (rem < 0) {
                rem += k;
            }
            if (nums[rem]>0) {
                ans += nums[rem];
                nums[rem]++;
            } else {
               nums[rem]=1;
            }
        }
        return ans;
    }
}
//longest Subarray Sums Divisible by K
class Solution {
    public int subarraysDivByK(int[] n, int k) {
        int[] nums = new int[30000];
        Arrays.fill(nums, -2);
        nums[0] = -1;
        int ans = 0;
        int sum = 0;
        int len = 0;
        for (int i = 0; i < n.length; i++) {
            sum += n[i];
            int rem = sum % k;
            if (rem < 0) {
                rem += k;
            }
            if (nums[rem] == -2) {
                nums[rem] = i;
            } else {
                len = Math.max(len, i - nums[rem]);
            }
        }
        return len;
    }
}
//Largest subarray of 0's and 1's

class Solution {
    int maxLen(int[] nums, int N)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(nums[i]==0)sum+=-1;
            if (map.containsKey(sum)) {
               ans=Math.max(ans,i-map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
//. Contiguous Array
//boi zero or one ka max length btao continues ho
class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] == 0) sum += -1;
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
// Sliding Window Maximum
// Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
// Output: [3,3,5,5,6,7]
// Explanation: 
// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7
//nlogn
//n
class Solution {
    private class pair implements Comparable<pair> {
        int value;
        int idx;
        pair(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        public int compareTo(pair o) {
            return o.value - this.value;
        }
    }
    public int[] maxSlidingWindow(int[] arr, int k) {
        PriorityQueue<pair> pq = new PriorityQueue<>();
        int y = 0;
        int[] ans = new int[arr.length - k + 1];
        for (int i = 0; i < arr.length; i++) {
            while (pq.size() > 0 && pq.peek().idx <= i - k) {
                pq.remove();
            }
            pq.add(new pair(arr[i], i));
            if (i >= k - 1) ans[y++] = pq.peek().value;
        }
        return ans;
    }
}
//nlogk
.//k-->space
//or front me big element hona chia
//bas is me dequeue lia hai or range me hai to sai hai warna first ko remove and ak chiz
//or ki agar arr elemnet big hai to bo dequue se element remove kre ga
public int[] maxSlidingWindow(int[] arr, int k) {
    LinkedList<Integer> dq = new LinkedList<>();
    int y = 0;
    int[] ans = new int[arr.length - k + 1];
    for (int i = 0; i < arr.length; i++) {
        while (dq.size() > 0 && dq.getFirst() <= i - k) {
            dq.removeFirst();
        }
        while (dq.size() > 0 && arr[dq.getLast()] <= arr[i]) {
            dq.removeLast();
        }
        dq.addLast(i);
        if (i >= k - 1) ans[y++] = arr[dq.getFirst()];
    }
    return ans;
}
//Khans algo
class Solution {
    public int maxSubArray(int[] nums) {
        int gsum = 0, cursum = 0;
        for (int i = 0; i < nums.length; i++) {
            cursum += nums[i];
            if (cursum > gsum) gsum = cursum;
            if (cursum <= 0) {
                cursum = 0;
            }
        }
        if (gsum == 0) {
            Arrays.sort(nums);
        }
        return gsum == 0 ? nums[nums.length - 1] : gsum;
    }
}

//Khans algo
//agar subarray chia ho to 
public int maxSubArray(int[] nums) {
    int gsum = 0, cursum = 0, gs = 0, ge = 0, cs = 0;
    for (int i = 0; i < nums.length; i++) {
        cursum += nums[i];
        if (cursum > gsum) {
            gsum = cursum;
            gs = cs;
            ge = i;
        }
        if (cursum <= 0) {
            cursum = 0;
            cs = i + 1;
        }
    }
    if (gsum == 0) {
        Arrays.sort(nums);
    }
    System.out.println(gs + " " + ge);
    return gsum == 0 ? nums[nums.length - 1] : gsum;
}
//. K-Concatenation Maximum Sum
//agar k==1 hai to simple kadane
//agar sum negative hai to simple kadans of first 2
//agar sum positive hai to kadans of first 2+(k-2)*sum;
class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] % ((int) 1e9 + 7);
        }
        if (k == 1) {
            return k(arr, k) % ((int) 1e9 + 7);
        } else if (sum < 0) {
            return ks(arr, k) % ((int) 1e9 + 7);
        } else {
            return (int) (ks(arr, k) % ((int) 1e9 + 7) + ((k - 2) * sum) % ((int) 1e9 + 7));
        }
    }
    public int k(int[] arr, int k) {
        long gsum = 0, cursum = 0;
        for (int i = 0; i < arr.length; i++) {
            cursum += arr[i] % ((int) 1e9 + 7);
            if (cursum > gsum) gsum = cursum % ((int) 1e9 + 7);
            if (cursum <= 0) {
                cursum = 0;
            }
        }
        return (int) (gsum % ((int) 1e9 + 7));
    }

    public int ks(int[] arr, int k) {
        int[] ans = new int[arr.length * 2];
        int y = 0;
        for (int i = 0; i < ans.length; i++) {
            ans[y] = arr[i % arr.length];
            y++;
        }
        return k(ans, k) % ((int) 1e9 + 7);
    }
}
//Maximum sum Rectangle
//tc n3 or more specfic--> 2nnm
//sp-m
class Solution {

    int maximumSumRectangle(int R, int C, int M[][]) {
        int sum = -(int) 1e9;
        int[] cursum = new int[C];
        //n
        for (int i = 0; i < M.length; i++) {  
            Arrays.fill(cursum, 0);
            //2n*m
            for (int j = i; j < M.length; j++) {
                for (int k = 0; k < M[0].length; k++) cursum[k] += M[j][k];
                sum = Math.max(sum, maxSubArray(cursum));
            }
        }
        return sum;
    }

    public int maxSubArray(int[] nums) {
        int gsum = -(int) 1e9, cursum = 0;
        for (int i = 0; i < nums.length; i++) {
            cursum += nums[i];
            if (cursum > gsum) {
                gsum = cursum;
            }
            if (cursum <= 0) {
                cursum = 0;
            }
        }
        return gsum;
    }
}
//upper wale me matrix print karna ho to
class Solution {

    int maximumSumRectangle(int R, int C, int M[][]) {
        int r1 = 0, r2 = 0, c1 = 0, c2 = 0;
        int max = -(int) 1e9;
        int[] cursum = new int[C];
        for (int i = 0; i < M.length; i++) {
            Arrays.fill(cursum, 0);
            for (int j = i; j < M.length; j++) {
                for (int k = 0; k < M[0].length; k++) cursum[k] += M[j][k];
                int[] sum = maxSubArray(cursum);
                if (sum[2] > max) {
                    max = sum[2];
                    r1 = i;
                    r2 = j;
                    c1 = sum[0];
                    c2 = sum[1];
                }
            }
        }
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
        return max;
    }

    public int[] maxSubArray(int[] nums) {
        int gsum = -(int) 1e9, cursum = 0, gsi = 0, gei = 0, cs = 0;
        for (int i = 0; i < nums.length; i++) {
            cursum += nums[i];
            if (cursum > gsum) {
                gsum = cursum;
                gsi = cs;
                gei = i;
            }
            if (cursum <= 0) {
                cursum = 0;
                cs = i + 1;
            }
        }
        return new int[] { gsi, gei, gsum };
    }
}
// Number of Submatrices That Sum to Target
//bas wase jase 1 d me kia tha k sum nickalo wase hi 2 d me bo wali trick se kar dia
//fix karte hai jo upper wale me kia tha
class Solution {
    public int numSubmatrixSumTarget(int[][] M, int target) {
        int max = -(int) 1e9;
        int[] cursum = new int[M[0].length];
        int sum1 = 0;
        int sum = 0;
        for (int i = 0; i < M.length; i++) {
            Arrays.fill(cursum, 0);
            for (int j = i; j < M.length; j++) {
                for (int k = 0; k < M[0].length; k++) cursum[k] += M[j][k];
                sum1 += subarraySum(cursum, target);
            }
        }
        return sum1;
    }

    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(sum, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }

        return ans;
    }
}

//Rabbits in Forest
//yar boi agar rabbit first baar bola me n hu to ans me ans me n+1;
//per agar n se bada ho gya to dubra add hoga
class Solution {
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < answers.length; i++) {
            if (map.containsKey(answers[i])) {
                map.put(answers[i], map.get(answers[i]) + 1);
            } else {
                map.put(answers[i], 1);
                ans += answers[i] + 1;
            }
            if (map.get(answers[i]) == (answers[i] + 1)) map.remove(answers[i]);
        }
        return ans;
    }
}
//Subarray Sum Equals K
class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(sum, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        return ans;
    }
}
//977. Squares of a Sorted Array
//https://leetcode.com/problems/squares-of-a-sorted-array/
// Input: nums = [-4,-1,0,3,10]
// Output: [0,1,9,16,100]
// Explanation: After squaring, the array becomes [16,1,0,9,100].
// After sorting, it becomes [0,1,9,16,100].
class Solution {
    public int[] sortedSquares(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int[] ans = new int[nums.length];
        int idx = nums.length - 1;
        while (l <=r) {
            if (Math.abs(nums[l]) >=Math.abs(nums[r])) {
                ans[idx] = Math.abs(nums[l] * nums[l]);
                l++;
                idx--;
            } else {
                ans[idx] = Math.abs(nums[r] * nums[r]);
                r--;
                idx--;
            }
        }
        return ans;
    }
}
//Next Greater Element III
// Input: n = 12
// Output: 21
public int nextGreaterElement(int n) {
    long m = n;
    char[] number = String.valueOf(m).toCharArray();
    int idx = -1;
    for (int i = number.length - 1; i > 0; i--) {
        if (number[i - 1] < number[i]) {
            idx = i - 1;
            break;
        }
    }
    if (idx == -1) return -1;

    for (int i = number.length - 1; i > idx; i--) {
        if (number[i] > number[idx]) {
            swap(number, i, idx);
            break;
        }
    }
    reverse(number, idx + 1, number.length - 1);
    String str = new String(number);
    if (Long.parseLong(str) <= Integer.MAX_VALUE) {
        return Integer.parseInt(str);
    } else {
        return -1;
    }
}

public static void swap(char[] ch, int i, int j) {
    char temp = ch[i];
    ch[i] = ch[j];
    ch[j] = temp;
}

public static void reverse(char[] ch, int i, int j) {
    while (i < j) {
        swap(ch, i, j);
        i++;
        j--;
    }
}

// Majority Element
//https://leetcode.com/problems/majority-element/
class Solution {
    public int majorityElement(int[] nums) {
        int count = 1;
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ans) {
                ans = nums[i];
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                count = 1;
                ans = nums[i];
            }
        }
        return ans;
    }
}
// Max Chunks To Make Sorted
class Solution {

    public int maxChunksToSorted(int[] arr) {
        int count = 0;
        int max = -(int) 1e9;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            if (max == i) count++;
        }
        return count;
    }
}
// Max Consecutive Ones III
//https://leetcode.com/problems/max-consecutive-ones-iii/
class Solution {
    public int longestOnes(int[] nums, int k) {
        int count = 0, si = 0, ei = 0;
        int len = 0;
        while (ei < nums.length) {
            if (nums[ei] == 0) {
                count++;
            }
            ei++;
            while (count > k) {
                if (nums[si] == 0) count--;
                si++;
            }
            len = Math.max(len, ei - si);
        }
        return len;
    }
}
//Chocolate Distribution Problem
//boi yar m bacho ko ak ak choclate packet dana hai or ans bo hai jis me min and max ka
//diff sabse kaam ho
//https://practice.geeksforgeeks.org/problems/chocolate-distribution-problem3825/1
    public long findMinDiff (ArrayList<Integer> a, int n, int m)
    {
        Collections.sort(a);
        if(m>n)return 0;
        int ans=(int)1e9;
        for(int i=0;i<=n-m;i++){
            ans=Math.min(ans,a.get(i+m-1)-a.get(i));
        }
        if(n==m)return a.get(n-1)-a.get(0);
        
        return ans;
    }

//. Divide Intervals Into Minimum Number of Groups
//boi hai ki rnage addition wala 
class Solution {
    public int minGroups(int[][] intervals) {
        int[]arr=new int[10000001];
        int ans=0;
        int max=0;
        for(int[]val:intervals){
           arr[val[0]]++;
           arr[val[1]+1]--;
        }
        for(int i=0;i<arr.length;i++){
            ans+=arr[i];
            max=Math.max(max,ans);
        }
        return max;
    }
}




















