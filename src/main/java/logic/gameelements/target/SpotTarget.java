package logic.gameelements.target;

public class SpotTarget extends AbstractTarget {

    public SpotTarget(){
        active=true;
    }

    @Override
    public int hit() {
        if(isActive()){
            setChanged();
            notifyObservers();
            active=false;
        }
        return 0;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
