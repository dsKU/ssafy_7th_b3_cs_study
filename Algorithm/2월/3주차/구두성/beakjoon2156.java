package DP;
import java.util.Scanner;

public class beakjoon2156 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N+1];
        int[] DP = new int[N+1];
        //0 : 전에 두 번 마심 //1 : 이전 잔 안 마심  2: 이전 잔만 마심
        
        for(int i =1 ;i <= N;i++){
            arr[i] = sc.nextInt();
        }
        DP[1] = arr[1];
        if(N >= 2)
            DP[2] = arr[1] + arr[2];
        
        for(int i =3 ;i <= N;i++){
            DP[i] = Math.max(DP[i-1] , Math.max(DP[i-2] + arr[i], DP[i-3] + arr[i-1] + arr[i] ));
        }    
        System.out.println(DP[N]);
        sc.close();
    }
}
