package implemented;
import java.io.*;
import java.util.*;

public class beakjoon17837 {
    static int N,M;
    static int[][] map;                     //규칙 저장하는 배열
    static ArrayList<Integer>[][] cntMap;   //해당 좌표의 기물의 index를 저장하는 배열
    static piece[] pieces;                  //기물의 정보 저장

    static int dx[] = {0, 1 , -1, 0, 0};
    static int dy[] = {0, 0 , 0, -1, 1};
    static int changeDir(int dir){
        if(dir == 1) return 2;
        if(dir == 2) return 1;
        if(dir == 3) return 4;
        if(dir == 4) return 3;
        return 0;
    }
    static void reverse(int start, ArrayList<Integer> list){
        Stack<Integer> st = new Stack<>();
        for(int i = start; i < list.size(); i++){
            st.add(list.get(i));
        }
        while(!st.isEmpty()){
            list.set(start++, st.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        cntMap = new ArrayList[N][N];
        pieces = new piece[M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                cntMap[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            pieces[i] = new piece(a,b,c);
            cntMap[a][b].add(i);
        }

        for(int turn = 1; turn <= 1000; turn++){    //제한 횟수
            for(int i = 0; i < M; i++){             //각 턴마다의 기물 이동
                piece p = pieces[i];

                int cx = p.x + dx[p.dir];
                int cy = p.y + dy[p.dir];

                if(cx < 0 || cy < 0 || cx >=N || cy >= N || map[cy][cx] == 2){
                    //범위 초과 및 다음칸이 파랑색일 경우
                    p.dir = changeDir(p.dir);
                    cx = p.x + dx[p.dir];
                    cy = p.y + dy[p.dir];
                    if(cx < 0 || cy < 0 || cx >=N || cy >= N || map[cy][cx] == 2){
                        //반대방향도 마찬가지일 때 넘김
                        continue;
                    }
                }

                ArrayList<Integer> temp = cntMap[p.y][p.x]; //좌표에 있는 piece Index
                int start = temp.indexOf(i);    

                if(map[cy][cx] == 1){   //빨간색 칸이면 뒤집기
                    reverse(start, temp);           
                }

                for(int j = start; j < temp.size(); j++){   //index보다 위에 있는 거 전부 좌표 바꿔서 옮기기
                    int idx = temp.get(j);
                    pieces[idx].y = cy;
                    pieces[idx].x = cx;
                    cntMap[cy][cx].add(idx);
                }

                if(start == 0){
                    temp.clear();
                }
                else {
                    while(temp.size() > start)
                         temp.remove(start);
                }    //원래 자리 지우기

                if(cntMap[cy][cx].size() >= 4){ //종료조건
                    System.out.println(turn);
                    return;
                }
            }   //end piece_for

        }   //end trun_for

        System.out.println(-1);
    }
}
class piece{
    int dir;
    int y;
    int x;
    public piece(int y, int x, int d){
        this.y = y;
        this.x = x;
        this.dir = d;

    }
}
/*
for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    System.out.print(cntMap[i][j].size() + " ");
                }
                System.out.println();
            }
            */