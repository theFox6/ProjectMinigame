package Games;

import printing.CharField;
import printing.PrintingGame;

public class Connectfour extends PrintingGame {

    public Connectfour() {
        super("4-gewinnt");
    }

    @Override
    public void run() {
        CharField f = new CharField();
        CharField g = new CharField();
        char colour = ' ', first = 't';
        String playername = "";
        Boolean win = false, player = true, abr = false;
        int width = 0, n = 0, height = 0, y, x, w, fieldsx = 0, fieldsy = 0;
        // set size
        do {
            p("Wieviele Fields wollen Sie in X-Richtung");
            fieldsx = input.nextInt();
            p("Wieviele Fields wollen Sie in Y-Richtung");
            fieldsy = input.nextInt();
            if (fieldsx < 4 || fieldsy < 4) {
                p("Bitte wÃ¤hlen Sie mindestens 4x4 ");
            }
        } while (fieldsx < 4 || fieldsy < 4);

        fieldsx = (fieldsx - 1) * 2;
        fieldsy = (fieldsy - 1) * 2;
        p("");
        n = 1;
        y = 0;
        for (x = 0; x <= fieldsx; x = x + 2) {
            final int RADIX = 10;
            char ch = Character.forDigit(n, RADIX);
            g.set(x, y, ch);
            n++;
        }
        //print field
          text.print(g.toString(0, 0, fieldsx + 1,1));
          
        //Charfield
        // for |
        for (y = 0; y <= fieldsy; y = y + 2) {
            for (x = 1; x <= fieldsx; x = x + 2) {
                f.set(x, y, '|');
            }
        }
        //for -
        for (y = 1; y <= fieldsy; y = y + 2) {
            for (x = 0; x <= fieldsx; x = x + 2) {
                f.set(x, y, '-');
            }
        }
        //for +
        for (y = 1; y <= fieldsy; y = y + 2) {
            for (x = 1; x <= fieldsx; x = x + 2) {
                f.set(x, y, '+');
            }
        }
        //print field
         text.print(f.toString(0, 0, fieldsx + 1, fieldsy + 1));
         p("");
        // the Game
        while (!win && !abr) {
            // set the line/row
            do {
                if (player) {
                    p("Spieler 1 ist an der Reihe.");
                    p("Sie haben O.");
                    colour = 'O';
                    playername = "Spieler 1";
                } else if (!player) {
                    p("Spieler 2 ist an der Reihe.");
                    p("Sie haben X.");
                    colour = 'X';
                    playername = "Spieler 2";
                }
                p("In welche Reihe wollen Sie setzen.");
                width = input.nextInt();
                width = (width - 1) * 2;

                if (width < 0 || width > fieldsx || f.get(width, 0) != ' ') {
                    p("Bitte wÃ¤hlen Sie eine gÃ¼ltige Reihe.");
                }
            } while (width < 0 || width > fieldsx || f.get(width, 0) != ' ');
            // determine the height of each chip
            for (height = fieldsy; height >= 0; height = height - 2) {
                if (f.get(width, height) == ' ') {
                    // set the chip
                    f.set(width, height, colour);
                    // create a new field
                    text.print(g.toString(0, 0, fieldsx + 1,1));
                    text.print(f.toString(0, 0, fieldsx + 1, fieldsy + 1));
                    p("");
                    break;
                }
            }
//check win
            // x=width
            if (height <= fieldsy - (3 * 2)) {
                n = 0;
                for (y = height; y <= height + (3 * 2); y = y + 2) {
                    if (f.get(width, y) == colour) {
                        n++;
                    } else {
                        break;
                    }
                }
                if (n == 4) {
                    p(playername + " hat gewonnen.");
                    win = true;
                }
            }
            // y=height
            if (!win) {
                n = 0;
                for (x = 0; x <= fieldsx; x = x + 2) {
                    if (f.get(x, height) == colour) {
                        n++;
                    } else {
                        n = 0;
                    }
                    if (n == 4) {
                        p(playername + " hat gewonnen.");
                        win = true;
                        break;
                    }
                }
            }
            // x
            if (!win && height <= fieldsy - (3 * 2)) {
                x = width;
                y = height;
                n = 0;
                while (x >= 0 + 2 && y <= fieldsy - 2) {
                    x = x - 2;
                    y = y + 2;
                }
                while (x <= fieldsx && y >= 0) {
                    if (f.get(x, y) == colour) {
                        n++;
                    } else {
                        n = 0;
                    }
                    if (n == 4) {
                        p(playername + " hat gewonnen.");
                        win = true;
                        break;
                    }
                    x = x + 2;
                    y = y - 2;
                }
            }
            // -x
            if (!win && height <= fieldsy - (3 * 2)) {
                x = width;
                y = height;
                n = 0;
                while (x <= fieldsx - 2 && y <= fieldsy - 2) {
                    x = x + 2;
                    y = y + 2;
                }
                while (x >= 0 && y >= 0) {
                    if (f.get(x, y) == colour) {
                        n++;
                    } else {
                        n = 0;
                    }
                    if (n == 4) {
                        p(playername + " hat gewonnen.");
                        win = true;
                        break;
                    }
                    x = x - 2;
                    y = y - 2;
                }
            }
//end check win
// control for the possibility of win
            // y=0
            if (!win) {
                y = 0;
                for (w = 0; w <= fieldsx - (3 * 2); w = w + 2) {
                    first = 't';
                    n = 0;
                    for (x = w; x <= w + (3 * 2); x = x + 2) {
                        if (first == 't' && f.get(x, y) != ' ') {
                            first = f.get(x, y);
                        }
                        if (f.get(x, y) == ' ' || f.get(x, y) == first) {
                            n++;
                        } else {
                            break;
                        }
                    }
                    if (n == 4) {
                        break;
                    }
                }
            }
            if (!win && n != 4) {
                // x=variabel
                for (x = 0; x <= fieldsx; x = x + 2) {
                    first = 't';
                    n = 0;
                    for (y = 0; y <= 3 * 2; y = y + 2) {
                        if (first == 't' && f.get(x, y) != ' ') {
                            first = f.get(x, y);
                        }
                        if (f.get(x, y) == ' ' || f.get(x, y) == first) {
                            n++;
                        } else {
                            break;
                        }
                    }
                    if (n == 4) {
                        break;
                    }
                }
            }
            if (!win && n != 4) {
                // -x
                for (w = 0; w <= fieldsx - (3 * 2); w = w + 2) {
                    y = 0;
                    first = 't';
                    n = 0;
                    for (x = w; x <= w + (3 * 2); x = x + 2) {
                        if (first == 't' && f.get(x, y) != ' ') {
                            first = f.get(x, y);
                        }
                        if (f.get(x, y) == ' ' || f.get(x, y) == first) {
                            n++;
                        } else {
                            break;
                        }
                        y = y + 2;
                    }
                    if (n == 4) {
                        break;
                    }
                }
            }
            if (!win && n != 4) {
                // x
                for (w = fieldsx; w >= 0 + (3 * 2); w = w - 2) {
                    y = 0;
                    first = 't';
                    n = 0;
                    for (x = w; x >= w - (3 * 2); x = x - 2) {
                        if (first == 't' && f.get(x, y) != ' ') {
                            first = f.get(x, y);
                        }
                        if (f.get(x, y) == ' ' || f.get(x, y) == first) {
                            n++;
                        } else {
                            break;
                        }
                        y = y + 2;
                    }
                    if (n == 4) {
                        break;
                    }
                }
            }
            if (n != 4) {
                p("Es gibt keine MÃ¶glichkeit mehr um zu gewinnen.");
                abr = true;
            }
//end check possibility of win
//switch players
            player = !player;
        }

    }
}
