#include <stdio.h>
int main()
{
	int n=0;
	int index;
	double max;
	double sum = 0;
	double average;
	scanf("%d", &n);
	
	int score[1000] = { 0 };
	for (int i = 0; i < n; i++) scanf("%d", &score[i]);

	max = score[0];
	index = 0;
	//max찾기
	for (int i = 1; i < n; i++) {
		if (max < score[i]) {
			max = score[i];
			index = i;
		}
	}
	//시그마 구하기
	for (int i = 0; i < n; i++) sum += score[i];
	average = sum*100/(n*max);
	printf("%.2lf", average);








	return 0;
}
