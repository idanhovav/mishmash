package eventplanner;

import java.util.ArrayList;

public class eventplanner{
	//an array list that stores all the events
	static ArrayList<Event> events = new ArrayList<Event>();

	public static void main(String args[]){
		int response;
		response = Menu();
		
		if(response == 1)
		{
			createEvent();
		}
		

		
		
	}

	private static void createEvent() {
		// TODO Asks user for info about event, then creates the event
		int year, month, day, startTime, duration;
		String title;
		
		//add print and input statements to get info
		
		Event event = new Event(year, month, day, startTime, duration, title);
		events.add(event);
	}

	private static int Menu() {
		// TODO prints out options for making event, changing event, etc.
		return 0;
	}

}
