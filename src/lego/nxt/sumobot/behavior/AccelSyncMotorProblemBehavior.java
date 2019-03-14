/**
 * 
 */
package lego.nxt.sumobot.behavior;

/**
 * @author Mindaugas Vidmantas
 *
 * 2019-03-14
 *
 * Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */


/*
 * Stebeti varikliu galia 
 * lyginti su greiciu, jeigu NEJUDA vadinasi istrigo ir
 * REIKIA REVERANSO
 * */

// Acceleration along Y axis. Positive or negative values in mg. (g = acceleration due to gravity = 9.81 m/s^2) 
// Acceleration along Z axis. Positive or negative values in mg. (g = acceleration due to gravity = 9.81 m/s^2) 

import lego.nxt.sumobot.SumoBot;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class AccelSyncMotorProblemBehavior implements Behavior {

    private boolean suppressed = false;

    private final SumoBot sumoBot;

    //float[] lightSample;

    public AccelSyncMotorProblemBehavior(SumoBot sumoBot) {
        this.sumoBot = sumoBot;
        this.sumoBot.getLeft().setSpeed(20);//.forward();
        this.sumoBot.getRight().setSpeed(20);//.forward();
    }

    @Override
    public boolean takeControl() {
    	System.out.println("--- AccelSyncMotorProblemBehavior  -- takeControl() --  sumoBot.getLight() = " + sumoBot.getLight());
    	return (Motor.A.getPower() > 80 
    			//&&
    			|| (sumoBot.getAccelHTSensor().getXAccel() < 1
    					//Acceleration along X axis. Positive or negative values in mg. (g = acceleration due to gravity = 9.81 m/s^2) 
    					|| sumoBot.getAccelHTSensor().getYAccel() < 1 
    					|| sumoBot.getAccelHTSensor().getZAccel() < 1));
    }

    @Override
    public void action() {
    	System.out.println("--- AccelSyncMotorProblemBehavior  -- action() --  sumoBot SYNC MOTORS && ACCEL ");
        suppressed = false;
        RegulatedMotor leftMotor = sumoBot.getLeft();
        RegulatedMotor rightMotor = sumoBot.getRight();

        leftMotor.rotate(-180, true);
        rightMotor.rotate(-720, true);

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