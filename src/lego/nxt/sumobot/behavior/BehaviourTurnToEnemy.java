/**
 * 
 */
package lego.nxt.sumobot.behavior;

import lego.nxt.sumobot.SumoBotMT;
import lejos.robotics.subsumption.Behavior;

/**
 * @author Mindaugas Vidmantas
 *
 * 2019-03-18
 *
 * Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */
public class BehaviourTurnToEnemy implements Behavior{
	
	// state variables for this behavior
	boolean stop = false;
	
	// what to do
	public void action()
	{	
		stop = false;
		try
		{
			while (!stop)
			{
				System.out.println("--- BehaviourTurnToEnemy  -- action() --  SumoBotMT.LIGHTVALUE  = " + SumoBotMT.LIGHTVALUE + ", SumoBotMT.lightSensor.getLightValue() = " + SumoBotMT.lightSensor.getLightValue());
				
				//IF (SONARFRONTVALUE > SONARRIGHTVALUE ) turn 90 degrees & GO
				SumoBotMT.turnToEnemy();
				
				SumoBotMT.SONARFRONTVALUE = 70;
				SumoBotMT.SONARRIGHTVALUE = 70;
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
		SumoBotMT.stop();
	}
	// when to start doing it
	public boolean takeControl()
	{
		//konfliktuos jeigu vaziuos tiesiai i priesa
		return (SumoBotMT.SONARFRONTVALUE < 70 );
	}	
}
