package interpreter.commands.multilinecommands;

import java.util.ArrayList;

import interpreter.commands.Command;
/**
 * The Abstract Command Class is being inherited by two  
 * Commands that can hold single commands in them:<br>
 * 	1 - LoopCommand<br>
 *  2 - IfCommand<br>
 *  All over commands are single commands which can be held and executed
 *  by the MultiLine Commands.
 * @author Arik Regev
 * @author Amit Koren
 */
public abstract class MultiLineCommand implements Command{

		protected ArrayList<Command> subCommands;
		private MultiLineCommand currentInnerCommand; 
		
		public MultiLineCommand() {
			this.subCommands = new ArrayList<Command>();
			this.currentInnerCommand = null;
		}
		public void addSubCommand(Command c) {
			if (currentInnerCommand == null)
				subCommands.add(c);
			else {
				currentInnerCommand.addSubCommand(c);
			}
		}
		
		public void beginMultiLine(MultiLineCommand c) {
			if(currentInnerCommand == null) {
				subCommands.add(c);
				currentInnerCommand = c;
			} else {
				currentInnerCommand.beginMultiLine(c);
			}
		}
		
		public boolean endMultiLine() {
			if(currentInnerCommand == null) //check if the end multiline is for this object
				return true;
			if(currentInnerCommand.endMultiLine())//pass the end multiline to inner recursively.
				currentInnerCommand = null; // InnerMultiLine was closed, so put null
			return false;
		}
		
}
