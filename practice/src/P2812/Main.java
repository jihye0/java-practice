package P2812;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());


		StringBuilder sb = new StringBuilder();

		String str = br.readLine();
		Stack<Integer> stack=new Stack<>();
		stack.push(str.charAt(0)-'0');
		for (int i = 1; i < N; i++) {
			//System.out.println(stack.toString());
			int num = str.charAt(i) - '0';
			while(stack.peek()<num && K>0) {
				stack.pop();
				K--;
				if(K==0) break;
				if(stack.isEmpty()) break;
			}
			stack.add(num);
			//if(K==0) break;
		}
		
		if(K>0) {
			while(K>0) {
				stack.pop();
				K--;
			}
		}
		
		for(int i=0; i<stack.size(); i++) {
			sb.append(stack.get(i));
		}

		
		
		System.out.println(sb);

	}

}
