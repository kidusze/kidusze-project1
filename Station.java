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
            TransferStation tS = (TransferStation) this;
            
            if (tS.next == null) {
                tS.next = nextStation;
            } else {
                tS.addTransferStationNext(nextStation);
            }
            nextStation.prev_Station = tS;
        } else if (nextStation instanceof TransferStation) {
            TransferStation stS = (TransferStation) nextStation;
            
            if (stS.prev_Station == null) {
                stS.prev_Station = this;
            } else {
                stS.addTransferStationPrev(this);
            }
            
            this.next = stS;
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
        //Station start_of_Station = this;
        //System.out.println("Visiting: " + this.name + ", Stops so far: " + stops);
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
        //stops++;


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
 /* 
    public int tripLength(Station dest) {
        // Base case: If the current station is the destination we return 0 indicating success
        if(this.equals(dest)) {
            return 0;
        }
        //Base case : If there is no next station we return -1 which should tell us that the destination is unreachable since there is nothing there 
        if(this.next_Station == null) {
            return -1;
        }

    }
    */


    //base case: station and destionation
   //bases case is when is when you stop at your destination
   //create a helpre method
   //go to the beginning station and recursively call 
   //when youre at the transfer station you will have to recursively check all of the transfer paths
   /* 
   public int tripLengthHelper(Station destination, int count, Station start_of_Station, ArrayList<Station> visitedStations ) {
      if (start_of_Station.equals(destination)) {
        return count;
      }
      if (start_of_Station == null) {
        return -1;
      }
      visitedStations.add(start_of_Station);
      if (start_of_Station instanceof TransferStation) {
        TransferStation transfer = (TransferStation) start_of_Station;
        for (Station station :transfer.otherStations) {

        }
      }
   }



   public int tripLength(Station destination) {
    int count = 0;
    Station start_of_Station = this;
    ArrayList <Station> visitedStations = new ArrayList<>();
    return tripLengthhelper(destination,count,start_of_Station, visitedStations);
   }

    if(curr.equals(destination)) 
        return count;
    }
    if(curr.next == null) {
        return -1;
    }
    if (visitedStations.contains(curr)) {
        return -1;
    }
    visitedStations.add(curr);
    if (curr instanceof TransferStation) {
        TransferStation transfer = (TransferStation) curr;
        for (int i=0; i < transfer.otherStations.size(); i++) {
            Station station = transfer.otherStations.get(i);
            if(station.getline().equals(destination.getline())) {
                int result = tripLengthhelper (destination, count + 1, station, visitedStations);
                if (result != -1) {
                    return result;
                }
        }
        //String desline = destination.getline();
        
        }
        
    }
    //System.out.println("curr station:" + curr + ", count:" + count);
    return tripLengthhelper(destination, count +1, curr.next, visitedStations); 
}

public int tripLength (Station dest) {
int count = 0;
Station curr = this;

if (this.equals(destination)) {
    return count;
}
if (this.next == null) {
    return -1;
}
//System.out.println("curr station:" + curr + ", count:" + count);
ArrayList <Station> visitedStations = new ArrayList<>();
return tripLengthhelper(destination,0, this, visitedStations);
}
public boolean equals (Station a) {
if(this.line == a.line && this.name == a.name) {
    return true;
}
else {
    return false;
}
}
}
*/
