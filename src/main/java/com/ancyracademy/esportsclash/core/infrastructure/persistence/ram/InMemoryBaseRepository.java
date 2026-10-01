package com.ancyracademy.esportsclash.core.infrastructure.persistence.ram;

import com.ancyracademy.esportsclash.core.domain.model.BaseEntity;
import com.ancyracademy.esportsclash.core.infrastructure.persistence.BaseRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryBaseRepository<T extends BaseEntity<T>> implements BaseRepository<T> {


    protected Map<String,T> entities =new HashMap<>();

    @Override
    public Optional<T> findById(String id){
        var entity= this.entities.get(id);
        return entity==null? Optional.empty():Optional.of(entity);
    }

    @Override
    public void save(T entity){
        entities.put(entity.getId(),entity.deepClone());
    }
    @Override
    public void delete(T entity){
        entities.remove(entity.getId());
    }

    @Override
    public void clear(){
        entities.clear();
    }
}
