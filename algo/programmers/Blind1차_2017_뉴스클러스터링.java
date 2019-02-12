import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

class Solution {
    
    public boolean isAlphabet(char c) {//알파벳 체크
        if((c >= 'A' && c <= 'Z') || (c >='a' && c <='z'))
            return true;
        return false;
    }
    
  public int solution(String str1, String str2) {
      str1 = str1.toUpperCase();
      str2 = str2.toUpperCase();
      int answer = 0;
      double similar = 1;
      ArrayList<String> strTok1 = new ArrayList<>();
      ArrayList<String> strTok2 = new ArrayList<>();
      
      int size1 = str1.length();
      int size2 = str2.length();
      
      for(int i = 0; i < size1 - 1; i++) {//str1 다중집합
          char c1 = str1.charAt(i);
          char c2 = str1.charAt(i + 1);
          if(isAlphabet(c1) && isAlphabet(c2)) {
              strTok1.add(c1 + "" + c2 + "");
          }
      }
      
      for(int i = 0; i < size2 - 1; i++) {//str2 다중집합
          char c1 = str2.charAt(i);
          char c2 = str2.charAt(i + 1);
          if(isAlphabet(c1) && isAlphabet(c2)) {
              strTok2.add(c1 + "" + c2 + "");
          }
      }
      
      size1 = strTok1.size();
      size2 = strTok2.size();
      
      HashSet<String> s = new HashSet<>();
      for(int i = 0; i < size1; i++)//set에 str1 다중집합 add
          s.add(strTok1.get(i));
      
      for(int i = 0; i < size2; i++)//set에 str2 다중집합 add
          s.add(strTok2.get(i));
      
      Iterator<String> it = s.iterator();
      if(!it.hasNext())//공집합인 경우
          return 65536;
      int inter = 0;
      int union = 0;
      while(it.hasNext()) {
          int cnt1 = 0;
          int cnt2 = 0;
          String str = it.next();
          for(int i = 0; i < size1; i++)
              if(str.equals(strTok1.get(i)))
                  cnt1++;
          for(int i = 0; i < size2; i++)
              if(str.equals(strTok2.get(i)))
                  cnt2++;
          if(cnt1 > cnt2) {//set에 속한 원소를 더 많이 가진 쪽의 개수를 합집합, 적은 쪽의 개수를 교집합에 더한다
              union += cnt1;
              inter += cnt2;
          } else if(cnt2 > cnt1) {
              union += cnt2;
              inter += cnt1;
          } else {//같을 경우
              union += cnt1;
              inter += cnt1;
          }
      }
      double d = (double)inter / (double)union;
      answer = (int)(d * 65536);
      return answer;
  }
}
