package squarelotron;

public interface SquarelotronMethods {
	public int[] numbers();
	public Squarelotron upsideDownFlip(int ring);
	public Squarelotron leftRightFlip(int ring);
	public Squarelotron inverseDiagonalFlip(int ring);
	public Squarelotron mainDiagonalFlip(int ring);
	public Squarelotron sideFlip(String side);
	public void rotateRight(int numberOfTurns);
	@Override public boolean equals(Object object);
	@Override public String toString();
}
