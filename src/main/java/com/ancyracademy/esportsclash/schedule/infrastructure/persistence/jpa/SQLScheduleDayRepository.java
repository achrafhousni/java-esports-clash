package com.ancyracademy.esportsclash.schedule.infrastructure.persistence.jpa;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.BaseRepository;
import com.ancyracademy.esportsclash.core.infrastructure.persistence.sql.SQLBaseRepository;
import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;
import com.ancyracademy.esportsclash.schedule.domain.model.ScheduleDay;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Optional;

public class SQLScheduleDayRepository extends SQLBaseRepository<ScheduleDay> implements ScheduleDayRepository {

    public SQLScheduleDayRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<ScheduleDay> getEntityClass() {
        return ScheduleDay.class;
    }

    @Override
    public Optional<ScheduleDay> findByDate(LocalDate date) {
        return entityManager.createQuery("SELECT sd FROM ScheduleDay sd WHERE sd.day = :date", ScheduleDay.class)
                .setParameter("date", date)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<ScheduleDay> findByMatchId(String matchId) {
        return entityManager.createQuery("SELECT sd FROM ScheduleDay sd JOIN sd.matches m WHERE m.id = :matchId", ScheduleDay.class)
                .setParameter("matchId", matchId)
                .getResultList()
                .stream()
                .findFirst();
    }
}
