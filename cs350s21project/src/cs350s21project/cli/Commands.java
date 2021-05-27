package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorDeployMunition;
import cs350s21project.controller.command.actor.CommandActorDeployMunitionShell;
import cs350s21project.controller.command.actor.CommandActorLoadMunition;
import cs350s21project.controller.command.munition.CommandMunitionDefineBomb;
import cs350s21project.controller.command.munition.CommandMunitionDefineDepthCharge;
import cs350s21project.controller.command.munition.CommandMunitionDefineMissile;
import cs350s21project.controller.command.munition.CommandMunitionDefineShell;
import cs350s21project.controller.command.munition.CommandMunitionDefineTorpedo;
//import cs350s21project.controller.command.sensor.CommandSensorDefineSonarActive;
//import cs350s21project.controller.command.view.CommandViewCreateWindowTop;
import cs350s21project.datatype.AgentID;
import cs350s21project.datatype.AttitudePitch;
import cs350s21project.datatype.AttitudeYaw;
import cs350s21project.datatype.DistanceNauticalMiles;
//import cs350s21project.datatype.Latitude;
//import cs350s21project.datatype.Longitude;
//import cs350s21project.datatype.Power;
//import cs350s21project.datatype.Sensitivity;
import cs350s21project.datatype.Time;


public class Commands {

	//testing commit
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