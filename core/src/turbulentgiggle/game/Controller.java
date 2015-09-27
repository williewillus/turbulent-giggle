package turbulentgiggle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.thalmic.myo.*;
import com.thalmic.myo.enums.PoseType;
import com.thalmic.myo.enums.VibrationType;

/**
 * Created by Quang on 9/26/2015.
 */
public class Controller extends AbstractDeviceListener {

    private Pose currentPose;
    private double rollW, pitchW, yawW;
    private int SCALE = 18;
    private double prevRoll, prevPitch, prevYaw;
    private Hub hub;

    public Controller(Hub hub) {
        this.hub = hub;
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

    @Override
    public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
        Quaternion normalized = rotation.normalized();

        double roll = Math.atan2(2.0f * (normalized.getW() * normalized.getX() + normalized.getY() * normalized.getZ()), 1.0f - 2.0f * (normalized.getX() * normalized.getX() + normalized.getY() * normalized.getY()));
        double pitch = Math.asin(2.0f * (normalized.getW() * normalized.getY() - normalized.getZ() * normalized.getX()));
        double yaw = Math.atan2(2.0f * (normalized.getW() * normalized.getZ() + normalized.getX() * normalized.getY()), 1.0f - 2.0f * (normalized.getY() * normalized.getY() + normalized.getZ() * normalized.getZ()));
        prevPitch = pitchW;
        prevRoll = rollW;
        prevYaw = yawW;
        rollW = ((roll + Math.PI) / (Math.PI * 2.0) * SCALE);
        pitchW = ((pitch + Math.PI / 2.0) / Math.PI * SCALE);
        yawW = ((yaw + Math.PI) / (Math.PI * 2.0) * SCALE);
    }

    public boolean left() {
        return Gdx.input.isButtonPressed(Input.Keys.LEFT) || rollW - prevRoll > 2;
    }

    public boolean right() {
        return Gdx.input.isButtonPressed(Input.Keys.RIGHT) || rollW - prevRoll < -2;
    }

    public boolean up() {
        return Gdx.input.isButtonPressed(Input.Keys.UP);
    }

    public boolean down() {
        return Gdx.input.isButtonPressed(Input.Keys.DOWN);
    }

    public boolean action() {
        return Gdx.input.isButtonPressed(Input.Keys.Z);
    }

    public boolean action2() {
        return Gdx.input.isButtonPressed(Input.Keys.X);
    }

}
