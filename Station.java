import java.util.*;
public  class Station {
    protected String line;
    protected String name;
    protected boolean in_service;
    protected Station prev_Station;
    protected Station next;

    public Station (String line, String name) {
        this.line = line;
        this.name = name;
        this.in_service = true; // set default value as true 
        this.prev_Station = null;
        this.next = null;
    }
    

    public boolean equals(Station k) {
        if (k.line.equals(this.line) && k.name.equals(this.name)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void connect(Station nextStation) {
        if (this instanceof TransferStation) {
            TransferStation transfer = (TransferStation) this;
           
            if (transfer.next == null) {
                transfer.next = nextStation;
            } else {
                transfer.addTransferStationNext(nextStation);
            }
            nextStation.prev_Station = transfer;
        } else if (nextStation instanceof TransferStation) {
            TransferStation transfers = (TransferStation) nextStation;
           
            if (transfers.prev_Station == null) {
                transfers.prev_Station = this;
            } else {
                transfers.addTransferStationPrev(this);
            }
           
            this.next = transfers;
        } else {
            this.next = nextStation;
            nextStation.prev_Station = this;
        }
        //this.addNext(nextStation);
        //nextStation.addPrev(this);
 
    }
    public void switchAvailable () {
        this.in_service = !in_service;
    }
    
    public boolean isAvailable () {
        return in_service;

    }
    public void addNext(Station next_Station) {
            // Connect the next station normally
            this.next = next_Station; // Set the next station
            //if (next_Station != null) {
            next_Station.prev_Station = this; // Set this station as previous for the next station
            //}
    }
    public void addPrev(Station prev) {
            // Connect the previous station normally
            this.prev_Station = prev; // Set the previous station
            //if (prev != null) {
            prev.next = this; // Set this station as next for the previous station
            //}

    }
    protected String getPrevStationName() {
        if (prev_Station != null) {
            return prev_Station.name;
        }
        else {
            return "none";
        }
    }
    protected String getNextStationName() {
        if (next!= null) {
            return next.name;
        }
        else {
            return "none";
        }
    }

    public String toString() {
        return  "STATION" + " " + name + ":" + " " + line + " " + "line, in service:" + " " + isAvailable() + "," + " "  + "previous station:" + " " + getPrevStationName() + "," + " "+ "next station:" + " " + getNextStationName();
        //return "STATION" + " " + name + ":" + " " + line + " " + "line, in service:" + " " + isAvailable() + "," + " " + "previous station:" + " " + prevname + "," + " "+ "next station:" + " " + nextname;
    }


    public int tripLengthHelper (Station destination, int stops, ArrayList<Station> visited_Stations) {
        if(this.equals(destination)) {
            return 0;
        }
        if (visited_Stations.contains(this)) {
            return - 1;
        }
        if(this.next == null) {
            return -1;
        }
        visited_Stations.add(this);


        if(this.next != null) {
            stops = this.next.tripLengthHelper(destination, stops, visited_Stations);
                if (stops != -1) {
                    return 1 + stops ;
                }
        }
        
        if (this instanceof TransferStation) {
                TransferStation transfer = (TransferStation) this;
                for (Station station :transfer.otherStations) {
                    //Station station = transfer.otherStations.get(i);
                    if (station.equals(destination)) {
                        return stops;
                    }
                    if(!visited_Stations.contains(station)) {
                        stops = station.tripLengthHelper(destination, stops, visited_Stations);
                        if (stops != -1) {
                            return  1 + stops ;
                        }
                    }
                }
        }
        return -1;
    }

    public int tripLength (Station destination) {
        int stops = 0;
        if(this.equals(destination)) {
            return stops;
        }
        if (this.next == null) {
            return - 1;
        }

        ArrayList <Station> visited_Stations = new ArrayList<>();
        return tripLengthHelper(destination, stops, visited_Stations);
    }
}
 