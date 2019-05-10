package yot;
import java.util.Random;
import java.util.ArrayList;

public class player {
	int id;
	ArrayList<mal> mal;
	ArrayList<mal> ghost;
	int point;
	int mal_num;//���� ���ǿ� �ȳ����� ������� �� ��
	player(int _id, int _mal_num)
	{
		id = _id;
		mal = new ArrayList<mal>();
		ghost = new ArrayList<mal>();
		point =0;
		mal_num = _mal_num;
	}
	ArrayList<mal> getmal()
	{
		return mal;
	}
	int getpoint()
	{
		return point;
	}
	int getmal_num()
	{
		return mal_num;
	}
	int createmal()
	{
		if(mal_num>0)
		{
			mal.add(new mal());//�� ���� �� �ø�
			mal_num--;
			System.out.println("�� ���� �Ϸ�");
			return 1;
		}
		return 0;
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
	String player_mal()
	{
		String s="";
		for(mal m : mal)
		{
			s += "<����:"+mal_num+"��:"+point+">("+m.getx()+","+m.gety()+","+m.getpoint()+")";
		}
		return s;
	}
	void wheremove(int posx, int posy, int active)//������ �ö� �� �ִ� ������ �� �� �ִ� ��ġ ���
	{
		//ghost.addAll(mal);
		//�ϴ� �����ϰ� ������
	}
	int move(int x, int y, int active)//(�� ��ġ, �������� ������ �����ָ�) ���� ���� �����ϱ��?
	{
		if(mal.size()!=0)
		{
			for(mal m : mal)
			{
				if(m.getx()==x && m.gety()==y)//(�� ��ġ�� �ִ� ���� ã�� �������� ��ŭ �̵���Ŵ)
				{
					m.setpos(active);
					//ghost.removeAll(ghost);
					//ghost.addAll(mal);
				}
			}
			return checkupda();
		}
		return 0;
	}
	int check_catch(int positionx, int positiony)
	{
		for(mal m : mal)
		{
			if(m.getx()==positionx && m.gety()==positiony)
			{
				System.out.println(m.getpoint()+"�� ������");
				mal_num += m.getpoint();
				delmal(positionx, positiony);//position�� ���� ��ġ�� ���� ������ ����
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
		System.out.println("P "+id+" �� ������ Ȯ��");
		for(mal m : mal)
		{
			if(m.gety()>20) {
				point += m.getpoint();
				//mal_num -= m.getpoint();<<���� ã�Ҵ�
				System.out.println("���� "+m.getpoint()+"�� ���� ���� ����Ʈ"+point);
				mal.remove(m);
				return 1;
			}
		}
		return 0;
	}
	int checkupda()
	{
		for(int p=0;p<mal.size();p++)
		{
			for(int q=p+1;q<mal.size();q++)
			{
				if(mal.get(p).getx()==mal.get(q).getx() && mal.get(p).gety()==mal.get(q).gety())//���� ��ġ�� ���� �ִٸ�
				{
					System.out.println("���� �߻���");
					mal.get(p).setpoint(mal.get(q).getpoint());//q��° ���� ���� ��ŭ p��° ���� ����
					mal.remove(q);
					return 1;
				}
			}
		}
		return 0;
	}
	void delmal(int x, int y)
	{
		for(mal m : mal)
		{
			if(m.getx()==x&&m.gety()==y)
			{
				System.out.println("���� �����߽��ϴ�");
				mal.remove(m);
				break;
			}
		}
	}
	void showall()
	{
		for(mal m : mal)
		{
			System.out.println(" "+m.getx()+m.gety()+m.getpoint()+" ");
		}
	}
}
