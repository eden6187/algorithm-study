package programmers.파일명_정렬;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] case1 = {"F-5 Freedom Fighter3dff", "B-50 Superfortress2fdf", "A-10 Thunderbolt II1sdfd", "F-14 Tomcat33sdfd"};
    String[] case2 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
    String[] ans = solution.solution(case1);
    System.out.println(Arrays.toString(ans));
  }

  public String[] solution(String[] files) {
    String[] answer = new String[files.length];
    List<File> fileList = new ArrayList<>();

    for (String file : files) {
      fileList.add(generateFile(file));
    }

    fileList.sort((o1,o2) -> {
      int headCompareResult = o1.getHead().compareTo(o2.head);
      int numberCompareResult = Integer.compare(o1.getNumber(),o2.getNumber());
      if(headCompareResult != 0) return headCompareResult;
      else return numberCompareResult;
    });

    return fileList.stream().map(File::getFileName).toArray(String[]::new);
  }

  File generateFile(String fileName){
    char[] fileNameCharacters = fileName.toCharArray();
    boolean finishHead = false;
    boolean finishNumber = false;

    StringBuilder head = new StringBuilder("");
    StringBuilder number = new StringBuilder("");
    StringBuilder tail = new StringBuilder("");

    for (char fileNameCharacter : fileNameCharacters) {
      if(Character.isDigit(fileNameCharacter) && !finishNumber){
        number.append(fileNameCharacter);
        finishHead = true;
        continue;
      }

      if(!finishHead){
        head.append(fileNameCharacter);
      }else{
        tail.append(fileNameCharacter);
        finishNumber = true;
      }
    }

    return new File(fileName, head.toString().toLowerCase(), Integer.parseInt(number.toString()), tail.toString());
  }

  class File{ // stableSort를 사용해야 한다.
    String fileName;
    String head; // 소문자 or 대문자로 모두 바꾸고
    int number; // parseInt하고
    String tail;

    public File(String fileName, String head, int number, String tail) {
      this.fileName = fileName;
      this.head = head;
      this.number = number;
      this.tail = tail;
    }

    public String getFileName() {
      return fileName;
    }

    public String getHead() {
      return head;
    }

    public int getNumber() {
      return number;
    }

    public String getTail() {
      return tail;
    }
  }

}