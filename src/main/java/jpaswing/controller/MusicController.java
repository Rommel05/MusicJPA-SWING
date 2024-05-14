package jpaswing.controller;

import jpaswing.entity.Music;
import jpaswing.repository.MusicRepository;
import jpaswing.repository.MusicRepositoryPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MusicController {
    private final MusicRepository musicRepository;
    private final MusicRepositoryPagination musicRepositoryPagination;
    private int currentPage = 0;
    private int count;
    private Optional<Music> currentBook;
    @Autowired
    public MusicController(MusicRepository musicRepository, MusicRepositoryPagination musicRepositoryPagination) {
        this.musicRepository = musicRepository;
        this.musicRepositoryPagination = musicRepositoryPagination;
        this.count = musicRepositoryPagination.countAllRecords();
    }
    public Optional<Music> getBook(){
        //El primer parámetro es el número de página y el segundo los registros que queremos que nos devuelva
        PageRequest pr = PageRequest.of(currentPage, 1);
        currentBook = Optional.of(musicRepositoryPagination.findAll(pr).getContent().get(0));
        return currentBook;
    }

    public Optional<Music> next(){
        //Si ya estoy al final, devuelvo el libro actual
        this.count = musicRepositoryPagination.countAllRecords();
        if (currentPage == this.count -1 ) return currentBook;

        currentPage++;
        return getBook();
    }

    public Optional<Music> previous(){
        //Si ya estoy al principio, devuelvo el libro actual
        if (currentPage == 0) return currentBook;

        currentPage--;
        return getBook();
    }

    public Optional<Music> first(){
        //Primer libro
        currentPage = 0;
        return getBook();
    }

    public Optional<Music> last(){
        //Último libro
        this.count = musicRepositoryPagination.countAllRecords();
        currentPage = count - 1;
        return getBook();
    }
}
