package squarelotron;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SquarelotronTest {

	private Squarelotron s;
	private Squarelotron s_4;
	private Squarelotron s_5;
	private Squarelotron s_array;
	private Squarelotron s_array_4;
	private Squarelotron s_array_5;

	@Before
	public void setUp() throws Exception {
		s = new Squarelotron(3);
		s_4 = new Squarelotron(4);
		s_5 = new Squarelotron(5);
		int[] temp = {1,2,3,4,5,6,7,8,9};
		int[] temp_4 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		int[] temp_5 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		s_array = new Squarelotron(temp);
		s_array_4 = new Squarelotron(temp_4);
		s_array_5 = new Squarelotron(temp_5);
	}

	@Test
	public void testSquarelotron() {
		assertEquals(3, s.size);
		int[][] test = {{1,2,3},{4,5,6},{7,8,9}};
		assertArrayEquals(test,s.squarelotron);
		assertArrayEquals(test,s_array.squarelotron);
		int[][] test_4 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		assertArrayEquals(test_4,s_4.squarelotron);
		assertArrayEquals(test_4,s_array_4.squarelotron);
		int[][] test_5 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		assertArrayEquals(test_5,s_5.squarelotron);
		assertArrayEquals(test_5,s_array_5.squarelotron);
	}

	@Test
	public void testNumbers() {
		int[] temp = {1,2,3,4,5,6,7,8,9};
		int[] temp_4 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		int[] temp_5 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		assertArrayEquals(s.numbers(),temp);
		assertArrayEquals(s_4.numbers(),temp_4);
		assertArrayEquals(s_5.numbers(),temp_5);
	}

	@Test
	public void testUpsideDownFlip() {
		// simple 3 by 3 case
		Squarelotron temp = s.upsideDownFlip(1);
		int[][] test = {{7,8,9},{4,5,6},{1,2,3}};
		assertArrayEquals(test,temp.squarelotron);
		// 4 by 4, but flip inner ring
		int[][] test_4 = {{1,2,3,4},{5,10,11,8},{9,6,7,12},{13,14,15,16}};
		Squarelotron temp_4 = s_4.upsideDownFlip(2);
		assertArrayEquals(test_4,temp_4.squarelotron);
		// 4 by 4, but flip outer ring
		int[][] test_4_1 = {{13,14,15,16},{9,6,7,12},{5,10,11,8},{1,2,3,4}};
		Squarelotron temp_4_1 = s_4.upsideDownFlip(1);
		assertArrayEquals(test_4_1,temp_4_1.squarelotron);
		// 5 by 5, flip the center, nothing will change
		int[][] test_5 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		Squarelotron temp_5 = s_5.upsideDownFlip(3);
		assertArrayEquals(test_5,temp_5.squarelotron);
	}

	@Test
	public void testLeftRightFlip() {
		// simple 3 by 3 case
		Squarelotron temp = s.leftRightFlip(1);
		int[][] test = {{3,2,1},{6,5,4},{9,8,7}};
		assertArrayEquals(test,temp.squarelotron);
		// 4 by 4, but flip inner ring
		int[][] test_4_1 = {{1,2,3,4},{5,7,6,8},{9,11,10,12},{13,14,15,16}};
		Squarelotron temp_4_1 = s_4.leftRightFlip(2);
		assertArrayEquals(test_4_1,temp_4_1.squarelotron);
		// 4 by 4, but flip outer ring
		int[][] test_4 = {{4,3,2,1},{8,6,7,5},{12,10,11,9},{16,15,14,13}};
		Squarelotron temp_4 = s_4.leftRightFlip(1);
		assertArrayEquals(test_4,temp_4.squarelotron);
		// 5 by 5, flip the center, nothing will change
		int[][] test_5 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		Squarelotron temp_5 = s_5.leftRightFlip(3);
		assertArrayEquals(test_5,temp_5.squarelotron);
	}

	@Test
	public void testInverseDiagonalFlip() {
		// simple 3 by 3 case
		Squarelotron temp = s.inverseDiagonalFlip(1);
		int[][] test = {{9,6,3},{8,5,2},{7,4,1}};
		assertArrayEquals(test,temp.squarelotron);
		// 4 by 4, but inverse inner ring
		int[][] test_4_1 = {{1,2,3,4},{5,11,7,8},{9,10,6,12},{13,14,15,16}};
		Squarelotron temp_4_1 = s_4.inverseDiagonalFlip(2);
		assertArrayEquals(test_4_1,temp_4_1.squarelotron);
		// 4 by 4, but inverse outer ring
		int[][] test_4 = {{16,12,8,4},{15,6,7,3},{14,10,11,2},{13,9,5,1}};
		Squarelotron temp_4 = s_4.inverseDiagonalFlip(1);
		assertArrayEquals(test_4,temp_4.squarelotron);
		// 5 by 5, flip the center, nothing will change
		int[][] test_5 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		Squarelotron temp_5 = s_5.inverseDiagonalFlip(3);
		assertArrayEquals(test_5,temp_5.squarelotron);
	}

	@Test
	public void testMainDiagonalFlip() {
		// simple 3 by 3 case
		Squarelotron temp = s.mainDiagonalFlip(1);
		int[][] test = {{1,4,7},{2,5,8},{3,6,9}};
		assertArrayEquals(test,temp.squarelotron);
		// 4 by 4, but inverse inner ring
		int[][] test_4_1 = {{1,2,3,4},{5,6,10,8},{9,7,11,12},{13,14,15,16}};
		Squarelotron temp_4_1 = s_4.mainDiagonalFlip(2);
		assertArrayEquals(test_4_1,temp_4_1.squarelotron);
		// 4 by 4, but inverse outer ring
		int[][] test_4 = {{1,5,9,13},{2,6,7,14},{3,10,11,15},{4,8,12,16}};
		Squarelotron temp_4 = s_4.mainDiagonalFlip(1);
		assertArrayEquals(test_4,temp_4.squarelotron);
		// 5 by 5, flip the center, nothing will change
		int[][] test_5 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		Squarelotron temp_5 = s_5.mainDiagonalFlip(3);
		assertArrayEquals(test_5,temp_5.squarelotron);
	}

	@Test
	public void testSideFlip() {
		// test on 4 by 4 board
		// flip top
		int[][] test_4_1 = {{5,6,7,8},{1,2,3,4},{9,10,11,12},{13,14,15,16}};
		Squarelotron temp_6 = s_4.sideFlip("top");
		assertArrayEquals(test_4_1,temp_6.squarelotron);
		// flip bottom
		int[][] test_4_2 = {{1,2,3,4},{5,6,7,8},{13,14,15,16},{9,10,11,12}};
		Squarelotron temp_7 = s_4.sideFlip("bottom");
		assertArrayEquals(test_4_2,temp_7.squarelotron);
		// flip left
		int[][] test_4_3 = {{2,1,3,4},{6,5,7,8},{10,9,11,12},{14,13,15,16}};
		Squarelotron temp_8 = s_4.sideFlip("left");
		assertArrayEquals(test_4_3,temp_8.squarelotron);
		// flip right
		int[][] test_4_4 = {{1,2,4,3},{5,6,8,7},{9,10,12,11},{13,14,16,15}};
		Squarelotron temp_9 = s_4.sideFlip("right");
		assertArrayEquals(test_4_4,temp_9.squarelotron);
	}

	@Test
	public void testRotateRight() {
		// test 4 by 4 case
		int[][] test_4 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int[][] test_4_1 = {{13,9,5,1},{14,10,6,2},{15,11,7,3},{16,12,8,4}};
		int[][] test_4_2 = {{16,15,14,13},{12,11,10,9},{8,7,6,5},{4,3,2,1}};
		int[][] test_4_3 = {{4,8,12,16},{3,7,11,15},{2,6,10,14},{1,5,9,13}};
		// rotate 0 degrees and 720 degrees
		// should stay the same
		s_4.rotateRight(0);
		assertArrayEquals(test_4,s_4.squarelotron);
		s_4.rotateRight(8);
		assertArrayEquals(test_4,s_4.squarelotron);
		// rotate to the right 90 degrees
		s_4.rotateRight(1);
		assertArrayEquals(test_4_1,s_4.squarelotron);
		// rotate to the left 90 degrees
		// back to original
		s_4.rotateRight(-1);
		assertArrayEquals(test_4,s_4.squarelotron);
		// rotate to the right 180 degrees
		s_4.rotateRight(2);
		assertArrayEquals(test_4_2,s_4.squarelotron);
		// rotate to the left 180 degrees
		// back to original
		s_4.rotateRight(-2);
		assertArrayEquals(test_4,s_4.squarelotron);
		// rotate to the right 270 degrees
		s_4.rotateRight(3);
		assertArrayEquals(test_4_3,s_4.squarelotron);
		// rotate to the left 270 degrees
		// back to original
		s_4.rotateRight(-3);
		assertArrayEquals(test_4,s_4.squarelotron);
	}

}
