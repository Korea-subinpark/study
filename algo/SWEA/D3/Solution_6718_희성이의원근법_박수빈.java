import java.util.Scanner;

public class Solution_6718_희성이의원근법_박수빈 {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
            if(N > 999999){ System.out.println("#" + test_case + " " + 5); continue;}
  			else if(N > 99999){ System.out.println("#" + test_case + " " + 4); continue;}
            else if(N > 9999){ System.out.println("#" + test_case + " " + 3); continue;}
            else if(N > 999){ System.out.println("#" + test_case + " " + 2); continue;}
            else if(N > 99){ System.out.println("#" + test_case + " " + 1); continue;}
            else { System.out.println("#" + test_case + " " + 0); continue;}
		}
		sc.close();
	}
}
