// 제목 : 벽 부수고 이동하기 4
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/16946
// 메모리(kb) : 157760
// 실행시간(ms) : 980

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	// 델타 탐색
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	//지도의 세로, 가로 크기와 지도
	static int n, m;
	static int[][] graph;
	//평지로 연결된 영역들의 칸 수
	static int[] areaOfSection;

	public static void main(String[] args) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				graph[i][j] = str.charAt(j) - '0';
			}
		}

		//평지로 연결된 영역에 영역 번호 매기기
		int numOfSections = setSectionNum();
		//영역 번호별로 칸수 구하기
		countAreaOfSections(numOfSections);

		//출력
		printAnswer();

	}

	//dfs로 평지에 번호를 매김
	private static int setSectionNum() {
		int sectionNum = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (graph[i][j] == 0) {
					dfs(i, j, ++sectionNum);
				}
			}
		}

		return sectionNum;
	}

	static void dfs(int x, int y, int sectionNum) {
		graph[x][y] = sectionNum;

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (needDFS(nx, ny)) {
				dfs(nx, ny, sectionNum);
			}
		}

	}
	
	//영역별로 면적을 구함
	private static void countAreaOfSections(int numOfSections) {
		areaOfSection = new int[numOfSections + 1];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (graph[i][j] != 1) {
					areaOfSection[graph[i][j]]++;
				}
			}
		}
	}

	//출력
	static void printAnswer() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				//평지였던 곳은 0으로 출력
				if (graph[i][j] != 1)
					sb.append(0);
				else {
					//벽이 있던 곳이라면 일단 그 칸을 부수면서 영역 1로 시작
					int areaNearWall = 1;
					//인근에 평지가 있을 때, 서로 다른 구역들만 구하기 위해 HashSet 사용
					HashSet<Integer> dupChecker = new HashSet<>();

					//델타 탐색
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						//평지라면 넣어줌
						if (areaNearWall(nx, ny)) {
							dupChecker.add(graph[nx][ny]);
						}
					}

					//주변의 서로 다른 평지마다, 연결된 영역을 늘려줌
					for (Integer sectionNum : dupChecker) {
						areaNearWall += areaOfSection[sectionNum];
					}

					//출력문에 일의 자리 출력
					sb.append(areaNearWall % 10);
				}
			}
			sb.append('\n');
		}

		//출력
		System.out.println(sb);
	}



	static boolean needDFS(int nx, int ny) {
		if (nx >= 0 && nx < n && ny >= 0 && ny < m && graph[nx][ny] == 0)
			return true;

		return false;
	}

	static boolean areaNearWall(int nx, int ny) {
		if (nx >= 0 && nx < n && ny >= 0 && ny < m && graph[nx][ny] != 1)
			return true;

		return false;
	}

}