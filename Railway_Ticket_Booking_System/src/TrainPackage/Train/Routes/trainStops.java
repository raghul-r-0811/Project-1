package TrainPackage.Train.Routes;

public class trainStops {
    String stopName;
    int stopOrder;

    public trainStops(String stopName, int stopOrder) {
        this.stopName = stopName;
        this.stopOrder = stopOrder;
    }

    public String getStopName() {
        return stopName;
    }

    public int getStopOrder() {
        return stopOrder;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public void setStopOrder(int stopOrder) {
        this.stopOrder = stopOrder;
    }
}
