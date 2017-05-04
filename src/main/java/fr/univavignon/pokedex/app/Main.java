package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

import java.util.Scanner;

/**
 * Created by jonathan on 05/04/17.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("#################");
        System.out.println("### PokemonGo ###");
        System.out.println("#################");

        int userChoice = 0;
        Scanner input = new Scanner(System.in);

        userChoice = displayMainMenu();

        while (true){

            userChoice = displayMainMenu();

            switch (userChoice) {
                case 1:
                    // Create a trainer
                    String trainerName = displayTrainerMenu();

                    switch (trainerName) {
                        case "9":
                            appExit();
                            break;
                        default:
                            PokemonTrainerFactory pokemonTrainerFactory = new PokemonTrainerFactory();
                            PokemonTrainer pokemonTrainer = pokemonTrainerFactory.createTrainer(trainerName);

                            if(pokemonTrainer == null) {
                                int teamChoice = displayTrainerTeamMenu();
                                Team teamName;

                                switch (teamChoice) {
                                    case 1:
                                        teamName = Team.MYSTIC;
                                        break;
                                    case 2:
                                        teamName = Team.INSTINCT;
                                        break;
                                    case 3:
                                        teamName = Team.VALOR;
                                        break;
                                    case 9:
                                        appExit();
                                        break;
                                }

                                pokemonTrainer = pokemonTrainerFactory.createTrainer(trainerName, teamName, new PokedexFactory());
                            }

                            System.out.print("pokemonTrainer : " + pokemonTrainer);
//                            System.out.println("Trainer name : " + pokemonTrainer.getName() + " !");
                            break;
                    }

                    System.out.println("\nTrainer " + trainerName + " has been created/loaded !");
                    break;
                case 9:
                    // "quit" the application
                    appExit();
                    break;
                /*default:
                    // The user input an unexpected choice.
                    System.out.println("Wrong choice...");*/
            }
        }
    }

    private static int displayMainMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("\nChoose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Create or load a trainer");
        System.out.println("9 - Quit");

        System.out.print("\nYour choice : ");

        selection = input.nextInt();
        return selection;
    }

    private static String displayTrainerMenu() {
        String selection;
        Scanner input = new Scanner(System.in);

        System.out.println("\nCreation trainer");
        System.out.println("----------------\n");
        System.out.println("9 - Quit");

        System.out.print("\nYour trainer name : ");

        selection = input.next();
        return selection;
    }

    private static int displayTrainerTeamMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("\nChoice the team");
        System.out.println("----------------\n");
        System.out.println("1 - Team MYSTIC (blue)");
        System.out.println("2 - Team INSTINCT (yellow)");
        System.out.println("3 - Team VALOR (red)");
        System.out.println("9 - Quit");

        System.out.print("\nYour team : ");

        selection = input.nextInt();
        return selection;
    }

    private static void appExit() {
        System.out.println("Good by trainer ! :)");
        System.exit(0);
    }
}
