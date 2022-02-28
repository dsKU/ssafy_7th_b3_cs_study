package Math;
import java.util.*;

public class beakjoon2960 {
    static int N,M;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        boolean[] val = new boolean[N+1];
        int cnt = 0;
        int ans = 0;
        for(int i = 2; i <= N; i++){
            if(val[i]) continue;
            for(int j = i; j <= N;j += i){
                if(val[j]) continue;
                cnt++;
                if(cnt == M){
                    ans = j;
                    break;
                } 
                val[j] = true;
            }
            

        }

        System.out.println(ans);
        sc.close();
    }
}
