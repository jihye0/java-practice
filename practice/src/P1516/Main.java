package P1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Building implements Comparable<Building> {
	int num;
	int time;

	public Building(int num, int time) {
		// super();
		this.num = num;
		this.time = time;
	}

	@Override
	public int compareTo(Building o) {
		// TODO Auto-generated method stub
		return this.time - o.time;
	}
}

public class Main {
	static int N; // 건물의 종류
	static Building[] buildings;
	static int[] indegree;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		N = Integer.parseInt(br.readLine());
		buildings = new Building[N + 1];
		adj = new ArrayList[N + 1];
		indegree=new int[N+1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
			while (true) {
				int num = Integer.parseInt(st.nextToken());
				if (num == -1)
					break;
				adj[num].add(i);
				indegree[i]++;
			}
		}
		PriorityQueue<Building> q = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				q.add(buildings[i]);
		}
//		for (int i = 1; i <= N; i++)
//			System.out.println(adj[i]);
//		
//		System.out.println(Arrays.toString(indegree));

		while (q.isEmpty() == false) {
//			for(int i=1; i<=N; i++) {
//				System.out.print(buildings[i].time+" ");
//			}
			int now = q.poll().num;
//			System.out.println(now);
			for(int next: adj[now]) {
				indegree[next]--;
				if(indegree[next]==0) {
					buildings[next].time+=buildings[now].time;
					q.offer(new Building(next, buildings[next].time));
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			sb.append(buildings[i].time).append("\n");
		}
		System.out.print(sb);

	}

}
