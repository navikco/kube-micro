package net.playdoh.leetcode;

import java.util.*;

public class MathPlay {

    public static void main(String[] args) {

        MathPlay mathPlay = new MathPlay();

        int[] nums = new int[] {2,7,11,15};
        System.out.println(mathPlay.twoSum(nums, 9));

        nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(mathPlay.threeSum(nums));

        int[] nums2 = {-1,2,1,-4};
        System.out.println(mathPlay.threeSumClosest(nums2, 1));

        System.out.println("Majority Element :::>>> " + mathPlay.majorityElement(new int[] {8, 8, 7, 7, 7}));
    }

    public List<Integer> twoSum(int[] nums, int target) {

        Hashtable<Integer, Integer> sums = new Hashtable<>();
        for(int i=0; i<nums.length; i++) {
            Integer num = new Integer(nums[i]);
            if(sums.containsKey(num))
                return Arrays.asList(sums.get(num).intValue(), i);
            else
                sums.put(target - nums[i], new Integer(i));
        }
        return null;
    }

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> resultList = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> matches = new ArrayList<>();
                        matches.add(nums[i]);
                        matches.add(nums[j]);
                        matches.add(nums[k]);
                        Collections.sort(matches);
                        if (resultList.indexOf(matches) < 0) {
                            resultList.add(matches);
                        }
                    }
                }
            }
        }
        return resultList;
    }

    public int threeSumClosest(int[] nums, int target) {
        int threeSum = 0;
        int distanceToTarget = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == target)
                        return sum;
                    else if (Math.abs(target - sum) < distanceToTarget) {
                        distanceToTarget = Math.abs(target - sum);
                        threeSum = sum;
                    }
                }
            }
        }
        return threeSum;
    }

    public int majorityElement(int[] nums) {

        Hashtable<Integer, Integer> majors = new Hashtable<>();
        int majorNum = 0, majorTimes = 0;

        if(nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i< nums.length; i++) {
            Integer num = majors.get(nums[i]);

            if (num != null) {
                if (majorTimes < num.intValue() + 1) {
                    majorTimes = num.intValue() + 1;
                    majorNum = nums[i];
                }
                majors.put(num, num.intValue() + 1);
            } else {
                majors.put(nums[i], 1);
                if (majorTimes < 1) {
                    majorNum = nums[i];
                    majorTimes = 1;
                }
            }
        }
        return majorNum;
    }
}