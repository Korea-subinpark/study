import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3143_가장빠른문자열타이핑 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			char[] A = st.nextToken().toCharArray();
			char[] B = st.nextToken().toCharArray();
			int cnt = 0;
			
			int[] fail = new int[B.length];
			//실패 함수 구하기
			int j = 0;
			for(int i = 1; i < B.length; i++) {
				while(j > 0 && B[i] != B[j])
					j = fail[j - 1];
				if(B[i] == B[j])
					fail[i] = j + 1;
			}
			
			//KMP
			j = 0;
			for(int i = 0; i < A.length; i++) {
				while(j > 0 && A[i] != B[j])
					j = fail[j - 1];
				if(A[i] == B[j]) {
					if(j == B.length - 1) {
						cnt++;
						j = fail[j];
					}
					else
						j++;
				}
			}
			
			int ans = A.length - cnt * (B.length - 1);
			System.out.println("#" + tc + " " + ans);
		}
	}

}
