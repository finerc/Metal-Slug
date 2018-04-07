package Pqb_Metal_Slug;

import java.awt.image.BufferedImage;

public class Enemy extends Substance{
	
	int speed;
	
	int enemy_kind;
	
	int AssassinatedCount = 0;   //��ɱ����������
	
	int AssassinatedCount2 = 0;  //��ɱ�ж�������
	
	protected boolean direction;
	
	protected int assassinated;
	
	int cur_image;
	
	public Enemy(int x, int y, int _speed, int _enemy_kind, boolean _direction){
		x_pos = x;
		y_pos = y;
		enemy_kind = _enemy_kind;
		if(enemy_kind == 1)
			image = Launcher.first_enemies[0];
		else if(enemy_kind == 2)
			image = Launcher.second_enemies[0];
		width = image.getWidth();
		height = image.getHeight();
		cur_image = 0;
		health_point = 3;
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
				x_pos = x_pos - speed;
			direction = false;
		}
	}
	
	public void moveReverse()
	{
		if(enemy_kind == 2)	//���ڵڶ���ʿ��
		{
			if(assassinated==0)		
				x_pos = x_pos + speed;
			direction = true;
		}
	}
	
	public void Assassinate()
	{
		assassinated = 1;
		if(AssassinatedCount==0)
		{
			cur_image = 0;
		}
	}
	
	public void AssassinateReverse()
	{
		assassinated = -1;
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
