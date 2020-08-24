package project1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

// ���̹��
public interface Boardconfig {
    int x = 70, y = 55; // ������ʼλ��
    int size = 50; // ���̷����С
    int row = 10, column = 10;// ����������
    Dimension dim1 = new Dimension(125, 0);  // ��Ϸ�����Ҳ��ִ�С
    Dimension dim2 = new Dimension(600, 0);  // ��Ϸ�����󲿷ִ�С
    Dimension dim3 = new Dimension(120, 40); // ��Ϸ�����Ҳ��ְ�ť��С
    // �����水ťλ��
    int btn_x = 325;                         
    int[] btn_y = { 350, 400, 450, 500, 550 };
    // �����С
    int width = 800, height = 650;
    String rule = "1. ��10*10�������ϣ�ÿ�����ĸ����ӣ��ĸ�Amazons����\n" +
    "2. ÿ�����Ӷ��൱�ڹ��������еĻʺ����ǵ����巽����ʺ���ͬ��\n" + 
    "   �����ڰ˸��������������ߣ������ܴ����谭��\n" +
    "3. ���ֵ�һ������ʱ���˷�ֻ�ܶ��ұ����ƶ��ĸ�Amazons�е�һ���������ƶ���ɺ�\n" +
    "   �ɵ�ǰ�ƶ��������ͷ�һ���ϰ����ϰ����ͷŷ��������ӵ��ƶ�������ͬ���ʺ���߷���\n" +
    "   ���ܴ����ϰ�����ͬ���ϰ��ķ���Ҳ�Ǳ���ģ�\n"+
    "4. ��ĳ�����ĳ���ƶ��󣬶Է��ĸ����Ӿ��������ƶ�ʱ���Է������������\n"+
    "5. ÿ�ο���λ�������·���������֣�\n"+
    "6. ����������˫�������ܳԵ��Է��򼺷������ӻ��ϰ���";
    String msg = "         ����ɹ�";	
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

    // ���ø�����������ʽ
    Font btn_font = new Font("����", Font.PLAIN, 20);
    Font text_font = new Font("����", Font.BOLD, 14);
    Font rule_font = new Font("����", Font.PLAIN, 18);
}
