package utils.Map.Cost;

public class Cost {

    private double cost_value;

    Cost(double cost){
        cost_value = cost;
    }

    public double getCost_value() {
        return cost_value;
    }

    public void setCost_value(double cost_value) {
        this.cost_value = cost_value;
    }

    public double getCost(){
        return  cost_value;
    }
}
