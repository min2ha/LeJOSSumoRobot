/**
 * 
 */
package lego.nxt.sumobot.behavior;

import lego.nxt.sumobot.SumoBot;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;

/**
 * @author Mindaugas Vidmantas
 *
 * 2019-03-09
 *
 * Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */
public class SonarEnemyDetection implements Behavior {

    private boolean suppressed = false;

    private final SumoBot sumoBot;
    
    //private Ultra sonar;

    //float[] lightSample;

    public SonarEnemyDetection(SumoBot sumoBot) {
        this.sumoBot = sumoBot;
        //lightSample = new float[sumoBot.getLight().getLightValue()];//.sampleSize()];
        //this.sumoBot.getLeft().setSpeed(300);//.forward();
        //this.sumoBot.getRight().setSpeed(300);//.forward();
    }

    @Override
    public boolean takeControl() {
    	//System.out.println("--- ObstacleBehavior  -- takeControl() --  sumoBot.getLight() = " + sumoBot.getLight());
    	//sumoBot.getTouch().fetchSample(touchSample, 0);
    	//sumoBot.getLight();//.getTouch().fetchSample(touchSample, 0);
    	return (sumoBot.getsonarFront().getDistance() > 30);//lightSample[0] == 1;
    }

    @Override
    public void action() {
    	System.out.println("--- SonarEnemyDetection  -- action() --  ");
        suppressed = false;
        RegulatedMotor leftMotor = sumoBot.getLeft();
        RegulatedMotor rightMotor = sumoBot.getRight();

        //leftMotor.rotate(450, true);
        //rightMotor.rotate(-450, true);
        //leftMotor.stop();//.rotate(450, true);
        //rightMotor.stop();//rotate(-450, true);

        //???
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