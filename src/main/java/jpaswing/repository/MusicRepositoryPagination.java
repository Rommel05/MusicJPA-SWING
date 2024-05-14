package jpaswing.repository;

import jpaswing.entity.Music;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface MusicRepositoryPagination extends PagingAndSortingRepository<Music, Long> {
    @Query("SELECT COUNT(m) FROM Music m")
    public int countAllRecords();
}
