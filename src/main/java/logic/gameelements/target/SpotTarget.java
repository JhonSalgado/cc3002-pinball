package logic.gameelements.target;

public class SpotTarget extends AbstractTarget {

    public SpotTarget(){
        super.active=true;
    }

    @Override
    public int hit() {
        if(isActive()){
            //JackPotBonus;
            active=false;
        }
        return 0;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
