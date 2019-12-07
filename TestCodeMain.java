package code;


import java.util.Scanner;

	public class TestCodeMain
	{
		
		public static int row = 3, col=3, i, j, max_heu = 800;
		
		
		//Input data into 2D array
		public static int[][] InputIntoArray(){
			
		       int arr[][] = new int[row][col];
		       
			   Scanner scan = new Scanner(System.in);
			   
		       // enter array elements.
		       System.out.println("Enter " +(row*col)+ " Array Elements : ");
		       for(i=0; i<row; i++)
		       {
		           for(j=0; j<col; j++)
		           {
		        	   int x = scan.nextInt();
		        	   if (x > 9) {
		        		   System.out.println("The element should be between 0-9");
		        		   x = scan.nextInt();
		        		   arr[i][j] = x;
		        	   }
		        	   else {
		               arr[i][j] = x;
		        	   }
		           }
		       }
			 
			return arr;
		   }
		
		//display the puzzle
		public static void DisplayArray(int[][] arr) {
			
			for(i=0; i<row; i++)
		       {
		           for(j=0; j<col; j++)
		           {
		               System.out.print( arr[i][j]+ "  ");
		           }
		           System.out.println();
		       }
		}
		
		
		
		
		
		
	   public static void main(String args[])
	   {
		   
		   
		   HillClimbing c = new HillClimbing();
		   
		   System.out.println("initial state");
		   c.initial_state= InputIntoArray();
		   
		   System.out.println();
		   System.out.println("The initial State Matrix is: ");
		   DisplayArray(c.initial_state);
		   
		   System.out.println("\n\nGoal state");
		   c.goal_state= InputIntoArray();
		   
		   System.out.println();
		   System.out.println("The Goal State Matrix to reach is: ");
		   DisplayArray(c.goal_state);
		   
		   c.findEmptyTile(c.initial_state);

		   System.out.println("\n");
		   c.currentHue = c.calHeuristic(c.initial_state);
		   c.calOtherHeuristics(c.initial_state);
		   System.out.println("\nCurrent state");
		   DisplayArray(c.initial_state);
		   System.out.println("Heuristic Score is "+c.currentHue);
		   
		   c.compareHeuScore();
		   
		   
	   }

}

