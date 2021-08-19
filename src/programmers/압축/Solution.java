package programmers.압축;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("TOBEORNOTTOBEORTOBEORNOT");
    }

    Map<String, Integer> map = new HashMap<>();
    List<Integer> answer = new ArrayList<>();

    public void init(){
        String[] alphabets = {
            "A","B","C","D","E","F","G","H",
            "I","J","K","L","M","N","O","P",
            "Q","R","S","T","U","V","W","X",
            "Y","Z"
        };

        for(int i = 0 ; i < alphabets.length; i++){
            map.put(alphabets[i], i + 1);
        }
    }

    public int[] solution(String msg) {
        init();
        while (msg.length() > 0){
            int lastIdxOfLongestString = lastIdxOfLongestString(msg);
            String stringAfterLongestSubstringRemoved = removeLongestString(msg, lastIdxOfLongestString);
            if(stringAfterLongestSubstringRemoved.length() >= 1){
                map.put(msg.substring(0,lastIdxOfLongestString + 1 + 1), IndexGenerator.generateIndex());
                // +1을 2번 해주어야 하는 이유
                // 1. 가장 긴 부분 문자열보다 한 칸 뒤에서 잘라야한다. 따라서 +1을 해주어야 하고
                // 2. substring(begin,end) 는 begin ~ end-1까지이기 때문에 + 1을 한번 더 해주어야 한다.
            }
            msg = stringAfterLongestSubstringRemoved;
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }


    public int lastIdxOfLongestString(String originalString){
        int offset;
        for(offset = 1 ; offset <= originalString.length(); offset++){
            String subStr = originalString.substring(0,offset);
            if(map.containsKey(subStr)) continue;
            break;
        }
        return offset-2;
    }

    public String removeLongestString(String originalString, int lastIdxOfString){
        String longestSubStr = originalString.substring(0, lastIdxOfString + 1);
        answer.add(map.get(longestSubStr));
        return originalString.substring(lastIdxOfString+1);
    }


    static class IndexGenerator{
        private static int index = 27;
        static int generateIndex(){
            return index++;
        }
    }
}