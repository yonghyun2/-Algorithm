import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem17144_미세먼지안녕 {
	static int dir[][] = {{0,1},{-1,0},{0,-1},{1,0}};
	static int N;
	static int M;
	static class Pos{
		int x;
		int y;
		int d;
		
		public Pos(int x,int y,int d) {
			this.x=x;
			this.y=y;
			this.d=d;
		}
	}
	static int edge(int x,int y,int a[][][]) {
		int count = 0;
		for(int i=0;i<4;i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			
			if(nx<1 || nx>N || ny<1 || ny>M) continue;
			if(a[nx][ny][0] == -1) continue;
			count++;			
		}
		
		return count;
	}
	
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int map[][][] = new int[N+1][M+1][2];
		
		ArrayList<Pos> list = new ArrayList<Pos>();
		int upx=0;
		int downx=0;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				if(map[i][j][0]==-1 && upx==0) {
					upx = i;
				}
				if(map[i][j][0]==-1 && upx!=0) {
					downx = i;
				}
			}
		}
		//System.out.println(upx +" "+downx);
		int sum = 0;
		for(int tc=1;tc<=T;tc++) {
			sum = 0;
			//전체 미세먼지 리스트에 추가
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					if(map[i][j][0]!=0 && map[i][j][0]!=-1) list.add(new Pos(i,j,map[i][j][0]));
				}
			}
			
			int size = list.size();
			for(int i=0;i<size;i++) {
				int x = list.get(i).x;
				int y = list.get(i).y;
				int d = list.get(i).d;
				
				for(int a=0;a<4;a++) {
					int nx = x + dir[a][0];
					int ny = y + dir[a][1];
					
					if(nx<1 || nx>N || ny<1 || ny>M) continue;
					if(map[nx][ny][0]==-1) continue;
					
					map[nx][ny][1] +=  map[x][y][0]/5;							
					
				}
				int temp = edge(x, y, map);
				map[x][y][1] += (map[x][y][0] -  ((map[x][y][0]/5)*temp));
				map[x][y][0] = 0;
			}			
			list.clear();
			
			
			
			//위쪽 미세먼지 이동
			for(int x=2;x<=M-1;x++) {
				map[upx][x+1][0] = map[upx][x][1];
			}
			for(int x=upx;x>=2;x--) {
				map[x-1][M][0] = map[x][M][1];
			}
			for(int x=M;x>=2;x--) {
				map[1][x-1][0] = map[1][x][1];
			}
			for(int x=1;x<=upx-2;x++) {
				map[x+1][1][0] = map[x][1][1];
			}
			
			
			
		
			//아래쪽 미세먼지 이동
			for(int x=2;x<=M-1;x++) {
				map[downx][x+1][0] = map[downx][x][1];
			}
			for(int x=downx;x<=N-1;x++) {
				map[x+1][M][0] = map[x][M][1];
			}
			for(int x=M;x>=2;x--) {
				map[N][x-1][0] = map[N][x][1];
			}
			
			
			for(int x=N;x>=downx+2;x--) {
				map[x-1][1][0] = map[x][1][1];
			}
			
		
				
				
			
			for(int x=2;x<=upx-1;x++) {
				for(int y=2;y<=M-1;y++) {
					map[x][y][0] = map[x][y][1];
				}
			}
			
			for(int x=downx+1;x<=N-1;x++) {
				for(int y=2;y<=M-1;y++) {
					map[x][y][0] = map[x][y][1];
				}
			}			

			
			
			//먼지 초기화
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {				
					map[i][j][1] = 0;
					
				}
			}
			
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					if(map[i][j][0]!=-1) sum += map[i][j][0];
				}
			}			
			
		}
//		for(int i=1;i<=N;i++) {
//			for(int j=1;j<=M;j++) {
//				System.out.print(map[i][j][0]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(sum);

	}

}
