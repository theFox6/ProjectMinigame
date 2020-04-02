package Games;

import printing.CharField;
import printing.PrintingGame;

public class Connectfour extends PrintingGame {

    public Connectfour() {
        super("4-gewinnt");
    }

    @Override
    public void run() {

        /**
         * deklaration and initalization
         */
        CharField f = new CharField();
        CharField g = new CharField();
        char color = ' ', first = 't', n2;
        String playername = "";
        Boolean win = false, player = true, stop = false, allowed = true, full = false;
        int width = 0, n = 0, height = 0, y, x, w, fieldsx = 0, fieldsy = 0, RADIX = 10, z, play = 0, turns = 0, fieldsP;

        /**
         * set size 
         * loop until size is 4x4 or higher
         */
        do {
            p("Wieviele Felder wollen Sie in X-Richtung?");
            p("(Die Zahlen werden nur bis 9 gelistet)");
            fieldsx = input.nextInt();
            p("Wieviele Felder wollen Sie in Y-Richtung?");
            fieldsy = input.nextInt();
            if (fieldsx < 4 || fieldsy < 4) {
                p("Bitte wählen Sie mindestens 4x4!");
            }
        } while (fieldsx < 4 || fieldsy < 4);

        /**
         * multiplicate to find the max on fields
         */
        fieldsP = fieldsy * fieldsx;

        /**
         * change fieldsx and fieldsy so they fit in the Charfield
         */
        fieldsx = (fieldsx - 1) * 2;
        fieldsy = (fieldsy - 1) * 2;
        p("");

        /**
         * set the Charfield 
         * set numbers
         */
        n = 1;
        y = 0;
        for (x = 0; x <= fieldsx; x = x + 2) {
            n2 = Character.forDigit(n, RADIX);
            g.set(x, y, n2);
            n++;
        }

        /**
         * show the Charfield
         */
        text.print(g.toString(0, 0, fieldsx + 1, 1));

        /**
         * put |,+,- into the Charfild
         */
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

        /**
         * show other Charfield
         */
        text.print(f.toString(0, 0, fieldsx + 1, fieldsy + 1));
        p("");

        /**
         * the real game 
         * loop as long as no win and chance to win (stop)
         */
        while (!win && !stop && !full) {
            turns++;

            /**
             * loop until ture argument
             */
            do {
                /**
                 * output for the user 
                 * set the right arguments
                 */
                if (player) {
                    p("Spieler 1 ist an der Reihe.");
                    p("Sie haben O.");
                    color = 'O';
                    playername = "Spieler 1";
                } else if (!player) {
                    p("Spieler 2 ist an der Reihe.");
                    p("Sie haben X.");
                    color = 'X';
                    playername = "Spieler 2";
                }

                /**
                 * set the row
                 * set width to fit in the Charfiled
                 */
                p("In welche Reihe wollen Sie setzen?");
                width = input.nextInt();
                width = (width - 1) * 2;
                if (width < 0 || width > fieldsx || f.get(width, 0) != ' ') {
                    p("Bitte wählen Sie eine gültige Reihe.");
                }
            } while (width < 0 || width > fieldsx || f.get(width, 0) != ' ');

            /**
             * determine the height of each chip
             * show Charfield again
             */
            for (height = fieldsy; height >= 0; height = height - 2) {
                if (f.get(width, height) == ' ') {
                    // set the chip
                    f.set(width, height, color);
                    // show the Charfield
                    text.print(g.toString(0, 0, fieldsx + 1, 1));
                    text.print(f.toString(0, 0, fieldsx + 1, fieldsy + 1));
                    p("");
                    break;
                }
            }
            /**
             * check if there are 4 in a row
             * controll win
             */
            if (turns >= 7) {
                // x = width
                if (height <= fieldsy - (3 * 2)) {
                    n = 0;
                    for (y = height; y <= height + (3 * 2); y = y + 2) {
                        if (f.get(width, y) == color) {
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

                // y = height
                if (!win) {
                    n = 0;
                    for (x = width - (3 * 2); x <= width + (3 * 2); x = x + 2) {
                        if (f.get(x, height) == color) {
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
                if (!win) {
                    x = width;
                    y = height;
                    n = 0;
                    while (x >= 0 + 2 && y <= fieldsy - 2) {
                        x = x - 2;
                        y = y + 2;
                    }
                    while (x <= fieldsx && y >= 0) {
                        if (f.get(x, y) == color) {
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
                if (!win) {
                    x = width;
                    y = height;
                    n = 0;
                    while (x <= fieldsx - 2 && y <= fieldsy - 2) {
                        x = x + 2;
                        y = y + 2;
                    }
                    while (x >= 0 && y >= 0) {
                        if (f.get(x, y) == color) {
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
            }

            /**
             * possibile win if 4 can be placed in a row 
             * in future method needs upgrade
             */
            if (allowed) {

                /**
                 * check hozizontal for the fist 2 rows
                 */
                if (!win) {
                    for (y = 0; y <= 2; y = y + 2) {
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
                        if (n == 4) {
                            break;
                        }
                    }
                }

                /**
                 * x = variabel 
                 * check for every vertikal row only for the first 4
                 */
                if (!win && n != 4) {
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

                /**
                 * -x 
                 * check for 2 diagonal row
                 */
                if (!win && n != 4) {
                    for (z = 0; z <= 2 && z <= fieldsy - (3 * 2); z = z + 2) {
                        for (w = 0; w <= fieldsx - (3 * 2); w = w + 2) {
                            y = z;
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
                }

                /**
                 * x
                 * check for 2 diagonal row
                 */
                if (!win && n != 4) {
                    for (z = 0; z <= 2 && z <= fieldsy - (3 * 2); z = z + 2) {
                        for (w = fieldsx; w >= 0 + (3 * 2); w = w - 2) {
                            y = z;
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
                }

                /**
                 * end game
                 * or continue until full
                 */
                if (n != 4) {
                    p("Spätestens jetzt besteht keine  Möglichkeit mehr zu gewinnen.");
                    do {
                        p("Wollen Sie dennoch weiterspielen? (1 für ja / 2 für nein)");
                        play = input.nextInt();
                    } while (play < 1 || play > 2);
                    if (play == 1) {
                        allowed = false;
                    } else {
                        stop = true;
                    }
                }
            }
            /**
             * check if the field is full
             */
            if (!allowed && turns == fieldsP) {
                full = true;
                p("Das Feld ist voll.");
            }

            /**
             * switch players
             */
            player = !player;
        }
    }
}
