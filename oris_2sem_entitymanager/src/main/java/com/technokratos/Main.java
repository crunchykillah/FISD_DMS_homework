package com.technokratos;

import com.technokratos.component.*;
import com.technokratos.config.Config;
import com.technokratos.model.*;
import org.flywaydb.core.Flyway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        // Создание контекста
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        Flyway flyway = (Flyway) context.getBean("flyway") ;
        flyway.migrate();

        EntityChecker checker = context.getBean(EntityChecker.class);

        if (checker.checkEntityStructure()) {
            GenreRepository genreRepository = context.getBean(GenreRepository.class);
            CountryRepository countryRepository = context.getBean(CountryRepository.class);
            AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
            MusicianRepository musicianRepository = context.getBean(MusicianRepository.class);
            MusicTrackRepository musicTrackRepository = context.getBean(MusicTrackRepository.class);

            Genre genre = new Genre();
            genre.setName("Rock");
            genre.setId(2);
            genre = genreRepository.save(genre);

            Country country = new Country();
            country.setName("USA");
            country.setId(1);
            country = countryRepository.save(country);

            Author author = new Author();
            author.setName("John Doe");
            author.setCountry(country);
            author.setId(1);
            author = authorRepository.save(author);

            Musician musician = new Musician();
            musician.setName("John Smith");
            musician.setCountry(country);
            musician.setId(1);
            musician = musicianRepository.save(musician);

            MusicTrack musicTrack = new MusicTrack();
            musicTrack.setName("My Song");
            musicTrack.setLength(300);
            musicTrack.setMusician(musician);
            musicTrack.setAuthor(author);
            musicTrack.setDate("2024-01-01");
            musicTrack.setGenre(genre);
            musicTrack.setId(12);
            musicTrack = musicTrackRepository.save(musicTrack);


            MusicTrack musicTrackTest = musicTrackRepository.find(12);
            if (musicTrackTest != null) {
                System.out.println("insert и find работают!");
            }

            musicTrackRepository.remove(12);

            musicTrackTest = musicTrackRepository.find(12);
            if (musicTrackTest == null) {
                System.out.println("remove работает!");
            }

        } else {
            System.out.println("структура не соответствует");
        }


    }
}
