package cs350s21project.cli;

//import cs350s21project.datatype.AgentID;
//import cs350s21project.datatype.Altitude;
//import cs350s21project.datatype.AttitudePitch;
//import cs350s21project.datatype.AttitudeYaw;
//import cs350s21project.datatype.CoordinateWorld3D;
//import cs350s21project.datatype.Course;
//import cs350s21project.datatype.DistanceNauticalMiles;
//import cs350s21project.datatype.FieldOfView;
//import cs350s21project.datatype.Groundspeed;
//import cs350s21project.datatype.Latitude;
//import cs350s21project.datatype.Longitude;
//import cs350s21project.datatype.Power;
//import cs350s21project.datatype.Sensitivity;
//import cs350s21project.datatype.Time;

public class CommandInterpreter {
//	public AgentID id;
//	public Altitude altitude;
//	public AttitudeYaw azimuth;
//	public CoordinateWorld3D coordinates;
//	public Course course;
//	public DistanceNauticalMiles distance;
//	public AttitudePitch elevation;
//	public String filename;
//	public FieldOfView fov;
//	public Latitude latitude;
//	public Longitude longitude;
//	public Power power;
//	public Sensitivity sensitivity;
//	public int size;
//	public Groundspeed speed;
//	public Time time;
	
	public void evaluate(String textCommand) throws Exception {
			if (textCommand == null || textCommand.isEmpty())
				throw new RuntimeException();
			
			String[] commands = textCommand.split(";");
			
			for(int i = 0; i < commands.length; i++)
			{
				//testing commit
				//VIEWS COMMANDS
				
//				if(commands[i].contains("create window top view"))
//				{
//					Commands.createWindow(textCommand, id, size);
//				}
//				
//				else if(commands[i].contains("delete window"))
//				{
//					Commands.deleteWindow(textCommand);
//				}
//				
//				
//				//ACTORS COMMANDS
//				
//				else if(commands[i].contains("define ship with munitions")) 
//				{
//					Commands.defineShip(textCommand);
//				}
//				
//				else if(commands[i].contains("create actor course speed")) 
//				{
//					Commands.createActor(textCommand);
//				}
//				
//				else if(commands[i].contains("set course ")) 
//				{
//					Commands.setCourse(textCommand);
//				}
//				
//				else if(commands[i].contains("set speed")) 
//				{
//					Commands.setSpeed(textCommand);
//				}
//				
//				else if(commands[i].contains("set altitude|depth")) 
//				{
//					Commands.setAltitudeDepth(textCommand);
//				}
//				
//				//MUNITIONS COMMANDS
//				
//				
//				else 
				if(commands[i].contains("define munition bomb")) 
				{
					Commands.defineBomb(textCommand);
				}
				
				else if(commands[i].contains("define munition shell")) 
				{
					Commands.defineShell(textCommand);
				}
				
				else if(commands[i].contains("define munition depth_charge fuze")) 
				{
					Commands.defineDepthCharge(textCommand);
				}
				
				else if(commands[i].contains("define munition torpedo sensor")) 
				{
					Commands.defineTorpedo(textCommand);
				}
				
				else if(commands[i].contains("define munition missile sensor arming distance")) 
				{
					Commands.defineMissile(textCommand);
				}
				
				else if(commands[i].contains("set load munition")) 
				{
					Commands.loadMunition(textCommand);
				}
				
				else if(commands[i].contains("set deploy munition")) 
				{
					Commands.deployMunition(textCommand);
				}
				
				else if(commands[i].contains("set deploy munition azimuth elevation")) 
				{
					Commands.deployMunitionShell(textCommand);
				}
				
				
//				//SENSORS/FUZES COMMANDS
//				
//				
//				else if(commands[i].contains("define sensor radar field of view power sensitivity ")) 
//				{
//					Commands.defineRadar(textCommand);
//				}
//				
//				else if(commands[i].contains("define sensor thermal field of view sensitivity")) 
//				{
//					Commands.defineThermal(textCommand);
//				}
//				
//				else if(commands[i].contains("define sensor acoustic sensitivity")) 
//				{
//					Commands.defineAcoustic(textCommand);
//				}
//				
//				else if(commands[i].contains("define sensor sonar active power sensitivity")) 
//				{
//					Commands.defineSonarActive(textCommand);
//				}
//				
//				else if(commands[i].contains("define sonar passive sensitivity")) 
//				{
//					Commands.defineSonarPassive(textCommand);
//				}
//				
//				else if(commands[i].contains("define sensor depth trigger")) 
//				{
//					Commands.defineDepth(textCommand);
//				}
//				
//				else if(commands[i].contains("define sensor distance trigger distance")) 
//				{
//					Commands.defineDistance(textCommand);
//				}
//				
//				else if(commands[i].contains("define sensor time trigger time")) 
//				{
//					Commands.defineTime(textCommand);
//				}
//				
//				
//				//MISC COMMANDS
//				
//				
//				else if(commands[i].contains("@load")) 
//				{
//					Commands.miscLoad(textCommand);
//				}
//				
//				else if(commands[i].contains("@pause")) 
//				{
//					Commands.miscPause(textCommand);
//				}
//				
//				else if(commands[i].contains("@resume")) 
//				{
//					Commands.miscResume(textCommand);
//				}
//				
//				else if(commands[i].contains("@set update")) 
//				{
//					Commands.miscSetUpdate(textCommand);
//				}
//				
//				else if(commands[i].contains("@wait")) 
//				{
//					Commands.miscWait(textCommand);
//				}
//				
//				else if(commands[i].contains("@force state to course speed")) 
//				{
//					Commands.setState(textCommand);
//				}
//				
//				else if(commands[i].contains("@exit")) 
//				{
//					Commands.miscExit(textCommand);
//				}
//				
				
		
			}
	}
}