import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, M;
	static int[][] board;
	static List<Pos> houses;
	static List<Pos> stores;
	static int[][] distances;
	static int[] selectedStores;
	static boolean[] visited;
	static int minCityDistance;
	static class Pos{
		int row, col;
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		houses = new ArrayList<>();
		stores = new ArrayList<>();
		selectedStores = new int[M];
		visited = new boolean[stores.size()];
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<N; colIdx++) {
				switch(Integer.parseInt(st.nextToken())) {
				case 1:
					houses.add(new Pos(rowIdx,colIdx));
					break;
				case 2:
					stores.add(new Pos(rowIdx,colIdx));
					break;
				}
			}
		}
		distances = new int[houses.size()][stores.size()];
		for(int houseIdx = 0; houseIdx < houses.size(); houseIdx++) {
			for(int storeIdx = 0; storeIdx < stores.size(); storeIdx++) {
				distances[houseIdx][storeIdx] = getDistance(houses.get(houseIdx), stores.get(storeIdx));
			}
		}
		minCityDistance = Integer.MAX_VALUE;
	}
	public static int getDistance(Pos first, Pos second) {
		return Math.abs(first.row - second.row) + Math.abs(first.col - second.col);
	}
	
	public static void makeCityDistance() {
		int cityDistance = 0;
		for(int houseIdx = 0; houseIdx < houses.size(); houseIdx++) {
			int houseDistance = Integer.MAX_VALUE;			
			for(int storeIdx : selectedStores) {
				houseDistance = houseDistance > distances[houseIdx][storeIdx] ? distances[houseIdx][storeIdx] : houseDistance; 
			}
			cityDistance += houseDistance;
		}
		minCityDistance = minCityDistance > cityDistance ? cityDistance : minCityDistance;
	}
	public static void selectStores(int selectedCnt, int selectedIdx) {
		if(selectedCnt == M) {
			makeCityDistance();
			return;
		}
		for(int storeIdx = selectedIdx+1; storeIdx<stores.size(); storeIdx++) {
			selectedStores[selectedCnt] = storeIdx;
			selectStores(selectedCnt+1, storeIdx);
		}
	}
	public static void solve() {
		selectStores(0, -1);
	}
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
    	init();
    	solve();
    	System.out.println(minCityDistance);
    }
}
