package Pqb_Metal_Slug;

import java.util.Random;

public class Enemy extends Substance{
	
	int initial_y_pos;
	
	int speed;
	
	int enemy_kind;
	
	int AssassinatedCount = 0;   //��ɱ����������
	
	int AssassinatedCount2 = 0;  //��ɱ�ж�������
	
	int death_image = -1;
	
	protected boolean direction;
	
	protected int assassinated;
	
	int cur_image;
	
	public Enemy(int x, int y, int _speed, int _enemy_kind, boolean _direction){
		x_pos = x;
		y_pos = y;
		initial_y_pos = y;
		enemy_kind = _enemy_kind;
		if(enemy_kind == 1)
			image = Launcher.first_enemies[0];
		else if(enemy_kind == 2)
			image = Launcher.second_enemies[0];
		width = image.getWidth();
		height = image.getHeight();
		cur_image = 0;
		health_point = 20;
		speed = _speed;
		direction = _direction;		
		assassinated = 0;		//һ��ʼ����ɱ
	}
	
	@Override
	public void step() {
		// TODO �Զ����ɵķ������ 
		if(assassinated == 1)			//��ȡ��ɱ�ж�
		{
			AssassinatedCount++;
			cur_image = (cur_image+1)%240;
			if(direction)
				image = Launcher.reverse_enemyAssassinate[cur_image/80];
			else
				image = Launcher.enemyAssassinate[cur_image/80];
			if(AssassinatedCount==240)
			{
				AssassinatedCount = 0;
				assassinated = 0;
			}
			return;
		}
		if(enemy_kind == 1){
			cur_image = (cur_image+1)%80;
			image = Launcher.first_enemies[cur_image/40];
		}else if(enemy_kind == 2){
			cur_image = (cur_image+1)%90;
			if(direction == false)
				image = Launcher.second_enemies[cur_image/15];
			else
				image = Launcher.reverse_second_enemies[cur_image/15];
		}
	}
	
	public void move()
	{
		if(enemy_kind == 2)	//���ڵڶ���ʿ��
		{
			if(assassinated==0)			//��ɱʱͣ��ԭ��
			{
				Random r = new Random();
				Random s = new Random(10);
				if(s.nextInt(2)==1)
					x_pos = x_pos - r.nextInt(speed+1);;
			}
			direction = false;
		}
	}
	
	public Slug[] shoot()
	{
		if(enemy_kind == 1)				//��һ��ʿ���������
			return new Slug[0];
		Random r = new Random();
		if(r.nextInt(2)==1)
		{
			Slug []slugs = new Slug[1];
			if(direction==true)
				slugs[0] = new Slug((x_pos+width/2)+50,(y_pos+height/2-10),true, false, 1);
			else
				slugs[0] = new Slug((x_pos+width/2)-50,(y_pos+height/2-10),false, false, 1);
			return slugs;
		}
		return new Slug[0];
	}
	
	public void moveReverse()
	{
		if(enemy_kind == 2)	//���ڵڶ���ʿ��
		{
			if(assassinated==0)		
			{
				Random r = new Random();
				x_pos = x_pos + r.nextInt(speed+1);;
			}
			direction = true;
		}
	}
	
	public void Assassinate(boolean ass_direction)
	{
		assassinated = 1;
		direction = ass_direction;
		if(AssassinatedCount==0)
		{
			cur_image = 0;
		}
	}
	
	public void AssassinateReverse()
	{
		assassinated = -1;
	}
	
	public boolean Death()
	{
		death_image = (death_image+1)%100;
		image = Launcher.enemyDeath[death_image/20];
		this.y_pos = initial_y_pos + height - image.getHeight()-10;
		if(death_image==99)
			return true;
		return false;
	}

	@Override
	public boolean outOfLeftBounds() {
		// TODO �Զ����ɵķ������
		return x_pos<=-20;
	}

	@Override
	public boolean outOfRightBounds() {
		// TODO �Զ����ɵķ������
		return false;
	}

}
