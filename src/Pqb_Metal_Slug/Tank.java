package Pqb_Metal_Slug;

import java.util.Random;

public class Tank extends Substance{
	
	int cur_image;
	
	public Tank(int x, int y){
		x_pos = x;
		y_pos = y;
		cur_image = 0;
		image = Launcher.tank[cur_image];
		width = image.getWidth();
		height = image.getHeight();
		health_point = 30;
	}

	@Override
	public void step() {
		// TODO 自动生成的方法存根
		Random r = new Random();
		cur_image = (cur_image+1)%200;
		image = Launcher.tank[cur_image/100];
	}

	@Override
	public boolean outOfLeftBounds() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean outOfRightBounds() {
		// TODO 自动生成的方法存根
		return false;
	}

}
