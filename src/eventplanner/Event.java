package eventplanner;

public class Event {
	private int year, month, day;
	private int startTime, duration;
	private String title;

	public Event(int year, int month, int day, int startTime, int duration, String title) {
		this.year = year;
		this.month = month;
		this.day = day;
		
		this.startTime = startTime;
		this.duration = duration;
		
		this.title = title;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public int getDay()
	{
		return day;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public int getStartTime()
	{
		return startTime;
	}
	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}
	public int getDuration()
	{
		return duration;
	}
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getDate()
	{
		return (String) (year + "-" + month + "-" + day);
	}
	public int getEndTime()
	{
		//mod 24 to stay within time
		return ( (startTime + duration) % 24 );
	}
	
}
