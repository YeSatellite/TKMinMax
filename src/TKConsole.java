public class TKConsole {
    private TKCore core;
    private MinMax0 test1;
    private MinMax0 test2;

    public void start() {
        int s1 = 0;
        int s2 = 0;
        for (int depth = 7; depth < 10; depth++) {
            this.core = new TKCore();
            this.test1 = new MinMax0(core, TKCore.PLAYER1, depth - 2);
            this.test2 = new MinMax0(core, TKCore.PLAYER2, depth);
            while (true) {
                if (core.getCurrentPlayer() == TKCore.PLAYER1) {
                    int pit = test1.find();
                    core.move(pit);
                } else {
                    int pit = test2.find();
                    core.move(pit);
                }
                if (core.finish()) {
                    System.out.println(core.getPlayer1().getBowl() + ":" + core.getPlayer2().getBowl());
                    s1 += core.getPlayer1().getBowl() > core.getPlayer2().getBowl() ? 1 : 0;
                    s2 += core.getPlayer1().getBowl() < core.getPlayer2().getBowl() ? 1 : 0;
                    break;
                }
            }
        }
    }

    public void print() {
        StringBuilder out = new StringBuilder();
        TKCore.Player player1 = core.getPlayer1();
        TKCore.Player player2 = core.getPlayer2();
        boolean player = core.getCurrentPlayer();
        for (int i = 8; i >= 0; i--) {
            out.append(' ');
            out.append(i + 1);
            out.append('.');
        }
        out.append('\n');

        for (int i = 8; i >= 0; i--) {
            if (0 <= player2.getPits()[i] && player2.getPits()[i] < 10) out.append(' ');
            out.append(player2.getPits()[i]);
            out.append('|');
        }
        out.append("(");
        if (player2.getBowl() < 10) out.append(' ');
        out.append(player2.getBowl()).append(")");
        if (player == TKCore.PLAYER2) out.append('<');
        out.append('\n');

        out.append("------------------\n");

        for (int i = 0; i < 9; i++) {
            if (0 <= player1.getPits()[i] && player1.getPits()[i] < 10) out.append(' ');
            out.append(player1.getPits()[i]);
            out.append('|');
        }
        out.append("(");
        if (player1.getBowl() < 10) out.append(' ');
        out.append(player1.getBowl()).append(")");
        if (player == TKCore.PLAYER1) out.append('<');
        out.append('\n');

        for (int i = 0; i < 9; i++) {
            out.append(' ');
            out.append(i + 1);
            out.append('.');
        }
        out.append('\n');

        System.out.println(out.toString());
    }

}
