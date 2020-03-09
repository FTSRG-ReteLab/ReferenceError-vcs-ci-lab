package hu.bme.mit.train;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainTachograph;
import hu.bme.mit.train.interfaces.TrainUser;

import java.util.Date;

public class TrainTachographImpl implements TrainTachograph {
    TrainController controller;
    TrainUser user;
    Table<Date, Integer, Integer> table = HashBasedTable.create();

    public TrainTachographImpl(TrainUser trainUser, TrainController trainController)
    {
        controller = trainController;
        user = trainUser;
    }

    @Override
    public void recordData() {
        table.put(new Date(), 0, user.getJoystickPosition());
        table.put(new Date(), 1, controller.getReferenceSpeed());
    }

    @Override
    public Table<Date, Integer, Integer> getData() {
        return table;
    }
}
