
// 제목 : 피리 부는 사나이
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/16724
// 메모리(kb) : 25116
// 실행시간(ms) : 312

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 지도의 크기와 지도
	static int N, M;
	static char[][] map;
	// 그 발판이 기존에 확인한 방음 시설로 연결되는지를 나타냄
	static int[][] checked;

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

		checked = new int[N][M];
	}

	static void printAnswer() {
		// 방음시설 수, 확인해본 경로의 수
		int answer = 0;
		int count = 0;

		// 모든 점에 대해서
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 방문해보지 않았고, 방음시설로 연결되지 않는다면 방음시설 수 하나 추가
				if (checked[i][j] == 0 && dfs(i, j, ++count)) {
					answer++;
				}
			}
		}

		// 출력
		System.out.println(answer);
	}

	// dfs
	static boolean dfs(int rowNum, int colNum, int count) {
		// 이번에 탐색해본 경로를 다시 밟는다면, 방음시설이 필요하다는 뜻이므로 true
		if (checked[rowNum][colNum] == count)
			return true;
		// 기존에 탐색해본 경로를 다시 밟는다면, 기존의 방음시설로 이어지므로 false
		else if (checked[rowNum][colNum] != 0 && checked[rowNum][colNum] < count)
			return false;

		// 방문 처리
		checked[rowNum][colNum] = count;

		// 다음 칸 따지기
		int nr = rowNum;
		int nc = colNum;

		if (map[rowNum][colNum] == 'L')
			nc--;
		if (map[rowNum][colNum] == 'R')
			nc++;
		if (map[rowNum][colNum] == 'U')
			nr--;
		if (map[rowNum][colNum] == 'D')
			nr++;

		// 재귀적으로 결과를 받아옴
		if (dfs(nr, nc, count))
			return true;
		else
			return false;

	}
}
