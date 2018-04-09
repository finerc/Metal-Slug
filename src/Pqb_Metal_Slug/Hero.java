package Pqb_Metal_Slug;

public class Hero extends Substance{
	
	public static final int Hero_initial_x = 50;
	
	public static final int Hero_initial_y = 400;
	
	int speed = 2;
	
	int cur_image = -1;
	
	int death_image = -1;
	
	int jump_image = -1;
	
	int squat_image = -1;
	
	boolean isSquating = false;
	
	
	
	public Hero(){
		image = Launcher.heros[0];
		cur_image = 0;
		x_pos = Hero_initial_x;
		y_pos = Hero_initial_y;
		width = image.getWidth();
		height = image.getHeight();
		health_point = 3;
	}

	//英雄走路的动画（8张图切换），在Launcher中10ms刷新一次，因此在这里再*10即100ms刷新一次
	@Override
	public void step() {
		 //TODO 自动生成的方法存根
		if(!isSquating)
		{
			cur_image = (cur_image+1)%80;
			image = Launcher.heros[cur_image/10];
		}else 
		{
			squat_image = (squat_image+1)%50;
			image = Launcher.heroSquat[squat_image/10];
		}
	}
	
	public void stepReverse() {
		 //TODO 自动生成的方法存根
		if(!isSquating)
		{
			cur_image = (cur_image+1)%80;
			image = Launcher.reverseheros[cur_image/10];
		}else 
		{
			squat_image = (squat_image+1)%50;
			image = Launcher.reverseheroSquat[squat_image/10];
		}
	}
	
	public void revive()
	{
		this.x_pos = Hero_initial_x;
		this.y_pos = Hero_initial_y;
		death_image = -1;
		cur_image = 0;
		image = Launcher.heros[cur_image/2];
	}
	
	public boolean Death()		//死亡特效
	{
		death_image = (death_image+1)%220;
		image = Launcher.heroDeath[death_image/20];
		this.y_pos = Hero_initial_y + height - image.getHeight()-10;
		if(death_image==219)
			return true;
		return false;
	}
	
	public boolean Jump()		//英雄跳跃特效
	{
		jump_image = (jump_image+1)%100;
		image = Launcher.heroJump[jump_image/20];
		if(jump_image/16==0||jump_image/16==4)
			this.y_pos = Hero_initial_y-30;
		else if(jump_image/16==1||jump_image/16==3)
			this.y_pos = Hero_initial_y-70;
		else if(jump_image/16==2)
			this.y_pos = Hero_initial_y-100;
		if(jump_image==79)
		{
			cur_image = 0;
			image = Launcher.heros[cur_image/2];
			return true;
		}
		return false;
	}
	
	public boolean RightJump()
	{
		jump_image = (jump_image+1)%100;
		image = Launcher.heroJump[jump_image/20];
		switch(jump_image/16)
		{
		case 0:
			this.y_pos = Hero_initial_y-50;
			this.x_pos+=20;
			break;
		case 1:
			this.y_pos = Hero_initial_y-90;
			this.x_pos+=40;
			break;
		case 2:
			this.y_pos = Hero_initial_y-120;
			this.x_pos+=60;
			break;
		case 3:
			this.y_pos = Hero_initial_y-90;
			this.x_pos+=80;
			break;
		case 4:
			this.y_pos = Hero_initial_y-50;
			this.x_pos+=100;
			break;
		}
		if(jump_image==79)
		{
			cur_image = 0;
			image = Launcher.heros[cur_image/2];
			return true;
		}
		return false;
	}
	
	//控制英雄左右移动的方法，speed参数可控
	public void moveRight()
	{
		if(!isSquating)
			x_pos = x_pos + speed;
		else
			x_pos = x_pos + speed/2;
	}
	
	public void moveLeft()
	{
		if(!isSquating)
			x_pos = x_pos - speed;
		else
			x_pos = x_pos - speed/2;
	}

	@Override
	public boolean outOfLeftBounds() {
		// TODO 自动生成的方法存根
		return x_pos<=0;
	}

	@Override
	public boolean outOfRightBounds() {
		// TODO 自动生成的方法存根
		return false;
	}
}
