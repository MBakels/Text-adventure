package game;

import java.util.StringTokenizer;

class Parser {

    private CommandWords commands;  // holds all valid command words

    public Parser() {
        commands = new CommandWords();
    }

    public Command getCommand(String string) {
    	String inputLine = string;
        String word1;
        String word2;
        String word3;

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if(tokenizer.hasMoreTokens()){
            word1 = tokenizer.nextToken();
        }else{
            word1 = null;
        }
        
        if(tokenizer.hasMoreTokens()){
            word2 = tokenizer.nextToken();
        }else{
            word2 = null;
        }
        
        if(tokenizer.hasMoreTokens()){
        	word3 = tokenizer.nextToken();
        }else{
            word3 = null;
        }

        if(commands.isCommand(word1)){
            return new Command(word1, word2, word3);
        }else{
            return new Command(null, word2, word3);
        }
    }

    /**
     * Return list of valid command words.
     */
    public String showCommands() {
        return commands.showAll();
    }
}
