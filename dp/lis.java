//Longest Increasing Subsequence
//recursion
    static int longestSubsequence(int size, int a[])
    {
        int max=0;
        int[]dp=new int[size];
        for(int i=0;i<a.length;i++){
            max=Math.max(max,help(a,i,dp));
        }
        return max;
    }
    static int help(int arr[],int ei,int[]dp)
    {
        if(ei==0)return dp[ei]=1;
        int max=0;
        if(dp[ei]!=0)return dp[ei];
        for(int i=ei;i>=0;i--){
            if(arr[i]<arr[ei]){
             max=Math.max(max,help(arr,i,dp));
            }
        }
        return dp[ei]=max+1;
    }
//dp
static int LIS_LR(int size, int a[])
    {
        int[]dp=new int[a.length];
        int max=0;
        for(int i=0;i<a.length;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(a[i]>a[j]){
                  dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }

    
    public static int LDS_LR(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }
//longest dcresing sub
public static void main(String[] args) {
    Scanner scn=new Scanner(System.in);
     int n=scn.nextInt();
    int[]a=new int[n];
     for(int i=0;i<a.length;i++){
         a[i]=scn.nextInt();
     }
     int[]dp=new int[a.length];
     
     int max=0;
     for(int i=0;i<a.length;i++){
         dp[i]=1;
         for(int j=0;j<i;j++){
             if(a[i]<a[j]){
               dp[i]=Math.max(dp[i],dp[j]+1);
             }
         }
         max=Math.max(max,dp[i]);
     }
     System.out.println(max);
 }
//Longest Bitonic subsequence
// Input: nums = [1, 2, 5, 3, 2]
// Output: 5
// Explanation: The sequence {1, 2, 5} is
// increasing and the sequence {3, 2} is 
// decreasing so merging both we will get 
// length 5.
public int LongestBitonicSequence(int[] nums)
    {
       int maxs=Integer.MIN_VALUE;
       int[]LIS=new int[nums.length];
       int[]LDS=new int[nums.length];
       lis(nums,LIS);
       lds(nums,LDS);
       for(int i=0;i<nums.length;i++)
       {
           maxs=Math.max(LIS[i]+LDS[i]-1,maxs);
       }
       return maxs;
    }
    public int lis(int[] nums,int[]dp)
    {
        
        int[]dp=new int[a.length];
        
        int max=0;
        for(int i=0;i<a.length;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(a[i]>a[j]){
                  dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }
    //sun yha lds back se karna hai is lia ase loop kia
    public static int lds(int[] arr,int[]dp) {
        int maxLen = 0, n = arr.length;
        
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

//Maximum sum increasing subsequence
//boi longest wale ka sum dna hai
public int maxSumIS(int a[], int n)  
	{  
	    int[]dp=new int[a.length];
        int max=0;
        for(int i=0;i<a.length;i++){
            dp[i]=a[i];
            for(int j=0;j<i;j++){
                if(a[i]>a[j]){
                  dp[i]=Math.max(dp[i],dp[j]+a[i]);
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
	}  

//Maximum Sum Bitonic Subsequence
public static int maxSumBS(int nums[], int n)
    {
        int maxs=Integer.MIN_VALUE;
       int[]LIS=new int[nums.length];
       int[]LDS=new int[nums.length];
       lis(nums,LIS);
       lds(nums,LDS);
       for(int i=0;i<nums.length;i++)
       {
           maxs=Math.max(LIS[i]+LDS[i]-nums[i],maxs);
       }
       return maxs;
    }
     public static int lis(int[] a,int[]dp)
    {
        int max=0;
        for(int i=0;i<a.length;i++){
            dp[i]=a[i];
            for(int j=0;j<i;j++){
                if(a[i]>a[j]){
                  dp[i]=Math.max(dp[i],dp[j]+a[i]);
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }
    //sun yha lds back se karna hai is lia ase loop kia
    public static int lds(int[] arr,int[]dp) {
        int maxLen = 0, n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    //Minimum number of deletions to make a sorted sequence
    //boi longest incresse sub hata do uske baad jo bacha bo ans
    public int minDeletions(int a[], int n) 
	{ 
	   int[]dp=new int[a.length];
        int max=0;
        for(int i=0;i<a.length;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(a[i]>a[j]){
                  dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            max=Math.max(max,dp[i]);
        }
        return a.length-max;
	} 

    //number of lis