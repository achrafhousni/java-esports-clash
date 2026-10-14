package com.ancyracademy.esportsclash.schedule.infrastructure.persistence.jpa;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.BaseRepository;
import com.ancyracademy.esportsclash.core.infrastructure.persistence.sql.SQLBaseRepository;
import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;
import com.ancyracademy.esportsclash.schedule.domain.model.ScheduleDay;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Optional;

public class SQLScheduleDayRepository extends SQLBaseRepository<ScheduleDay> implements ScheduleDayRepository {

    SQLScheduleDayRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<ScheduleDay> getEntityClass() {
        return ScheduleDay.class;
    }

    @Override
    public Optional<ScheduleDay> findByDate(LocalDate date) {
        return Optional.empty();
    }

    @Override
    public Optional<ScheduleDay> findByMatchId(String matchId) {
        return Optional.empty();
    }
}
