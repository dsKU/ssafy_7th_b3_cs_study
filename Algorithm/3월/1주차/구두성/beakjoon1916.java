package Graph;
import java.io.*;
import java.util.*;

public class beakjoon1916 {
    static int N,M;
    static int [][] list;
    static int from, to;
    static int[] min_ ;
    static boolean[] visited;
    static int get_idx(){
        int index = -1;
        int min_val = Integer.MAX_VALUE;

        for(int i = 0 ; i < N; i++){
            if(!visited[i] && min_val > min_[i]){
                min_val = min_[i];
                index = i;
            }
        }
        
        return index;
    }
    static void Dijkstra(){
        min_ = new int[N];
        visited = new boolean[N];

        Arrays.fill(min_, Integer.MAX_VALUE);
        for(int i = 0; i< N;i++){
            min_[i] = list[from][i];
            
        }
        Arrays.fill(visited, false);
        min_[from] = 0;
        visited[from] = true;
        int idx = -1;
        
        while((idx = get_idx())!=-1){
            visited[idx] = true;
            
            for(int j = 1; j< N;j++){
                if(!visited[j] && list[idx][j] != Integer.MAX_VALUE){
                    min_[j] = Math.min(min_[j], min_[idx] + list[idx][j]);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new int[N][N];

        for(int i = 0 ; i < N; i++)
            Arrays.fill(list[i], Integer.MAX_VALUE);

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            if(list[a][b] > c) list[a][b] = c;
        }

        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken())-1;
        to = Integer.parseInt(st.nextToken())-1;

        Dijkstra();

        System.out.println(min_[to]);
    }
}
/*
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5

answer : 4

ArrayList와 priorityQueue를 썼는데 메모리 초과(왜..?)떠서 고민한 결과
N이 1000정도 밖에 안 해서 그냥 N*N 배열을 써서 구현

*/