package implemented;
import java.io.*;
import java.util.*;
public class beakjoon13459 {
    static int N,M;
    static char[][][] map;
    static int[][] blue;
    static int[][] red;
    static int[] hole;
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,-1,1,0,0};
    static void copy(int dep){
        int pre = dep-1;
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                map[dep][i][j] = map[pre][i][j];
            }
        }
    }

    static boolean solve(int dep){
        if(dep > 10) return false;
        copy(dep);
        //위
        int preDep = dep - 1;   //이전 깊이
        if(red[preDep][0] < blue[preDep][0]){
            //레드가 먼저 출발해야할 경우 레드부터 이동을 진행
            boolean complete = move(dep, 1, red[preDep], 'R');
            //파란색 공이 구멍에 들어가면 다음을 확인 할 필요없음
            if(!move(dep, 1, blue[preDep], 'B')) { 
                //파란색 공이 구멍에 안들어갔고, 
                //빨간색 공이 구멍에 들어갔으면 더이상 해줄 필요 없음으로 리턴
                if(complete) return true;
                //파란색 공이 안 들어갔으면 다음 depth로 이동
                if(solve(dep + 1)) return true;
                map[dep][blue[dep][0]][blue[dep][1]] = '.';
            }
            if(!complete){
                map[dep][red[dep][0]][red[dep][1]] = '.';
            }
            
        }
        else{     
            //파란색공이 먼저 출발해야할 경우 위와 마찬가지 
            if(!move(dep, 1, blue[preDep], 'B')){
                if(move(dep, 1, red[preDep], 'R')) return true;
                if(solve(dep + 1)) return true;
                map[dep][blue[dep][0]][blue[dep][1]] = '.';
                map[dep][red[dep][0]][red[dep][1]] = '.';
            }
        }
        
        //아래
        if(red[preDep][0] > blue[preDep][0]){//레드가 아래에 있을 떄 레드부터
            boolean complete = move(dep, 2, red[preDep], 'R');
            if(!move(dep, 2, blue[preDep], 'B')) {
                if(complete) return true;
                if(solve(dep + 1)) return true;
                map[dep][blue[dep][0]][blue[dep][1]] = '.';
            }
            if(!complete){
                map[dep][red[dep][0]][red[dep][1]] = '.';
            }
        }
        else{      
            if(!move(dep, 2, blue[preDep], 'B')) {
                if(move(dep, 2, red[preDep], 'R')) return true;
                if(solve(dep + 1)) return true;
                map[dep][blue[dep][0]][blue[dep][1]] = '.';
                map[dep][red[dep][0]][red[dep][1]] = '.';
            }
        }
    
        //왼쪽
        if(red[preDep][1] < blue[preDep][1]){ //레드가 왼쪽에 있을 떄
            boolean complete = move(dep, 3, red[preDep], 'R');
            if(!move(dep, 3, blue[preDep], 'B')) {
                if(complete) return true;
                if(solve(dep + 1)) return true;
                map[dep][blue[dep][0]][blue[dep][1]] = '.';
            }
            if(!complete){
                map[dep][red[dep][0]][red[dep][1]] = '.';
            }
        }
        else{      
            if(!move(dep, 3, blue[preDep], 'B')) {
                if(move(dep, 3, red[preDep], 'R')) return true;
                if(solve(dep + 1)) return true;
                map[dep][blue[dep][0]][blue[dep][1]] = '.';
                map[dep][red[dep][0]][red[dep][1]] = '.';
            }
        }

        //오른쪽
        if(red[preDep][1] > blue[preDep][1]){ //레드가 왼쪽에 있을 떄
            boolean complete = move(dep, 4, red[preDep], 'R');
            if(!move(dep, 4, blue[preDep], 'B')) {
                if(complete) return true;
                if(solve(dep + 1)) return true;
                map[dep][blue[dep][0]][blue[dep][1]] = '.';
            }
            if(!complete){
                map[dep][red[dep][0]][red[dep][1]] = '.';
            }
        }
        else{      
            if(!move(dep, 4, blue[preDep], 'B')){
                if(move(dep, 4, red[preDep], 'R')) return true;
                if(solve(dep + 1)) return true;
                map[dep][blue[dep][0]][blue[dep][1]] = '.';
                map[dep][red[dep][0]][red[dep][1]] = '.';
            }
        }

        return false;
    }
    static boolean move(int dep,int d, int[] ball, char ch){
        int y = ball[0];    //공의 이동할 x 값
        int x = ball[1];    //공의 이동할 y 값
        map[dep][y][x] = '.';   //이동할 거니까 빈공간으로 지정

        while(map[dep][y][x] == '.'){   //빈공간일 떄 이동
            x = x + dx[d];
            y = y + dy[d];
            //다음 이동 공간이 구멍일 떄 리턴
            if(map[dep][y][x] == 'O') return true;
            
        }
        //현재 위치가 공이기 때문에 한 칸 반대로 이동
        x -= dx[d]; 
        y -= dy[d];

        //이동한 위치에 공 업데이트
        map[dep][y][x] = ch;
        //현재 depth에서 공위치 초기화
        if(ch == 'R'){
            red[dep][0] = y;
            red[dep][1] = x;
        }
        else{
            blue[dep][0] = y;
            blue[dep][1] = x;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new char[11][N][M];
        blue = new int[11][2];
        red = new int[11][2];

        for(int i = 0 ; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                map[0][i][j] = str.charAt(j);
                if(map[0][i][j] == 'R'){
                    red[0][0] = i;
                    red[0][1] = j;
                }
                else if(map[0][i][j] == 'B'){
                    blue[0][0] = i;
                    blue[0][1] = j;
                }
                else if(map[0][i][j] == 'O' ){
                    hole = new int[]{i, j};
                }
                
            }
        }

        if(solve(1)) System.out.println("1");
        else System.out.println("0");


    }
}
