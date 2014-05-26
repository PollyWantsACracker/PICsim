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
public class CommandDummy extends Command{
    
    public CommandDummy () {
        super (0,0);
    }
    
    @Override
    public void readParameters(int [] aParameters) {

    }
    
    @Override
    public int executeCommand() {
        return 0;
    }
    
}
