package com.example.demo;

import com.example.demo.EventPart.Controller.Responses.ActivityResponses;
import com.example.demo.EventPart.Entities.Event;
import com.example.demo.EventPart.Repos.EventRepo;
import com.example.demo.EventPart.RequestForms.EventRequest;
import com.example.demo.EventPart.Service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class ExtraTest {

    @InjectMocks
    private ActivityService yourService;

    @Mock
    private EventRepo eventRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateActivities() {
        // Arrange
        List<Event> events = new ArrayList<>();
        // Add events to the list

        when(eventRepo.findAll()).thenReturn(events);

        // Act
        yourService.updateActivities();

        // Assert
        // Add assertions based on your expected behavior
        // For example:
        verify(eventRepo, times(events.size())).save(any(Event.class));
    }

    @Test
    void testAddEvent() {
        // Arrange
        EventRequest eventRequest = EventRequest.builder()
                .date("2023-11-23")  // provide a valid date string
                .startTime("10:00:00")  // provide a valid start time string
                .finishTime("12:00:00")  // provide a valid finish time string
                .title("Sample Event")
                .description("Event description")

                .build();// create your event request
        String userID = "someUserID";
        LocalDateTime now = LocalDateTime.now();

        when(eventRepo.findAllByDateAndUserID(any(LocalDateTime.class), eq(userID)))
                .thenReturn(Optional.of(new ArrayList<>()));

        // Act
        ActivityResponses response = yourService.addEvent(eventRequest, userID);

        // Assert
        // Add assertions based on your expected behavior
        // For example:
        assertEquals(AddResponse.class, response.getClass());
    }
}
