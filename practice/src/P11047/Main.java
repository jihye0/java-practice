package P11047;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; //동전의 개수 
	static int K; //내가 만들어야 하는 가치의크기 
	static int[] value;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		value=new int[N];
		for(int i=0; i<N; i++) {
			int tmp=Integer.parseInt(br.readLine());
			value[i]=tmp;
		}
		int ans=0;
		for(int i=N-1; i>=0; i--) {
			while(K>=value[i]) {
				//K의 값을 update
				K=K-value[i];
				ans++;
			}
		}
		System.out.println(ans);

	}

}
