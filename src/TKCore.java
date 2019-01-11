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
        Player other = oppPlayer(self);
        Player tmp = self;
        int balls = tmp.pits[pit];
        if (balls < 1) return false;

        //Move
        tmp.pits[pit] = 0;
        if(balls==1) pit++;
        while (balls > 0) {
            if (pit == NINE) {
                tmp = oppPlayer(tmp);
                pit = 0;
            }
            if (tmp.pits[pit] == -1){
                Player nTmp = oppPlayer(tmp);
                nTmp.bowl++;
                continue;
            }
            tmp.pits[pit++]++;
            balls--;
        }
        pit--;

        // Test
        if (tmp == other) {
            if (other.pits[pit] % 2 == 0) {
                self.bowl += other.pits[pit];
                other.pits[pit] = 0;
            }
            // Tuz
            if (other.pits[pit] == 3 && !self.haveTuz) {
                self.haveTuz = true;
                self.bowl += other.pits[pit];
                other.pits[pit] = -1;
            }
        }
        currentPlayer = !currentPlayer;
        return true;
    }
    
    public boolean finish(){
        boolean ans = false;
        for (int p = 0; p < 2; p++) {
            Player p1 = p == 0 ? player1 : player2;
            Player p2 = oppPlayer(p1);
            boolean have = false;
            for (int i = NINE - 1; i >= 0; i--) {
                if (p1.pits[i] > 0) {
                    have = true;
                    break;
                }
            }
            if (!have) {
                for (int i = 0; i < NINE; i++) {
                    if (p2.pits[i] > 0) {
                        p1.bowl += p2.pits[i];
                        p2.pits[i] = 0;
                    }
                }
                ans = true;
            }
        }
        return ans;
    }

    private Player oppPlayer(Player player){
        return player == player1 ? player2 : player1;
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
        private boolean haveTuz;

        Player() {
            this.bowl = 0;
            this.pits = new int[NINE];
            for (int i = 0; i < NINE; i++) pits[i] = NINE;
            this.haveTuz = false;
        }

        private Player(int bowl, int[] pits, boolean haveTuz) {
            this.bowl = bowl;
            this.pits = pits;
            this.haveTuz = haveTuz;
        }

        public int getBowl() {
            return bowl;
        }

        public int[] getPits() {
            return pits;
        }

        public boolean HaveTuz() {
            return haveTuz;
        }

        Player copy(){
            return new Player(bowl,pits.clone(), haveTuz);
        }
    }
}