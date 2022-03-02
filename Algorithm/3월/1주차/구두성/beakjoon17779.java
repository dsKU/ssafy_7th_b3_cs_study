import java.io.*;
import java.util.*;
public class beakjoon17779 {
    static int N,M;
    static int[][] map;
    static int[][] sumMap;
    static int[] area = new int[5];
    static int diff(){
        int max = 0;
        int min = 987654321;
        for(int i = 0 ; i < 5; i++){
            max = Math.max(max, area[i]);
            min = Math.min(min, area[i]);
        }
        return max - min;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        sumMap = new int[N+1][N+1];
        
        int total = 0;

        for(int i = 1 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                sumMap[i][j] = sumMap[i][j-1] + map[i][j];
            }
            total += sumMap[i][N];
        }

        int ans = 987654321;
        for(int x = 1 ; x < N; x++){
            for(int y = 2 ; y < N; y++){
                for(int d1 = 1; d1 <= 18; d1 ++){
                    int x1 = x + d1;
                    int y1 = y - d1;
                    if(x1 > N) continue;
                    if(y1 < 1) continue;

                    for(int d2 = 1; d2 <= 18; d2 ++){
                        int x2 = x + d2;
                        int y2 = y + d2;
                        int x3 = x2 + d1;
                        int y3 = y2 - d1;
                        if(x2 > N) continue;
                        if(y2 > N) continue;
                        if(x3 > N) continue;
                        if(y3 > N || y3 < 1) continue;

                        //처리를 해야하는데 어캐해주냐  다시 시간있을 떄 처음부터 하자 집중안된다.
                        area[0] = 0;    //1구역
                        area[1] = 0;    //2구역
                        for(int i = 1; i <= x-1; i++){
                            area[0] += sumMap[i][y];
                            area[1] += sumMap[i][N] - sumMap[i][y];
                        }
                        for(int i = 1; i <= d1; i++){    //남은 1구역
                            area[0] += sumMap[x+i-1][y-i];
                        }
                        for(int i = 0; i <= d2; i++){   //남은 2구역
                            area[1] += sumMap[x + i][N] - sumMap[x + i][y + i];
                        }
                        /////////////////////////////////////////////////////////////////////
                        area[2] = 0;    //3구역
                        area[3] = 0;    //4구역
                        for(int i = x3 + 1 ; i <= N; i++){  //공통부분 x3 + 1 ~ N 까지
                            area[2] += sumMap[i][y3 - 1];
                            area[3] += sumMap[i][N] - sumMap[i][y3 - 1];
                        }
                        int ty = y1 - 1;
                        for(int i = 0; i <= d2; i++){    //남은 3구역
                            area[2] += sumMap[x1+i][ty + i];
                        }

                        for(int i = 0; i < d1; i++){
                            area[3] += sumMap[x3 - i][N] - sumMap[x3 - i][y3 + i];
                        }
                        /////////////////////////////////////////////////////////////////////
                        area[4] = total - area[0] - area[1] - area[2] - area[3];
                        ans = Math.min(ans, diff());
                    }//end for_d2
                }//end for_d1

                
            }//end for_y
        }//end for_x
        System.out.println(ans);
    }
}
