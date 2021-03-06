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
	//背景图片的大小863*560
    public static final int WIDTH = 863;  
    public static final int HEIGHT = 560;  
    //游戏界面固定大小880*605  
    public static final int FRAME_WIDTH = 863;  
    public static final int FRAME_HEIGHT = 560;  
    
    public static BufferedImage[] heros = new BufferedImage[8];
    public static BufferedImage[] reverseheros = new BufferedImage[8];
    public static BufferedImage[] heroDeath = new BufferedImage[11];
    public static BufferedImage[] heroJump = new BufferedImage[5];
    public static BufferedImage[] heroSquat = new BufferedImage[5];
    public static BufferedImage[] reverseheroSquat = new BufferedImage[5];
    public static BufferedImage[] first_enemies = new BufferedImage[2];
    public static BufferedImage[] second_enemies = new BufferedImage[6];
    public static BufferedImage[] reverse_second_enemies = new BufferedImage[6];
    public static BufferedImage[] enemyAssassinate = new BufferedImage[3];
    public static BufferedImage[] enemyDeath = new BufferedImage[5];
    public static BufferedImage[] reverse_enemyAssassinate = new BufferedImage[3];
    public static BufferedImage[] tank = new BufferedImage[2];
    public static BufferedImage slug;
    public static BufferedImage enemyslug;
    public static BufferedImage background; 
    public static BufferedImage staterow;
    public static BufferedImage[] lifenum = new BufferedImage[3];
    public static BufferedImage pause;
    public static BufferedImage start;
    public static BufferedImage gameover;
    
    
    enum Hero_State{
    	right_slugs, no_slugs, left_slugs
    }
    
    Hero_State hero_state = Hero_State.right_slugs;
    
    //游戏状态
    enum State{
    	start, pause, game_over, running
    }
    
    Boolean isDeath = false; 	//判定一次生命的消失
    Boolean isJumping = false;	//判定是否在跳跃;
    Boolean Right = false, Left = false, Up = false, Shooting = false;
    State current_state = State.start;
    public Hero hero = new Hero();
    Slug []slugs = {};
    Slug []enemyslugs = {};
    Enemy []enemies = {};
    Tank []tanks = {};
    
    //静态区，不将静态变量的初始化置于静态区内报错
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
		    heroSquat[0] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat1.png"));
		    heroSquat[1] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat2.png"));
		    heroSquat[2] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat3.png"));
		    heroSquat[3] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat4.png"));
		    heroSquat[4] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat5.png"));
		    reverseheroSquat[0] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat1.png"));
		    reverseheroSquat[1] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat2.png"));
		    reverseheroSquat[2] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat3.png"));
		    reverseheroSquat[3] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat4.png"));
		    reverseheroSquat[4] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat5.png"));
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
		    reverse_enemyAssassinate[0] = ImageIO.read(Launcher.class.getResource("/images/reverseEnemyAssassinate0.png"));
		    reverse_enemyAssassinate[1] = ImageIO.read(Launcher.class.getResource("/images/reverseEnemyAssassinate1.png"));
		    reverse_enemyAssassinate[2] = ImageIO.read(Launcher.class.getResource("/images/reverseEnemyAssassinate2.png"));
		    enemyDeath[0] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath0.png"));
		    enemyDeath[1] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath1.png"));
		    enemyDeath[2] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath2.png"));
		    enemyDeath[3] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath3.png"));
		    enemyDeath[4] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath4.png"));
		    tank[0] = ImageIO.read(Launcher.class.getResource("/images/tank1.png"));
		    tank[1] = ImageIO.read(Launcher.class.getResource("/images/tank2.png"));
		    background = ImageIO.read(Launcher.class.getResource("/images/background.png"));
		    staterow = ImageIO.read(Launcher.class.getResource("/images/StateRow0.png"));
		    lifenum[0] = ImageIO.read(Launcher.class.getResource("/images/life_0.png"));
		    lifenum[1] = ImageIO.read(Launcher.class.getResource("/images/life_1.png"));
		    lifenum[2] = ImageIO.read(Launcher.class.getResource("/images/life_2.png"));
		    slug = ImageIO.read(Launcher.class.getResource("/images/slug.png"));
		    enemyslug = ImageIO.read(Launcher.class.getResource("/images/enemySlug.png"));
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
    	
    	//设置窗体名称、默认关闭方式、大小、位置
    	JFrame frame = new JFrame("合金弹头--By 平琦斌");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setAlwaysOnTop(true); //设置窗体置顶  
    	frame.setLocationRelativeTo(null);
    	frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
    	//中间容器Jpanel
    	final Launcher Iden = new Launcher();
    	frame.add(Iden);
    	frame.setVisible(true);
    	frame.setResizable(false);
    	Iden.launch();
    	frame.addKeyListener(new KeyAdapter(){
    		public void keyPressed(KeyEvent e) {
    	        Iden.processParentEvent(e);
    	    }
    		
    		public void keyReleased(KeyEvent e) {
    	        Iden.processParentEvent(e);
    	    }
    	});
    }
    
    public void launch()
    {
    	//设置键盘监听事件
    	KeyAdapter myKeyAdapter = new KeyAdapter(){
    		
    		@Override
    		public void keyPressed(KeyEvent e)
    		{
    			int keycode = e.getKeyCode();
    			if(keycode==KeyEvent.VK_ENTER&&current_state!=State.running){			//按下回车键进入running状态
    				init_data();
    				current_state = State.running;
    			}else if(keycode==KeyEvent.VK_D)		//按下D且状态为Running时英雄向右移动
    			{
    				Right = true;
    			}else if(keycode==KeyEvent.VK_A)		//按下A且状态为Running时英雄向左移动
    			{
    				Left = true;
    			}else if(keycode==KeyEvent.VK_J&&current_state==State.running&&Shooting==false&&!isDeath)
    			{
    				Shooting = true;
    				Slug []newslug = new Slug[1];
    				if(hero_state == Hero_State.right_slugs)
    					newslug[0] = new Slug((hero.x_pos+hero.width/2),(hero.y_pos+hero.height/2-10),true, true, 5);	//子弹方向往右, 英雄子弹	
    				else if(hero_state == Hero_State.left_slugs)	
    					newslug[0] = new Slug((hero.x_pos+hero.width/2),(hero.y_pos+hero.height/2-10),false, true, 5);	//子弹方向往左， 英雄子弹
    				slugs = Arrays.copyOf(slugs, slugs.length + newslug.length);  
    			    //从newBullets数组中拷贝所有元素到bullets数组末尾  
    			    System.arraycopy(newslug, 0, slugs, slugs.length - newslug.length, newslug.length); 
    			}else if(keycode==KeyEvent.VK_K&&current_state==State.running)
    			{
    				Up = true;
    				isJumping = true;
    			}else if(keycode==KeyEvent.VK_S&&current_state==State.running)
    			{
    				hero.isSquating = true;
    				hero.y_pos = hero.Hero_initial_y + 30;
    				hero.step(); 				
    			}
    		}
    		
    		@Override
    		public void keyReleased(KeyEvent e)
    		{	
    			int keycode = e.getKeyCode();
    			if(keycode==KeyEvent.VK_D)
    			{
    				Right = false;
    			}else if(keycode == KeyEvent.VK_A)
    			{
    				Left = false;
    			}
    			else if(keycode == KeyEvent.VK_K)
    			{
    				Up = false;
    			}else if(keycode == KeyEvent.VK_J)
    			{
    				Shooting = false;
    			}else if(keycode==KeyEvent.VK_S)
    			{
    				hero.isSquating = false;
    				hero.y_pos = hero.Hero_initial_y;
    				hero.step();
    			}
    		}
    	};
    	
    	//this.setFocusable(true);
        this.addKeyListener(myKeyAdapter);
        
    	//创建计时器
    	 Timer timer = new Timer();  
    	 timer.schedule(new TimerTask(){
    		 private int runTimes = 0;

    		 //要做什么事--核心算法
			public void run() {
				// TODO 自动生成的方法存根
				if(current_state==State.running)
				{
					runTimes++;
					
		    		if(Right==true&&current_state==State.running&&!isDeath)
		    		{
		    			hero.step();
	    				if(!BlockRight())		//只有当右边无阻碍时才能走
	    					hero.moveRight();
	    				hero_state = Hero_State.right_slugs;							//人物往右， 子弹方向往右
		    		}else if(Left==true&&current_state==State.running&&!isDeath&&!hero.outOfLeftBounds())
		    		{
		    			hero.stepReverse();
	    				if(!BlockLeft())		//只有当左边无阻碍时才能走
	    					hero.moveLeft();
	    				hero_state = Hero_State.left_slugs;								//人物往左，子弹方向往左
		    		}
					
		    		if(runTimes%500==0)
		    			EnemyShoot();
		    		
					EnemyStep();
					
					moveAllEnemies();
					
					moveAllSlugs();
					
					SlugHitEnemy();
					
					for(int j=0;j<enemies.length;j++)			//检测每个士兵的血量， 若士兵死亡则出现死亡特效
						if(enemies[j].health_point<=0)
						{
							if(enemies[j].Death())
							{
								enemies[j] = enemies[enemies.length-1];
								enemies = Arrays.copyOf(enemies,enemies.length-1);
							}
						}
					
					SlugHitHero();
					
					if(isJumping)
					{
						if(hero.Jump())
						{
							isJumping = false;
							hero.y_pos = hero.Hero_initial_y;
						}
					}
					
    				if(hero.health_point == 0)			//血量清零，game over
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
    	enemyslugs = new Slug[0];
    	enemies = new Enemy[0];
    	init_enemy();
    }
    
    public void processParentEvent(AWTEvent e){
        this.processEvent(e);
    }
    
  //按不同状态显示不同的界面
    @Override
    public void paint(Graphics g)
    {
    	g.drawImage(background, 0, 0, null);
    	g.drawImage(staterow,0,0,null);
    	if(hero.health_point>=1)
    		g.drawImage(lifenum[hero.health_point-1], 55, 10, null);
    	paintHero(g);
    	paintSlug(g);
    	paintEnemy(g);
    	if(current_state==State.game_over)
    		g.drawImage(gameover,255,0,null);
    	if(current_state==State.start)
    		g.drawImage(start,245,180,null);
    }
    
    //初始化敌人对象
    private void init_enemy()
    {
    	Enemy []T_enemies = new Enemy[2];
    	T_enemies[0] = new Enemy(790,Hero.Hero_initial_y+hero.height-first_enemies[0].getHeight()+50-tank[0].getHeight(),0,1,false);
    	T_enemies[1] = new Enemy(800,Hero.Hero_initial_y+hero.height-second_enemies[0].getHeight()-10,1,2,false);
    	enemies = Arrays.copyOf(enemies, enemies.length + T_enemies.length);  
	    //从newBullets数组中拷贝所有元素到bullets数组末尾  
	    System.arraycopy(T_enemies, 0, enemies, enemies.length - T_enemies.length, T_enemies.length); 
	    
	    Tank  []T_tanks = new Tank[1];
	    T_tanks[0] = new Tank(700,Hero.Hero_initial_y+hero.height-tank[0].getHeight());
	    tanks = Arrays.copyOf(tanks, tanks.length + T_tanks.length);
	    System.arraycopy(T_tanks, 0, tanks, tanks.length-T_tanks.length, T_tanks.length);
    }
    
    //显示所有我方子弹
    private void paintSlug(Graphics g) {
		// TODO 自动生成的方法存根
		for(Slug slug_:slugs){
			g.drawImage(slug_.image, slug_.x_pos,slug_.y_pos,null);
		}
		
		for(Slug slug_:enemyslugs){
			g.drawImage(slug_.image, slug_.x_pos,slug_.y_pos,null);
		}
	}
    
    //显示所有敌人
    private void paintEnemy(Graphics g) {
		// TODO 自动生成的方法存根
		for(Enemy enemy_:enemies){
			g.drawImage(enemy_.image, enemy_.x_pos,enemy_.y_pos,null);
		}
		for(Tank tank_:tanks){
			g.drawImage(tank_.image, tank_.x_pos,tank_.y_pos,null);
		}
	}
    
    public void paintHero(Graphics g)
    {
    	g.drawImage(hero.image, hero.x_pos, hero.y_pos, null);
    }
    
    //所有敌人的移动
    public void moveAllEnemies()
    {
    	for(Enemy enemy_:enemies){
    		if(enemy_.health_point<=0)
    			continue;
    		if(enemy_.x_pos>hero.x_pos+hero.width)			//始终朝着英雄方向移动
    		{
    			enemy_.move();
    			enemy_.AssassinatedCount2 = 0;		//英雄躲避了刺杀
    		}
    		else if(enemy_.x_pos+enemy_.width<hero.x_pos)		//
    		{
    			enemy_.moveReverse();  	
    			enemy_.AssassinatedCount2 = 0;
    		}
    		else
    		{
    			if(enemy_.x_pos>hero.x_pos)
    				enemy_.Assassinate(false); 		//太接近了，敌人用刀刺杀
    			else
    				enemy_.Assassinate(true);
    			if(!isDeath)				//英雄死亡不再计数
    				enemy_.AssassinatedCount2++;
    			//System.out.println(enemy_.AssassinatedCount2);
    			if(enemy_.AssassinatedCount2 == 200)				//完成一次刺杀
    			{
    				if(hero.health_point>0)
    					hero.health_point--;
    				enemy_.AssassinatedCount2 = 0;
    				isDeath = true;
    			}
    				
    		}
		}
    }
    
    //所有敌人的动画
    public void EnemyStep()
    {
    	for(Enemy enemy_:enemies){
			enemy_.step();
		}
    	for(Tank tank_:tanks){
			tank_.step();
		}
    }
    
    //持枪敌人射击
    public void EnemyShoot()
    {
    	for(Enemy enemy_:enemies){
    		if(Math.abs(enemy_.x_pos - hero.x_pos)<200)
    			continue;
    		Slug []T_enemyslugs = enemy_.shoot();
    		enemyslugs = Arrays.copyOf(enemyslugs, enemyslugs.length + T_enemyslugs.length);
    	    System.arraycopy(T_enemyslugs, 0, enemyslugs, enemyslugs.length-T_enemyslugs.length, T_enemyslugs.length);
    	}
    }
    
	//所有子弹的移动
	public void moveAllSlugs()
	{
		for(Slug slug_:slugs){
			slug_.step();
		}
		
		for(Slug slug_:enemyslugs){
			slug_.step();
		}
	}
	
	//生成一个敌人
	public void createAnEnemy()
	{
		Enemy en = new Enemy(800,Hero.Hero_initial_y+hero.height-first_enemies[0].getHeight()-10,3,1,false);
		 //对enemies数组扩容1  
		enemies = Arrays.copyOf(enemies,enemies.length + 1);  
        //将新敌人放入数组末尾  
		enemies[enemies.length - 1] = en;  
	}
	
	//检查英雄右方Block
	public boolean BlockRight()
	{
		for(Enemy enemy_:enemies)
			if((enemy_.x_pos>=hero.x_pos)&&(enemy_.x_pos-hero.x_pos<10)&&hero.y_pos+hero.height>=enemy_.y_pos)		//第三个判别式判定我方是否跳跃
				return true;
		return false;
	}
	
	public boolean BlockLeft()
	{
		for(Enemy enemy_:enemies)
			if((hero.x_pos>enemy_.x_pos)&&(hero.x_pos-enemy_.x_pos<10)&&hero.y_pos+hero.height>=enemy_.y_pos)
				return true;
		return false;
	}
	
	//检查子弹和敌人碰撞
	public void SlugHitEnemy()
	{
		for(int i=0;i<slugs.length;i++)
		{
			for(int j=0;j<enemies.length;j++)
			{
				if(Substance.hit(slugs[i], enemies[j]))
				{
					//分别使用敌人和子弹的最后一个实体来填补碰撞位置
					enemies[j].health_point--;
					
					slugs[i] = slugs[slugs.length-1];
					slugs = Arrays.copyOf(slugs, slugs.length-1);
					
					i--;	//重新检测当前位置的子弹（新的）
					break;   //显然一个子弹不会碰到两个敌人
				}
			}
		}	
	}
	
	//检查子弹和英雄碰撞
	public void SlugHitHero()
	{
		for(int i=0;i<enemyslugs.length;i++)
		{
				if(Substance.hit(enemyslugs[i], hero))
				{				
					if(hero.health_point>0)
					{
						hero.health_point--;
						isDeath = true;
					}
					enemyslugs[i] = enemyslugs[enemyslugs.length-1];
					enemyslugs = Arrays.copyOf(enemyslugs, enemyslugs.length-1);
					
					break;   //显然一个子弹不会碰到两个敌人
				}
		}
	}
	
	
	
	
	//检查所有敌人和子弹是否越界
	public void outOfBounds(){
		//检查所有敌人是否越界
		Enemy []EnemyLives = new Enemy[enemies.length];
		int index = 0;
		for(Enemy enemy_:enemies){
			if(!enemy_.outOfLeftBounds())
			{
				EnemyLives[index++]=enemy_;
			}
		}
		enemies = Arrays.copyOf(EnemyLives, index);
		
		//检查所有子弹是否越界
		Slug []SlugLives = new Slug[slugs.length];
		index = 0;
		for(Slug slug_:slugs){
			if(!slug_.outOfRightBounds()&&!slug_.outOfLeftBounds())
			{
				SlugLives[index++] = slug_;
			}
		}
		slugs = Arrays.copyOf(SlugLives,index);
		
		SlugLives = new Slug[enemyslugs.length];
		index = 0;
		for(Slug slug_:enemyslugs){
			if(!slug_.outOfRightBounds()&&!slug_.outOfLeftBounds())
			{
				SlugLives[index++] = slug_;
			}
		}
		enemyslugs = Arrays.copyOf(SlugLives,index);
	}
	
}