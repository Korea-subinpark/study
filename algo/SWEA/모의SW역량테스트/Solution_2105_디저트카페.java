import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * ��ȸ�� �����ϴ� ������ r,c�� ���� ���� w�� h��� �ϸ�
 * (r,c), (r+w, r+c), (r+w+h, c+w-h), (r+h, c-h)�� ���������� �ϴ� �簢���� �����ȴ�
 * �� ���������� ������ ����� �ʴ��� Ȯ���� �ڿ�
 * �簢���� ��� ��ȸ�ϸ� ��ġ�� ��ȣ�� ������ Ȯ���Ѵ�
 * ��� ������ �����ϸ� ī���� ������ (w+h) * 2�� �ȴ�
 */
public class Solution_2105_����Ʈī�� {
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int maxCnt = -1;
			for(int r = 0; r < N - 2; r++) {
				for(int c = 1; c < N - 1; c++) {
					for(int w = 1; w < N; w++) {
						if(r + w >= N || c + w >= N) continue;
						for(int h = 1; h < N; h++) {
							if(c - h < 0 || r + h + w >= N) continue;
							int cnt = visit(r, c, w, h);
							if(maxCnt < cnt)
								maxCnt = cnt;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + maxCnt);
		}
	}
	
	public static int visit(int r, int c, int w, int h) {//������� �簢������ ī�� �� ī��Ʈ
		boolean[] number = new boolean[101];
		for(int i = 0; i <= w; i++) {
			if(number[map[r + i][c + i]]) return -1;//��ȣ�� ��ġ�� ���
			number[map[r + i][c + i]] = true;
			if(number[map[r + h + i][c - h + i]]) return -1;//��ȣ�� ��ġ�� ���
			number[map[r + h + i][c - h + i]] = true;
		}
		
		for(int i = 1; i < h; i++) {
			if(number[map[r + i][c - i]]) return -1;//��ȣ�� ��ġ�� ���
			number[map[r + i][c - i]] = true;
			if(number[map[r + w + i][c + w - i]]) return -1;//��ȣ�� ��ġ�� ���
			number[map[r + w + i][c + w - i]] = true;
		}
		
		return (w + h) << 1;
	}
	
}