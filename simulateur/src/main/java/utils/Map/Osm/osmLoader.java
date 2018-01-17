package utils.Map.Osm;

import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;
import utils.Map.Cost.GPS_node;
import utils.Map.Map;
import java.util.List;
import java.util.Iterator;

public class osmLoader {
    static org.jdom2.Document file;
    static Element root;

    public static final String IDENT_NODE = "node";
    public static final String IDENT_WAY = "way";
    public static final String IDENT_ND = "nd";

    public static Map load(String file_name)
    {
        Map map = new Map();
        GPS_node[] list_node;
        SAXBuilder sxb = new SAXBuilder();
        long id_tmp;
        Double lat_tmp,lon_tmp;
        List current_root, current_sub_root;
        Iterator iter_root,iter_sub_root,iter_vertex;
        Element curent,v1,v2;

        try
        {
            file = sxb.build(new File(file_name));
        }
        catch(Exception e){}

        root = file.getRootElement();

        current_root = root.getChildren(IDENT_NODE);

        iter_root = current_root.iterator();
        while(iter_root.hasNext())
        {

            curent = (Element)iter_root.next();
            id_tmp = Long.parseLong(curent.getAttributeValue("id"));
            lat_tmp = Double.parseDouble(curent.getAttributeValue("lat"));
            lon_tmp = Double.parseDouble(curent.getAttributeValue("lon"));
            map.addCarrefours(new GPS_node(id_tmp,lat_tmp,lon_tmp));
            System.out.println("new vertex: " + curent.getAttributeValue("id") + ", " + curent.getAttributeValue("lat") + ", " + curent.getAttributeValue("lon"));
        }

        current_root = root.getChildren(IDENT_WAY);

        iter_root = current_root.iterator();
        while(iter_root.hasNext())
        {

            curent = (Element)iter_root.next();
            current_sub_root = curent.getChildren(IDENT_ND);
            iter_sub_root = current_sub_root.iterator();

            while(iter_sub_root.hasNext()){
                v1 = (Element) iter_sub_root.next();
                if(iter_sub_root.hasNext()) {
                    v2 = (Element) iter_sub_root.next();
                    list_node = map.getCarrefours(Double.parseDouble(v1.getAttributeValue("ref")),
                            Double.parseDouble(v2.getAttributeValue("ref")));
                    map.addRoute(list_node[0],list_node[1],1);
                }

            }
        }

        return map;
    }
}
