#include<iostream>
#include<queue>
#include<string.h>
using namespace std;
typedef struct{
	int x;
	int y;
	int time;
}Q;

queue<Q> q;

int R, C;
int goal_x, goal_y;
int pool[55][55];
void view() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (pool[i][j] == 0) cout << "S ";
			else if (pool[i][j] < 0) cout << ". ";
			else if (pool[i][j] == 999) cout << "X ";
			else if (pool[i][j] == 9999) cout << "D ";
			else if (pool[i][j] > 0) cout << "* ";
		}
		cout << '\n';
	}
	cout << '\n';
}

void fill_pool() {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (pool[i][j] == 1) {
				if (i > 0) {
					if (pool[i - 1][j] < 0) pool[i - 1][j] = -2;
				}
				if (j < C-1) {
					if (pool[i][j + 1] < 0) pool[i][j + 1] = -2;
				}
				if (i < R-1) {
					if (pool[i + 1][j] < 0) pool[i + 1][j] = -2;
				}
				if (j > 0) {
					if (pool[i][j - 1] < 0) pool[i][j - 1] = -2;
				}
				pool[i][j]++;
			}
		}
	}
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (pool[i][j] == -2) {
				pool[i][j] = 1;
			}
		}
	}
}


int bfs(int x, int y) {
	Q data;
	data.x = x;
	data.y = y;
	data.time = 0;
	q.push(data);
	pool[x][y] = 0;


	int water_clock = 0;

	while (!q.empty()) {
		Q cur = q.front();
		q.pop();
		//view();
		if ((cur.x+1== goal_x && cur.y == goal_y)
			|| (cur.x == goal_x && cur.y + 1 == goal_y)
			||(cur.x == goal_x && cur.y - 1 == goal_y)
			||(cur.x - 1 == goal_x && cur.y == goal_y)
			) {
			return cur.time + 1;
		}

		//pool fill
		if (q.empty()) {
			fill_pool();
			water_clock++;
		}
		if (water_clock==cur.time) {
			fill_pool();
			water_clock++;
		}
		//view();
		//move
		if (cur.x > 0) {
			if (pool[cur.x-1][cur.y] < 0) {
				Q nxt;
				nxt.x = cur.x - 1;
				nxt.y = cur.y;
				nxt.time = cur.time + 1;
				q.push(nxt);
				pool[cur.x-1][cur.y] = 0;
			}
		}
		if (cur.y > 0) {
			if (pool[cur.x][cur.y - 1] < 0) {
				Q nxt;
				nxt.x = cur.x;
				nxt.y = cur.y - 1;
				nxt.time = cur.time + 1;
				q.push(nxt);
				pool[cur.x][cur.y-1] = 0;
			}
		}
		if (cur.y < C-1) {
			if (pool[cur.x][cur.y + 1] < 0) {
				Q nxt;
				nxt.x = cur.x;
				nxt.y = cur.y + 1;
				nxt.time = cur.time + 1;
				q.push(nxt);
				pool[cur.x][cur.y+1] = 0;
			}
		}
		if (cur.x < R-1) {
			if (pool[cur.x + 1][cur.y] < 0) {
				Q nxt;
				nxt.x = cur.x + 1;
				nxt.y = cur.y;
				nxt.time = cur.time + 1;
				q.push(nxt);
				pool[cur.x+1][cur.y] = 0;
			}
		}
		pool[cur.x][cur.y] = 999;
	}
	return -1;
}

int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	
	cin >> R >> C;
	int start_x, start_y;
	//memset(pool, -1, sizeof(int) * 55 * 55);
	for (int i = 0; i < 55; i++) for (int j = 0; j < 55; j++) pool[i][j] = -1;
	
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			char ch; cin >> ch;
			if (ch == 'D') {
				goal_x = i; goal_y = j;
				pool[i][j] = 9999;
			}
			if (ch == 'S') {
				start_x = i; start_y= j;
			}
			if (ch == '*')
				pool[i][j] = 1;
			if (ch == 'X')
				pool[i][j] = 999;
		}
	}
	int ret = bfs(start_x, start_y);
	if (ret < 0) {
		cout << "KAKTUS";
	}
	else {
		cout << ret;
	}


	return 0;

}

