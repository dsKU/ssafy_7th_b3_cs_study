package DP;
import java.io.*;
import java.util.*;

public class beakjoon1463 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+5];
        
        arr[1] = 0;
        arr[2] = 1;
        arr[3] = 1;
        for(int i = 4; i <= N; i++){
            arr[i] = arr[i - 1] + 1;
            if(i % 3 == 0) arr[i] = Math.min(arr[i/3] + 1, arr[i]);
            if( i % 2 == 0) arr[i] = arr[i] = Math.min(arr[i], arr[i/2] + 1);
            
        }


        System.out.println(arr[N]);
    }
}
