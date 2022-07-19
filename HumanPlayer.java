import java.util.Scanner;
public class HumanPlayer extends Player{
    Scanner k = new Scanner(System.in);
    public HumanPlayer(String name, char letter)
    {
        super(name, letter);
    }

    public Move getMove(Board board)
    {
        int x, y;

        do
        {
            System.out.println("Enter the row(0-7):");
            x = k.nextInt();

        }while(x > 7 || x < 0   );
        do
        {
            System.out.println("Enter the column(0-7):");
            y = k.nextInt();
        }while(x > 7 || x < 0 || y > 7 || y < 0  );
        return new Move(x,y);

    }
    public Player freshCopy()
    {
        return new HumanPlayer(getName(), getLetter());
    }




}
