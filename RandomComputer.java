public class RandomComputer extends Player {
    public RandomComputer(String name, char letter)
    {
        super(name, letter);
    }
    public Move getMove(Board board)
    {

        int randomCol = (int)(Math.random()*7);
        int randomRow = (int)(Math.random()*7);
        System.out.println("Coumputer generated row "+randomRow+"  column "+randomCol);
        return new Move(randomCol, randomRow);
    }
    public Player freshCopy()
    {
        return  new RandomComputer(getName(), getLetter());
    }


}
