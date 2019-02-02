import java.util.Scanner;
/*
비트들을 문자열로 저장하고
0에서 1 또는 1에서 0으로 반전되는 순간을 카운트한다
만약 1로 시작했을 경우에는 1을 더 증가시킨다
*/
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int N = sc.nextInt();
         
        for(int n=1; n<=N; n++) {
            String num = sc.next();
            int answer = 0;
             
            for(int i=0; i<num.length() - 1; i++) {
                if(num.charAt(i) != num.charAt(i+1))
                    answer++;
            }
            if(num.charAt(0) == '1')
                answer++;
            System.out.println("#" + n + " " + answer);
             
        }
        sc.close();
    }
 
}
