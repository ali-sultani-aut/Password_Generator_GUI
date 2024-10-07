/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;

public class PasswordEncryptor {

    private int shift; // The number of positions to shift each character

    public PasswordEncryptor(String secretKey) {
        this.shift = calculateShift(secretKey); // Shifts based on the secret key passed
    }

    // Uses secret key to calculate the shift number which is used later to decrypt
    private int calculateShift(String secretKey) {
        int shiftValue = 0;
        for (char c : secretKey.toCharArray()) {
            shiftValue += (int) c;
        }
        return shiftValue % 26; // Keeps the shift number within a reasonable range 0 to 25
    }
     
    // Method used to encrypt the string data passed to it by shifting characters
    public String encrypt(String data) {

        StringBuilder encrypted = new StringBuilder();
        for (char c : data.toCharArray()) {

            char shiftedChar = (char) (c + shift);
            encrypted.append(shiftedChar);
        }

        return encrypted.toString();
    }

    // Method to decrypt the string data by reversing the shift using the shif number
    public String decrypt(String encryptedData) {

        StringBuilder decrypted = new StringBuilder();

        for (char c : encryptedData.toCharArray()) {
            char shiftedChar = (char) (c - shift);
            decrypted.append(shiftedChar);
        }

        return decrypted.toString();
    }
}
