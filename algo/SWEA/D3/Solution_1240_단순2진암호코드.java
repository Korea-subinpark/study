import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1240_단순2진암호코드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			String temp = "";
			String codeRow = "";
			for(int i = 0; i < N; i++) {
				temp = br.readLine();
				if(temp.contains("1"))
					codeRow = temp;
			}
			
			for(int i = codeRow.length() - 1; i >= 0; i--) {
				if(codeRow.charAt(i) == '1') {
					temp = codeRow.substring(i - 55, i + 1);
					break;
				}
			}
			
			int[] codeArr = new int[8]; 
			int idx = 0;
			for(int i = 0; i < 8; i++) {
				String sub = temp.substring(idx, idx + 7);
				switch(sub) {
				case "0001101":
					codeArr[i] = 0;
					break;
				case "0011001":
					codeArr[i] = 1;
					break;
				case "0010011":
					codeArr[i] = 2;
					break;
				case "0111101":
					codeArr[i] = 3;
					break;
				case "0100011":
					codeArr[i] = 4;
					break;
				case "0110001":
					codeArr[i] = 5;
					break;
				case "0101111":
					codeArr[i] = 6;
					break;
				case "0111011":
					codeArr[i] = 7;
					break;
				case "0110111":
					codeArr[i] = 8;
					break;
				case "0001011":
					codeArr[i] = 9;
					break;
				}
				idx += 7;
			}
			
			int sum1 = 0;
			int sum2 = 0;
			int total = 0;
			int ans = 0;
			for(int i = 0; i < 8; i++) {
				if(i % 2 == 0) {
					sum1 += codeArr[i];
				} else {
					sum2 += codeArr[i];
				}
				total += codeArr[i];
			}
			if((sum1 * 3 + sum2) % 10 == 0)
				ans = total;
			
			System.out.println("#" + tc + " " + ans);
		}
		
		
	}

}
