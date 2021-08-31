package programmers.방금그곡;

import java.util.ArrayList;
import java.util.List;

class Solution {

  class Music {

    String name;
    int duration;

    public Music(String name, int duration) {
      this.name = name;
      this.duration = duration;
    }
  }

  public String solution(String m, String[] musicinfos) {
    final List<String> mList = parseMelody(m);
    List<Music> matchedMusic = new ArrayList<>();

    for (String musicinfo : musicinfos) {
      String[] infoTokens = musicinfo.split(",");
      String name = infoTokens[2];
      int duration = getPlayTimeFrom(infoTokens[0], infoTokens[1]);
      String melody = infoTokens[3];
      List<String> melodies = parseMelody(melody);
      if (isMatch(duration, mList, melodies)) {
        matchedMusic.add(new Music(name, duration));
      }
    }

    if (matchedMusic.size() == 0) {
      return "(None)";
    }
    matchedMusic.sort((o1, o2) -> Integer.compare(o2.duration, o1.duration));
    return matchedMusic.get(0).name;
  }

  public int getPlayTimeFrom(String startTime, String endTime) {
    //00:00의 edge 케이스 고려하지 않음

    String[] startTimeTokens = startTime.split(":");
    int startTimeHour = Integer.parseInt(startTimeTokens[0]);
    int startTimeMinute = Integer.parseInt(startTimeTokens[1]);

    int start = (startTimeHour * 60 + startTimeMinute);

    int end = 60 * 24;

    String[] endTimeTokens = endTime.split(":");
    int endTimeHour = Integer.parseInt(endTimeTokens[0]);
    int endTimeMinute = Integer.parseInt(endTimeTokens[1]);
    end = (endTimeHour * 60 + endTimeMinute);

    return end - start;
  }

  public List<String> parseMelody(String melodyToParse) {
    List<String> melodies = new ArrayList<>();
    int ptr = 0;
    while (ptr < melodyToParse.length()) {

      int cur = ptr;
      int next = ptr + 1;

      if (next < melodyToParse.length() && melodyToParse.charAt(next) == '#') {
        melodies.add(melodyToParse.substring(cur, cur + 2));
        ptr += 2;
        continue;
      }

      melodies.add(melodyToParse.substring(cur, next));
      ptr++;
    }
    return melodies;
  }

  public boolean isMatch(int playTime, List<String> m, List<String> melody) {
    for (int t = 0; t <= playTime - m.size(); t++) {
      int offset;
      for (offset = 0; offset < m.size(); offset++) {
        int curMelodyIdx = ((t + offset) % melody.size());
        if (!m.get(offset).equals(melody.get(curMelodyIdx))) {
          break;
        }
      }
      if (offset == m.size()) {
        return true;
      }
    }
    return false;
  }
}

