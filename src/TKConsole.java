import java.util.Scanner;

public class TKConsole {
    private TKCore core;
    private MinMax test1;
    private MinMax test2;

    public TKConsole() {
        this.core = new TKCore();
        this.test1 = new MinMax(core, TKCore.PLAYER1, 4);
        this.test2 = new MinMax(core, TKCore.PLAYER2, 7);
    }

    public void start() {
        while (true) {
            print();
            System.out.print("Enter: ");
            if (core.getCurrentPlayer()== TKCore.PLAYER1) {
                int pit = test1.find();
                System.out.println(pit+1);
                core.move(pit);
            }else {
                int pit = test2.find();
                System.out.println(pit+1);
                core.move(pit);
            }
            if(core.finish()){
                print();
                System.out.println("End----------------------------");
                break;
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
        if (player==TKCore.PLAYER2)out.append('<');
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
        if (player==TKCore.PLAYER1)out.append('<');
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
