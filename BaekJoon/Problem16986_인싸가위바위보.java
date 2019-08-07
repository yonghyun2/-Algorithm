import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem16986_인싸가위바위보 {
	static int N;
	static int K;
	static int array[][];
	static boolean result = false;
	static class user {
		int line;
		int what[] = new int[21];
		
		public user() {
			super();
		}

		public user(int line, int what[]) {
			this.line = line;
			this.what = what;
		}
	}
	
	static user player[] = new user[4];

	static int whoWin(int what1, int what2, int user1, int user2) {
		if (what1 != what2) {
			if (array[what1][what2] == 2) {
				return user1;
			} else if (array[what1][what2] == 0) {
				return user2;
			}

		} else { // 비겼다
			return Math.max(user1, user2);
		}

		return -1;
	}

	static void dfs(int user1, int user2, int winnerCount[], boolean check[], int x, int y) {
		if(winnerCount[1]==K) {
			result = true;
			return;
		}
		
		else if(winnerCount[2]==K || winnerCount[3]==K) {
			return;
		}
		
		int userNext = 6 - user1 - user2; //다음 플레이어
		
		if(user1 == 1) {
			for(int i=1;i<=N;i++) {
				if(!check[i]) {
					if(user2==2) {
						int winner = whoWin(i, player[user2].what[x],1,user2);
						winnerCount[winner]++;
						check[i] = true;
						dfs(winner,userNext,winnerCount,check,x+1,y);
						winnerCount[winner]--;
						check[i] = false;
						
						
					} else if(user2==3) {
						int winner = whoWin(i, player[user2].what[y],1,user2);
						winnerCount[winner]++;
						check[i] = true;
						dfs(winner,userNext,winnerCount,check,x,y+1);
						winnerCount[winner]--;
						check[i] = false;
						
					}
				}
			}
		} else if(user2 == 1) {
			for(int i=1;i<=N;i++) {
				if(!check[i]) {
					if(user1==2) {
						int winner = whoWin(player[user1].what[x], i, user1, 1);
						winnerCount[winner]++;
						check[i] = true;
						dfs(winner,userNext,winnerCount,check,x+1,y);
						winnerCount[winner]--;
						check[i] = false;
						
					} else if(user1==3) {
						int winner = whoWin(player[user1].what[y], i, user1,1);
						winnerCount[winner]++;
						check[i] = true;
						dfs(winner,userNext,winnerCount,check,x,y+1);
						winnerCount[winner]--;
						check[i] = false;
						
					}
				}
			}
			
		} else { //둘다 지우가 아닐때
			if(user1==2) {
				int winner = whoWin(player[user1].what[x],player[user2].what[y],user1,user2);
				winnerCount[winner]++;
				dfs(winner,userNext,winnerCount,check,x+1,y+1);
				winnerCount[winner]--;
				
			} else if(user1==3){
				int winner = whoWin(player[user1].what[y],player[user2].what[x],user1,user2);
				winnerCount[winner]++;
				dfs(winner,userNext,winnerCount,check,x+1,y+1);
				winnerCount[winner]--;
			}
		}
				
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N + 1][N + 1];
		
	
		
		boolean check[] = new boolean[N + 1];
		int winnerCount[] = new int[4];
		int number[] = new int[21];

	

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 20; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		player[2] = new user(2,number);

		int number2[] = new int[21];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 20; i++) {
			number2[i] = Integer.parseInt(st.nextToken());
		}
		player[3] = new user(3,number2);
		
		//System.out.println(Arrays.toString(player[2].what));
		//System.out.println(Arrays.toString(player[3].what));

		dfs(1, 2, winnerCount, check, 1, 1);
		
		
		if(result==true) System.out.println(1);
		else System.out.println(0);

	}

}
