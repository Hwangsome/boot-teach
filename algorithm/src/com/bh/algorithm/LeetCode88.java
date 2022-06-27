package com.bh.algorithm;

import java.util.Arrays;

public class LeetCode88 {
    public static void main(String[] args) {
        int [] nums1 = {1,7,8,0,0,0};
        int [] nums2 = {2,5,6};
        leetCode88(nums1,6,nums2,3);
    }

    public static void leetCode88(int [] nums1,int m,int []nums2,int n){
        int tmp[] =new int[m+n];

        /**
         * 双指针
         */
        for (int nums1Index = 0, nums2Index = 0 ,index = 0; index <tmp.length; index++) {
            if (nums1Index >=m){
                tmp[index] = nums2[index];
            }else if (nums2Index >=n){
                tmp[index] = nums1[index];
            }else if (nums1[nums1Index] >nums2[nums2Index]){
                tmp[index] = nums2[nums2Index];
                nums2Index++;
            } else {
                tmp[index] = nums1[nums1Index];
                nums1Index++;
            }
        }
        Arrays.stream(nums1).forEach(value -> System.out.println(value));
    }
}
