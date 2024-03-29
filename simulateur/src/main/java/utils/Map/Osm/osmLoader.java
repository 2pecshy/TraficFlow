package utils.Map.Osm;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import utils.Map.Cost.GPS_node;
import utils.Map.Map;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class osmLoader {
    static org.jdom2.Document file;
    static Element root;

    public static final String IDENT_NODE = "node";
    public static final String IDENT_WAY = "way";
    public static final String IDENT_ND = "nd";
    public static final String IDENT_TAG = "tag";

    public static final Tag WAY_MOTORWAY = new Tag("highway","motorway");//autoroute
    public static final Tag WAY_MOTORWAY_LINK = new Tag("highway","motorway_link");//autoroute entrée/sortie
    public static final Tag WAY_SECONDARY = new Tag("highway","secondary");//departemental
    public static final Tag WAY_TERTIARY = new Tag("highway","tertiary");
    public static final Tag ONEWAY = new Tag("oneway", "yes");
    public static final Tag ROUNDABOUT = new Tag("junction","roundabout");//rond point
    public static final Tag CIRCULAR = new Tag("junction","circular");//rond point avec feux

    private static ArrayList<Tag> tagFilter(ArrayList<Tag> filter,Iterator iter_sub_root){
        Element v1;
        Iterator<Tag> iter_filter;
        Tag curent_tag;
        ArrayList<Tag> res = new ArrayList<Tag>();

        while(iter_sub_root.hasNext()){

            iter_filter = filter.iterator();
            v1 = (Element) iter_sub_root.next();
            while (iter_filter.hasNext()){

                curent_tag = iter_filter.next();
                if(curent_tag.getK().equals( v1.getAttributeValue("k")) &&
                        curent_tag.getV().equals(v1.getAttributeValue("v"))){
                    res.add(curent_tag);
                }
            }
        }
        return res;
    }

    public static Map load(String file_name)
    {
        Map map = new Map();
        GPS_node[] list_node;
        SAXBuilder sxb = new SAXBuilder();
        long id_tmp;
        Double lat_tmp,lon_tmp;
        List current_root, current_sub_root;
        Iterator iter_root,iter_sub_root;
        Element curent,v1,v2;
        ArrayList<Tag> default_filter = new ArrayList<Tag>(),filter_result,isMotorWay,isOneWay;
        boolean oneway;

        default_filter.add(WAY_MOTORWAY);
        default_filter.add(WAY_MOTORWAY_LINK);
        default_filter.add(WAY_SECONDARY);
        default_filter.add(WAY_TERTIARY);
        default_filter.add(ROUNDABOUT);
        default_filter.add(ONEWAY);
        default_filter.add(CIRCULAR);

        isMotorWay = new ArrayList<Tag>();
        isMotorWay.add(WAY_MOTORWAY);
        isMotorWay.add(WAY_MOTORWAY_LINK);
        isMotorWay.add(WAY_SECONDARY);
        isMotorWay.add(WAY_TERTIARY);

        isOneWay = new ArrayList<Tag>();
        isOneWay.add(ROUNDABOUT);
        isOneWay.add(ONEWAY);
        isOneWay.add(CIRCULAR);

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
            //System.out.println("new vertex: " + curent.getAttributeValue("id") + ", " + curent.getAttributeValue("lat") + ", " + curent.getAttributeValue("lon"));
        }

        current_root = root.getChildren(IDENT_WAY);
        iter_root = current_root.iterator();
        while(iter_root.hasNext()) {

            curent = (Element) iter_root.next();
            current_sub_root = curent.getChildren(IDENT_TAG);
            iter_sub_root = current_sub_root.iterator();

            filter_result = tagFilter(default_filter, iter_sub_root);
            oneway = Tag.inFilter(filter_result,isOneWay);
            if (Tag.inFilter(filter_result,isMotorWay)) {

                current_sub_root = curent.getChildren(IDENT_ND);
                iter_sub_root = current_sub_root.iterator();
                v1 = (Element) iter_sub_root.next();
                while (iter_sub_root.hasNext()) {

                    v2 = (Element) iter_sub_root.next();
                    list_node = map.getCarrefours(Double.parseDouble(v1.getAttributeValue("ref")),
                            Double.parseDouble(v2.getAttributeValue("ref")));
                    map.addRoute(list_node[0], list_node[1], 1);
                    if (!oneway) {
                        map.addRoute(list_node[1], list_node[0], 1);
                    }
                    v1 = v2;
                }
            }
        }
        map.updateSourcesAndSinks();
        return map;
    }
}
