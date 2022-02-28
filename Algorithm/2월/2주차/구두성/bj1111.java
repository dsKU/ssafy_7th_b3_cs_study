import java.io.*;
import java.util.*;

public class bj1111 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] val = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            val[i] = Integer.parseInt(st.nextToken());
        }

        if(N == 1){
            System.out.println("A");
            return;
        }
        if(N == 2){
            if(val[0] == val[1])
                System.out.println(val[1]);
            else
                System.out.println("A");
            return;
        }

        int a = (val[1] - val[0]) == 0 ? 0 : (val[2] - val[1]) / (val[1] - val[0]);
        int b = val[2] - a * val[1];
        for(int i = 0; i < N - 1; i++){
            if(val[i+1] == (val[i] * a + b) ) continue;
            System.out.println("B");
            return;
        }
        System.out.println((val[N-1] * a + b));


    }
}
