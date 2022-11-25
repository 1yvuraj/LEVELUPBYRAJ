public class l005_bits {
    //1 bit 0 ya 1(imp)
    //1 byte 8 bit(imp)
    //1 int= 4 byte=32 bit
    //1 long = 8 byte=64 bit
    //on/true/set=1;
    //unset/false/off=0  

        //  |(or) &(and) ^(xor) !(not)
    //0 0->  0    0        0 -->1
    //0 1->  1    0        1--->0
    //1 0->  1    0        1--->0
    //1 1--> 1    1        0--->0


    //x=~x  (~ one's complimate)
    //x=(100101)2
    //ans  x=(011010)2  (one's complimate)
    //negative kar de ga  bas in (two's complimate)


    //left shift(<<)
   // x=(1000101101)2   
   //x=x<<2
   // x=(100010110100)2

   //left shift(>>)
   // x=(1000101101)2   (always add zero)(sun is me dekh rha change kuki left me hai pr right shift me nhi dekh rha bas drop kuki right me add hua hoga is lia 32 bit  hai dekh nhi rhi)
   //x=x<<2
   // x=(111000101101)2  (bas right shift me hota hai )sun jase 32 bit me aye ge ab jitne tune add kare to least sighnficate bit me se bo remove
   // add to name se hi hai left ya right
   //right shift me mst bit agar zero to zero add and one to one
   //number odd ya even to bit representation me last bit 0 to even or 1 hai to odd
    public static int leftShift(int x) {
        return x << 2;
    }

    public static int rightShift(int x) {
        return x >> 2;
    }

    public static int setTrue(int x, int idx) {//mtlb ak number and idx to use true ko true and false ko false
        int mask = 1 << idx;
        return x | mask;
    }

    public static int setFalse(int x, int idx){//mtlb ak number and idx to use false ko false and true ko false
        int mask = ~(1 << idx);
        return x & mask;
    }
   //NOTE LEFT SHIFT ME 
   //1 ->1
   //10 ->2^1
   //100 ->2^2
   //1000 ->2^3
   //so the formul is (number*2^number of left shift)
    public static int multiplyBy2(int num, int pow) {
        return (num << pow);//pow mtlb kitne se lift shift karna hai
    }

    public static int divideBy2(int num, int pow) {
        return (num >> pow);//divide to mtlb right shift
    }

    public static boolean isEven(int x) {
        return (x & 1) == 0;
    }

    // 231
    //matlb 2 4 8 16 32 64 inko ak number se kaam and(&) karde to and zero aya to true;
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 342
    public boolean isPowerOfFour(int n) {
        if (!isPowerOfTwo(n))
            return false;
        // for c++. unsigned int N = n;
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 0)
                count++;
            n >>>= 1; // only for java, cpp N >>=1;
        }

        return (count & 1) == 0;
    }

    // 136
    //dekh matlb jo repeat nhi hai xor se same wale 0 ho jai ge last me ak element bacha ko ans hoga
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int ele : nums) {
            ans ^= ele;
        }

        return ans;

    }

    // 268
    //(nums)^(jo hone chia the)
    public int missingNumber(int[] nums) {
        int n = nums.length, ans = n, i = 0;
        while (i < n) {
            ans ^= nums[i] ^ (i++);

        }
        return ans;
    }

    // 191

    public int hammingWeight_1(int n) {
        int count = 0, i = 0;
        while (i < 32) {
            if ((n & (1 << i)) != 0)
                count++;
            i++;
        }

        return count;
    }

    public int hammingWeight_2(int n) {
        int count = 0, i = 0;
        while (n != 0) {
            if ((n & 1) != 0)
                count++;
            n >>>= 1;
        }

        return count;
    }
   //mostly use
   //(n&n-1) to ak ak bit kaam hoti rhi gi and n==0 to count ans hoga
    public int hammingWeight_3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n & (n - 1));
        }

        return count;
    }
    //10 -> 1010
    //1's complimate -> 0101
    //                    +1
    //2's ------------> 0110 -->(-10)
    //---------------->  1010
    //---------------->  0110
    //---------------->  0010(nums ki last bit mil jai gi)
    //formula   n&(n-1)--> last bit chali jati hai
    //          n&(-n)-->last bit a jati hai 
    //         n=n&(n-1)||n&(-n)  to n mil jai ga
 

    //NOTE
    //x=n&(n-1) 
    //if n have p bits then x have p-1 bit becouse on every step p decreasing
    // 338
    // x=n&(n-1) to ab dekho  we use this formula by this formula we get small number whose value is already solve in past
        public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;

    }

    //260
    // dekh phale sab ka xor kar le nums ka then 
    // jo value aye usko then last bit nicklana hai then ak ak number
    //lana hai or xor karna hai zero walo ko ak me or non zero ko ak me
    // ab ye is lia kia kuki for ex agar 1 ak bar A me gya to second time bhi A me hi jai ga
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int ele : nums) xor ^= ele;
        int mask = xor & (-xor);
        
        int a = 0, b = 0;
        for(int ele : nums){
            if((ele & mask) == 0) a ^= ele;
            else b ^= ele;
        }
        
        return new int[]{a,b};
    }
    //if two number repted
    public int findDuplicate(int[] nums) {
        int xor=0;
        for(int i:nums)xor^=i; 
        for(int i=1;i<nums.length;i++)xor^=i; 
        return xor;
    }
  //most imp 
  //diff boolean arr and bit me kase
  //boolean (32 size ka)      bit
  //int idx=k           int mask =i<<k
  //arr[idx]=true        i/mask=true;
  //arr[idx]=false       i&mask=false;
  //if(arr[idx])         //if(i&mask)==0{do some work}
  //{do work}

  //287 -> after linkedlist
    


    public static void main(String... args) {
        System.out.println(rightShift(40));
    }
}