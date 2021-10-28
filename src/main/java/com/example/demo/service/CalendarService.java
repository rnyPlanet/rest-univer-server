package com.example.demo.service;

import com.example.demo.model.Calendar;

import java.util.Date;

public interface CalendarService {

    Calendar findByDay(Date date);

    Calendar findById(Long id);

}
