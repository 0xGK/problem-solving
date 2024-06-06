#include<iostream>
#include<vector>
#include<queue>
#define MAX_N 20001
#define MAX_VALUE (1<<30)
#define endl '\n'
using namespace std;
typedef struct {
	int dest;
	int cost;
}Node;
vector<Node> nodes[MAX_N];
int cost[MAX_N];
bool visited[MAX_N];
int V, E, S;
struct compare {
	bool operator()(const Node &a, const Node &b) {
		return a.cost > b.cost;
	}
};

void dijkstra(int start) {
    priority_queue<Node, vector<Node>, compare> pq;

    // cost 배열 (무한대 값으로) 초기화
    for (int i = 1; i <= V; i++) {
        cost[i] = MAX_VALUE;
		visited[i] = false;
    }

    cost[start] = 0;
    pq.push({ start, 0 });

    while (!pq.empty()) {
        Node cur = pq.top();
        pq.pop();
		int src = cur.dest;
        // 수행속도 향상 : Type.1, Type.2 중 택 1
        // Type.1 start : 더 큰 가중치로 도착한 경우 패스
        //if (cost[cur.dest] < cur.cost)
        //    continue;
        // Type.1 end

        // Type.2 start : 방문했던 정점이면 패스
        // *주의 "위대한 항로" 문제처럼
        // 간선의 가중치가 바뀔 수 있는 경우 오답
        if (visited[cur.dest])
            continue;

        visited[cur.dest] = true;
        // Type.2 end

        // 연결된 간선 탐색
        for (Node next : nodes[src]) {
			int dest = next.dest;
			
            // cost 가 더 작을 때만 갱신하고 pq큐에 넣음
            if (cost[dest] > next.cost + cost[src]) {
                cost[dest] = next.cost + cost[src];
				pq.push({ dest, cost[dest] });
            }
        }
    }
}

// dest 값을 주지 않으면 목적지를 특정하지 않음.
// 목적지가 없으면 끝까지 수행

int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	cin >> V >> E >> S;

    for (int i = 0; i < E; i++) {
        int a, b, c;
		cin >> a >> b >> c;
		nodes[a].push_back({ b,c });
    }

    dijkstra(S);

	for (int i = 1; i <= V; i++) {
		if(cost[i]==MAX_VALUE)
			cout << "INF" << endl;
		else {
			cout << cost[i] << endl;
		}
	}

}