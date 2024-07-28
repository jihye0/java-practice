package P10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		List<Integer> stack=new ArrayList<>();
		for(int i=0; i<N; i++) {
			String x;
			st = new StringTokenizer(br.readLine());
			x=st.nextToken();
			if(x.equals("push")) {
				int num=Integer.parseInt(st.nextToken());
				stack.add(num);
				
			}
			else if(x.equals("pop")) {
				if(stack.size()==0) System.out.println(-1);
				else {
					System.out.println(stack.get(stack.size() - 1));
					stack.remove(stack.size() - 1);
				}
				
			}
			else if(x.equals("size")) {
				int size=stack.size();
				System.out.println(size);
			}
			else if(x.equals("empty")) {
				if(stack.size()==0) System.out.println(1);
				else System.out.println(0);
				
			}
			else if(x.equals("top")) {
				if(stack.size()==0) System.out.println(-1);
				else {
					System.out.println(stack.get(stack.size() - 1));
				}
				
			}
			//System.out.println(x);
		//	x = Integer.parseInt(st.nextToken());

		}

	}

}
