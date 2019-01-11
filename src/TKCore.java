public class TKCore {
    public static boolean PLAYER1 = false;
    public static boolean PLAYER2 = true;

    private static int NINE = 9;

    private boolean currentPlayer;
    private Player player1;
    private Player player2;

    public TKCore() {
        player1 = new Player();
        player2 = new Player();
        currentPlayer = PLAYER1;
    }

    private TKCore(boolean currentPlayer, Player player1, Player player2) {
        this.currentPlayer = currentPlayer;
        this.player1 = player1;
        this.player2 = player2;
    }

    public boolean move(int pit) {
        Player self = currentPlayer == PLAYER1 ? player1 : player2;
        Player other = currentPlayer == PLAYER1 ? player2 : player1;
        Player tmp = self;
        int balls = tmp.pits[pit];
        if (balls == 0) return false;

        //Move
        tmp.pits[pit] = 0;
        if(balls==1){
            if (++pit == NINE) {
                tmp = other;
                pit = 0;
            }
            tmp.pits[pit++]++;
            balls--;
        }
        while (balls > 0) {
            if (pit == NINE) {
                tmp = tmp == self ? other : self;
                pit = 0;
            }
            tmp.pits[pit++]++;
            balls--;
        }
        pit--;

        // Test
        if (tmp == other) {
            if (tmp.pits[pit] % 2 == 0) {
                self.bowl += tmp.pits[pit];
                tmp.pits[pit] = 0;
            }
            // Tuz

        }
        currentPlayer = !currentPlayer;
        return true;
    }

    public boolean getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public TKCore copy(){
        return new TKCore(currentPlayer,player1.copy(),player2.copy());
    }

    public class Player{
        private int bowl;
        private int[] pits;

        Player() {
            this.bowl = 0;
            this.pits = new int[NINE];
            for (int i = 0; i < NINE; i++) pits[i] = NINE;
        }

        private Player(int bowl, int[] pits) {
            this.bowl = bowl;
            this.pits = pits;
        }

        public int getBowl() {
            return bowl;
        }

        public int[] getPits() {
            return pits;
        }

        Player copy(){
            return new Player(bowl,pits.clone());
        }
    }
}