package project1;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.UIManager;

// 实现对JPanel的监听处理
public class ButtonListener implements Boardconfig, ActionListener {
    public Amazon gf;
    public InitUI UI;

    public ButtonListener(InitUI UI){
        this.UI = UI;
    }
    public ButtonListener(Amazon gf) {
        this.gf = gf;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
    	if(e.getActionCommand().equals("黑方(后手)")) {
    		Amazon gf = new Amazon();
    		gf.mode = 1;
            gf.initUI();
            UI.dispose();
            gf.jt.setText("白方先行");
            AI whiteAI= new AI(gf.isAvail, 2, gf.turnNum); 
			whiteAI.use();
			gf.isAvail[whiteAI.bestStart_x][whiteAI.bestStart_y] = 0;
			gf.isAvail[whiteAI.bestFinish_x][whiteAI.bestFinish_y] = 2;
			gf.isAvail[whiteAI.bestBarrier_x][whiteAI.bestBarrier_y] = 3;
			gf.print(gf.getGraphics());
			gf.turn=3;
            FrameListener fl = new FrameListener();
            fl.setGraphics(gf);
            gf.addMouseListener(fl);
    	}
    	else if(e.getActionCommand().equals("白方(先手)")) {
    		Amazon gf = new Amazon();
    		gf.mode = 2;
            gf.initUI();
            UI.dispose();
            gf.jt.setText("白方先行");
            FrameListener fl = new FrameListener();
            fl.setGraphics(gf);
            gf.addMouseListener(fl);
    	}
    	else if(e.getActionCommand().equals("单人游戏")){
        	InitUI gui = new InitUI();
        	gui.initUI(2);
        	UI.dispose();
        	try {
				clearinfo(2);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if (e.getActionCommand().equals("双人游戏")) {
            Amazon gf = new Amazon();
            gf.mode = 3;
            gf.initUI();
            UI.dispose();
            gf.jt.setText("白方先行");
            FrameListener fl = new FrameListener();
            fl.setGraphics(gf);
            gf.addMouseListener(fl);
            try {
				clearinfo(3);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getActionCommand().equals("规则说明")){
            UIManager.put("OptionPane.buttonFont", btn_font);
            UIManager.put("OptionPane.messageFont", rule_font);
            JOptionPane.showMessageDialog(null, rule, "规则说明", JOptionPane.PLAIN_MESSAGE);
        }else if(e.getActionCommand().equals("继续游戏")) {
        	Amazon gf = new Amazon();
        	gf.mode = 4;
        	gf.initUI();
            UI.dispose();
            if(gf.turn == 0) {
            	gf.jt.setText("白方行子");
            }else {
            	gf.jt.setText("黑方行子");
            }
            FrameListener fl = new FrameListener();
            fl.setGraphics(gf);
            gf.addMouseListener(fl);
        }else if(e.getActionCommand().equals("退出游戏")){
            System.exit(0);
        }else if(e.getActionCommand().equals("保存")){
        	try {
				savegame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getActionCommand().equals("退出")){
            InitUI UI = new InitUI();
            UI.initUI(5);
        }
    }
    
    // 保存游戏进度
    public void savegame() throws IOException {
    	if(this.gf.turn == 0 || this.gf.turn == 3) {
    		File f = new File("GameInfo.txt");
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            FileWriter out = null;
    		try {
    			out = new FileWriter(f);
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		out.write(this.gf.mode + "\r\n");
    		out.write(this.gf.turn + "\r\n");
            for(int i = 0;i < 10; i++){
                for(int j = 0;j < 10; j++){
                    out.write(this.gf.isAvail[i][j] + "\t");
                }
                out.write("\r\n");
            }
            out.close();
            UIManager.put("OptionPane.buttonFont", btn_font);
            UIManager.put("OptionPane.messageFont", rule_font);
            JOptionPane.showMessageDialog(null, msg, "提示", JOptionPane.PLAIN_MESSAGE);
    	}
    	
    }
    
    // 清除游戏保存进度
    public void clearinfo(int mode) throws IOException {
    	File f = new File("GameInfo.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        FileWriter out = null;
		try {
			out = new FileWriter(f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out.write(mode + "\r\n");
		out.write(0 + "\r\n");
        for(int i = 0;i < 10; i++){
            for(int j = 0;j < 10; j++){
                out.write(PawnInfo[i][j] + "\t");
            }
            out.write("\r\n");
        }
        out.close();
    }
}
