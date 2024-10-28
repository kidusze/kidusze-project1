public class EndStation extends Station {
    public EndStation(String line, String name) {
        super(line, name);
        makeEnd();
    }
    public void makeEnd() {
        // make it points to itself  
        if(this.prev_Station == null && this.next != null) {
            this.prev_Station = next;
        } 
        else if(this.next == null && this.prev_Station != null) {
            this.next = prev_Station;
        }

        //this.prev_Station.next = this;
    }
    public String toString() {
        return "ENDSTATION " + name + ": " + line + " line, in service: " + isAvailable() + ", previous station: " + getPrevStationName() + ", next station: " + getNextStationName();
    }
}
