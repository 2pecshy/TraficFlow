package utils.Map.Cost;

public class GraphWeight {

    private Integer weight;
    private Integer v1;
    private Integer v2;

    public GraphWeight(Integer weight_, Integer v1_, Integer v2_){

        weight = weight_;
        v1 = v1_;
        v2 = v2_;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight_){
        weight = weight_;
    }

    public Integer getV1() {
        return v1;
    }

    public Integer getV2() {
        return v2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("(");
        res.append(v1);
        res.append("->");
        res.append(v2);
        res.append(")");
        return res.toString();
    }
}
