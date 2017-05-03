package ihm;

public class NomNonValideException extends Exception {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Construct the Exception
	 */
		public NomNonValideException(){}
	
		/** 
		* Crée une nouvelle instance de NomNonValideException 
		* @param message Le message détaillant exception 
		*/  
		public NomNonValideException(String message) {  
			super(message); 
		}  
		/** 
		* Crée une nouvelle instance de NomNonValideException 
		* @param cause L'exception à l'origine de cette exception 
		*/  
		public NomNonValideException(Throwable cause) {  
			super(cause); 
		}  
		/** 
		* Crée une nouvelle instance de NomNonValideException 
		* @param message Le message détaillant exception 
		* @param cause L'exception à l'origine de cette exception 
		*/  
		public NomNonValideException(String message, Throwable cause) {  
			super(message, cause); 
		} 
		
}
