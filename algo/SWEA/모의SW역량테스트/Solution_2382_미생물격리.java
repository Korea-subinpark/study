import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_2382_�̻����ݸ� {
	static int N, M, K;
	static ArrayList<Obj> list;
	static boolean[] visited;
	
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	static boolean isBorder(int x, int y) {//���� �׵θ� �κп� ����ִ��� Ȯ��
		return (x == 0 || x == N - 1 || y == 0 || y == N - 1);
	}
	
	public static class Obj implements Comparable<Obj> {
		int r, c;//��ǥ
		int num, dir;//�̻��� ���� �̵� ����
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
				for(int j = 0; j < len; j++) {//�̻��� �̵�
					Obj temp = list.get(j);
					temp.r += dx[temp.dir];
					temp.c += dy[temp.dir];
				}
				remain = new ArrayList<>();//������ �� ���� �̻���
				for(int j = 0; j < len; j++) {//��ǰ�� ��Ҵ����� �ٸ� �̻����� �������� Ȯ��
					if(!visited[j]) {//���� Ȯ������ ���� �̻����� ���
						visited[j] = true;
						Obj temp = list.get(j);
						if(isBorder(temp.r, temp.c)) {
							temp.num /= 2;//�̻��� �� �ݰ�
							if(temp.dir == 2)//�̵����� �ݴ��
								temp.dir = 1;
							else if(temp.dir == 4)
								temp.dir = 3;
							else
								temp.dir += 1;
							remain.add(temp);
						} else {//���� ��ġ�� �ִ� �̻��� Ȯ��
							ArrayList<Obj> sum = new ArrayList<>();
							sum.add(temp);
							for(int k = 0; k < len; k++) {
								if(!visited[k] && list.get(k).equals(temp)) {//���� ��ġ�� �̻����� �ִ� ���
									visited[k] = true;
									sum.add(list.get(k));
								}
							}
							if(sum.size() != 1) {
								Collections.sort(sum);
								Obj biggest = sum.get(0);//���� ���� �̻��� ���� ���� ����
								for(int k = 1; k < sum.size(); k++)
									biggest.num += sum.get(k).num;
								remain.add(biggest);
							} else
								remain.add(temp);
						}
					}
				}
				
				list.clear();
				list = remain;//�̻��� ���� ������Ʈ
			}
			
			int ans = 0;
			for(int i = 0; i < list.size(); i++)
				ans += list.get(i).num;
			System.out.println("#" + tc + " " + ans);
		}
	}

}
