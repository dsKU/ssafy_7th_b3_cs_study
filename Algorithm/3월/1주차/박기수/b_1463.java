package ps_java;


import java.io.*;
import java.util.Arrays;

public class b_1463 {
	static int N, arr[];
	public static void main(String[] args) throws Exception{
		input();
		arr = new int[N+1];
		Arrays.fill(arr, 1000000000);
		arr[N] = 0;
		
		for(int i = N; i>=1; i--) {
			if(i%3==0)
				arr[i/3] = Math.min(arr[i]+1, arr[i/3]);
			if(i%2==0)
				arr[i/2] = Math.min(arr[i]+1, arr[i/2]);
			arr[i-1] = Math.min(arr[i-1], arr[i]+1);
		}
		System.out.println(arr[1]);
	}
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		br.close();
	}
}	
