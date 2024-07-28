package P9202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] mx = { -1, 1, 0, 0, -1, 1, -1, 1 };
	static int[] my = { 0, 0, -1, 1, -1, 1, 1, -1 };
	static int[] score = { 0, 0, 0, 1, 1, 2, 3, 5, 11 };

	static int w;
	static int b;
	static char[][] map;
	static boolean[][] visited;
	static String answer;
	static int sum;
	static int count;
	static StringBuilder sb = new StringBuilder();
	static TriNode root;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		w = Integer.parseInt(br.readLine()); // 단어의
		String word[]=new String[w];
		root=new TriNode();
		for (int j = 0; j < w; j++) {
			word[j] = br.readLine();
		}
		for(int j=0; j<w; j++) {
			insert(word[j]);
		}
		br.readLine();
		b = Integer.parseInt(br.readLine()); // 보드의 개수
		// System.out.println(b);
		//root = new TriNode[;
		
		for (int i = 0; i < b; i++) {
			
			
			//TriNode now_root = root[i];
			

			if (i != 0)
				br.readLine();
			sb = new StringBuilder();
			sum = 0;
			count = 0;
			answer = null;
			map = new char[4][4];
			visited = new boolean[4][4];
			String board;
			for (int j = 0; j < 4; j++) {
				// if(j!=0) br.readLine();
				board = br.readLine();
				// System.out.println(board);
				map[j][0] = board.charAt(0);
				map[j][1] = board.charAt(1);
				map[j][2] = board.charAt(2);
				map[j][3] = board.charAt(3);
				// System.out.println(Arrays.toString(map[j]));
			}
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					if (root.hasChild(map[y][x])) {
						search(y, x, 1, root.getChild(map[y][x]));
					}
				}
			}
			StringBuilder ans_sb = new StringBuilder();
			ans_sb.append(sum).append(" ").append(answer).append(" ").append(count);
			System.out.println(ans_sb);
			root.clearHit();

		}
	}

	static void search(int y, int x, int length, TriNode node) {
		// 체크인
		visited[y][x] = true;
		sb.append(map[y][x]); // string 더하기 연산 자바에서 너무 느림

		// 목적지인가
		if (node.isWord && node.isHit == false) {
			node.isHit = true;
			// 점수계산
			sum += score[length];
			count++;
			String foundWord = sb.toString();
			if (answer == null)
				answer = foundWord;
			else if (compare(answer, foundWord) < 0) {
				answer = foundWord;
			}
		}
		// 연결된 곳을 순회
		for (int i = 0; i < 8; i++) {
			int ty = y + my[i];
			int tx = x + mx[i];
			// 갈 수 있는가 map 영역 체크 방문한 적 없고 node가 해당 자식을 가지는가
			if (0 <= ty && ty < 4 && 0 <= tx && tx < 4) {
				if (visited[ty][tx] == false && node.hasChild(map[ty][tx])) {
					// 간다
					search(ty, tx, length + 1, node.getChild(map[ty][tx]));
				}
			}
		}
		// 체크아웃
		visited[y][x] = false;
		sb.deleteCharAt(length - 1);

	}

	static int compare(String a, String b) {
		int result = Integer.compare(a.length(), b.length());
		if (result == 0) {
			return b.compareTo(a);
		}
		return result;
	}

	static void insert(String word) {
		TriNode current = root;
		for (int i = 0; i < word.length(); i++) {
			if (current.hasChild(word.charAt(i)) == false) {
				current.child[word.charAt(i) - 'A'] = new TriNode();
			}
			current = current.getChild(word.charAt(i));
		}
		current.isWord = true;
	}

}

class TriNode {
	TriNode[] child = new TriNode[26];
	boolean isWord = false; // 단어의 끝이
	boolean isHit = false;

	void clearHit() {
		// map하나 돌고나서 해주기
		isHit = false;
		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].clearHit();
			}
		}
	}

	boolean hasChild(char c) {
		return child[c - 'A'] != null;
	}

	TriNode getChild(char c) {
		return child[c - 'A'];
	}

}