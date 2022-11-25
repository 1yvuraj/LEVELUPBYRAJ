//Longest Palindromic Subsequence
class Solution {
    public int longestPalindromeSubseq(String S) {
        int[][] dp = new int[S.length()][S.length()];
        for (int[] d : dp) Arrays.fill(d, -1);
        return help(S, 0, S.length() - 1, dp);
    }
    public int help(String s, int i, int j, int[][] dp) {
        if (i >= j) return i == j ? 1 : 0;
        if (dp[i][j] != -1) return dp[i][j];
        if (s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = help(s, i + 1, j - 1, dp) + 2;
        } else {
            return dp[i][j] = Math.max(help(s, i + 1, j, dp), help(s, i, j - 1, dp));
        }
    }
}
//tabo
class Solution {
    public int longestPalindromeSubseq(String S) {
        int[][] dp = new int[S.length()][S.length()];
        return help(S, 0, S.length() - 1, dp);
    }
    public int help(String s, int I, int J, int[][] dp) {
        for (int gap = 0; gap < s.length(); gap++) {
            for (int i = 0, j = gap; j < s.length(); i++, j++) {
                if (i >= j) {
                    dp[i][j] = i == j ? 1 : 0;
                    continue;
                }
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[I][J];
    }
}
// Longest Common Subsequence
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        return help(text1,text2, text1.length(), text2.length(), dp);
    }
    public int help(String str1,String str2 ,int I, int J, int[][] dp) {
        for (int i = 0;i<=str1.length(); i++) {
            for (int j=0;j<=str2.length();j++) {
                if (i==0 || j==0) {
                    dp[i][j] =0;
                    continue;
                }
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j-1]);
                }
            }
        }
        return dp[I][J];
    }
}
// 583
public int minDistance(String word1, String word2) {
    return word1.length() + word2.length() - 2 * longestCommonSubsequence(word1, word2);
}

//Distinct Subsequences
//plus 1 is lia nhi kuki is me equal hone per ak count karna hai
// Input: s = "rabbbit", t = "rabbit"
// Output: 3
// Explanation:
// As shown below, there are 3 ways you can generate "rabbit" from s.
// rabbbit
// rabbbit
// rabbbit
class Solution {

    public int numDistinct(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int[] d : dp) Arrays.fill(d, -1);
        return longestPlaindromicSubsequence_memo(text1, text2, text1.length(), text2.length(), dp);
    }

    public static int longestPlaindromicSubsequence_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (m == 0) {
            return dp[n][m] = 1;
        }
        if (n < m) return dp[n][m] = 0;
        if (dp[n][m] != -1) return dp[n][m];
        int a = longestPlaindromicSubsequence_memo(s1, s2, n - 1, m - 1, dp);
        int b = longestPlaindromicSubsequence_memo(s1, s2, n - 1, m, dp);

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = a + b;
        } else {
            return dp[n][m] = b;
        }
    }
}
//dp
public static int longestPlaindromicSubsequence_memo(String s1, String s2, int N, int M, int[][] dp) {
    for (int n = 0; n <= N; n++) {
        for (int m = 0; m <= M; m++) {
            if (m == 0) {
                dp[n][m] = 1;
                continue;
            }
            if (n < m) {
                dp[n][m] = 0;
                continue;
            }
            if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                dp[n][m] = dp[n - 1][m - 1] + dp[n - 1][m];
            } else {
                dp[n][m] = dp[n - 1][m];
            }
        }
    }
    return dp[N][M];
}
//72. Edit Distance
// Insert a character
// Delete a character
// Replace a character
//plus one kuki kitne operation lage ge to jo ak not equal hai uska 1 hai
public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
    if (n == 0 || m == 0) {
        return dp[n][m] = (n == 0 ? m : n);
    }

    if (dp[n][m] != -1)
        return dp[n][m];

    int insert = minDistance(word1, word2, n, m - 1, dp);
    int delete = minDistance(word1, word2, n - 1, m, dp);
    int replace = minDistance(word1, word2, n - 1, m - 1, dp);

    if (word1.charAt(n - 1) == word2.charAt(m - 1))
        return dp[n][m] = replace;
    else
        return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
}
//agar cost bhi dia ho to
public int minDistance_02(String word1, String word2, int n, int m, int[] cost, int[][] dp) {
    if (n == 0 || m == 0) {
        return dp[n][m] = (n == 0 ? m * cost[0] : n * cost[2]);
    }

    if (dp[n][m] != -1)
        return dp[n][m];

    int insert = minDistance_02(word1, word2, n, m - 1, cost, dp);
    int delete = minDistance_02(word1, word2, n - 1, m, cost, dp);
    int replace = minDistance_02(word1, word2, n - 1, m - 1, cost, dp);

    if (word1.charAt(n - 1) == word2.charAt(m - 1))
        return dp[n][m] = replace;
    else
        return dp[n][m] = Math.min(Math.min(insert + cost[0], delete + cost[2]), replace + cost[1]);
}

//44. Wildcard Matching
// Input: s = "aa", p = "a"
// Output: false
// Explanation: "a" does not match the entire string "aa".
class Solution {
    public boolean isMatch(String s, String p) {
        p = Match(p);
        Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
        return help(s, p, s.length(), p.length(), dp);
    }

    public boolean help(String s, String p, int n, int m, Boolean[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0) return dp[n][m] = true; else if (m == 1 && p.charAt(m - 1) == '*') return dp[n][m] = true; else return dp[n][m] = false;
        }
        if (dp[n][m] != null) return dp[n][m];
        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);
        if (ch1 == ch2 || ch2 == '?') {
            return dp[n][m] = help(s, p, n - 1, m - 1, dp);
        } else if (ch2 == '*') {
            boolean res = false;
            res = res || help(s, p, n, m - 1, dp);
            res = res || help(s, p, n - 1, m, dp);
            return dp[n][m] = res;
        } else {
            return dp[n][m] = false;
        }
    }

    public String Match(String s) {
        if (s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        int i = 1;
        while (i < s.length()) {
            while (i < s.length() && sb.charAt(sb.length() - 1) == '*' && s.charAt(i) == '*') {
                i++;
            }
            if (i < s.length()) sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }
}
//Delete Operation for Two Strings
//Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
public int minDistance(String word1, String word2) {
    return word1.length() + word2.length() - 2 * longestCommonSubsequence(word1, word2);
}
//115. Distinct Subsequences
//Given two strings s and t, return the number of distinct subsequences of s which equals t.
//Input: s = "rabbbit", t = "rabbit"
// Output: 3
// Explanation:
// As shown below, there are 3 ways you can generate "rabbit" from s.
// rabbbit
// rabbbit
// rabbbit
    public int numDistinct(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int[] d : dp) Arrays.fill(d, -1);
        return longestPlaindromicSubsequence_memo(text1, text2, text1.length(), text2.length(), dp);
    }

    //     public static int longestPlaindromicSubsequence_memo(String s1, String s2, int n, int m, int[][] dp) {
    //         if (m == 0) {
    //             return dp[n][m] = 1;
    //         }
    //         if (n < m) return dp[n][m] = 0;
    //         if (dp[n][m] != -1) return dp[n][m];

    //         if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
    //             return dp[n][m] = longestPlaindromicSubsequence_memo(s1, s2, n - 1, m - 1, dp) + longestPlaindromicSubsequence_memo(s1, s2, n - 1, m, dp);
    //         } else {
    //             return dp[n][m] = longestPlaindromicSubsequence_memo(s1, s2, n - 1, m, dp);
    //         }
    //     }

    public static int longestPlaindromicSubsequence_memo(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }

                if (n < m) {
                    dp[n][m] = 0;
                    continue;
                }
                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + dp[n - 1][m];
                } else {
                    dp[n][m] = dp[n - 1][m];
                }
            }
        }
        return dp[N][M];
    }

//1035. Uncrossed Lines
//You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
//Input: nums1 = [1,4,2], nums2 = [1,2,4]
// Output: 2
// Explanation: We can draw 2 uncrossed lines as in the diagram.
// We cannot draw 3 uncrossed lines, because the line from nums1[1] = 4 to nums2[2] = 4 will intersect the line from nums1[2]=2 to nums2[1]=2.

public int maxUncrossedLines(int[] arr1, int[] arr2, int N, int M, int[][] dp) {
    for (int n = 0; n <= N; n++) {
        for (int m = 0; m <= M; m++) {
            if (n == 0 || m == 0) {
                dp[n][m] = 0;
                continue;
            }

            if (arr1[n - 1] == arr2[m - 1])
                dp[n][m] = dp[n - 1][m - 1] + 1;// lcss(str1, str2, n - 1, m - 1, dp) + 1;
            else
                dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
        }
    }

    return dp[N][M];
}
//1458. Max Dot Product of Two Subsequences
// Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
// Output: 18
// Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
// Their dot product is (2*3 + (-2)*(-6)) = 18.
public int maxDotProduct(int[] nums1, int[] nums2, int n, int m, int[][] dp) {

    if (n == 0 || m == 0) {
        return dp[n][m] = -(int) 1e8;
    }

    if (dp[n][m] != -(int) 1e9)
        return dp[n][m];

    int val = nums1[n - 1] * nums2[m - 1];
    int acceptBothNumbers = maxDotProduct(nums1, nums2, n - 1, m - 1, dp) + val;
    int a = maxDotProduct(nums1, nums2, n - 1, m, dp);
    int b = maxDotProduct(nums1, nums2, n, m - 1, dp);

    return dp[n][m] = maximum(val, acceptBothNumbers, a, b);
}
//longest common substring 
public static int lcsubstring_DP(String str1, String str2, int N, int M, int[][] dp) {
    int maxLen = 0, ei = 0;
    for (int n = 0; n <= N; n++) {
        for (int m = 0; m <= M; m++) {
            if (n == 0 || m == 0) {
                dp[n][m] = 0;
                continue;
            }

            if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
                dp[n][m] = dp[n - 1][m - 1] + 1;
                if (dp[n][m] > maxLen) {
                    maxLen = dp[n][m];
                    ei = n - 1;
                }
            }
        }
    }

    return maxLen;
}
//longest palandrome substring 
public String longestPalindrome(String s) {
    int n = s.length();
    boolean[][] dp = new boolean[n][n];

    int count = 0, MaxLen = 0, si = 0;
    for (int gap = 0; gap < n; gap++) {
        for (int i = 0, j = gap; j < n; i++, j++) {
            if (gap == 0)
                dp[i][j] = true;
            else if (gap == 1 && s.charAt(i) == s.charAt(j))
                dp[i][j] = true;
            else
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

            if (dp[i][j]) {
                count++;
                if (j - i + 1 > MaxLen) {
                    MaxLen = j - i + 1;
                    si = i;
                }
            }
        }
    }

    return s.substring(si, si + MaxLen);
}
//132. Palindrome Partitioning II
// Given a string s, partition s such that every substring of the partition is a palindrome.
// Return the minimum cuts needed for a palindrome partitioning of s.
//muje jase hi palandrome milta hai mane cut laga ke aage ki string pass kar dia

public int minCut(String s, int si, int ei, int[] dp, boolean[][] pdp) {
    if (pdp[si][ei])
        return 0;
    if (dp[si] != -1)
        return dp[si];

    int minAns = (int) 1e8;
    for (int cut = si; cut <= ei; cut++) {
        if (pdp[si][cut]) {
            minAns = Math.min(minAns, minCut(s, cut + 1, ei, dp, pdp) + 1);
        }
    }

    return dp[si] = minAns;
}

// faafaaaaabaageeg
public int minCut(String s) {
    int n = s.length();
    boolean[][] pdp = new boolean[n][n];
    int count = 0, MaxLen = 0, si = 0;
    for (int gap = 0; gap < n; gap++)
        for (int i = 0, j = gap; j < n; i++, j++) {
            if (gap == 0)
                pdp[i][j] = true;
            else if (gap == 1 && s.charAt(i) == s.charAt(j))
                pdp[i][j] = true;
            else
                pdp[i][j] = s.charAt(i) == s.charAt(j) && pdp[i + 1][j - 1];
        }

    int[] dp = new int[n + 1];
    Arrays.fill(dp, -1);
    return minCut(s, 0, n - 1, dp, pdp);
}
//Count subsequences of type a^i, b^j, c^k
public int fun(String s)
{
    long a=0;
    long ab=0;
    long abc=0;
    int mod=(int)1e9+7;
    for(int i=0;i<s.length();i++){
        char ch=s.charAt(i);
        if(ch=='a'){
           a=(2*a+1)%mod; 
        }else if(ch=='b'){
           ab=(2*ab+a)%mod; 
        }else{
            abc=(2*abc+ab)%mod;
        }
    }
    return (int)(abc%mod);
}
//139. Word Break
//boi hai ki call jab phala word jo hai arr me use kare ge
//n2 length of string
class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> map = new HashSet<>();
        Boolean[] dp = new Boolean[s.length() + 1];
        for (String val : wordDict) {
            map.add(val);
        }
        return help(s, map, 0, dp);
    }

    public boolean help(String s, HashSet<String> map, int si, Boolean[] dp) {
        if (si == s.length()) return true;
        if (dp[si] != null) return dp[si];
        for (int i = si; i <= s.length(); i++) {
            if (map.contains(s.substring(si, i))) {
                if (help(s, map, i, dp)) return dp[i] = true;
            }
        }
        return dp[si] = false;
    }
}
//140. Word Break II
//boi jase hi word mile ga aage search kar lo
class Solution {
    ArrayList<String> main = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> map = new HashSet<>();
        for (String val : wordDict) {
            map.add(val);
        }
        help(s, map, 0, "");
        return main;
    }
    public void help(String s, HashSet<String> map, int si, String ans) {
        if (si == s.length()) {
            String a = "";
            a += ans;
            main.add(ans.trim());
            return;
        }
        for (int i = si; i <= s.length(); i++) {
            if (map.contains(s.substring(si, i))) {
                help(s, map, i, ans + s.substring(si, i) + " ");
            }
        }
    }
}




