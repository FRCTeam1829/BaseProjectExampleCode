/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbonauts.frc2014.subsystems;

import com.carbonauts.frc2014.Constants;
import com.carbonauts.frc2014.util.CarbonDigitalInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Nick
 */
public class Pickup extends Subsystem {

    private Talon mPivotMotor;
    private Talon mRollerMotor;
    
    private DigitalInput limitForward = new CarbonDigitalInput(Constants.LIMIT_PICKUP_FORWARD, Constants.DIGITAL_INPUTS_INVERTED);
    private DigitalInput limitResting = new CarbonDigitalInput(Constants.LIMIT_PICKUP_RESTING, Constants.DIGITAL_INPUTS_INVERTED);
    private DigitalInput limitReverse = new CarbonDigitalInput(Constants.LIMIT_PICKUP_REVERSE, Constants.DIGITAL_INPUTS_INVERTED);
    
    private int lastPosition;
    private boolean rollerDirection;
    
    public boolean isAtLimit() {
        return limitForward.get() || limitResting.get() || limitReverse.get();
    }
    
    /**
     * Determines whether the system is currently at the position fed as a
     * parameter
     * @param position The position we're checking whether we're at or not
     * @return True if we're at position, false otherwise
     */
    public boolean isAtPosition(int position) {
        switch(position) {
            case Constants.PICKUP_POSITION_FORWARD:
                return limitForward.get();
            case Constants.PICKUP_POSITION_RESTING:
                return limitResting.get();
            case Constants.PICKUP_POSITION_REVERSE:
                return limitReverse.get();
            default:
                return false;
        }
    }
    
    protected void initDefaultCommand() {
        
    }
}