package chanshengshi;

import java.util.Scanner;

public class baibanA  {
	static int n;
	static int count;//ά��
	static int size=0;
	static int [][]a=new int[20][20];
	static int [][]b=new int[20][20];
	static int [][]c=new int[20][20];
	static int x;//�ҵ���ʼ0 ��λ��
	static int y;//�ҵ���ʼ0 ��λ��
	static int xx;//�ҵ�Ŀ��0 ��λ��
	static int yy;//�ҵ�Ŀ��0 ��λ��
	static int x1;//�ҵ����ڵ�0 ��λ��
	static int y1;//�ҵ����ڵ�0 ��λ��
	static Node open=new Node();
	static Node closed=new Node();
	public static class Node{
		int [][] data;
		int wn;
		int deep;
		int fn;
		Node before;
		Node next;
		public Node()
		{}
		public Node(int [][]data)
		{
					this.data=data;
		}
	}
	public  static void Findw1(int a[][])//�ҳ�ʼ������0��λ��
	{
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<count;j++)
			{
				if(a[i][j]==0)
				{
					x=i;
					y=j;
				}
			}
		}
	}
	public  static void Findw(int a[][])//��Ŀ�������0��λ��
	{
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<count;j++)
			{
				if(a[i][j]==0)
				{
					xx=i;
					yy=j;
				}
			}
		}
	}
	public  static void Findw2(Node head)//���ڵ�� 0λ��
	{
		Node tail=head;
		if(head.next.before==null)
		{
			x1=100;
			y1=100;
		}
		else {
			for(int i=0;i<count;i++)
			{
				for(int j=0;j<count;j++)
				{
					if(tail.next.before.data[i][j]==0)
					{
						x1=i;
						y1=j;
					}
				}
			}
		}
	}
	public  static void Add(Node head,int a[][],int n) {	//���뿪ʼ�ڵ���open  
		System.out.println("����Add");
		Node node=new Node(a);
		node.wn=n;
		Node tail1=head;
		Node tail2=head;
		Node tail3=head;
		Node tail4=head.next;
		int flag=0;
		Node tail=head.next;
		if(tail==null)
		{
			head.next=node;
			node.deep=0;
			node.fn=node.deep+node.wn;
		}
		System.out.println("Add���open��");
		while(tail1.next!=null)
		{
			for(int i=0;i<count;i++)
			{
				for(int j=0;j<count;j++)
				{
					System.out.print(tail1.next.data[i][j]+"	");
				}
				System.out.print("\n");
			}
			System.out.println("hn fn:"+tail1.next.wn+" | "+tail1.next.fn);
			tail1=tail1.next;
		}
	}
	public  static void Addclosed1(Node head) {	//��open�� ��һ�� �ڵ� ��closed
		System.out.println("����Addclosed1");
		Node tail=head;
		Node tail1=closed;
		Node node=new Node(head.next.data);
		Node closed1=closed;
		while(closed1.next!=null)
		{
			closed1=closed1.next;
		}
		closed1.next=node;
		while(tail1.next!=null)
		{
			System.out.println("closed�ھ���");
			for(int i=0;i<count;i++)
			{
				for(int j=0;j<count;j++)
				{
					System.out.print(tail1.next.data[i][j]+"	");
				}
				System.out.print("\n");
			}
			tail1=tail1.next;
		}
	}
	public  static void Addclosed2(Node head,int a[][],int n ){	//�滻closed�� ��ͬ�ڵ���fn�ϴ�
		System.out.println("����Addclosed2");
		Node node=new Node(a);
		Node tail=head;
		node.before=head.next;
		while(tail.next!=null)
		{
			   int flag1=1;
				for(int i=0;i<count;i++)
				{
					for(int j=0;j<count;j++)
					{
						if(a[i][j]!=tail.next.data[i][j])
						{
							
							flag1=0;
						}
					}
				}
				if(flag1==1)//������Ⱦ��� �滻�ظ�����
				{
					node.next=tail.next.next;
					tail.next=node;
				}
				else {
					tail=tail.next;
				}
		}
	}
	public  static void Add1(Node head,int a[][],int n) {	//�������open  ���Ϊ�����head.deep��1
		Node node=new Node(a);
		node.wn=n;
		Node tail1=head;
		Node tail2=head;
		Node tail3=head;
		Node tail4=head.next;
		int flag=0;
		Node tail=head.next;	
		if(tail!=null)
		{
			node.deep=head.next.deep+1;
			node.fn=node.wn+node.deep;
			while(node.fn>=tail.fn)
			{
				  if(flag==0)
				  {
					if(tail.next==null)//��ǰ����Ҫ�������
					{
						flag=1;
						tail.next=node;
						node.before=head.next;//���ø��׽ڵ�
					}
					else {
						tail=tail.next;
						tail3=tail3.next;
					}
				}
				  else {
					  break;
				  }		
			}
			if(flag==0)
			{
				node.next=tail3.next;
				tail3.next=node;	
				node.before=head.next;//���ø��׽ڵ�
			}
		}
		else {//��ǰopenΪ��
			head.next=node;
			node.deep=0;
			node.fn=node.wn+node.deep;
		}		
	}
	public  static void Add2(Node head,int a[][],int n) {//�滻��ͬ��fn�ϴ�ľ���
		System.out.println("����Add1�ľ���");
		Node node=new Node(a);
		Node tail=head;
		node.before=head.next;
		while(tail.next!=null)
		{
			   int flag1=1;
				for(int i=0;i<count;i++)
				{
					for(int j=0;j<count;j++)
					{
						if(a[i][j]!=tail.next.data[i][j])
						{
							flag1=0;
						}
					}
				}
				if(flag1==1)//������Ⱦ��� �滻�ظ�����
				{
					node.next=tail.next.next;
					tail.next=node;
				}
				else {
					tail=tail.next;
				}
		}
	}
	public  static void Delete(Node head)//����չ���� open�ڵ�һ������
	{
		Node tail=head.next;
		Node tail1=head;
		head.next=tail.next;
	}
	public  static int Compareopen(Node head,int a[][],int n)//��ǰ���� �Ƿ��� open��  �����fn��С����1
	{
		Node tail=open;
		int fn=n+head.next.deep+1;
		while(tail.next!=null)
		{
		   int flag1=1;
			for(int i=0;i<count;i++)
			{
				for(int j=0;j<count;j++)
				{
					if(a[i][j]!=tail.next.data[i][j])
					{
						
						flag1=0;
					}
				}
			}
			if(flag1==1)//������Ⱦ���
			{
				if(fn<tail.next.fn)
				{
					return 1;
				}
			}
			else {
				tail=tail.next;
			}
		}
		if(tail.next==null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	public  static int Compareclosed(Node head,int a[][],int n)//��ǰ���� �Ƿ��� closed��  �����fn��С����1
	{
		Node tail=closed;
		
	    if(tail.next==null)
	    {
	    	return 0;
	    }
	    else {
	    	int fn=n+head.next.deep+1;
	    	while(tail.next!=null)
			{
			   int flag1=1;
				for(int i=0;i<count;i++)
				{
					for(int j=0;j<count;j++)
					{
						if(a[i][j]!=tail.next.data[i][j])
						{
							
							flag1=0;
						}
					}
				}
				if(flag1==1)//������Ⱦ���
				{
					if(fn<tail.next.fn)
					{
						return 1;
					}
				}
				else {
					tail=tail.next;
				}	
			}
			if(tail.next==null) {
				return 0;
			}
			else {
				return 1;
			}
	    }	
	}
	public  static void Move(Node head)//�ƶ�0  ������open
	{
		System.out.println("����Move");
		Node tail2=head;
		int flag=0;
		while(tail2.next!=null)
		{
			for(int i=0;i<count;i++)
			{
				for(int j=0;j<count;j++)
				{
					if(head.next.data[i][j]!=b[i][j])
					{
						flag=1;
					}
				}
			}
			tail2=tail2.next;	
		}
		System.out.println("flag:"+flag);
		if(flag==1)
		{
			System.out.println("�ƶ�");
			int m=0;
			Node tail=null;		
			Node tail1=head;		
			Findw1(open.next.data);
			System.out.println("x y:"+x+" "+y);
			if(x-1>=0)//�����ƶ�
			{
				System.out.println("��������");
				Findw1(open.next.data);
				System.out.println("x y:"+x+" "+y);
				Findw2(open);
				System.out.println("x1 y1:"+x1+" "+y1);
				if((x-1)!=x1)
				{
					System.out.println("���ϲ��ظ�");
					tail=head;
					int [][]v=new int[20][20];
					int hn=0;
					for(int i=0;i<count;i++)
					{
						for(int j=0;j<count;j++)
						{
							v[i][j]=head.next.data[i][j];
						}
					}				
					m=v[x][y];
					v[x][y]=v[x-1][y];
					v[x-1][y]=m;	
					for(int i=0;i<count;i++)
					{
						for(int j=0;j<count;j++)
						{
							if(!(i==xx&&j==yy))
							{
								if(v[i][j]!=b[i][j])
									hn++;
							}
						}
					}
					System.out.println("�����open closed��"+Compareopen(open,v,hn)+" "+Compareclosed(closed,v,hn));
					if(Compareopen(open,v,hn)!=1&&Compareclosed(closed,v,hn)!=1)
					{
						Add1(open,v,hn);
					}
					else if(Compareopen(open,v,hn)==1)
					{
						Add2(open,v,hn);
					}
					else if(Compareclosed(closed,v,hn)==1)
					{
						Addclosed2(closed,v,hn);
					}
					System.out.println("���Ͻ�����open�ھ���");
					while(tail.next!=null)
					{
					
						for(int i=0;i<count;i++)
						{
							for(int j=0;j<count;j++)
							{
								System.out.print(tail.next.data[i][j]+"	");
							}
							System.out.print("\n");
						}
						System.out.println("hn fn:"+tail.next.wn+" | "+tail.next.fn);
						tail=tail.next;
					}
				}
			}		
			if(x+1<count)//����
			{
				System.out.println("��������");
				Findw1(open.next.data);
				System.out.println("x y:"+x+" "+y);
				Findw2(open);
				System.out.println("x1 y1:"+x1+" "+y1);
				if((x+1)!=x1)
				{
					System.out.println("���²��ظ�");
					System.out.println("���£�"+x+count);
					int [][]v=new int[20][20];
					tail=head;
					int hn=0;
					for(int i=0;i<count;i++)
					{
						for(int j=0;j<count;j++)
						{
							v[i][j]=head.next.data[i][j];
						}
					}
					System.out.println("��⣺"+x+count);
					m=v[x][y];
					v[x][y]=v[x+1][y];
					v[x+1][y]=m;	
					for(int i=0;i<count;i++)
					{
						for(int j=0;j<count;j++)
						{
							if(!(i==xx&&j==yy))
							{
								if(v[i][j]!=b[i][j])
									hn++;
							}
						}
					}
					System.out.println("�����open closed��"+Compareopen(open,v,hn)+" "+Compareclosed(closed,v,hn));
					if(Compareopen(open,v,hn)!=1&&Compareclosed(closed,v,hn)!=1)
					{
						Add1(open,v,hn);
					}
					else if(Compareopen(open,v,hn)==1)
					{
						Add2(open,v,hn);
					}
					else if(Compareclosed(closed,v,hn)==1)
					{
						Addclosed2(closed,v,hn);
					}
					while(tail.next!=null)
					{
						System.out.println("���½�����open�ڵľ���");
						for(int i=0;i<count;i++)
						{
							for(int j=0;j<count;j++)
							{
								System.out.print(tail.next.data[i][j]+"	");
							}
							System.out.print("\n");
						}
						System.out.println("hn fn:"+tail.next.wn+" | "+tail.next.fn);
						tail=tail.next;
					}
				}

			}
			if(y-1>=0)//����
			{
				System.out.println("��������");
				Findw1(open.next.data);
				System.out.println("x y:"+x+" "+y);
				Findw2(open);
				System.out.println("x1 y1:"+x1+" "+y1);
				if((y-1)!=y1)
				{
					System.out.println("�����ظ�");
					tail=head;
					int [][]v=new int[20][20];
					int hn=0;
					for(int i=0;i<count;i++)
					{
						for(int j=0;j<count;j++)
						{
							v[i][j]=head.next.data[i][j];
						}
					}
					System.out.println("��⣺"+x+count);
					m=v[x][y];
					v[x][y]=v[x][y-1];
					v[x][y-1]=m;	
					for(int i=0;i<count;i++)
					{
						for(int j=0;j<count;j++)
						{
							if(!(i==xx&&j==yy))
							{
								if(v[i][j]!=b[i][j])
									hn++;
							}
						}
					}
					System.out.println("�����open closed��"+Compareopen(open,v,hn)+" "+Compareclosed(closed,v,hn));
					if(Compareopen(open,v,hn)!=1&&Compareclosed(closed,v,hn)!=1)
					{
						Add1(open,v,hn);
					}
					else if(Compareopen(open,v,hn)==1)
					{
						Add2(open,v,hn);
					}
					else if(Compareclosed(closed,v,hn)==1)
					{
						Addclosed2(closed,v,hn);
					}
					tail=head;
					while(tail.next!=null)
					{
						System.out.println("���󽻻���open�ڵľ���");
						for(int i=0;i<count;i++)
						{
							for(int j=0;j<count;j++)
							{
								System.out.print(tail.next.data[i][j]+"	");
							}
							System.out.print("\n");
						}
						System.out.println("hn fn:"+tail.next.wn+" | "+tail.next.fn);
						tail=tail.next;
					}
				}

			}
				if(y+1<count)//����
				{
					System.out.println("��������");
					Findw1(open.next.data);
					System.out.println("x y:"+x+" "+y);
					Findw2(open);
					System.out.println("x1 y1:"+x1+" "+y1);
					if((y+1)!=y1)
					{
						System.out.println("���Ҳ��ظ�");
						tail=head;
						int [][]v=new int[20][20];
						int hn=0;
						for(int i=0;i<count;i++)
						{
							for(int j=0;j<count;j++)
							{
								v[i][j]=head.next.data[i][j];
							}
						}
						System.out.println("��⣺"+x+count);
						m=v[x][y];
						v[x][y]=v[x][y+1];
						v[x][y+1]=m;	
						for(int i=0;i<count;i++)
						{
							for(int j=0;j<count;j++)
							{
								if(!(i==xx&&j==yy))
								{
									if(v[i][j]!=b[i][j])
										hn++;
								}
							}
						}					
						System.out.println("�����open closed��"+Compareopen(open,v,hn)+" "+Compareclosed(closed,v,hn));
						if(Compareopen(open,v,hn)!=1&&Compareclosed(closed,v,hn)!=1)
						{
							Add1(open,v,hn);
						}
						else if(Compareopen(open,v,hn)==1)
						{
							Add2(open,v,hn);
						}
						else if(Compareclosed(closed,v,hn)==1)
						{
							Addclosed2(closed,v,hn);
						}
						tail=head;
						while(tail.next!=null)
						{
							System.out.println("���ҽ�����open�ڵľ���");
							for(int i=0;i<count;i++)
							{
								for(int j=0;j<count;j++)
								{
									System.out.print(tail.next.data[i][j]+"	");
								}
								System.out.print("\n");
							}
							System.out.println("hn fn:"+tail.next.wn+" | "+tail.next.fn);
							tail=tail.next;
						}
					}
				}
				Addclosed1(open);
				System.out.println("�ƶ��꣬ɾȥopen�еĵ�һ������");
				Delete(head);
				while(tail1.next!=null)
				{
					for(int i=0;i<count;i++)
						{
							for(int j=0;j<count;j++)
							{
								System.out.print(tail1.next.data[i][j]+"	");
							}
							System.out.print("\n");
						}
						System.out.println("hn fn:"+tail1.next.wn+" | "+tail1.next.fn);
						tail1=tail1.next;
				}
				Move(head);
			
		}
		else {
			System.out.println("�ﵽĿ��ڵ㣺");
			for(int i=0;i<count;i++)
			{
				for(int j=0;j<count;j++)
					{
						System.out.print(head.next.data[i][j]+"	");
					}
					System.out.print("\n");
			}
		}
	}
	public static void main(String[] args) {
		System.out.println("�������Ѿ����úó�ʼ��������Ŀ���������ֻ��������Ӧά����");
		System.out.println("����ά����");
		Scanner sc1 = new Scanner(System.in);		
		count=sc1.nextInt();
		int hn=0;
		Node tail1=open;
//		System.out.println("�����ʼλ��Ԫ�أ�");
//		for(int i=0;i<count;i++)
//		{
//			for(int j=0;j<count;j++)
//			{
//				Scanner sc2 = new Scanner(System.in);		
//				x=sc2.nextInt();
//				a[i][j]=x;
//			}
//		}
		if(count==3)
		{
			a[0][0]=2;
			a[0][1]=8;
			a[0][2]=3;
			a[1][0]=1;
			a[1][1]=6;
			a[1][2]=4;
			a[2][0]=7;
			a[2][1]=0;
			a[2][2]=5;
			b[0][0]=1;
			b[0][1]=2;
			b[0][2]=3;
			b[1][0]=8;
			b[1][1]=0;
			b[1][2]=4;
			b[2][0]=7;
			b[2][1]=6;
			b[2][2]=5;		
//			a[0][0]=1;
//			a[0][1]=3;
//			a[0][2]=4;
//			a[1][0]=2;
//			a[1][1]=8;
//			a[1][2]=6;
//			a[2][0]=5;
//			a[2][1]=7;
//			a[2][2]=0;
//			b[0][0]=1;
//			b[0][1]=2;
//			b[0][2]=3;
//			b[1][0]=4;
//			b[1][1]=5;
//			b[1][2]=6;
//			b[2][0]=7;
//			b[2][1]=8;
//			b[2][2]=0;		
		}
		if(count==4)
		{
			a[0][0]=1;
			a[0][1]=2;
			a[0][2]=3;
			a[0][3]=4;
			a[1][0]=5;
			a[1][1]=6;
			a[1][2]=7;
			a[1][3]=8;
			a[2][0]=9;
			a[2][1]=10;
			a[2][2]=11;
			a[2][3]=12;
			a[3][0]=13;
			a[3][1]=0;
			a[3][2]=14;
			a[3][3]=15;
			b[0][0]=1;
			b[0][1]=2;
			b[0][2]=3;
			b[0][3]=4;
			b[1][0]=5;
			b[1][1]=6;
			b[1][2]=7;
			b[1][3]=8;
			b[2][0]=9;
			b[2][1]=10;
			b[2][2]=11;
			b[2][3]=12;
			b[3][0]=13;
			b[3][1]=14;
			b[3][2]=15;
			b[3][3]=0;
		}
		Findw1(a);
		Findw(b);
//		System.out.println("����Ŀ��λ��Ԫ�أ�");
//		for(int i=0;i<count;i++)
//		{
//			for(int j=0;j<count;j++)
//			{
//				Scanner sc2 = new Scanner(System.in);		
//				x=sc2.nextInt();
//				b[i][j]=x;
//			}
//		}
		System.out.println("x1 y1��"+x1+y1);
		System.out.println("x y��"+x+y);
		System.out.println("xx yy��"+xx+yy);
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<count;j++)
			{
				if(!(i==xx&&j==yy))
				{
					if(a[i][j]!=b[i][j])
					{
						hn=hn+1;
					}	
				}
			}
		}
		System.out.println("hn��"+hn);
		Add(open,a,hn);
		System.out.println("��ʼ����");
		while(tail1.next!=null)
		{
			for(int i=0;i<count;i++)
			{
				for(int j=0;j<count;j++)
				{
					System.out.print(tail1.next.data[i][j]+"	");
				}
				System.out.println("hn fn:"+tail1.next.wn+" | "+tail1.next.fn);
			}
			tail1=tail1.next;
		}
		System.out.println("Ŀ�����");
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<count;j++)
			{
				System.out.print(b[i][j]+"	");
			}
			System.out.print("\n");
		}
		System.out.println("Ŀ�����");
		Move(open);
		System.out.println("Ŀ�����");
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<count;j++)
			{
				System.out.print(b[i][j]+"	");
			}
			System.out.print("\n");
		}
	}
}
