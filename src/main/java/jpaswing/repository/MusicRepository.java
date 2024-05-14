package jpaswing.repository;

import jpaswing.entity.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MusicRepository extends CrudRepository<Music,Long> {
    Music findFirstByOrderByIdAsc();
}
