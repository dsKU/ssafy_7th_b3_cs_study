package Graph;
import java.io.*;
import java.util.*;

public class beakjoon1238 {
    static int N,M, K;
    static ArrayList<Node1238>[] map;
    static ArrayList<Node1238>[] map2;
    static int[] min_;
    static int[] min_2;
    static int MAX_VAL = 10000000;

    static void Dijkstra1(int start){
        Arrays.fill(min_, MAX_VAL);
        min_[start] = 0;
        PriorityQueue<Node1238> pq = new PriorityQueue<>();
        
        pq.add(new Node1238(start,0));
        while(!pq.isEmpty()){
            Node1238 Node1238 = pq.poll();
            if(min_[Node1238.a] < Node1238.b) continue;
            for(Node1238 n : map[Node1238.a]){
                if(min_[n.a] > min_[Node1238.a]+n.b){
                    min_[n.a] = min_[Node1238.a]+n.b;
                    pq.offer(new Node1238(n.a, min_[n.a]));
                }
            }
        }
    }

    static void Dijkstra2(int start){
        Arrays.fill(min_2, MAX_VAL);
        min_2[start] = 0;

        PriorityQueue<Node1238> pq = new PriorityQueue<>();
        
        pq.add(new Node1238(start,0));
        while(!pq.isEmpty()){
            Node1238 Node1238 = pq.poll();
            if(min_2[Node1238.a] < Node1238.b) continue;
            for(Node1238 n : map2[Node1238.a]){
                if(min_2[n.a] > min_2[Node1238.a]+n.b){
                    min_2[n.a] = min_2[Node1238.a]+n.b;
                    pq.offer(new Node1238(n.a, min_2[n.a]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N+1];
        min_ = new int[N+1];
        map2 = new ArrayList[N+1];
        min_2 = new int[N+1];
        for(int i = 1 ; i <= N; i++){
            map[i] = new ArrayList<>();
            map2[i] = new ArrayList<>();
        }
            
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a].add(new Node1238(b,c));
            map2[b].add(new Node1238(a,c)); // 간선을 역으로 줘서 저장
        }
        Dijkstra1(K);   // K 에서 집으로 돌아가는 거리들
        Dijkstra2(K);   // k로 가는 거리들
        int ans = 0;
        for(int i = 1 ; i <= N; i++){
            ans = Math.max(ans, min_[i] + min_2[i]);
        }

        System.out.println(ans);
    }
}

class Node1238 implements Comparable<Node1238>{
    int a;
    int b;
    Node1238(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Node1238 o) {
        return this.b - o.b;
    }
}

/*test case

4 9 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3
2 1 100

answer = 10


*/