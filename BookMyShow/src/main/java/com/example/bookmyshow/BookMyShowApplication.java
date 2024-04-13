package com.example.bookmyshow;

import com.example.bookmyshow.controllers.*;
import com.example.bookmyshow.dto.*;
import com.example.bookmyshow.exceptions.AuditoriumNotFound;
import com.example.bookmyshow.exceptions.TheatreNotFoundException;
import com.example.bookmyshow.models.Language;
import com.example.bookmyshow.models.SeatType;
import com.example.bookmyshow.models.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class BookMyShowApplication implements CommandLineRunner {
	UserController userController;
	CityController cityController;
	ShowController showController;
	TheatreController theatreController;
	TicketController ticketController;
	AuditoriumController auditoriumController;

	@Autowired
	public BookMyShowApplication(UserController userController, CityController cityController
					, ShowController showController, TheatreController theatreController,
								 TicketController ticketController, AuditoriumController auditoriumController) {
		this.userController = userController;
		this.cityController = cityController;
		this.showController = showController;
		this.theatreController = theatreController;
		this.ticketController = ticketController;
		this.auditoriumController = auditoriumController;
	}

	public static void main(String[] args) {
		System.out.println("Starting the Application...");

		SpringApplication.run(BookMyShowApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World! from Jimen";
	}

	@Override
	public void run(String... args) throws TheatreNotFoundException, AuditoriumNotFound {
		CreateUserRequestDto requestDto = new CreateUserRequestDto();
		requestDto.setName("Jimen Luhar");
		requestDto.setEmail("jjimenpanchal@gmail.com");
		userController.createUser(requestDto);

		//create city
		AddCityRequestDto addCityRequestDto = new AddCityRequestDto();
		addCityRequestDto.setName("Ramsore");
		addCityRequestDto.setPincode(314030L);
		cityController.addCity(addCityRequestDto);

		//add Theatre
		AddTheatreRequestDto addTheatreRequestDto = new AddTheatreRequestDto();
		addTheatreRequestDto.setName("Ramsore Theatre");
		addTheatreRequestDto.setAddress("Ramsore, chikhali, Rajasthan");
		theatreController.addTheatre(addTheatreRequestDto);

		//add Auditorium
		AddAuditoriumRequestDto addAuditoriumRequestDto = new AddAuditoriumRequestDto();
		addAuditoriumRequestDto.setName("Audi 1");
		addAuditoriumRequestDto.setTheatreId(1L);
		auditoriumController.addAuditorium(addAuditoriumRequestDto);

		//add seats
		Map<SeatType, Integer> seats = new HashMap<>();
		seats.put(SeatType.GOLD, 10);
		seats.put(SeatType.SILVER, 20);
		seats.put(SeatType.PLATINUM, 30);
		auditoriumController.addSeats(1L, seats);

		// add shows
		AddShowRequestDto addShowRequestDto = new AddShowRequestDto();
		addShowRequestDto.setAudiId(1L);
		addShowRequestDto.setStartTime(new Date());
		addShowRequestDto.setEndTime(new Date());
		addShowRequestDto.setLanguage(Language.HINDI);
		showController.addShow(addShowRequestDto);

		//Book Tickets
		TicketBookRunner ticketBookRunner1 = new TicketBookRunner(ticketController, 1L, 1L,
				List.of(12L, 13L, 14L));
		TicketBookRunner ticketBookRunner2 = new TicketBookRunner(ticketController, 1L, 1L,
				List.of(13L, 14L, 15L));

		Thread thread1 = new Thread(ticketBookRunner1);
		Thread thread2 = new Thread(ticketBookRunner2);

		thread1.start();
		thread2.start();

	}

}
