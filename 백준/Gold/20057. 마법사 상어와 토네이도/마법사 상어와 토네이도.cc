#include<iostream>
#define MAX_N 501
#define left -1
#define right 1
#define up -1
#define down 1
#define left_direction 0
#define down_direction 1
#define right_direction 2
#define up_direction 3
using namespace std;

int dc_10[2][4]={{left,left,right,left},
                {left,right,right,right}};
int dr_10[2][4]={{up,down,down,up},
                {down,down,up,up}};
                
int dc_7[2][4]={{0,left,0,left},
                {0,right,0,right}};
int dr_7[2][4]={{up,0,up,0},
                {down,0,down,0}};

int dc_2[2][4]={{0,left*2,0,left*2},
                {0,right*2,0,right*2}};
int dr_2[2][4]={{up*2,0,up*2,0},
                {down*2,0,down*2,0}};
                
int dc_1[2][4]={{right,left,left,left},
                {right,right,left,right}};
int dr_1[2][4]={{up,up,up,down},
                {down,up,down,down}};

int dc_5[4]={left*2,0,right*2,0};
int dr_5[4]={0,down*2,0,up*2};

int dc_alpha[4]={left,0,right,0};
int dr_alpha[4]={0,down,0,up};



int N;
int MAP[MAX_N][MAX_N];
int score;
void view(){
    cout<<"score: "<<score<<endl;
    for(int i=1;i<=N;i++){
        for(int j=1;j<=N;j++){
            cout<<MAP[i][j]<<" ";
        }
        cout<<endl;
    }
    cout<<endl;

}
void init(){
    cin>>N;
    for(int i=1;i<=N;i++){
        for(int j=1;j<=N;j++){
            cin>>MAP[i][j];
        }
    }
    score=0;

}
int getDirection(int n, int cnt){
    int direction;
    // 홀수면, 왼쪽과 아래쪽을 각각 n번씩
    if(n%2){
        if(cnt<n) direction = left_direction;
        else direction = down_direction;
    }
    // 짝수면, 오른쪽과 위쪽을 각각 n번씩
    else{
        if(cnt<n) direction = right_direction;
        else direction = up_direction;
    }
    return direction;
}

bool inMAP(pair<int, int> pos){
    return 1<=pos.first && pos.first<=N && 1<=pos.second && pos.second<=N;
}

pair<int, int> movement(pair<int, int> cur, int direction){
    int n_r, n_c;
    pair<int, int> next;
    if(direction==left_direction) next = make_pair(cur.first, cur.second-1);
    else if(direction==right_direction) next = make_pair(cur.first, cur.second+1);
    else if(direction==up_direction) next = make_pair(cur.first-1, cur.second);
    else if(direction==down_direction) next = make_pair(cur.first+1, cur.second);

    int point = MAP[next.first][next.second];
    MAP[next.first][next.second]=0;

    int p_10 = int(point*0.1);
    int p_7 = int(point*0.07);
    int p_5 = int(point*0.05);
    int p_2 = int(point*0.02);
    int p_1 = int(point*0.01);

    point-=p_10*2+p_7*2+p_5+p_2*2+p_1*2;

    // 10%
    if(p_10){
        for(int i=0; i<2; i++){
            n_r = next.first+dr_10[i][direction];
            n_c = next.second+dc_10[i][direction];
            if(inMAP(make_pair(n_r,n_c))){
                MAP[n_r][n_c]+=p_10;
            }
            else score+=p_10;
        }
    }
    // 7%
    if(p_7){
        for(int i=0; i<2; i++){
            n_r = next.first+dr_7[i][direction];
            n_c = next.second+dc_7[i][direction];
            if(inMAP(make_pair(n_r,n_c))){
                MAP[n_r][n_c]+=p_7;
            }
            else score+=p_7;
        }
    }
    // 5%
    if(p_5){
        n_r = next.first+dr_5[direction];
        n_c = next.second+dc_5[direction];
        if(inMAP(make_pair(n_r,n_c))){
            MAP[n_r][n_c]+=p_5;
        }
        else score+=p_5;
    }
    // 2%
    if(p_2){
        for(int i=0; i<2; i++){
            n_r = next.first+dr_2[i][direction];
            n_c = next.second+dc_2[i][direction];
            if(inMAP(make_pair(n_r,n_c))){
                MAP[n_r][n_c]+=p_2;
            }
            else score+=p_2;
        }
    }
    // 1%
    if(p_1){
        for(int i=0; i<2; i++){
            n_r = next.first+dr_1[i][direction];
            n_c = next.second+dc_1[i][direction];
            if(inMAP(make_pair(n_r,n_c))){
                MAP[n_r][n_c]+=p_1;
            }
            else score+=p_1;
        }
    }
    // alpha
    n_r = next.first+dr_alpha[direction];
    n_c = next.second+dc_alpha[direction];
    if(inMAP(make_pair(n_r,n_c))){
        MAP[n_r][n_c]+=point;
    }
    else score+=point;

    return next;
}

void solve(){
    int direction;
    pair<int,int> cur = {N/2+1,N/2+1};

    for(int n=1; n<=N; n++){
        for(int cnt=0; cnt<n*2; cnt++){
            if(n==N && cnt==n-1) break;
            direction = getDirection(n, cnt);
            cur = movement(cur, direction);
        }
    }
    cout<<score<<endl;
}


int main(){
    cin.tie(0);cout.tie(0);ios::sync_with_stdio(0);
    init();
    solve();
    return 0;
}