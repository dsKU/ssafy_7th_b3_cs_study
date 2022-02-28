package DP;
import java.io.*;
import java.util.*;
public class beakjoon1890 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [][] map = new int[N][N];
        long [][] dp = new long[N][N];

        dp[0][0] = 1;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) continue;
                if(i + map[i][j] < N) dp[i + map[i][j] ][ j] += dp[i][j];
                if(j + map[i][j] < N) dp[i][ j + map[i][j] ] += dp[i][j];
            }
        }


        System.out.println(dp[N-1][N-1]);

    }
}
