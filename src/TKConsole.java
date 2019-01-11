import java.util.Scanner;

public class TKConsole {
    private TKCore core;

    public TKConsole() {
        this.core = new TKCore();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            print();
            System.out.print("Enter: ");
            int pit = sc.nextInt();
            if (pit == -1) break;
            core.move(pit - 1);
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
            if (player2.getPits()[i] < 10) out.append(' ');
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
            if (player1.getPits()[i] < 10) out.append(' ');
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
