package chanshengshi;

import java.util.Scanner;

public class baibanA  {
	static int n;
	static int count;//维数
	static int size=0;
	static int [][]a=new int[20][20];
	static int [][]b=new int[20][20];
	static int [][]c=new int[20][20];
	static int x;//找到初始0 行位置
	static int y;//找到初始0 列位置
	static int xx;//找到目的0 行位置
	static int yy;//找到目的0 列位置
	static int x1;//找到父节点0 行位置
	static int y1;//找到父节点0 列位置
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
	public  static void Findw1(int a[][])//找初始矩阵中0的位置
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
	public  static void Findw(int a[][])//找目标矩阵中0的位置
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
	public  static void Findw2(Node head)//父节点的 0位置
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
	public  static void Add(Node head,int a[][],int n) {	//加入开始节点入open  
		System.out.println("进入Add");
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
		System.out.println("Add后的open：");
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
	public  static void Addclosed1(Node head) {	//将open内 第一个 节点 入closed
		System.out.println("进入Addclosed1");
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
			System.out.println("closed内矩阵：");
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
	public  static void Addclosed2(Node head,int a[][],int n ){	//替换closed内 相同节点且fn较大
		System.out.println("进入Addclosed2");
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
				if(flag1==1)//出现相等矩阵 替换重复矩阵
				{
					node.next=tail.next.next;
					tail.next=node;
				}
				else {
					tail=tail.next;
				}
		}
	}
	public  static void Add1(Node head,int a[][],int n) {	//升序加入open  深度为进入的head.deep加1
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
					if(tail.next==null)//当前矩阵要插入最后
					{
						flag=1;
						tail.next=node;
						node.before=head.next;//设置父亲节点
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
				node.before=head.next;//设置父亲节点
			}
		}
		else {//当前open为空
			head.next=node;
			node.deep=0;
			node.fn=node.wn+node.deep;
		}		
	}
	public  static void Add2(Node head,int a[][],int n) {//替换相同且fn较大的矩阵
		System.out.println("进入Add1的矩阵：");
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
				if(flag1==1)//出现相等矩阵 替换重复矩阵
				{
					node.next=tail.next.next;
					tail.next=node;
				}
				else {
					tail=tail.next;
				}
		}
	}
	public  static void Delete(Node head)//移走展开的 open内第一个矩阵
	{
		Node tail=head.next;
		Node tail1=head;
		head.next=tail.next;
	}
	public  static int Compareopen(Node head,int a[][],int n)//当前矩阵 是否在 open中  相等且fn较小返回1
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
			if(flag1==1)//出现相等矩阵
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
	public  static int Compareclosed(Node head,int a[][],int n)//当前矩阵 是否在 closed中  相等且fn较小返回1
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
				if(flag1==1)//出现相等矩阵
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
	public  static void Move(Node head)//移动0  并放入open
	{
		System.out.println("进入Move");
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
			System.out.println("移动");
			int m=0;
			Node tail=null;		
			Node tail1=head;		
			Findw1(open.next.data);
			System.out.println("x y:"+x+" "+y);
			if(x-1>=0)//向上移动
			{
				System.out.println("进入向上");
				Findw1(open.next.data);
				System.out.println("x y:"+x+" "+y);
				Findw2(open);
				System.out.println("x1 y1:"+x1+" "+y1);
				if((x-1)!=x1)
				{
					System.out.println("向上不重复");
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
					System.out.println("检测结果open closed："+Compareopen(open,v,hn)+" "+Compareclosed(closed,v,hn));
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
					System.out.println("向上交换后open内矩阵：");
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
			if(x+1<count)//向下
			{
				System.out.println("进入向下");
				Findw1(open.next.data);
				System.out.println("x y:"+x+" "+y);
				Findw2(open);
				System.out.println("x1 y1:"+x1+" "+y1);
				if((x+1)!=x1)
				{
					System.out.println("向下不重复");
					System.out.println("向下："+x+count);
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
					System.out.println("检测："+x+count);
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
					System.out.println("检测结果open closed："+Compareopen(open,v,hn)+" "+Compareclosed(closed,v,hn));
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
						System.out.println("向下交换后open内的矩阵：");
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
			if(y-1>=0)//向左
			{
				System.out.println("进入向左");
				Findw1(open.next.data);
				System.out.println("x y:"+x+" "+y);
				Findw2(open);
				System.out.println("x1 y1:"+x1+" "+y1);
				if((y-1)!=y1)
				{
					System.out.println("向左不重复");
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
					System.out.println("检测："+x+count);
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
					System.out.println("检测结果open closed："+Compareopen(open,v,hn)+" "+Compareclosed(closed,v,hn));
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
						System.out.println("向左交换后open内的矩阵：");
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
				if(y+1<count)//向右
				{
					System.out.println("进入向右");
					Findw1(open.next.data);
					System.out.println("x y:"+x+" "+y);
					Findw2(open);
					System.out.println("x1 y1:"+x1+" "+y1);
					if((y+1)!=y1)
					{
						System.out.println("向右不重复");
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
						System.out.println("检测："+x+count);
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
						System.out.println("检测结果open closed："+Compareopen(open,v,hn)+" "+Compareclosed(closed,v,hn));
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
							System.out.println("向右交换后open内的矩阵：");
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
				System.out.println("移动完，删去open中的第一个矩阵：");
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
			System.out.println("达到目标节点：");
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
		System.out.println("代码中已经设置好初始数码矩阵和目标数码矩阵，只需输入相应维数！");
		System.out.println("输入维数：");
		Scanner sc1 = new Scanner(System.in);		
		count=sc1.nextInt();
		int hn=0;
		Node tail1=open;
//		System.out.println("输入初始位置元素：");
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
//		System.out.println("输入目标位置元素：");
//		for(int i=0;i<count;i++)
//		{
//			for(int j=0;j<count;j++)
//			{
//				Scanner sc2 = new Scanner(System.in);		
//				x=sc2.nextInt();
//				b[i][j]=x;
//			}
//		}
		System.out.println("x1 y1："+x1+y1);
		System.out.println("x y："+x+y);
		System.out.println("xx yy："+xx+yy);
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
		System.out.println("hn阵："+hn);
		Add(open,a,hn);
		System.out.println("初始矩阵：");
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
		System.out.println("目标矩阵：");
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<count;j++)
			{
				System.out.print(b[i][j]+"	");
			}
			System.out.print("\n");
		}
		System.out.println("目标矩阵：");
		Move(open);
		System.out.println("目标矩阵：");
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
