#include<iostream>
#include<algorithm>
using namespace std;
int heap[200001];
int heap_size = 0;
void view() {
	int n = 1;
	int k = 1;
	for (int i = 1; i <= heap_size; i++) {
		cout << heap[i] << " ";
		if (n++ % k == 0) {
			cout << endl;
			n = 1;
			k *= 2;
		}
	}


}
void pop() {
	if (heap_size == 0) {
		cout << 0 << "\n";
		return;
	}

	cout << heap[1]<<'\n';
	heap[1] = heap[heap_size];
	heap_size--;

	int idx = 1;
	int child_idx = 2;


	while (child_idx <= heap_size) {
		if (child_idx + 1 <= heap_size && heap[child_idx] > heap[child_idx + 1]) {
			child_idx++;
		}

		if (heap[idx] > heap[child_idx]) {
			int tmp = heap[idx];
			heap[idx] = heap[child_idx];
			heap[child_idx] = tmp;
			idx = child_idx;
			child_idx *= 2;
		}
		else {
			break;
		}
	}

}
void push(int x) {
	heap[++heap_size] = x;
	int idx = heap_size;
	while (idx > 1) {
		if (heap[idx] < heap[idx / 2]) {
			int tmp = heap[idx];
			heap[idx] = heap[idx / 2];
			heap[idx / 2] = tmp;
		}
		idx /= 2;
	}
}

void run() {
	int n; cin >> n;
	for (int i = 0; i < n; i++) {
		int x; cin >> x;
		if (x > 0) {
			push(x);
		}
		else {
			pop();
		}
	}
}

int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	run();
	return 0;
}

