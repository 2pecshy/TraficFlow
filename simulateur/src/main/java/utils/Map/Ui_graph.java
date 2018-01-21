package utils.Map;

import com.mxgraph.swing.mxGraphComponent;
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
        //Object parent = graph.getDefaultParent();

        /*graph.getModel().beginUpdate();
        try {
            Object v1_ = graph.insertVertex(parent, null, "v1", 20, 20, 10, 10);
            Object v2_ = graph.insertVertex(parent, null, "v2", 240, 150, 10, 10);
            graph.insertEdge(parent, null, 230, v1_, v2_);
        } finally {
            graph.getModel().endUpdate();
        }*/

        /*mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);*/

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
                v1 = graph.insertVertex(parent, null, "", currentRoute.getV1().getLon()*50000-min_lon+10, -currentRoute.getV1().getLat()*50000-min_lat+10, 1, 1);
                v2 = graph.insertVertex(parent, null, "", currentRoute.getV2().getLon()*50000-min_lon+10, -currentRoute.getV2().getLat()*50000-min_lat+10, 1, 1);
                graph.insertEdge(parent,null,null,v1,v2);

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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setVisible(true);
    }

}
