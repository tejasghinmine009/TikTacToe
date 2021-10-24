import java.util.*;

public class tictactoe {

    static ArrayList<Integer>  playerPos= new ArrayList<>();
    static ArrayList<Integer>  cpuPos= new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard =  {
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}
        };
        printGameBoard(gameBoard);

        while(true){
            System.out.println("Enter your placement from 1 to 9 : ");
            Scanner sc = new Scanner(System.in);
            int playerpos = sc.nextInt();

            while(playerPos.contains(playerpos)||cpuPos.contains(playerpos)){
                System.out.println("position taken!! Enter a correct position");
                playerpos = sc.nextInt();
            }

            placePiece(gameBoard,playerpos,"player");
            String res = checkWinner();
            if(res.length()>0){
                System.out.println(res);
                break;
            }

            Random rand = new Random();
            int cpupos = rand.nextInt(9)+1;
            while(playerPos.contains(cpupos)||cpuPos.contains(cpupos)){
                System.out.println("position taken!! Cpu taking a correct position");
                cpupos = rand.nextInt(9)+1;
            }

            placePiece(gameBoard,cpupos,"cpu");
            printGameBoard(gameBoard);

            res = checkWinner();
            if(res.length()>0){
                System.out.println(res);
                break;
            }
        }


    }

    public static void printGameBoard(char[][] gameBoard){
        for(char[] row:gameBoard){
            for(char c:row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard,int pos,String user){
        char symbol = ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPos.add(pos);
        }else if(user.equals("cpu")){
            symbol = 'O';
            cpuPos.add(pos);
        }
        switch(pos){
            case 1:
                gameBoard[0][0] =symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List letfcol = Arrays.asList(1,4,7);
        List midcol = Arrays.asList(2,5,8);
        List rightcol = Arrays.asList(3,6,9);
        List diaI = Arrays.asList(1,5,9);
        List diaII = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(letfcol);
        winning.add(midcol);
        winning.add(rightcol);
        winning.add(diaI);
        winning.add(diaII);
        for(List l:winning){
            if(playerPos.containsAll(l)){
                return "Congratulation you won";
            }else if(cpuPos.containsAll(l)){
                return "Cpu wins!! Better  luck next time";
            }else if(playerPos.size()+cpuPos.size() == 9){
                return "Tie";
            }
        }
        return "";
    }
}
