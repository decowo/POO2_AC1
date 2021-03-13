package Ac1.Event.Controllers;

import Ac1.Event.DTO.EventDTO;
import Ac1.Event.DTO.EventInsertDTO;
import Ac1.Event.DTO.EventUpdateDTO;
import Ac1.Event.Entity.Event;
import Ac1.Event.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Events")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getEvents()
    {
        List <EventDTO> list = service.getEvent();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable long id)
    {
        EventDTO dto = service.getEventById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@RequestBody EventUpdateDTO updateDto, @PathVariable Long id){
        EventDTO dto = service.update(id, updateDto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<EventDTO> insert(@RequestBody EventInsertDTO insertDto){
        EventDTO dto = service.insert(insertDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
