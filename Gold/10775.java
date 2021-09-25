// 제목 : 공항
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/10775
// 메모리(kb) : 22660
// 실행시간(ms) : 264

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	// parent[i] = j는 i보다 작거나 같은 수 중 가장 큰 수가 j라는 뜻
	static int[] parent;

	public static void main(String[] args) throws IOException {
		// 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 게이트 수, 비행기 수
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());

		// parent 배열 초기화
		parent = new int[G + 1];
		for (int i = 1; i <= G; i++)
			parent[i] = i;
		// 도킹시킬 수 있는 비행기 수
		int count = 0;

		// 각각의 비행기마다
		for (int i = 0; i < P; i++) {
			// 사용할 수 있는 게이트 번호 최댓값
			int Gi = Integer.parseInt(br.readLine());
			// 사용할 수 있는 게이트가 없다면 중단
			if (findParent(Gi) == 0)
				break;

			// 사용할 수 있는 게이트가 있다면, 도킹한 비행기 수를 하나 늘리고, 사용할 수 있는 게이트 번호를 갱신함
			count++;
			makeUnion(parent[Gi] - 1, parent[Gi]);
		}

		System.out.println(count);
	}

	// find 연산
	static int findParent(int node) {
		if (node == parent[node])
			return node;

		return parent[node] = findParent(parent[node]);
	}

	// union 연산
	static void makeUnion(int a, int b) {
		int aParent = findParent(a);
		int bParent = findParent(b);
		if (aParent > bParent)
			parent[a] = bParent;
		else
			parent[b] = aParent;
	}

}
