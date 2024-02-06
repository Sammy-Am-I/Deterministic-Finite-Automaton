package summer;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/* class DFA*/
public class Dfa{ 
	//transition function
	public static char performTransition(String transitionRules[],char state,char alpha){ /* loops for all the rules*/
		for(int i=0;i<transitionRules.length;i++){
			if(transitionRules[i].charAt(1)==state && transitionRules[i].charAt(3)==alpha)/* if a valid match found */
				return transitionRules[i].charAt(7);// returns next state
		}
		return state;
	}
	public static void main(String args[]){
		//Scanner objects and Strings
		Scanner input = new Scanner(System.in);
		String ans = ""; String file = "";
		int len=args.length;

		//User input 
		System.out.println("Please enter file: ");
		file = input.next();
		
		//try and catch block
		try{
			Scanner data =new Scanner(System.in); // Scanner object for reading from console
			BufferedReader Dfafile =new BufferedReader(new FileReader(file));// BufferedReader applied on FileReader object
			String alphabet=Dfafile.readLine(); // reads alphabet from file
			String states=Dfafile.readLine();// reads states from file
			String startstate=Dfafile.readLine();// reads start state from file
			String acceptstates=Dfafile.readLine(); // reads accept states from file

			//Displays alphabet, states,start state and accept states
			System.out.println("Alphabet: "+alphabet);
			System.out.println("States: "+states);
			System.out.println("Start state: "+startstate);
			System.out.println("Accept States: "+acceptstates);
			String rulesString="",rule;

			/*reads rules from file and concatenates them with a # symbol*/
			while((rule=Dfafile.readLine())!=null) {
				rulesString=rulesString+rule+"#";
			}
			String rules[]=rulesString.split("#");/* splits the rules at # symbol to form an array of rules*/
			System.out.println("Transition rules are:");// displays rules
			for(int i=0;i<rules.length;i++) {
				System.out.println(rules[i]);
			}
			while(!ans.equals("N")) {
				System.out.println("Enter the string for testing:");
				String string=data.next();// reads a string from console for testing
				char state=startstate.charAt(0);
				/* removes {,} from accept states to form only string of states without {,}*/
				acceptstates=acceptstates.replace("{","");
				acceptstates=acceptstates.replace("}","");
				acceptstates=acceptstates.replace(",","");
				/*loops till input string not finished*/
				for(int i=0;i<string.length();i++){
					char alpha=string.charAt(i);// reads a char from input string at position i
					state=performTransition(rules,state,alpha);// calls to performTransition() to make state change according to rule
				}
				if(acceptstates.contains(state+"")) { // if state is in accept states list
					System.out.println("String accepted"); // string is accepted
					System.out.println("Would you like to continue?(Y/N)"); 
					ans = input.next();
				}else {
					System.out.println("String not accepted"); // otherwise string not accepted
					System.out.println("Would you like to continue?(Y/N)"); 
					ans = input.next();
				}
			}
			Dfafile.close();// closing of BufferedReader
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

