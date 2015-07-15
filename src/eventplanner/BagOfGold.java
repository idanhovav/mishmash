package eventplanner;

import java.util.Scanner;

public class BagOfGold {
	
	//stores carrying capacity
	float cap;
	
	//stores number of bars
	int bars;
	
	//stores weights of bars
	float[] weights;
	
	//next row's possibilities that are already counted
	int[] nextpossibilities;
	
	//used to get user input
	Scanner scanner = new Scanner(System.in);
	

	public BagOfGold(float cap, int bars) {
		//initialize values
		this.cap = cap;
		this.bars = bars;
		
		//initialize size of weights array based on number of bars
		weights = new float[bars];
		
		//only need it for after 1st row, so bars-1
		nextpossibilities = new int[bars-1];
		
		//initialized to 0
		for(int i = 0; i < nextpossibilities.length; i++)
		{
			nextpossibilities[i] = 0;
		}
		
		/*3D array that stores in order:
		*the total weight of that combo
		*the weights of the bars in combo
		*
		*Therefore, the rows of the array is the different cardinalities
		*the squares in each row represent the types of combinations possible
		*within those squares are arrays that store the total weight of that combo first
		*then the number of sets w/cardinality 1 greater that start with that set
		*then the weights of the bars in that combo
		*It looks like a 2D array that has an array in each square 
		*/
		
		//nvm gotta break it up b/c not same amount of combos in each, so not a square
		float[][][] combos;
		
		//
		
		//user inputs weights, store weights in array
		for(int i = 0; i < weights.length; i++)
		{
			System.out.println("Please enter the weight of bar #" + i+1 + ": ");
			weights[i] = scanner.nextFloat();
		}
		
		
		//number of rows in array based on number of bars,
		//since each row represents all the combinations possible
		//with a specific cardinality
		combos = new float[bars][][];
		
		for(int i = 0; i < combos.length; i++)
		{
			//putting i+1 instead of i b/c 0th row stores cardinality 1, etc.
			//how many squares in each row? Based on all possible combos bars choose i
			//i + 1 so that we can store the weight of i bars and
			//the total weight of them (+1) and the total possible combos w/this beginning
			//in the next step (+1)
			combos[i] = new float[Combinations(bars, (i+1))][(i+1) + 2];
		}
		
		//loops through squares of first row of array
		for(int a = 0; a < combos[0].length; a++)
		{
			//loops through bricks 
			for(int b = 0; b < weights.length; b++)
			{
				//sum of combo
				combos[0][a][0] = weights[b];
				
				//bricks used in this combo
				combos[0][a][2] = weights[b];
				
				//next round num of combinatios w/that start
				//loops through weights to find the last brick of this set
				//subtract's that brick's index from weights length
				//to get the other possible sets for next round
				for(int i = 0; i < weights.length; i++)
				{
					if(weights[i] == combos[0][a][2])
					{
						//distance from last bar used to end of bars
						combos[0][a][0] = weights.length - (i+1);
						
						//starts at end of next possibilities
						combos[0][a][1] += nextpossibilities[0];
						
						//update number of next possibilities
						nextpossibilities[0] = (int)combos[0][a][1];
						
						//ADD ANOTHER BRICK TO THIS COMBO
						
					}
				}
				

			}
		}
		
		//looping through cardinalities
		for(int a = 1; a<combos.length; a++)
		{
			
			//looping through squares in cardinalities
			for(int b = 0; b < combos[a].length; b++)
			{
				
				//looping through starting point of next possibilities in previous
				//cardinality
				for(int c = 0; c < combos[a-1].length; c++)
				{
					//set the values of this combo based off combo we're building off of
					//we see which we're building off of by checking the 2nd value in the box
					if(b >= combos[a-1][c][1] && b < combos[a-1][c+1][1])
					{
						//putting values of previous row in this row
						for(int i = 0; i < combos[a-1][c].length; i++)
						{
							combos[a][b][i] = combos[a][c][i];
						}
						
						//now we build off of the copies of the previous row,
						//and add new brick, changing all values
						
			
					}
				}
			}
		}
		
		
		
		
		
		
		//cardinality of 2
	
		for(int b = 0; b < combos[0][0][2]; b++)	
		{
			//the first combo of cardinality 1's total weight is pasted
			//into all future combos of cardinality 2 that begin w/the same
			//brick that was used in the first combo of cardinality 1
			combos[1][b][0] = combos[0][0][1];
			
			//i can calc this later with the rest of the nextcombos
			//same way I did it last time
			combos[1][b][1] = -1;
			
			//adding bricks already used since all these combos are
			//just additions to original 0th combo of cardinality 1
			combos[1][b][2] = combos[0][0][2];
		}
		
		//starts at end of previous nextcombos, goes until end of this nextcombos
		for(int b = (int)combos[0][0][2] + 1; b < combos[0][1][2]; b++)
		{
			combos[1][b][0] = combos[0][1][1];
			combos[1][b][1] = -1;
			combos[1][b][2] = combos[0][1][2];
		}
		
		for(int a= 0; a < combos[1].length; a++)
		{
			
		}
		
		
		
		
	}

	public int Combinations(int n, int r)
	{
		//can't choose more out of less
		if(n < r)
		{
			return 0;
		}
		int numerator = factorial(n);
		int denominator = factorial(r)*factorial(n-r);
		int combinations = numerator/denominator;

		return combinations;
	}
	
	//a must be nonnegative
	public int factorial(int a)
	{
		if(a == 0)
		{
			return 1;
		}
		
		return a * (factorial(a-1));
	}
	
	
	//returns the number of unused bricks when given the bricks used
	//needed to figure out how many combinations are possible with each beginning
	/*
	 * Example: Array has 1, 2, 3. Choose two from that array:
	 * (1,2), (1,3), and (2,3)
	 * The first array of 1, the set of (1) has 2 other bricks, (2,3), and so there
	 * are two more possible arrays of cardinality two when adding 1 more element to
	 * the original set (1).
	 * 
	 * But should only be done from left to right, does that make sense?
	 * As in (1) has 2 elements after it, so 2 possibilities
	 * (2) has 1 element after it, so 1 more possibility
	 * (3) has 0 elements after it, so 0 more possibilities
	 * 
	 * 
	 * (1,2,3,4)
	 * (1),      (2),      (3),      (4)
	 * (1,2),    (1,3),    (1,4),    (2,3),    (2,4)
	 * (1,2,3),  (1,2,4),  (1,3,4),  (2,3,4)
	 * (1,2,3,4)
	 * 
	 * 
	 * here's the pattern: the number of elements left to the right of the
	 * LAST element of the set is how many sets can be made from that set by adding 1
	 * w/o double counting or overlapping elsewhere.
	 * 
	 * So (2,3) produces 1 more, (2,3,4) in next round b/c (4) is to the right of (3)
	 * 
	 * 
	 * we could just use the index of the last brick in each combo to see how many
	 * more there are. just do array.length - lastbrickindex
	 */
	public int unused()
	{
		
		
		return -1;
	}
}
