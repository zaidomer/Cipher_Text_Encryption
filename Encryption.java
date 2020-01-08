/**
 * Encryption.java
 * A program used to encrypt messages, count 4 letter words, and decrypt messages
 * Zaid Omer
 * November 7, 2017
 */

// Import Scanner and Java IO
import java.util.Scanner;
import java.io.*;

public class Encryption {
    
    public static void main(String[] args) throws Exception {
        
        // Declare varibales
        String cipherAlphabet = "";
        String messageToEncrypt = "";
        int fourLetterWord;
        char userChoice;
        String messageToDecrypt;
        String encryptedMessage;
        String decryptedMessage;
        File inputFile = new File("infile.txt");
        File outputFile = new File("outfile.txt");
        
        // Create new Scanners (system.in, readFile), and PrintWriter for reading text file
        Scanner input = new Scanner(System.in);
        Scanner readFile = new Scanner(inputFile);
        PrintWriter printOut = new PrintWriter(outputFile);
        
        // Main Menu Loop
        do {
            
            System.out.println("Welcome to the Encryption And Count 4 Letter Words Program!");
            System.out.println("In infile.txt, write you cipher alphabet, followed by the messafe to encrypt, decrypt, or count 4 letter words.");
            System.out.println("Your output result will be saved in oufile.txt");
            System.out.println("Press 'E' for encryption");
            System.out.println("Press 'D' for decryption");
            System.out.println("Press 'C' to count 4 letter words");
            System.out.println("Press 'Q' to quit the program");
            userChoice = Character.toUpperCase(input.nextLine().charAt(0));
            
            // Loop through contents of text file to find entire message and cipher alphabet
            while (readFile.hasNext()) {
                
                // Only used \n for first line, or else all of output would be one line too low when printing variables to text file
                if (messageToEncrypt == "") {
                    cipherAlphabet = readFile.next();
                    messageToEncrypt += readFile.nextLine() + ("\n");
                } // end of if condition
                
                else {
                    
                    // "\r" is used to add the carrige return (new line) in the text file, and in printing out
                    messageToEncrypt += readFile.nextLine() + ("\r\n");
                    
                } // end of if statement
            } // end of while loop for file contents
            
            // Convert mesage and cipher alphabet to capital letters, and also declare messageToDecrypt variable
            // messageToDecrypt has same value as messageToEcnrypt as they are both retrieved in same way.
            messageToEncrypt = messageToEncrypt.toUpperCase();
            messageToDecrypt = messageToEncrypt;
            cipherAlphabet = cipherAlphabet.toUpperCase();
            
            // User choice results
            if (userChoice == 'E') {
                
                System.out.println();
                System.out.println("Cipher Alphabet:");
                System.out.println();
                System.out.println(cipherAlphabet);
                System.out.println();
                System.out.println("Original Message (to encrypt): ");
                System.out.println(messageToEncrypt);
                encryptedMessage = encrypt(cipherAlphabet, messageToEncrypt);
                System.out.println("Encrypted message: ");
                System.out.println(encryptedMessage);
                System.out.println("Saving cipher alphabet and encrypted message to outfile.txt...");
                
                // Print to text file (outfile.txt)
                printOut.println(cipherAlphabet);
                printOut.println(encryptedMessage);
                System.out.println("Save complate! You will see the result when you exit this program!");
                System.out.println();
            } // end of if condition
            
            else if (userChoice == 'C') {
                fourLetterWord = count4LetterWords(messageToEncrypt);
                System.out.println("There are " + fourLetterWord + " four letter words in the message: \n" + messageToEncrypt);
                System.out.println();
            } // end of if condition
            
            else if (userChoice == 'D') {
                System.out.println();
                System.out.println("Cipher Alphabet: ");
                System.out.println();
                System.out.println(cipherAlphabet);
                System.out.println();
                System.out.println("Original Message (to decrypt): ");
                System.out.println(messageToDecrypt);
                decryptedMessage = decrypt(cipherAlphabet, messageToDecrypt);
                System.out.println("Decrypted message: ");
                System.out.println(decryptedMessage);
                System.out.println();
            } // end of if condition
            
            // Invalid input error message
            else if (userChoice != 'Q') {
                System.out.println("Invalid Input! Please try again.");
                System.out.println();
            } // end of if condition and if statement
            
        } while (userChoice != 'Q');   // end of menu do while loop (while they don't want to quit)
        
        // Closing remark
        System.out.println("Thank you for using this program!");
        
        // Close scanner, readfile, and printout
        input.close();
        printOut.close();
        readFile.close();
        
    } // end of main
    
    /** A method that takes a message from a text file, and encrypts it based on user-dfined cipher alphabet(what letters should be changed to) from text file
      * @param cipherAlphabet is the reorganized alphabet used to encrypt and switch around letters
      * @param messageToEncrypt is the original message entered by the user from infile.txt, which they want to be encrypted
      * @return encodedMessage is the encrypted version of the messageToEncrypt string
      */
    public static String encrypt(String cipherAlphabet, String messageToEncrypt) {
        
        // Decalre vraiables for this program
        String encodedMessage = "";
        char newLetter;
        int cipherLetterIndex;
        String originalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        // Analyze each character in message individually
        for (int index = 0; index <= messageToEncrypt.length() - 1; index++) {
            
            // Use index of each letter in message, compared to original alphebet
            cipherLetterIndex = originalAlphabet.indexOf(messageToEncrypt.charAt(index));
            if (cipherLetterIndex > -1) {
                
                // Add cipherAlphabet character to new message, based on OriginalAlphabet index
                newLetter = cipherAlphabet.charAt(cipherLetterIndex);
                encodedMessage += newLetter;
            } // end of if condition
            
            else {
                encodedMessage += messageToEncrypt.charAt(index);
            } // end of else condition and if statement
            
        } // end of for loop
        return encodedMessage;
        
    } // end of encrypt method
    
    /** A method that takes a message from a text file, and decrypts it based on user-dfined cipher alphabet(what letters should be changed based upon) from text file
      * @param cipherAlphabet is the reorganized alphabet used to decrypt and switch around letters
      * @param messageToDecrypt is the original message entered by the user from infile.txt, which they want to be decrypted
      * @return decodedMessage is the decrypted version of the messageToDecrypt string
      */
    public static String decrypt(String cipherAlphabet, String messageToDecrypt) {
        
        // Decalre vraiables for this program
        char newLetter;
        int originalLetterIndex;
        String decodedMessage = "";
        String originalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        // Analyze each character of string letter by letter
        for (int index = 0; index <= messageToDecrypt.length() - 1; index++) {
            
            // Use index of each letter in message, and compare to cipher alphabet
            originalLetterIndex = cipherAlphabet.indexOf(messageToDecrypt.charAt(index));
            if (originalLetterIndex > -1) {
                
                // Use the index from above and add originalAlphabet character (from index) to message
                newLetter = originalAlphabet.charAt(originalLetterIndex);
                decodedMessage += newLetter;
            } // end of if condition
            
            else {
                decodedMessage += messageToDecrypt.charAt(index);
            } // end of if condition and if statement
            
        } // end of for loop
        
        return decodedMessage;
    } // end of decrypt method
    
    /** A method that searches for how many 4 letter words are in a message from a textfile
      * @param sentence is the main string which is searhed through for 4 letter words (message from text file)
      * @return numFourLetterWords is the integer amount of the number of 4 letter words in the sentence
      */
    public static int count4LetterWords(String sentence) {
        
        // Method Variables
        String originalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int numFourLetterWords = 0;
        int letterCount = 0;
        System.out.println();
        
        // Analyze each character of message
        for (int index = 0; index <= sentence.length() - 1; index++) {
            
            // If the character is a letter, letter count is increased
            if ((originalAlphabet.indexOf(sentence.charAt(index)) > -1)) {
                letterCount += 1;
                
                // Number of 4 letter words increased only if there are exactly 4 letters (checked by punctuation or a space after the word)
                if ((letterCount == 4) && (originalAlphabet.indexOf(sentence.charAt(index + 1)) == -1)) {
                    numFourLetterWords++;
                    
                } // end of if condition and if statement
                
            }  // end of if statement
            
            // Set letter count to 0, as a result of the next character of the message not being a letter
            else {
                letterCount = 0;
            } // end of if condition and of statement
            
        } // end of for loop
        
        return numFourLetterWords;
    } // end of count4LetterWords method
    
} // end of class

