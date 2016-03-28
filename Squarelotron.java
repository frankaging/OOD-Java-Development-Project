package squarelotron;

import java.util.*;

public class Squarelotron implements SquarelotronMethods{

	/*
	 * instance variables
	 */
	public int[][] squarelotron;
	public int size;

	/**
	 * constructor
	 * @param n - the dimension of the squarelotron
	 */
	public Squarelotron(int n){
		this.squarelotron = new int[n][n];
		size = n;
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				this.squarelotron[i][j] = (i*n)+j+1;
			}
		}
	}

	/**
	 * additional constructor
	 * @param array - 1d array convert to a squarelotron
	 */
	public Squarelotron(int[] array){
		Squarelotron newS = Squarelotron.makeSquarelotron(array);
		this.squarelotron = new int[newS.size][newS.size];
		this.size = newS.size;
		// deep-copy the board
		for(int i=0; i<newS.size; i++){
			for(int j=0; j<newS.size; j++){
				this.squarelotron[i][j] = newS.squarelotron[i][j];
			}
		}
	}

	/**
	 * method to construct a Squarelotron using an array
	 * @param array - pass in array
	 * @return - a new Squarelotron class
	 */
	public static Squarelotron makeSquarelotron(int[] array){
		// test if it is a perfect square length
		int sqrt = (int) Math.sqrt(array.length);
		if(sqrt*sqrt != array.length){
			throw new IllegalArgumentException("bad array provided");
		}
		// test if the element is an integer between 0 to 99
		for(int i=0; i<array.length; i++){
			if(array[i] < 0 || array[i] >99 || array[i] != (int)array[i]){
				throw new IllegalArgumentException("bad array provided");
			}
		}
		// if pass the previous test, we need to construct squarelotron
		Squarelotron s= new Squarelotron(sqrt);
		for(int i=0; i<sqrt; i++){
			for(int j=0; j<sqrt; j++){
				s.squarelotron[i][j] = array[(i*sqrt)+j];
			}
		}
		s.size = sqrt;
		return s;
	}

	/**
	 *  convert the squarelotron board to a 1-D array
	 */
	@Override
	public int[] numbers() {
		int[] result = new int[this.size * this.size];
		for(int i=0; i<result.length; i++){
			result[i] = this.squarelotron[i/this.size][i-((i/this.size)*this.size)];
		}
		return result;
	}

	/**
	 * flip the squarelotron with the indicated ring number
	 * flip direction - upsideDown
	 * @param ring - ring number
	 */
	@Override
	public Squarelotron upsideDownFlip(int ring) {
		// deep copy the board out
		int[][] deepCopy1 = new int[this.size][this.size];
		int[][] deepCopy2 = new int[this.size][this.size];
		for(int i=0; i<this.size; i++){
			for(int j=0; j<this.size; j++){
				deepCopy1[i][j] = this.squarelotron[i][j];
				deepCopy2[i][j] = this.squarelotron[i][j];
			}
		}
		// manipulating on deepCopy2, upsideDownFlip on deepCopy2
		for(int i=0; i<this.size/2; i++){
			int[] temp = deepCopy2[i];
			deepCopy2[i] = deepCopy2[deepCopy2.length - i - 1];
			deepCopy2[deepCopy2.length - i - 1] = temp;
		}
		// get the wanted ring on deepCopy2, and copy those numbers onto deepCopy1
		// upper bound
		for(int j=ring-1; j<this.size-ring; j++){
			deepCopy1[ring-1][j] = deepCopy2[ring-1][j];
		}
		// right bound
		for(int i=ring-1; i<this.size-ring; i++){
			deepCopy1[i][this.size-ring] = deepCopy2[i][this.size-ring];
		}
		// lower bound
		for(int j=this.size-ring; j>ring-1; j--){
			deepCopy1[this.size-ring][j] = deepCopy2[this.size-ring][j];
		}
		// left bound
		for(int i=this.size-ring; i>ring-1; i--){
			deepCopy1[i][ring-1] = deepCopy2[i][ring-1];
		}
		// use deepCopy1 to get a new squarelotron
		Squarelotron s= new Squarelotron(this.size);
		for(int i=0; i<s.size; i++){
			for(int j=0; j<s.size; j++){
				s.squarelotron[i][j] = deepCopy1[i][j];
			}
		}
		return s;
	}

	/**
	 * flip the squarelotron with the indicated ring number
	 * flip direction - leftRightSide
	 * @param ring - ring number
	 */
	@Override
	public Squarelotron leftRightFlip(int ring) {
		// deep copy the board out
		int[][] deepCopy1 = new int[this.size][this.size];
		int[][] deepCopy2 = new int[this.size][this.size];
		for(int i=0; i<this.size; i++){
			for(int j=0; j<this.size; j++){
				deepCopy1[i][j] = this.squarelotron[i][j];
				deepCopy2[i][j] = this.squarelotron[i][j];
			}
		}
		// manipulating on deepCopy2, leftRightFlip on deepCopy2
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < (this.size + 1) / 2; j++) {
				int temp = deepCopy2[i][j];
				deepCopy2[i][j] = deepCopy2[i][this.size - 1 - j];
				deepCopy2[i][this.size - 1 - j] = temp;
			}
		}
		// get the wanted ring on deepCopy2, and copy those numbers onto deepCopy1
		// upper bound
		for(int j=ring-1; j<this.size-ring; j++){
			deepCopy1[ring-1][j] = deepCopy2[ring-1][j];
		}
		// right bound
		for(int i=ring-1; i<this.size-ring; i++){
			deepCopy1[i][this.size-ring] = deepCopy2[i][this.size-ring];
		}
		// lower bound
		for(int j=this.size-ring; j>ring-1; j--){
			deepCopy1[this.size-ring][j] = deepCopy2[this.size-ring][j];
		}
		// left bound
		for(int i=this.size-ring; i>ring-1; i--){
			deepCopy1[i][ring-1] = deepCopy2[i][ring-1];
		}
		// use deepCopy1 to get a new squarelotron
		Squarelotron s= new Squarelotron(this.size);
		for(int i=0; i<s.size; i++){
			for(int j=0; j<s.size; j++){
				s.squarelotron[i][j] = deepCopy1[i][j];
			}
		}
		return s;
	}

	/**
	 * inverse the squarelotron with the indicated ring number
	 * inverse direction - anti-transpose
	 * @param ring - ring number
	 */
	@Override
	public Squarelotron inverseDiagonalFlip(int ring) {
		// deep copy the board out
		int[][] deepCopy1 = new int[this.size][this.size];
		int[][] deepCopy2 = new int[this.size][this.size];
		for(int i=0; i<this.size; i++){
			for(int j=0; j<this.size; j++){
				deepCopy1[i][j] = this.squarelotron[i][j];
				deepCopy2[i][j] = this.squarelotron[i][j];
			}
		}
		// manipulating on deepCopy2, inverseDiagonalFlip on deepCopy2
		int[][] temp = new int[this.size][this.size];
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				temp[i][j] = deepCopy2[this.size-j-1][this.size-i-1];
			}
		}
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				deepCopy2[i][j] = temp[i][j];
			}
		}
		// get the wanted ring on deepCopy2, and copy those numbers onto deepCopy1
		// upper bound
		for(int j=ring-1; j<this.size-ring; j++){
			deepCopy1[ring-1][j] = deepCopy2[ring-1][j];
		}
		// right bound
		for(int i=ring-1; i<this.size-ring; i++){
			deepCopy1[i][this.size-ring] = deepCopy2[i][this.size-ring];
		}
		// lower bound
		for(int j=this.size-ring; j>ring-1; j--){
			deepCopy1[this.size-ring][j] = deepCopy2[this.size-ring][j];
		}
		// left bound
		for(int i=this.size-ring; i>ring-1; i--){
			deepCopy1[i][ring-1] = deepCopy2[i][ring-1];
		}
		// use deepCopy1 to get a new squarelotron
		Squarelotron s= new Squarelotron(this.size);
		for(int i=0; i<s.size; i++){
			for(int j=0; j<s.size; j++){
				s.squarelotron[i][j] = deepCopy1[i][j];
			}
		}
		return s;
	}

	/**
	 * inverse the squarelotron with the indicated ring number
	 * inverse direction - transpose
	 * @param ring - ring number
	 */
	@Override
	public Squarelotron mainDiagonalFlip(int ring) {
		// deep copy the board out
		int[][] deepCopy1 = new int[this.size][this.size];
		int[][] deepCopy2 = new int[this.size][this.size];
		for(int i=0; i<this.size; i++){
			for(int j=0; j<this.size; j++){
				deepCopy1[i][j] = this.squarelotron[i][j];
				deepCopy2[i][j] = this.squarelotron[i][j];
			}
		}
		// manipulating on deepCopy2, mainDiagonalFlip on deepCopy2
		int[][] temp = new int[this.size][this.size];
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				temp[i][j] = deepCopy2[j][i];
			}
		}
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				deepCopy2[i][j] = temp[i][j];
			}
		}
		// get the wanted ring on deepCopy2, and copy those numbers onto deepCopy1
		// upper bound
		for(int j=ring-1; j<this.size-ring; j++){
			deepCopy1[ring-1][j] = deepCopy2[ring-1][j];
		}
		// right bound
		for(int i=ring-1; i<this.size-ring; i++){
			deepCopy1[i][this.size-ring] = deepCopy2[i][this.size-ring];
		}
		// lower bound
		for(int j=this.size-ring; j>ring-1; j--){
			deepCopy1[this.size-ring][j] = deepCopy2[this.size-ring][j];
		}
		// left bound
		for(int i=this.size-ring; i>ring-1; i--){
			deepCopy1[i][ring-1] = deepCopy2[i][ring-1];
		}
		// use deepCopy1 to get a new squarelotron
		Squarelotron s= new Squarelotron(this.size);
		for(int i=0; i<s.size; i++){
			for(int j=0; j<s.size; j++){
				s.squarelotron[i][j] = deepCopy1[i][j];
			}
		}
		return s;
	}

	/**
	 * side flip the squarelotron with the indicated side
	 * flip direction - the out-most 2 rows in suggested direction
	 * @param ring - ring number
	 */
	@Override
	public Squarelotron sideFlip(String side) {
		// deep copy the board out
		int[][] deepCopy1 = new int[this.size][this.size];
		for(int i=0; i<this.size; i++){
			for(int j=0; j<this.size; j++){
				deepCopy1[i][j] = this.squarelotron[i][j];
			}
		}
		// flip the side as indicated
		if(side == "top"){
			// flip top 2 rows
			int[] temp = deepCopy1[0];
			deepCopy1[0] = deepCopy1[1];
			deepCopy1[1] = temp;
		}else if(side == "bottom"){
			// flip bottom 2 rows
			int[] temp = deepCopy1[this.size-2];
			deepCopy1[this.size-2] = deepCopy1[this.size-1];
			deepCopy1[this.size-1] = temp;
		}else if(side == "left"){
			// flip left 2 columns
			for(int i=0; i<this.size; i++){
				int temp = deepCopy1[i][0];
				deepCopy1[i][0] = deepCopy1[i][1];
				deepCopy1[i][1] = temp;
			}
		}else if(side == "right"){
			// flip right 2 columns
			for(int i=0; i<this.size; i++){
				int temp = deepCopy1[i][this.size-2];
				deepCopy1[i][this.size-2] = deepCopy1[i][this.size-1];
				deepCopy1[i][this.size-1] = temp;
			}
		}else{
			throw new IllegalArgumentException("bad side choice !!!");
		}
		// use deepCopy1 to get a new squarelotron
		Squarelotron s= new Squarelotron(this.size);
		for(int i=0; i<s.size; i++){
			for(int j=0; j<s.size; j++){
				s.squarelotron[i][j] = deepCopy1[i][j];
			}
		}
		return s;
	}

	/**
	 *  rotate the squarelotron
	 *  @param numberOfTurns - indicating how it will turn
	 */
	@Override
	public void rotateRight(int numberOfTurns) {
		// three different situation
		// -3 or 1
		// 1. diagonal flip (transpose) 2.upside down flip
		// -2 or 2
		// 1. left to right flip 2.upside down flip
		// -1 or 3
		// 1. main inverse diagonal flip 2.left to right flip
		// 0 is no change at all
		int ind = numberOfTurns%4;
		if(ind == -1 || ind == 3){
			// transpose
			int[][] temp = new int[this.size][this.size];
			for (int i = 0; i < this.size; i++) {
				for (int j = 0; j < this.size; j++) {
					temp[i][j] = this.squarelotron[j][i];
				}
			}
			for (int i = 0; i < this.size; i++) {
				for (int j = 0; j < this.size; j++) {
					this.squarelotron[i][j] = temp[i][j];
				}
			}
			// flip upside down
			for(int i=0; i<this.size/2; i++){
				int[] temp_1 = this.squarelotron[i];
				this.squarelotron[i] = this.squarelotron[this.size - i - 1];
				this.squarelotron[this.size - i - 1] = temp_1;
			}
		}else if(ind == 2 || ind == -2){
			// flip upside down
			for(int i=0; i<this.size/2; i++){
				int[] temp_2 = this.squarelotron[i];
				this.squarelotron[i] = this.squarelotron[this.size - i - 1];
				this.squarelotron[this.size - i - 1] = temp_2;
			}
			// flip right to left
			for (int i = 0; i < this.size; i++) {
				for (int j = 0; j < (this.size + 1) / 2; j++) {
					int temp_3 = this.squarelotron[i][j];
					this.squarelotron[i][j] = this.squarelotron[i][this.size - 1 - j];
					this.squarelotron[i][this.size - 1 - j] = temp_3;
				}
			}
		}else if(ind == 1 || ind == -3){
			// inverse transpose
			int[][] temp_4 = new int[this.size][this.size];
			for (int i = 0; i < this.size; i++) {
				for (int j = 0; j < this.size; j++) {
					temp_4[i][j] = this.squarelotron[this.size-j-1][this.size-i-1];
				}
			}
			for (int i = 0; i < this.size; i++) {
				for (int j = 0; j < this.size; j++) {
					this.squarelotron[i][j] = temp_4[i][j];
				}
			}
			// flip upside down
			for(int i=0; i<this.size/2; i++){
				int[] temp_1 = this.squarelotron[i];
				this.squarelotron[i] = this.squarelotron[this.size - i - 1];
				this.squarelotron[this.size - i - 1] = temp_1;
			}
		}else{
			System.out.println("Nothing Changed !!!");
		}
	}

	/**
	 *  determine if two squarelotron is equal to each other
	 *  @param object - a passed in squarelotron object
	 */
	@Override 
	public boolean equals(Object object){
		// determine if it is a squarelotron
		if(object.getClass() == this.getClass()){
			Squarelotron newS = (Squarelotron) object;
			if(newS.size == this.size){
				// create two deep copy of each squarelotron for comparing
				Squarelotron s_1 = new Squarelotron(this.size);
				Squarelotron s_2 = new Squarelotron(this.size);
				for (int i = 0; i < this.size; i++) {
					for (int j = 0; j < this.size; j++) {
						s_1.squarelotron[i][j] = this.squarelotron[i][j];
					}
				}
				for (int i = 0; i < this.size; i++) {
					for (int j = 0; j < this.size; j++) {
						s_2.squarelotron[i][j] = newS.squarelotron[i][j];
					}
				}
				// determine whether it is possible to rotate the current board
				// to get the pass-in board
				for(int turns = 0; turns < 4; turns++){
					int flag = 1;
					s_1.rotateRight(turns);
					// check if it is equal
					for (int i = 0; i < this.size; i++) {
						for (int j = 0; j < this.size; j++) {
							if(s_2.squarelotron[i][j] != s_1.squarelotron[i][j]){
								flag = 0;
							}
						}
					}
					// if flag is still 1, then it is equal, we turn true
					if(flag == 1){
						return true;
					}
				}

			}else{
				return false;
			}
		}else{
			return false;
		}
		return false;
	}

	/**
	 *  convert the board to a printable string version
	 */
	@Override 
	public String toString(){
		String s = "";
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				s += Integer.toString(this.squarelotron[i][j]) + "\t";
			}
			s += "\n";
		}
		return s;
	}

	/**
	 * testing main loop
	 * @param args
	 */
	public static void main(String[] args) {
		Squarelotron printTest = new Squarelotron(4);
		System.out.println("Starting Point");
		printTest.rotateRight(0);
		System.out.print(printTest);
		System.out.println("To The Right 90 Degrees");
		printTest.rotateRight(1);
		System.out.print(printTest);
		System.out.println("To The Right 180 Degrees");
		printTest.rotateRight(1);
		System.out.print(printTest);
		System.out.println("To The Right 270 Degrees");
		printTest.rotateRight(1);
		System.out.print(printTest);
		System.out.println("To The Right 360 Degrees");
		printTest.rotateRight(1);
		System.out.print(printTest);
	}
}
