package org.example.service;

import org.example.model.*;
import org.example.payment.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {

    private static BookingService instance;

    private PaymentService paymentService;

    private final List<Movie> movies;

    private final List<Multiplex> multiplexes;

    private final Map<String, Show> shows;

    private final Map<String, Booking> bookings;

    private BookingService() {
        this.movies = new ArrayList<>();
        this.multiplexes = new ArrayList<>();
        this.shows = new HashMap<>();
        this.bookings = new HashMap<>();
    }

    public static synchronized BookingService getInstance(){
        if(instance == null){//t1
            instance = new BookingService();
        }
        return instance;
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public void addMultiplex(Multiplex multiplex){
        multiplexes.add(multiplex);
    }

    public void addShow(Show show){
        shows.put(show.getId(), show);
    }

//    public Map<String, Seat> availableSeats(Show show){
//        show.getScreen().getSeats();
//    }

    public void addObserversToPayment(PaymentObserver observer){
        paymentService.addObservers(observer);
    }

    public synchronized Booking bookTicket(User user, Show show, List<Seat> selectedSeats, PaymentMethod paymentMethod){
        // check if the seats are available
        if(areSeatsAvailable(selectedSeats, show)){
            // if yes => change the status of the seats => BOOKED
            markSeatsAsBooked(selectedSeats, show);
            // price
            double totalPrice = calculateTotalPrice(selectedSeats, show);
            Booking booking = new Booking(user, show, selectedSeats, totalPrice);
            bookings.put(booking.getId(), booking);
            PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(paymentMethod);
            paymentService.setPaymentStrategy(paymentStrategy);
            Payment payment = paymentService.processServicePayment(totalPrice);
            booking.setPayment(payment);
            return booking;
        }
        return null;
    }

    public synchronized void confirmBooking(String bookingId){
        Booking booking = bookings.get(bookingId);
        if(booking.getBookingStatus() == BookingStatus.PENDING){
            booking.setBookingStatus(BookingStatus.CONFIRMED);
        }
        System.out.println("Booking successfull");

    }

    public synchronized void cancelBooking(String bookingId){
        Booking booking = bookings.get(bookingId);
        if(booking.getBookingStatus() != BookingStatus.CANCELLED){
            booking.setBookingStatus(BookingStatus.CANCELLED);
            markSeatsAsAvailable(booking.getReservedSeats(), booking.getShow());
        }
        System.out.println("Booking cancelled");

    }

    private synchronized void markSeatsAsAvailable(List<Seat> selectedSeats, Show show){
        for(Seat seat: selectedSeats) {
            Seat showSeat = show.getScreen().getSeats().get(seat.getSeatNumber());
            showSeat.setSeatStatus(SeatStatus.AVAILABLE);
        }
    }

    private double calculateTotalPrice(List<Seat> selectedSeats, Show show){
        return selectedSeats.stream().mapToDouble(Seat::getCategoryPrice).sum()
                +(show.getShowPrice()*selectedSeats.size());
    }

    private void markSeatsAsBooked(List<Seat> selectedSeats, Show show){
        for(Seat seat: selectedSeats) {
            Seat showSeat = show.getScreen().getSeats().get(seat.getSeatNumber());
            showSeat.setSeatStatus(SeatStatus.BOOKED);
        }
    }

    private boolean areSeatsAvailable(List<Seat> selectedSeats, Show show){
        for(Seat seat: selectedSeats){
            Seat showSeat = show.getScreen().getSeats().get(seat.getSeatNumber());
            if(showSeat.getSeatStatus() != SeatStatus.AVAILABLE){
                return false;
            }
        }
        return true;
    }

    public Map<String, Show> getShows() {
        return shows;
    }
}
