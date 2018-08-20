package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.Hittable;

/**
 * Component for conect the logic and the entity of a game element
 *
 * @author Jhon Salgado
 */
public class HittableComponent extends Component {
    protected Hittable hittable;


    /**
     * Creates a hittable component
     *
     * @param hittable
     */
    public HittableComponent(Hittable hittable){
        this.hittable=hittable;
    }


    /**
     * Return the logic element that are represented by the entity
     *
     * @return a logic.gameelements.Hittable
     */
    public Hittable getHittable(){
        return hittable;
    }
}
