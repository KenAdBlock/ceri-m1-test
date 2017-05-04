package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

import java.util.Scanner;

/**
 * Created by jonathan on 05/04/17.
 */
public class Main {

    private static PokemonTrainer pokemonTrainer = null;

    public static void main(String[] args) {
        System.out.println("#################");
        System.out.println("### PokemonGo ###");
        System.out.println("#################");

        String trainerName = "";
        while (true){

            int userChoice = displayMainMenu();

            switch (userChoice) {
                case 1:
                    // Create or loaded a trainer
                    String trainerNameChoice = displayTrainerMenu();
                    trainerName = trainerNameChoice;

                    switch (trainerNameChoice) {
                        case "9":
                            // "quit" the application
                            appExit();
                            break;
                        default:
                            // Create a trainer with or without team
                            PokemonTrainerFactory pokemonTrainerFactory = new PokemonTrainerFactory();
                            pokemonTrainer = pokemonTrainerFactory.createTrainer(trainerName);

                            if(pokemonTrainer == null) {
                                int teamChoice = displayTrainerTeamMenu();
                                Team teamName = Team.VALOR;

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

                                //System.out.print("tn:" + trainerName);
                                pokemonTrainer = pokemonTrainerFactory.createTrainer(trainerName, teamName, new PokedexFactory());
                                System.out.print("New trainer created : " + pokemonTrainer.getName());
                            }

                            System.out.println(pokemonTrainer.getName() + " has been loaded !");
                            break;
                    }

                    break;
                case 9:
                    // "quit" the application
                    appExit();
                    break;
                default:
                    // The user input an unexpected choice.
                    System.out.println("Wrong choice...");
            }
        }
    }

    private static int displayMainMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("\nChoose from these choices");
        System.out.println("-------------------------");
        System.out.println("1 - Create or load a trainer");
        System.out.println("9 - Quit");

        System.out.print("\nYour choice : ");

        selection = input.nextInt();
        return selection;
    }

    private static String displayTrainerMenu() {
        String selection;
        Scanner input = new Scanner(System.in);

        System.out.println("\nCreation of trainer");
        System.out.println("-------------------");
        System.out.println("9 - Quit");

        System.out.print("\nYour trainer name : ");

        selection = input.next();
        return selection;
    }

    private static int displayTrainerTeamMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("\nChoice the team");
        System.out.println("---------------");
        System.out.println("1 - Team MYSTIC (blue)");
        System.out.println("2 - Team INSTINCT (yellow)");
        System.out.println("3 - Team VALOR (red)");
        System.out.println("9 - Quit");

        System.out.print("\nYour team : ");

        selection = input.nextInt();
        return selection;
    }

    private static void appExit() {
        String trainerName = "guest";

        if(pokemonTrainer != null) {
            trainerName = pokemonTrainer.getName();
        }

        System.out.println("Good bye " + trainerName + " ! :)");
        System.exit(0);
    }
}
