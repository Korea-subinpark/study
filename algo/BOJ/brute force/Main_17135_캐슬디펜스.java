import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스 {
	static int N, M, D, enemyCnt, ans;
	
	static class P {
		int x, y;
		boolean dead;
		public P (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void solve(int[] archerP, LinkedList<P> enemyP) {
		int enemy = enemyCnt;
		int time = 0;
		int kill = 0;
		
		while(enemy > 0) {
			int[] killD = {10000000, 10000000, 10000000};
			P[] killWho = new P[3];
			
			Iterator<P> it = enemyP.iterator();
			while(it.hasNext()) {//모든 적 병사에 대하여 순회
				P cur = it.next();
				if(cur.dead) continue;//이미 죽었을 경우 continue
				if(cur.x + time >= N) {//성에 닿은 경우 dead 처리 후 continue
					cur.dead = true;
					enemy--;
					continue;
				}
				for(int i = 0; i < 3; i++) {
					int dist = (N - (cur.x + time)) + Math.abs(cur.y - archerP[i]);//거리 계산
					if(dist > D) continue;//사정거리보다 멀 경우 continue
					if(killD[i] >= dist) {//사정거리 안에 있는 경우
						if(killWho[i] == null) {//현재 선택하고 있던 적 병사가 없는 경우
							killD[i] = dist;
							killWho[i] = cur;
						} else {//선택한 병사가 있었던 경우
							if(killD[i] > dist) {//거리가 더 짧은 경우 업데이트
								killD[i] = dist;
								killWho[i] = cur;
							} else if(killWho[i].y > cur.y) {//거리가 같지만 더 왼쪽에 있는 경우 업데이트
								killWho[i] = cur;
							}
						}
					} 
				}
			}
			
			if(killWho[0] != null && killWho[1] != null && killWho[2] != null) {//3명의 궁수 모두 타겟이 있는 경우
				killWho[0].dead = true;
				killWho[1].dead = true;
				killWho[2].dead = true;
				if(killWho[0] != killWho[1]) {//같은 적 병사를 타겟으로 하고 있는지 검사
					if(killWho[0] != killWho[2]) {
						if(killWho[1] != killWho[2]) {
							enemy -= 3;
							kill += 3;
						} else {
							enemy -= 2;
							kill += 2;
						}
					} else {
						enemy -= 2;
						kill += 2;
					}
				} else {
					if(killWho[1] != killWho[2]) {
						enemy -= 2;
						kill += 2;
					} else {
						enemy--;
						kill++;
					}
				}
				
			} else {//한 명의 궁수라도 타겟을 정하지 못한 경우
				if(killWho[0] != null) {
					killWho[0].dead = true;
					enemy--;
					kill++;
					if(killWho[1] != null && killWho[0] != killWho[1]) {
						killWho[1].dead = true;
						enemy--;
						kill++;
					}
					if(killWho[2] != null && killWho[0] != killWho[2]) {
						killWho[2].dead = true;
						enemy--;
						kill++;
					}
				} else {
					if(killWho[1] != null) {
						killWho[1].dead = true;
						enemy--;
						kill++;
						if(killWho[2] != null && killWho[1] != killWho[2]) {
							killWho[2].dead = true;
							enemy--;
							kill++;
						}
					} else {
						if(killWho[2] != null) {
							killWho[2].dead = true;
							enemy--;
							kill++;
						}
					}
				}
			}
			
			if(ans < kill)//최대 kill 수 업데이트
				ans = kill;
			time++;//시간 증가
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		ArrayList<P> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 1) {
					list.add(new P(i, j));
					enemyCnt++;
				}
			}
		}
		
		for(int i = 0; i < M - 2; i++) {//궁수 3명의 모든 위치를 순회
			for(int j = i + 1; j < M - 1; j++) {
				for(int k = j + 1; k < M; k++) {
					LinkedList<P> temp = new LinkedList<>();
					for(int m = 0; m < list.size(); m++)//적 병사 위치 복사
						temp.add(new P(list.get(m).x, list.get(m).y));
					
					int[] archerP = {i, j, k};//3명의 궁수 위치
					solve(archerP, temp);
				}
			}
		}
		System.out.println(ans);
	}

}
