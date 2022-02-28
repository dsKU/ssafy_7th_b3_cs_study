package Divide_And_Conquer;
import java.util.*;
public class beakjoon5904 {
    static int N;
    static int l, len;
    static void find(int level, int length){
        if(N <= length){
            l = level;
            len = length;
            return;
        }
        else{
            find(level + 1, length * 2 + level + 1);
        }
    }

    static void solve(int level, int length, int tgt){
        int pre = (length - level) / 2;
        if(pre == 0){
            if(tgt == 1){
                System.out.println("m");
            }
            else{
                System.out.println("o");
            }
            return;
        }

        int temp = tgt - pre;
        if(temp <= 0){
            solve(level - 1, pre, tgt);
        }
        else if(temp <= level){
            if(temp == 1) System.out.println("m");
            else System.out.println("o");
        }
        else{
            solve(level - 1, pre, temp - level);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
    
        find(3, 3);
        solve(l, len, N);        

        sc.close();
    }

}
