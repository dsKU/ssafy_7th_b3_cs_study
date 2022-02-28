package Graph;
import java.io.*;
import java.util.*;

public class beakjoon23288 {
    static int N, M, K;
    static int[][] map ;
    static int[][] cnt;
    static Dice dice;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int d = 2;
    static int diceX = 1;
    static int diceY = 1;
    static void change_dir(int diff){  
        if(diff > 0){               //시계방향 회전

            d = (d + 3) % 4;
            /*
            if(d == 0) d = 3;       //서    ->  북
            else if(d == 3) d = 2;  //북    ->  동
            else if(d == 2) d = 1;  //동    ->  남
            else if(d == 1) d = 0;  //남    ->  서
            */
        }
        else if(diff < 0){          //반시계 방향
            d = (d + 1) % 4;
            /*
            if(d == 0) d = 1;       //서    ->  남
            else if(d == 1) d = 2;  //남    ->  동
            else if(d == 2) d = 3;  //동    ->  북
            else if(d == 3) d = 0;  //북    ->  서
            */
        }
        //if diff == 0 -> not change
    }
    
    static int DFS(int y, int x, int val, boolean flag){
        visited[y][x] = flag;

        int ret = 1;

        for(int i = 0; i < 4; i++){
            int cx = x + dx[i];
            int cy = y + dy[i];
            if(cx == 0 || cy == 0 || cx > M || cy > N || map[cy][cx] != val) continue;
            if(visited[cy][cx] == flag) continue;
            
            ret += DFS(cy, cx, val, flag);
            
        }
                
        return ret;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());   //횟수
        
        map = new int[N+1][M+1];
        cnt = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        for(int i = 1 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 1 ; i <= N; i++){   //각 칸마다 최대 연속 횟수 계산
            for(int j = 1 ; j <= M; j++){   //여기 부분이 잘못 됐었음
                cnt[i][j] = DFS(i, j, map[i][j], true);
                DFS(i, j, map[i][j], false);
            }
        }

        dice = new Dice();
        int score = 0;
        while(K-->0){
            int cx = diceX + dx[d];
            int cy = diceY + dy[d];
            if(cx <= 0 || cy <= 0 || cx > M || cy > N){
                d = (d + 2) % 4;
                cx = diceX + dx[d];
                cy = diceY + dy[d];
            }

            //주사위 굴리기
            dice.roll(d);
            //주사위 방향 바꾸기
            change_dir(dice.down - map[cy][cx]);
            //점수 더하기
            score += (cnt[cy][cx] * map[cy][cx]);

            //위치 업데이트
            //System.out.println(diceY + ", "+ diceX + " => " + cy + ", "+ cx + " : " + dice.down+" / " +  d );
            diceX = cx;
            diceY = cy;
            
            
        }

        System.out.println(score);
    }
}
class Dice{
    int[] side;
    int up;
    int down;
    public Dice(){
        side = new int[4];
        side[0] = 4;
        side[1] = 5;
        side[2] = 3;
        side[3] = 2;
        up = 1;
        down = 6;
    }   // 0 : 서   1 : 남  2 : 동  3 : 북
    public void roll(int d){
        down = side[d];     //굴릴 방향의 면이 아래로
        side[d] = up;       //그 면이 윗면

        up = 7 - down;      //바꿔주기
        side[( d + 2 ) % 4 ] = 7 - side[d];
        //System.out.println("d = " + d + " up = " +up + ", down = "+ down+" \nside 0 1 2 3 =" +side[0] + " "+side[1] + " "+side[2] + " "+side[3] + "\n" );
    }

}