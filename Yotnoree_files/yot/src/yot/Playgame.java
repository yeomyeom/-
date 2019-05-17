package yot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Playgame implements ActionListener{
	Player []play;
	Yotboard board;
	int pieceNum;
	int playerNum;
	int turn=0;
	int winner=-1;
	int result=0;
	Player nowPlayer;
	int i=0;
	int control=1;
	Playgame(int people, int mal)
	{
		play = new Player[people];
		for(int i=0;i<people;i++)
		{
			play[i] = new Player(i,mal);
		}
		board = new Yotboard(this);//yotboard���� ��ư�� Ŭ�� �Ǿ��ٴ� ������ �ޱ����� ���� ��ü�� ����
		playerNum = people;
		pieceNum = mal;
	}
	int checkFinish()
	{
		for(int i=0;i<play.length;i++)
		{
			if(play[i].getPoint() == pieceNum)
			{
				play = null;
				board.finishMessage(i);
				//board = null;
				return i;//i��° �÷��̾� �¸�
			}
		}
		return -1;//���� ���� �ȳ���
	}
	int checkCatch(int index)//���� �÷��̾� �ε�����
	{
		Player catcher = play[index];//catcher�� ������ �÷��̾�
		int positionx,positiony;
		for(int q=0;q<catcher.getPiece().size();q++)//���� �÷��̾��� ��� ��
		{
			positionx = catcher.getPiece().get(q).getX();
			positiony = catcher.getPiece().get(q).getY();//���� �÷��̾��� q��° �� ��ġ
			for(int i=0;i<play.length;i++)
			{
				if(i!=index)
				{
					if(play[i].checkCatch(positionx,positiony)==1)//i��° Player�� ����� ���ؼ� ������ ����
					{
						board.message("P"+index+"�� P"+i+"�� ���� ��Ҵ�");
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
			nowPlayer = play[turn];
			board.changePlayer(turn);
			board.buttonColor(1);
			result=nowPlayer.throwYot();//������ ��ư Ŭ��
			board.printResult(result);//���� ��� ȭ�鿡 ���
			if(nowPlayer.getPieceNum()>0)//������� ���� �ִٸ� 
			{
				board.buttonColor(3);//���ο� �� ��ư Ȱ��ȭ
			}
			if(nowPlayer.getPiece().size()>0)//�ǿ� ���� �ִٸ� 
			{
				board.buttonColor(5);//�� Ŭ�� ��ư Ȱ��ȭ
			}
			control =2;
		}
	}
	void go_2(int posx, int posy)
	{
		int index;
		if(control==2)
		{
			if(nowPlayer.getPiece().size()==0 && nowPlayer.getPieceNum()>0)//������ ���� ���� ������� ���� �ִٸ� 0,0�� ���� �����
			{
				board.message("������� �� ����");
				control=3;
				go_3();
			}
			else
			{
				index = nowPlayer.checkEnable(posx, posy);//�ش� ��ư�� ���� �ִ��� Ȯ��
				if(index!=-1)
				{
					//���� ������ Player���� �˾Ƽ� ã�� ������ ����� �̵���
					board.printPiece(4, posx, posy);//�������� ������� ���� ���� �� �̵�
					if(nowPlayer.move(posx, posy, result)==1) //���⼭ �˾Ƽ� ������� �Ǵ�����
					{//���ų� ���������� ȭ�鿡 ǥ�ø� ���Ѵ� �̰Ͷ����� �ڲ� ������ ����.
						board.message("P "+turn+" �� �ϳ��� �������ϴ�");
					}
					else if(nowPlayer.checkPiecein() ==1)
					{
						board.message("P "+turn+" �� �ϳ��� �����߽��ϴ�");
					}
					else
					{
						/////////////////////////// ���׻���� ����
						board.printPiece(turn,nowPlayer.getPiece().get(index).getX(),nowPlayer.getPiece().get(index).getY());//�÷��̾�, �̵� ���� ��ǥ
						/////////////////////////// ���׻���� ����
					}
					board.setplayerInfo(turn, nowPlayer.playerPiece());
					if(nowPlayer.getPiece().size()>0)
					{
						board.buttonColor(5);
					}
					else
					{
						board.buttonColor(6);
					}
					if(nowPlayer.getPieceNum()>0)
					{
						board.buttonColor(3);
					}
					else
					{
						board.buttonColor(4);
					}
					if(nowPlayer.getPieceNum()<=0 && nowPlayer.getPiece().size()<=0)//������� ���� ������ ���� ������
					{
						control=-1;//��� ����
						System.out.println("��� ����");
						go_4();//�ش� �÷��̾� �� ����
					}
					else//������ ������ �ʾҴٸ� (�̷��� �س��� �ڹ� �ͼ��� �ȶ�)
					{
						if(checkCatch(turn)==1 || result == 4 || result ==5)//�ٽ� �� ������ ����
						{
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
					board.message("������ ��ư Ŭ����"+posx +" , "+posy);
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
			//���� ������ Player���� �˾Ƽ� ã�� ������ ����� �̵���
			if(nowPlayer.createPiece()==1) //������� ���� ���� �ִٸ� ���� ���� ����
			{
				nowPlayer.move(0, 0, result); //���⼭ �˾Ƽ� ������� �Ǵ�����
				board.setplayerInfo(turn, nowPlayer.playerPiece());
				board.printPiece(turn,0,result);//�÷��̾�, �̵� ���� ��ǥ
				if(nowPlayer.getPiece().size()>0)
				{
					board.buttonColor(5);
				}
				else
				{
					board.buttonColor(6);
				}
				if(nowPlayer.getPieceNum()>0)
				{
					board.buttonColor(3);
				}
				else
				{
					board.buttonColor(4);
				}
				if(checkCatch(turn)==1 || result == 4 || result ==5)//�ٽ� �� ������ ����
				{
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
				board.message("�� �̻� ���� ���� �� �� �����ϴ�.");
				control=2;//�� ���� �ѵ��� �Ѿ�� go_2�� �۵� �� �� �ֵ��� �Ѵ�.
			}
		}
	}
	void go_4()
	{
		winner = checkFinish();
		if(winner != -1)
		{
			control=5;//�ƹ� ���� ����
			System.out.println(winner+ " ��° �÷��̾ �¸��Ͽ����ϴ�.");
		}
		else 
		{
			turn++;
			if(turn >= playerNum)
			{
				turn =0;
			}
			board.message("P "+turn+" ����");
			board.initcp();
			board.buttonColor(2);
			board.buttonColor(4);
			board.buttonColor(6);
			control =1;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==board.throwButton)
		{
			if(control==1)
			{
				go_1();
			}
		}
		if(e.getSource()==board.newPiece)
		{
			if(control == 2)
			{
				control=3;
				go_3();
			}
		}
		if(control==2) {
			for(int i=1;i<21;i++) {
				if(e.getSource()==board.panButton[0][i])
				{
					go_2(0,i);
				}
			}
			for(int p=1;p<6;p++) {
				if(e.getSource()==board.panButton[1][p])
				{
					go_2(1,p);
				}	
			}
			for(int q=1;q<6;q++) {
				if(e.getSource()==board.panButton[2][q])
				{
					go_2(2,q);
				}
			}
		}
		for(int r=0;r<6;r++)
		{
			if(e.getSource()==board.testButton[r])
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
					board.printResult(result);//���� ��� ȭ�鿡 ���
				}
			}
		}
	}
}
