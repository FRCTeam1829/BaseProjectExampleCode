/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carbonauts.frc2014.util;

import com.carbonauts.frc2014.Constants;
import com.carbonauts.frc2014.command.CarbonRampCommand;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Our own implementation of the Talon supports the use of a CarbonRamp so that
 * a set() call can alternately use setRamp() so that the Talon will slowly
 * approach the set value rather than abruptly jump to it.
 * @author Greg Armstrong
 * @author Nick
 */
public class CarbonTalon extends Talon implements ICarbonRampable {
    
    private final CarbonRamp ramp;
    private CarbonRampCommand rampCommand;
    
    public CarbonTalon(int channel) {
        this(channel, Constants.RAMP_DEFAULT_STEPSIZE, Constants.RAMP_DEFAULT_STEPTIME);
    }
    
    public CarbonTalon(int channel, double stepSize, long stepTime) {
        super(channel);
        ramp = new CarbonRamp(0, stepSize, stepTime);
        rampCommand = new CarbonRampCommand(ramp, this);
        Scheduler.getInstance().add(rampCommand);
    }
    
    public CarbonTalon(int slot, int channel) {
        this(slot, channel, Constants.RAMP_DEFAULT_STEPSIZE, Constants.RAMP_DEFAULT_STEPTIME);
    }
    
    public CarbonTalon(int slot, int channel, double stepSize, long stepTime) {
        super(slot, channel);
        ramp = new CarbonRamp(0, stepSize, stepTime);
        rampCommand = new CarbonRampCommand(ramp, this);
        Scheduler.getInstance().add(rampCommand);
    }

    public double getStepSize() {
        return ramp.getStepSize();
    }
    
    public void setStepSize(double stepSize) {
        ramp.setStepSize(stepSize);
    }
    
    public long getStepTime() {
        return ramp.getStepTime();
    }
    
    public void setStepTime(long stepTime) {
        ramp.setStepTime(stepTime);
    }
    
    public void setDefaultStep() {
        ramp.setStepSize(Constants.RAMP_DEFAULT_STEPSIZE);
        ramp.setStepTime(Constants.RAMP_DEFAULT_STEPTIME);
    }
    
    public void setRamp(double setPoint) {
        if(!rampCommand.isRunning()) {
            rampCommand = new CarbonRampCommand(ramp, this);
            Scheduler.getInstance().add(rampCommand);
        }
        ramp.setTarget(setPoint);
    }
    
    public double getTargetSpeed() {
        return ramp.getTarget();
    }
    
    public void stopMotor() {
        ramp.setTarget(0.0);
    }
    
    public void reset() {
        ramp.reset();
    }
    
    public void setRampEnabled(boolean enabled) {
        ramp.setEnabled(enabled);
    }
    
    public boolean isRampEnabled() {
        return ramp.isEnabled();
    }
    
    public void hardSet(double setpoint) {
        super.set(setpoint);
    }
    
    public void hardStopMotor() {
        ramp.setTarget(0.0);
        ramp.setOutput(0.0);
        //rampCommand.setFinished(true);
        super.stopMotor();
    }
}