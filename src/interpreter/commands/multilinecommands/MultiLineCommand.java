package interpreter.commands.multilinecommands;

import java.util.ArrayList;

import interpreter.commands.Command;
import interpreter.symboles.SymbolTable;
import interpreter.symboles.SymbolTable.SymbolException;
/**
 * The Abstract Command Class is being inherited by two  
 * Commands that can hold single commands in them:<br>
 * 	1 - WhileCommand<br>
 *  2 - IfCommand<br>
 *  All over commands are single commands which can be held and executed
 *  by the MultiLine Commands.
 * @author Arik Regev
 * @author Amit Koren
 */
public class MultiLineCommand implements Command{
	
	private ArrayList<Command> subCommands; 
	private ControlCommand owner;
	
	public MultiLineCommand() {
		this.subCommands = new ArrayList<Command>();
	}
	public ControlCommand getOwner() {
		return owner;
	}
	public void setOwner(ControlCommand owner) {
		this.owner = owner;
	}
	public void addSubCommand(Command c) {
		subCommands.add(c);
	}
	@Override
	public void doCommand(SymbolTable symTable) throws SymbolException {
		for(Command c : subCommands) 
			c.doCommand(symTable);
		
	}
		

		
}
