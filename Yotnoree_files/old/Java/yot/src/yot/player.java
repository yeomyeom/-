package yot;
import java.util.Random;
import java.util.ArrayList;

public class player {
	int id;
	ArrayList<mal> mal;
	ArrayList<mal> ghost;
	int point;
	int mal_num;//���� �� ��
	player(int _id, int _mal_num)
	{
		id = _id;
		mal = new ArrayList<mal>();
		ghost = new ArrayList<mal>();
		point =0;
		mal_num = _mal_num;
	}
	int getmal_num()
	{
		return mal_num;
	}
	void createmal()
	{
		mal.add(new mal());//�� ���� �� �ø�
		mal_num--;
	}
	int throwyot()
	{
		Random r = new Random();
		int act=0;
		act = r.nextInt(6)-1;
		if(act == 0)
			act = 5;
		return act;
	}
	void showmal()
	{
		for (int i=0;i<mal.size();i++)
		System.out.println(point+ " "+ mal.get(i).getx()+" "+ mal.get(i).gety());
	}
	void wheremove(int posx, int posy, int active)//������ �ö� �� �ִ� ������ �� �� �ִ� ��ġ ���
	{
		ghost.addAll(mal);
		//�ϴ� �����ϰ� ������
	}
	void move(int x, int y, int active)//(�� ��ġ, �������� ������ �����ָ�) ���� ���� �����ϱ��?
	{
		if(mal.size()!=0)
		{
			for(mal m : mal)
			{
				if(m.getx()==x && m.gety()==y)//(�� ��ġ�� �ִ� ���� ã�� �������� ��ŭ �̵���Ŵ)
				{
					m.setpos(active);
					showmal();
					ghost.removeAll(ghost);
					ghost.addAll(mal);
				}
			}
			checkupda();
		}
	}
	int check_catch(int positionx, int positiony)
	{
		for(mal m : mal)
		{
			if(m.getx()==positionx && m.gety()==positiony)
			{
				delmal(positionx, positiony);//position�� ���� ��ġ�� ���� ������ ����
				mal_num++;
				return 1;
			}
		}
		return 0;
	}
	int check_enable(int posx, int posy)//�ش� ��ġ�� ���� �ֳ� ����
	{
		int i=0;
		for(mal m : mal)
		{
			if(m.getx()==posx && m.gety()==posy)
			{
				return i;
			}
			i++;
		}
		return -1;
	}
	int check_malin()
	{
		for(mal m : mal)
		{
			if(m.getx()==2 && m.gety()>6) {
				point += m.getpoint();
				mal_num--;
				return 1;
			}
		}
		return 0;
	}
	void checkupda()
	{
		for(int p=0;p<mal.size();p++)
		{
			for(int q=p+1;q<mal.size();q++)
			{
				if(mal.get(p).getx()==mal.get(q).getx() && mal.get(p).gety()==mal.get(q).gety())//���� ��ġ�� ���� �ִٸ�
				{
					mal.get(p).setpoint(mal.get(q).getpoint());//q��° ���� ���� ��ŭ p��° ���� ����
					mal.remove(q);
				}
			}
		}
	}
	void delmal(int x, int y)
	{
		for(mal m : mal)
		{
			if(m.getx()==x&&m.gety()==y)
			{
				mal.remove(m);
				break;
			}
		}
	}
	
}
