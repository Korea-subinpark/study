import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3378_��Ÿ�ϸ����鿩���� {
	static String[] masterCode;
	static String[] myCode;
	static int[] cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			masterCode = new String[p];//������ �ڵ�
			myCode = new String[q];
			cnt = new int[q];//������ ����� �迭
			Arrays.fill(cnt, -2);
			
			for(int i = 0; i < p; i++)
				masterCode[i] = br.readLine();
			for(int i = 0; i < q; i++)
				myCode[i] = br.readLine();
		
			for(int R = 1; R <= 20; R++)//��� ��츦 üũ
				for(int C = 1; C <= 20; C++)
					for(int S = 1; S <= 20; S++)
						if(masterChk(R, C, S))
							solve(R, C, S);
			
			System.out.print("#" + tc);
			for(int i = 0; i < cnt.length; i++)
				System.out.print(" " + cnt[i]);
			System.out.println();
		}
	}
	
	public static boolean masterChk(int R, int C, int S) {
		int a = 0;
		int b = 0;
		int c = 0;
		
		for(int i = 0; i < masterCode.length; i++) {
			String s = masterCode[i];
			int j = 0;
			for(; j < s.length(); j++)//. ���� ī��Ʈ
				if(s.charAt(j) != '.')
					break;
			
			if(R * a + C * b + S * c != j) return false;
			
			for(; j < s.length(); j++) {
				if(s.charAt(j) == '(') a++;
				else if(s.charAt(j) == ')') a--;
				else if(s.charAt(j) == '{') b++;
				else if(s.charAt(j) == '}') b--;
				else if(s.charAt(j) == '[') c++;
				else if(s.charAt(j) == ']') c--;
			}
		}
		return true;
	}
	
	public static void solve(int R, int C, int S) {
		int a = 0;
		int b = 0;
		int c = 0;
		
		for(int i = 0; i < myCode.length; i++) {
			String s = myCode[i];
			
			int temp = R * a + C * b + S * c;
			if(cnt[i] == -2)//�ʱ� ������ ���
				cnt[i] = temp;
			else if(cnt[i] > 0 && cnt[i] != temp)//�ذ� �������� ���� ���
				cnt[i] = -1;
			
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == '(') a++;
				else if(s.charAt(j) == ')') a--;
				else if(s.charAt(j) == '{') b++;
				else if(s.charAt(j) == '}') b--;
				else if(s.charAt(j) == '[') c++;
				else if(s.charAt(j) == ']') c--;
			}
		}
	}
}

