package utils.Map.Cost;

public class Cost {

    private Integer cost_value[][];
    private Integer vertex_number;

    Cost(Integer vertex_number_){
        vertex_number = vertex_number_;
        cost_value = new Integer[vertex_number_][vertex_number_];
        for(int i = 0; i < vertex_number; i++){
            for(int j = 0; j < vertex_number; j++){
                cost_value[i][j] = Integer.MIN_VALUE;
            }
        }
    }

    public Integer getCost(Integer v1,Integer v2) {
        return cost_value[v1][v2];
    }

    public void setCost(Integer v1,Integer v2, Integer value) {
        this.cost_value[v1][v2] = value;
    }

}
