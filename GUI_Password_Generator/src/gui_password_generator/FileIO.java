/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;
import java.io.IOException;

public abstract class FileIO 
{
    public abstract void saveToFile(String content) throws IOException; // to save content to a file (via abstract method)
    public abstract String loadFromFile() throws IOException;           // load content from a file (via abstract method)
}