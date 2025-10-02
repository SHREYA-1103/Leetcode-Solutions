import java.util.ArrayList;
import java.util.List;

public class DesignCircularDeque_641 {
    static class MyCircularDeque {

        int arr[];
        int front, rear, size;
        
        // optimal -- O(1), O(1)
        public MyCircularDeque(int k) {
            arr = new int[k];
            size = k;
            front = -1;
            rear = -1;
        }
        
        // Optimal -- O(1), O(1)
        public boolean insertFront(int value) {
            if(isFull()){
                return false;
            }

            // inserting first element
            if(isEmpty()){
                front = rear = 0;
            }

            else{
                front = (front-1+size)%size;
            }

            arr[front] = value;
            
            return true;
        }
        
        // Optimal -- O(1), O(1)
        public boolean insertLast(int value) {
            if(isFull()){
                return false;
            }

            // inserting first element
            if(isEmpty()){
                front = rear = 0;
            }
            else{
                rear = (rear+1)%size;
            }

            arr[rear] = value;

            return true;
        }
        
        // Optimal -- O(1), O(1)
        public boolean deleteFront() {
            if(isEmpty()){
                return false;
            }

            if(front == rear){ // only one element
                front = rear = -1;
            }
            
            else{
                front = (front+1)%size;
            }

            return true;
        }
        
        // Optimal -- O(1), O(1)
        public boolean deleteLast() {
            if(isEmpty()){
                return false;
            }

            if(front == rear){ // only one element
                front = rear = -1;
            }
            else{
                rear = (rear-1+size)%size;
            }

            return true;
        }
        
        // Optimal -- O(1), O(1)
        public int getFront() {
            if(isEmpty()){
                return -1;
            }

            return arr[front];
        }
        
        // Optimal -- O(1), O(1)
        public int getRear() {
            if(isEmpty()){
                return -1;
            }

            return arr[rear];
        }
        
        // Optimal -- O(1), O(1)
        public boolean isEmpty() {
            return front == -1;
        }
        
        // Optimal -- O(1), O(1)
        public boolean isFull() {
            return (rear+1)%size == front;
        }
    }

    public static void main(String[] args) {
        // Example input:
        // ["MyCircularDeque","insertFront","insertLast","getRear","isFull","deleteLast","insertFront","getFront"]
        // [[3],[9],[8],[],[],[],[5],[]]

        String[] ops = {"MyCircularDeque","insertFront","insertLast","getRear","isFull","deleteLast","insertFront","getFront"};
        int[][] params = {{3},{9},{8},{},{},{},{5},{}};

        List<Object> output = new ArrayList<>();
        MyCircularDeque dq = null;

        for (int i = 0; i < ops.length; i++) {
            switch (ops[i]) {
                case "MyCircularDeque" -> {
                    dq = new MyCircularDeque(params[i][0]);
                    output.add(null);
                }
                case "insertFront" -> output.add(dq.insertFront(params[i][0]));
                case "insertLast" -> output.add(dq.insertLast(params[i][0]));
                case "deleteFront" -> output.add(dq.deleteFront());
                case "deleteLast" -> output.add(dq.deleteLast());
                case "getFront" -> output.add(dq.getFront());
                case "getRear" -> output.add(dq.getRear());
                case "isEmpty" -> output.add(dq.isEmpty());
                case "isFull" -> output.add(dq.isFull());
            }
        }

        System.out.println(output);
    }
}