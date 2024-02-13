import Classes.Combat;
import Classes.Entities.Base.Entity;
import Data.Enemies.Bestiarium;
import Classes.Entities.Player;

import java.util.ArrayList;
import java.util.List;

import java.io.*;

import static Services.EnemyInitializor.initializeEnemies;
import static Services.MapInitializor.initializeMap;

public class Main {
    public static String MAP_FILEPATH = "C:\\Users\\everyone codes\\IdeaProjects\\hashCodeRPG\\Cardinal\\src\\XMLFile\\Map.xml";
    public static String ENEMIES_FILEPATH = "C:\\Users\\everyone codes\\IdeaProjects\\hashCodeRPG\\Cardinal\\src\\XMLFile\\Enemies.xml";

    public static void main(String[] args) {
        try{
            initializeEnemies(ENEMIES_FILEPATH);
            initializeMap(MAP_FILEPATH);
        }
        catch (Exception e){

        }

        Player currentPlayer = new Player();
        currentPlayer.createPlayer();

        boolean runGame = true;
        while (runGame){
            Combat combat = new Combat(currentPlayer.getPlayerEntity(), new ArrayList<>(List.of(spawnEnemy())));
            combat.startCombat();

            while (combat.isCombatActive){
                Combat.Action action = currentPlayer.getPlayerEntity().decideAction();
                combat.combatRound(action);
            }
        }
    }

    public static Entity spawnEnemy(){
        List<Entity> enemies = Bestiarium.getInstance().getEntitiesByName("Goblin");
        Entity currentEnemy = enemies.getFirst();
        System.out.println("A " + currentEnemy.name + " appeared!");
        return currentEnemy;
    }
}