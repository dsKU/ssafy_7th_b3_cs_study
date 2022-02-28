package implemented;
import java.io.*;
import java.util.*;

public class beakjoon10799 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int ans = 0;
        int len = str.length();
        Stack<Character> st = new Stack<>();
        for(int i = 0 ; i < len; i++){
            if(i+1 < len && str.charAt(i) == '(' && str.charAt(i+1) == ')'){
                ans += st.size();
                i++;
            }
            else if(str.charAt(i) == '(') st.add('(');
            else{
                ans += 1;
                st.pop();
                
            }
        }

        System.out.println(ans);
    }
}
