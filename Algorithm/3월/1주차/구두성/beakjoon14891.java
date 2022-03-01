package implemented;
import java.io.*;
import java.util.*;
public class beakjoon14891 {
    static int N,M;
    static int[][] left = new int[4][2];
    static int[][] right = new int[4][2];
    static char[][] wheel = new char[4][8];
    
    static void spin(int idx, int dir){
        if(dir == 1){   //시계
            left[idx][1] = left[idx][1] == 0 ? 7 : left[idx][1] - 1;
            right[idx][1] = right[idx][1] == 0 ? 7 : right[idx][1] - 1;
        }
        else{
            left[idx][1] = left[idx][1] == 7 ? 0 : left[idx][1] + 1;
            right[idx][1] = right[idx][1] == 7 ? 0 : right[idx][1] + 1;
        }
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < 4; i++){
            wheel[i] = br.readLine().toCharArray();
            left[i][0] = 6;
            right[i][0] = 2;
            left[i][1] = 6;
            right[i][1] = 2;
        }
        N = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            spin(a, dir);
            
            int temp = a;
            int d = -dir;
            while(temp > 0){
                if(wheel[temp][ left[temp][0] ] == wheel[temp-1][ right[temp-1][0] ]) break;
                spin(--temp, d);
                d = -d;
            }

            temp = a;
            d = -dir;
            while(temp < 3){
                if(wheel[temp][right[temp][0]] == wheel[temp+1][left[temp+1][0]]) break;
                spin(++temp, d);
                d = -d;
            }

            for(int j = 0 ; j < 4; j++){
                left[j][0] = left[j][1];
                right[j][0] = right[j][1];
            }
        }


        int ans = 0;
        for(int i = 0 ; i < 4; i++){
            int top = (left[i][0] + 2) % 8;
            if(wheel[i][top] == '1') ans += (1<<i);
        }

        System.out.println(ans);
//2,6
    }
}
