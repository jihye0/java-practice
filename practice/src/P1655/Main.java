package P1655;

import java.io.*;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> minPQ = new PriorityQueue<>();
		int num;
		for (int i = 0; i < N; i++) {
			num = Integer.parseInt(br.readLine());
			if (maxPQ.size()==minPQ.size()) {
				maxPQ.add(num);
			} else {
				minPQ.add(num);
			}
			if (!minPQ.isEmpty()) {
				if (maxPQ.peek() > minPQ.peek())
				 {
					minPQ.add(maxPQ.poll());
					maxPQ.add(minPQ.poll());
				}
			}
			sb.append(maxPQ.peek()+"\n");
			//System.out.println(maxPQ.peek());
		}
		System.out.println(sb.toString());

	}

}
