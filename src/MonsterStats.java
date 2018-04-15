/**
 * Monster Stats class
 */
public class MonsterStats
{
    //Stats and info that each monster will have is listed here.
    String name;
    int enemHealth;
    int enemStr;
    int xpGiven;
    int goldGiven;
    String weakness;
    int AIdifficulty;
    int level;
    //The AI goes here, and will have differnet difficulties -- If I have time...
    public void AI(int i){
            enemHealth=enemHealth*i;
            enemStr=enemStr*i;
            xpGiven=xpGiven*i;
            goldGiven=goldGiven*i;
    }
}