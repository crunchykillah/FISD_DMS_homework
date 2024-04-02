package com.technokratos.component;

import com.technokratos.manager.EntityManager;
import com.technokratos.model.MusicTrack;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MusicTrackRepository {

    private final EntityManager entityManager;

    public MusicTrack save(MusicTrack musicTrack) {
        try {
            return entityManager.save(musicTrack);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Integer id) {
        entityManager.remove(MusicTrack.class, id);
    }

    public MusicTrack find(Integer id) {
        return entityManager.find(MusicTrack.class, id);
    }

    public List<MusicTrack> findAll() {
        return entityManager.findAll(MusicTrack.class);
    }
}
