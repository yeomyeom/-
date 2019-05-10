package yot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Playgame implements ActionListener{
	player []play;
	Yot_pan pan;
	int mal_num;
	int player_num;
	int turn=0;
	int winner=-1;
	int result=0;
	player nowplayer;
	int i=0;
	int control=1;
	Playgame(int people, int mal)
	{
		play = new player[people];
		for(int i=0;i<people;i++)
		{
			play[i] = new player(i,mal);
		}
		pan = new Yot_pan(this);//yotpan���� ��ư�� Ŭ�� �Ǿ��ٴ� ������ �ޱ����� ���� ��ü�� ����
		player_num = people;
		mal_num = mal;
	}
	int check_finish()
	{
		for(int i=0;i<play.length;i++)
		{
			if(play[i].point > mal_num)
			{
				play = null;
				pan.finish(i);
				pan = null;
				return i;//i��° �÷��̾� �¸�
			}
		}
		return -1;//���� ���� �ȳ���
	}
	int check_catch(int index)//���� �÷��̾� �ε�����
	{
		player catcher = play[index];//catcher�� ������ �÷��̾�
		int positionx,positiony;
		for(int q=0;q<catcher.mal.size();q++)//���� �÷��̾��� ��� ��
		{
			positionx = catcher.mal.get(q).getx();
			positiony = catcher.mal.get(q).gety();//���� �÷��̾��� q��° �� ��ġ
			for(int i=0;i<play.length;i++)
			{
				if(i!=index)
				{
					if(play[i].check_catch(positionx,positiony)==1)//i��° player�� ����� ���ؼ� ������ ����
					{
						System.out.println("��Ҵ�");
						return 1;//��ĭ�� ���� �ٸ� �÷��̾��� ���� �������� �����Ƿ� �׳� ������ ���� ����
					}
				}
			}
		}
		return 0;
	}
	void go_1()
	{
		if(control==1) {
			System.out.println("�� ������ Ŭ����");
			nowplayer = play[turn];
			pan.changeplayer(turn);
			result=nowplayer.throwyot();//������ ��ư Ŭ��
			pan.printyotresult(result);//���� ��� ȭ�鿡 ���
			control =2;
		}
	}
	void go_2(int posx, int posy)
	{
		int index;
		if(control==2)
		{
			if(nowplayer.mal.size()==0)//���� ������ ���� ������ 0�� ���� �����ϰ� ���� ��� ��ŭ �̵� 
			{
				System.out.println("������� �� ����");
				control=3;
				go_3();
			}
			else
			{
				index = nowplayer.check_enable(posx, posy);//�ش� ��ġ�� ��ư�� ���� �ִ��� Ȯ��
				if(index!=-1)
				{
					System.out.println("��ư �Է��� ��ȿ��");
					//���� ������ player���� �˾Ƽ� ã�� ������ ����� �̵���
					pan.printmal(4, posx, posy);//�������� ������� ���� ���� �� �̵�
					nowplayer.move(posx, posy, result); //���⼭ �˾Ƽ� ������� �Ǵ�����
					if(nowplayer.check_malin()==0)
					{
						pan.printmal(turn,nowplayer.mal.get(index).getx(),nowplayer.mal.get(index).gety());//�÷��̾�, �̵� ���� ��ǥ
					}
					if(check_catch(turn)==1 || result == 4 || result ==5)//�ٽ� �� ������ ����
					{
						control=1;
						go_1();
					}
					else
					{
						System.out.println("next player");
						go_4();//�ش� �÷��̾� �� ����
					}
				}
				else
				{
					System.out.println("������ ��ư Ŭ����"+posx +" , "+posy);
					//�ٽ� �Է��Ҷ� ���� ��ٸ�
				}
			}
		}
		else
		{
			//���� ���� �������� �ʾҴµ� �� Ŭ���ϸ� �ƹ� ���� ����
		}
	}
	void go_3()
	{
		if(control==3) {
			if(nowplayer.getmal_num()>0) {
				System.out.println("���ο� ���� ����");
				nowplayer.createmal();//���� ������� 0 ��ư�� ��ġ�� ����
				nowplayer.move(0, 0, result); //���⼭ �˾Ƽ� ������� �Ǵ�����
				if(result<0)
				{
					result += 20;
				}
				pan.printmal(turn, 0, result);
				if(check_catch(turn)==1 || result == 4 || result ==5)//�ٽ� �� ������ ����
				{
					control=1;
					go_1();
				}
				else
				{
					System.out.println(turn + " ��° player ����");
					go_4();//�ش� �÷��̾� �� ����
				}
			}//����� �� �ִ� ���� ��� ������ 0 0�� �߰�
			else
			{
				System.out.println("���̻� �߰��� ���� �����ϴ�.");
				control=2;
			}
		}
	}
	void go_4()
	{
		play[turn].check_malin();
		turn++;
		if(turn >= player_num)
		{
			turn -= player_num;
		}
		winner = check_finish();
		if(winner != -1)
		{
			System.out.println(winner+ " ��° �÷��̾ �¸��Ͽ����ϴ�.");
		}
		control =1;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pan.throw_button)
		{
			go_1();
		}
		if(e.getSource()==pan.new_mal)
		{
			if(control == 2)
			{
				control=3;
				go_3();
			}
		}
		for(int i=1;i<21;i++) {
			if(e.getSource()==pan.pan_button[0][i])
			{
				go_2(0,i);
			}
		}
		for(int p=0;p<7;p++) {
			if(e.getSource()==pan.pan_button[1][p])
			{
				go_2(1,p);
			}
		}
		for(int q=0;q<7;q++) {
			if(e.getSource()==pan.pan_button[2][q])
			{
				go_2(2,q);
			}
		}
		for(int r=0;r<7;r++)
		{
			if(e.getSource()==pan.test_button[r])
			{
				if(control==1)
				{
					if(r==0)
					{
						result = -1;
					}
					else
					{
						result = r;
					}
					pan.printyotresult(result);//���� ��� ȭ�鿡 ���
					control =2;
				}
			}
		}

	}
}
