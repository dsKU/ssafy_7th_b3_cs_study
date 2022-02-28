package SlidingWindow;
import java.io.*;
import java.util.*;

public class beakjoon2559 {
    static int N,M;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int start = 0;
        int end = 0;
        int subSum = 0;
        int ans = -987654321;
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            
            
            subSum += arr[i];
            end = i+1;
            if(end - start > M){
                subSum -= arr[start++];
            }
            if(end - start == M)
                ans = Math.max(ans, subSum);
        }
        System.out.println(ans);

    }
}
