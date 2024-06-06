#include <iostream>
#include <algorithm>
#include <string>
using namespace std;
void run()
{
	string str[5];
	for (int i = 0; i < 5; i++)
	{
		cin >> str[i];
	}
	for (int i = 0; i < 15; i++)
	{
		for (int j = 0; j < 5; j++)
		{
			if (str[j].size() > i)
				cout << str[j][i];
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