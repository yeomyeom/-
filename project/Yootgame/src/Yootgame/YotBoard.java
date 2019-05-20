package Yootgame;

import java.awt.*;
import javax.swing.*;

public class YotBoard extends JFrame {
	private JPanel panelPan;
	JButton [][]panButton;
	private PlayGame play;
	private int windowSizeX=1000;
	private int windowSizeY=700;
	private int buttonSizeX = 50;
	private int buttonSizeY = 50;
	JButton throwButton, newPiece;
	JButton []testButton = new JButton[7];
	JButton nowPlayer;
	JButton panClick;
	JButton []playerInfobtn;
	JLabel yotResult;
	JLabel boardMessage;
	JLabel []playerInfo;
	public YotBoard(PlayGame playObject) {
		play = playObject;
		Toolkit tools = Toolkit.getDefaultToolkit();
		Image mouseimg = tools.getImage("./img/cursor.png");
		Cursor newcursor = tools.createCustomCursor(mouseimg, new Point(0,0), "LOL");
		setCursor(newcursor);
		panelPan = new JPanel();
		panButton = new JButton [3][21];
		int xpos=buttonSizeX*7;
		int ypos=buttonSizeY*7;
		double buttonInterval=buttonSizeX*1.25;
   	 	panelPan.setLayout(null);
   	 	panelPan.setBackground(new Color(255,255,255));
   	 	for(int i=1;i<21;i++)
   	 	{
   	 		if(i<6) {
   	 			ypos -= buttonInterval;
   	 		}
   	 		else if(i<11) {
   	 			xpos -= buttonInterval;
   	 		}
   	 		else if(i<16) {
   	 			ypos += buttonInterval;
   	 		}
   	 		else {
   	 			xpos += buttonInterval;
   	 		}
   	 	
			if(i==5 || i==10 || i==15){
				panButton[0][i] = new JButton(new ImageIcon("./img/bigcircle.jpg"));
			}
			else if(i==20) {
				panButton[0][i] = new JButton(new ImageIcon("./img/startcircle.jpg"));
			}
			else {
				panButton[0][i] = new JButton(new ImageIcon("./img/circle.jpg"));
			}
			panButton[0][i].setLocation(xpos,ypos);
			panButton[0][i].setSize(buttonSizeX,buttonSizeY);
			panButton[0][i].setBorderPainted(false);
			panButton[0][i].setContentAreaFilled(false);
			panelPan.add(panButton[0][i]);
			panButton[0][i].addActionListener(play);
   	 	}
		/*for(int i=0;i<25;i++)//0~20
		{
			if(i<7)
			{
				ypos -= buttonSizeY;
			}else if(i<13)
			{
				xpos -= buttonSizeX;
			}
			else if(i<19)
			{
				ypos += buttonSizeY;
			}else
			{
				xpos += buttonSizeX;
			}
			if(i!=3 && i!=9 &&i!=15 &&i!=21) {
				if(p!=0) {
					if(p==5 || p==10 || p==15){
						panButton[0][p] = new JButton(new ImageIcon("./img/bigcircle.jpg"));
					}
					else if(p==20) {
						panButton[0][p] = new JButton(new ImageIcon("./img/startcircle.jpg"));
					}
					else {
						panButton[0][p] = new JButton(new ImageIcon("./img/circle.jpg"));
					}
					panButton[0][p].setLocation(xpos,ypos);
					panButton[0][p].setSize(buttonSizeX,buttonSizeY);
					panButton[0][p].setBorderPainted(false);
					panButton[0][p].setContentAreaFilled(false);
					panelPan.add(panButton[0][p]);
					panButton[0][p].addActionListener(play);
				}
				p++;
			}
		}*/
		ypos =buttonSizeY-10;
		xpos =buttonSizeX*7-10;
		int p;
		for(p=0;p<6;p++)
		{
			
			if(p==3)
			{
				xpos -= buttonSizeX;
				ypos += buttonSizeY;
			}else {
				if(p==0)
				{
					xpos -= buttonSizeX;
					ypos += buttonSizeY;
				}
				else 
				{
					panButton[1][p] = new JButton(new ImageIcon("./img/circle.jpg"));
					//panButton[1][p] = new JButton(Integer.toString(p));
					panButton[1][p].setLocation(xpos,ypos);
					xpos -= buttonSizeX;
					ypos += buttonSizeY;
					panButton[1][p].setSize(buttonSizeX,buttonSizeY);
					panButton[1][p].setBorderPainted(false);
					panButton[1][p].setContentAreaFilled(false);
					panelPan.add(panButton[1][p]);
					panButton[1][p].addActionListener(play);
				}
			}
		}
		xpos = buttonSizeX-10;
		ypos = buttonSizeY-10;
		for(p=0;p<6;p++)
		{
			if(p==0) {
				xpos += buttonSizeX;
				ypos += buttonSizeY;
			}
			else
			{
				if(p==3) {
					panButton[2][p] = new JButton(new ImageIcon("./img/bigcircle.jpg"));
				}else{
					panButton[2][p] = new JButton(new ImageIcon("./img/circle.jpg"));
				}
				panButton[2][p].setLocation(xpos,ypos);
				xpos += buttonSizeX;
				ypos += buttonSizeY;
				panButton[2][p].setSize(buttonSizeX,buttonSizeY);
				panButton[2][p].setBorderPainted(false);
				panButton[2][p].setContentAreaFilled(false);
				panelPan.add(panButton[2][p]);
				panButton[2][p].addActionListener(play);
			}
		}
		
		throwButton = new JButton("윷 던지기");
		throwButton.setSize(150,50);
		throwButton.setBorderPainted(true);
		throwButton.setContentAreaFilled(true);
		throwButton.setLocation(450,500);
		throwButton.setBackground(new Color(255,255,0));
		panelPan.add(throwButton);
		throwButton.addActionListener(play);
		
		panClick = new JButton("판 클릭");
		panClick.setSize(150,50);
		panClick.setBorderPainted(true);
		panClick.setContentAreaFilled(true);
		panClick.setLocation(450,450);
		panClick.setBackground(new Color(153,204,255));
		panelPan.add(panClick);
		
		newPiece = new JButton("새로운 말 꺼내기");
		newPiece.setSize(150,50);
		newPiece.setBorderPainted(true);
		newPiece.setContentAreaFilled(true);
		newPiece.setLocation(450,550);
		newPiece.setBackground(new Color(153,204,255));
		panelPan.add(newPiece);
		newPiece.addActionListener(play);
		
		nowPlayer = new JButton();
		nowPlayer.setBorderPainted(false);
		nowPlayer.setContentAreaFilled(false);
		nowPlayer.setSize(200,70);
		nowPlayer.setLocation(50,500);
		panelPan.add(nowPlayer);
		
		yotResult = new JLabel();
		yotResult.setSize(300,70);
		yotResult.setLocation(250,500);
		panelPan.add(yotResult);
		
		boardMessage = new JLabel("P0 차례");
		boardMessage.setSize(300,50);
		boardMessage.setLocation(50,400);
		panelPan.add(boardMessage);
		
		playerInfo=new JLabel[4];
		playerInfobtn=new JButton[4];
		ImageIcon []img = new ImageIcon[4];
		img[0] = new ImageIcon("./img/red.jpg");
		img[1] = new ImageIcon("./img/blue.jpg");
		img[2] = new ImageIcon("./img/green.jpg");
		img[3] = new ImageIcon("./img/yellow.jpg");
		for(int i=0;i<4;i++)
		{
			playerInfobtn[i] = new JButton();
			playerInfobtn[i].setIcon(img[i]);
			playerInfobtn[i].setSize(50, 50);
			playerInfobtn[i].setBorderPainted(false);
			playerInfobtn[i].setContentAreaFilled(false);
			playerInfobtn[i].setLocation(450,i*50);
			playerInfo[i] = new JLabel();
			playerInfo[i].setSize(500,50);
			playerInfo[i].setLocation(500,i*50);
			panelPan.add(playerInfobtn[i]);
			panelPan.add(playerInfo[i]);
		}
		String []s = new String[6];
		s[0]="백도";
		s[1]="모";
		s[2]="도";
		s[3]="개";
		s[4]="걸";
		s[5]="윷";
		for(int q=0;q<6;q++)
		{
			testButton[q] = new JButton(s[q]);
			testButton[q].setSize(60,60);
			testButton[q].setBorderPainted(true);
			testButton[q].setContentAreaFilled(true);
			testButton[q].setLocation(50+q*60,450);
			panelPan.add(testButton[q]);
			testButton[q].addActionListener(play);
		}
		
        this.add(panelPan);
        this.setTitle("Yot play");
        this.setVisible(true);
        this.setSize(windowSizeX, windowSizeY);
	}
	void refreashFrame()//changeplayer와 printyotresult 화면 갱신때 빈칸으로 만듬
	{
		nowPlayer.setIcon(null);
		yotResult.setText("");
	}
	void changePlayer(int i)
	{
		ImageIcon []img = new ImageIcon[4];
		img[0] = new ImageIcon("./img/red.jpg");
		img[1] = new ImageIcon("./img/blue.jpg");
		img[2] = new ImageIcon("./img/green.jpg");
		img[3] = new ImageIcon("./img/yellow.jpg");
		nowPlayer.setIcon(img[i]);
	}
	void printResult(int i)
	{
		String text;
		switch(i){
		case -1:
			text = "빽도";
			break;
		case 1:
			text ="도";
			break;
		case 2:
			text ="개";
			break;
		case 3:
			text ="걸";
			break;
		case 4:
			text ="윷";
			break;
		case 5:
			text ="모";
			break;
		default:
			text = "printyotresult ERROR";
			break;
		}
		yotResult.setText(text);
	}
	void printPiece(int player, int posx, int posy)
	{
		if(posx==0 && posy==0)
		{
			posx=0;
			posy=20;
		}
		else if(posx==0 && posy==-1)
		{
			posx=0;
			posy=19;
		}
		else if(posx==1 && posy==3)
		{
			posx=2;
			posy=3;
		}
		else if(posy>20)
		{
			player = 9;//골인 지점으로 들어와서 표시 안해줘도됨
		}
		if(player == 0)
		{
			if((posx==0 && posy==5) || posy==10 || posy == 15 || (posx==2 && posy==3)) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/bigred.jpg"));
			}
			else if(posy==20) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/startred.jpg"));
			}
			else {
				panButton[posx][posy].setIcon(new ImageIcon("./img/red.jpg"));
			}
		}
		else if(player == 1)
		{
			if((posx==0 && posy==5) || posy==10 || posy == 15 || (posx==2 && posy==3)) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/bigblue.jpg"));
			}
			else if(posy==20) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/startblue.jpg"));
			}
			else {
				panButton[posx][posy].setIcon(new ImageIcon("./img/blue.jpg"));
			}
		}
		else if(player == 2)
		{
			if((posx==0 && posy==5) || posy==10 || posy == 15 || (posx==2 && posy==3)) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/biggreen.jpg"));
			}
			else if(posy==20) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/startgreen.jpg"));
			}
			else {
				panButton[posx][posy].setIcon(new ImageIcon("./img/green.jpg"));
			}
		}
		else if(player == 3)
		{
			if((posx==0 && posy==5) || posy==10 || posy == 15 || (posx==2 && posy==3)) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/bigyellow.jpg"));
			}
			else if(posy==20) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/startyellow.jpg"));
			}
			else {
				panButton[posx][posy].setIcon(new ImageIcon("./img/yellow.jpg"));
			}
		}
		else if(player == 4)//흰색 판으로 다시 되돌림
		{
			if((posx==0 && posy==5) || posy==10 || posy == 15 || (posx==2 && posy==3)) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/bigcircle.jpg"));
			}
			else if(posy==20) {
				panButton[posx][posy].setIcon(new ImageIcon("./img/startcircle.jpg"));
			}
			else {
				panButton[posx][posy].setIcon(new ImageIcon("./img/circle.jpg"));
			}
		}
		/*else if(player == 5)//이건 미리가기용도
		{
			panButton[posx][posy].setIcon(new ImageIcon("./img/yellow.jpg"));
		}*/
		else
		{
			//System.out.println("printmal error");
		}
		
	}
	void message(String s)
	{
		boardMessage.setText(s);
	}
	void finishMessage(int winner)
	{
		boardMessage.setText("Player "+winner+ " is win");
	}
	void setplayerInfo(int i, String s)
	{
		playerInfo[i].setText(s);
	}
	void buttonColor(String s)
	{
		switch (s)
		{
		case "throwBtnOFF"://1
			throwButton.setBackground(new Color(153,204,255));//던지기 파랑
			break;
		case "throwBtnON"://2
			throwButton.setBackground(new Color(255,255,0));//던지기 노랑
			break;
		case "newPieceBtnON"://3
			newPiece.setBackground(new Color(255,255,0));//새로운 말 노랑
			break;
		case "newPieceBtnOFF"://4
			newPiece.setBackground(new Color(153,204,255));//새로운 말 파랑
			break;
		case "clickBoardON"://5
			panClick.setBackground(new Color(255,255,0));//판클릭 노랑
			break;
		case "clickBoardOFF"://6
			panClick.setBackground(new Color(153,204,255));//판클릭 파랑
			break;
		}
	}
}