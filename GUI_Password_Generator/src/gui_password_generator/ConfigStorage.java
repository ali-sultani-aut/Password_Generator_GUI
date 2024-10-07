/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_password_generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigStorage extends FileIO // extend FileIO for handling saving, loading and delete data
{
    private static final String CONFIG_FILE_PATH = "config.txt";

    @Override
    public void saveToFile(String configContent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH))) {
            writer.write(configContent);
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error saving configuration", e);
        }
    }

    @Override
    public String loadFromFile() throws IOException {
        StringBuilder content = new StringBuilder(); // collects file content using StringBuilder
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) // reads every line in the file
            {
                content.append(line).append("\n"); // appends every line to StringBuilder and adds a new line
            }
        } catch (IOException e) {
            throw new IOException("Error loading configuration", e);
        }
        return content.toString();
    }

    public void deleteConfig() throws IOException // delete config file
    {
        File configFile = new File(CONFIG_FILE_PATH);
        if (configFile.exists()) {
            if (!configFile.delete()) {
                throw new IOException("Error deleting configuration file");
            }
        }
    }
}