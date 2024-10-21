package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Screen {
    private final String id;

    private String name;

    private List<Show> shows;

    private Multiplex multiplex;

    private Map<String, Seat> seats;

    private int totalSeats;

    public Screen(String name, Multiplex multiplex, int totalSeats) {
        this.id = IDGenerator.generateID();
        this.name = name;
        this.shows = new ArrayList<>();
        this.multiplex = multiplex;
        this.seats = initialiseSeats();
        this.totalSeats = totalSeats;
    }

    private Map<String, Seat> initialiseSeats(){
        Map<String, Seat> seatMap = new HashMap<>();
        for(int i=1; i<=10; i++){
            seatMap.put(String.valueOf(i), new Seat(String.valueOf(i), SeatCategory.SILVER));
        }
        for(int i=11; i<=20; i++){
            seatMap.put(String.valueOf(i), new Seat(String.valueOf(i), SeatCategory.GOLD));
        }
        for(int i=21; i<=30; i++){
            seatMap.put(String.valueOf(i), new Seat(String.valueOf(i), SeatCategory.PLATINUM));
        }
        return  seatMap;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void addShows(Show show) {
        this.shows.add(show);
    }

    public Multiplex getMultiplex() {
        return multiplex;
    }

    public void setMultiplex(Multiplex multiplex) {
        this.multiplex = multiplex;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }
}
