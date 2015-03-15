import java.lang.Math;

public class Node {

	int [][] state = new int[3][3];
	Node parent;
	
	int f,g,h;
	
	public Node()
	{
		parent = null;
		f=0;
		g=0;
		h=0;
	
	}
		
	public void display_state() {
		
		
		System.out.println(" ");
		for(int i=0;i<3;i++)
		{
			System.out.print("\n");
			for(int j=0;j<3;j++)
			{
				System.out.print("\t" + this.state[i][j]);
				
			}
		}
	
	}
	
	public void getFcost(Node goal_state, int dist){
		int h= this.getHeuristics(goal_state);
		this.h=h;
		this.g=dist;
		this.f=h+dist;
	
	}
	
	public int getHeuristics(Node goal_state){
		
		int h=0,hnew=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(this.state[i][j]==goal_state.state[i][j])
					{
					h=h+0;
					}
				else
				{
					
					for(int r=0;r<3;r++)
					{
						for(int c=0;c<3;c++)
						{
							if(this.state[i][j]==goal_state.state[r][c])
							{
								hnew=Math.abs(i-r)+Math.abs(j-c);
								
							}
						}
					}
					h=h+hnew;
				}
			}
		}
		return h;
	
	}
	
}
