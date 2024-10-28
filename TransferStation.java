import java.util.*;
public class TransferStation extends Station {
    protected ArrayList<Station> otherStations;
    public TransferStation (String line, String name) {
        super(line, name);
        otherStations = new ArrayList<>();
    }
 // Call the parent method to set the next station
    public void addTransferStationNext (Station station) {
            otherStations.add(station);
            station.prev_Station = this;
    }
    public void addTransferStationPrev (Station station) {
            otherStations.add(station);
            station.next = this;
    }
    public List<Station> getOtherStations() {
        return otherStations; // Return a copy to prevent external modification
    }


    public String toString() {
        String ret = "TRANSFER" + super.toString(); 
        ret += "\n\tTransfers: \n";
        if (this.getOtherStations().size() > 0) {
            for (int i = 0; i < this.getOtherStations().size(); i++) {
                ret += "\t" + this.getOtherStations().get(i) + "\n";
            }
        }
        return ret;
    }
} 