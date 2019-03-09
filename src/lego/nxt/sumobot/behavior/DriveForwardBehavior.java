package lego.nxt.sumobot.behavior;

import lego.nxt.sumobot.SumoBot;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class DriveForwardBehavior implements Behavior {
	private boolean suppressed = false;
	


    private final SumoBot sumoBot;

    //float[] touchSample;

    public DriveForwardBehavior(SumoBot sumoBot) {
        this.sumoBot = sumoBot;
        
        //sets the acceleration rate of this motor in degrees/sec/sec
        //The default value is 6000;
        
        //public void setSpeed(int speed)
        //Sets desired motor speed , in degrees per second; 
        //The maximum reliably sustainable velocity is 100 x battery voltage under moderate load, 
        //such as a direct drive robot on the level. 
        
        sumoBot.setLeftSpeed(100);//.left.setPower(30);//.forward();
        sumoBot.setRightSpeed(100);//Motor.C.setPower(30);//.forward();
        //touchSample = new float[sumoBot.getTouch().sampleSize()];
    }

	public boolean takeControl() {
		return (sumoBot.getsonarFront().getDistance() < 30);//lightSample[0] == 1;
		//return true;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		Motor.A.forward();
		Motor.C.forward();
		while (!suppressed)
			Thread.yield();
		Motor.A.stop(); // clean up
		Motor.C.stop();
	}
}