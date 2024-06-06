#include<stdio.h>
int main(){
    int square[100][100]={0,};
    int n;
    int x,y;
    int sum=0;
    scanf("%d",&n);
    for(int i=0;i<n;i++){
        scanf("%d %d",&x,&y);
        for(int j=0;j<10;j++){
            for(int k=0;k<10;k++){
                square[x+j][y+k]=1;
            }
        }

    }
    for(int i=0;i<100;i++){
        for(int j=0;j<100;j++){
            sum+=square[i][j];
        }
    }
    printf("%d",sum);

    return 0;
}