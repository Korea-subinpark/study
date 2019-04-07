import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_17135_ĳ�����潺 {
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
			while(it.hasNext()) {//��� �� ���翡 ���Ͽ� ��ȸ
				P cur = it.next();
				if(cur.dead) continue;//�̹� �׾��� ��� continue
				if(cur.x + time >= N) {//���� ���� ��� dead ó�� �� continue
					cur.dead = true;
					enemy--;
					continue;
				}
				for(int i = 0; i < 3; i++) {
					int dist = (N - (cur.x + time)) + Math.abs(cur.y - archerP[i]);//�Ÿ� ���
					if(dist > D) continue;//�����Ÿ����� �� ��� continue
					if(killD[i] >= dist) {//�����Ÿ� �ȿ� �ִ� ���
						if(killWho[i] == null) {//���� �����ϰ� �ִ� �� ���簡 ���� ���
							killD[i] = dist;
							killWho[i] = cur;
						} else {//������ ���簡 �־��� ���
							if(killD[i] > dist) {//�Ÿ��� �� ª�� ��� ������Ʈ
								killD[i] = dist;
								killWho[i] = cur;
							} else if(killWho[i].y > cur.y) {//�Ÿ��� ������ �� ���ʿ� �ִ� ��� ������Ʈ
								killWho[i] = cur;
							}
						}
					} 
				}
			}
			
			if(killWho[0] != null && killWho[1] != null && killWho[2] != null) {//3���� �ü� ��� Ÿ���� �ִ� ���
				killWho[0].dead = true;
				killWho[1].dead = true;
				killWho[2].dead = true;
				if(killWho[0] != killWho[1]) {//���� �� ���縦 Ÿ������ �ϰ� �ִ��� �˻�
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
				
			} else {//�� ���� �ü��� Ÿ���� ������ ���� ���
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
			
			if(ans < kill)//�ִ� kill �� ������Ʈ
				ans = kill;
			time++;//�ð� ����
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
		
		for(int i = 0; i < M - 2; i++) {//�ü� 3���� ��� ��ġ�� ��ȸ
			for(int j = i + 1; j < M - 1; j++) {
				for(int k = j + 1; k < M; k++) {
					LinkedList<P> temp = new LinkedList<>();
					for(int m = 0; m < list.size(); m++)//�� ���� ��ġ ����
						temp.add(new P(list.get(m).x, list.get(m).y));
					
					int[] archerP = {i, j, k};//3���� �ü� ��ġ
					solve(archerP, temp);
				}
			}
		}
		System.out.println(ans);
	}

}
