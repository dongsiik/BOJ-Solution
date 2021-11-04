
// 제목 : 내 선물을 받아줘
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/15559
// 메모리(kb) : 25884
// 실행시간(ms) : 364

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 지도의 크기와 지도
	static int N, M;
	static char[][] map;
	// 분리 집합 알고리즘에 필요한 부모 배열
	static int[] parent;

	public static void main(String[] args) throws IOException {

		init();
		printAnswer();
	}

	// 입력받기
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();

	}

	static void printAnswer() {

		// 부모 배열 초기화
		parent = new int[N * M];
		for (int i = 0; i < N * M; i++)
			parent[i] = i;

		// 처음 답을 N*M로 하고, 같은 선물로 이어질 경우 하나씩 뺄 예정
		int answer = N * M;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 지금 점, 지금 점의 방향으로 이어지는 다음 점
				int curNode = i * M + j;
				int nextNode = curNode;

				switch (map[i][j]) {
				case 'N':
					nextNode -= M;
					break;
				case 'S':
					nextNode += M;
					break;
				case 'W':
					nextNode--;
					break;
				case 'E':
					nextNode++;
					break;

				}

				// 두 점 연결해서 이번에 같은 경로에 있다면 정답 하나 빼기
				if (union(curNode, nextNode))
					answer--;
			}
		}

		// 출력
		System.out.println(answer);
	}

	// 분리 집합 알고리즘에 필요한 조상 노드 찾기, 합치기
	static int getParent(int node) {
		if (parent[node] == node)
			return node;

		return parent[node] = getParent(parent[node]);
	}

	static boolean union(int nodeA, int nodeB) {
		int parentA = getParent(nodeA);
		int parentB = getParent(nodeB);

		if (parentA == parentB)
			return false;

		if (parentA < parentB)
			parent[parentB] = parentA;
		else
			parent[parentA] = parentB;

		return true;
	}
}
