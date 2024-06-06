#include<stdio.h>
int main() {

	int X;
	int x = 1;
	int row = 1, col = 1;
	int where = 0; 
	//1:좌측에서, 2:대각선(우상), 3:대각선(좌하), 4:위쪽에서
	scanf("%d", &X);
	 
	while (x!=X) {
		if (row == 1&&where!=1) {
			x++;
			col++;
			where = 1;
			 
		}
		else if (row == 1 && where == 1) {
			x++;
			row++;
			col--;
			where = 2;
			 
		}
		else if (col == 1 && where != 4) {
			x++;
			row++;
			where = 4;
			 
		}
		else if (col == 1 && where == 4) {
			x++;
			row--;
			col++;
			where = 3;
			 
		}
		else if (row != 1 && col != 1 && where==2) {
			x++;
			row++;
			col--;
			 
		}
		else if (row != 1 && col != 1 && where == 3) {
			x++;
			row--;
			col++;
			 
		}
	}

	printf("%d/%d", row, col);


	return 0;
}