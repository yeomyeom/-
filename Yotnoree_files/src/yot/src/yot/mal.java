package yot;

public class mal {
	int x,y;
	int point;
	mal()
	{
		x=0;
		y=0;
		point =1;
	}
	int getx()
	{
		return x;
	}
	int gety()
	{
		return y;
	}
	int getpoint()
	{
		return point;
	}
	void fixpos_1(int act)
	{
		if(x==0 && y==5)//�̷��� ������ ���� ���
		{
			x=1;
			y=0;
		}
		else if(x==0 && y==10)
		{
			x=2;
			y=0;
		}
	}
	void fixpos_2(int act)
	{
		if(act>0)
		{
			if(x==1 && y==3)
			{
				x=2;
				y=3;
			}
			else if(x==1 && y>5)
			{
				x=0;
				y+=9;
			}
			else if(x==2 && y>5)
			{
				x=0;
				y+=14;
			}
		}
		else//�鵵
		{
			if(x==1 && y<1)
			{
				x=0;
				y=5+y;
			}
			else if(x==1 && y==3)//5.10 bug fix
			{
				x=2;
				y=3;
			}
			else if(x==2 && y<1)
			{
				x=0;
				y=10+y;
			}
			else if(x==0 && y<1)
			{
				y=20+y;
			}
		}
	}
	void setpos(int act)
	{
		fixpos_1(act);//�ει���  ȮȮ�������ؾ߾�  �����ױ�  �����������
		y += act;
		fixpos_2(act);//�ει���  ȮȮ�������ؾ߾�  �����ױ�  �����������
	}
	void setpoint(int a)
	{
		point += a;//�������� ������ ����
	}
}
