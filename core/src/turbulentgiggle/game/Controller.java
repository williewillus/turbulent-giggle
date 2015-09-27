package turbulentgiggle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.thalmic.myo.*;
import com.thalmic.myo.enums.PoseType;
import com.thalmic.myo.enums.VibrationType;

import java.util.LinkedList;

/**
 * Created by Quang on 9/26/2015.
 */
public class Controller extends AbstractDeviceListener {

    private Pose currentPose;
    private double rollW, pitchW, yawW;
    private int SCALE = 18;
    private Hub hub;
    private double initRoll, initPitch, initYaw;
    private double avgRoll, avgPitch, avgYaw;
    private boolean set = false;
    private LinkedList<Double> roll, pitch, yaw;

    public Controller(Hub hub) {
        this.hub = hub;
        currentPose = new Pose(PoseType.REST);
        roll = new LinkedList<Double>();
        pitch = new LinkedList<Double>();
        yaw = new LinkedList<Double>();
    }

    @Override
    public void onPose(Myo myo, long timestamp, Pose pose) {
        currentPose = pose;
        if (currentPose.getType() == PoseType.FIST) {
            myo.vibrate(VibrationType.VIBRATION_MEDIUM);
        }
    }

    public void poll() {
        hub.run(20);
    }

    public void calibrate() {

    }

    private static final int BUFFER_SIZE = 40;
    
    @Override
    public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
        Quaternion normalized = rotation.normalized();

        double roll = Math.atan2(2.0f * (normalized.getW() * normalized.getX() + normalized.getY() * normalized.getZ()), 1.0f - 2.0f * (normalized.getX() * normalized.getX() + normalized.getY() * normalized.getY()));
        double pitch = Math.asin(2.0f * (normalized.getW() * normalized.getY() - normalized.getZ() * normalized.getX()));
        double yaw = Math.atan2(2.0f * (normalized.getW() * normalized.getZ() + normalized.getX() * normalized.getY()), 1.0f - 2.0f * (normalized.getY() * normalized.getY() + normalized.getZ() * normalized.getZ()));
        rollW = ((roll + Math.PI) / (Math.PI * 2.0) * SCALE);
        pitchW = ((pitch + Math.PI / 2.0) / Math.PI * SCALE);
        yawW = ((yaw + Math.PI) / (Math.PI * 2.0) * SCALE);
        this.pitch.add(pitchW);
        this.roll.add(rollW);
        this.yaw.add(yawW);
        if(!set) {
            initRoll = rollW;
            initYaw = yawW;
            initPitch = pitchW;
            set = true;
        }
        if(this.pitch.size() > BUFFER_SIZE) {
            avgPitch = (avgPitch * BUFFER_SIZE - this.pitch.removeFirst() + pitchW)/BUFFER_SIZE;
            avgRoll = (avgRoll * BUFFER_SIZE - this.roll.removeFirst() + rollW)/BUFFER_SIZE;
            avgYaw = (avgYaw * BUFFER_SIZE - this.yaw.removeFirst() + yawW)/BUFFER_SIZE;
        } else {
            avgPitch = (avgPitch * (this.pitch.size() - 1) + pitchW)/(this.pitch.size());
            avgRoll = (avgRoll * (this.roll.size() - 1) + rollW)/(this.roll.size());
            avgYaw = (avgYaw * (this.yaw.size() - 1) + yawW)/(this.yaw.size());
        }
//        if(!set) {
//            initPitch = pitchW;
//            initRoll = rollW;
//            initYaw = yawW;
//            set = true;
//        }

    }

    private static double SENSITIVITY = 1.5;

    public boolean rotateCounterClockwise() {
        return rollW - initRoll < -SCALE/2 ? rollW - initRoll + SCALE > SENSITIVITY : rollW - initRoll < SCALE/2 && rollW - initRoll > SENSITIVITY;
    }

    public boolean rotateClockwise() {
        return rollW - initRoll > SCALE/2 ? rollW - initRoll - SCALE < -SENSITIVITY : rollW - initRoll > -SCALE/2 && rollW - initRoll < -SENSITIVITY;
    }

    public boolean left() {
//        System.out.println(yawW + " " + initYaw);
        return yawW - initYaw < -SCALE/2 ? yawW - initYaw + SCALE > SENSITIVITY : yawW - initYaw < SCALE/2 && yawW - initYaw > SENSITIVITY;
    }

    public boolean right() {
        return yawW - initYaw > SCALE/2 ? yawW - initYaw - SCALE < -SENSITIVITY : yawW - initYaw > -SCALE/2 && yawW - initYaw < -SENSITIVITY;
    }

    public boolean up() {
        return Gdx.input.isButtonPressed(Input.Keys.UP);
    }

    public boolean down() {
        return Gdx.input.isButtonPressed(Input.Keys.DOWN);
    }

    public boolean action() {
        return pitchW - initPitch < -SENSITIVITY*2;//currentPose.getType() == PoseType.WAVE_IN;//currentPose.getType() == PoseType.FIST || currentPose.getType() == PoseType.FINGERS_SPREAD;
    }

    public boolean action2() {
        return currentPose.getType() == PoseType.FIST;
    }

    public boolean action3() {
        return currentPose.getType() == PoseType.FINGERS_SPREAD;
    }

}
