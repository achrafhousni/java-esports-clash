package com.ancyracademy.esportsclash.schedule.infrastructure.persistence.ram;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.BaseRepository;
import com.ancyracademy.esportsclash.core.infrastructure.persistence.ram.InMemoryBaseRepository;
import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;
import com.ancyracademy.esportsclash.schedule.domain.model.ScheduleDay;

import java.time.LocalDate;
import java.util.Optional;

public class InMemoryScheduleDayRepository extends InMemoryBaseRepository<ScheduleDay> implements ScheduleDayRepository {
    @Override
    public Optional<ScheduleDay> findByDate(LocalDate date) {
        return  entities.values().stream()
                .filter(scheduleDay -> scheduleDay.getDay().equals(date))
                .findFirst();
    }
}
