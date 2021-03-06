package lego.nxt.sumobot.behavior;


import lego.nxt.sumobot.SumoBot;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class ObstacleBehavior implements Behavior {

    private boolean suppressed = false;

    private final SumoBot sumoBot;

    //float[] lightSample;

    public ObstacleBehavior(SumoBot sumoBot) {
        this.sumoBot = sumoBot;
        //lightSample = new float[sumoBot.getLight().getLightValue()];//.sampleSize()];
        this.sumoBot.getLeft().setSpeed(20);//.forward();
        this.sumoBot.getRight().setSpeed(20);//.forward();
    }

    @Override
    public boolean takeControl() {
    	//System.out.println("--- ObstacleBehavior  -- takeControl() --  sumoBot.getLight() = " + sumoBot.getLight());
    	//sumoBot.getTouch().fetchSample(touchSample, 0);
    	//sumoBot.getLight();//.getTouch().fetchSample(touchSample, 0);
    	return (sumoBot.getLight().getLightValue() > 55);//lightSample[0] == 1;
    }

    @Override
    public void action() {
    	System.out.println("--- ObstacleBehavior  -- action() --  sumoBot.getLight() > 55 ");
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