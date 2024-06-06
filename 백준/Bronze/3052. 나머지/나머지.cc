#include <stdio.h>
int main()
{
	int arr[10];
	int compare[42] = { 0 };
	int cnt = 0;
	for (int i = 0; i < 10; i++) scanf("%d", &arr[i]);
	for (int i = 0; i < 10; i++) arr[i] = arr[i] % 42;


	for(int j=0; j<10; j++){
		for (int i = 0; i < 42; i++) {
			if (arr[j] == i)
				compare[i] = 1;
		}
	}
	for (int i = 0; i < 42; i++) if (compare[i] == 1) cnt++;


	printf("%d", cnt);
	return 0;
}
