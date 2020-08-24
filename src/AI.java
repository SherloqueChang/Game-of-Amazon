package amazon;

public class AI {
	//��ȡ��ǰ���̵�״̬
	int[][] board=new int[10][10];
	//���ڸ��������������
	int[][] boardcopy=new int[10][10];
	int[][] chessboard=new int[10][10];
	//�洢�����ƶ�������
	int[][] canMoveChess=new int[10][10];
	//�Ը���λ�õ����Ƚ�������
	int[][] chessMobility=new int[10][10];
	//�洢����λ�������queen move number ��king move number
	int[][] myQueenMoveNumber=new int[10][10];
	int[][] myKingMoveNumber=new int[10][10];
	int[][] enemyQueenMoveNumber=new int[10][10];
	int[][] enemyKingMoveNumber=new int[10][10];
	//��¼��ͬ�׶θ�������������ռ��Ȩ��
	double[][] weight = new double[3][5];
	//player���������ɫ��turnNum�ǵ�ǰ�Ļغ���
	int player, turnNum;
	//��¼�����ó������ӷ���
	int bestStart_x, bestStart_y, bestFinish_x, bestFinish_y, bestBarrier_x, bestBarrier_y;
	//�����������ӣ����readme
	double territory1, territory2, position1, position2, mobility, k;
	//8������
	int[] dx=new int[8];
	int[] dy=new int[8];
	//haveCanMoveChess��¼�Ƿ��п��ƶ������ӣ�isMove��¼�Ƿ��Ѿ�����
	boolean haveCanMoveChess, isMove;
	//���캯����Ҳ�ǳ�ʼ������
	AI(int[][] board,int player,int turnNum) {
		dx[0]=-1;dy[0]=-1;
		dx[1]=-1;dy[1]=0;
		dx[2]=-1;dy[2]=1;
		dx[3]=0;dy[3]=-1;
		dx[4]=0;dy[4]=1;
		dx[5]=1;dy[5]=-1;
		dx[6]=1;dy[6]=0;
		dx[7]=1;dy[7]=1;
		this.board=board;
		this.player=player;
		this.turnNum=turnNum;
		isMove = false;
		territory1=territory2=position1=position2=mobility=0;
		k=0.5;
		weight[0][0]=0.14;weight[0][1]=0.37;weight[0][2]=0.13;weight[0][3]=0.13;weight[0][4]=0.20;
		weight[1][0]=0.30;weight[1][1]=0.25;weight[1][2]=0.20;weight[1][3]=0.20;weight[1][4]=0.05;
		weight[2][0]=0.80;weight[2][1]=0.10;weight[2][2]=0.05;weight[2][3]=0.05;weight[2][4]=0.00;
		haveCanMoveChess=true;
	}
	// ��������±��Ƿ�Խ��
	public boolean ifIn(int x, int y){
        if(x < 0 || y < 0 || x > 9 || y > 9)
            return false;
        return true;
    }
	
    //����������0
    public void reset(int[][] x) {
    	for(int i=0;i<10;i++) {
    		for(int j=0;j<10;j++) {
    			x[i][j]=0;
    		}
    	}
    }
    
    //�ж�һ�������ܷ�����ƶ�
    public boolean canKingMove(int x, int y) {
		for (int i = 0; i < 8; i++)
			if (ifIn(x + dx[i], y + dy[i]) && boardcopy[x + dx[i]][y + dy[i]] == 0) return true;
		return false;
	}
    
    //Ѱ�ҿ����ƶ������ӣ��������ע��canMoveChess��
    public void findCanMove() {
    	haveCanMoveChess = false;
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			if (boardcopy[i][j] == player && canKingMove(i, j)) {
    				haveCanMoveChess = true;
    				canMoveChess[i][j] = player;
    			}
    }
    
    //�����̸��ƣ���ֹͿɫ���������������ƻ�
    public void copy() {
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			boardcopy[i][j] = board[i][j];
    }
    
    //����QueenMoveNumber������Ϳɫ����number����������������role�Ǵ洢��λ��
    public void searchQueenMove(int number,int[][] role) {
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			//Ѱ�Ҹ���ɫ������
    			if (canMoveChess[i][j] == player) {
    				for (int p = 0; p < 8; p++)
    					for (int d = 1; d < 10; d++) {
    						if (ifIn(i + d * dx[p], j + d * dy[p]) && boardcopy[i + d * dx[p]][j + d * dy[p]] == 0) {
    							//����Ϳɫ
    							chessboard[i + d * dx[p]][j + d * dy[p]] = player;
    							role[i + d * dx[p]][j + d * dy[p]] = number;
    						}
    						else break;
    					}
    				canMoveChess[i][j] = 0;
    			}
    	//��������
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			if (chessboard[i][j] != 0) boardcopy[i][j] = chessboard[i][j];
    	
    	reset(chessboard);
    }
    
  //����kingMoveNumber������Ϳɫ����number����������������role�Ǵ洢��λ��
    public void searchKingMove(int number,int[][] role) {
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			//Ѱ�Ҹ�ɫ������
    			if (canMoveChess[i][j] == player) {
    				for (int p = 0; p < 8; p++) {
    					if (ifIn(i + dx[p], j + dy[p]) && boardcopy[i + dx[p]][j + dy[p]] == 0) {
    						chessboard[i + dx[p]][j + dy[p]] = player;
    						role[i + dx[p]][j + dy[p]] = number;
    					}
    				}
    				canMoveChess[i][j] = 0;
    			}
    	//����
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			if (chessboard[i][j]!=0) boardcopy[i][j] = chessboard[i][j];
    	reset(chessboard);
    }
    void markNumber() {
    	/*myQueenMoveNumber*/
    	copy();
    	int number = 1;
    	haveCanMoveChess = true;
    	reset(canMoveChess);
    	while (haveCanMoveChess) {
    		findCanMove();
    		if (!haveCanMoveChess) break;
    		searchQueenMove(number, myQueenMoveNumber);
    		number++;
    	}
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			if (myQueenMoveNumber[i][j] == 0) myQueenMoveNumber[i][j] = 9999;
    	
    	/*myKingMoveNumber*/
    	copy();
    	number = 1;
    	haveCanMoveChess = true;
    	reset(canMoveChess);
    	while (haveCanMoveChess) {
    		findCanMove();
    		if (!haveCanMoveChess) break;
    		searchKingMove(number, myKingMoveNumber);
    		number++;
    	}
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			if (myKingMoveNumber[i][j] == 0) myKingMoveNumber[i][j] = 9999;
    	
    	
    	//���������ӵ���ɫ���ڶԶԷ��ľ��ƽ�������
    	player = 3-player;
    	/*enemyQueenNumber*/
    	copy();
    	number = 1;
    	haveCanMoveChess = true;
    	reset(canMoveChess);
    	while (haveCanMoveChess) {
    		findCanMove();
    		if (!haveCanMoveChess) break;
    		searchQueenMove(number, enemyQueenMoveNumber);
    		number++;
    	}
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			if (enemyQueenMoveNumber[i][j] == 0) enemyQueenMoveNumber[i][j] = 9999;
    	
    	/*enemyKingMoveNumber*/
    	copy();
    	number = 1;
    	haveCanMoveChess = true;
    	reset(canMoveChess);
    	while (haveCanMoveChess) {
    		findCanMove();
    		if (!haveCanMoveChess) break;
    		searchKingMove(number, enemyKingMoveNumber);
    		number++;
    	}
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++)
    			if (enemyKingMoveNumber[i][j] == 0) enemyKingMoveNumber[i][j] = 9999;
    	
    	//���»ص�ԭ���Ľ�ɫ
    	
    	player = 3-player;
    }
    /*����*/
    void calculate() {
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++) {
    			if (board[i][j] != 0) continue;
    			/*territory1*/
    			if (myQueenMoveNumber[i][j] < enemyQueenMoveNumber[i][j]) territory1++;
    			else if (myQueenMoveNumber[i][j] > enemyQueenMoveNumber[i][j]) territory1--;
    			else {
    				if (myQueenMoveNumber[i][j] != 9999) territory1 += k;
    			}
    			/*territory2*/
    			if (myKingMoveNumber[i][j] < enemyKingMoveNumber[i][j]) territory2++;
    			else if (myKingMoveNumber[i][j] > enemyKingMoveNumber[i][j]) territory2--;
    			else {
    				if (myKingMoveNumber[i][j] != 9999) territory2 += k;
    			}
    			/*position1&&position2*/
    			position1 += 2 * (Math.pow(2, -myQueenMoveNumber[i][j]) - Math.pow(2, enemyQueenMoveNumber[i][j]));
    			position2 += Math.min(1, Math.max(-1.0, (double)(enemyKingMoveNumber[i][j] - myKingMoveNumber[i][j]) / 6));
    		}
    }
    
    /*��������*/
    void calculateMobility() {
    	double mobility1=0, mobility2=0;
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++) {
    			if (board[i][j] != 0) continue;
    			for (int p = 0; p < 8; p++)
    				if (ifIn(i + dx[p], j + dy[p]) && board[i + dx[p]][j + dy[p]] == 0 ) chessMobility[i][j]++;
    		}
    	for (int i = 0; i < 10; i++)
    		for (int j = 0; j < 10; j++) {
    			if (board[i][j] == player) {
    				for (int p = 0; p < 8; p++)
    					for (int d = 1; d < 10; d++) {
    						if (ifIn(i + d * dx[p], j + d * dy[p]) && board[i + d * dx[p]][j + d * dy[p]] == 0) {
    							mobility1 += (double)chessMobility[i + d * dx[p]][j + d * dy[p]] / d;
    						}
    						else break;
    					}
    			}
    			if (board[i][j] == 3-player) {
    				for (int p = 0; p < 8; p++)
    					for (int d = 1; d < 10; d++) {
    						if (ifIn(i + d * dx[p], j + d * dy[p]) && board[i + d * dx[p]][j + d * dy[p]] == 0) {
    							mobility2 += (double)chessMobility[i + d * dx[p]][j + d * dy[p]] / d;
    						}
    						else break;
    					}
    			}
    		}
    	mobility = mobility1 - mobility2;
    	reset(chessMobility);
    }
    
    //�Ծ��ƽ�������
    public double calculateValue(){
    	if (turnNum <= 20)
			return territory1 * weight[0][0] + territory2 * weight[0][1] + mobility * weight[0][4] + position1 * weight[0][2] + position2 * weight[0][3];
		else if (turnNum <= 49)
			return territory1 * weight[1][0] + territory2 * weight[1][1] + mobility * weight[1][4] + position1 * weight[1][2] + position2 * weight[1][3];
		else
			return territory1 * weight[2][0] + territory2 * weight[2][1] + mobility * weight[2][4] + position1 * weight[2][2] + position2 * weight[2][3];
    }
    
    //����alpha-beta��֧�����о���
    public double alpha_beta(int depth, int simulate, double alpha, double beta, int stepNum) {
    	//�Ѿ��ﵽģ�ⲽ��
    	if (depth == 0) {
    		territory1 = 0;
    		territory2 = 0;
    		position1 = 0;
    		position2 = 0;
    		mobility = 0;
    		markNumber();
    		calculate();
    		calculateMobility();
    		double t = calculateValue();
    		return t;
    	}
    	
    	else {
    		copy();
    		for (int x = 0; x < 10; x++)
    			for (int y = 0; y < 10; y++) {
    				if (board[x][y] == simulate) {
    					//ģ��ѡ������
    					for (int i = 0; i < 8; i++)
    						for (int d = 1; d < 10; d++) {
    							//ģ���ƶ�����
    							int finish_x = x + dx[i] * d;
    							int finish_y = y + dy[i] * d;
    							if (!ifIn(finish_x, finish_y)) break;
    							if(board[finish_x][finish_y] != 0) break;
    							board[finish_x][finish_y] = simulate;
    							board[x][y] = 0;
    							for (int j = 0; j < 8; j++)
    								for (int d2 = 1; d2 < 10; d2++) {
    									//ģ����ϰ�
    									int barrier_x = finish_x + dx[j] * d2;
    									int barrier_y = finish_y + dy[j] * d2;
    									if (!ifIn(barrier_x, barrier_y)) break;
    									if(board[barrier_x][barrier_y] != 0) break;
    									board[barrier_x][barrier_y] = 3;
    									//���������ҵ�һ�����ӷ���
    									if(!isMove) {
    										bestStart_x = x;
											bestStart_y = y;
											bestFinish_x = finish_x;
											bestFinish_y = finish_y;
											bestBarrier_x = barrier_x;
											bestBarrier_y = barrier_y;
											isMove = true;
    									}
    									double ans = alpha_beta(depth - 1, 3 - simulate, alpha, beta, stepNum);
    									//����
    									board[barrier_x][barrier_y] = 0;
    									//�ҷ�max
    									if(simulate==player) {
    										if (ans > alpha) {
        										alpha = ans;
        										if (depth == stepNum) {
        											bestStart_x = x;
        											bestStart_y = y;
        											bestFinish_x = finish_x;
        											bestFinish_y = finish_y;
        											bestBarrier_x = barrier_x;
        											bestBarrier_y = barrier_y;
        										}
        									}
    										if (alpha >= beta) {
    											board[finish_x][finish_y] = 0;
    											board[x][y] = simulate;
        										return alpha;
    										}
    									}
    									//�з�min
    									else {
    										if (ans < beta) {
        										beta = ans;
        									}
    										if (alpha >= beta) {
    											board[finish_x][finish_y] = 0;
    											board[x][y] = simulate;
        										return beta;
    										}
    									}
    								}
    							//����
    							board[finish_x][finish_y] = 0;
								board[x][y] = simulate;
    						}
    				}
    			}
    		//�ҷ�max
    		if(simulate==player) {
    			return alpha;
    		}
    		//�з�min
    		else 
    			return beta;
    	}
    }
    
    public void use() {
    	//ǰ35�غ�����һ�㣬36��79�������㣬�����������
    	if(turnNum<=35) {
    		k = 0.2;
			alpha_beta(1, player, -99999, 99999, 1);
    	}
    	else if(turnNum<=79) {
			k = -0.2;
			alpha_beta(2, player, -99999, 99999, 2);
		}
		else {
			k = 0.2;
			alpha_beta(3, player, -99999, 99999, 3);
		}
		
    }
    
    public static void main(String[] args) {
    	//ʾ��
    	int[][] board = new int[10][10];
    	AI whiteAI= new AI(board, 2, 0); 
		whiteAI.board[3][0] = 1;
		whiteAI.board[6][0] = 1;
		whiteAI.board[0][3] = 1;
		whiteAI.board[9][3] = 1;
		whiteAI.board[3][9] = 2;
		whiteAI.board[6][9] = 2;
		whiteAI.board[0][6] = 2;
		whiteAI.board[9][6] = 2;
		whiteAI.use();
		whiteAI.board[whiteAI.bestStart_x][whiteAI.bestStart_y] = 0;
		whiteAI.board[whiteAI.bestFinish_x][whiteAI.bestFinish_y] = 1;
		whiteAI.board[whiteAI.bestBarrier_x][whiteAI.bestBarrier_y] = 3;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				System.out.printf("%d ",whiteAI.board[i][j]);
			}
			System.out.printf("\n");
		}
    }
}
