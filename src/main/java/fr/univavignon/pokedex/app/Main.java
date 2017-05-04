package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by jonathan on 05/04/17.
 */
public class Main {

    private static PokemonTrainerFactory pokemonTrainerFactory = new PokemonTrainerFactory();
    private static PokemonTrainer pokemonTrainer = null;


    public static void main(String[] args) {
        System.out.println("#################");
        System.out.println("### PokemonGo ###");
        System.out.println("#################");

        //String trainerName = "";
        while (true){

            int userChoice = displayMainMenu();


            if(pokemonTrainer == null) {
                displayGuestMenu(userChoice);
            } else {
                displayLoadedTrainerMenu(userChoice);
            }



        }
    }

    private static int displayMainMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("\nChoose from these choices");
        System.out.println("-------------------------");

        if(pokemonTrainer == null) {
            System.out.println("1 - Create or load a trainer");
        } else {
            System.out.println("1 - Create pokemon");
            System.out.println("2 - Show pokemons");
        }


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


    private static void displayGuestMenu(int userChoice) {

        switch (userChoice) {
            case 1:
                // Create or loaded a trainer
                String trainerNameChoice = displayTrainerMenu();
                String trainerName = trainerNameChoice;

                switch (trainerNameChoice) {
                    case "9":
                        // "quit" the application
                        appExit();
                        break;
                    default:
                        // Create a trainer with or without team
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
                            save();
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


    private static void displayLoadedTrainerMenu(int action) {

        switch (action) {
            case 1:
                System.out.println("\nCreating pokemon");
                System.out.println("---------------");


                System.out.print("Pokemon id : ");
                System.out.print("Pokemon cp : ");
                System.out.print("Pokemon hp : ");
                System.out.print("Pokemon dust : ");
                System.out.print("Pokemon candy : ");

                int index = 1;
                int hp = 1;
                int cp = 0;
                int dust = 0;
                int candy = 0;

                Pokemon newPokemon = pokemonTrainer.getPokedex().createPokemon(index, cp, hp, dust, candy);

                pokemonTrainer.getPokedex().addPokemon(newPokemon);

                save();

                try {
                    String pokemonName = pokemonTrainer.getPokedex().getPokemon(pokemonTrainer.getPokedex().size() - 1).getName();
                    System.out.print("Congratulations ! You have new pokemon : " + pokemonName + "\n");
                } catch (PokedexException e) {
                    e.printStackTrace();
                }

                break;
            case 2:
                System.out.println("\nYour pokemons");
                System.out.println("---------------");

                for (Pokemon pokemon: pokemonTrainer.getPokedex().getPokemons()) {

                    System.out.println(pokemon.getIndex() + " - " + pokemon.getName());
                }

                break;
            case 9:
                appExit();
                break;
            default:
                // The user input an unexpected choice.
                System.out.println("Wrong choice...");
        }


    }

    private static void save() {

        if(pokemonTrainer != null) {

            try {
                pokemonTrainerFactory.saveData(pokemonTrainer);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (PokedexException e) {
                e.printStackTrace();
            }
        }
    }

    private static void appExit() {
        String trainerName = "guest";

        if(pokemonTrainer != null) {
            trainerName = pokemonTrainer.getName();
            save();
        }

        System.out.println("Good bye " + trainerName + " ! :)");
        System.exit(0);
    }
}
