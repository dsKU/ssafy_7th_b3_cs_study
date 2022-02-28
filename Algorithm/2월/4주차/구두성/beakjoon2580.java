package BackTracking;
import java.io.*;
import java.util.*;


public class beakjoon2580 {
    static int[][] sudoku = new int[9][9];
    static int[][] visited1 = new int[10][10];//가로
    static int[][] visited2 = new int[10][10];// 세로
    static int[][] visited3 = new int[10][10];//3*3
    static int cnt = 0;
    static int check_area(int i , int j){
        if(i <=2 && j <= 2) return 1;
        if(i <=2 && j <= 5) return 2;
        if(i <=2 && j <= 8) return 3;
        if(i <=5 && j <= 2) return 4;
        if(i <=5 && j <= 5) return 5;
        if(i <=5 && j <= 8) return 6;
        if(i <=8 && j <= 2) return 7;
        if(i <=8 && j <= 5) return 8;
        return 9;
    }

    static boolean back(int cnt){
        if(cnt == 81){
            
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    System.out.print(sudoku[i][j] + " ");
                }
                System.out.println();
            }
            
            return true;
            
        }
        int x = cnt / 9;
        int y = cnt % 9;
        if(sudoku[x][y] == 0){
            for(int i = 1; i <= 9; i++){
                if(visited1[x][i] == 0 && visited2[y][i] == 0 &&  visited3[check_area(x, y)][i] == 0){
                    visited1[x][i] = 1;
                    visited2[y][i] = 1;
                    visited3[check_area(x, y)][i] = 1;

                    sudoku[x][y] = i;
                    if(back(cnt+1)) return true;
                    sudoku[x][y] = 0;

                    visited1[x][i] = 0;
                    visited2[y][i] = 0;
                    visited3[check_area(x, y)][i] = 0;
                }

            }
        }
        else{
            return back(cnt+1);
        }
        
        return false;

    }//end function

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i++){
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < 9; j++){
                sudoku[i][j] = str[j].charAt(0) - '0';
                
                if(sudoku[i][j] == 0) continue;

                visited1[i][sudoku[i][j]] = 1;
                visited2[j][sudoku[i][j]] = 1;
                visited3[check_area(i, j)][sudoku[i][j]] = 1;
            }
        }
        back(0);
    }
}
