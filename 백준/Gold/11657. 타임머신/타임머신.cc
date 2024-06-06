#include<iostream>
#include<vector>
#include<queue>
#define MAX_N 1001
#define endl '\n'

const long long INF = 30'000'000'000;
using namespace std;

typedef struct Edge {
	int src;
	int dest;
	int cost;
}Edge;

vector<Edge> edges;
long long dist[MAX_N];
bool visited[MAX_N];
int V, E, X;

void bellman(int start) {
	for (int i = 1; i <= V; i++) 
		dist[i] = INF;
	dist[start] = 0;

	for (int i = 1; i <= V - 1; i++) {
		for (auto edge : edges) {
			if (dist[edge.src] == INF) continue;
			if (dist[edge.dest] > dist[edge.src] + edge.cost) 
				dist[edge.dest] = dist[edge.src] + edge.cost;
		}
	}
	for (auto edge : edges) {
		if (dist[edge.src] == INF) continue;
		if (dist[edge.dest] > dist[edge.src] + edge.cost) {
			cout<<-1;
			return;
		}
	}

	for (int i = 2; i <= V; i++) {
		if (dist[i] == INF)
			cout << -1 << endl;
		else
			cout << dist[i] << endl;
	}
}


int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	cin >> V >> E;
	for (int i = 0; i < E; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		edges.push_back({ a,b,c });
	}
	bellman(1);

}