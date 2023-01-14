
//xor of 0 ^x=x
//xor 0f x^x matlb even=0;
//xor of x^x^x matlb odd=x;
    public int xorAllNums(int[] nums1, int[] nums2) {
        int count = 0;
        int k = 0;
        if (nums2.length % 2 == 1) {
            for (int i = 0; i < nums1.length; i++) {
                count = count ^ nums1[i];
            }
        }
        if (nums1.length % 2 == 1) {
            for (int i = 0; i < nums2.length; i++) {
                count = count ^ nums2[i];
            }
        }
        return count;
    }

