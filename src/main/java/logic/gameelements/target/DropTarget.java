package logic.gameelements.target;

import java.util.Random;

public class DropTarget extends AbstractTarget{

    public DropTarget(){
        super.active=true;
    }

    @Override
    public int hit() {
        if(isActive()){
            Random rnd= new Random();
            if(rnd.nextInt(100)<30){
                //ExtraBallBonus
            }
            active=false;
        }
        return 0;
    }

    @Override
    public int getScore() {
        return 100;
    }
}
