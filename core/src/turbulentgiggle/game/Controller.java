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
    private boolean set = false;
    private int curNum = 0;
    private Myo myo;

    public Controller(Hub hub) {
        this.hub = hub;
        currentPose = new Pose(PoseType.REST);
    }

    public void recalibrate() {
        initRoll = rollW;
        initPitch = pitchW;
        initYaw = yawW;
        myo.vibrate(VibrationType.VIBRATION_MEDIUM);

    }

    @Override
    public void onPose(Myo myo, long timestamp, Pose pose) {
        currentPose = pose;
    }

    public void poll() {
        hub.run(20);
    }

    private static final int BUFFER_SIZE = 500;
    
    @Override
    public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
        if(this.myo == null) {
            this.myo = myo;
        }
        Quaternion normalized = rotation.normalized();

        double roll = Math.atan2(2.0f * (normalized.getW() * normalized.getX() + normalized.getY() * normalized.getZ()), 1.0f - 2.0f * (normalized.getX() * normalized.getX() + normalized.getY() * normalized.getY()));
        double pitch = Math.asin(2.0f * (normalized.getW() * normalized.getY() - normalized.getZ() * normalized.getX()));
        double yaw = Math.atan2(2.0f * (normalized.getW() * normalized.getZ() + normalized.getX() * normalized.getY()), 1.0f - 2.0f * (normalized.getY() * normalized.getY() + normalized.getZ() * normalized.getZ()));
        rollW = ((roll + Math.PI) / (Math.PI * 2.0) * SCALE);
        pitchW = ((pitch + Math.PI / 2.0) / Math.PI * SCALE);
        yawW = ((yaw + Math.PI) / (Math.PI * 2.0) * SCALE);
        if(!set) {
            initRoll = rollW;
            initYaw = yawW;
            initPitch = pitchW;
            set = true;
        }
//        if(!set) {
//            initPitch = pitchW;
//            initRoll = rollW;
//            initYaw = yawW;
//            set = true;
//        }

    }

    private static double SENSITIVITY = 1;

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
        return pitchW - initPitch > SENSITIVITY*2;
    }

    public boolean down() {
        return pitchW - initPitch < -SENSITIVITY*2;
    }

    public boolean action() {
        return pitchW - initPitch < -SENSITIVITY*4;//currentPose.getType() == PoseType.WAVE_IN;//currentPose.getType() == PoseType.FIST || currentPose.getType() == PoseType.FINGERS_SPREAD;
    }

    public boolean action2() {
        return currentPose.getType() == PoseType.FIST || currentPose.getType() == PoseType.DOUBLE_TAP;
    }

    public boolean action3() {
        return currentPose.getType() == PoseType.WAVE_IN;
    }

}
