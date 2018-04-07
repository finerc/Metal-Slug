package Pqb_Metal_Slug;

import Pqb_Metal_Slug.Substance;;

public class Slug extends Substance{
	static int speed = 20;
	
	protected boolean direction;		//direction = true的话子弹往右， direction =false的话子弹往左
	
	public Slug(int x,int y, boolean _direction)
	{
		x_pos = x;
		y_pos = y;
		image = Launcher.slug;
		width = image.getWidth();
		height = image.getHeight();
		health_point = 1;
		direction = _direction;
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
