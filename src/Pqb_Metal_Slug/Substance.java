package Pqb_Metal_Slug;

import java.awt.image.BufferedImage;

public abstract class Substance {
	protected int x_pos;
	protected int y_pos;
	protected int width;
	protected int height;
	protected BufferedImage image;
	protected int health_point;    //Hp;
	
	/** 
     * ����ʵ�嶼���ƶ� 
     * �ƶ��ķ�ʽ�������Լ�ʵ�� 
     */  
    public abstract void step();  
      
    /** 
     * ���Խ��ķ��� 
     * @return �Ƿ�Խ�� 
     */  
    public abstract boolean outOfLeftBounds();  
    
    public abstract boolean outOfRightBounds();  
      
    /** 
     * �����������ʵ���Ƿ���ײ�ķ��� 
     * �;�������޹أ����Զ���Ϊ��̬���� 
     * @param f1 ʵ��1 
     * @param f2 ʵ��2 
     * @return �Ƿ���ײ 
     */  
    public static boolean hit(Substance s1,Substance s2){  
        //step1: ����������ε����ĵ�  
        int s1x = s1.x_pos + s1.width/2;  
        int s1y = s1.y_pos + s1.height/2;  
        int s2x = s2.x_pos + s2.width/2;  
        int s2y = s2.y_pos + s2.height/2;  
        //step2: �����������ײ���  
        boolean H = Math.abs(s1x - s2x) < (s1.width + s2.width)/2;  
        boolean V = Math.abs(s1y -s2y) < (s1.height + s2.height)/2;  
        //step3: ������������ͬʱ��ײ  
        return H&V;  
    }
}
