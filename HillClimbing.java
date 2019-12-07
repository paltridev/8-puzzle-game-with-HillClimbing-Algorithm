package code;

import java.util.Arrays;

public class HillClimbing{
	
	int n = 3;
	int[][] goal_state;
	int[][] initial_state;
	int[][] current_state = initial_state;
	public int[][] newInput;
	int[] up,down,right,left;
    int emptyTile_row = 0;
    int emptyTile_col = 0;
    int hUp,hDown,hRight,hLeft;
	public int currentHue;
	public int[] newInputarr;
	public static int row = 3, col=3, i, j, max_heu = 800;
    
    //to find the empty tile in the state
	public void findEmptyTile(int[][] newState) {

		for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[0].length; j++) {
                if (newState[i][j] == 0) {
                    emptyTile_row = i;
                    emptyTile_col = j;
                    
                }				
            }	
		}
	}
	
	//convert 2D Arrays into a single array
	public int[] convertInitialStateToArray() {
		int[] actualPosition = new int[9];
		int counter=0;
		
		for (int i = 0; i < initial_state.length; i++) {
            for (int j = 0; j < initial_state[0].length; j++) {
            	
            	actualPosition[counter] = initial_state[i][j];
                counter++;
            }	
		}
		
		return actualPosition;
	}
	
	public int[] convertGoalStateToArray() {
		int[] numPosition = new int[9];
		int counter=0;
		
		for (int i = 0; i < initial_state.length; i++) {
            for (int j = 0; j < initial_state[0].length; j++) {
            	
                numPosition[counter] = goal_state[i][j];
                counter++;
            }	
		}
		return numPosition;
	}
	
	//calculate the heuristic for each node state
	public int calHeuristic(int[][] newState){
	      int arrayPosition;
	      int tile;
	      int sum = 0;
	        for(int i = 0; i <n; i++){
	            for(int j = 0; j<n; j++){
	                tile = newState[i][j];
	                if(tile == 0) {
	                	continue;
	                }
	                arrayPosition = goal_state[i][j];
	                
	                if(arrayPosition-tile == 0) {
	                	sum = sum+100;
	                }
	                
	                double ii = Math.floor(((double)(tile-1))/this.n);
	                double jj = (tile-1)%this.n;

	                if ((Math.abs(i-ii)+Math.abs(j-jj)) == 1) {
		                sum = sum + 75;
		            }
	                else if ((Math.abs(i-ii)+Math.abs(j-jj)) == 2) {
	                sum = sum + 50;
	                }
	                else if ((Math.abs(i-ii)+Math.abs(j-jj)) == 3) {
		                sum = sum+25;
		            }
	                else if ((Math.abs(i-ii)+Math.abs(j-jj)) == 4) {
		                sum = sum+0;
		            }
	        }
	    }
		return sum;
}
	
	//create array posibility if empty tile swapped downwards
	public void swapAndCalDown(int[][] newState) {
		
		int down_state[][] = new int[newState.length][newState[0].length];
		
        for (int i = 0; i < newState.length; i++) {
        	
            for (int j = 0; j < newState[0].length; j++) {

                if ((i - 1) == emptyTile_row && j == emptyTile_col) {
                	//swap down
                    down_state[i][j] = newState[i - 1][j];
                    down_state[i - 1][j] = newState[i][j];
                } else {
                	//remain the same
                    down_state[i][j] = newState[i][j];
                }
            }
        }
        
        down = new int[9];
		int counter=0;
		
		for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[0].length; j++) {
            	
                down[counter] = down_state[i][j];
                counter++;
            }	
		}
		
		hDown = calHeuristic(down_state);
		
	}
	
	//create array posibility if empty tile swapped upwards
	public void swapAndCalUp(int[][] newState) {
		
		int up_state[][] = new int[newState.length][newState[0].length];
        for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[0].length; j++) {

                if (i == emptyTile_row && j == emptyTile_col) {
                	//swap up
                    up_state[i][j] = newState[i - 1][j];
                    up_state[i - 1][j] = newState[i][j];
                } else {
                	
                    up_state[i][j] = newState[i][j];
                }
            }
        }
       
        up = new int[9];
		int counter=0;
		
		for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[0].length; j++) {
            	
                up[counter] = up_state[i][j];
                counter++;
            }	
		}
		hUp = calHeuristic(up_state);
		
	}
	
	
	//create array posibility if empty tile swapped right
		public void swapAndCalRight(int[][] newState) {
			
			int right_state[][] = new int[newState.length][newState[0].length];
	        for (int i = 0; i < newState.length; i++) {
	            for (int j = 0; j < newState[0].length; j++) {

	            	if (i == emptyTile_row && j == emptyTile_col) {
	            		//swap right
	                    right_state[i][j] = newState[i][j + 1];
	                    right_state[i][j + 1] = newState[i][j];
	                    j++;
	                    
	                } else {
	                	
	                    right_state[i][j] = newState[i][j];
	                }
	            }
	        }
	       
	        right = new int[9];
			int counter=0;
			
			for (int i = 0; i < newState.length; i++) {
	            for (int j = 0; j < newState[0].length; j++) {
	            	
	                right[counter] = right_state[i][j];
	                counter++;
	            }	
			}
	       
			hRight = calHeuristic(right_state);
			
		}
		
		
		//create array posibility if empty tile swapped left
		public void swapAndCalLeft(int[][] newState) {
			
		int left_state[][] = new int[newState.length][newState[0].length];
		for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[0].length; j++) {

                if (i == emptyTile_row && j == emptyTile_col) {
                	//swap left
                    left_state[i][j] = newState[i][j - 1];
                    left_state[i][j - 1] = newState[i][j];
                } else {
                	
                    left_state[i][j] = newState[i][j];
                }
            }
        }
		
		 left = new int[9];
			int counter=0;
			
			for (int i = 0; i < newState.length; i++) {
	            for (int j = 0; j < newState[0].length; j++) {
	            	
	                left[counter] = left_state[i][j];
	                counter++;
	            }	
			}
			
			hLeft = calHeuristic(left_state);
		
	}
		
		
		//each possible rule for empty tile to move
		public void calOtherHeuristics(int[][] newState) {
			
			findEmptyTile(newState);
			
			if (emptyTile_row == 0 && emptyTile_col == 0) {
				//0,0 position is empty tile
				swapAndCalDown(newState);
				swapAndCalRight(newState);
				
	            

	        } else if (emptyTile_row == 0 && emptyTile_col == 1) {
	        	//0,1 position is empty tile
	        	swapAndCalLeft(newState);
	        	swapAndCalDown(newState);
	        	swapAndCalRight(newState);
	           

	        } else if (emptyTile_row == 0 && emptyTile_col == 2) {
	        	//0,2 position is empty tile
	        	swapAndCalLeft(newState);
	        	swapAndCalDown(newState);
	         

	        } else if (emptyTile_row == 1 && emptyTile_col == 0) {
	        	//1,0 position is empty tile
	        	swapAndCalDown(newState);
	        	swapAndCalRight(newState);
	        	swapAndCalUp(newState);
	       

	        } else if (emptyTile_row == 1 && emptyTile_col == 1) {
	        	//1,1 position is empty tile
	        	swapAndCalDown(newState);
	        	swapAndCalLeft(newState);
	        	swapAndCalRight(newState);
	        	swapAndCalUp(newState);
	            

	        } else if (emptyTile_row == 1 && emptyTile_col == 2) {
	        	//1,2 position is empty tile
	            swapAndCalUp(newState);
	        	swapAndCalLeft(newState);
	        	swapAndCalDown(newState);
	            

	        } else if (emptyTile_row == 2 && emptyTile_col == 0) {
	        	//2,0 position is empty tile
	           swapAndCalRight(newState);
	           swapAndCalUp(newState);

	        } else if (emptyTile_row == 2 && emptyTile_col == 1) {
	        	//2,1 position is empty tile
	        	swapAndCalLeft(newState);
	        	swapAndCalRight(newState);
	        	swapAndCalUp(newState);

	        } else if (emptyTile_row == 2 && emptyTile_col == 2) {
	        	//2,2 position is empty tile
	        	swapAndCalLeft(newState);
	        	swapAndCalUp(newState);
	        }
			
		}
		
		public void compareHeuScore() {
			
			
			   while(currentHue != max_heu) {
				   
				   System.out.println("\nEnter new State of puzzle:");
				   newInput = TestCodeMain.InputIntoArray();
				   
				   newInputarr = new int[9];
					int counter=0;
					
					for (int i = 0; i < newInput.length; i++) {
			            for (int j = 0; j < newInput[0].length; j++) {
			            	
			            	newInputarr[counter] = newInput[i][j];
			                counter++;
			            }	
					}
					
					

					
					if(Arrays.equals(newInputarr, up) || Arrays.equals(newInputarr, down) || Arrays.equals(newInputarr, left) || Arrays.equals(newInputarr, right)) {
						
					
						if (calHeuristic(newInput) > currentHue) {
							System.out.println("\nCurrent state");
							TestCodeMain.DisplayArray(newInput);
							int[][] x = newInput;
							calOtherHeuristics(x);
							currentHue = calHeuristic(newInput);
							current_state = newInput;
							System.out.println("Heuristic Score is "+currentHue);
						}
						
						else {
							System.out.println(" ");
							TestCodeMain.DisplayArray(newInput);
							System.out.println("Heuristic Score is "+calHeuristic(newInput));
							System.out.println("Try another state, This state is not a good one");
						}
		
					}
					else {
						System.out.println("Incorrect Input state, TRY AGAIN PLEASE!");
					}
				
			   }
			   
			   if (currentHue == max_heu) {
				   System.out.println("\nProblem solved");
			   }
		}
	
}
