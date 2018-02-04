package utils.Map;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import utils.Map.Cost.Route;

import javax.swing.*;
import java.util.Iterator;
import java.util.Set;

public class Ui_graph extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;
    private mxGraph graph;

    public Ui_graph(){

        super("TraficFlow");
        graph = new mxGraph();

    }

    public void setUIGraphFromMap(Map map){
        //TODO mapToMxGraph
        Set<Route> routeSet = map.getRoutes();
        Iterator<Route> iterRoute = routeSet.iterator();
        Route currentRoute;
        Object v1;
        Object v2;
        Object parent = graph.getDefaultParent();
        double min_lon;
        double min_lat;
        String colorRed;
        String colorGreen;

        graph.getModel().beginUpdate();

        graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
        graph.removeCells(graph.getChildEdges(graph.getDefaultParent()));

        parent = graph.getDefaultParent();
        String styleParent = mxConstants.STYLE_FILLCOLOR + "=#ff0000";

        min_lat = Double.MAX_VALUE;
        min_lon = Double.MAX_VALUE;
        try {
            while (iterRoute.hasNext()){

                currentRoute = iterRoute.next();
                if(-currentRoute.getV1().getLat()*50000 < min_lat){
                    min_lat = -currentRoute.getV1().getLat()*50000;
                }
                if(-currentRoute.getV2().getLat()*50000 < min_lat){
                    min_lat = -currentRoute.getV2().getLat()*50000;
                }
                if(currentRoute.getV1().getLon()*50000 < min_lon){
                    min_lon = currentRoute.getV1().getLon()*50000;
                }
                if(currentRoute.getV2().getLon()*50000 < min_lon){
                    min_lon = currentRoute.getV2().getLon()*50000;
                }
            }
            iterRoute = routeSet.iterator();
            while (iterRoute.hasNext()){

                currentRoute = iterRoute.next();
                v1 = graph.insertVertex(parent, null, "", currentRoute.getV1().getLon()*50000-min_lon+10, -currentRoute.getV1().getLat()*50000-min_lat+10, 1, 1,mxConstants.STYLE_FILLCOLOR+"=#FF0000");
                v2 = graph.insertVertex(parent, null, "", currentRoute.getV2().getLon()*50000-min_lon+10, -currentRoute.getV2().getLat()*50000-min_lat+10, 1, 1,mxConstants.STYLE_FILLCOLOR+"=#FF0000");
                if(currentRoute.getAgents().size() >= 10) {
                    graph.insertEdge(parent, null, null, v1, v2, "strokeColor=#FF0000");
                }
                else if(currentRoute.getAgents().size() >= 5){
                    graph.insertEdge(parent, null, null, v1, v2, "strokeColor=#CCCC00");
                }
                else{
                    graph.insertEdge(parent, null, null, v1, v2, "strokeColor=#0000FF");
                }

            }
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public void setTitle(String title){
        super.setTitle(title);
    }

    public void show_G(){

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setVisible(true);
    }


}
