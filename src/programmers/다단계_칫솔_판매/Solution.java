package programmers.다단계_칫솔_판매;

import java.util.HashMap;
import java.util.Map;

class Solution {

  public static void main(String[] args) {
    String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
    String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
    String[] seller = {"young", "john", "tod", "emily", "mary"};
    int[] amount = {12, 4, 2, 5, 10};
    Solution solution = new Solution();
    solution.solution(enroll, referral, seller, amount);
  }

  Map<String, Node> tree = new HashMap<>();
  public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
    int[] answer = new int[enroll.length];
    tree.put("-", new Node());

    for (int i = 0; i < enroll.length; i++) {
      String parent = referral[i];
      String childName = enroll[i];
      Node child = new Node();
      child.parent = tree.get(parent);
      tree.put(childName, child);
    }

    for (int i = 0; i < seller.length; i++) {
      Node node = tree.get(seller[i]);
      node.calcProfit(amount[i] * 100);
    }

    int idx = 0;
    for (String s : enroll) {
      answer[idx++] = tree.get(s).amount;
    }

    return answer;
  }

  class Node {
    Node parent;
    int amount;

    public void calcProfit(int profit){
      if(this.parent != null) {
        int profitToParent = profit / 10;
        this.amount += profit - profitToParent;
        this.parent.calcProfit(profitToParent);
      }
    }
  }
}