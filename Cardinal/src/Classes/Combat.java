package Classes;

import Classes.Entities.Base.Entity;

import java.util.List;
import java.util.Objects;

public class Combat {
    Entity player;
    List<Entity> combatants;
    public enum Action{
        ATTACK,
        DEFEND,
        RUN,
        INVALID
    }
    public boolean isCombatActive;

    public Combat(Entity player, List<Entity> combatants){
        this.player = player;
        this.combatants = combatants;
    }

    public Entity getCombatantByEntityName(String name){
        return this.combatants.stream().filter(x -> Objects.equals(x.name, name)).toList().getFirst();
    }

    public List<Entity> getCombatants(){
        return combatants;
    }

    public Entity getPlayerEntity(){
        return player;
    }

    public void setIsCombatActive(boolean isCombatActive){
        this.isCombatActive = isCombatActive;
    }

    public void startCombat(){
        player.setCurrentTarget(getCombatants().getFirst());
        for(Entity combatant : combatants){
            combatant.setCurrentTarget(getPlayerEntity());
        }
        this.isCombatActive = true;
    }

    public void combatRound(Action playerAction){
        entityAction(playerAction, getPlayerEntity());
        for(Entity combatant : combatants){
            entityAction(combatant.decideAction(), combatant);
        }
        checkCombat();
        displayCombatInfo();
    }

    private void entityAction(Action entityAction, Entity entity){
        if (entityAction == Action.ATTACK)
            attackTarget(entity.getCurrentTarget(), entity);
        if (entityAction == Action.DEFEND)
            return;
        if (entityAction == Action.RUN)
            return;
    }
    private void attackTarget(Entity target, Entity attacker){
        double damageToTarget = attacker.attack();
        target.getDamaged(damageToTarget);
        System.out.println(attacker.name + " attacked " + target.name + ". It suffered " + damageToTarget + " damage!");
    }

    private void displayCombatInfo(){
        System.out.println("--------------------------------");
        System.out.println("Player: " + getPlayerEntity().name + " \tHealth: " + getPlayerEntity().getCurrentHealth());
        for(Entity combatant : getCombatants()){
            System.out.println("Enemy: " + combatant.name + " \tHealth: " + combatant.getCurrentHealth());
        }
        System.out.println("--------------------------------");
    }

    private void checkCombat(){
        if(getPlayerEntity().getCurrentHealth() <= 0){
            setIsCombatActive(false);
            System.out.println("Combat ended.");
        }

        if(getCombatants().isEmpty()){
            setIsCombatActive(false);
            System.out.println("Combat ended.");
            return;
        }

        for(Entity combatant : getCombatants()){
            if(combatant.getCurrentHealth() <= 0){
                removeCombatantFromFight(combatant);
            }
        }

        if(getCombatants().isEmpty()){
            setIsCombatActive(false);
            System.out.println("Combat ended.");
        }
    }

    private void removeCombatantFromFight(Entity combatant){
        this.combatants.remove(combatant);
    }
}
