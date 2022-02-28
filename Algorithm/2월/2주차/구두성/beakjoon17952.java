package implemented;
import java.io.*;
import java.util.*;

public class beakjoon17952 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());

        int score = 0;
        Stack<int[]> stack = new Stack<>();
        for(int i = 1 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 0){
                if(stack.isEmpty()) continue;
                int[] temp = stack.pop();

                if(--temp[1] == 0) score += temp[0];
                else stack.add(temp);
                
            }
            else{
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if(c == 1) score += b;
                else stack.add(new int[]{b, c-1});
                

            }
        }
        System.out.println(score);
    }
}
