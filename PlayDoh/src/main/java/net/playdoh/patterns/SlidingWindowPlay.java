package net.playdoh.patterns;

import java.util.Arrays;

public class SlidingWindowPlay {

    public static void main(String[] args) {

        SlidingWindowPlay slidingWindowPlay = new SlidingWindowPlay();

        System.out.println("Sliding Window Averages [1, 3, 2, 6, -1, 4, 1, 8, 2], 5 :::>>> " + Arrays.toString(slidingWindowPlay.slidingWindowAverage(new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2}, 5)));
        System.out.println("Sliding Window Max SUM [2, 1, 5, 1, 3, 2], 3 :::>>> " + slidingWindowPlay.slidingWindowMaxSum(new int[]{2, 1, 5, 1, 3, 2}, 3));
        System.out.println("Smallest Sliding Window SUM [2, 1, 5, 2, 3, 2], 7 :::>>> " + slidingWindowPlay.smallestSlidingWindowSum(new int[]{2, 1, 5, 2, 3, 2}, 7));
        System.out.println("Smallest Sliding Window SUM [2, 1, 5, 2, 3, 2, 7], 7 :::>>> " + slidingWindowPlay.smallestSlidingWindowSum(new int[]{2, 1, 5, 2, 3, 2, 7}, 7));
        System.out.println("Sliding Window Minimum Sub Array [2, 1, 5, 2, 3, 2], 7 :::>>> " + slidingWindowPlay.findMinSubArray(7, new int[]{2, 1, 5, 2, 3, 2}));
        System.out.println("Sliding Window Minimum Sub Array [3, 4, 1, 1, 6], 8 :::>>> " + slidingWindowPlay.findMinSubArray(8, new int[]{3, 4, 1, 1, 6}));

        System.out.println("Sliding Window Max Sum from Sub Array [34, -50, 42, 14, -5, 86] :::>>> " + SlidingWindowPlay.findMaxSumSubArray(new int[]{34, -50, 42, 14, -5, 86, -7}));
    }

    public double[] slidingWindowAverage(int[] nums, int k) {
        double[] averages = new double[nums.length - k + 1];

        int windowStart = 0;
        double windowSum = 0.0;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {

            windowSum += nums[windowEnd];
            if (windowEnd >= k - 1) {
                averages[windowStart] = windowSum / k;
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return averages;
    }

    public int slidingWindowMaxSum(int[] nums, int k) {

        int windowStart = 0, windowSum = 0, maxSum = 0;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {

            windowSum += nums[windowEnd];
            if (windowEnd >= k - 1) {
                if (windowSum > maxSum) {
                    maxSum = windowSum;
                }
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return maxSum;
    }

    public int smallestSlidingWindowSum(int[] nums, int k) {

        int windowStart = 0, windowSum = 0, smallestWindow = Integer.MAX_VALUE;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {

            windowSum += nums[windowEnd];
            while (windowSum >= k) {

                smallestWindow = Math.min(smallestWindow, windowEnd - windowStart + 1);
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return smallestWindow;
    }

    public static int findMinSubArray(int S, int[] arr) {
        // TODO: Write your code here
        int windowStart = 0, smallestSubArray = Integer.MAX_VALUE;
        int subArraySum = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            subArraySum += arr[windowEnd];
            while (subArraySum >= S) {
                smallestSubArray = Math.min(smallestSubArray, windowEnd - windowStart + 1);
                subArraySum -= arr[windowStart];
                windowStart++;
            }
        }

        return smallestSubArray == Integer.MAX_VALUE ? 0 : smallestSubArray;
    }

//  [34, -50, 42, 14, -5, 86, -7] - 137
    public static int findMaxSumSubArray(int[] nums) {

        int windowStart = 0, sum = 0, maxSum = 0;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {

            sum += nums[windowEnd];
            if (sum > maxSum) {
                maxSum = sum;
            } else if (sum < maxSum && nums[windowEnd] > nums[windowStart]) {
                sum -= nums[windowStart++];
            }
        }
        return maxSum;
    }
}