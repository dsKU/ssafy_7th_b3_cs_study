package combination;
import java.io.*;
import java.util.*;

public class beakjoon10819 {
    static int N;
    static int arr[];
    static boolean[] visited;
    static int ans = 0;
    static void solve(int pre, int idx, int sum){
        if(idx == N){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i = 0 ; i < N;i++){
            if(visited[i]) continue;
            if(pre == -1){
                visited[i] = true;
                solve(i, idx+1, sum);
                visited[i] = false;
            } 
            else{
                visited[i] = true;
                solve(i, idx+1, sum + Math.abs(arr[pre] - arr[i]));
                visited[i] = false;
            }
            

        }


    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        solve(-1, 0, 0);
        System.out.println(ans);
    }
}
