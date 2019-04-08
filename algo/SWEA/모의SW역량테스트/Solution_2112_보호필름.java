import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_��ȣ�ʸ� {
	static int D, W, K, min;
	
	public static boolean[][] copy(boolean[][] f) {//2���� �迭 ����
		boolean[][] ret = new boolean[D][W];
		for(int i = 0; i < D; i++)
			System.arraycopy(f[i], 0, ret[i], 0, W);
		return ret;
	}
	
	public static boolean chk(boolean[][] f) {//���� �˻� �Լ�
		for(int i = 0; i < W; i++) {
			boolean temp = f[0][i];
			boolean success = false;
			int cnt = 1;
			for(int j = 1; j < D; j++) {
				if(cnt >= K) {//������ �����ϴ� ���
					success = true;
					break;
				}
				if(temp == f[j][i])
					cnt++;
				else {
					temp = f[j][i];
					cnt = 1;
				}
			}
			if(cnt >= K)
				success = true;
			if(!success)//������ �������� ���ϴ� ���
				return false;
		}
		return true;
	}
	
	public static void comb(int idx, boolean[][] f, int cnt) {
		if(min <= cnt)//��Ʈ��ŷ
			return;
		if(chk(f)) {//���� �˻縦 ������ ���
			if(min > cnt)
				min = cnt;
			return;
		}
		if(idx == D) return;//��� �࿡ ���Ͽ� �����غ� ���
		
		boolean[][] A = copy(f);
		for(int i = 0; i < W; i++)//A��ǰ ����
			A[idx][i] = false;
		comb(idx + 1, A, cnt + 1);
		
		boolean[][] B = copy(f);
		for(int i = 0; i < W; i++)//B��ǰ ����
			B[idx][i] = true;
		comb(idx + 1, B, cnt + 1);
		
		comb(idx + 1, f, cnt);//��ǰ�� �������� �ʾ��� ��
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			min = Integer.MAX_VALUE;
			
			boolean[][] film = new boolean[D][W];
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < W; j++) {
					int a = Integer.parseInt(st.nextToken());
					if(a == 0)
						film[i][j] = false;
					else
						film[i][j] = true;
				}
			}
			
			comb(0, film, 0);
			
			System.out.println("#" + tc + " " + min);
		}
	}

}
