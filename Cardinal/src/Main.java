import Classes.Combat;
import Classes.Entities.Base.Entity;
import Classes.Entities.Enemies.Bestiarium;
import Classes.Map.MapCell;
import Classes.Entities.Player;
import Classes.Map.Map;

import java.util.ArrayList;
import java.util.List;

import java.io.*;

import static Services.EnemyInitializor.initializeEnemies;
import static Services.MapInitializor.initializeMap;

public class Main {
    public static String MAP_FILEPATH = "C:\\Users\\everyone codes\\IdeaProjects\\Cardinal\\src\\Data\\Map\\Map.xml";
    public static String ENEMIES_FILEPATH = "C:\\Users\\everyone codes\\IdeaProjects\\Cardinal\\src\\Data\\Entities\\Enemies.xml";

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