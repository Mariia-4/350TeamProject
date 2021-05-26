package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.sensor.CommandSensorDefineAcoustic;
import cs350s21project.controller.command.sensor.CommandSensorDefineDepth;
import cs350s21project.controller.command.sensor.CommandSensorDefineDistance;
import cs350s21project.controller.command.sensor.CommandSensorDefineRadar;
import cs350s21project.controller.command.sensor.CommandSensorDefineSonarActive;
import cs350s21project.controller.command.sensor.CommandSensorDefineSonarPassive;
import cs350s21project.controller.command.sensor.CommandSensorDefineThermal;
import cs350s21project.controller.command.sensor.CommandSensorDefineTime;
import cs350s21project.controller.command.view.CommandViewCreateWindowTop;
import cs350s21project.datatype.AgentID;
import cs350s21project.datatype.Altitude;
import cs350s21project.datatype.AngleNavigational;
import cs350s21project.datatype.DistanceNauticalMiles;
import cs350s21project.datatype.FieldOfView;
import cs350s21project.datatype.Latitude;
import cs350s21project.datatype.Longitude;
import cs350s21project.datatype.Power;
import cs350s21project.datatype.Sensitivity;
import cs350s21project.datatype.Time;


public class Commands {

	//Took out all methods not applicable to my share of the work.\
	//12:00 update to Git
	

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
	

}
