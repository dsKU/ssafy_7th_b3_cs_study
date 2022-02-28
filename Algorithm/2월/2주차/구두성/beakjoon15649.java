package BackTracking;
import java.util.Arrays;
import java.util.Scanner;

public class beakjoon15649 {
    static int N;
    static int M;
    static int[] arr;
    static Boolean[] visited ;
    static void DFS(int depth){
        if(depth == M) {
            for(int i = 0 ; i < M; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 1 ; i <= N; i ++){
            if(!visited[i]){
                arr[depth] = i;
                visited[i] = true;
                DFS(depth+1);

                visited[i] = false;
            }            
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N+1];
        visited = new Boolean[N+1];
        Arrays.fill(visited, false);
        DFS(0);
        sc.close();
        
    }   
}
