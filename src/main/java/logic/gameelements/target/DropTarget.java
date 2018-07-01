package logic.gameelements.target;

import java.util.Random;

public class DropTarget extends AbstractTarget{

    public DropTarget(){
        active=true;
    }

    @Override
    public int hit() {
        if(isActive()){
            active=false;
            setChanged();
            notifyObservers(0.3);
        }
        return getScore();
    }

    @Override
    public int getScore() {
        if(isActive()) {
            return 100;
        }
        else{
            return 0;
        }
    }
}
