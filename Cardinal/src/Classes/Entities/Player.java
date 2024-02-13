package Classes.Entities;

import Classes.Entities.Base.Entity;

import java.util.Scanner;

public class Player {
    Entity player;

    public Player(){
    }

    public Player(Entity player){
        this.player = player;
    }

    public void createPlayer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the world of Thravis. \n"
        + "What is your name, oh adventurer?");
        String playerName = scanner.nextLine();
        this.player = new Entity(true, playerName, 1, 10, 1);
        startStoryIntroduction(player);
    }
    public Entity getPlayerEntity(){
        return this.player;
    }
    private static void startStoryIntroduction(Entity player){
        System.out.println(player.name + ", this world is under constant thread of destruction.\n"
        + "Not by monsters, but by the Kingdoms ruling it. Something must be done.\n"
        + "Or there will be nothing left to safe ...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
