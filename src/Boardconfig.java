package project1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

// 棋盘规格
public interface Boardconfig {
    int x = 70, y = 55; // 棋盘起始位置
    int size = 50; // 棋盘方格大小
    int row = 10, column = 10;// 棋盘行列数
    Dimension dim1 = new Dimension(125, 0);  // 游戏界面右部分大小
    Dimension dim2 = new Dimension(600, 0);  // 游戏界面左部分大小
    Dimension dim3 = new Dimension(120, 40); // 游戏界面右部分按钮大小
    // 主界面按钮位置
    int btn_x = 325;                         
    int[] btn_y = { 350, 400, 450, 500, 550 };
    // 界面大小
    int width = 800, height = 650;
    String rule = "1. 在10*10的棋盘上，每方有四个棋子（四个Amazons）；\n" +
    "2. 每个棋子都相当于国际象棋中的皇后，它们的行棋方法与皇后相同，\n" + 
    "   可以在八个方向上任意行走，但不能穿过阻碍；\n" +
    "3. 当轮到一方行棋时，此方只能而且必须移动四个Amazons中的一个，并在移动完成后，\n" +
    "   由当前移动的棋子释放一个障碍，障碍的释放方法与棋子的移动方法相同（皇后的走法，\n" +
    "   不能穿过障碍），同样障碍的放置也是必须的；\n"+
    "4. 当某方完成某次移动后，对方四个棋子均不能再移动时，对方将输掉比赛；\n"+
    "5. 每次开局位于棋盘下方的玩家先手；\n"+
    "6. 整个比赛中双方均不能吃掉对方或己方的棋子或障碍。";
    String msg = "         保存成功";	
    int[][] PawnInfo = new int[][]{
    		{ 0, 0, 0, 1, 0, 0, 2, 0, 0, 0 },
    		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
    		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
    		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    		{ 0, 0, 0, 1, 0, 0, 2, 0, 0, 0 }
    };

    Image initui = new ImageIcon("pic\\initui.jpg").getImage();
    Image chessboard = new ImageIcon("pic\\chessboard.jpg").getImage();

    // 设置各部分字体样式
    Font btn_font = new Font("隶书", Font.PLAIN, 20);
    Font text_font = new Font("隶书", Font.BOLD, 14);
    Font rule_font = new Font("楷体", Font.PLAIN, 18);
}
