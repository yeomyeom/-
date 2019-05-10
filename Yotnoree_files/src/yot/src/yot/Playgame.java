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
			if(play[i].getpoint() == mal_num)
			{
				play = null;
				pan.finish(i);
				//pan = null;
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
						pan.message("P"+index+"�� P"+i+"�� ���� ��Ҵ�");
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
			result =0;
			nowplayer = play[turn];
			pan.changeplayer(turn);
			pan.buttoncolor(1);
			result=nowplayer.throwyot();//������ ��ư Ŭ��
			pan.printyotresult(result);//���� ��� ȭ�鿡 ���
			if(nowplayer.getmal_num()>0)//������� ���� �ִٸ� 
			{
				pan.buttoncolor(3);//���ο� �� ��ư Ȱ��ȭ
			}
			if(nowplayer.getmal().size()>0)//�ǿ� ���� �ִٸ� 
			{
				pan.buttoncolor(5);//�� Ŭ�� ��ư Ȱ��ȭ
			}
			control =2;
		}
	}
	void go_2(int posx, int posy)
	{
		int index;
		if(control==2)
		{
			if(nowplayer.mal.size()==0 && nowplayer.getmal_num()>0)//������ ���� ���� ������� ���� �ִٸ� 0,0�� ���� �����
			{
				pan.message("������� �� ����");
				control=3;
				go_3();
			}
			else
			{
				index = nowplayer.check_enable(posx, posy);//�ش� ��ư�� ���� �ִ��� Ȯ��
				if(index!=-1)
				{
					//���� ������ player���� �˾Ƽ� ã�� ������ ����� �̵���
					pan.printmal(4, posx, posy);//�������� ������� ���� ���� �� �̵�
					if(nowplayer.move(posx, posy, result)==1) //���⼭ �˾Ƽ� ������� �Ǵ�����
					{//���ų� ���������� ȭ�鿡 ǥ�ø� ���Ѵ� �̰Ͷ����� �ڲ� ������ ����.
						pan.message("P "+turn+" �� �ϳ��� �������ϴ�");
					}
					else if(nowplayer.check_malin() ==1)
					{
						pan.message("P "+turn+" �� �ϳ��� �����߽��ϴ�");
					}
					else
					{
						/////////////////////////// ���׻���� ����
						pan.printmal(turn,nowplayer.mal.get(index).getx(),nowplayer.mal.get(index).gety());//�÷��̾�, �̵� ���� ��ǥ
						/////////////////////////// ���׻���� ����
					}
					pan.setplayer_info(turn, nowplayer.player_mal());
					if(nowplayer.getmal().size()>0)
					{
						pan.buttoncolor(5);
					}
					else
					{
						pan.buttoncolor(6);
					}
					if(nowplayer.getmal_num()>0)
					{
						pan.buttoncolor(3);
					}
					else
					{
						pan.buttoncolor(4);
					}
					if(nowplayer.getmal_num()<=0 && nowplayer.getmal().size()<=0)//������� ���� ������ ���� ������
					{
						control=-1;//��� ����
						System.out.println("��� ����");
						go_4();//�ش� �÷��̾� �� ����
					}
					else//������ ������ �ʾҴٸ� (�̷��� �س��� �ڹ� �ͼ��� �ȶ�)
					{
						if(check_catch(turn)==1 || result == 4 || result ==5)//�ٽ� �� ������ ����
						{
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							control=1;
							go_1();
						}
						else
						{
							go_4();//�ش� �÷��̾� �� ����
						}
					}
				}
				else
				{
					pan.message("������ ��ư Ŭ����"+posx +" , "+posy);
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
		if(control==3)
		{
			//���� ������ player���� �˾Ƽ� ã�� ������ ����� �̵���
			if(nowplayer.createmal()==1) //������� ���� ���� �ִٸ� ���� ���� ����
			{
				nowplayer.move(0, 0, result); //���⼭ �˾Ƽ� ������� �Ǵ�����
				pan.setplayer_info(turn, nowplayer.player_mal());
				pan.printmal(turn,0,result);//�÷��̾�, �̵� ���� ��ǥ
				if(nowplayer.getmal().size()>0)
				{
					pan.buttoncolor(5);
				}
				else
				{
					pan.buttoncolor(6);
				}
				if(nowplayer.getmal_num()>0)
				{
					pan.buttoncolor(3);
				}
				else
				{
					pan.buttoncolor(4);
				}
				if(check_catch(turn)==1 || result == 4 || result ==5)//�ٽ� �� ������ ����
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					control=1;
					go_1();
				}
				else
				{
					go_4();//�ش� �÷��̾� �� ����
				}
			}
			else
			{
				pan.message("�� �̻� ���� ���� �� �� �����ϴ�.");
				control=2;//�� ���� �ѵ��� �Ѿ�� go_2�� �۵� �� �� �ֵ��� �Ѵ�.
			}
		}
	}
	void go_4()
	{
		winner = check_finish();
		if(winner != -1)
		{
			control=5;//�ƹ� ���� ����
			System.out.println(winner+ " ��° �÷��̾ �¸��Ͽ����ϴ�.");
		}
		else 
		{
			turn++;
			if(turn >= player_num)
			{
				turn =0;
			}
			pan.message("P "+turn+" ����");
			pan.initcp();
			pan.buttoncolor(2);
			pan.buttoncolor(4);
			pan.buttoncolor(6);
			control =1;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pan.throw_button)
		{
			if(control==1)
			{
				go_1();
			}
		}
		if(e.getSource()==pan.new_mal)
		{
			if(control == 2)
			{
				control=3;
				go_3();
			}
		}
		if(control==2) {
			for(int i=1;i<21;i++) {
				if(e.getSource()==pan.pan_button[0][i])
				{
					go_2(0,i);
				}
			}
			for(int p=0;p<6;p++) {
				if(e.getSource()==pan.pan_button[1][p])
				{
					go_2(1,p);
				}	
			}
			for(int q=0;q<6;q++) {
				if(e.getSource()==pan.pan_button[2][q])
				{
					go_2(2,q);
				}
			}
		}
		for(int r=0;r<6;r++)
		{
			if(e.getSource()==pan.test_button[r])
			{
				if(control==1)
				{
					go_1();
					r--;
					if(r==0)
					{
						result = 5;
					}
					else
					{
						result = r;
					}
					pan.printyotresult(result);//���� ��� ȭ�鿡 ���
				}
			}
		}
	}
}
