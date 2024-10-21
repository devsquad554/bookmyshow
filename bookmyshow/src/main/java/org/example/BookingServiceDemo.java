package org.example;

import org.example.decfilter.GenreFilter;
import org.example.decfilter.LocationFilter;
import org.example.decfilter.PriceFilter;
import org.example.decfilter.ShowFilter;
import org.example.filter.Filter;
import org.example.filter.FilterManager;
import org.example.filter.MovieFilter;
import org.example.filter.MultiplexFilter;
import org.example.model.*;
import org.example.payment.EmailNotificationObserver;
import org.example.payment.SmsNotificationObserver;
import org.example.service.BookingService;
import org.example.sorter.CheapestShowSorter;
import org.example.sorter.ShowManager;
import org.example.sorter.SortingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingServiceDemo {
    public  static void main(String[] args){
        BookingService bookingService = BookingService.getInstance();

        Movie devara = new Movie("DEVARA", "Telugu", "Action Drama", "20204-10-01", 3);
        Movie inception = new Movie("Inception", "English", "Sci-FI", "20204-10-10", 2);

        Multiplex multiplex = new Multiplex("Maheswari-Pareshwari", "Kacheguda");

        Screen screen = new Screen("Screen1", multiplex,  90);

        multiplex.addScreens(screen);

        Show show = new Show(devara,
                LocalDateTime.of(2024, 10, 13, 22, 0),
                LocalDateTime.of(2024, 10, 14, 1, 0),
                screen);

        Show show1 = new Show(inception,
                LocalDateTime.of(2024, 10, 13, 18, 0),
                LocalDateTime.of(2024, 10, 14, 21, 0),
                screen);

        bookingService.addShow(show);
        bookingService.addShow(show1);

        User rakesh = new User("Rakesh", "rakesh111@gmail.com");
        bookingService.addObserversToPayment(new EmailNotificationObserver());
        bookingService.addObserversToPayment(new SmsNotificationObserver());
        Seat seat1 = new Seat(show.getScreen().getSeats().get("1").getSeatNumber(), SeatCategory.GOLD);
        Seat seat2 = new Seat(show.getScreen().getSeats().get("2").getSeatNumber(), SeatCategory.GOLD);

        Booking booking = bookingService.bookTicket(rakesh,show, List.of(seat1, seat2));

        boolean isPaymentSuccess = true; // whatever phone is returning

        if(booking!=null){
            System.out.println("Booking succesful BookingID: "+ booking.getId());
            if(isPaymentSuccess){
                bookingService.confirmBooking(booking.getId());
            }else{
                bookingService.cancelBooking(booking.getId());
            }
        }


        // strategy desing pattern
        Filter movieFilter = new MovieFilter("DEVARA");
        Filter multiplexFilter = new MultiplexFilter("Maheswari-Pareshwari");

        FilterManager filterManager = new FilterManager();

        filterManager.setFilter(movieFilter);
        filterManager.setFilter(multiplexFilter);
        List<Show> filteredShowsByMovie = filterManager.applyFilters(new ArrayList<>(bookingService.getShows().values()));

        for(Show filteredShow: filteredShowsByMovie){
            System.out.println(filteredShow.getMovie().getTitle());
        }

        // decorator design pattern
        ShowFilter locationFilter = new LocationFilter("HYD");
        ShowFilter genreFilter = new GenreFilter("Action Drama", locationFilter);
        ShowFilter priceFilter = new PriceFilter(60, genreFilter);


        List<Show> decFilterShows = genreFilter.apply(new ArrayList<>(bookingService.getShows().values()));

        for(Show filteredShow: decFilterShows){
            System.out.println(filteredShow.getMovie().getTitle());
        }

        SortingStrategy sortingStrategy = new CheapestShowSorter();
        ShowManager showManager = new ShowManager(sortingStrategy);

        List<Show> sortedFilteredShows  = showManager.getSortShows(decFilterShows);

        sortedFilteredShows.forEach(System.out::println);

    }

}

