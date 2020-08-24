package project1;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Graphics;

// 初始界面
public class InitUI extends JFrame implements Boardconfig {

    JButton[] btn;
    
    public void initUI(int mode) {
        this.setTitle("亚马逊棋");
        this.setSize(width, height);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        String[] btnstr = new String[mode];
        if(mode==5) {
        	btnstr[0]="单人游戏";
        	btnstr[1]="双人游戏";
        	btnstr[2]="继续游戏";
        	btnstr[3]="规则说明";
        	btnstr[4]="退出游戏";
        }
        if(mode==2) {
        	btnstr[0]="黑方(后手)";
        	btnstr[1]="白方(先手)";
        }
        ButtonListener btnlistener = new ButtonListener(this);
        btn = new JButton[btnstr.length];
        for(int i = 0;i < btnstr.length;i++){
            btn[i] = new JButton(btnstr[i]);
            btn[i].setBounds(btn_x, btn_y[i], dim3.width, dim3.height);
            btn[i].addActionListener(btnlistener);
            btn[i].setContentAreaFilled(false);
            btn[i].setBorder(null);
            btn[i].setFont(btn_font);
            this.add(btn[i]); 
        }
        this.setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);

        g.drawImage(initui, 0, 0, this.getWidth(), this.getHeight(), this);
        for(int i = 0;i < this.btn.length;i++){
            this.btn[i].requestFocus();
        }
    }
    public static void main(String[] args){
        InitUI ui = new InitUI();
        ui.initUI(5);
    }
}