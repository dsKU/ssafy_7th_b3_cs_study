package Divide_And_Conquer;
import java.io.*;

public class beakjoon2447 {
    static char[][] star;
    static int N;
    static void solve(int level, int x, int y){
        if(level == 1) {
            star[x][y] = '*';
        }
        else{
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(i == 1 && j == 1) continue;
                    solve(level/3, x + i * level / 3, y+ j * level / 3);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N==1){
            System.out.println("*");
            return;
        }

        star = new char[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                star[i][j] = ' ';
            }
        }
        solve(N, 0, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sb.append(star[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }
}
