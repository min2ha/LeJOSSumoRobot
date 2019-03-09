/**
 * 
 */
package lego.nxt.sumobot.behavior;

/**
 * @author Mindaugas Vidmantas
 *
 * 2019-03-08
 *
 * Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */


import lego.nxt.sumobot.SumoBot;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class EnemyDetectionBehavior implements Behavior {

    private boolean suppressed = false;

    private final SumoBot sumoBot;
    
    //private Ultra sonar;

    //float[] lightSample;

    public EnemyDetectionBehavior(SumoBot sumoBot) {
        this.sumoBot = sumoBot;
        //lightSample = new float[sumoBot.getLight().getLightValue()];//.sampleSize()];
        
        //public void setSpeed(int speed)
        //Sets desired motor speed , in degrees per second; 
        //The maximum reliably sustainable velocity is 100 x battery voltage under moderate load, 
        //such as a direct drive robot on the level. 

        this.sumoBot.getLeft().setSpeed(900);//.forward();
        this.sumoBot.getRight().setSpeed(900);//.forward();
    }

    @Override
    public boolean takeControl() {
    	//System.out.println("--- ObstacleBehavior  -- takeControl() --  sumoBot.getLight() = " + sumoBot.getLight());
    	//sumoBot.getTouch().fetchSample(touchSample, 0);
    	//sumoBot.getLight();//.getTouch().fetchSample(touchSample, 0);
    	return  (sumoBot.getsonarFront().getDistance() > 30);//lightSample[0] == 1;
    }

    @Override
    public void action() {
    	System.out.println("--- EnemyDetectionBehavior  -- action() --  " );
        suppressed = false;
        RegulatedMotor leftMotor = sumoBot.getLeft();
        RegulatedMotor rightMotor = sumoBot.getRight();

        leftMotor.rotate(450, true);
        rightMotor.rotate(-450, true);
        //leftMotor.backward();//.rotate(450, true);
        //rightMotor.forward();//rotate(-450, true);

        while( (leftMotor.isMoving() || rightMotor.isMoving()) && !suppressed )
            Thread.yield();

        leftMotor.stop(); // clean up
        rightMotor.stop();
    }

    @Override
    public void suppress() {
        suppressed = true;
    }
}
