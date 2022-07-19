import java.util.Scanner;

public class MainFile {
    public static void main(String[] args)
    {
        //new Connect5GameFrame();
        Board b = new Board();
        Scanner k = new Scanner(System.in);
        String p1_name,p2_name;
        Player p1,p2;
        /* get players */

        do {
            System.out.println("Enter the Player 1 (RED) name (Press C for Computer):");

            p1_name = k.nextLine();
            if (!p1_name.equals("C")) p1 = new HumanPlayer(p1_name, 'R');
            else p1 = new RandomComputer("Computer", 'R');
            System.out.println("Player1 is  "+p1.getName()+" ("+p1.getLetter()+") ");
        }while (p1_name.equals(""));

        do {
            System.out.println("Enter the Player 2 (BLUE) name (Press C for Computer):");
            p2_name = k.nextLine();
            if(!p2_name.equals("C")) p2 = new HumanPlayer(p2_name,'B');
            else p2= new RandomComputer(p2_name,'B');
        }while (p2_name.equals(""));



        do{
            if(b.isFull() && b.getWinner() == b.PLAYING )
                System.out.println("Its a tie Game!!!");

            System.out.println("Enter the Player "+p1.getName()+" (RED) turn :");
            Move m1 = p1.getMove(b);
            while(!b.makeMove(m1, p1.getLetter())){
                m1 = p1.getMove(b);
            }

            if (b.getWinner() == p1.getLetter())
            {
                b.repaint();
                System.out.println("Player "+p1.getName()+" (RED) Won the Game");
                break;
            }

            b.repaint();

            if(b.isFull() && b.getWinner() == b.PLAYING )
                System.out.println("Its a tie Game!!!");


            System.out.println("Enter the Player "+p2.getName()+" (BLUE) turn :");
            m1 = p2.getMove(b);
            while(!b.makeMove(m1, p2.getLetter())){
                m1 = p2.getMove(b);
            }

            if (b.getWinner() == p2.getLetter())
            {
                b.repaint();
                System.out.println("Player "+p2.getName()+" (BLUE) Won the Game");
                break;
            }


            b.repaint();
        }while(b.getWinner() == b.PLAYING && !b.isFull());
    }


}
// is full method needs to be changed
