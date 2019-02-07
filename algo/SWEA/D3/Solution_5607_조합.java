import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5607_а╤гу {
	static int p = 1234567891;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			long ans = nCr(n, r, p);
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	public static long nCr(int n, int r, int p) {
		long ret = 1;
		
		if(r == 0 || n == 1)
			return 1;
		
		long[] fac = new long[n + 1];
		fac[1] = 1;
		for(int i = 2; i <= n; i++)
			fac[i] = fac[i - 1] * i % p;
		
		ret = fac[n] * power(fac[r] * fac[n - r] % p, p - 2, p);
		return ret % p;
	}
	
	public static long power(long x, int y, int p) {
		long ret = 1;
		while(y > 0) {
			if(y % 2 == 1)
				ret = ret * x % p;
			y = y >> 1;
			x = x * x % p;
		}
		return ret;
	}

}
