import java.util.Random;
import java.util.Scanner;


public class TicTacToe{

    //parameters :
    private int tictac[][]; // create a grid to keep the gird informations
                            // 0 is an empty coefficient ; 1 is the 'X' ; 2 is the 'O'
    private int turn;   //1 means it's X's turn ;  2 means it's O's turn
    //builder : 

    TicTacToe(){
       tictac = new int[3][3];
       for(int  i = 0 ; i < tictac.length ; i++){
            for(int j = 0 ; j < tictac[i].length ; j++){
                tictac[i][j] = 0; //initialization of each coefficient of the array
            }
       }
    }

    //access : 

    public void setCoef(int line, int col, int val){
        if(val > 2 || val < 0){
            throw new IllegalArgumentException("wrong value of the coefficient" + val);
        }
        if(line > 2 || line < 0){
            throw new IllegalArgumentException("wrong index of line : "+ line);
        }
        if(col > 2 || col < 0){
            throw new IllegalArgumentException("wrong value of column : " + col);
        }
        tictac[line][col] = val;
    }

    public int getCoef(int line, int col){
        if(line > 2 || line < 0){
            throw new IllegalArgumentException("wrong index of line : "+ line);
        }
        if(col > 2 || col < 0){
            throw new IllegalArgumentException("wrong value of column : " + col);
        }
        return tictac[line][col];
    }

    public String toString(){
        String res = "\n+---+---+---+\n";//top line;
        for(int i = 0 ; i < tictac.length ; i++){
            res +="|";
            for(int j = 0 ; j < tictac[i].length ; j++){
                switch(tictac[i][j]){
                    case 0:
                        res += "   |";
                        break;
                    case 1:
                        res += " X |";
                        break;
                    case 2:
                        res += " O |";
                        break;
                    default:
                    throw new IllegalArgumentException(res + "is the coefficient in the array of tictactoe at line "+ i+" and column " + j);
                }
            }
            res += "\n+---+---+---+\n";
        }
        return res;
    }

    //functions :

    public void nextTurn(){
        if(turn == 1){
            turn = 2;
        }else if(turn == 2){
            turn =1;
        }
    }

    /**
     * @return returns if the grid is completely empty 
     */
    public boolean empty(){
        for(int  i = 0 ; i < tictac.length ; i++){
            for(int j = 0 ; j < tictac[i].length ; j++){
                if(tictac[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return returns if the grid is completely full 
     */
    public boolean full(){
        for(int  i = 0 ; i < tictac.length ; i++){
            for(int j = 0 ; j < tictac[i].length ; j++){
                if(tictac[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public int click(Scanner sc){
        int clicked = 0;
        do{
            clicked = sc.nextInt();
        }while(clicked > 9 || clicked < 1);
        clicked--;//to match with the 0 - 8 cases (the user sees 1-9)
        int line = clicked/3;
        int col = clicked % 3;
        if(tictac[2 -line][col] == 0){//assert the selected position isn't already occuped
            tictac[2 -line][col] = turn;
            return 1;
        }
        return 0; //0 if nothing had been clicked
    }

    public void printTurn(){
        if(turn == 1){
            System.out.println("It's X's turn !\n");
        }else if(turn == 2){
            System.out.println("It's O's turn !\n");
        }
    }

    public void printWinner(int e){
        if(e == 1){
            System.out.println("X is winner !\n");
        }else if(e == 2){
            System.out.println("O is winner !\n");
        }else if(e == 0){
            System.out.println("equality -_-\n");
        }
    }

    public void party(){
        Random r = new Random(System.currentTimeMillis());
        turn = 1 + r.nextInt() % 2;
        Scanner scan = new Scanner(System.in);
        int i;
        int winner;
        do{
           //printRaw();
            printTurn();
            System.out.println(this);
            i = click(scan);//in order to stay in the current turn if the posistion isn't good
            //System.out.println(this);
            if(i == 1){
                nextTurn();
            }
            winner = winDetection();
        }while(!full() && (winner == 0));
        scan.close();
        System.out.println(this);
        printWinner(winner);
    }

    public void printRaw(){
        for(int  i = 0 ; i < tictac.length ; i++){
            for(int j = 0 ; j < tictac[i].length ; j++){
                System.out.printf("%d ", tictac[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.printf("%d\n", turn);
    }

    /**
     * @return 0 if no win ; 1 if X is winner ; 2 if O is winner
     */
    public int winDetection(){
        //detection of the middle box :
        int m = tictac[1][1];//set in memory the player in the middle box
        if(m == 1 || m == 2){
            if(tictac[0][1] == m && tictac[0][2] == m){
                return m;   //the middle column is full of the same player
            }
            if(tictac[1][0] == m && tictac[1][2] == m){
                return m;   //the middle line is full of the same player
            }
            if(tictac[0][0] == m && tictac[2][2] == m){
                return m;   //the principal diagonal is full of the same player
            }
            if(tictac[2][2] == m && tictac[0][2] == m){
                return m;   //the secondary diagonal is full of the same player
            }
        }

        m = tictac[0][0];//set in memory the player in the left upper corner
        if(m == 1 || m == 2){
            if(tictac[0][1] == m && tictac[0][2] == m){
                return m;   //the upper line is full of the same player
            }
            if(tictac[1][0] == m && tictac[2][0] == m){
                return m;   //the left column is full of the same player
            }
        }

        m = tictac[2][2];//set in memory the player in the right down corner
        if(m == 1 || m == 2){
            if(tictac[2][0] == m && tictac[2][1] == m){
                return m;   //the downer line is full of the same player
            }
                if(tictac[0][2] == m && tictac[1][2] == m){
                    return m;   //the right column is full of the same player
            }
        }
        //if no win detected, return 0 :
        return 0;
    }
}






