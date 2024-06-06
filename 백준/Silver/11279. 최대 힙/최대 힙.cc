#include <iostream>
#include <algorithm>
#include <queue>
#include <string>
using namespace std;
void run()
{
	priority_queue<int> pq;
	int N;
	cin >> N;
	while (N-- > 0)
	{
		int x;
		cin >> x;
		if (x > 0)
			pq.push(x);
		else
		{
			if (pq.empty())
				cout << 0 << '\n';
			else
			{
				cout << pq.top() << '\n';
				pq.pop();
			}
		}
	}
}
int main()
{
	cin.tie(0);
	ios::sync_with_stdio(0);
	run();
	return 0;
}