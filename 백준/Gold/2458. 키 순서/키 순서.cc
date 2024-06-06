#include<algorithm>
#include<iostream>
using namespace std;
#define INF 987654321
int dist[501][501];
int N, M, a, b, c;

// static 함수로 빼야 속도가 빠르다.
// main 함수 내에 구현하지 말 것!
void floyd() {
	// 꼭 k 를 가장 바깥쪽 for 루프에서 돌려야 정답이 나온다. 안쪽에 있으면 오답
	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// i -> j 바로 가는 거리보다
				// i -> k 로 가고 k -> j 로 가는 거리가 더 짧다면 갱신한다.
				dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
			}
		}
	}
}

int main() {
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (i == j) {
				dist[i][j] = 0; // 동일한 정점까지 거리는 0으로 설정
			}
			else {
				dist[i][j] = INF; // 무한대 값으로 초기화
			}

		}
	}

	for (int i = 0; i < M; i++) {
		cin >> a >> b;
		dist[a][b] = 1;
	}

	floyd();
	int cnt = N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (dist[i][j] == INF && dist[j][i]==INF)
			{
				cnt--;
				break;
			}

		}
	}
	cout << cnt;
}