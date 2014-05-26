/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logik.Command;

/**
 *
 * @author Christopher Heß
 */
public class CommandReturn extends Command{
    
    public CommandReturn (int aLineNumber) {
        super (aLineNumber,1);
    }
    
    @Override
    public void readParameters(int [] aParameters) {
    }
    
    @Override
    public int executeCommand() {
        return -3;
    }
}
