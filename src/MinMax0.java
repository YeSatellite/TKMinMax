import java.util.ArrayList;
import java.util.List;

public class MinMax0 {
    private TKCore core;
    private boolean myPlayer;
    private int depth;

    public MinMax0(TKCore core, boolean myPlayer, int depth) {
        this.core = core;
        this.myPlayer = myPlayer;
        this.depth = depth;
    }

    public int find(){
        Result result = miniMax(depth, core.copy(),Integer.MIN_VALUE, Integer.MAX_VALUE);
        return result.movedPit;
    }

    private Result miniMax(int depth, TKCore game, int alpha, int beta){
        List<Integer> nextMoves = generateMoves(game);
        int score;
        int movement = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            alpha = evaluate(game);
            beta = evaluate(game);
        } else {
            for (int move : nextMoves) {
                TKCore newGame = game.copy();
                newGame.move(move);
                game.finish();
                if (isMyGame(game)) {
                    score = miniMax(depth - 1, newGame, alpha, beta).score;
                    if (score > alpha) {
                        alpha = score;
                        movement = move;
                    }
                } else {
                    score = miniMax(depth - 1, newGame, alpha, beta).score;
                    if (score < beta) {
                        beta = score;
                        movement = move;
                    }
                }
                if (alpha >= beta) break;
            }
        }
        return new Result(isMyGame(game)?alpha:beta,movement);


    }
    private boolean isMyGame(TKCore game){
        return game.getCurrentPlayer() == myPlayer;
    }

    private int evaluate(TKCore game) {
        int score = 0;
        score += game.getPlayer2().getBowl();
        score -= game.getPlayer1().getBowl();
        int scr = 182 - game.getPlayer2().getBowl() - game.getPlayer1().getBowl();
        int t = scr/10;
        score += game.getPlayer2().HaveTuz()?t:0;
        score -= game.getPlayer1().HaveTuz()?t:0;
        if (myPlayer == TKCore.PLAYER1) score = -score;
        return score;
    }

    private List<Integer> generateMoves(TKCore game) {
        List<Integer> nextMoves = new ArrayList<>();
        int[] pits = game.getPlayer().getPits();
        for (int i = 0; i < pits.length; i++) {
            if (pits[i]>0) nextMoves.add(i);
        }
        return nextMoves;
    }
    class Result{
        int score;
        int movedPit;

        public Result(int score, int movedPit) {
            this.score = score;
            this.movedPit = movedPit;
        }
    }
}
