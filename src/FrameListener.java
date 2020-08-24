package project1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;

// �������¼�
public class FrameListener implements Boardconfig, MouseListener{
    public Amazon gf;

    public void setGraphics(Amazon gf){
        this.gf = gf;
    }

    public void mouseClicked(java.awt.event.MouseEvent e){
        // �����λ��
        int ex = e.getX();
        int ey = e.getY();
        // �����λ�ö�Ӧ�������Ͻ�λ��
        int cx = ((ex - x) / size) * size + x;
        int cy = ((ey - y) / size) * size + y;
        // ��Ӧ�����±�
        int ax = (cx - x) / size;
        int ay = (cy - y) / size;

        Graphics g = gf.getGraphics();

        if(ifIn(ax, ay)){
        	gf.turnNum++;
        	if(gf.mode == 2 || gf.mode == 3) {
        		if(gf.turn == 0){
                    // ѡ��Ҫ�ƶ��İ���
                    if(gf.isAvail[ax][ay] == 2){
                        gf.tx = ax; gf.ty = ay;
                        g.drawOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
                        g.setColor(Color.black);
                        g.drawOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
                        gf.turn++;
                        gf.jt.setText("�׷�����");
                    }
                }else if(gf.turn == 1){
                    // ѡ������ƶ���λ��    
                    if(gf.tx == ax && gf.ty == ay){
                        // �ظ���������ӿ�ȡ��ѡ��
                        gf.turn--;
                        g.setColor(Color.white);
                        g.drawOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
                        g.fillOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
                    }else if(isLegal(gf.tx, gf.ty, ax, ay)){
                        gf.isAvail[gf.tx][gf.ty] = 0;
                        gf.tx = ax; gf.ty = ay;
                        gf.isAvail[ax][ay] = 2;
                        gf.paint(g);
                        gf.turn++;
                    }
                }else if(gf.turn == 2){
                    // ѡ�������õ��ϰ�λ��
                    if(isLegal(gf.tx, gf.ty, ax, ay)){
                        g.setColor(Color.gray);
                        g.fillOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
                        gf.isAvail[ax][ay] = 3;
                        gf.turn++;
                        int t = checkResult();
                        if(t == 0)  gf.jt.setText("�ڷ�����");
                        else if(t == 1) gf.jt.setText("�׷���ʤ");
                        else if(t == 2) gf.jt.setText("�ڷ���ʤ");
                        if(gf.mode==2 && gf.turn == 3) {
                			AI blackAI= new AI(gf.isAvail, 1, gf.turnNum); 
                			blackAI.use();
                			gf.isAvail[blackAI.bestStart_x][blackAI.bestStart_y] = 0;
                			gf.isAvail[blackAI.bestFinish_x][blackAI.bestFinish_y] = 1;
                			gf.isAvail[blackAI.bestBarrier_x][blackAI.bestBarrier_y] = 3;
                			gf.print(g);
                			int tt = checkResult();
                            if(t == 0)  gf.jt.setText("�׷�����");
                            else if(tt == 1) gf.jt.setText("�׷���ʤ");
                            else if(tt == 2) gf.jt.setText("�ڷ���ʤ");
                			gf.turn = 0;
                		}
                    }
                }		
        	}
        	if(gf.mode==1 || gf.mode==3) {
        		if(gf.turn == 3){
                    // ѡ��Ҫ�ƶ��ĺ���
                    if(gf.isAvail[ax][ay] == 1){
                        gf.tx = ax; gf.ty = ay;
                        g.setColor(Color.white);
                        g.drawOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
                        gf.turn++;
                    }
                }else if(gf.turn == 4){
                    // ѡ������ƶ���λ��
                    if(gf.tx == ax && gf.ty == ay){
                        // �ظ���������ӿ�ȡ��ѡ��
                        gf.turn--;
                        g.setColor(Color.black);
                        g.drawOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
                        g.fillOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
                    }else if(isLegal(gf.tx, gf.ty, ax, ay)){
                        gf.isAvail[gf.tx][gf.ty] = 0;
                        gf.tx = ax; gf.ty = ay;
                        gf.isAvail[ax][ay] = 1;
                        gf.paint(g);
                        gf.turn++;
                    }
                }else if(gf.turn == 5){
                    // ѡ�������õ��ϰ�λ��
                    if(isLegal(gf.tx, gf.ty, ax, ay)){
                        g.setColor(Color.gray);
                        g.fillOval(cx+size/5, cy+size/5, size/4*3, size/4*3);
                        gf.isAvail[ax][ay] = 3;
                        gf.turn = 0;
                        int t = checkResult();
                        if(t == 0)  gf.jt.setText("�׷�����");
                        else if(t == 1) gf.jt.setText("�׷���ʤ");
                        else if(t == 2) gf.jt.setText("�ڷ���ʤ");
                        if(gf.mode==1 && gf.turn == 0) {
                			AI whiteAI= new AI(gf.isAvail, 2, gf.turnNum); 
                			whiteAI.use();
                			gf.isAvail[whiteAI.bestStart_x][whiteAI.bestStart_y] = 0;
                			gf.isAvail[whiteAI.bestFinish_x][whiteAI.bestFinish_y] = 2;
                			gf.isAvail[whiteAI.bestBarrier_x][whiteAI.bestBarrier_y] = 3;
                			gf.print(g);
                			int tt = checkResult();
                            if(t == 0)  gf.jt.setText("�ڷ�����");
                            else if(tt == 1) gf.jt.setText("�׷���ʤ");
                            else if(tt == 2) gf.jt.setText("�ڷ���ʤ");
                			gf.turn = 3;
                		}
                    }
                }
        	}
        }
    }
    public void mousePressed(java.awt.event.MouseEvent e){

    }

    public void mouseReleased(java.awt.event.MouseEvent e) {
		  
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
		  
    }

    public void mouseExited(java.awt.event.MouseEvent e) {
		  
    }
    // ��������±��Ƿ�Խ��
    public boolean ifIn(int x, int y){
        if(x < 0 || y < 0 || x > 9 || y > 9)
            return false;
        return true;
    }
    // �ƶ�λ���Ƿ�Ϸ�
    public boolean isLegal(int a, int b, int x, int y){
    	if(a==x && b==y)
    		return false;
        if(a != x && b != y && Math.abs(a-x) != Math.abs(b-y))
            return false;
        if(a == x){
            // ��ͬһ���ƶ�
            if(b < y){
                for(int i = b+1;i <= y;i++){
                    if(gf.isAvail[a][i] != 0)
                        return false;
                }
            }else{
                for(int i = y;i <= b-1;i++){
                    if(gf.isAvail[a][i] != 0)
                        return false;
                }
            }
        }else if(b == y){
            // ��ͬһ���ƶ�
            if(a > x){
                for(int i = x;i <= a-1;i++){
                    if(gf.isAvail[i][y] != 0)
                        return false;
                }
            }else{
                for(int i = a+1;i <= x;i++){
                    if(gf.isAvail[i][y] != 0)
                        return false;
                }
            } 
        }else if(Math.abs(a-x) == Math.abs(b-y)){
            // �Խ����ƶ�
            if(a < x && b < y){
                for(int i = 1;i <= Math.abs(a-x);i++){
                    if(gf.isAvail[a+i][b+i] != 0)
                        return false;
                }
            }else if(a < x && b > y){
                for(int i = 1;i <= Math.abs(a-x);i++){
                    if(gf.isAvail[a+i][b-i] != 0)
                        return false;
                }
            }else if(a > x && b > y){
                for(int i = 1;i <= Math.abs(a-x);i++){
                    if(gf.isAvail[a-i][b-i] != 0)
                        return false;
                }
            }else{
                for(int i = 1;i <= Math.abs(a-x);i++){
                    if(gf.isAvail[a-i][b+i] != 0)
                        return false;
                }
            }
        }
        return true;
    }
    // �����Ϸ�Ƿ����
    public int checkResult(){
        final int[] dx = { 1, -1, 0, 0, 1, -1, 1, -1 };
        final int[] dy = { 0, 0, 1, -1, 1, -1, -1, 1 };
        int wc = 0, bc = 0;
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(gf.isAvail[i][j] == 1){
                    // ����
                    int cnt = 0;
                    for(int k = 0;k < 8;k++){
                        int nx = i + dx[k], ny = j + dy[k];
                        if(ifIn(nx, ny)){
                            if(gf.isAvail[nx][ny] != 0) cnt++;
                        }else{
                            cnt++;
                        }
                    }
                    // �ú�����·����
                    if(cnt == 8) bc++;
                }else if(gf.isAvail[i][j] == 2){
                    // ����
                    int cnt = 0;
                    for(int k = 0;k < 8;k++){
                        int nx = i + dx[k], ny = j + dy[k];
                        if(ifIn(nx, ny)){
                            if(gf.isAvail[nx][ny] != 0) cnt++;
                        }else{
                            cnt++;
                        }
                    }
                    // �ð�����·����
                    if(cnt == 8) wc++;
                }
            }
        }
        if(wc == gf.w_cnt){
            return 2;
        }else if(bc == gf.b_cnt){
            return 1;
        }
        return 0;
    }
}