package mod3.repository;

import mod3.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT i FROM Image i ORDER BY posted_time DESC")
    List<Image> findAllImageOrderByDateDesc();
}
