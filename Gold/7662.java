// 제목 : 이중 우선순위 큐
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/7662
// 메모리(kb) : 312536
// 실행시간(ms) : 2428
// 참고한 링크 : https://girawhale.tistory.com/14

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());

			// 트리 맵을 이중 우선순위 큐처럼 사용. Key는 정수, Value는 큐에 그 정수가 몇 번 있는지를 나타냄
			// https://coding-factory.tistory.com/557
			TreeMap<Integer, Integer> q = new TreeMap<>();

			for (int i = 0; i < k; i++) {
				// 연산자와 숫자 입력받기
				StringTokenizer st = new StringTokenizer(br.readLine());
				String operation = st.nextToken();
				int inputNum = Integer.parseInt(st.nextToken());

				// 삽입 연산이라면 입력받은 값의 개수를 하나 늘려줌
				if (operation.equals("I")) {
					q.put(inputNum, q.getOrDefault(inputNum, 0) + 1);
				}
				// 삭제라면
				else {
					// 큐가 비었으면 생략
					if (q.isEmpty())
						continue;

					// 입력받은 숫자에 따라 최댓값과 최솟값을 찾아냄
					int numFromQ = inputNum == 1 ? q.lastKey() : q.firstKey();
					// 그 값의 개수를 하나 줄여주고, 개수가 0이 되면(==빼기 전에 하나 있었다면) 그 값을 아예 큐에서 없애버림
					if (q.put(numFromQ, q.get(numFromQ) - 1) == 1) {
						q.remove(numFromQ);
					}
				}
			}

			// 결과 출력
			if (q.isEmpty())
				sb.append("EMPTY\n");
			else
				sb.append(q.lastKey()).append(' ').append(q.firstKey()).append('\n');
		}

		System.out.println(sb);
	}
}