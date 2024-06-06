#define INF 100 * 100000 + 1
#include<cstdio>
#include<string.h>
#include<algorithm>
using namespace std;
int dist[101][101];
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
	scanf("%d %d", &N, &M);

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
		// 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
		// 동일한 노선이 여러개면 비용이 작은 값만 들고 있으면 된다.
		scanf("%d %d %d", &a, &b, &c);
		dist[a][b] = min(dist[a][b], c);
	}

	floyd();

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			// 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
			if (dist[i][j] == INF)
				dist[i][j] = 0;

			printf("%d ", dist[i][j]);
		}
		printf("\n");
	}
}