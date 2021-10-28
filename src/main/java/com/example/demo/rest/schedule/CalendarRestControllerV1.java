package com.example.demo.rest.schedule;

import com.example.demo.model.Calendar;
import com.example.demo.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/v1/calendar/")
public class CalendarRestControllerV1 {

    private final CalendarService calendarService;

    @Autowired
    public CalendarRestControllerV1(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("day/{date}")
    public ResponseEntity<Calendar> getDay(@PathVariable(name = "date") String dateStr) throws ParseException {

        LocalDate localDate = LocalDate.parse(dateStr);

        Date date = java.sql.Date.valueOf(localDate);

        Calendar calendar = calendarService.findByDay(date);

        if (calendar != null) {
            return new ResponseEntity<>(calendar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("dayById/{id}")
    public Calendar getFooById(@PathVariable Long id) {
        return calendarService.findById(id);
    }
}