package com.example.demo;

import com.example.demo.EventPart.Controller.Responses.ActivityResponses;
import com.example.demo.EventPart.Controller.Responses.EventResponse;
import com.example.demo.EventPart.Entities.Event;
import com.example.demo.EventPart.Repos.EventRepo;
import com.example.demo.EventPart.Service.ActivityService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class DemoApplicationTests {

	@InjectMocks
	private ActivityService yourService;

	@Mock
	private EventRepo eventRepo;

	@Test
	void testGetAllEventsByUserID() {
		// Arrange
		String userID = "someUserID";
		List<Event> events = new ArrayList<>();
		// Add events to the list

		when(eventRepo.findAllByUserID(userID)).thenReturn(Optional.of(events));

		// Act
		List<EventResponse> eventResponses = yourService.getAllEventsByUserID(userID);

		// Assert
		// Add assertions based on your expected behavior
		// For example:
		assertEquals(events.size(), eventResponses.size());
	}

	@Test
	void testGetAllEventsByUserIDByDay() {
		// Arrange
		String userID = "someUserID";
		String date = "2023-11-22T10:00:00"; // Change this to a valid date string
		LocalDateTime parsedDate = LocalDateTime.parse(date);

		List<Event> events = new ArrayList<>();
		// Add events to the list

		when(eventRepo.findAllByDateAndUserID(parsedDate, userID)).thenReturn(Optional.of(events));

		// Act
		List<EventResponse> eventResponses = yourService.getAllEventsByUserIDByDay(userID, date);

		// Assert
		// Add assertions based on your expected behavior
		// For example:
		assertEquals(events.size(), eventResponses.size());
	}
}
