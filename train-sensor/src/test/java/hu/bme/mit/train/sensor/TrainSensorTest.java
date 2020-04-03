package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
    private TrainController controller = mock(TrainController.class);
    private TrainUser user = mock(TrainUser.class);
    private TrainSensor sensor = new TrainSensorImpl(controller, user);

    @Before
    public void before() {
        when(controller.getReferenceSpeed()).thenReturn(5);
    }

    // Test if setting speed to negative causes an alarm
    @Test
    public void TestNegativeSpeed() {
        clearInvocations(user);
        sensor.overrideSpeedLimit(-1);
        verify(user).setAlarmState(true);
    }

    // Test if setting speed to erroneously high value causes an alarm
    @Test
    public void TestTooHighSpeed() {
        clearInvocations(user);
        sensor.overrideSpeedLimit(9999999);
        verify(user).setAlarmState(true);
    }

    // Test if setting speed to erroneously low value causes an alarm
    @Test
    public void TestTooLowSpeed() {
        clearInvocations(user);
        sensor.overrideSpeedLimit(1);
        verify(user).setAlarmState(true);
    }

    // Test if setting speed to reference speed causes no alarm
    @Test
    public void TestNoProblems() {
        clearInvocations(user);
        sensor.overrideSpeedLimit(5);
        verifyZeroInteractions(user);
    }
}
