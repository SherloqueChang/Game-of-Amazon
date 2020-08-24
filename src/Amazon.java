package project1;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Amazon extends JPanel implements Boardconfig{
    public Graphics g;
    // 二维数组存储棋盘的落子情况,1-黑,2-白,3-障碍
    public int[][] isAvail = new int [10][10];
    // 游戏状态
    JTextField jt = new JTextField();
    // 记录上一步操作棋子对应的数组下标
    public int tx = -1, ty = -1;
    // 每步操作的状态
    public int turn = 0;
    // 回合数
    public int turnNum = 0;
    // 初始棋子的个数
    public int w_cnt = 4, b_cnt = 4;
    // 标识模式
    public int mode;
    // 初始化界面，并设置相应属性
    public void initUI(){
        JFrame jf = new JFrame();
        jf.setTitle("亚马逊棋");
        jf.setSize(width, height);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(3);
        jf.setLayout(new BorderLayout());

        // 初始棋子
        if(mode == 4) {
        	try {
				loadgame();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else {
        	isAvail[3][0] = 1;
            isAvail[6][0] = 1;
            isAvail[0][3] = 1;
            isAvail[9][3] = 1;
            isAvail[3][9] = 2;
            isAvail[6][9] = 2;
            isAvail[0][6] = 2;
            isAvail[9][6] = 2;
        }
        
        // 左边棋盘界面
        this.setPreferredSize(dim2);
        this.setBackground(Color.gray);
        jf.add(this, BorderLayout.CENTER);

        // 右边容器界面
        JPanel jp = new JPanel();
        jp.setPreferredSize(dim1);
        jp.setBackground(new Color(208, 198, 139));
        jf.add(jp, BorderLayout.EAST);
        jp.setLayout(new FlowLayout());

        // 加入按钮组件（右边界面）
        String[] butname = { "保存", "退出" };
        JButton[] button = new JButton[butname.length];
        for (int i = 0; i < butname.length; i++) {
            button[i] = new JButton(butname[i]);
            button[i].setPreferredSize(dim3);
            button[i].setContentAreaFilled(false);
            button[i].setBorder(null);
            button[i].setFont(btn_font);
            jp.add(button[i]);
        }

        // 右边按钮监视类
        ButtonListener btnListen = new ButtonListener(this);
        for (int i = 0; i < butname.length; i++) {
            button[i].addActionListener(btnListen);
        }

        jt.setFont(text_font); 
        jp.add(jt);
        jf.setVisible(true);
    }

    // 重写重绘方法
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(chessboard, 0, 0, this.getWidth(), this.getHeight(), this);
        
        // 重绘棋盘
        g.setColor(Color.black);
        for(int i = 0;i <= row; i++){
            g.drawLine(x, y + size*i, x + size*column, y+size*i);
        }
        for(int j = 0;j <= column; j++){
            g.drawLine(x+size*j, y, x+size*j, y+size*row);
        }

        // 重绘棋子
        for(int i = 0;i < row; i++){
            for(int j = 0;j < column; j++){
                if(isAvail[i][j]==1) {
					int cx=size*i+x;
					int cy=size*j+y;
					g.setColor(Color.black);
					g.fillOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
				}
				else if(isAvail[i][j]==2) {
					int cx=size*i+x;
					int cy=size*j+y;
					g.setColor(Color.white);
					g.fillOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
				}
				else if(isAvail[i][j]==3) {
					int cx=size*i+x;
					int cy=size*j+y;
					g.setColor(Color.gray);
					g.fillOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
				}
            }
        }
    }
    
    // 读取游戏进度
    public void loadgame() throws NumberFormatException, IOException {
    	File f = new File("GameInfo.txt");
        BufferedReader in = new BufferedReader(new FileReader(f));
        String line;
        int row = 0;
        while((line = in.readLine()) != null){
            String[] temp = line.split("\t");
            if(row == 0) {
            	this.mode = Integer.parseInt(temp[0]);
            }else if(row == 1){
            	this.turn = Integer.parseInt(temp[0]);
            }else {
            	for(int j = 0;j < temp.length;j++){
                    this.isAvail[row-2][j] = Integer.parseInt(temp[j]);
                }
            }
            row++;
        }
    }
}
