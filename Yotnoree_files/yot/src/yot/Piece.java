package yot;

public class Piece {

	private int x,y;
	private int point;
	
	public Piece(){
		this.x=0;
		this.y=0;
		this.point =1;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getPoint(){
		return point;
	}
	
	public void setPos(int act){
		fixpos_1(act);//�ει���  ȮȮ�������ؾ߾�  �����ױ�  �����������
		y += act;
		fixpos_2(act);//�ει���  ȮȮ�������ؾ߾�  �����ױ�  �����������
	}
	public void setPoint(int a){
		point += a;//�������� ������ ����
	}
	public void fixpos_1(int act){
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
	public void fixpos_2(int act){
		if(act>0){
			if(x==1 && y==3){
				x=2;
				y=3;
			}else if(x==1 && y>5){
				x=0;
				y+=9;
			}else if(x==2 && y>5){
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
	
}
