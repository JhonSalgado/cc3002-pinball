package gui;

import com.almasb.fxgl.entity.component.Component;

public class SoundComponent extends Component {
    protected String audioAsset;
    protected String baseAudioAsset;

    public SoundComponent(String asset){
        this.audioAsset=asset;
        this.baseAudioAsset=asset;
    }

    public String getAudio(){
        return audioAsset;
    }

    public void setNewAudio(String newAsset){
        audioAsset=newAsset;
    }

    public void setBaseAudioAsset(){
        audioAsset=baseAudioAsset;
    }
}
