/**
 * 
 */
package lego.nxt.sumobot.behavior;

import lego.nxt.sumobot.SumoBotMovingSonar;
import lejos.robotics.subsumption.Behavior;

/**
 * @author Mindaugas Vidmantas
 *
 * 2019-03-10
 *
 * Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */
public class MoveSonarBehavior implements Behavior{
	
	// state variables for this behavior
	boolean stop = false;
	
	// what to do
	public void action()
	{	
		stop = false;
		SumoBotMovingSonar.sonarMotor.resetTachoCount();
		try
		{
			while (!stop)
			{
				SumoBotMovingSonar.travel(8);
				//SumoBotMovingSonar.turnAccordingToSonarValue(8);
				//SumoBotMT.turn(90);
			}
		}
		catch(Exception ex)
		{
		}
	}

	// how to stop doing it
	public void suppress()
	{	
		stop = true;
		SumoBotMovingSonar.stop();
	}
	// when to start doing it
	public boolean takeControl()
	{
		return true;
	}	
}
