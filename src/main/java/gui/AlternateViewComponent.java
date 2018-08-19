package gui;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Node;

public class AlternateViewComponent extends Component {
    protected Node baseNode;
    protected Node newNode;


    public AlternateViewComponent(Node baseNode, Node alternativeNode){
        this.baseNode=baseNode;
        newNode=alternativeNode;
    }

    public Node getAlternativeNode(){
        return newNode;
    }

    public Node getBaseNode(){
        return baseNode;
    }

}
