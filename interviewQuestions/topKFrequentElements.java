package interviewQuestions;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class topKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        HashMap<Integer, Integer> freqMap = new HashMap();
        for (int n : nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> freqMap.get(n1) - freqMap.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n : freqMap.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // 3. build an output array
        // O(k log k) time
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            result[i] = heap.poll();
        }
        return result;
    }
}