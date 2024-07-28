package practice;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int a, b;
	static int[] a_list;
	static int[] b_list;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		// System.out.println(T);
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		// System.out.println(a);
		st = new StringTokenizer(br.readLine());
		a_list = new int[a];
		for (int i = 0; i < a; i++) {
			a_list[i] = Integer.parseInt(st.nextToken());
		}
		// System.out.println(Arrays.toString(a_list));
		st = new StringTokenizer(br.readLine());
		b = Integer.parseInt(st.nextToken());
		// System.out.println(b);
		st = new StringTokenizer(br.readLine());
		b_list = new int[b];
		for (int i = 0; i < b; i++) {
			b_list[i] = Integer.parseInt(st.nextToken());
		}
		//입력받은 수 변수에 저장
		//subA에 더한 값 저장
		List<Long> subA=new ArrayList<>();
		List<Long> subB=new ArrayList<>();
		
		for (int i = 0; i < a; i++) {
			long sum=0;
			for (int j = i; j < a; j++) {
				sum+=a_list[j];
				subA.add(sum);
			}
		}
		for (int i = 0; i < b; i++) {
			long sum=0;
			for (int j = i; j < b; j++) {
				sum+=b_list[j];
				subB.add(sum);
			}
		}
		// System.out.println(Arrays.toString(subA));
		//오름차순으로 정리
		Collections.sort(subA);
		// System.out.println(Arrays.toString(subA));
		// System.out.println(Arrays.toString(subB));
		//내림차순으로 정리
		Collections.sort(subB, Comparator.reverseOrder());
		// System.out.println(Arrays.toString(subB));

		int pA = 0; //subA의 포인터
		int pB = 0; //subB의 포인터
		long sum;
		long result = 0;
		while (true) {
			long currentA=subA.get(pA);
			long target=T-currentA;
			
			if (subB.get(pB) > target) {
				pB++;
			} else if (subB.get(pB) < target) {
				pA++;
			} else { // sum=T
				long countA=0;
				long countB=0;
				while(pA<subA.size() && subA.get(pA)==currentA) {
					countA++;
					pA++;
				}
				while(pB<subB.size() && subB.get(pB)==target) {
					countB++;
					pB++;
				}
				result += countA * countB;
			}
			
			if ((pA >= a * (a + 1) / 2) || (pB >= b * (b + 1) / 2)) {
				break;
			}
		}
		// System.out.println("답");
		System.out.println(result);
	}

}
