    #include<iostream>
#include<vector>
#include<queue>
#include<string.h>
using namespace std;
int N, K;

queue<int> q;
int visited[200000];

int bfs() {
	q.push(N);
	memset(visited, -1, 200000 * sizeof(int));
	visited[N] = 0;
	while (!q.empty()) {
		int cur = q.front();
		q.pop();

		if (cur == K) {
			return visited[cur];
		}
		
		if (cur + 1 <= 100000 && visited[cur + 1]<0) {
			q.push(cur + 1);
			visited[cur + 1] = visited[cur] + 1;
		}
		if (cur * 2 <= 200000 && visited[cur * 2] < 0){
			q.push(cur *2);
			visited[cur *2] = visited[cur] + 1;
		}
		if (cur - 1 >= 0 && visited[cur -1] < 0) {
			q.push(cur - 1);
			visited[cur - 1] = visited[cur] + 1;
		}

	}
}


int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	cin >> N >> K;
	cout << bfs() << endl;

	return 0;

}
