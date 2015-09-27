package turbulentgiggle.game.myo;

import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.enums.LockingPolicy;

/*
 * JVM Arguments to help debug.
 -Xcheck:jni
 -XX:+ShowMessageBoxOnError
 -XX:+UseOSErrorReporting
 */
public class HelloMyo {
    public static void main(String[] args) {
        try {
            Hub hub = new Hub("com.example.hello-myo");

            System.out.println("Attempting to find a Myo...");
            Myo myo = hub.waitForMyo(10000);
            hub.setLockingPolicy(LockingPolicy.LOCKING_POLICY_NONE);
            if (myo == null) {
                throw new RuntimeException("Unable to find a Myo!");
            }

            System.out.println("Connected to a Myo armband!");
            DeviceListener dataCollector = new DataCollector();
            hub.addListener(dataCollector);

            while (true) {
                hub.run(1000 / 20);
                System.out.print(dataCollector);
            }
        } catch (Exception e) {
            System.err.println("Error: ");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
