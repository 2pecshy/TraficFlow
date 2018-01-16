package utils.Map;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

public class Ui_graph extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;

    public Ui_graph(){

        super("JGraphX TEST01");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            Object v1_ = graph.insertVertex(parent, null, "v1", 20, 20, 10, 10);
            Object v2_ = graph.insertVertex(parent, null, "v2", 240, 150, 10, 10);
            graph.insertEdge(parent, null, 230, v1_, v2_);
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);

    }

    public void show_G(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 320);
        this.setVisible(true);
    }

}
