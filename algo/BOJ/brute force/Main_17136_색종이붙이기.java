import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17136_�����̺��̱� {
	static int[][] board;
	static int[] cntBySize;
	static int ans = Integer.MAX_VALUE;
	
	public static void boardCopy(int[][] src, int[][] dest) {
		for(int i = 0; i < 10; i++)
			System.arraycopy(src[i], 0, dest[i], 0, 10);
	}
	
	public static void dfs(int cnt, int x, int y, int size, int[][] boardCur, int remain) {
		if(ans < cnt)
			return;
		
		for(int i = x; i < x + size; i++) {//�����̸� ���̴� �۾�
			for(int j = y; j < y + size; j++) {
				if(i >= 10 || j >= 10 || boardCur[i][j] == 0)
					return;
				boardCur[i][j] = 0;
				remain--;
			}
		}
		
		if(remain == 0) {//��� ������ �����̸� �ٿ��� ��
			if(ans > cnt)
				ans = cnt;
			return;
		}
		
		here:
		for(int i = 0; i < 10; i++) {//�Ⱥ��� ������ �ִ��� ã��
			for(int j = 0; j < 10; j++) {
				if(boardCur[i][j] == 1) {
					for(int k = 5; k >= 1; k--) {//5��������� 1��������� �ٿ�����
						if(cntBySize[k] == 5) continue;//���� ����� 5�� ����ߴٸ� continue
						int[][] temp = new int[10][10];
						boardCopy(boardCur, temp);
						cntBySize[k]++;
						dfs(cnt + 1, i, j, k, temp, remain);
						cntBySize[k]--;
					}
					break here;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new int[10][10];
		cntBySize = new int[6];
		int remain = 0;
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 10; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1)
					remain++;
			}
		}
		
		int[][] temp = new int[10][10];
		boardCopy(board, temp);
		dfs(0, 0, 0, 0, temp, remain);
		
		if(ans == Integer.MAX_VALUE)//�Ұ����� ���
			System.out.println(-1);
		else
			System.out.println(ans);
	}

}
