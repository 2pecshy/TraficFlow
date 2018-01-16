package utils.Map.Osm;

import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import utils.Map.Cost.GPS_node;
import utils.Map.Cost.Route;

import java.util.List;
import java.util.Iterator;
public class osmLoader {
    static org.jdom2.Document file;
    static Element root;

    public static final String IDENT_NODE = "node";
    public static final String IDENT_WAY = "way";
    public static final String IDENT_ND = "nd";

    public static DirectedGraph<GPS_node,Route> load(String file_name)
    {
        DirectedGraph<GPS_node,Route> map = new DefaultDirectedGraph<GPS_node, Route>(Route.class);
        SAXBuilder sxb = new SAXBuilder();
        Integer id_tmp,lat_tmp,lon_tmp;
        List current_root;
        Iterator iter;
        Element curent;

        try
        {
            file = sxb.build(new File(file_name));
        }
        catch(Exception e){}

        //On initialise un nouvel élément root avec l'élément root du file.
        root = file.getRootElement();

        current_root = root.getChildren(IDENT_NODE);

        iter = current_root.iterator();
        while(iter.hasNext())
        {

            curent = (Element)iter.next();
            id_tmp = Integer.valueOf(curent.getAttributeValue("id"));
            lat_tmp = Integer.valueOf(curent.getAttributeValue("lat"));
            lon_tmp = Integer.valueOf(curent.getAttributeValue("lon"));
            map.addVertex(new GPS_node(id_tmp,lat_tmp,lon_tmp));
            System.out.println("new vertex: " + curent.getAttributeValue("id") + ", " + curent.getAttributeValue("lat") + ", " + curent.getAttributeValue("lon"));
        }

        current_root = root.getChildren(IDENT_WAY);

        iter = current_root.iterator();
        while(iter.hasNext())
        {

            curent = (Element)iter.next();
            id_tmp = Integer.valueOf(curent.getAttributeValue("id"));
            lat_tmp = Integer.valueOf(curent.getAttributeValue("lat"));
            lon_tmp = Integer.valueOf(curent.getAttributeValue("lon"));
            map.addVertex(new GPS_node(id_tmp,lat_tmp,lon_tmp));
            System.out.println("new vertex: " + curent.getAttributeValue("id") + ", " + curent.getAttributeValue("lat") + ", " + curent.getAttributeValue("lon"));
        }

        return map;
    }
}
