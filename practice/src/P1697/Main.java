package P1697;

import java.io.*;
import java.util.*;

class point{
	int pos;
	int cost;
	public point(int pos, int cost) {
		this.pos = pos;
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "point [now=" + pos + ", cost=" + cost + "]";
	}
	
	
}

public class Main {
	static int N; //수빈이의 위치 
	static int K; //동생의 위치 
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		Queue q=new ArrayDeque<point>();
		q.add(new point(N, 0));
		visited=new int[100001];
		visited[N]=0;
		Arrays.fill(visited, Integer.MAX_VALUE);
		int ans=Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			point now=(point) q.poll();
			//if(visited[now.pos]>now.cost) visited[now.pos]=now.cost;
			if(now.pos==K) {
				if(ans>now.cost) ans=now.cost;
			}
			else if(now.cost<ans) {
				if( now.pos-1>-1 && visited[now.pos-1]>now.cost+1) {
					visited[now.pos-1]=now.cost+1;
					q.add(new point(now.pos-1, now.cost+1));
				}
				if( now.pos+1<100001 && visited[now.pos+1]>now.cost+1) {
					visited[now.pos+1]=now.cost+1;
					q.add(new point(now.pos+1, now.cost+1));
				}
				if( now.pos*2<100001 && visited[now.pos*2]>now.cost) {
					visited[now.pos*2]=now.cost;
					q.add(new point(now.pos*2, now.cost));
				}
			}
		}
		System.out.println(ans);
	}

}
