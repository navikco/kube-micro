package net.playdoh.leetcode;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class ArrayPlay {

    public static void main(String[] args) {

        ArrayPlay arrayPlay = new ArrayPlay();
        System.out.println("Check Sum of Two Numbers in Array  :::>>> " + arrayPlay.checkSum(new int[]{-4, 3, 1, 8, 4, 12, 13}, 8));

        System.out.println("Unique Array Length :::>>> " + arrayPlay.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));

        System.out.println("Remove Duplicates :::>>> " + arrayPlay.removeElement(new int[]{3, 2, 2, 3}, 3));

        System.out.println("Search Element INSERT Position :::>>> " + arrayPlay.searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println("Search Element INSERT Position :::>>> " + arrayPlay.searchInsert(new int[]{1, 3, 5, 6}, 3));

        System.out.println("Max SubArray :::>>> " + arrayPlay.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

        System.out.println("Increment Array :::>>> " + arrayPlay.plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));

        System.out.println("Merged Sorted Arrays :::>>> " + ArrayUtils.isSorted(arrayPlay.mergeSortedArray(new int[]{9, 12, 18, 36, 40, 46, 51}, new int[]{3, 12, 19, 37})));

        int nums[] = new int[]{1,2,3,0,0,0};
        arrayPlay.merge(nums, 3, new int[]{2,5,6}, 3);
        System.out.println("Merged Sorted Arrays :::>>> " + ArrayUtils.isSorted(nums));

        System.out.println("MissedRanges in Array :::>>> " + arrayPlay.findMissingRanges(new int[]{}, -3, -1));

        System.out.println("SummaryRanges in Array :::>>> " + arrayPlay.summaryRanges(new int[]{-2147483648,-2147483647,2147483647}));

        System.out.println("Find in Array[3] :::>>> " + arrayPlay.findIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 3));
        System.out.println("Find in Array[1] :::>>> " + arrayPlay.findIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1));
        System.out.println("Find in Array[10] :::>>> " + arrayPlay.findIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10));
        System.out.println("Find in Array[5] :::>>> " + arrayPlay.findIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        System.out.println("Find in Array[15] :::>>> " + arrayPlay.findIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 15));
        System.out.println("Find in Array[-1] :::>>> " + arrayPlay.findIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, -1));

        System.out.println("Unique Intersections in TWO Arrays[1,2,3,4,3,5,6]:[3,7,8,3,5,9] :::>>> " + Arrays.toString(arrayPlay.intersection(new int[]{1,2,3,4,3,5,6}, new int[]{3,7,8,3,5,9})));
        System.out.println("NON-Unique Intersections in TWO Arrays[1,2,3,4,5,6]:[3,7,8,3,5,9] :::>>> " + Arrays.toString(arrayPlay.nonUniqueIntersection(new int[]{1,2,3,4,3,5,6}, new int[]{3,7,8,3,5,9})));

        System.out.println("Sliding Window Averages [1, 3, 2, 6, -1, 4, 1, 8, 2] :::>>> " + Arrays.toString(arrayPlay.slidingWindowAverage(new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2}, 5)));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0)
            return;
        for(int i=0,j=0,k=m; i<m+n; i++) {

            if(j >= n)
                return;
            if(i >= k) {
                nums1[i] = nums2[j++];
            } else if(nums1[i] > nums2[j]) {
                pushArray(nums1, i);
                nums1[i] = nums2[j++];
                k++;
            }
        }
    }
    private void pushArray(int[] nums, int index) {

        for(int i=nums.length-2; i>=index; i--)
            nums[i+1] = nums[i];
    }

    public int[] mergeSortedArray(int[] nums1, int[] nums2) {

        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        int[] mergedArray = new int[nums1Length + nums2Length];

        int nums1Index = 0, nums2Index = 0;
        for (int i = 0; i < mergedArray.length; i++) {

            if (nums1Index >= nums1Length)
                mergedArray[i] = nums2[nums2Index++];
            else if (nums2Index >= nums2Length)
                mergedArray[i] = nums1[nums1Index++];
            else
                mergedArray[i] = nums1[nums1Index] <= nums2[nums2Index] ? nums1[nums1Index++] : nums2[nums2Index++];
        }
        return mergedArray;
    }

    public boolean checkSum(int[] nums, int val) {

        Set<Integer> uniqueSet = new HashSet<>();

        for (int num : nums) {

            if (uniqueSet.contains(num))
                return true;
            uniqueSet.add(val - num);
        }
        return false;
    }

    public int removeDuplicates(int[] nums) {

        int dups = 0;
        int previous = 0;
        for (int i = 0; i < nums.length - dups; i++) {

            if (i > 0 && nums[i] == previous) {
                removeDups(i, nums);
                dups++;
                i--;
            }
            previous = nums[i];
        }
        return nums.length - dups;
    }

    private void removeDups(int index, int[] nums) {
        for (int i = index; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
    }

    public int removeElement(int[] nums, int val) {

        if (nums == null || nums.length == 0)
            return 0;

        //3,2,2,3 | 3

        int dups = 0;
        for (int i = 0; i < nums.length - dups; i++) {

            if (nums[i] == val) {
                removeVal(i, nums);
                dups++;
                i--;
            }
        }
        return nums.length - dups;
    }

    private void removeVal(int index, int[] nums) {

        for (int i = index; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
    }

    public int searchInsert(int[] nums, int target) {

        if (nums[0] > target)
            return 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] >= target)
                return i;
        }
        return nums.length;
    }

    public int maxSubArray(int[] nums) {

        int maxSum = nums[0], sum = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];
            if (maxSum < sum) {
                maxSum = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    public int[] plusOne(int[] digits) {

        Stack<Integer> stack = new Stack<>();
        int remainder = 1;
        for (int i = digits.length - 1; i >= 0; i--) {

            stack.push(digits[i] + remainder < 10 ? digits[i] + remainder : digits[i] + remainder - 10);
            remainder = digits[i] + remainder < 10 ? 0 : 1;
            if (i == 0 && remainder > 0)
                stack.push(remainder);
        }
        int[] increments = new int[stack.size()];
        for (int i = 0; !stack.empty(); i++) {
            increments[i] = stack.pop();
        }

        return increments;
    }


    public List<String> findMissingRanges(int[] nums, int lower, int upper) {

        List<String> missedRanges = new ArrayList<>();
        int previousNum = 0;
        boolean missed = false;

        if (nums.length == 0) {
            this.addMissingRanges(lower, upper, missedRanges);
            return missedRanges;
        }
        for (int i = 0; i <= nums.length - 1; i++) {

            if (i == 0 && nums[i] > lower) {
                this.addMissingRanges(lower, nums[i] - 1, missedRanges);
            }
            if (i == nums.length - 1 && nums[i] < upper) {
                this.addMissingRanges(nums[i] + 1, upper, missedRanges);
            }
            if (nums.length > i + 1 && nums[i + 1] - nums[i] > 1) {
                this.addMissingRanges(nums[i] + 1, nums[i + 1] - 1, missedRanges);
            }
        }

        return missedRanges;
    }

    public void addMissingRanges(int num1, int num2, List<String> missedRanges) {

        int index = missedRanges.size();
        if (Math.abs(num2 - num1) >= 1) {
            missedRanges.add(index, num1 + "->" + num2);
        } else {
            missedRanges.add(index, "" + num2);
        }
    }

    public List<String> summaryRanges(int[] nums) {

        List<String> ranges = new ArrayList<>();
        if (nums.length == 1) {
            ranges.add(nums[0] + "");
            return ranges;
        }
        int index = 0;
        boolean inRange = false;
        for (int i = 0; i < nums.length - 1; i++) {
            int num1 = nums[i];
            int num2 = nums[i + 1];
            if (num2 - num1 == 1) {
                if (inRange) {
                    String range = ranges.get(index);
                    ranges.set(index, range.substring(0, range.indexOf("->") + 2) + num2);
                } else {
                    if(i > 0) {
                        ranges.set(index, ranges.get(index) + "->" + num2);
                    } else {
                        ranges.add(num1 + "->" + num2);
                    }
                }
                inRange = true;
            } else if (num1 + 1 < num2) {
                if(i == 0) {
                    ranges.add(num1 + "");
                }
                ranges.add(num2 + "");
                index++;
                inRange = false;
            }
        }
        return ranges;
    }

    public int findIndex(int[] nums, int num) {

        int left = 0;
        int right = nums.length - 1;
        int pivot;

        System.out.println("left :-> " + left + " : right :-> " + right);
        while (left <= right) {

            pivot = left + (right - left) / 2;
            System.out.println("left :-> " + left + " : right :-> " + right + " : pivot :-> " + pivot);

            if (num > nums[pivot]) {
                left = pivot + 1;
            } else if (num < nums[pivot]) {
                right = pivot - 1;
            } else {
                return pivot;
            }
        }
        return -1;
    }

    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> intersections = new HashSet<>();
        Set<Integer> nums = new HashSet<>();
        Integer num = null;
        for (int i = 0; i < nums1.length; i++) {
            nums.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            num = new Integer(nums2[i]);
            if (nums.contains(num)) {
                intersections.add(num);
            }
        }
        return intersections.stream().mapToInt(i -> i).toArray();
    }

    public int[] nonUniqueIntersection(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> intersections = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {

            if (nums1[i] == nums2[j]) {
                intersections.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            }
        }
        return intersections.stream().mapToInt(i -> i).toArray();
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
}