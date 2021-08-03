package baekjoon.boj1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        Main main = new Main();
        main.solve();
    }

    int N;
    List<int[]> meetings = new ArrayList<>();

    public void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split(" ");
            int[] meeting = new int[2];

            for (int j = 0; j < 2; j++) meeting[j] = Integer.parseInt(arr[j]);

            meetings.add(meeting);
        }
    }

    public void solve() throws IOException {
        getInput();
        meetings.sort((o1, o2) -> {
            if(o1[1] == o2[1]) return Integer.compare(o1[0],o2[0]);
            return Integer.compare(o1[1],o2[1]);
        });

        int scheduledMeetings = 1;
        int end = meetings.get(0)[1];

        for (int i = 1; i < meetings.size(); i++) {
            if(meetings.get(i)[0] >= end){
                scheduledMeetings++;
                end = meetings.get(i)[1];
            }
        }

        System.out.println(scheduledMeetings);
    }
}
