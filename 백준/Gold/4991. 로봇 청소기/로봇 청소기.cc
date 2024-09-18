#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;
const int MAX_DIST = 40000;

struct xy {int x, y;};
int w, h, len;
char board[20][20];
int dist[11][20][20];
xy dust[11], s;
queue<xy> Q;
xy dxy[] = {{1,0}, {-1,0}, {0,1}, {0,-1}};

void bfs(int idx) {
    for(int x=0; x<h; x++)
        for(int y=0; y<w; y++)
            dist[idx][x][y] = MAX_DIST;
    Q.push(dust[idx]);
    dist[idx][dust[idx].x][dust[idx].y] = 0;
    while(not Q.empty()) {
        const auto [x, y] = Q.front(); Q.pop();
        for(const auto [dx, dy]:dxy) {
            int nx = x + dx, ny = y + dy;
            if(nx<0 or nx>=h or ny<0 or ny>=w) continue;
            if(board[nx][ny] == 'x') continue;
            if(dist[idx][nx][ny] != MAX_DIST) continue;
            dist[idx][nx][ny] = dist[idx][x][y] + 1;
            Q.push({nx, ny});
        }
    }
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    while(true) {
        len = 0;
        cin >> w >> h;
        if(w == 0 and h == 0) break;
        for(int x=0; x<h; x++) {
            for(int y=0; y<w; y++) {
                cin >> board[x][y];
                if(board[x][y] == 'o') s = {x, y};
                else if(board[x][y] == '*') dust[len++] = {x, y};
            }
        }
        dust[len] = s;
        for(int idx=0; idx<=len; idx++) bfs(idx);
        
        int order[len+1];
        for(int i=0; i<=len; i++) order[i] = i;

        int ans = MAX_DIST;
        do {
            int d = 0;
            for(int from=len, to=0; to<len and d<ans; from=to++)
                d += dist[order[from]][dust[order[to]].x][dust[order[to]].y];
            
            ans = min(ans, d);
        } while(next_permutation(order, order+len));
        cout << (ans < MAX_DIST ? ans : -1) << '\n';
    }
}