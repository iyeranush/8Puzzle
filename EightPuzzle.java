import java.util.ArrayList;
import java.util.Scanner;



public class EightPuzzle {

	
	static ArrayList<Node> puzzle_list = new ArrayList<Node>();
	static ArrayList<Node> successor_list = new ArrayList<Node>();
	static int depth=0,count=0;
	
	
	static Scanner input_states = new Scanner(System.in);
	static Node init_state = new Node();
	static Node goal_state = new Node();

	private static void getUserinput(Node init_state, Node goal_state) {
		/*Get input from user*/
		
		int i=0, j=0;
		
		System.out.println("\nEnter Initial Puzzle:\n ");
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				System.out.println("initial["+i+"]["+j+"]: ");
				init_state.state[i][j] = input_states.nextInt();
				
			}
		}
		System.out.println("\nEnter Goal state:\n ");
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				System.out.println("goal_state["+i+"]["+j+"]: ");
				goal_state.state[i][j] = input_states.nextInt();
				
			}
		}
		
		System.out.println("\nInitial state:");
		init_state.display_state();
		System.out.println("\n");
		
		System.out.println("\nGoal state:");
		goal_state.display_state();
		System.out.println("\n");

		puzzle_list.add(init_state);
	}

	private static void astar() {
		/*Here a node is checked for being a goal or finding its blank tile*/
		
		boolean isGoal = isgoal(puzzle_list.get(depth).state);
		if(isGoal==false)
		{
			findBlankspace(puzzle_list.get(depth));
		}
		else
		{
			
			depth++;
			System.out.println("\nThe Search state list is:\n");
			for(Node obj:puzzle_list)
			{
				obj.display_state();
			}
			System.out.println("\nNumber of nodes generated: "+ depth);
			System.out.println("\nNumber of nodes expanded: "+ count);
		
			System.exit(0);
			
		}
				
		
	}
	
	
	private static void findBlankspace(Node current_array) {
		/*Blank tile is searched*/
		
		int row=0,col=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(current_array.state[i][j]==0)
				{
					row=i;
					col=j;
				}				
			}
		}
		
		
		if(row==0 && col==0)
		{
			successor_list.add(move_down(row,col,current_array));
			successor_list.add(move_right(row,col,current_array));

		}
		if(row==0 && col==2)
		{
			successor_list.add(move_down(row,col,current_array));
			successor_list.add(move_left(row,col,current_array));

		}
		if(row==2 && col==0)
		{
			successor_list.add(move_right(row,col,current_array));
			successor_list.add(move_up(row,col,current_array));
		}
		if(row==2 && col==2)
		{
			
			successor_list.add(move_up(row,col,current_array));
			successor_list.add(move_left(row,col,current_array));
			
		}
		if(row==0 && col==1)
		{
			successor_list.add(move_right(row,col,current_array));
			successor_list.add(move_down(row,col,current_array));
			successor_list.add(move_left(row,col,current_array));
		}
		if(row==1 && col==0)
		{
			successor_list.add(move_right(row,col,current_array));
			successor_list.add(move_down(row,col,current_array));
			successor_list.add(move_up(row,col,current_array));
		}
		if(row==1 && col==2)
		{
			successor_list.add(move_down(row,col,current_array));
			successor_list.add(move_up(row,col,current_array));
			successor_list.add(move_left(row,col,current_array));
		}
		if(row==2 && col==1)
		{
			successor_list.add(move_left(row,col,current_array));
			successor_list.add(move_up(row,col,current_array));
			successor_list.add(move_right(row,col,current_array));
			
		}
		if(row==1 && col==1)
		{
			successor_list.add(move_left(row,col,current_array));
			successor_list.add(move_up(row,col,current_array));
			successor_list.add(move_right(row,col,current_array));
			successor_list.add(move_down(row,col,current_array));
			
		}
		
		updateList();
		
	
	}
	
	private static void updateList(){
		puzzle_list.add(bestnode(successor_list));
		successor_list.clear();
		depth++;
		Node current = puzzle_list.get(depth);
		astar();
	}
	
	

	private static int check(Node temp) {
		
		int flag=0;
		/*For Printing the states in the Puzzle list*/
		
		for(Node obj:puzzle_list)
		{
			
			flag =0;
			for(int i =0; i<=2;i++)
			{
				for(int j=0;j<=2;j++)
				{
					if(temp.state[i][j]==obj.state[i][j])
					{
						flag=0;
						
					}
					else {flag=1;break;}
					
				}
				if(flag==1) break;
					
			}
			if(flag==0) return flag;
				
		}
		return flag;
	}

	private static Node bestnode(ArrayList<Node> successor) {
		/*This function calculates the best node among the successor nodes*/
		int temp=0,index=0;
		boolean first=true;
		for(Node obj1:successor)
		{
	
			if(check(obj1)==1)
			{
				if(first)
				{
				temp=obj1.f;
				first=false;
				}
				if(obj1.f<=temp)
				{
				temp=obj1.f;	
				index=successor.indexOf(obj1);
				}
			}
			
		}
		
		return successor.get(index);
	}

		
	
		private static Node move_up(int row, int col, Node cs) {
			
			int temp1;
			Node temp= new Node();
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					temp.state[i][j] = cs.state[i][j];			
				}
			}
			temp1=temp.state[row-1][col];
			temp.state[row-1][col]=0;
			temp.state[row][col]=temp1;
			temp.getFcost(goal_state,depth);
			count++;
			return temp;
		}	

	private static Node move_left(int row, int col, Node cs) {
		// One move left
		int temp1;
		Node temp= new Node();
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				temp.state[i][j] = cs.state[i][j];			
			}
		}
		temp1=temp.state[row][col-1];
		temp.state[row][col-1]=0;
		temp.state[row][col]=temp1;
		temp.getFcost(goal_state,depth);
		count++;
		return temp;
	}

	private static Node move_down(int row, int col, Node current_node) {
		// One move down
		int temp1;
		Node temp= new Node();
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				temp.state[i][j] = current_node.state[i][j];			
			}
		}
		temp1=temp.state[row+1][col];
		temp.state[row+1][col]=0;
		temp.state[row][col]=temp1;
		temp.getFcost(goal_state,depth);
		count++;
		return temp;
	}

	private static Node move_right(int row, int col, Node current_node) {
		 
		int temp1;
		Node temp= new Node();
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				temp.state[i][j] = current_node.state[i][j];			
			}
		
		}
		temp1=temp.state[row][col+1];
		temp.state[row][col+1]=0;
		temp.state[row][col]=temp1;
		temp.getFcost(goal_state,depth);
		
		count++;
		return temp;
		
	}

	private static boolean isgoal(int[][] current ) {
		
		/*To check if current state is the goal state*/
		
		boolean isGoal=true;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(goal_state.state[i][j]!=current[i][j])
				{isGoal=false; 
				
				return isGoal; }
			}
		}
		isGoal=true;
		puzzle_list.add(goal_state);
		return isGoal;
	}

	public static void main(String[] args) {

		EightPuzzle game= new EightPuzzle();
		game.getUserinput(init_state, goal_state);
		game.astar();
	
	}

}
