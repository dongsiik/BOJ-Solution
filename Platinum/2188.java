// 제목 : 축사 배정
// 티어 : 플래티넘 4
// 링크 : https://www.acmicpc.net/problem/2188
// 메모리(kb) : 16772
// 실행시간(ms) : 208

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	//소 수, 축사 수
	static int N, M;
	//소별로 희망하는 축사번호 목록
	static ArrayList<Integer>[] preference;
	//축사에 들어가있는 소 번호
	static int[] cowInCage;
	//DFS에서 방문 여부배열
	static boolean[] checked;

	public static void main(String[] args) throws IOException {

		init();
		bipartiteMatching();

	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		preference = new ArrayList[N + 1];
		cowInCage = new int[M + 1];
		checked = new boolean[M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			preference[i] = new ArrayList<>(S);
			for (int j = 0; j < S; j++) {
				preference[i].add(Integer.parseInt(st.nextToken()));
			}
		}

	}

	static void bipartiteMatching() {
		
		//제대로 배정된 소의 수
		int answer = 0;

		for (int i = 1; i <= N; i++) {
			Arrays.fill(checked, false);
			//배정되었다면 개수 하나 증가
			if (dfs(i)) answer++;
		}

		System.out.println(answer);
	}

	//cow번 소가 축사를 배정받을 수 있다면 true, 아니라면 false
	static boolean dfs(int cow) {

		//선호하는 축사들에 대해서
		for (int cageNum : preference[cow]) {
			//이미 처리했다면 생략, 아니라면 처리했다고 표시
			if (checked[cageNum]) continue;
			checked[cageNum] = true;

			//축사가 비었거나, 이전주인이 다른 축사를 찾아서 간다면
			if (cowInCage[cageNum] == 0 || dfs(cowInCage[cageNum])) {
				//이 축사를 차지하고 true 리턴
				cowInCage[cageNum] = cow;
				return true;
			}
		}

		//축사를 못 찾았다면 false 리턴
		return false;
	}
}