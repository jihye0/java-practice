package P1062;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int N, K;
	static String[] words;
	static boolean[] visited= new boolean[26];
	
	//static int[] visited={0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	static int length;
	static int max;

	public static void main(String[] args) throws FileNotFoundException{
		//System.setIn(new FileInputStream("src/DAY01/P1062/input3.txt"));
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		K=sc.nextInt();
		words=new String[N];
		for(int i=0; i<26; i++) {
		visited[i]=false;
		}
		if(K<5) {
			System.out.println(0);
			return;
		}else if(K==26) {
			System.out.println(N);
			return;
		}
		for(int i=0; i<N; i++) {
			words[i]=sc.next().replaceAll("[antic]", "");
		}
		visited['a'-'a']=true;
		visited['n'-'a']=true;
		visited['t'-'a']=true;
		visited['i'-'a']=true;
		visited['c'-'a']=true;
		//System.out.println(Arrays.toString(words));
		//int[] visited={0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		K=K-5;
		if(K==0) {
			max=countWords();
		}
		else {
			for(int i=0; i<26; i++) {
				if(visited[i]==false) {
					dfs(i);
				}
			}	
		}
		System.out.println(max);
	}
	
	public static void dfs(int index) {
		//체크인 방문시작
		visited[index]=true;
		length++;
		//System.out.println(Arrays.toString(alphabet));
		//목적지인가
		if (length==K) {
			max=Math.max(countWords(), max);
			//단어 check
		}
		else {
			for(int k=index+1; k<26; k++) {
				//연결된 곳을 순회
				if(visited[k]==false){
					//갈 수 있는가
					//간다
					dfs(k);
				}		
			}
		}
		//체크아웃 방문완료
		visited[index]=false;
		length--;
		//초기화를 언제
	}
	
	static int countWords() {
		int count=0;
		for(int i=0; i<N; i++) {
			boolean isReadable=true;
			String word=words[i];
			for(int j=0; j<word.length(); j++) {
				if(visited[word.charAt(j)-'a']==false) {
					isReadable=false;
					break;
				}
			}
			if(isReadable==true) {
				count++;
			}
		}
		return count;
	}
	
}

