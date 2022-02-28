package Greedy;
import java.io.*;
import java.util.*;

public class beakjoon11399 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int ans = 0;
        int temp = 0;
        for(int i = 0 ; i < N; i++){
            temp += arr[i];
            ans += temp;
        }
        System.out.println(ans);
    }
}
