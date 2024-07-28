package P1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Integer> heap=new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		heap.add(-1);
	//	System.out.println(N);
		
		for(int i=0; i<N; i++) {
			int x;
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			if(x==0) {
				if(heap.size()==1) {
					System.out.println(0);
				}
				else {
					System.out.println(delete());
				}
			}
			else {
				insert(x);
			}

		}
	}
	static int delete() {
		//한개 이상 들어있
		//배열에서 가장 작은 값 반환하고 그 값을 배열에서 제거
		//루트 노드 삭제
		//한개만 있을 때
		//System.out.println(heap);
		int min=heap.get(1);
		heap.remove(1);
		if(heap.size()==1 || heap.size()==2) {
			return min;
		}
		else {
			heap.add(1, heap.get(heap.size()-1));
			heap.remove(heap.size()-1);
			int index=1;
			while(true) {
				if(2*index==(heap.size()-1)) {
					if(heap.get(index)<=heap.get(index*2)) break;
					else {
						Collections.swap(heap,index,index*2);
						break;
					}
				}
				else if((heap.get(index)<heap.get(index*2)) && (heap.get(index)<heap.get(index*2+1))) break;
				else {
					if(heap.get(index*2)<heap.get(index*2+1)) {
						Collections.swap(heap,index,index*2);
						index=index*2;
					}
					else {
						Collections.swap(heap,index,index*2+1);
						index=index*2+1;
					}
				}
				//index가 리프노드라면
				if(2*index>=heap.size()) break;
			}
			
		}
		
		
		
		return min;
		
	}
	
	
	static void insert(int x) {
		heap.add(x);
		//System.out.println(heap);
		int index=heap.size()-1;
		if(index>1) {
			while(true) {
				if(heap.get(index/2)>heap.get(index)) {
					//바꾸
					Collections.swap(heap,index/2,index);
					index=index/2;
				}
				else break;
				if(index==1) break;
			}
			
		}
		
		
	}

}
