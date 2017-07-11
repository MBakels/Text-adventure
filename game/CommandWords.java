package game;

class CommandWords {
    private static final String validCommands[] = {
        "move", "quit", "help", "look", "inventory", "pickup", "drop", "use", "status", "attack"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word.
     * Return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString) {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString)){
                return true;
            }
        }
        return false;
    }

    /**
     * Return all valid commands.
     */
    public String showAll() {
    	String string = "";
        for(int i = 0; i < validCommands.length; i++) {
        	string += validCommands[i] + "  ";
        }
        return string;
    }
}
