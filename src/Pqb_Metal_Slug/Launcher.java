package Pqb_Metal_Slug;

import java.util.*;
import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.event.*;  
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;

import Pqb_Metal_Slug.Launcher;
import Pqb_Metal_Slug.Launcher.State;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Launcher extends JPanel{
	//����ͼƬ�Ĵ�С863*560
    public static final int WIDTH = 863;  
    public static final int HEIGHT = 560;  
    //��Ϸ����̶���С880*605  
    public static final int FRAME_WIDTH = 880;  
    public static final int FRAME_HEIGHT = 605;  
    
    public static BufferedImage[] heros = new BufferedImage[8];
    public static BufferedImage[] reverseheros = new BufferedImage[8];
    public static BufferedImage[] heroDeath = new BufferedImage[11];
    public static BufferedImage[] heroJump = new BufferedImage[5];
    public static BufferedImage[] first_enemies = new BufferedImage[2];
    public static BufferedImage[] second_enemies = new BufferedImage[6];
    public static BufferedImage[] reverse_second_enemies = new BufferedImage[6];
    public static BufferedImage[] enemyAssassinate = new BufferedImage[3];
    public static BufferedImage background;
    public static BufferedImage slug;
    public static BufferedImage pause;
    public static BufferedImage start;
    public static BufferedImage gameover;
    
    
    enum Hero_State{
    	right_slugs, no_slugs, left_slugs
    }
    
    Hero_State hero_state = Hero_State.right_slugs;
    
    //��Ϸ״̬
    enum State{
    	start, pause, game_over, running
    }
    
    Boolean isDeath = false; 	//�ж�һ����������ʧ
    Boolean isJumping = false;	//�ж��Ƿ�����Ծ;
    State current_state = State.start;
    public Hero hero = new Hero();
    Slug []slugs = {};
    Enemy []enemies = {};
    
    //��̬����������̬�����ĳ�ʼ�����ھ�̬���ڱ���
    static{  
	    try{
		    heros[0] = ImageIO.read(Launcher.class.getResource("/images/hero-3.png"));
		    heros[1] = ImageIO.read(Launcher.class.getResource("/images/hero-2.png"));
		    heros[2] = ImageIO.read(Launcher.class.getResource("/images/hero-1.png"));
		    heros[3] = ImageIO.read(Launcher.class.getResource("/images/hero0.png"));
		    heros[4] = ImageIO.read(Launcher.class.getResource("/images/hero1.png"));
		    heros[5] = ImageIO.read(Launcher.class.getResource("/images/hero2.png"));
		    heros[6] = ImageIO.read(Launcher.class.getResource("/images/hero3.png"));
		    heros[7] = ImageIO.read(Launcher.class.getResource("/images/hero4.png"));
		    reverseheros[0] = ImageIO.read(Launcher.class.getResource("/images/hero-3_2.png"));
		    reverseheros[1] = ImageIO.read(Launcher.class.getResource("/images/hero-2_2.png"));
		    reverseheros[2] = ImageIO.read(Launcher.class.getResource("/images/hero-1_2.png"));
		    reverseheros[3] = ImageIO.read(Launcher.class.getResource("/images/hero0_2.png"));
		    reverseheros[4] = ImageIO.read(Launcher.class.getResource("/images/hero1_2.png"));
		    reverseheros[5] = ImageIO.read(Launcher.class.getResource("/images/hero2_2.png"));
		    reverseheros[6] = ImageIO.read(Launcher.class.getResource("/images/hero3_2.png"));
		    reverseheros[7] = ImageIO.read(Launcher.class.getResource("/images/hero4_2.png"));
		    heroDeath[0] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath0.png"));
		    heroDeath[1] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath1.png"));
		    heroDeath[2] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath2.png"));
		    heroDeath[3] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath3.png"));
		    heroDeath[4] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath4.png"));
		    heroDeath[5] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath5.png"));
		    heroDeath[6] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath6.png"));
		    heroDeath[7] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath7.png"));
		    heroDeath[8] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath8.png"));
		    heroDeath[9] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath9.png"));
		    heroDeath[10] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath10.png"));
		    heroJump[0] = ImageIO.read(Launcher.class.getResource("/images/HeroJump0.png"));
		    heroJump[1] = ImageIO.read(Launcher.class.getResource("/images/HeroJump1.png"));
		    heroJump[2] = ImageIO.read(Launcher.class.getResource("/images/HeroJump2.png"));
		    heroJump[3] = ImageIO.read(Launcher.class.getResource("/images/HeroJump3.png"));
		    heroJump[4] = ImageIO.read(Launcher.class.getResource("/images/HeroJump4.png"));
		    first_enemies[0]  = ImageIO.read(Launcher.class.getResource("/images/enemy_1_0.png"));
		    first_enemies[1]  = ImageIO.read(Launcher.class.getResource("/images/enemy_1_1.png"));
		    second_enemies[0]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_-2.png"));
		    second_enemies[1]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_-1.png"));
		    second_enemies[2]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_0.png"));
		    second_enemies[3]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_1.png"));
		    second_enemies[4]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_2.png"));
		    second_enemies[5]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_3.png"));
		    reverse_second_enemies[0]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_-2.png"));
		    reverse_second_enemies[1]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_-1.png"));
		    reverse_second_enemies[2]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_0.png"));
		    reverse_second_enemies[3]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_1.png"));
		    reverse_second_enemies[4]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_2.png"));
		    reverse_second_enemies[5]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_3.png"));
		    enemyAssassinate[0] = ImageIO.read(Launcher.class.getResource("/images/EnemyAssassinate0.png"));
		    enemyAssassinate[1] = ImageIO.read(Launcher.class.getResource("/images/EnemyAssassinate1.png"));
		    enemyAssassinate[2] = ImageIO.read(Launcher.class.getResource("/images/EnemyAssassinate2.png"));
		    background = ImageIO.read(Launcher.class.getResource("/images/background.png"));
		    slug = ImageIO.read(Launcher.class.getResource("/images/slug.png"));
		    /*pause = ImageIO.read(Launcher.class.getResource("pause.png"));*/
		    start = ImageIO.read(Launcher.class.getResource("/images/start.png"));
		    gameover = ImageIO.read(Launcher.class.getResource("/images/gameover.png"));
	    }catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
    }
    
    public static void main(String args[])
    {	
    	
    	//���ô������ơ�Ĭ�Ϲرշ�ʽ����С��λ��
    	JFrame frame = new JFrame("�Ͻ�ͷ--By ƽ����");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setAlwaysOnTop(true); //���ô����ö�  
    	frame.setLocationRelativeTo(null);
    	frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
    	//�м�����Jpanel
    	final Launcher Iden = new Launcher();
    	frame.add(Iden);
    	frame.setVisible(true);
    	Iden.launch();
    	frame.addKeyListener(new KeyAdapter(){
    		public void keyPressed(KeyEvent e) {
    	        Iden.processParentEvent(e);
    	    }
    	});
    }
    
    public void launch()
    {
    	//���ü��̼����¼�
    	KeyAdapter myKeyAdapter = new KeyAdapter(){
    		@Override
    		public void keyPressed(KeyEvent e)
    		{
    			int keycode = e.getKeyCode();
    			if(keycode==KeyEvent.VK_ENTER&&current_state!=State.running){			//���»س�������running״̬
    				init_data();
    				current_state = State.running;
    			}else if(keycode==KeyEvent.VK_SPACE){	//���¿ո��������ͣ״̬
    				current_state = State.pause;
    			}else if(keycode==KeyEvent.VK_D&&current_state==State.running&&!isDeath)		//����D��״̬ΪRunningʱӢ�������ƶ�
    			{
    				hero.step();
    				if(!BlockRight())		//ֻ�е��ұ����谭ʱ������
    					hero.moveRight();
    				hero_state = Hero_State.right_slugs;							//�������ң� �ӵ���������
    			}else if(keycode==KeyEvent.VK_A&&current_state==State.running&&!isDeath)		//����A��״̬ΪRunningʱӢ�������ƶ�
    			{
    				hero.stepReverse();
    				if(!BlockLeft())		//ֻ�е�������谭ʱ������
    					hero.moveLeft();
    				hero_state = Hero_State.left_slugs;								//���������ӵ���������
    			}else if(keycode==KeyEvent.VK_J&&current_state==State.running)
    			{
    				Slug []newslug = new Slug[1];
    				if(hero_state == Hero_State.right_slugs)
    					newslug[0] = new Slug((hero.x_pos+hero.width/2),(hero.y_pos+hero.height/2-10),true);	//�ӵ���������
    				else if(hero_state == Hero_State.left_slugs)
    					newslug[0] = new Slug((hero.x_pos+hero.width/2),(hero.y_pos+hero.height/2-10),false);	//�ӵ���������
    				slugs = Arrays.copyOf(slugs, slugs.length + newslug.length);  
    			    //��newBullets�����п�������Ԫ�ص�bullets����ĩβ  
    			    System.arraycopy(newslug, 0, slugs, slugs.length - newslug.length, newslug.length); 
    			}else if(keycode==KeyEvent.VK_K&&current_state==State.running)
    			{
    				isJumping = true;
    			}else if(keycode==KeyEvent.VK_D&&keycode==KeyEvent.VK_J&&current_state==State.running&&!isDeath)
    			{
    				hero.step();
    				if(!BlockRight())		//ֻ�е��ұ����谭ʱ������
    					hero.moveRight();
    				hero_state = Hero_State.right_slugs;	
    				Slug []newslug = new Slug[1];
    				if(hero_state == Hero_State.right_slugs)
    					newslug[0] = new Slug((hero.x_pos+hero.width/2),(hero.y_pos+hero.height/2-10),true);	//�ӵ���������
    				else if(hero_state == Hero_State.left_slugs)
    					newslug[0] = new Slug((hero.x_pos+hero.width/2),(hero.y_pos+hero.height/2-10),false);	//�ӵ���������
    				slugs = Arrays.copyOf(slugs, slugs.length + newslug.length);  
    			    //��newBullets�����п�������Ԫ�ص�bullets����ĩβ  
    			    System.arraycopy(newslug, 0, slugs, slugs.length - newslug.length, newslug.length); 
    			}
    		}
    	};
        this.addKeyListener(myKeyAdapter);
        
    	//������ʱ��
    	 Timer timer = new Timer();  
    	 timer.schedule(new TimerTask(){
    		 private int runTimes = 0;

    		 //Ҫ��ʲô��--�����㷨
			public void run() {
				// TODO �Զ����ɵķ������
				if(current_state==State.running)
				{
					runTimes++;
					
					/*if(runTimes%100==0)
					{	
						createAnEnemy();
					}*/
					
					EnemyStep();
					
					moveAllEnemies();
					
					moveAllSlugs();
					
					SlugHit();
					
					if(isJumping)
					{
						if(hero.Jump())
						{
							isJumping = false;
							System.out.println("jumping");
							hero.y_pos = hero.Hero_initial_y;
						}
					}
					
    				if(hero.health_point == 0)			//Ѫ�����㣬game over
    				{
    					if(hero.Death())
    						current_state = State.game_over;
    				}else if(isDeath)
    				{
    					if(hero.Death())
    					{
    						hero.revive();    	
    						isDeath = false;
    					}
    				}
					
					outOfBounds();
				}
				repaint();
			}
    	 },5, 5);
    	
    }
    
    public void init_data()
    {
    	isDeath = false;
    	isJumping = false;
    	hero = new Hero();
    	slugs = new Slug[0];
    	enemies = new Enemy[0];
    	init_enemy();
    }
    
    public void processParentEvent(AWTEvent e){
        this.processEvent(e);
    }
    
  //����ͬ״̬��ʾ��ͬ�Ľ���
    @Override
    public void paint(Graphics g)
    {
    	g.drawImage(background, 0, 0, null);
    	paintHero(g);
    	paintSlug(g);
    	paintEnemy(g);
    	if(current_state==State.game_over)
    		g.drawImage(gameover,255,0,null);
    	if(current_state==State.start)
    		g.drawImage(start,245,180,null);
    }
    
    //��ʼ�����˶���
    private void init_enemy()
    {
    	Enemy []T_enemies = new Enemy[2];
    	T_enemies[0] = new Enemy(800,Hero.Hero_initial_y+hero.height-first_enemies[0].getHeight()-10,0,1,false);
    	T_enemies[1] = new Enemy(800,Hero.Hero_initial_y+hero.height-second_enemies[0].getHeight()-10,1,2,false);
    	enemies = Arrays.copyOf(enemies, enemies.length + T_enemies.length);  
	    //��newBullets�����п�������Ԫ�ص�bullets����ĩβ  
	    System.arraycopy(T_enemies, 0, enemies, enemies.length - T_enemies.length, T_enemies.length); 
    }
    
    //��ʾ�����ҷ��ӵ�
    private void paintSlug(Graphics g) {
		// TODO �Զ����ɵķ������
		for(Slug slug_:slugs){
			g.drawImage(slug_.image, slug_.x_pos,slug_.y_pos,null);
		}
	}
    
    //��ʾ���е���
    private void paintEnemy(Graphics g) {
		// TODO �Զ����ɵķ������
		for(Enemy enemy_:enemies){
			g.drawImage(enemy_.image, enemy_.x_pos,enemy_.y_pos,null);
		}
	}
    
    public void paintHero(Graphics g)
    {
    	g.drawImage(hero.image, hero.x_pos, hero.y_pos, null);
    }
    
    //���е��˵��ƶ�
    public void moveAllEnemies()
    {
    	for(Enemy enemy_:enemies){
    		if(enemy_.x_pos>hero.x_pos+hero.width)			//ʼ�ճ���Ӣ�۷����ƶ�
    		{
    			enemy_.move();
    			enemy_.AssassinatedCount2 = 0;		//Ӣ�۶���˴�ɱ
    		}
    		else if(enemy_.x_pos+enemy_.width<hero.x_pos)		//
    		{
    			enemy_.moveReverse();  	
    			enemy_.AssassinatedCount2 = 0;
    		}
    		else
    		{
    			enemy_.Assassinate(); 		//̫�ӽ��ˣ������õ���ɱ
    			if(!isDeath)				//Ӣ���������ټ���
    				enemy_.AssassinatedCount2++;
    			//System.out.println(enemy_.AssassinatedCount2);
    			if(enemy_.AssassinatedCount2 == 200)				//���һ�δ�ɱ
    			{
    				if(hero.health_point>0)
    					hero.health_point--;
    				enemy_.AssassinatedCount2 = 0;
    				isDeath = true;
    			}
    				
    		}
		}
    }
    
    //���е��˵Ķ���
    public void EnemyStep()
    {
    	for(Enemy enemy_:enemies){
			enemy_.step();
		}
    }
    
	//�����ӵ����ƶ�
	public void moveAllSlugs()
	{
		for(Slug slug_:slugs){
			slug_.step();
		}
	}
	
	//����һ������
	public void createAnEnemy()
	{
		Enemy en = new Enemy(800,Hero.Hero_initial_y+hero.height-first_enemies[0].getHeight()-10,3,1,false);
		 //��enemies��������1  
		enemies = Arrays.copyOf(enemies,enemies.length + 1);  
        //���µ��˷�������ĩβ  
		enemies[enemies.length - 1] = en;  
	}
	
	//���Ӣ���ҷ�Block
	public boolean BlockRight()
	{
		for(Enemy enemy_:enemies)
			if((enemy_.x_pos>=hero.x_pos)&&(enemy_.x_pos-hero.x_pos<10))
				return true;
		return false;
	}
	
	public boolean BlockLeft()
	{
		for(Enemy enemy_:enemies)
			if((hero.x_pos>enemy_.x_pos)&&(hero.x_pos-enemy_.x_pos<10))
				return true;
		return false;
	}
	
	//����ӵ��͵�����ײ
	public void SlugHit()
	{
		for(int i=0;i<slugs.length;i++)
		{
			for(int j=0;j<enemies.length;j++)
			{
				if(Substance.hit(slugs[i], enemies[j]))
				{
					//�ֱ�ʹ�õ��˺��ӵ������һ��ʵ�������ײλ��
					enemies[j].health_point--;
					if(enemies[j].health_point==0)
					{
						enemies[j] = enemies[enemies.length-1];
						enemies = Arrays.copyOf(enemies,enemies.length-1);
					}
					
					slugs[i] = slugs[slugs.length-1];
					slugs = Arrays.copyOf(slugs, slugs.length-1);
					
					i--;	//���¼�⵱ǰλ�õ��ӵ����µģ�
					break;   //��Ȼһ���ӵ�����������������
				}
			}
		}
			
	}
	
	
	//������е��˺��ӵ��Ƿ�Խ��
	public void outOfBounds(){
		//������е����Ƿ�Խ��
		Enemy []EnemyLives = new Enemy[enemies.length];
		int index = 0;
		for(Enemy enemy_:enemies){
			if(!enemy_.outOfLeftBounds())
			{
				EnemyLives[index++]=enemy_;
			}
		}
		enemies = Arrays.copyOf(EnemyLives, index);
		
		//��������ӵ��Ƿ�Խ��
		Slug []SlugLives = new Slug[slugs.length];
		index = 0;
		for(Slug slug_:slugs){
			if(!slug_.outOfRightBounds()&&!slug_.outOfLeftBounds())
			{
				SlugLives[index++] = slug_;
			}
		}
		slugs = Arrays.copyOf(SlugLives,index);
	}
	
}