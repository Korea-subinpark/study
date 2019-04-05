import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1018_ü���Ǵٽ�ĥ�ϱ� {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i + 7 < N; i++) {//ü������ 8x8�� �ڸ���
			for(int j = 0; j + 7 < M; j++) {
				int cnt = 0;
				here:for(int r = i; r < i + 8; r++) {//W���� ���۵Ǵ� ü����
					for(int c = j; c < j + 8; c++) {
						if((r + c) % 2 == 0)
							if(map[r][c] == 'B')
								cnt++;
						else
							if(map[r][c] == 'W')
								cnt++;
						if(min < cnt)
							break here;
					}
				}
				if(min > cnt)
					min = cnt;
				cnt = 0;
				here2:for(int r = i; r < i + 8; r++) {//B���� ���۵Ǵ� ü����
					for(int c = j; c < j + 8; c++) {
						if((r + c) % 2 == 0)
							if(map[r][c] == 'W')
								cnt++;
						else
							if(map[r][c] == 'B')
								cnt++;
						if(min < cnt)
							break here2;
					}
				}
				if(min > cnt)
					min = cnt;
			}
		}
		System.out.println(min);
	}

}
