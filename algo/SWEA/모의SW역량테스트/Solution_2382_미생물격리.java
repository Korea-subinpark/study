import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_2382_미생물격리 {
	static int N, M, K;
	static ArrayList<Obj> list;
	static boolean[] visited;
	
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	static boolean isBorder(int x, int y) {//현재 테두리 부분에 닿아있는지 확인
		return (x == 0 || x == N - 1 || y == 0 || y == N - 1);
	}
	
	public static class Obj implements Comparable<Obj> {
		int r, c;//좌표
		int num, dir;//미생물 수와 이동 방향
		public Obj(int r, int c, int num, int dir) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.dir = dir;
		}
		@Override
		public int compareTo(Obj o) {
			return o.num - num;
		}
		@Override
		public boolean equals(Object obj) {
			Obj o = (Obj)obj;
			if(r == o.r && c == o.c)
				return true;
			return false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				list.add(new Obj(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			ArrayList<Obj> remain = null;
			for(int i = 0; i < M; i++) {
				int len = list.size();
				visited = new boolean[len];
				for(int j = 0; j < len; j++) {//미생물 이동
					Obj temp = list.get(j);
					temp.r += dx[temp.dir];
					temp.c += dy[temp.dir];
				}
				remain = new ArrayList<>();//합쳐진 후 남는 미생물
				for(int j = 0; j < len; j++) {//약품에 닿았는지와 다른 미생물과 만났는지 확인
					if(!visited[j]) {//아직 확인하지 않은 미생물인 경우
						visited[j] = true;
						Obj temp = list.get(j);
						if(isBorder(temp.r, temp.c)) {
							temp.num /= 2;//미생물 수 반감
							if(temp.dir == 2)//이동방향 반대로
								temp.dir = 1;
							else if(temp.dir == 4)
								temp.dir = 3;
							else
								temp.dir += 1;
							remain.add(temp);
						} else {//같은 위치에 있는 미생물 확인
							ArrayList<Obj> sum = new ArrayList<>();
							sum.add(temp);
							for(int k = 0; k < len; k++) {
								if(!visited[k] && list.get(k).equals(temp)) {//같은 위치에 미생물이 있는 경우
									visited[k] = true;
									sum.add(list.get(k));
								}
							}
							if(sum.size() != 1) {
								Collections.sort(sum);
								Obj biggest = sum.get(0);//가장 많은 미생물 수를 가진 군집
								for(int k = 1; k < sum.size(); k++)
									biggest.num += sum.get(k).num;
								remain.add(biggest);
							} else
								remain.add(temp);
						}
					}
				}
				
				list.clear();
				list = remain;//미생물 정보 업데이트
			}
			
			int ans = 0;
			for(int i = 0; i < list.size(); i++)
				ans += list.get(i).num;
			System.out.println("#" + tc + " " + ans);
		}
	}

}
