package Ac1.Event.Controllers;

import Ac1.Event.DTO.EventDTO;
import Ac1.Event.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
