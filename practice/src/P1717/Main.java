package P1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		parent=new int[n+1];
		for(int i=0; i<n+1; i++) {
			parent[i]=i;
		}
		for(int i=0; i<m; i++) {
			st=new StringTokenizer(br.readLine());
			int op=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			if(op==0) {
				union(a, b);
			}else {
				if(find(a)==find(b))
					System.out.println("YES");
				else System.out.println("NO");
			}
		}

	}
	
	static int find(int num) {
		if(parent[num]==num) return num;
		else return parent[num]=find(parent[num]);
	}
	
	
	static void union(int a, int b) {
		int aParent=find(a);
		int bParent=find(b);
		parent[aParent]=bParent;
	}

}
