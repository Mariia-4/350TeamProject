package cs350s21project.cli;

import java.util.*;
import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorCreateActor;
import cs350s21project.controller.command.actor.CommandActorDefineShip;
import cs350s21project.controller.command.actor.CommandActorDeployMunition;
import cs350s21project.controller.command.actor.CommandActorDeployMunitionShell;
import cs350s21project.controller.command.actor.CommandActorLoadMunition;
import cs350s21project.controller.command.actor.CommandActorSetAltitudeDepth;
import cs350s21project.controller.command.actor.CommandActorSetCourse;
import cs350s21project.controller.command.actor.CommandActorSetSpeed;
import cs350s21project.controller.command.actor.CommandActorSetState;
import cs350s21project.controller.command.misc.CommandMiscExit;
import cs350s21project.controller.command.misc.CommandMiscLoad;
import cs350s21project.controller.command.misc.CommandMiscPause;
import cs350s21project.controller.command.misc.CommandMiscResume;
import cs350s21project.controller.command.misc.CommandMiscSetUpdate;
import cs350s21project.controller.command.misc.CommandMiscWait;
import cs350s21project.controller.command.munition.CommandMunitionDefineBomb;
import cs350s21project.controller.command.munition.CommandMunitionDefineDepthCharge;
import cs350s21project.controller.command.munition.CommandMunitionDefineMissile;
import cs350s21project.controller.command.munition.CommandMunitionDefineShell;
import cs350s21project.controller.command.munition.CommandMunitionDefineTorpedo;
import cs350s21project.controller.command.sensor.CommandSensorDefineAcoustic;
import cs350s21project.controller.command.sensor.CommandSensorDefineDepth;
import cs350s21project.controller.command.sensor.CommandSensorDefineDistance;
import cs350s21project.controller.command.sensor.CommandSensorDefineRadar;
import cs350s21project.controller.command.sensor.CommandSensorDefineSonarActive;
import cs350s21project.controller.command.sensor.CommandSensorDefineSonarPassive;
import cs350s21project.controller.command.sensor.CommandSensorDefineThermal;
import cs350s21project.controller.command.sensor.CommandSensorDefineTime;
import cs350s21project.controller.command.view.CommandViewCreateWindowTop;
import cs350s21project.controller.command.view.CommandViewDeleteWindow;
import cs350s21project.datatype.AgentID;
import cs350s21project.datatype.Altitude;
import cs350s21project.datatype.AngleNavigational;
import cs350s21project.datatype.AttitudePitch;
import cs350s21project.datatype.AttitudeYaw;
import cs350s21project.datatype.CoordinateWorld3D;
import cs350s21project.datatype.Course;
import cs350s21project.datatype.DistanceNauticalMiles;
import cs350s21project.datatype.FieldOfView;
import cs350s21project.datatype.Groundspeed;
import cs350s21project.datatype.Latitude;
import cs350s21project.datatype.Longitude;
import cs350s21project.datatype.Power;
import cs350s21project.datatype.Sensitivity;
import cs350s21project.datatype.Time;

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
		String[] input = textCommand.split("[\\s()]");
		AgentID id = new AgentID(input[2]);
		
		//create list of AgentID's to save each munition 
		List<AgentID> munitions = new ArrayList<AgentID>();
		int i = 6;
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
	
	//MISC COMMANDS----------------------------------------------------------
	public static void miscLoad(String textCommand) {
		String[] input = textCommand.split("\\s");
		String filename = input[1];
				
		CommandMiscLoad comm = new CommandMiscLoad(CommandManagers.getInstance(), textCommand, filename);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void miscPause(String textCommand) {
		CommandMiscPause comm = new CommandMiscPause(CommandManagers.getInstance(), textCommand);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void miscResume(String textCommand) {
		CommandMiscResume comm = new CommandMiscResume(CommandManagers.getInstance(), textCommand);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void miscSetUpdate(String textCommand) {
		String[] input = textCommand.split("\\s");
		Time time = new Time(Double.parseDouble(input[2]));
			
		CommandMiscSetUpdate comm = new CommandMiscSetUpdate(CommandManagers.getInstance(), textCommand, time);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void miscWait(String textCommand) {
		String[] input = textCommand.split("\\s");
		Time time = new Time(Double.parseDouble(input[1]));
				
		CommandMiscWait comm = new CommandMiscWait(CommandManagers.getInstance(), textCommand, time);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void setState(String textCommand) {
		String[] input = textCommand.split("\\s");
		AgentID id = new AgentID(input[1]);
				
		String[] coord = input[4].split("/");
		Altitude altitude = new Altitude(Double.parseDouble(coord[2]));

		int d;
		int m;
		double s;
		//Further split latitude and longitude to get degrees, minutes, seconds
		String[] lat = coord[0].split("[*'\"]");
		d = Integer.parseInt(lat[0]);
		m = Integer.parseInt(lat[1]);
		s = Double.parseDouble(lat[2]);
		Latitude latitude = new Latitude(d, m, s);
			
		String[] lon = coord[1].split("[*'\"]");
		d = Integer.parseInt(lon[0]);
		m = Integer.parseInt(lon[1]);
		s = Double.parseDouble(lon[2]);
		Longitude longitude = new Longitude(d, m, s);

		CoordinateWorld3D coordinate = new CoordinateWorld3D(latitude, longitude, altitude);
				
		Course course = new Course(Double.parseDouble(input[7]));
			
		Groundspeed speed = new Groundspeed(Double.parseDouble(input[9]));
				
		CommandActorSetState comm = new CommandActorSetState(CommandManagers.getInstance(), textCommand, id, coordinate, course, speed);
		CommandManagers.getInstance().schedule(comm);
				
	}

	public static void miscExit(String textCommand) {
		CommandMiscExit comm = new CommandMiscExit(CommandManagers.getInstance(), textCommand);
		CommandManagers.getInstance().schedule(comm);
	}
			
	//SENSOR/FUZES COMMANDS-----------------------------------------
	public static void defineRadar(String textCommand) 
	{
		String[] input = textCommand.split("\\s");
				
		AgentID id = new AgentID(input[3]);
		AngleNavigational angle = new AngleNavigational(Double.parseDouble(input[8]));
		FieldOfView fov = new FieldOfView(angle);
		Power p = new Power(Double.parseDouble(input[10]));
		Sensitivity sen = new Sensitivity(Double.parseDouble((input[12])));
				
		CommandSensorDefineRadar comm = new CommandSensorDefineRadar(CommandManagers.getInstance(), textCommand, id, fov, p, sen);
		CommandManagers.getInstance().schedule(comm);	
	}
	
	public static void defineThermal(String textCommand) 
	{		
		String[] input = textCommand.split("\\s");
				
		AgentID id = new AgentID(input[3]);
		AngleNavigational angle = new AngleNavigational(Double.parseDouble(input[8]));
		FieldOfView fov = new FieldOfView(angle);
		Sensitivity sen = new Sensitivity(Double.parseDouble((input[10])));
				
		CommandSensorDefineThermal comm = new CommandSensorDefineThermal(CommandManagers.getInstance(), textCommand, id, fov, sen);
		CommandManagers.getInstance().schedule(comm);		
	}

	public static void defineAcoustic(String textCommand) 
	{
		String[] input = textCommand.split("\\s");
		AgentID id = new AgentID(input[3]);
				
		Sensitivity sen = new Sensitivity(Double.parseDouble((input[6])));
				
		CommandSensorDefineAcoustic comm = new CommandSensorDefineAcoustic(CommandManagers.getInstance(), textCommand, id, sen);
		CommandManagers.getInstance().schedule(comm);	
	}

	public static void defineSonarActive(String textCommand) 
	{
		String[] input = textCommand.split("\\s");
				
		AgentID id = new AgentID(input[4]);
		Sensitivity sen = new Sensitivity(Double.parseDouble((input[9])));
		Power p = new Power(Double.parseDouble(input[7]));
				
		CommandSensorDefineSonarActive comm = new CommandSensorDefineSonarActive(CommandManagers.getInstance(), textCommand, id, p, sen);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void defineSonarPassive(String textCommand) 
	{
		String[] input = textCommand.split("\\s");
				
		AgentID id = new AgentID(input[4]);
		Sensitivity sen = new Sensitivity(Double.parseDouble((input[7])));
				
		CommandSensorDefineSonarPassive comm = new CommandSensorDefineSonarPassive(CommandManagers.getInstance(), textCommand, id, sen);
		CommandManagers.getInstance().schedule(comm);
	}

			
	public static void defineDepth(String textCommand) 
	{
		String[] input = textCommand.split("\\s");
				
		AgentID id = new AgentID(input[3]);
		Altitude alt = new Altitude(Integer.parseInt((input[7])));
				
		CommandSensorDefineDepth comm = new CommandSensorDefineDepth(CommandManagers.getInstance(), textCommand, id, alt);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void defineDistance(String textCommand) 
	{
		String[] input = textCommand.split("\\s");
				
		AgentID id = new AgentID(input[3]);
		DistanceNauticalMiles dis = new DistanceNauticalMiles(Double.parseDouble(input[7]));
				
		CommandSensorDefineDistance comm = new CommandSensorDefineDistance(CommandManagers.getInstance(), textCommand, id, dis);
		CommandManagers.getInstance().schedule(comm);	
	}

	public static void defineTime(String textCommand) 
	{
		String[] input = textCommand.split("\\s");
				
		AgentID id = new AgentID(input[3]);
		Time time = new Time(Double.parseDouble(input[7]));
				
		CommandSensorDefineTime comm = new CommandSensorDefineTime(CommandManagers.getInstance(), textCommand, id, time);
		CommandManagers.getInstance().schedule(comm);
	}
			

	//MUNITION COMMANDS
	public static void defineBomb(String textCommand) {
		String[] input = textCommand.split("\\s");
					
		AgentID ID = new AgentID(input[3]);
					
		CommandMunitionDefineBomb comm = new CommandMunitionDefineBomb(CommandManagers.getInstance(), textCommand, ID);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void defineShell(String textCommand) {
		String[] input = textCommand.split("\\s");
					
		AgentID ID = new AgentID(input[3]);
					
		CommandMunitionDefineShell comm = new CommandMunitionDefineShell(CommandManagers.getInstance(), textCommand, ID);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void defineDepthCharge(String textCommand) {
		String[] input = textCommand.split("\\s");
					
		AgentID idMunition = new AgentID(input[3]);
		AgentID idFuze = new AgentID(input[6]);
				
		CommandMunitionDefineDepthCharge comm = new CommandMunitionDefineDepthCharge(CommandManagers.getInstance(), textCommand, idMunition, idFuze);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void defineTorpedo(String textCommand) {
		String[] input = textCommand.split("\\s");
					
		AgentID idMunition = new AgentID(input[3]);				
		AgentID idSensor = new AgentID(input[6]);
		AgentID idFuze = new AgentID(input[8]);
		Time time = new Time(Double.parseDouble(input[11]));
					
		CommandMunitionDefineTorpedo comm = new CommandMunitionDefineTorpedo(CommandManagers.getInstance(), textCommand, idMunition,idSensor, idFuze, time);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void defineMissile(String textCommand) {
		String[] input = textCommand.split("\\s");
					
		AgentID idMunition = new AgentID(input[3]);
		AgentID idSensor = new AgentID(input[6]);
		AgentID idFuze = new AgentID(input[8]);				
		DistanceNauticalMiles distance = new DistanceNauticalMiles(Double.parseDouble(input[11]));
				
		CommandMunitionDefineMissile comm = new CommandMunitionDefineMissile(CommandManagers.getInstance(), textCommand, idMunition,idSensor, idFuze, distance);
		CommandManagers.getInstance().schedule(comm);
	}
				
	public static void loadMunition(String textCommand) {
		String[] input = textCommand.split("\\s");
					
		AgentID idActor = new AgentID(input[1]);
		AgentID idMunition = new AgentID(input[4]);
					
		CommandActorLoadMunition comm = new CommandActorLoadMunition(CommandManagers.getInstance(), textCommand, idActor, idMunition);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void deployMunition(String textCommand) {
		String[] input = textCommand.split("\\s");
					
		AgentID idActor = new AgentID(input[1]);
		AgentID idMunition = new AgentID(input[4]);
					
		CommandActorDeployMunition comm = new CommandActorDeployMunition(CommandManagers.getInstance(), textCommand, idActor, idMunition);
		CommandManagers.getInstance().schedule(comm);
	}

	public static void deployMunitionShell(String textCommand) {
		String[] input = textCommand.split("\\s");
					
		AgentID idActor = new AgentID(input[1]);
		AgentID idMunition = new AgentID(input[4]);
		AttitudeYaw azimuth = new AttitudeYaw(Double.parseDouble(input[7]));
		AttitudePitch elevation = new AttitudePitch(Double.parseDouble(input[9]));
					
		CommandActorDeployMunitionShell comm = new CommandActorDeployMunitionShell(CommandManagers.getInstance(), textCommand, idActor, idMunition, azimuth, elevation);
		CommandManagers.getInstance().schedule(comm);
	}		
	
}
