#include<stdio.h>
#include<string.h>
int main() {

	int n, i = 0;
	scanf("%d", &n);
	while (1) {
		if (n <= 1 + 3 * i * (i + 1)) {
			printf("%d", i+1);
			break;
		}
		i++;

	}




	return 0;
}