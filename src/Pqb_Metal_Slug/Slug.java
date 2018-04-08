package Pqb_Metal_Slug;

import Pqb_Metal_Slug.Substance;;

public class Slug extends Substance{
	int speed;
	
	protected boolean direction;		//direction = true�Ļ��ӵ����ң� direction =false�Ļ��ӵ�����
	
	public Slug(int x,int y, boolean _direction, boolean Kinds, int _speed) 	//trueΪӢ���ӵ���falseΪ�����ӵ�
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
		// TODO �Զ����ɵķ������
		if(direction == true)
			x_pos += speed;
		else
			x_pos -= speed;
	}

	@Override
	public boolean outOfLeftBounds() {
		// TODO �Զ����ɵķ������
		return x_pos<=0;
	}

	@Override
	public boolean outOfRightBounds() {
		// TODO �Զ����ɵķ������
		return x_pos+ width >= 850;
	}
}
