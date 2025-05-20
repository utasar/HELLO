import java.util.ArrayDeque;
public class Main{
    public static void main(String[] args) throws Exception {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] numArray ={9, 6, 4, 2, 5, 1, 3};
        for(int number: numArray){
            stack.push(number);// insert into stack
            queue.offer(number);//insert into queue
        }
        System.out.println("Stack Content:");
        while(!stack.isEmpty()){
            System.out.println(stack.pop());// pull out from stack
        }
        System.out.println("Queue Content:");
        while(!queue.isEmpty()){
            System.out.println(queue.poll());//pull out from queue
        }

    }
}
