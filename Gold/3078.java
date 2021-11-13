// 제목 : 좋은 친구
// 티어 : 골드 3
// 링크 : https://www.acmicpc.net/problem/3078
// 메모리(kb) : 36228
// 실행시간(ms) : 328

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//전체 학생 수, 친해질 수 있는 범위
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//범위 안에서, 이름 길이별 친구 수
		int[] nameLength = new int[21];
		
		//범위 안에 있는 이름 길이들
		ArrayDeque<Integer> names = new ArrayDeque<>(K+1);
		
		//출력할 정답
		long answer = 0L;
		
		for(int i=0;i<N;i++) {
			//범위를 벗어나는 친구가 있으면, 빼고 개수도 줄임
			if(names.size()>K) {
				int oldName = names.poll();
				nameLength[oldName]--;
			}
			
			//지금 학생의 이름 길이를 입력받고, 자신보다 성적이 높으면서 K등 이내이고, 이름 길이가 같은 친구 수를 정답에 더함
			int name = br.readLine().length();
			answer += nameLength[name];
			
			//자신을 큐에 넣고, 개수도 하나 늘림
			names.offer(name);
			nameLength[name]++;
		}
		
		//출력
		System.out.println(answer);
	}
}