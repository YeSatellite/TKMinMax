import java.util.ArrayList;
import java.util.List;

public class MinMax {
    private TKCore core;
    private boolean myPlayer;
    private int depth;

    public MinMax(TKCore core,boolean myPlayer, int depth) {
        this.core = core;
        this.myPlayer = myPlayer;
        this.depth = depth;
    }

    public int find(){
        Result result = miniMax(depth, core.copy());
        return result.movedPit;
    }

    private Result miniMax(int depth, TKCore game){
        List<Integer> nextMoves = generateMoves(game);
        int currentScore;
        int bestScore = isMyGame(game) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestMovement = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            bestScore = evaluate(game);
        } else {
            for (int move : nextMoves) {
                TKCore newGame = game.copy();
                newGame.move(move);
                game.finish();
                if (isMyGame(game)) {
                    currentScore = miniMax(depth - 1, newGame).score;
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestMovement = move;
                    }
                } else {
                    currentScore = miniMax(depth - 1, newGame).score;
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestMovement = move;
                    }
                }
            }
        }
        return new Result(bestScore,bestMovement);


    }
    private boolean isMyGame(TKCore game){
        return game.getCurrentPlayer() == myPlayer;
    }

    private int evaluate(TKCore game) {
        int score = 0;
        score += game.getPlayer2().getBowl();
        score -= game.getPlayer1().getBowl();
        score += game.getPlayer2().HaveTuz()?10:0;
        score -= game.getPlayer1().HaveTuz()?10:0;
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
