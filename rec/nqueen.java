public class l006_Nqueen {
    //diganol r-c+m-1
    //antidiganol r+c
    static boolean[] rows, cols, diags, adiags;

    public static int nqueen_01(int n, int tnq, int bno, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n, c = bidx % n;
            if (!rows[r] && !cols[c] && !diags[r - c + n - 1] && !adiags[r + c]) {
                rows[r] = cols[c] = diags[r - c + n - 1] = adiags[r + c] = true;
                count += nqueen_01(n, tnq - 1, bidx + 1, psf + "(" + r + "," + c + ") ");
                rows[r] = cols[c] = diags[r - c + n - 1] = adiags[r + c] = false;
            }
        }
        return count;
    }

    static int row = 0, col = 0, diag = 0, adiag = 0;

    public static int nqueen_02(int n, int tnq, int bno, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n, c = bidx % n;
            if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r - c + n - 1))) == 0
                    && (adiag & (1 << (r + c))) == 0) {
                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));

                count += nqueen_02(n, tnq - 1, bidx + 1, psf + "(" + r + "," + c + ") ");

                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));
            }
        }
        return count;
    }

    public static int nqueen_03(int n, int floor, String psf) {
        if (floor == n) {
            System.out.println(psf);
            return 1;
        }
        int count = 0, r = floor;
        for (int room = 0; room < n; room++) {
            int c = room;
            if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r - c + n - 1))) == 0
                    && (adiag & (1 << (r + c))) == 0) {
                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));

                count += nqueen_03(n, floor + 1, psf + "(" + r + "," + c + ") ");

                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));
            }
        }
        return count;
    }

    public static boolean isPalindrome(String str, int i, int j) {
        while (i <= j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void solution(String str, int i, String asf) {
        if (i >= str.length()) {
            System.out.println(asf);
            return;
        }
        for (int j = i; j < str.length(); j++) {
            if (isPalindrome(str, i, j)) {
                solution(str, j + 1, asf + "(" + str.substring(i, j + 1) + ") ");
            }
        }
    }
    public static List<Integer> getPrice(char[][] grid, int cost) {
        List<Integer>ans=new ArrayList<>();
        boolean[][]visit=new boolean[grid.length][grid[0].length];
        int[][]dir={{0,-1},{-1,0},{0,1},{1,0}};
        int sum=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(visit[i][j]==false && grid[i][j]!='w')
                {

                    int temp=solve(grid,i,j,grid[0].length-1,grid.length-1,dir,visit,cost);
                    ans.add(temp*cost);
                }

            }
        }

        
        return ans;
        
    } 
    
    public static int solve(char[][] grid,int sr,int sc,int ec,int er,int[][]dir,boolean[][]visit,int cost) {
      
       int count=0;
       visit[sr][sc]=true;
      
       for(int d=0;d<dir.length;d++){
        int r=sr+dir[d][0];
        int c=sc+dir[d][1];
        
        if(r>=0 && c>=0 && r<=er && c<=ec && !visit[r][c] && grid[r][c]!='w'){
        count+=solve(grid,r,c,ec,er,dir,visit,cost);
        }
       }
       
       return count+1;
    } 

    public static void main(String... args) {
        int n = 4, tnq = 4;
        // rows = new boolean[n];
        // cols = new boolean[n];
        // diags = new boolean[n + n - 1];
        // adiags = new boolean[n + n - 1];
        // System.out.println(nqueen_02(n, tnq, 0, ""));
        System.out.println(nqueen_03(n, 0, ""));
    }
}