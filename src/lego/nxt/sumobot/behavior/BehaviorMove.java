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
public class BehaviorMove implements Behavior{
	
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
					System.out.println("--- BehaviorMove  -- action() --  SumoBotMT.LIGHTVALUE  = " + SumoBotMT.LIGHTVALUE + ", SumoBotMT.lightSensor.getLightValue() = " + SumoBotMT.lightSensor.getLightValue());

					//SumoBotMT.observeLightSensorValue();
					SumoBotMT.travel();
					//SumoBotMT.travel(1040);
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
			SumoBotMT.stop();
		}
		// when to start doing it
		public boolean takeControl()
		{
			//jeigu atstumas mazeja?
			return true;
		}	
	}
