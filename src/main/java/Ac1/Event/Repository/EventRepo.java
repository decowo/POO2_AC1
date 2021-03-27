package Ac1.Event.Repository;

import Ac1.Event.Entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
    @Query("SELECT c FROM Event c " +
            "WHERE " +
            "LOWER(c.name)     LIKE   LOWER(CONCAT('%', :name, '%')) AND " +
            "LOWER(c.place)  LIKE   LOWER(CONCAT('%', :place, '%'))     "
    )

    public Page<Event> find(Pageable pageRequest, String name, String place);

    @Query("SELECT c FROM Event c " +
            "WHERE " +
            "LOWER(c.description)     LIKE   LOWER(CONCAT('%', :description, '%'))"
    )
    public Optional<Event> findDescrip(String description);




    @Query("SELECT c FROM Event c " +
    "WHERE " +
    "LOWER(c.name)     LIKE   LOWER(CONCAT('%', :name, '%'))"
)
public Optional<Event> findName(String name);


@Query("SELECT c FROM Event c " +
"WHERE " +
"LOWER(c.place)     LIKE   LOWER(CONCAT('%', :place, '%'))"
)
public Optional<Event> findPlace(String place);
}
