package yot;

import java.util.Random;
import java.util.ArrayList;

public class Player {
	private int id;
	private ArrayList<Piece> pieces;
	//private ArrayList<Piece> ghost;
	private int point;
	private int pieceNum;//���� ���ǿ� �ȳ����� ������� �� ��
	
	public Player(int id, int pieceNum){
		this.id = id;
		this.pieces = new ArrayList<Piece>();
		//this.ghost = new ArrayList<Piece>();
		this.point =0;
		this.pieceNum = pieceNum;
	}
	public ArrayList<Piece> getPiece(){
		return this.pieces;
	}
	public int getPoint()
	{
		return this.point;
	}
	public int getPieceNum(){
		return this.pieceNum;
	}
	public int createPiece(){
		if(pieceNum>0)
		{
			pieces.add(new Piece());//�� ���� �� �ø�
			pieceNum--;
			System.out.println("�� ���� �Ϸ�");
			return 1;
		}
		return 0;
	}
	public int throwYot(){
		Random r = new Random();
		int act=0;
		act = r.nextInt(6)-1;
		if(act == 0)
			act = 5;
		return act;
	}
	public String playerPiece(){
		String s="";
		for(Piece piece : pieces)
		{
			s += "<����:"+pieceNum+"��:"+point+">("+piece.getX()+","+piece.getY()+","+piece.getPoint()+")";
		}
		return s;
	}
	/*public void wheremove(int posX, int posY, int active) {//������ �ö� �� �ִ� ������ �� �� �ִ� ��ġ ���
	
		//ghost.addAll(mal);
		//�ϴ� �����ϰ� ������
	}*/
	
	public int move(int x, int y, int active) {  //(�� ��ġ, �������� ������ �����ָ�) ���� ���� �����ϱ��?		
		if(pieces.size()!=0)
		{
			for(Piece piece : pieces)
			{
				if(piece.getX()==x && piece.getY()==y)//(�� ��ġ�� �ִ� ���� ã�� �������� ��ŭ �̵���Ŵ)
				{
					piece.setPos(active);
					//ghost.removeAll(ghost);
					//ghost.addAll(mal);
				}
			}
			return checkUpda();
		}
		return 0;
	}
	public int checkCatch(int positionx, int positiony){
		for(Piece piece : pieces)
		{
			if(piece.getX()==positionx && piece.getY()==positiony)
			{
				System.out.println(piece.getPoint()+"�� ������");
				pieceNum += piece.getPoint();
				pieces.remove(piece);
				return 1;
			}
		}
		return 0;
	}
	public int checkEnable(int posX, int posY) { //�ش� ��ġ�� ���� �ֳ� ����
		int i=0;
		for(Piece piece : pieces){
			if(piece.getX()==posX && piece.getY()==posY){
				return i;
			}
			i++;
		}
		return -1;
	}
	public int checkPiecein(){
		System.out.println("P "+id+" �� ������ Ȯ��");
		for(Piece piece : pieces){
			if(piece.getY()>20) {
				point += piece.getPoint();
				System.out.println("���� "+piece.getPoint()+"�� ���� ���� ����Ʈ"+point);
				pieces.remove(piece);
				return 1;
			}
		}
		return 0;
	}
	public int checkUpda(){
		for(int p=0; p<pieces.size(); p++)
		{
			for(int q=p+1; q<pieces.size(); q++)
			{
				if(pieces.get(p).getX()==pieces.get(q).getX() && pieces.get(p).getY()==pieces.get(q).getY())//���� ��ġ�� ���� �ִٸ�
				{
					System.out.println("���� �߻���");
					pieces.get(p).setPoint(pieces.get(q).getPoint());//q��° ���� ���� ��ŭ p��° ���� ����
					pieces.remove(q);
					return 1;
				}
			}
		}
		return 0;
	}
}