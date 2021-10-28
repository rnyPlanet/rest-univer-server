package com.example.demo.service.impl;

import com.example.demo.model.Calendar;
import com.example.demo.model.schedule.TypeClasses;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.service.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public Calendar findByDay(Date date) {
        Calendar result = calendarRepository.findByDay(date).get(0);
        log.info("IN calendar findByDay: {}", result);
        return result;
    }

    @Override
    public Calendar findById(Long id) {
        Calendar result =calendarRepository.findById(id).get();
        log.info("IN calendar findById: {}", result);
        return result;
    }

}