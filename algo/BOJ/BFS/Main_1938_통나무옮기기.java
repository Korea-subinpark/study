import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1938_통나무옮기기 {
	static int N, min;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<Tree> q;
	static Tree target;//옮겨야 하는 위치
	
	public static class Tree {
		int x, y;//중간좌표만 저장
		int cnt;//동작 횟수
		boolean s;//현재 상태(가로방향: false)
		public Tree(int x, int y, int cnt, boolean s) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.s = s;
		}
	}
	
	public static boolean inRange(int x, int y, boolean flag) {//범위 안에 있는지 확인
		if(!flag) {//가로 방향인 경우
			return (x >= 0 && x < N && y - 1 >= 0 && y + 1 < N);
		} else {//세로 방향인 경우
			return (x - 1 >= 0 && x + 1 < N && y >= 0 && y < N);
		}
	}
	
	public static boolean move(Tree t, int dir) {
		//dir: 0-상, 1-하, 2-좌, 3-우, 4-회전
		switch(dir) {
			case 0://상
				if(!t.s) {//가로 방향인 경우
					if(inRange(t.x - 1, t.y, false) && !visited[t.x - 1][t.y][0] && map[t.x - 1][t.y] == 0 && map[t.x - 1][t.y - 1] == 0 && map[t.x - 1][t.y + 1] == 0) {
						visited[t.x - 1][t.y][0] = true;
						q.add(new Tree(t.x - 1, t.y, t.cnt + 1, false));
						return true;
					}
				} else {//세로 방향인 경우
					if(inRange(t.x - 1, t.y, true) && !visited[t.x - 1][t.y][1] && map[t.x - 2][t.y] == 0) {
						visited[t.x - 1][t.y][1] = true;
						q.add(new Tree(t.x - 1, t.y, t.cnt + 1, true));
						return true;
					}
				}
				break;
			case 1://하
				if(!t.s) {//가로 방향인 경우
					if(inRange(t.x + 1, t.y, false) && !visited[t.x + 1][t.y][0] && map[t.x + 1][t.y] == 0 && map[t.x + 1][t.y - 1] == 0 && map[t.x + 1][t.y + 1] == 0) {
						visited[t.x + 1][t.y][0] = true;
						q.add(new Tree(t.x + 1, t.y, t.cnt + 1, false));
						return true;
					}
				} else {//세로 방향인 경우
					if(inRange(t.x + 1, t.y, true) && !visited[t.x + 1][t.y][1] && map[t.x + 2][t.y] == 0) {
						visited[t.x + 1][t.y][1] = true;
						q.add(new Tree(t.x + 1, t.y, t.cnt + 1, true));
						return true;
					}
				}	
				break;
			case 2://좌
				if(!t.s) {//가로 방향인 경우
					if(inRange(t.x, t.y - 1, false) && !visited[t.x][t.y - 1][0] && map[t.x][t.y - 2] == 0) {
						visited[t.x][t.y - 1][0] = true;
						q.add(new Tree(t.x, t.y - 1, t.cnt + 1, false));
						return true;
					}
				} else {//세로 방향인 경우
					if(inRange(t.x, t.y - 1, true) && !visited[t.x][t.y - 1][1] && map[t.x - 1][t.y - 1] == 0 && map[t.x][t.y - 1] == 0 && map[t.x + 1][t.y - 1] == 0) {
						visited[t.x][t.y - 1][1] = true;
						q.add(new Tree(t.x, t.y - 1, t.cnt + 1, true));
						return true;
					}
				}
				break;
			case 3://우
				if(!t.s) {//가로 방향인 경우
					if(inRange(t.x, t.y + 1, false) && !visited[t.x][t.y + 1][0] && map[t.x][t.y + 2] == 0) {
						visited[t.x][t.y + 1][0] = true;
						q.add(new Tree(t.x, t.y + 1, t.cnt + 1, false));
						return true;
					}
				} else {//세로 방향인 경우
					if(inRange(t.x, t.y + 1, true) && !visited[t.x][t.y + 1][1] && map[t.x - 1][t.y + 1] == 0 && map[t.x][t.y + 1] == 0 && map[t.x + 1][t.y + 1] == 0) {
						visited[t.x][t.y + 1][1] = true;
						q.add(new Tree(t.x, t.y + 1, t.cnt + 1, true));
						return true;
					}
				}
				break;
			case 4://회전
				if(!t.s) {//가로 방향인 경우
					if(inRange(t.x, t.y, true) && !visited[t.x][t.y][1]) {
						if(map[t.x - 1][t.y - 1] == 0 && map[t.x - 1][t.y] == 0 && map[t.x - 1][t.y + 1] == 0) {//회전할 때 장애물이 없는지 확인
							if(map[t.x + 1][t.y - 1] == 0 && map[t.x + 1][t.y] == 0 && map[t.x + 1][t.y + 1] == 0) {
								visited[t.x][t.y][1] = true;
								q.add(new Tree(t.x, t.y, t.cnt + 1, true));
								return true;
							}
						}
					}
				} else {//세로 방향인 경우
					if(inRange(t.x, t.y, false) && !visited[t.x][t.y][0]) {
						if(map[t.x - 1][t.y - 1] == 0 && map[t.x][t.y - 1] == 0 && map[t.x + 1][t.y - 1] == 0) {//회전할 때 장애물이 없는지 확인
							if(map[t.x - 1][t.y + 1] == 0 && map[t.x][t.y + 1] == 0 && map[t.x + 1][t.y + 1] == 0) {
								visited[t.x][t.y][0] = true;
								q.add(new Tree(t.x, t.y, t.cnt + 1, false));
								return true;
							}
						}
					}
				}
				break;
			default:
				break;
		}
		return false;
	}
	
	public static boolean complete(Tree t) {//도착했는지 확인하는 함수
		return (t.x == target.x && t.y == target.y && t.s == target.s);
	}

	public static void bfs() {
		while(!q.isEmpty()) {
			Tree cur = q.poll();
			if(complete(cur)) {//목표지점에 도착했을 경우
				min = cur.cnt;
				q.clear();
				return;
			}
			for(int i = 0; i < 5; i++) {//상하좌우와 회전했을 때 5방향 순회
				move(cur, i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N][2];//가로방향일 때와 세로방향일 때 분리해서 중복체크
		q = new LinkedList<>();
		int[] B = new int[6];
		int[] E = new int[6];
		int Bidx = 0;
		int Eidx = 0;
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				char c = s.charAt(j);
				if(c == 'B') {//출발지점 저장
					B[Bidx++] = i;
					B[Bidx++] = j;
					map[i][j] = 0;
				} else if(c == 'E') {//도착지점 저장
					E[Eidx++] = i;
					E[Eidx++] = j;
					map[i][j] = 0;
				} else {
					map[i][j] = c - '0';
				}
			}
		}
		
		Tree start = null;
		if(B[0] == B[2]) {//시작할 때 방향이 가로방향일 때
			start = new Tree(B[2], B[3], 0, false);
			visited[B[2]][B[3]][0] = true;
		} else {//세로방향일 때
			start = new Tree(B[2], B[3], 0, true);
			visited[B[2]][B[3]][1] = true;
		}
		if(E[0] == E[2]) {//도착지점 방향이 가로방향일 때
			target = new Tree(E[2], E[3], 0, false);
		} else {//세로방향일 때
			target = new Tree(E[2], E[3], 0, true);
		}
		q.add(start);
		
		bfs();
		
		System.out.println(min);
	}

}
