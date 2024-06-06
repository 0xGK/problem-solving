#include<stdio.h>
int main() {
	int T, x1, y1, r1, x2, y2, r2;
	int distance_square;
	scanf("%d", &T);

	for (int i = 0; i < T; i++) {
		scanf("%d %d %d %d %d %d", &x1, &y1, &r1, &x2, &y2, &r2);
		distance_square = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);

		if (distance_square == 0 && r1 == r2) printf("%d\n", -1);
		else if ((r1 + r2) * (r1 + r2) == distance_square) printf("%d\n", 1);
		else if ((r1 - r2) * (r1 - r2) == distance_square) printf("%d\n", 1);
		else if ((r1 - r2) * (r1 - r2) < distance_square && (r1 + r2) * (r1 + r2) > distance_square) printf("%d\n", 2);
		else printf("%d\n", 0);


	}
	

	return 0;
}
