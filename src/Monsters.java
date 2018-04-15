/**
 * Monster class page
 */
public class Monsters
{
    //Each method uses the info from MonsterStats and creates each monster from the information that will be used in battle.
    public MonsterStats createRat(Player p) {
        MonsterStats monsterRat = new MonsterStats();
        monsterRat.name = "Rat";
        monsterRat.enemHealth=10;
        monsterRat.enemStr=2;
        monsterRat.xpGiven=10;
        monsterRat.level=1;
        monsterRat.weakness="fire";
        monsterRat.goldGiven=5;
        if(p.level > 4) 
        {
        	int t = p.level%4;
        	int i = p.level-t;
        	monsterRat.AI(i);
        }
        return monsterRat;
    }

    public MonsterStats createGoblin(Player p) {
        MonsterStats monsterGoblin = new MonsterStats();
        monsterGoblin.name = "Goblin";
        monsterGoblin.enemHealth=35;
        monsterGoblin.enemStr=5;
        monsterGoblin.level=2;
        monsterGoblin.xpGiven=20;
        monsterGoblin.weakness="ice";
        monsterGoblin.goldGiven=10;
        if(p.level > 4) 
        {
        	int t = p.level%4;
        	int i = p.level-t;
        	monsterGoblin.AI(i);
        }
        return monsterGoblin;
    }

    public MonsterStats createOctopus(Player p) {
        MonsterStats monsterOctopus = new MonsterStats();
        monsterOctopus.name = "Octopus";
        monsterOctopus.enemHealth=60;
        monsterOctopus.enemStr=8;
        monsterOctopus.level=5;
        monsterOctopus.xpGiven=30;
        monsterOctopus.weakness="fire";
        monsterOctopus.goldGiven=15;
        if(p.level > 4) 
        {
        	int t = p.level%4;
        	int i = p.level-t;
        	monsterOctopus.AI(i);
        }
        return monsterOctopus;
    }

    public MonsterStats createDragon(Player p) {
        MonsterStats monsterDragon = new MonsterStats();
        monsterDragon.name = "Dragon";
        monsterDragon.enemHealth=100;
        monsterDragon.enemStr=10;
        monsterDragon.level=10;
        monsterDragon.xpGiven=50;
        monsterDragon.weakness="ice";
        monsterDragon.goldGiven=20;
        if(p.level > 4) 
        {
        	int t = p.level%4;
        	int i = p.level-t;
        	monsterDragon.AI(i);
        }
        return monsterDragon;
    }


}