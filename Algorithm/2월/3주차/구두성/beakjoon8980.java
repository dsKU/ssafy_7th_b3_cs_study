package 다시풀기;
import java.io.*;
import java.util.*;

public class beakjoon8980 {
    static int N,K,M;
    static int[][] delivery;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        delivery = new int[M][3];
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            delivery[i][0] = Integer.parseInt(st.nextToken());
            delivery[i][1] = Integer.parseInt(st.nextToken());
            delivery[i][2] = Integer.parseInt(st.nextToken());

        }
        Arrays.sort(delivery, (a,b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        
        int ans = 0;
        int[] city = new int[N+1];
        for(int i = 0; i < M; i++){
            
            int temp = 0;
            for(int j = delivery[i][0] ; j < delivery[i][1]; j++){
                temp = Math.max(temp, city[j]); 
            } 

            temp = K - temp;//남은 무게
            if(delivery[i][2] > temp) delivery[i][2] = temp;
            ans += delivery[i][2];

            for(int j = delivery[i][0] ; j < delivery[i][1]; j++){
                city[j] += delivery[i][2];
            }

        }
        for(int i : city){
            System.out.print(i + " ");
        }
        System.out.println("\n"+ans);
    }
}
