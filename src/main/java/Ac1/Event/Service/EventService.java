package Ac1.Event.Service;

import Ac1.Event.DTO.EventDTO;
import Ac1.Event.DTO.EventInsertDTO;
import Ac1.Event.DTO.EventUpdateDTO;
import Ac1.Event.Entity.Event;
import Ac1.Event.Repository.EventRepo;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepo repo;

    public Page<EventDTO> getEvent(PageRequest pageRequest, String name, String place) {
        Page<Event> list = repo.find(pageRequest, name, place);
        return list.map( c -> new EventDTO(c));
    }
    public EventDTO getEventbyDescription(String Description) {
        Optional<Event> op = repo.findDescrip(Description);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        return new EventDTO(event);
    }

    private List<EventDTO> toDTOList(List<Event> list) {
List<EventDTO> listDTO = new ArrayList<>();
for (Event e: list)
{
    listDTO.add(new EventDTO(e.getId(),e.getName(),e.getDescription(),e.getPlace(),e.getStart_time(),e.getEnddate(),e.getStartdate(),e.getEnd_time(),e.getEmail()));
}
return listDTO;
    }

    public EventDTO getEventById(Long id) {
        Optional<Event> op = repo.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        return new EventDTO(event);
    }
    public EventDTO update(long id, EventUpdateDTO updateDTO)
    {
        try
        {
            Event entity = repo.getOne(id);
            entity.setName(updateDTO.getName());
            entity = repo.save(entity);
            return new EventDTO(entity);
        }
        catch (EntityNotFoundException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");

        }
    }
public void delete (long id)
{
    try {
        repo.deleteById(id);
        }
    catch(EmptyResultDataAccessException e)
    {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
    }
}

    public EventDTO insert(EventInsertDTO insertDTO) {
        Event entity = new Event(insertDTO);
        entity = repo.save(entity);
        return new EventDTO(entity);
    }

}
