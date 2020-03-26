package Games;

import printing.PrintingGame;

public class Connectfour extends PrintingGame {

    public Connectfour() {
        super("Schere Stein Papier");
    }

    @Override
    public void run() {
        Boolean win = false, player = true, abr = false;
        String colour = "", first = "none";
        int width = 0, n, height = 0, y, x, w, fieldsx = 0, fieldsy = 0;
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
        // create Field for Numbers
        String[] numbers = new String[fieldsx + 1];
        // for " "
        for (x = 1; x <= fieldsx; x = x + 2) {
            numbers[x] = " ";
        }
        // for number
        n = 1;
        for (x = 0; x <= fieldsx; x = x + 2) {
            numbers[x] = String.valueOf(n);
            n++;
        }
        // create field for number
        for (x = 0; x <= fieldsx; x++) {
            System.out.print(numbers[x]);
        }
        p("");

        String[][] fields = new String[fieldsy + 1][fieldsx + 1];
        // for |
        for (x = 1; x <= fieldsx; x = x + 2) {
            for (y = 0; y <= fieldsy; y = y + 2) {
                fields[y][x] = "|";
            }
        }
        // for -
        for (x = 0; x <= fieldsx; x = x + 2) {
            for (y = 1; y <= fieldsy; y = y + 2) {
                fields[y][x] = "-";
            }
        }
        // for +
        for (x = 1; x <= fieldsx; x = x + 2) {
            for (y = 1; y <= fieldsy; y = y + 2) {
                fields[y][x] = "+";
            }
        }
        // for " "
        for (x = 0; x <= fieldsx; x = x + 2) {
            for (y = 0; y <= fieldsy; y = y + 2) {
                fields[y][x] = " ";
            }
        }
        // creates field for game
        for (y = 0; y <= fieldsy; y++) {
            for (x = 0; x <= fieldsx; x++) {
                System.out.print(fields[y][x]);
            }
            p("");
        }
        // the Game
        while (!win && !abr) {
            // set the line/row
            do {
                if (player) {
                    p("");
                    p("Spieler 1 ist an der Reihe ");
                    p("Sie haben O");
                    colour = "O";
                } else if (!player) {
                    p("");
                    p("Spieler 2 ist an der Reihe ");
                    p("Sie haben X");
                    colour = "X";
                }
                p("In welche Reihe wollen Sie setzen");
                width = input.nextInt();
                width = (width - 1) * 2;

                if (width < 0 || width > fieldsx || fields[0][width] != " ") {
                    p("Bitte wÃ¤hlen Sie eine gÃ¼ltige Reihe");
                }
            } while (width < 0 || width > fieldsx || fields[0][width] != " ");
            // determine the height of each chip
            for (height = fieldsy; height >= 0; height = height - 2) {
                if (fields[height][width] == " ") {
                    // set the chip
                    fields[height][width] = colour;
                    // creat field for numbers
                    for (x = 0; x <= fieldsx; x++) {
                        System.out.print(numbers[x]);
                    }
                    // create a new field
                    p("");
                    for (y = 0; y <= fieldsy; y++) {
                        for (x = 0; x <= fieldsx; x++) {
                            System.out.print(fields[y][x]);
                        }
                        p("");
                    }
                    break;
                }
            }
//check win
            // x=width
            if (height <= fieldsy - (3 * 2)) {
                n = 0;
                for (y = height; y <= height + (3 * 2); y = y + 2) {
                    if (fields[y][width] == colour) {
                        n++;
                    } else {
                        break;
                    }
                }
                if (n == 4) {
                    p("Es gibt einen Sieger");
                    win = true;
                }
            }
            // y=height
            if (!win) {
                n = 0;
                for (x = 0; x <= fieldsx; x = x + 2) {
                    if (fields[height][x] == colour) {
                        n++;
                    } else {
                        n = 0;
                    }
                    if (n == 4) {
                        p("Es gibt einen Sieger");
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
                    if (fields[y][x] == colour) {
                        n++;
                    } else {
                        n = 0;
                    }
                    if (n == 4) {
                        p("Es gibt einen Sieger");
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
                    if (fields[y][x] == colour) {
                        n++;
                    } else {
                        n = 0;
                    }
                    if (n == 4) {
                        p("Es gibt einen Sieger");
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
                    first = "none";
                    n = 0;
                    for (x = w; x <= w + (3 * 2); x = x + 2) {
                        if (first == "none" && fields[y][x] != " ") {
                            first = fields[y][x];
                        }
                        if (fields[y][x] == " " || fields[y][x] == first) {
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
                    first = "none";
                    n = 0;
                    for (y = 0; y <= 3 * 2; y = y + 2) {
                        if (first == "none" && fields[y][x] != " ") {
                            first = fields[y][x];
                        }
                        if (fields[y][x] == " " || fields[y][x] == first) {
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
                    first = "none";
                    n = 0;
                    for (x = w; x <= w + (3 * 2); x = x + 2) {
                        if (first == "none" && fields[y][x] != " ") {
                            first = fields[y][x];
                        }
                        if (fields[y][x] == " " || fields[y][x] == first) {
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
                    first = "none";
                    n = 0;
                    for (x = w; x >= w - (3 * 2); x = x - 2) {
                        if (first == "none" && fields[y][x] != " ") {
                            first = fields[y][x];
                        }
                        if (fields[y][x] == " " || fields[y][x] == first) {
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
                p("Es gibt keine MÃ¶glichkeit mehr um zu gewinnen");
                abr = true;
            }
//end check possibility of win
//switch players
            player = !player;
        }

    }
}
