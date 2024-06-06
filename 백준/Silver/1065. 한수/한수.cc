#include<stdio.h>
int main() {
	int n;
	int cnt = 0;
	scanf("%d", &n);
	for (int i = 1; i < n + 1; i++) {
		if (i < 100) cnt++;
		else if (i < 1000) {
			if (i / 100 - ((i % 100) / 10) == ((i % 100) / 10) - i % 10) cnt++;
		}
	}
	printf("%d", cnt);

	return 0;
}