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
public class BehaviourContraAttack implements Behavior
{
	// what to do
	public void action()
	{	
    	//System.out.println("--- ObstacleBehavior  -- action() --  sumoBot.getLight()  = " + SumoBotMT.lightSensor.getLightValue());

		try
		{
			
			SumoBotMT.travel(-1);
			SumoBotMT.turn(-20);
			
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
		//jeigu kryptis == pirmyn, bet accel rodo ne i ta puse, vadinasi atejo laikas suktis
		//arba suktis kol suveiks 
		// 1. sonar 
		// 2. white line
		return true;//|| SumoBotMT.sonar.getDistance() < 10 );
	}	
}


