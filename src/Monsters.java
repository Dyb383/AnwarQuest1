/**
 * Monster class page
 */
public class Monsters
{
    //Each method uses the info from MonsterStats and creates each monster from the information that will be used in battle.
    public MonsterStats createRat() {
        MonsterStats monsterRat = new MonsterStats();
        monsterRat.name = "Rat";
        monsterRat.enemHealth=10;
        monsterRat.enemStr=2;
        monsterRat.xpGiven=10;
        monsterRat.level=1;
        monsterRat.weakness="fire";
        monsterRat.AIdifficulty=1;
        monsterRat.goldGiven=5;
        return monsterRat;
    }

    public MonsterStats createGoblin() {
        MonsterStats monsterGoblin = new MonsterStats();
        monsterGoblin.name = "Goblin";
        monsterGoblin.enemHealth=35;
        monsterGoblin.enemStr=5;
        monsterGoblin.level=2;
        monsterGoblin.xpGiven=20;
        monsterGoblin.weakness="ice";
        monsterGoblin.AIdifficulty=1;
        monsterGoblin.goldGiven=10;
        return monsterGoblin;
    }

    public MonsterStats createOctopus() {
        MonsterStats monsterOctopus = new MonsterStats();
        monsterOctopus.name = "Octopus";
        monsterOctopus.enemHealth=60;
        monsterOctopus.enemStr=8;
        monsterOctopus.level=5;
        monsterOctopus.xpGiven=30;
        monsterOctopus.weakness="fire";
        monsterOctopus.AIdifficulty=1;
        monsterOctopus.goldGiven=15;
        return monsterOctopus;
    }

    public MonsterStats createDragon() {
        MonsterStats monsterDragon = new MonsterStats();
        monsterDragon.name = "Dragon";
        monsterDragon.enemHealth=100;
        monsterDragon.enemStr=10;
        monsterDragon.level=10;
        monsterDragon.xpGiven=50;
        monsterDragon.weakness="ice";
        monsterDragon.AIdifficulty=1;
        monsterDragon.goldGiven=20;
        return monsterDragon;
    }

}