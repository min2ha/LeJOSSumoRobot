/**
 * 
 */
package lego.nxt.sumobot.behavior;

import lego.nxt.sumobot.SumoBotMT;
import lejos.robotics.subsumption.Behavior;

/**
 * @author Mindaugas Vidmantas
 *
 * 2019-03-10
 *
 * Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */
public class BehaviorAvoid implements Behavior
{
	// what to do
	public void action()
	{	
		try
		{
			SumoBotMT.travel(-20);
			SumoBotMT.turn(-120);
		}
		catch(Exception ex)
		{
		}
	}

	// how to stop doing it
	public void suppress()
	{	
		SumoBotMT.stop();
	}

	// when to start doing it
	public boolean takeControl()
	{
		return (SumoBotMT.lightSensor.getLightValue() < 55 || SumoBotMT.sonar.getDistance() < 20 );
	}	
}


