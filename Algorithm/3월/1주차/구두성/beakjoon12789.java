package etc;
import java.io.*;
import java.util.*;

public class beakjoon12789 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        Stack<Integer> stack = new Stack<>();
        int idx = 1;

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] != idx){
                stack.add(arr[i]);
            }
            else{
                idx++;
                while(!stack.isEmpty() && stack.peek() == idx){
                    stack.pop();
                    idx++;
                }
            }
        }
        while(!stack.isEmpty() && stack.peek() == idx){
            stack.pop();
            idx++;
        }
        if(stack.isEmpty()) System.out.println("Nice");
        else System.out.println("Sad");


    }
}
