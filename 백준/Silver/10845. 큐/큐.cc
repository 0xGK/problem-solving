#include<iostream>	
#include<string>
using namespace std;

void run() {
	int n; cin >> n;
	int q[10001];
	int front = 0;
	int back = 0;

	while (n--) {
		string cmd; cin >> cmd;
		if (cmd == "push") {
			int k; cin >> k;
			q[back++] = k;
		}
		else if (cmd == "empty") {
			if (front==back) cout << 1 << endl;
			else cout << 0 << endl;
		}
		else if (cmd == "size") {
			cout << back-front<<endl;
		}
		else if (cmd == "front") {
			if (back-front==0) cout << -1<< endl;
			else cout << q[front] << endl;
		}
		else if (cmd == "pop") {
			if (back - front == 0) cout << -1 << endl;
			else cout << q[front++] << endl;
		}
		else if (cmd == "back") {
			if (back - front == 0) cout << -1 << endl;
			else cout << q[back-1] << endl;
		}
	}

}


int main() {
	run();

	return 0;
}
