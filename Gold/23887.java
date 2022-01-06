// 제목 : 프린트 전달
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/23887
// 메모리(kb) : 109172
// 실행시간(ms) : 1196

// 공식 풀이 참고
// https://upload.acmicpc.net/2e037db3-396a-4b24-a6bc-808f187335ac/

// 코드가 많이 지저분합니다...

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	// 교실 크기, 학생 수, 시작 학생
	static int N, M, K, S;
	// 교실 배치
	static int[][] map;
	// 학생들
	static Student[] students;
	// 그래프
	static ArrayList<Student>[] graph;
	// DFS에서 방문 여부
	static boolean[] visited;
	// 학생별로 받아야할 프린터 수
	static int[] answer;

	public static void main(String args[]) throws IOException {

		// 입력받기
		init();
		// 프린트를 전달받는 경로 구하기
		makeGraph();
		// 학생별로 프린트를 받아야 할 양 구하기
		printAnswer();
	}

	// 입력받기
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		students = new Student[K + 1];

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()) - 1;
			int Y = Integer.parseInt(st.nextToken()) - 1;
			// 지도에도 표시하고, 학생 객체에도 좌표를 저장함
			map[X][Y] = i;
			students[i] = new Student(X, Y, i);
		}

		S = Integer.parseInt(br.readLine());
	}

	// 프린트를 전달받을 경로 구하기
	static void makeGraph() {
		// 델타 탐색용 배열
		int[] dx = { -1, -1, -1, 1, 1, 1, 0, 0 };
		int[] dy = { -1, 0, 1, -1, 0, 1, -1, 1 };

		// 인접리스트로 표현한 그래프
		graph = new ArrayList[K + 1];
		for (int i = 1; i <= K; i++) {
			graph[i] = new ArrayList<>();
		}

		// 빨리 받는 순서, 적은 번호 순서대로 프린트를 받는 우선순위 큐
		PriorityQueue<Student> pq = new PriorityQueue<>();
		pq.offer(students[S]);
		students[S].time = 0;

		while (!pq.isEmpty()) {
			Student curStudent = pq.poll();

			// 8방향 탐색
			for (int d = 0; d < 8; d++) {
				int nx = curStudent.x + dx[d];
				int ny = curStudent.y + dy[d];
				int nTime = curStudent.time + 1;
				// 범위 안이고, 학생이 있고, 처음 방문했다면 큐에 넣고, 연결해줌
				if (checkInterior(nx, ny) && map[nx][ny] > 0) {
					Student nextStudent = students[map[nx][ny]];
					if (nextStudent.time > nTime) {
						nextStudent.time = nTime;
						pq.offer(nextStudent);
						graph[curStudent.number].add(nextStudent);
						graph[nextStudent.number].add(curStudent);
					}
				}
			}
		}

	}

	// 정답 구하기
	static void printAnswer() {
		// 받아야할 프린트 수, 방문여부
		answer = new int[K + 1];
		visited = new boolean[K + 1];

		// DFS
		DFS(students[S]);

		// 모두 전달할 수 있다면 경로 출력, 아니라면 -1 출력
		if (answer[S] == K) {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= K; i++) {
				sb.append(answer[i]).append(' ');
			}
			System.out.println(sb);
		} else {
			System.out.println(-1);
		}
	}

	static boolean checkInterior(int nx, int ny) {
		if (nx >= 0 && nx < N && ny >= 0 && ny < M)
			return true;
		return false;
	}

	static int DFS(Student student) {

		// 방문 처리
		visited[student.number] = true;
		int res = 1;

		// 방문하지 않은 인접한 점들에 대해 재귀
		for (Student nearStudent : graph[student.number]) {
			if (!visited[nearStudent.number]) {
				res += DFS(nearStudent);
			}
		}

		return answer[student.number] = res;
	}

	static class Student implements Comparable<Student> {
		int number, time, x, y;

		public Student(int x, int y, int number) {
			this.x = x;
			this.y = y;
			this.number = number;
			this.time = 1_000_000;
		}

		@Override
		public int compareTo(Student o) {
			if (this.time != o.time) {
				return this.time - o.time;
			} else {
				return this.number - o.number;
			}
		}
	}

}