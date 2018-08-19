package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.Hittable;

public class HittableComponent extends Component {
    Hittable hittable;

    public HittableComponent(Hittable hittable){
        this.hittable=hittable;
    }

    public Hittable getHittable(){
        return hittable;
    }
}
