package hu.bme.mit.train.interfaces;

import com.google.common.collect.Table;

import java.util.Date;

public interface TrainTachograph {
    void recordData();

    Table<Date, Integer, Integer> getData();
}
