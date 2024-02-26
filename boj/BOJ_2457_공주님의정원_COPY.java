package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2457_공주님의정원_COPY {
    // 그리디
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Project[] arr = new Project[n+1];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken()); // start month
            int sd = Integer.parseInt(st.nextToken()); // start day
            int em = Integer.parseInt(st.nextToken()); //
            int ed = Integer.parseInt(st.nextToken());
            arr[i] = new Project(sm,sd,em,ed);
        }
        arr[n] = new Project(13,32,13,32);

        Arrays.sort(arr);

        // 기준 시작일 init
        int month = 3;
        int day = 1;

        int cnt = 0;
        for (int i = 0; i <= n; i++) {
            int sm = arr[i].startM;
            int sd = arr[i].startD;
            int em = arr[i].endM;
            int ed = arr[i].endD;
            if (month > 11 || (month == 11 && day >= 30)) {
                break;
            }
//			System.out.println(i);
            if (month < sm || (month == sm && day < sd)) {
                if (i > 0) {
                    if (arr[i-1].startM < month || (arr[i-1].startM == month && arr[i-1].startD <= day)) {
                        month = arr[i-1].endM;
                        day = arr[i-1].endD - 1;
                        System.out.println(month + " " + day);
                        cnt++;
                        if (i != n && month < sm || (month == sm && day < sd)) {
                            month = arr[i].endM;
                            day = arr[i].endD - 1;
                            if (day == 0) {
                                month--;
                                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                                    day = 31;
                                } else if (month == 2) {
                                    day = 28;
                                } else {
                                    day = 30;
                                }
                            }
                            System.out.println(month + " " + day);
                            cnt++;
                        }
                    } else {
                        System.out.println(0);
                        return;
                    }

                } else {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(cnt);

    }
    private static class Project implements Comparable<Project>{
        int startM, startD, endM, endD;
        private Project(int sm, int sd, int em, int ed) {
            startM = sm;
            startD = sd;
            endM = em;
            endD = ed;
        }

        @Override
        public int compareTo(Project o) {
            if (this.endM == o.endM) {
                return this.endD - o.endD;
            }
            return this.endM - o.endM;
        }
    }
}
