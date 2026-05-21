/******************************************************************************
Richard Magiday
cop3503C_cmb_26
05/20/26
problem: CS2 PA1
*******************************************************************************/

import java.util.*;

// Holds a candidate pair of game point values
class Pair {
    int first;
    int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class Main {

    /*
     * Required for sorted arrays.
     * Returns Pair(0, 0) when no valid pair exists.
     */
    static Pair getCandidatePair(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;

        // Two-pointer: move inward based on whether the current sum is too small or too large
        while (left < right) {
            int sum = A[left] + A[right];

            if (sum == target)
                return new Pair(A[left], A[right]);
            else if (sum < target)
                left++;
            else
                right--;
        }

        return new Pair(0, 0);
    }

    /*
     * Uses a HashSet
     * Returns Pair(0, 0) when no valid pair exists.
     */
    static Pair findPairUnsorted(int[] A, int target) {
        HashSet<Integer> seen = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            int need = target - A[i];

            if (seen.contains(need))
                return new Pair(A[i], need);

            seen.add(A[i]);
        }

        return new Pair(0, 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt(); // number of test cases

        for (int m = 1; m <= k; m++) {
            int sortedStatus = sc.nextInt(); // 1 = sorted, 0 = unsorted
            int n = sc.nextInt();            // number of games

            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt();
            }

            int T = sc.nextInt(); // target points on the card

            Pair result;

            if (sortedStatus == 1) {
                // Sorted path: two-pointer, no HashSet
                result = getCandidatePair(A, T);
            } else {
                // Unsorted path: HashSet O(n)
                result = findPairUnsorted(A, T);
            }

            // Output — n1 must be less than n2
            if (result.first == 0 && result.second == 0) {
                System.out.println("Test case#" + m + ": No way you can spend exactly " + T + " points.");
            } else {
                int n1 = Math.min(result.first, result.second);
                int n2 = Math.max(result.first, result.second);
                System.out.println("Test case#" + m + ": Spend " + T +
                        " points by playing the games with " + n1 +
                        " points and " + n2 + " points.");
            }
        }

        sc.close();
    }
}
