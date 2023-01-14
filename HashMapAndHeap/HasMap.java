
public class HasMap {
    //X of a Kind in a Deck of Cards
    //https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/
    public boolean hasGroupsSizeX(int[] deck) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int min = (int) 1e9;

        for (int i = 0; i < deck.length; i++) {
            if (map.containsKey(deck[i])) {
                map.put(deck[i], map.get(deck[i]) + 1);
            } else {
                map.put(deck[i], 1);
            }
        }
        int ans = map.get(deck[0]);
        for (int i = 0; i < deck.length; i++) {
            ans = gcd(ans, map.get(deck[i]));
            if (ans < 2) return false;
        }

        return true;
    }

    public int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
//Check Arithmetic Progression
boolean checkIsAP(int arr[] ,int n)
{
Arrays.sort(arr);
int ans=arr[1]-arr[0];
for(int i=1;i<arr.length-1;i++){
 if((arr[i+1]-arr[i])!=ans)return false;
}
return true;
}

//Tricky Sorting Cost
// You have to sort the integers in ascending order by the following operation. Operation is to pick an integer and place it at end or at start. 
// Input: N = 3
// arr = {2, 1, 3}
// Output: 1
// Explaination: Place 1 at start.
//https://practice.geeksforgeeks.org/problems/morning-assembly3038/1
static int sortingCost(int N, int arr[]){
    // code here
    HashMap<Integer,Integer>map=new HashMap<>();
    int l=1;
    int max=1;
     for(int i=0;i<arr.length;i++){
       if(map.containsKey(arr[i]-1)){
           int len=map.get(arr[i]-1);
           map.put(arr[i],len+1);
           max=Math.max(max,map.get(arr[i]));
           
       }else{
         map.put(arr[i],1);  
       }
     }
     return arr.length-max;
}
//871. Minimum Number of Refueling Stops(gas station)
//https://leetcode.com/problems/minimum-number-of-refueling-stops/
// Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
// Output: 2
// Explanation: We start with 10 liters of fuel.
// We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
// Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
// and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
// We made 2 refueling stops along the way, so we return 2.
public int minRefuelStops(int target, int startFuel, int[][] stations) {
    PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
    int distace=startFuel;
    int idx=0;
    int ans=0;
    while(distace<target){
        while(idx<stations.length && stations[idx][0]<=distace){
            q.add(stations[idx][1]);
            idx++;
        }
        if(q.isEmpty())return -1;
        distace+=q.remove();
        ans++;
    }
    return ans;
}
// Rabbits in Forest
public int numRabbits(int[] arr) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
        if (map.containsKey(arr[i])) {
            map.put(arr[i], map.get(arr[i]) + 1);
        } else {
            map.put(arr[i], 1);
        }
    }
    int ans = 0;
    for (int key : map.keySet()) {
        ans += (int) Math.ceil(map.get(key) * 1.0 / (key + 1) * 1.0) * (key + 1);
    }
    return ans;
}
//Array of Doubled Pairs
//sort is lia kia kuki -2 ke lia -4 chia hana
//per agar sort nhi hua
// to phale -4 aya to hum -2 chia per hum code 2*val check kar rhe is lia sort kia
public boolean canReorderDoubled(int[] arr) {
    Arrays.sort(arr);
    HashMap<Integer, Integer> hmap = new HashMap<>();

    for (int val : arr) {
        if (val != 0) {
            if (hmap.containsKey(val)) {
                hmap.put(val, hmap.get(val) + 1);
            } else {
                hmap.put(val, 1);
            }
        }
    }

    for (int val : arr) {
        if(hmap.containsKey(val) && hmap.containsKey(2*val)){
            hmap.put(val,hmap.get(val)-1);
             hmap.put(val*2,hmap.get(2*val)-1);
             if(hmap.get(val)==0)hmap.remove(val);
             if(hmap.get(2*val)==0)hmap.remove(2*val);
            
        }
    }

    return hmap.size() == 0;
}

//128. Longest Consecutive Sequence
// Input: nums = [100,4,200,1,3,2]
// Output: 4
// Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
public int longestConsecutive(int[] nums) {
    HashMap<Integer,Boolean>map=new HashMap<>();
    for(int i=0;i<nums.length;i++){
        map.put(nums[i],true);
    }
    for(int i=0;i<nums.length;i++){
        if(map.containsKey(nums[i]-1)){
            map.put(nums[i],false);
        }
    }
    int k=1;
    int ans=1;
    int max=0;
    for(int i=0;i<nums.length;i++){
        while(map.get(nums[i]) && map.containsKey(nums[i]+k)){
            ans++;
            k++;
        }
        if(max<ans)max=ans;
        ans=1;
        k=1;
        
    }
    return max;
}

// count of Subarray Sum Equals K
public int subarraySum(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int ans = 0;int sum=0;
    for (int i = 0; i < nums.length; i++) {
        sum+=nums[i];
        if(map.containsKey(sum-k))ans+=map.get(sum-k);
        if (map.containsKey(sum)) {
            map.put(sum, map.get(sum) + 1);
        } else {
            map.put(sum, 1);
        }
    }
    return ans;
}

//525. Contiguous Array
//Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
public int findMaxLength(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int sum = 0;
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (nums[i] == 0) sum += -1;
        if (map.containsKey(sum)) {
            max = Math.max(max, i - map.get(sum));
        } else {
            map.put(sum, i);
        }
    }
    return max;
}

// Subarrays with equal 1s and 0s
static int countSubarrWithEqualZeroAndOne(int nums[], int n)
{
HashMap<Integer, Integer> map = new HashMap<>();
map.put(0, 1);
int sum = 0;
int max = 0;
for (int i = 0; i < nums.length; i++) {
    sum += nums[i];
    if (nums[i] == 0) sum += -1;
    if (map.containsKey(sum)) {
        max+=map.get(sum);
        map.put(sum, map.get(sum)+1);
    } else {
        map.put(sum, 1);
    }
}
return max;
}
//Equal 0, 1 and 2
long getSubstringWithEqual012(String str) 
    { 
        HashMap<String,Integer>map=new HashMap<>();
        int c0=0;
        int c1=0;
        int c2=0;
        String ans=(c1-c0)+"#"+(c2-c1);
        long main=0;
        map.put(ans,1);
        for(int i=0;i<str.length();i++){
            int ch=str.charAt(i)-'0';
            if(ch==0){
                c0++;
            }else if(ch==1){
                c1++;
            }else{
                c2++;
            }
            String a=(c1-c0)+"#"+(c2-c1);
            if(map.containsKey(a)){
                main+=map.get(a);
                map.put(a,map.get(a)+1);
            }else{
                map.put(a,1);
            }
        }
        return main;
    }

//Max Consecutive Ones
public int findMaxConsecutiveOnes(int[] nums) {
    int si = 0;
    int ei = 0;
    int len = 0;
    int max = 0;
    while (ei < nums.length) {
        if (nums[ei] == 0) {
            len++;
        }
        ei++;
        while (len == 1) {
            if (nums[si] == 0) {
                len--;
            }
            si++;
        }
        max = Math.max(max, ei - si);
    }
    return max;
}

//Length of the largest subarray with contiguous elements
static int findLength(int arr[])
{
    int n = arr.length;
    int max_len = 1; // Initialize result

    // One by one fix the starting points
    for (int i=0; i<n-1; i++)
    {
        // Create an empty hash set and add i'th element
        // to it.
        HashSet<Integer> set = new HashSet<>();
        set.add(arr[i]);

        // Initialize max and min in current subarray
        int mn = arr[i], mx = arr[i];

        // One by one fix ending points
        for (int j=i+1; j<n; j++)
        {
            // If current element is already in hash set, then
            // this subarray cannot contain contiguous elements
            if (set.contains(arr[j]))
                break;

            // Else add current element to hash set and update
            // min, max if required.
            set.add(arr[j]);
            mn = Math.min(mn, arr[j]);
            mx = Math.max(mx, arr[j]);

            // We have already checked for duplicates, now check
            // for other property and update max_len if needed
            if (mx-mn == j-i)
                max_len = Math.max(max_len, mx-mn+1);
        }
    }
    return max_len;

    //Anagram Palindrome
    int isPossible (String S)
    {
        HashMap<Character,Integer>map=new HashMap<>();
        for(int i=0;i<S.length();i++){
            if(map.containsKey(S.charAt(i))){
                map.put(S.charAt(i),map.get(S.charAt(i))+1);
            }else{
                map.put(S.charAt(i),1);
            }
        }
        int count=0;
        for(int i=0;i<S.length();i++){
           if(map.containsKey(S.charAt(i)) && map.get(S.charAt(i))%2!=0){
               count++;
               map.remove(S.charAt(i));
           }else{
               map.remove(S.charAt(i));
           }
        }
        
        return count>1?0:1;
       
    }
    //Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        List<List<String>> a = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            String s = new String(str);
            if (map.containsKey(s)) {
                map.get(s).add(strs[i]);
            } else {
                ArrayList<String> ans = new ArrayList<>();
                map.put(s, ans);
                map.get(s).add(strs[i]);
            }
        }
        for (int i = 0; i < strs.length; i++) {
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            String s = new String(str);
            if (map.containsKey(s)) a.add(map.get(s));
            map.remove(s);
        }
        return a;
    }
    //Minimum Window Substring
    public String minWindow(String s, String t) {
        int si = 0, ei = 0, count = t.length(), len = (int) 1e9;
        String ans = "";
        int[] freq = new int[128];
        for (int i = 0; i < t.length(); i++) {
            freq[t.charAt(i)]++;
        }
        while (ei < s.length()) {
            if (freq[s.charAt(ei)] > 0) count--;
            freq[s.charAt(ei)]--;

            ei++;
            while (count == 0) {
                if (len > ei - si) {
                    len = ei - si;
                    ans = s.substring(si, ei);
                }
                if (freq[s.charAt(si)] == 0) count++;
                freq[s.charAt(si)]++;
                si++;
            }
        }
        return ans;
    }

    //Find All Anagrams in a String
    public List<Integer> findAnagrams(String s, String p) {
        int[] arr = new int[26];
        ArrayList<Integer>list=new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            arr[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if(i+p.length()-1<s.length()){
            String ans = s.substring(i, i + p.length());
            int[] subarr = new int[26];
            for (int j = 0; j < ans.length(); j++) {
                subarr[ans.charAt(j) - 'a']++;
            }
            if(Arrays.equals(arr, subarr))list.add(i);
            }
        }
        return list;
    }

    //Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        int si = 0, ei = 0;
        int[] arr = new int[128];
        int count = 0, len = 0;
        while (ei < s.length()) {
            if (arr[s.charAt(ei)] == 1) {
                count++;
            }
            arr[s.charAt(ei)]++;
            ei++;
            while (count > 0) {
                if (arr[s.charAt(si)] == 2) {
                    
                    count--;
                }
                arr[s.charAt(si)]--;
                si++;
            }
            len = Math.max(len, ei - si);
        }
        return len;
    }
    //Check if two strings are k-anagrams or not
    boolean areKAnagrams(String s1, String s2, int k) {
        // code here
        int[]arr=new int[26];
        int count=0;
        if(s1.length()!=s2.length())return false;
        for(int i=0;i<s1.length();i++){
            arr[s1.charAt(i)-'a']++;
        }
         for(int i=0;i<s2.length();i++){
            arr[s2.charAt(i)-'a']--;
        }
        
        for(int i=0;i<26;i++){
            if(arr[i]>0){
                count+=arr[i];
            }
        }
        return count<=k;
        
    }
    //Find Anagram Mappings
    //     6
    // 1 2 3 4 5 2
    // 4 3 2 1 5 2
    public static int[] anagramMappings(int[] arr1, int[] arr2) {
        int[]arr=new int[arr1.length];
        HashMap<Integer,ArrayList<Integer>>map=new HashMap<>();
        for(int i=0;i<arr2.length;i++){
            if(map.containsKey(arr2[i])){
                map.get(arr2[i]).add(i);
            }else{
                ArrayList<Integer>a=new ArrayList<>();
                a.add(i);
                map.put(arr2[i],a);
            }
        }
        for(int i=0;i<arr1.length;i++){
            arr[i]=map.get(arr1[i]).remove(0);
        }
 
         return arr;
     }
     //Isomorphic Strings
     //boi map dusri string ke sath
     public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map1.containsKey(s.charAt(i)) == false) map1.put(s.charAt(i), t.charAt(i));
            if (map2.containsKey(t.charAt(i)) == false) map2.put(t.charAt(i), s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            if (map1.get(s.charAt(i)) != t.charAt(i) || map2.get(t.charAt(i)) != s.charAt(i)) return false;
        }

        return true;
    }
    //Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer>map=new HashMap<>();
        int sum=0;
        int ans=0;
        map.put(0,1);
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(map.containsKey(sum-k)){
                ans+=map.get(sum-k);
            }
            if(map.containsKey(sum)){
                map.put(sum,map.get(sum)+1);
            }else{
                map.put(sum,1);
            }
        }
        return ans;
    }
    //Count Number of Nice Subarrays
    //boi hai sum karte rhe sum-k wala bas yha odd k hone chia the to odd ko 1 se and even ko 0 se kar dia
    public int numberOfSubarrays(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int ans = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) nums[i] = 1; else nums[i] = 0;
        }
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

    //Subarrays with K Different Integers
    public int subarraysWithKDistinct(int[] nums, int k) {
        return most(nums,k)-most(nums,k-1);
    }
    public int most(int[] nums, int k) {
        int si=0,ei=0,count=0,len=0;
        int[]arr=new int[nums.length+1];
        while(ei<nums.length){
            if(arr[nums[ei]]==0)count++;
            arr[nums[ei]]++;
            ei++;
            while(count>k){
                if(arr[nums[si]]==1){
                    count--;
                }
                arr[nums[si]]--;
                si++;
                
            }
            //k length ke abko add and k-1 walo ko and minus and ans mila jai ga
            len+=ei-si;
        }
        return len;
    }
    //Count Distinct Elements In Every Window Of Size K
    public static ArrayList<Integer> solution(int[] arr, int k) {
        //write your code here
        HashMap<Integer, Integer>map = new HashMap<>();
        ArrayList<Integer>ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
          map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        ans.add(map.size());
        if (map.get(arr[0]) == 1) {
          map.remove(arr[0]);
        } else {
          map.put(arr[0], map.get(arr[0]) - 1);
        }
        int j = 1;
        for (int i = k; i < arr.length; i++) {
          map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
          ans.add(map.size());
          if (map.get(arr[j]) == 1) {
            map.remove(arr[j]);
          } else {
            map.put(arr[j], map.get(arr[j]) - 1);
          }
          j++;
        }
        return ans;
      }

//Largest subarray with 0 sum
int maxLen(int arr[], int n)
    {
    HashMap<Integer, Integer>map = new HashMap<>();
    map.put(0, -1);
    int sum = 0;
    int max = -(int)1e9;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      if (map.containsKey(sum)) {
        max = Math.max(max, i - map.get(sum));
      }
      if (!map.containsKey(sum)) {
        map.put(sum, i);
      }

    }
    return max==-(int)1e9?0:max;
    }
    
//count all sunarry with sum zero;
    public static long findSubarray(long[] arr ,int n) 
    {
      HashMap<Long, Long>map = new HashMap<>();
      map.put(0L, 1L);
      long sum = 0;
      long count=0;
      for (int i = 0; i < arr.length; i++) {
        sum += arr[i];
        if (map.containsKey(sum)) {
         count+=map.get(sum);
         
        }
        map.put(sum,map.getOrDefault(sum,0L)+1L);

      }
      return  count;
    }
//Largest Subarray With Contiguous Elements
public static int solution(int[] arr) {
    // write your code here
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      int max = arr[i];
      int min = arr[i];
      HashSet<Integer>set = new HashSet<>();
      set.add(arr[i]);
      for (int j = i + 1; j < arr.length; j++) {
        if (set.contains(arr[j]))break;
        set.add(arr[j]);
        max = Math.max(max, arr[j]);
        min = Math.min(min, arr[j]);
        if (max - min == j - i) {
          count = Math.max(count, j - i + 1);
        }
      }
    }
    return count;
  }

  //Smallest window in a string containing all the characters of another string
  public static String smallestWindow(String s, String t)
    {
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
        return len == (int) 1e9 ? "-1" : ans;
    }

    //Smallest Substring Of A String Containing All Unique Characters Of Itself
    public static int solution(String s) {
        int ns = s.length(),  si = 0, ei = 0, count = 0, len = (int) 1e9;
    
        String ans = "";
        int[] freq = new int[128];
        for (int i = 0; i < ns; i++) {
          if (freq[s.charAt(i)] == 0) {
            freq[s.charAt(i)]++;
            count++;
          }
        }
        while (ei < ns) {
          if (freq[s.charAt(ei)]==1) count--;
          freq[s.charAt(ei)]--;
          ei++;
    
          while (count == 0) {
            if (len > ei - si) {
              ans = s.substring(si, ei);
              len = ei - si;
            }
            if (freq[s.charAt(si)] == 0)count++;
            freq[s.charAt(si)]++;
            si++;
          }
        }
        return len == (int) 1e9 ? 0 : len;
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
 //Count Of Substrings Having All Unique Characters
 public static int solution(String s) {
    int ns = s.length(),  si = 0, ei = 0, count = 0, len = 0;
    int[] freq = new int[128];
    while (ei < ns) {
      if (freq[s.charAt(ei)] ==1) count++;
      freq[s.charAt(ei)]++;
      ei++;
      while (count == 1) {
        if (freq[s.charAt(si)] == 2)count--;
        freq[s.charAt(si)]--;
        si++;
      }
      len+=ei-si;
    }
    return  len;
  }
  //Longest Substring With Exactly K Unique Characters
  public static int solution(String s, int k) {
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
      while (count > k) {
        //matlb 2 distint character bache hai to count decrese

        if (freq[s.charAt(si)] == 1) {
          count--;
        }
        freq[s.charAt(si)]--;
        si++;
      }
      len = Math.max(len, ei - si);
      //ye bhar is lia kuki atmost bola hai to 1 se k tak or while me likha mtlb k hi ho to to waha len likhte

    }
    return len;
  }
  //Count Of Substrings With Exactly K Unique Characters
  public static int help(String s, int k) {
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
      while (count > k) {
        //matlb 2 distint character bache hai to count decrese

        if (freq[s.charAt(si)] == 1) {
          count--;
        }
        freq[s.charAt(si)]--;
        si++;
      }
      len += ei - si ;
      //ye bhar is lia kuki atmost bola hai to 1 se k tak or while me likha mtlb k hi ho to to waha len likhte

    }
    return len;
  }
  public static int solution(String s, int k) {
    return help(s, k) - help(s, k - 1);
  }

  //Find All Anagrams in a String
  public List<Integer> findAnagrams(String s, String p) {
    
    return a;
  }

  //K Anagrams
  public static boolean areKAnagrams(String str1, String str2, int k) {
    int[]freq=new int[128];
    for(int i=0;i<str1.length();i++){
        freq[str1.charAt(i)]++;
    }
    for(int i=0;i<str2.length();i++){
        if(freq[str2.charAt(i)]!=0)freq[str2.charAt(i)]--;
    }
    int diff=0;
    for(int i=0;i<freq.length;i++){
        diff+=freq[i];
    }
    return diff<=k;
 }
 //Group Anagrams
 public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>>ans=new ArrayList<>();
    HashMap<String,ArrayList<String>>map=new HashMap<>();
    for(int i=0;i<strs.length;i++){
        char[]str=strs[i].toCharArray();
        Arrays.sort(str);
        String s=new String(str);
        if(map.containsKey(s)){
            map.get(s).add(strs[i]);
        }else{
            map.put(s,new ArrayList<>());
            map.get(s).add(strs[i]);
        }
    }
    for(int i=0;i<strs.length;i++){
        char[]str=strs[i].toCharArray();
        Arrays.sort(str);
        String s=new String(str);
        if(map.containsKey(s))ans.add(map.get(s));
        map.remove(s);
    }
    return ans;
}

//Group Shifted String
public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs)    {
    ArrayList<ArrayList<String>>ans = new ArrayList<>();
    HashMap<String, ArrayList<String>>map = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
      String s = help(strs[i]);
      if (map.containsKey(s)) {
        map.get(s).add(strs[i]);
      } else {
        map.put(s, new ArrayList<>());
        map.get(s).add(strs[i]);
      }
    }
    for (ArrayList<String> al : map.values()) {
      ans.add(al);
    }
    return ans;
  }
  public static String help(String  strs)    {
    int diff = 0;
    String ans = "";
    for (int i = 1; i < strs.length(); i++) {
      diff = strs.charAt(i) - strs.charAt(i - 1);
      if (diff < 0) {
        diff += 26;
      }
      ans += 'a' + diff;
    }
    return ans;
  }

//Word Pattern
class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");
    if (arr.length != pattern.length()) {
      return false;
    }
    HashMap<Character, String> map = new HashMap<Character, String>();
    for (int i = 0; i < pattern.length(); i++) {
      char c = pattern.charAt(i);
      if (map.containsKey(c)) {
        String value = map.get(c);
        if (!value.equals(arr[i])) {
          return false;
        }
      } else if (map.containsValue(arr[i])){
	    return false;
	 }
      map.put(c, arr[i]);
    }
    return true;
    }
}
//Count Of Subarrays Having Sum Equals To K
//sum-k +k=sum to sum-k logo ko add
//ase question me boi upper karte hai ab divide me bhi rem se
public static int solution(int[] arr, int k) {
    HashMap<Integer, Integer>map = new HashMap<>();
    int sum = 0;
    map.put(0, 1);
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      if (map.containsKey(sum - k))ans += map.get(sum - k);
      if (map.containsKey(sum))map.put(sum, map.get(sum) + 1);
      else map.put(sum, 1);
    }
    return ans;
  }
//Longest Subarray With Sum Divisible By K
public static int solution(int[] arr, int k) {
    HashMap<Integer, Integer>map = new HashMap<>();
    int sum = 0;
    map.put(0, -1);
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      int rem = sum % k;
      if (rem < 0)rem += k;
      if (map.containsKey(rem))ans = Math.max(ans, i - map.get(rem));
      else map.put(rem, i);
    }
    return ans;
  }
//Subarray Sums Divisible by K
public int subarraysDivByK(int[] arr, int k) {
    HashMap<Integer, Integer>map = new HashMap<>();
    int sum = 0;
    map.put(0, 1);
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      int rem=sum%k;
      if(rem<0)rem+=k;
      if (map.containsKey(rem))ans+=map.get(rem);
      if (map.containsKey(rem))map.put(rem,map.get(rem)+1);
      else map.put(rem, 1);
    }
    return ans;
}
//Longest Subarray With Equal Number Of Zeroes And Ones
public static int solution(int[] arr) {
    HashMap<Integer, Integer>map = new HashMap<>();
    int sum = 0;
    map.put(0, -1);
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      if(arr[i]==0)sum =sum-1;
      else sum+=arr[i];
      if (map.containsKey(sum))ans=Math.max(ans,i-map.get(sum));
      else map.put(sum, i);
    }
    return ans;
  }

//Count Of Subarrays With Equal Number Of Zeroes And Ones
//sun jab count hota hai to hume +1 kar ke count increse karna hoga kuki  count kar rhe
//or zero ko 1 se treat 
//per jab longest hoga to i ak baar update hoga 
//or zero ko -1 se
public static int solution(int[] arr) {
    HashMap<Integer, Integer>map = new HashMap<>();
    int sum = 0;
    map.put(0, 1);
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      if(arr[i]==0)sum =sum-1;
      else sum+=arr[i];
      if (map.containsKey(sum))ans+=map.get(sum);
      if (map.containsKey(sum))map.put(sum,map.get(sum)+1);
      else map.put(sum, 1);
    }
    return ans;
    }

//Longest Subarray With Equal Number Of 0s 1s And 2s
public static int solution(int[] arr) {
    int zcount = 0 ;
    int ocount = 0;
    int tcount = 0;
    int ans = 0;
    HashMap<String, Integer> map = new HashMap<>();
    map.put(0 + "*" + 0, -1);
    for(int i = 0 ; i  < arr.length ;i++) {
        if(arr[i] == 1) {
            ocount++;
        }else if(arr[i] == 0) {
            zcount++;
        }else {
            tcount++;
        }
        String s = (ocount - zcount) + "*" + (tcount - zcount);
        if(map.containsKey(s)) {
            ans = Math.max(ans,i - map.get(s));
        }else {
            map.put(s, i);
        }
    }
    return ans;
}

//Count Of Subarrays With Equal Number Of 0s 1s And 2s
public static int solution(int[] arr) {
    int zcount = 0 ;
    int ocount = 0;
    int tcount = 0;
    int ans = 0;
    HashMap<String, Integer> map = new HashMap<>();
    map.put(0 + "*" + 0, 1);
    for (int i = 0 ; i  < arr.length ; i++) {
      if (arr[i] == 1) {
        ocount++;
      } else if (arr[i] == 0) {
        zcount++;
      } else {
        tcount++;
      }
      String s = (ocount - zcount) + "*" + (tcount - zcount);
      if (map.containsKey(s)) ans += map.get(s);
      if (map.containsKey(s)) {
        map.put(s, map.get(s) + 1);
      } else {
        map.put(s, 1);
      }
    }
    return ans;
  }

  //Pairs With Equal Sum
  public static boolean solution(int[] arr) {
    HashSet<Integer>set=new HashSet<>();
    for(int i=0;i<arr.length;i++){
        for(int j=i+1;j<arr.length;j++){
            int sum=arr[i]+arr[j];
            if(set.contains(sum))return true;
            else set.add(sum);
        }
    }

    return false;
}
// Rabbits in Forest

public int numRabbits(int[] answers) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int ans = 0;
    for (int val : answers) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }
    for (int key : map.keySet()) {
        int freq = map.get(key);
        int keys = key + 1;
        // convert freq and keys into double
        int data = (int) Math.ceil(freq * 1.0 / keys * 1.0);

        ans += data * keys;
    }
    return ans;
}
//Array of Doubled Pairs
//sort is lia kuki hum small ka square se big mil jai ga per big ke square se small nhi
public boolean canReorderDoubled(int[] arr) {
    Arrays.sort(arr);
    HashMap<Integer, Integer> hmap = new HashMap<>();

    for (int val : arr) {
        if (val != 0) {
            if (hmap.containsKey(val)) {
                hmap.put(val, hmap.get(val) + 1);
            } else {
                hmap.put(val, 1);
            }
        }
    }

    for (int val : arr) {
        if(hmap.containsKey(val) && hmap.containsKey(2*val)){
            hmap.put(val,hmap.get(val)-1);
             hmap.put(val*2,hmap.get(2*val)-1);
             if(hmap.get(val)==0)hmap.remove(val);
             if(hmap.get(2*val)==0)hmap.remove(2*val);
            
        }
    }

    return hmap.size() == 0;
}
//Task Completion
public static void completeTask(int n, int m, int[] arr) {
    HashSet<Integer>set = new HashSet<>();
    ArrayList<Integer>a1 = new ArrayList<>();
    ArrayList<Integer>a2 = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      set.add(arr[i]);
    }
    int c = 0;
    for (int i = 1; i <= n; i++) {
      if (c % 2 == 0 && (!set.contains(i))) {
        a1.add(i);
        c++;
      } else if (c % 2 == 1 && (!set.contains(i))) {
        a2.add(i);
        c++;
      }

    }
    for (int a : a1) {
      System.out.print(a + " ");
    }
    System.out.println();
    for (int a : a2) {
      System.out.print(a + " ");
    }
    System.out.println();
  }

  //Pairs With Given Sum In Two Sorted Matrices
  public static int solve(int[][] num1, int[][] num2, int k) {
    HashSet<Integer>set = new HashSet<>();
    for (int i = 0; i < num1.length; i++) {
      for (int j = 0; j < num1[0].length; j++) {
        set.add(num1[i][j]);
      }
    }
    int count = 0;
    for (int i = 0; i < num2.length; i++) {
      for (int j = 0; j < num2[0].length; j++) {
        if (set.contains(k - num2[i][j])) {
          count++;
          set.remove(k - num2[i][j]);
        }
      }
    }
    return count;
  }
  //k sum
  public List<List<Integer>> threeSum(int[] arr) {
    Arrays.sort(arr);
    return kSumHelper(arr, 0, 3, 0);
}

public List<List<Integer>> twoSum(int[] arr, int si, int ei, int target) {
    List<List<Integer>> res = new ArrayList<>();
    int left = si;
    int right = ei;

    while (left < right) {
        if (left != si && arr[left] == arr[left - 1]) {
            left++;
            continue;
        }

        int sum = arr[left] + arr[right];
        if (sum == target) {
            List<Integer> list = new ArrayList<>();
            list.add(arr[left]);
            list.add(arr[right]);
            res.add(list);

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

public List<List<Integer>> kSumHelper(int[] nums, int target, int k, int si) {
    if (k == 2) {
        return twoSum(nums, si, nums.length - 1, target);
    }
    List<List<Integer>> res = new ArrayList<>();
    int n = nums.length;
    if (n < k) return res;
    // Arrays.sort(nums);
    for (int i = si; i <= n - k; i++) {
        if (i != si && nums[i] == nums[i - 1]) continue;

        int val = nums[i];
        int targ = target - val;
        List<List<Integer>> ans = kSumHelper(nums, targ, k - 1, i + 1);
        for (List<Integer> list : ans) {
            list.add(val);
            res.add(list);
        }
    }
    return res;
}
//Powerful Integers
public List<Integer> powerfulIntegers(int x, int y, int bound) {
    List<Integer> ans = new ArrayList<Integer>();
    HashSet<Integer> set = new HashSet<Integer>();
    for (int i = 1; i < bound; i *= x) {
        for (int j = 1; j < bound; j *= y) {
            if (i + j <= bound) {
                set.add(i + j);
                if (y == 1) {
                    break;
                }
            }
        }
        if (x == 1) {
            break;
        }
    }
    ans.addAll(set);
    return ans;

//First Non-repeating Character
public static int solution(String s) {
    // write your code here
    HashMap<Character, Integer>map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }
    for (int i = 0; i < s.length(); i++) {
      if (map.get(s.charAt(i)) == 1) {
        return  i;
      }

    }
    return -1;
  }