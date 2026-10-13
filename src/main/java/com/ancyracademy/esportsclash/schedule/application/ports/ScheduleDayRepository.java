package com.ancyracademy.esportsclash.schedule.application.ports;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.BaseRepository;
import com.ancyracademy.esportsclash.schedule.domain.model.ScheduleDay;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduleDayRepository extends BaseRepository<ScheduleDay> {


    Optional<ScheduleDay> findByDate(LocalDate date);
}
