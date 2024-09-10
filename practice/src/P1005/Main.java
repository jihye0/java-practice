package P1005;

import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int N;
	static int K;
	static ArrayList<Integer>[] next;
	static int[] indegree;
	static int[] time;
	static int[] total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 건물의 개수
			K = Integer.parseInt(st.nextToken()); // 규칙의 개수
			st = new StringTokenizer(br.readLine());
			time = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			indegree = new int[N + 1];
			next = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				next[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				next[a].add(b);
				indegree[b]++;
			}
			total = new int[N + 1];
			Arrays.fill(total, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			// pre 하나도 없는 것
			for (int i = 1; i <= N; i++) {
				total[i]=time[i];
				if (indegree[i] == 0) {
					q.offer(i);
				}
			}
			int ans = Integer.parseInt(br.readLine());
			while (!q.isEmpty()) {
				int now = q.poll();
				for (int i : next[now]) {
					total[i]=Math.max(total[i], total[now]+time[i]);
					indegree[i]--;
					if(indegree[i]==0)
						q.offer(i);
				}
			}

			sb.append(total[ans]);
			sb.append('\n');
		}
		System.out.println(sb);

	}

}
