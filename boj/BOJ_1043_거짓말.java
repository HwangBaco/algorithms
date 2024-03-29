package 알고리즘연습.boj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @intuition 브루트포스인줄 알았는데, union-find인 것 같다.
 * 진실을 아는 사람들이 있는 파티에 참여하는 사람들도 진실을 알게 되는데, 이게 1다리만 계산하면 되는 게 아니라 skewed tree 관계가 될 경우에는 반복수를 감안할 수 없기 떄문에 브루트포스로는 반복수의 기준이 명확하지 않다.
 * 즉, 관계성을 계속 저장해둬야 하는 문제였던 거다.
 * 이는 사람간의 그래프를 형성하는 문제라고 볼 수 있으므로, union find로 풀어낼 수 있다.
 *
 * @algorithm union-find
 * @time O(logN) : union-find -> 132ms
 * @memory O(N*M) : parent 배열 * M 크기의 list
 */
public class BOJ_1043_거짓말 {
    private static int[] parent;
    private static int N, M;
    private static List<Integer>[] parties;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        parties = new ArrayList[M];

        // 0을 root로 두는 건 진실은 아는 것.
        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        if (cnt > 0) {
            for (int i = 0; i < cnt; i++) {
                int v = Integer.parseInt(st.nextToken());
                union(0, v);
            }
        }

        for (int i = 0; i < M; i++) {
            parties[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            int u = 0;
            for (int j = 0; j < cnt; j++) {
                int v = Integer.parseInt(st.nextToken());
                parties[i].add(v);
                if (j == 0) {
                    u = v;
                    continue;
                }
                union(u, v);
            }
        }
        int ans = M;
        int res = 0;
        top:
        for (List<Integer> party : parties) {
            for (Integer i : party) {
                if (find(i) == 0) {
                    res++;
                    continue top;
                }
            }
        }
        sb.append(ans - res);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void union(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);
        if (uRoot == vRoot) {
            return;
        }
        if (uRoot < vRoot) {
            parent[vRoot] = uRoot;
        } else {
            parent[uRoot] = vRoot;
        }
    }

    private static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }
}
