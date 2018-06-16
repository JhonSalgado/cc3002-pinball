package logic.bonus;

public abstract class AbstractBonus implements Bonus{
    protected int timesTriggered;
    /**
     * Gets the number of times the bonus has been triggered.
     *
     * @return number of times the bonus has been triggered
     */
    @Override
    public int timesTriggered() {
        return timesTriggered;
    }
}
