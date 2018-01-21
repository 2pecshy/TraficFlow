package utils.Map.Osm;

import java.util.ArrayList;
import java.util.Iterator;

public class Tag {

    private String k,v;

    Tag(String k_, String v_){

        k = k_;
        v = v_;
    }

    public String getK() {
        return k;
    }

    public String getV() {
        return v;
    }

    public boolean inFilter(ArrayList<Tag> tag){

        Iterator<Tag> iter_filter = tag.iterator();
        Tag curent_tag;

        while (iter_filter.hasNext()){

            curent_tag = iter_filter.next();
            if(curent_tag.getK().equals( this.k) &&
                    curent_tag.getV().equals(this.v)){
                return true;
            }
        }
        return false;
    }

    public static boolean inFilter(ArrayList<Tag> filter,ArrayList<Tag> filter_result){

        Iterator<Tag> iter_filter = filter.iterator();
        Iterator<Tag> iter_filter_result;
        Tag curent_tag_filter,curent_tag_result;

        while (iter_filter.hasNext()){

            curent_tag_filter = iter_filter.next();
            iter_filter_result = filter_result.iterator();
            while(iter_filter_result.hasNext()) {

                curent_tag_result = iter_filter_result.next();
                if (curent_tag_filter.getK().equals(curent_tag_result.k) &&
                        curent_tag_filter.getV().equals(curent_tag_result.v)) {
                    return true;
                }
            }
        }
        return false;
    }
}
