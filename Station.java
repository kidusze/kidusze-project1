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
            this.next = next_Station; // Set the next station
            next_Station.prev_Station = this; // Set this station as previous for the next station
            //}
    }
    public void addPrev(Station prev) {
            this.prev_Station = prev; // Set the previous station
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


   