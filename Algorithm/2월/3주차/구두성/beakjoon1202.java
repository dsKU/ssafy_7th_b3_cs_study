package Greedy;
import java.io.*;
import java.util.*;

public class beakjoon1202 {
    static int N,M;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] jewelry_ = new int[N][2];
        Integer[] bag = new Integer[M];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            jewelry_[i][0] = Integer.parseInt(st.nextToken());
            jewelry_[i][1] = Integer.parseInt(st.nextToken());
            
        }
        for(int i = 0 ; i < M; i++){
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewelry_, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });

        Arrays.sort(bag);//, Collections.reverseOrder());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long sum = 0L;
        int idx = 0;
    
        for(int i = 0 ; i < M; i++){
            for(int j = idx; j < N; j++){
                if(bag[i] >= jewelry_[j][0]){
                    pq.add(jewelry_[j][1]);
                    idx=j+1;
                }
                else{
                    
                    break;
                }
            }
            if(!pq.isEmpty()){
                sum += pq.poll();
            }
        }

        System.out.println(sum);
    } 
}
