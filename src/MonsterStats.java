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
    public void AI(){
        if (AIdifficulty==1){
            enemHealth=enemHealth*1;
            enemStr=enemStr*1;
            xpGiven=xpGiven*1;
            goldGiven=goldGiven*1;
        }
        
        if (AIdifficulty==2){
            enemHealth=enemHealth*2;
            enemStr=enemStr*2;
            xpGiven=xpGiven*2;
            goldGiven=goldGiven*2;
        }
        
        if (AIdifficulty==3){
            enemHealth=enemHealth*3;
            enemStr=enemStr*3;
            xpGiven=xpGiven*3;
            goldGiven=goldGiven*3;
        }
        
        if (AIdifficulty==4){
            enemHealth=enemHealth*4;
            enemStr=enemStr*4;
            xpGiven=xpGiven*4;
            goldGiven=goldGiven*4;
        }
    }
}