package P23291;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int K;
	static ArrayList<Integer>[] arr;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int ans;

	static void print_arr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < arr[i].size(); j++) {
				System.out.print(arr[i].get(j));
				System.out.print(' ');
			}
			System.out.print('\n');
		}
		System.out.println();
	}

	static void move_fish() {
		ArrayList<Integer>[] tmp_arr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tmp_arr[i] = new ArrayList<>();
			for (int j = 0; j < arr[i].size(); j++) {
				tmp_arr[i].add(arr[i].get(j));
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < tmp_arr[i].size(); j++) {
				int tmp = tmp_arr[i].get(j);
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx < 0 || nx > N - 1 || ny < 0 || ny > tmp_arr[i].size() - 1)
						continue;
					if (tmp_arr[nx].size() == 0)
						continue;
					if (tmp_arr[nx].size() <= ny)
						continue;

					if (tmp_arr[nx].get(ny) - tmp_arr[i].get(j) >= 5) {
						tmp += (tmp_arr[nx].get(ny) - tmp_arr[i].get(j)) / 5;
					} else if (tmp_arr[i].get(j) - tmp_arr[nx].get(ny) >= 5) {
						tmp -= (tmp_arr[i].get(j) - tmp_arr[nx].get(ny)) / 5;
					}
				}
				arr[i].set(j, tmp);
			}
		}
		//print_arr();
	}

	static void make_line() {
		ArrayList<Integer>[] tmp_arr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tmp_arr[i] = new ArrayList<>();
			for (int j = 0; j < arr[i].size(); j++) {
				tmp_arr[i].add(arr[i].get(j));
			}
		}
		for (int i = 0; i < N; i++) {
			arr[i].clear();
		}
		int index = 0;
		for (int i = 0; i < N; i++) {
			if (tmp_arr[i].size() > 0) {
				for (int j = 0; j < tmp_arr[i].size(); j++) {
					arr[index].add(tmp_arr[i].get(j));
					index++;
				}
			}
		}
		// ans++;
		//print_arr();
	}

	static void spin() {
		for (int i = 0; i < N / 2; i++) {
			arr[i + N / 2].add(arr[N / 2 - 1 - i].get(0));
		}
		for (int i = 0; i < N / 2; i++) {
			arr[i].clear();
		}
		for (int i = 0; i < N / 4; i++) {
			arr[i + N / 4 * 3].add(arr[N / 4 * 3 - 1 - i].get(1));
			arr[i + N / 4 * 3].add(arr[N / 4 * 3 - 1 - i].get(0));
		}
		for (int i = 0; i < N / 4; i++) {
			arr[i + N / 2].clear();
		}
		//print_arr();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N];
		for (int i = 0; i < N; i++)
			arr[i] = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i].add(num);
		}

		while (true) {
			//print_arr();
			ans++;
			//System.out.println(ans);
			// 1. 작은 것에 한마리
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				min = Math.min(arr[i].get(0), min);
			}
			for (int i = 0; i < N; i++) {
				if (arr[i].get(0) == min)
					arr[i].set(0, min + 1);
			}
			// 2. 공중부양 작업
			arr[1].add(arr[0].get(0));
			arr[0].remove(0);

			while (true) {
				//System.out.println(ans);
				int start = -1;
				int end = -1;
				int len = -1;
				for (int i = 0; i < N; i++) {
					if (arr[i].size() >= 2 && start < 0) {
						start = i;
						len = arr[i].size();
					}
					if (start >= 0 && arr[i].size() < 2 && end < 0)
						end = i;
				}
				if(end < 0 || start<0) break;

				if (end + len - 1 >= N)
					break;
				for (int i = 0; i < len; i++) {
					for (int j = end - 1; j >= start; j--) {
						arr[end + i].add(arr[j].get(i));
					}
				}
				for (int i = start; i < end; i++)
					arr[i].clear();
				//if(end+len-1>=N-1) break;
				//print_arr();
			}
			move_fish();
			make_line();
			spin();
			move_fish();
			make_line();
			int cmax = 0;
			int cmin = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				cmax = Math.max(cmax, arr[i].get(0));
				cmin = Math.min(cmin, arr[i].get(0));
			}
			if (cmax - cmin <= K)
				break;
		}

		// print_arr();

		// print_arr();

		System.out.println(ans);

	}

}
