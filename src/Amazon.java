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
    // ��ά����洢���̵��������,1-��,2-��,3-�ϰ�
    public int[][] isAvail = new int [10][10];
    // ��Ϸ״̬
    JTextField jt = new JTextField();
    // ��¼��һ���������Ӷ�Ӧ�������±�
    public int tx = -1, ty = -1;
    // ÿ��������״̬
    public int turn = 0;
    // �غ���
    public int turnNum = 0;
    // ��ʼ���ӵĸ���
    public int w_cnt = 4, b_cnt = 4;
    // ��ʶģʽ
    public int mode;
    // ��ʼ�����棬��������Ӧ����
    public void initUI(){
        JFrame jf = new JFrame();
        jf.setTitle("����ѷ��");
        jf.setSize(width, height);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(3);
        jf.setLayout(new BorderLayout());

        // ��ʼ����
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
        
        // ������̽���
        this.setPreferredSize(dim2);
        this.setBackground(Color.gray);
        jf.add(this, BorderLayout.CENTER);

        // �ұ���������
        JPanel jp = new JPanel();
        jp.setPreferredSize(dim1);
        jp.setBackground(new Color(208, 198, 139));
        jf.add(jp, BorderLayout.EAST);
        jp.setLayout(new FlowLayout());

        // ���밴ť������ұ߽��棩
        String[] butname = { "����", "�˳�" };
        JButton[] button = new JButton[butname.length];
        for (int i = 0; i < butname.length; i++) {
            button[i] = new JButton(butname[i]);
            button[i].setPreferredSize(dim3);
            button[i].setContentAreaFilled(false);
            button[i].setBorder(null);
            button[i].setFont(btn_font);
            jp.add(button[i]);
        }

        // �ұ߰�ť������
        ButtonListener btnListen = new ButtonListener(this);
        for (int i = 0; i < butname.length; i++) {
            button[i].addActionListener(btnListen);
        }

        jt.setFont(text_font); 
        jp.add(jt);
        jf.setVisible(true);
    }

    // ��д�ػ淽��
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(chessboard, 0, 0, this.getWidth(), this.getHeight(), this);
        
        // �ػ�����
        g.setColor(Color.black);
        for(int i = 0;i <= row; i++){
            g.drawLine(x, y + size*i, x + size*column, y+size*i);
        }
        for(int j = 0;j <= column; j++){
            g.drawLine(x+size*j, y, x+size*j, y+size*row);
        }

        // �ػ�����
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
    
    // ��ȡ��Ϸ����
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
