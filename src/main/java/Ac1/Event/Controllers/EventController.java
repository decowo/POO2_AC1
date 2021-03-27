package Ac1.Event.Controllers;

import Ac1.Event.DTO.EventDTO;
import Ac1.Event.DTO.EventInsertDTO;
import Ac1.Event.DTO.EventUpdateDTO;
import Ac1.Event.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/Events")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> getEvents(
            @RequestParam(value = "page",         defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
            @RequestParam(value = "direction",    defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy",      defaultValue = "id") String orderBy,
            @RequestParam(value = "name",         defaultValue = "") String name,
            @RequestParam(value = "place",      defaultValue = "") String place,
            @RequestParam(value = "description",      defaultValue = "") String Description

    )
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);

        Page <EventDTO> list = service.getEvent(pageRequest, name.trim(), place.trim());

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable long id)
    {
        EventDTO dto = service.getEventById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<EventDTO> getDescription(@PathVariable String description)
    {
        EventDTO dto = service.getEventbyDescription(description);

        return ResponseEntity.ok().body(dto);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<EventDTO> getName(@PathVariable String name)
    {
        EventDTO dto = service.getEventbyname(name);

        return ResponseEntity.ok().body(dto);
    }

@GetMapping("/place/{place}")
public ResponseEntity<EventDTO> getPlace(@PathVariable String place)
{
    EventDTO dto = service.getEventbyplace(place);

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
