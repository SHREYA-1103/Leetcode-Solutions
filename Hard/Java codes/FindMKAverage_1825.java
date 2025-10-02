import java.util.*;

class MKAverage {
    int m, k;
    Queue<Integer> q;
    TreeMap<Integer, Integer> left, right, middle;
    int leftSize, rightSize, middleSize;
    long middleSum;

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        q = new LinkedList<>();
        left = new TreeMap<>();
        right = new TreeMap<>();
        middle = new TreeMap<>();
        leftSize = rightSize = middleSize = 0;
        middleSum = 0;
    }

    private void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private void remove(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.get(val) - 1);
        if (map.get(val) == 0) map.remove(val);
    }

    public void addElement(int num) {
        q.offer(num);

        if (leftSize < k || (!left.isEmpty() && num <= left.lastKey())) {
            add(left, num);
            leftSize++;
        } else if (rightSize < k || (!right.isEmpty() && num >= right.firstKey())) {
            add(right, num);
            rightSize++;
        } else {
            add(middle, num);
            middleSize++;
            middleSum += num;
        }

        balance();

        if (q.size() > m) {
            int old = q.poll();
            if (left.containsKey(old)) {
                remove(left, old);
                leftSize--;
            } else if (right.containsKey(old)) {
                remove(right, old);
                rightSize--;
            } else {
                remove(middle, old);
                middleSize--;
                middleSum -= old;
            }
            balance();
        }
    }

    private void balance() {
        // move from left -> middle if left too big
        while (leftSize > k) {
            int val = left.lastKey();
            remove(left, val);
            leftSize--;
            add(middle, val);
            middleSize++;
            middleSum += val;
        }

        // move from right -> middle if right too big
        while (rightSize > k) {
            int val = right.firstKey();
            remove(right, val);
            rightSize--;
            add(middle, val);
            middleSize++;
            middleSum += val;
        }

        // move from middle -> left if left too small
        while (leftSize < k && middleSize > 0) {
            int val = middle.firstKey();
            remove(middle, val);
            middleSize--;
            middleSum -= val;
            add(left, val);
            leftSize++;
        }

        // move from middle -> right if right too small
        while (rightSize < k && middleSize > 0) {
            int val = middle.lastKey();
            remove(middle, val);
            middleSize--;
            middleSum -= val;
            add(right, val);
            rightSize++;
        }
    }

    public int calculateMKAverage() {
        if (q.size() < m) return -1;
        return (int) (middleSum / (m - 2 * k));
    }
}

public class FindMKAverage_1825 {
    public static void main(String[] args) {
        // Example: m = 3, k = 1
        MKAverage mk = new MKAverage(3, 1);

        mk.addElement(3);
        mk.addElement(1);
        System.out.println(mk.calculateMKAverage()); // Output: -1 (not enough elements)

        mk.addElement(10);
        System.out.println(mk.calculateMKAverage()); // Output: 3 (middle element is [3])

        mk.addElement(5);
        System.out.println(mk.calculateMKAverage()); // Output: 5 (middle elements = [3,5], average = 8/2)

        mk.addElement(5);
        System.out.println(mk.calculateMKAverage()); // Output: 5 (middle elements = [5,5], average = 10/2)
    }
}
