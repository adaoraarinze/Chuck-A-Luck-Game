/* SELF ASSESSMENT

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String)
and the Wallet object, and a void return type [Mark out of 7: 7].
Comment: Yes my method ResolveBet is correctly defined.

My program presents the amount of cash in the wallet
and asks the user how much he/she would like to bet [Mark out of 8: 8].
Comment: Yes my program presents the amount of cash in the wallet and asks the user what they will bet.

My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5: 5].
Comment: Yes I have error checked my program.

My program creates three Dice objects, rolls them and creates
a total variable with a summation of the roll values returned [Mark out of 15: 15].
Comment: Yes the Dice class works well with my program.

My program determines the winnings by comparing the bet type with the total
and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20].
Comment: Yes my program correctly determines the winnings for each bet type.

My program outputs the results (win or loss) and adds the winnings to the wallet
if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10].
Comment: Yes my program outputs the correct results and adds or takes away money from the wallet
accordingly.

2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15]
Comment: Yes my program correctly utilises the wallet object.

My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5: 5]
Comment: Yes my program loops accordingly.

I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
Comment: Yes I ask the user for one of the 4 bet types.

My program calls resolveBet for each bet type entered [Mark out of 5: 5].
Comment:  Yes my program calls ResolveBet accordingly.

At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 5]
Comment: Yes my program presents a final summary.

 Total Mark out of 100 (Add all the previous marks): 100
*/

import java.util.Scanner;

public class ChuckALuck {
    public static void main(String[] args) {
        System.out.println("""
                Welcome to a game of Chuck A Luck. The rules are as follows:
                There are 4 types of bets: triple, field, high and low.
                Triple: All three dice must show the same number except for 1s or all 6s. Payout is 30:1.
                Field: The total of the 3 dice must sum to a number less than 8 or greater than 12. Payout is 1:1.
                High: The total of the 3 dice must be greater than 10 but can't be a triple. Payout is 1:1.
                Low: The total of the 3 dice must be less than 11 but can't be a triple. Payout is 1:1.
                \s""");
        System.out.print("Enter the amount of money that you have in your wallet: ");
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        double cash = 0;
        Wallet theWallet = new Wallet();

        do
        {

            if (input.hasNextDouble())
            {

                while(!quit) {

                    cash = input.nextDouble();

                    if (cash <= 0)
                    {
                        System.out.println("Error - Enter a valid amount.");
                        System.out.print("Enter the amount of money that you have in your wallet: ");
                    }

                    else
                    {
                        theWallet.put(cash);

                        while(!quit)
                        {
                            if(theWallet.check() <= 0)
                            {
                                quit = true;
                            }

                            else
                            {
                                System.out.print("Type quit or enter the type of bet you want to place: ");
                                String bet = input.next();


                                if (bet.equalsIgnoreCase("triple")
                                        || bet.equalsIgnoreCase("field")
                                        || bet.equalsIgnoreCase("high")
                                        || bet.equalsIgnoreCase("low"))
                                {
                                    ResolveBet(bet, theWallet);
                                }

                                else if (bet.equalsIgnoreCase("quit"))
                                {
                                    quit = true;
                                }

                                else
                                {
                                    System.out.println("Error - enter a valid bet.");
                                }
                            }
                        }
                    }
                }
            }

            else
            {
                System.out.println("Error - Enter a valid amount.");
                input.next();
                System.out.print("Enter the amount of money that you have in your wallet: ");
            }

        } while(!quit);

        System.out.println("You started with €" + cash + " in your wallet, and now you have €" +
                        theWallet.check() + " in your wallet. Thanks for playing!");
    }

    public static void ResolveBet( String bet, Wallet theWallet ) {
        System.out.print("You have €" + theWallet.check() +" in your wallet, how much would you like to bet?: ");
        Scanner input = new Scanner(System.in);
        boolean quit = false;

        while (!quit)
        {

            if (input.hasNextDouble())
            {

                double cashBet = input.nextDouble();

                if (theWallet.get(cashBet))
                {
                    Dice dice1 = new Dice();
                    Dice dice2 = new Dice();
                    Dice dice3 = new Dice();

                    int total = dice1.roll();
                    total = total + dice2.roll();
                    total = total + dice3.roll();

                    quit = true;

                    if (bet.equalsIgnoreCase("triple"))
                    {

                        if ((dice1.topFace() == dice2.topFace()) && (dice1.topFace() == dice3.topFace())
                                && dice1.topFace() != 1 && dice1.topFace() != 6)
                        {
                            double winnings = cashBet * 30;
                            cashBet = winnings + cashBet;
                            theWallet.put(cashBet);

                            System.out.println("You won €" + winnings + "!");
                        }

                        else
                        {
                            System.out.println("You lost €" + cashBet);
                        }

                    }

                    if (bet.equalsIgnoreCase("field"))
                    {

                        if (total < 8 || total > 12)
                        {
                            double winnings = cashBet * 2;
                            cashBet = winnings + cashBet;
                            theWallet.put(cashBet);

                            System.out.println("You won €" + winnings + "!");
                        }

                        else
                        {
                            System.out.println("You lost €" + cashBet);
                        }

                    }

                    if (bet.equalsIgnoreCase("high"))
                    {

                        if (total > 10 || ((dice1.topFace() == dice2.topFace()) && (dice1.topFace() == dice3.topFace())
                                && dice1.topFace() != 4 && dice1.topFace() != 5 && dice1.topFace() != 6))
                        {
                            double winnings = cashBet * 2;
                            cashBet = winnings + cashBet;
                            theWallet.put(cashBet);

                            System.out.println("You won €" + winnings + "!");
                        }

                        else
                        {
                            System.out.println("You lost €" + cashBet);
                        }

                    }

                    if (bet.equalsIgnoreCase("low"))
                    {

                        if (total < 11 || ((dice1.topFace() == dice2.topFace()) && (dice1.topFace() == dice3.topFace())
                                && dice1.topFace() != 1 && dice1.topFace() != 2 && dice1.topFace() != 3))
                        {
                            double winnings = cashBet * 2;
                            cashBet = winnings + cashBet;
                            theWallet.put(cashBet);

                            System.out.println("You won €" + winnings + "!");
                        }

                        else
                        {
                            System.out.println("You lost €" + cashBet);
                        }

                    }
                }

                else if (!theWallet.get(cashBet))
                {
                    System.out.println("You don't have the funds for that, try again.");
                    System.out.print("You have €" + theWallet.check() + " in your wallet, " +
                            "how much would you like to bet?: ");
                }

            }

            else
            {
                System.out.println("Error - enter a valid amount.");
                input.next();
                System.out.print("You have €" + theWallet.check() + " in your wallet, " +
                        "how much would you like to bet?: ");
            }
        }
    }
}

