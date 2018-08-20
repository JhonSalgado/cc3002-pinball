package gui;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Node;

/**
 * Component for add a alternative view to an Entity
 *
 * @author Jhon Salgado
 */
public class AlternateViewComponent extends Component {
    protected Node baseNode;
    protected Node newNode;


    /**
     * Creates a new instance of AlternateViewComponent
     *
     * @param baseNode
     * @param alternativeNode
     */
    public AlternateViewComponent(Node baseNode, Node alternativeNode){
        this.baseNode=baseNode;
        newNode=alternativeNode;
    }

    /**
     * Return the alternative view of an entity
     *
     * @return the new node for the View
     */
    public Node getAlternativeNode(){
        return newNode;
    }

    /**
     * Return the original view of a entity
     *
     * @return the node for the View
     */
    public Node getBaseNode(){
        return baseNode;
    }

}
