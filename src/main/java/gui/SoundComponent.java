package gui;

import com.almasb.fxgl.entity.component.Component;

/**
 * Component for add a specific sound to an entity
 *
 * @author Jhon Salgado
 */
public class SoundComponent extends Component {
    protected String audioAsset;
    protected String baseAudioAsset;

    /**
     * Creates a sound component adding a sound from the assets
     *
     * @param asset
     */
    public SoundComponent(String asset){
        this.audioAsset=asset;
        this.baseAudioAsset=asset;
    }

    /**
     * return the entity collision sound
     *
     * @return an audio asset
     */
    public String getAudio(){
        return audioAsset;
    }

    /**
     * set the collision sound to another, is useful for perceive when a target is disabled
     *
     * @param newAsset
     */
    public void setNewAudio(String newAsset){
        audioAsset=newAsset;
    }


    /**
     * set the collision sound to the original sound
     *
     */
    public void setBaseAudioAsset(){
        audioAsset=baseAudioAsset;
    }
}
