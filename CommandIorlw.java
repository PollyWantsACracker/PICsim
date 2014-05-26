/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logik.Command;

import Logik.DataStorage;
import Logik.WReg;

/**
 *
 * @author Christopher Heß
 */
public class CommandIorlw extends Command{
    private WReg wReg;
    private DataStorage dataStorage;
    
    public CommandIorlw (int aLineNumber, WReg aWReg, DataStorage aStack) {
        super (aLineNumber,1);
        this.wReg = aWReg;
        this.dataStorage = aStack;
    }
    
    @Override
    public void readParameters(int [] aParameters) {
        parameters [0] = aParameters [0]; 
    }
    
    @Override
    public int executeCommand() {
        wReg.setValue(wReg.getValue() | parameters[0]);
        return -1;
    }
}
