package Graph;
import java.io.*;
import java.util.*;


public class beakjoon1012 {
    static int N, M;
    static int[][] map;
    static int dx[] = {-1 , 0, 1, 0};
    static int dy[] = {0 , 1, 0, -1};
    static void solve(int y, int x){
        map[y][x] = 0;

        for(int i = 0 ; i <4; i++){
            int cy = y + dy[i];
            int cx = x + dx[i];
            if(cx >= 0 && cx   < M && cy >=0 && cy < N){
                if(map[cy][cx]!=1)continue;
                solve(cy,cx);
            }
        }
    }

    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(br.readLine());

        for(int t= 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            map = new int[N][M];

            for(int i = 0 ; i < K; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[a][b] = 1;
            }
            int ans = 0;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    if(map[i][j] != 1) continue;
                    solve(i,j);
                    ans++;
                }
            }
            System.out.println(ans);
        }
    }
}
