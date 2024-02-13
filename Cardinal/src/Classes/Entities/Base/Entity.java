package Classes.Entities.Base;

import Classes.Combat;

import java.util.Scanner;

public class Entity {
    int id;
    public String name;
    public String description;
    double level;
    double health;
    double attack;
    Entity currentTarget;
    boolean isPlayer;

    public Entity(int id, String name, String description, double health, double attack) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.health = health;
        this.attack = attack;
    }

    public Entity(boolean isPlayer, String name, double level, double health, double attack){
        this.isPlayer = isPlayer;
        this.name = name;
        this.level = level;
        this.health = health;
        this.attack = attack;
    }

    public double attack(){
        return this.attack;
    }

    public void getDamaged(double damage){
        this.health -= damage;
    }

    public Combat.Action decideAction(){
        if(this.isPlayer){
            Scanner scanner = new Scanner(System.in);
            Combat.Action playerAction = Combat.Action.INVALID;

            System.out.println("What would you like to do?");
            System.out.println("ATTACK\tDEFEND\tRUN");
            while (playerAction == Combat.Action.INVALID){
                String playerActionString = scanner.nextLine();
                try{
                    playerAction = Combat.Action.valueOf(playerActionString);
                }catch (Exception e){
                    System.out.println(playerActionString + " is not a valid action.");
                }
            }
            return playerAction;
        }else{
            return Combat.Action.ATTACK;
        }
    }

    public Entity getCurrentTarget(){
        return this.currentTarget;
    }

    public void setCurrentTarget(Entity entity){
        this.currentTarget = entity;
    }

    public double getCurrentHealth(){
        return this.health;
    }

    public String getName(){
        return this.name;
    }

    public int getId() {
        return id;
    }
}
