import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_5672_올해의조련사 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			char[] c = new char[N];
			
			for(int i = 0; i < N; i++)
				c[i] = br.readLine().charAt(0);
			
			char[] result = new char[N];//출력할 문자를 저장
			
			int s = -1;// 앞쪽에서 글자를 취할 수 있도록 index
			int e = N;// 뒤쪽에서부터 가리키는 index
			
			for (int i = 0; i < N; i++) {
				if(c[s + 1] < c [e - 1]) {
					result[i] = c[++s];
				} else if(c[s + 1] > c[e - 1]) {
					result[i] = c[--e];
				} else {//같은 경우
					int j;
					for (j = 1; s + j < e - j && c[s + j] == c[e - j] ; j++);
					if(c[s + j] < c[e - j])
						result[i] = c[++s];
					else if(c[s + j] > c[e - j])
						result[i] = c[--e];
					else//아무거나 넣는다
						result[i] = c[++s];
				}
			}
			
			System.out.println("#" + tc + " " + new String(result));
			
		}
		
	}

}
