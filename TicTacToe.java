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

    public void click(Scanner sc){
        int clicked = 0;
        do{
            clicked = sc.nextInt();
        }while(clicked > 9 || clicked < 1);
        clicked--;//to match with the 0 - 8 cases (the user sees 1-9)
        int line = clicked/3;
        int col = clicked % 3;
        tictac[2 -line][col] = turn;
    }

    public void printTurn(){
        if(turn == 1){
            System.out.println("It's X's turn !\n");
        }else if(turn == 2){
            System.out.println("It's O's turn !\n");
        }
    }

    public void party(){
        Random r = new Random(System.currentTimeMillis());
        turn = 1 + r.nextInt() % 2;
        Scanner scan = new Scanner(System.in);
        while(!full()){
            printRaw();
            printTurn();
            System.out.println(this);
            click(scan);
            //System.out.println(this);
            nextTurn();
        }
        scan.close();
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






}






