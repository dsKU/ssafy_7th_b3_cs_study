package implemented;
import java.io.*;
import java.util.*;
public class beakjoon16236 {
    static int N,M;
    static int[][] map;
    static Shark16236 shark;
    static int[][] distance;
    static int dy[] = {-1, 0, 0, 1};
    static int dx[] = {0, -1, 1, 0};

    static void init(){
        for(int i = 0 ; i < N; i++){       
            for(int j = 0 ; j < N; j++){
                distance[i][j] = 0;
            }
        }
        distance[shark.y][shark.x] = 1;
    }
    static int[] bfs(){
        Queue<int[]> que = new LinkedList<>();
        que.add( new int[]{shark.y, shark.x});
        ArrayList<int[]> pq = new ArrayList<>();
        int dis = 9999;

        while(!que.isEmpty()){
            int[] p = que.poll();
            
            if(distance[p[0]][p[1]] >= dis) break;

            for(int i = 0 ; i < 4; i++){
                int cx = p[1] + dx[i];
                int cy = p[0] + dy[i];
                if(cx < 0 || cx >= N || cy < 0 || cy >= N || distance[cy][cx] != 0)continue;
                if(map[cy][cx] > shark.size) continue;

                distance[cy][cx] = distance[p[0]][p[1]] + 1;
                int[] temp = {cy, cx};

                if(map[cy][cx] != 0 && map[cy][cx] < shark.size){
                    dis = distance[cy][cx];
                    pq.add(temp);
                }
                
                que.add(temp);
            }
        }
        if(pq.size() > 0){
            Collections.sort(pq, (a,b)-> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            return pq.get(0);
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        distance = new int[N][N];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; j++){
                map[i][j] =Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    shark = new Shark16236(i, j, 2);
                }
            }
        }

        int time = 0;
        while(true){
            init();
            int[] feed = bfs();
            if(feed == null) break;
        
            time += distance[feed[0]][feed[1]] - 1;
            map[shark.y][shark.x] = 0;
            map[feed[0]][feed[1]] = 9;

            shark.y = feed[0];
            shark.x = feed[1];
            shark.feed++;
            
            if(shark.feed == shark.size){
                shark.size ++;
                shark.feed = 0;
            }
        }

        System.out.println(time);

    }
}
class Shark16236{
    int y, x, size, feed;
    public Shark16236(int a,int b, int c){
        y = a;
        x = b;
        size = c;
        feed = 0;
    }
}