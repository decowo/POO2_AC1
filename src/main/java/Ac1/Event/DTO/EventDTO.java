package Ac1.Event.DTO;

import Ac1.Event.Entity.Event;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDTO {

    public EventDTO(Event event) {
        setName(event.getName());
        setDescription(event.getDescription());
        setPlace(event.getPlace());
        setStart_time(event.getStart_time());
        setStartdate(event.getStartdate());
        setEnddate(event.getEnddate());
        setEnd_time(event.getEnd_time());
        setEmail(event.getEmail());

    }
    public EventDTO(Long id, String name, String description, String place, LocalTime start_time, LocalDate enddate, LocalDate startdate, LocalTime end_time, String email) {
        setName(name);
        setDescription(description);
        setPlace(place);
        setStart_time(start_time);
        setStartdate(startdate);
        setEnddate(enddate);
        setEnd_time(end_time);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name;
    private String description;
    private String place;
    @JsonFormat(pattern = "yyyy-MM-dd-")
    private LocalDate startdate;
    @JsonFormat(pattern = "yyyy-MM-dd-")
    private LocalDate enddate;
    private LocalTime start_time;
    private LocalTime end_time;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
