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
    	//System.out.println("--- ObstacleBehavior  -- action() --  sumoBot.getLight()  = " + SumoBotMT.lightSensor.getLightValue());

		try
		{
			//SumoBotMT.setSpeed(500);
			SumoBotMT.travel(-1);
			SumoBotMT.turn(-100);
			//SumoBotMT.setSpeed(550);
		
			//lightSensor.getLightValue()
			SumoBotMT.LIGHTVALUE = 50;
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
		return (SumoBotMT.LIGHTVALUE > 54 );//|| SumoBotMT.sonar.getDistance() < 10 );
	}	
}


