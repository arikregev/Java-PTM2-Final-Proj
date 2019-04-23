package interpreter.symbols;

import interpreter.commands.ExecutionException;

public class Exceptions {

	@SuppressWarnings("serial")
	public static class SymbolException extends ExecutionException {
		public final String symbolName;
	
		public SymbolException(String symbolName) {
			super();
			this.symbolName = symbolName;
		}
	}
	@SuppressWarnings("serial")
	public static class SymbolUnInitializedException extends SymbolException {
	
		public SymbolUnInitializedException(String symbolName) {
			super(symbolName);
		}
	}

	@SuppressWarnings("serial")
	public static class SymbolAlreadyExistException extends SymbolException {
	
		public SymbolAlreadyExistException(String symbolName) {
			super(symbolName);
		}
	}

	@SuppressWarnings("serial")
	public static class SymbolNotExistException extends SymbolException {
	
		public SymbolNotExistException(String symbolName) {
			super(symbolName);
		}
	}
	
	@SuppressWarnings("serial")
	public static class BindPathNotExistException extends SymbolException {
		String bindPath;
		public BindPathNotExistException(String symbolName, String bindPath) {
			super(symbolName);
			this.bindPath = bindPath;
		}
	}



}
