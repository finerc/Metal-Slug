package Pqb_Metal_Slug;

import Pqb_Metal_Slug.Substance;;

public class Slug extends Substance{
	int speed;
	
	protected boolean direction;		//direction = true的话子弹往右， direction =false的话子弹往左
	
	public Slug(int x,int y, boolean _direction, boolean Kinds, int _speed) 	//true为英雄子弹，false为敌人子弹
	{
		x_pos = x;
		y_pos = y;
		if(Kinds==true)
			image = Launcher.slug;
		else
			image = Launcher.enemyslug;
		width = image.getWidth();
		height = image.getHeight();
		health_point = 1;
		direction = _direction;
		speed = _speed;
	}

	@Override
	public void step() {
		// TODO 自动生成的方法存根
		if(direction == true)
			x_pos += speed;
		else
			x_pos -= speed;
	}

	@Override
	public boolean outOfLeftBounds() {
		// TODO 自动生成的方法存根
		return x_pos<=0;
	}

	@Override
	public boolean outOfRightBounds() {
		// TODO 自动生成的方法存根
		return x_pos+ width >= 850;
	}
}
