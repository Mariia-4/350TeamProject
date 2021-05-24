package cs350s21project.cli;

import java.util.*;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorCreateActor;
import cs350s21project.controller.command.actor.CommandActorDefineShip;
import cs350s21project.controller.command.actor.CommandActorSetAltitudeDepth;
import cs350s21project.controller.command.actor.CommandActorSetCourse;
import cs350s21project.controller.command.actor.CommandActorSetSpeed;
import cs350s21project.controller.command.view.CommandViewCreateWindowTop;
import cs350s21project.controller.command.view.CommandViewDeleteWindow;
import cs350s21project.datatype.AgentID;
import cs350s21project.datatype.Altitude;
import cs350s21project.datatype.CoordinateWorld3D;
import cs350s21project.datatype.Course;
import cs350s21project.datatype.Groundspeed;
import cs350s21project.datatype.Latitude;
import cs350s21project.datatype.Longitude;

public class Commands {
	
	//VIEW COMMANDS----------
	public static void createWindow(String textCommand) {
		//split string command to get values
		String[] input = textCommand.split("[\\s()]");
		//create id
		AgentID id = new AgentID(input[2]);
		//get size
		int size = Integer.parseInt(input[6]);
		//get latitude1
		String[] lat = input[8].split("[*'#\"]");
		int d = Integer.parseInt(lat[0]);
		int m = Integer.parseInt(lat[1]);
		double s = Double.parseDouble(lat[2]);
		Latitude latitude1 = new Latitude(d, m, s);
		//get latitude2
		String[] lat2 = input[9].split("[*'#\"]");
		d = Integer.parseInt(lat2[0]);
		m = Integer.parseInt(lat2[1]);
		s = Double.parseDouble(lat2[2]);
		Latitude latitude2 = new Latitude(d, m, s);
		//get latitude3
		String[] lat3 = input[10].split("[*'#\"]");
		d = Integer.parseInt(lat3[0]);
		m = Integer.parseInt(lat3[1]);
		s = Double.parseDouble(lat3[2]);
		Latitude latitude3 = new Latitude(d, m, s);
		//get longitude1
		String[] long1 = input[12].split("[*'#\"]");
		d = Integer.parseInt(long1[0]);
		m = Integer.parseInt(long1[1]);
		s = Double.parseDouble(long1[2]);
		Longitude longitude1 = new Longitude(d, m, s);
		//get longitude2
		String[] long2 = input[13].split("[*'#\"]");
		d = Integer.parseInt(long2[0]);
		m = Integer.parseInt(long2[1]);
		s = Double.parseDouble(long2[2]);
		Longitude longitude2 = new Longitude(d, m, s);
		//get longitude3
		String[] long3 = input[14].split("[*'#\"]");
		d = Integer.parseInt(long3[0]);
		m = Integer.parseInt(long3[1]);
		s = Double.parseDouble(long3[2]);
		Longitude longitude3 = new Longitude(d, m, s);
		
		//create command passing in all values
		CommandViewCreateWindowTop comm = new CommandViewCreateWindowTop(CommandManagers.getInstance(), textCommand, id, size, latitude1, latitude2, latitude3, longitude1, longitude2, longitude3);
		//pass in command to schedule
		CommandManagers.getInstance().schedule(comm);
	}
	
	public static void deleteWindow(String textCommand) {
		String[] input = textCommand.split("\\s");
		AgentID ID = new AgentID(input[2]);
		CommandViewDeleteWindow comm = new CommandViewDeleteWindow(CommandManagers.getInstance(), textCommand, ID);
		CommandManagers.getInstance().schedule(comm);	
	}
	
	//ACTOR COMMANDS
	public static void defineShip(String textCommand) {
		String[] input = textCommand.split("\\s");
		AgentID id = new AgentID(input[2]);
		
		//create list of AgentID's to save each munition 
		List<AgentID> munitions = new ArrayList<AgentID>();
		int i = 4;
		while(i < input.length) {
			AgentID m = new AgentID(input[i]);
			munitions.add(m);
			i++;
		}
		
		CommandActorDefineShip comm = new CommandActorDefineShip(CommandManagers.getInstance(), textCommand, id, munitions);
		CommandManagers.getInstance().schedule(comm);
	}
	
	public static void createActor(String textCommand) {
		String[] input = textCommand.split("\\s");
		AgentID id1 = new AgentID(input[2]);
		AgentID id2 = new AgentID(input[4]);
		
		//split coordinates by / to get altitude, longitude, and latitude 
		String[] coord = input[6].split("/");
		Altitude altitude = new Altitude(Double.parseDouble(coord[2]));

		int d;
		int m;
		double s;
		//Further split latitude and longitude to get degrees, minutes, seconds
		String[] alt = coord[0].split("[*'\"]");
		d = Integer.parseInt(alt[0]);
		m = Integer.parseInt(alt[1]);
		s = Double.parseDouble(alt[2]);
		Latitude latitude = new Latitude(d, m, s);
		
		String[] lon = coord[1].split("[*'\"]");
		d = Integer.parseInt(lon[0]);
		m = Integer.parseInt(lon[1]);
		s = Double.parseDouble(lon[2]);
		Longitude longitude = new Longitude(d, m, s);

		CoordinateWorld3D coordinate = new CoordinateWorld3D(latitude, longitude, altitude);
		
		Course course = new Course(Double.parseDouble(input[9]));
		Groundspeed speed = new Groundspeed(Double.parseDouble(input[11]));
		
		CommandActorCreateActor comm = new CommandActorCreateActor(CommandManagers.getInstance(), textCommand, id1, id2, coordinate, course, speed);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void setCourse(String textCommand) {
		String[] input = textCommand.split("\\s");
		AgentID id = new AgentID(input[1]);
		Course course = new Course(Double.parseDouble(input[3]));
		
		CommandActorSetCourse comm = new CommandActorSetCourse(CommandManagers.getInstance(), textCommand, id, course);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void setSpeed(String textCommand) {
		String[] input = textCommand.split("\\s");
		AgentID id = new AgentID(input[1]);
		Groundspeed speed = new Groundspeed(Double.parseDouble(input[3]));
		
		CommandActorSetSpeed comm = new CommandActorSetSpeed(CommandManagers.getInstance(), textCommand, id, speed);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void setAltitudeDepth(String textCommand) {
		String[] input = textCommand.split("[\\s|]");
		AgentID id = new AgentID(input[1]);
		Altitude altitude = new Altitude(Double.parseDouble(input[4]));
			
		CommandActorSetAltitudeDepth comm = new CommandActorSetAltitudeDepth(CommandManagers.getInstance(), textCommand, id, altitude);
		CommandManagers.getInstance().schedule(comm);
	}
	
}
