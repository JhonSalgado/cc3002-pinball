package logic.bonus;

/**
 * Abstract class that represents a bonus object.
 *
 * @author Jhon Salgado
 */
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
