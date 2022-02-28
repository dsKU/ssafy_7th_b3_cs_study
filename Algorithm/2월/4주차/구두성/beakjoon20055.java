package implemented;
import java.io.*;
import java.util.*;
public class beakjoon20055 {
    static int N,M;
    static int len;
    static int nextIndex(int idx){
        return (idx - 1) == 0 ? len : idx - 1;
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        len = N * 2 ;
        int container[] = new int[len + 1];
        
        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i <= N * 2; i++){
            container[i] = Integer.parseInt(st.nextToken());
        }

        
        int start = 1;
        int end = N;
        int turn = 1;
        boolean isEmpty[] = new boolean[len + 1];
        Queue<Integer> robot = new LinkedList<>();
        int cnt = 0;
        while(true){                        
            start = nextIndex(start);
            end = nextIndex(end);

            if(!robot.isEmpty()){   //2번
                int size = robot.size();
                for(int i = 0  ; i < size; i++){
                    int idx = robot.poll();
                    isEmpty[idx]=false;
                    if(idx == end) continue;
                    
                    int temp = (idx % len) + 1;
                    if(container[temp] > 0 && !isEmpty[temp]){
                        container[temp]--;
                        if(container[temp] == 0) cnt++;
                        if(temp == end) continue;
                        
                        isEmpty[temp] = true;
                        robot.add(temp);
                    }
                    else{
                        isEmpty[idx] = true;
                        robot.add(idx);
                    }
                }
            }
            
            
            if(container[start] > 0 && !isEmpty[start]){ //3번 수행
                robot.add(start);
                isEmpty[start] = true;
                container[start] --;
                if(container[start] == 0) cnt++;
            }
            
            //4번 수행
            if(cnt >= M) break;
            
            turn++;
        }
        System.out.println(turn);
    }
}
