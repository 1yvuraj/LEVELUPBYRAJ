//benery search
public static int binarySearch(int[] arr, int data) {
    int n = arr.length, si = 0, ei = n - 1;
    while (si <= ei) {
        int mid = (si + ei) / 2; // si + (ei - si) / 2;
        if (arr[mid] == data)
            return mid;
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return -1;
}
//first index
//bas equal to hata dia yar saf hai ei mid se piche le gye to starting index aye ga
public static int first index(int[] arr, int data) {
    int n = arr.length, si = 0, ei = n - 1;
    if(data<=arr[0] || data>=arr[arr.length-1])return data<=arr[0]?arr[0]:arr[arr.length-1];
    while (si <= ei) {
        int mid = (si + ei) / 2;
        if (arr[mid]<data)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    //yha question poadhna ki bol kya rha dekhne ko
    return  arr[si]==data?si:-1;
}
//lastindex
public static int lastindex(int[] arr, int data) {
    int n = arr.length, si = 0, ei = n - 1;
    while (si <= ei) {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return si >0 && arr[si-1]==data?si-1:-1;
}
//searchInsert
//is se last index bhi mil gya upper walese chota hai
public static int searchInsert(int[] arr, int data) {
    int n = arr.length, si = 0, ei = n - 1;
    while (si <= ei) {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    int insertPos = si;//if data nhi mila to
    int lastIndex = si - 1;//if data mil gya 

    return (lastIndex >= 0 && arr[lastIndex] == data) ? lastIndex : insertPos;
}
//closest number 
//ceil and floor
//https://practice.geeksforgeeks.org/problems/find-the-closest-number5513/1
//agar ye hai ki diff kaam wala dana hai to if me data - arr[ei and si kar ke return kar sakta hu]
public static int closestnumber (int[] arr, int data) {
    int n = arr.length, si = 0, ei = n - 1;
    if(data<=arr[0] || data>=arr[arr.length-1])return data<=arr[0]?arr[0]:arr[arr.length-1];
    while (si <= ei) {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    //yha question poadhna ki bol kya rha dekhne ko per ei floor ko and si ceil ko point kar rha hoga
    return arr[ei];
}

//jase question hai seggrgate the arry (IMP ki is se na bas data apni correct postion pe jai ga baki sorted nhi hoge) matlb data ke se chote left me and bade sare left me
//or data apni correct postion pe to
//boi do point agar itr chota hai data se to swap and na main thing is that data ko last me daal data 
//usse correct postion pe aye ga
//ak ko humne kar dia ab boi quick sort karna hai to jo elemnet sai position per hai use picha and aage recusion ko bol 
//kar de sai
public static int sagregate (int[] arr, int data,int si,int ei) {
    swap(arr,data,ei);
    int prt=si-1;
    int itr=si;
    while(itr<=ei){
        if(arr[itr]<=data){
            swap(arr,++prt,itr);
            
        }
        itr++;
    }
    return prt;
}
static void swap(int[] arr,int i,int j) {
    int temp=arr[i];
    arr[i]=arr[j];
    arr[j]=temp;
}
//nlogn
//agar sorted hai arr to n2 ho gi
public static void Quicksort (int[] arr,int si,int ei) {
    if(si>ei)return;
    int pivet=ei;//ab yha jis jo dalo ge usse ho jai ga like si ,ei+ei,(ei+ei)/2;
    int pidx=sagregate(arr,pivet,si,ei);
    Quicksort(arr,si,pivet-1);
    Quicksort(arr,pivet+1,ei);

}

//Search a 2D Matrix
//https://leetcode.com/problems/search-a-2d-matrix/
//yha bo corner wale se is lia nhi kia kuki is question me bas row sorted thi bo
//jab karte jab row and col dono sorted ho
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m= matrix[0].length;
        int n= matrix.length;
        int si=0;
        int ei=m*n-1;
        while (si<=ei) {
            int mid=(si+ei)/2;
            int r=mid/m;
            int c=mid%m;
            if ( matrix[r][c] == target) return true; 
            else if ( matrix[r][c] < target) si=mid+1; 
            else ei=mid-1;
        }
        return false;
    }
}
//Search a 2D Matrix II
//Integers in each row are sorted in ascending from left to right.
//Integers in each column are sorted in ascending from top to bottom.
//m+n
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
       int si=matrix.length-1,ei=0;
        while(si>=0 && ei<matrix[0].length){
            if(matrix[si][ei]==target)return true;
            else if(matrix[si][ei]<target)ei++;
            else si--;
        }
        return false;
    }
}
 




