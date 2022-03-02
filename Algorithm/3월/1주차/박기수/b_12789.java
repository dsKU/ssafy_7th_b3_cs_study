package ps_java;


import java.io.*;
import java.util.*;

public class b_12789 {
	static int N,arr[];
	static Stack<Integer> s = new Stack<>();
	public static void main(String[] args) throws Exception{
		input();
		int next = 1;
		
		for(int i : arr) {
			s.add(i);
			while(!s.isEmpty() && s.peek() == next) {
				next++;
				s.pop();
			}
		}
		if(next==N+1)
			System.out.println("Nice");
		else
			System.out.println("Sad");
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		br.close();
	}
}	
