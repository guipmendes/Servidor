/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Mendes
 * Data 19/03/2017
 */
public class ArquivoLog {
    
    File arquivo;
    FileReader fileReader;
    BufferedReader bufferedReader;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    
    public ArquivoLog(String mensagem){
        escreverLog(mensagem);
    }
    public void escreverLog(String mensagem){
        try {
            arquivo = new File("arquivo.log");
            fileReader = new FileReader(arquivo);
            bufferedReader = new BufferedReader(fileReader);
            Vector texto = new Vector();
            
            while(bufferedReader.ready()){
                texto.add(bufferedReader.readLine());
            }
            
            fileWriter = new FileWriter(arquivo);
            bufferedWriter = new BufferedWriter(fileWriter);
            
            for(int i=0; i<texto.size(); i++){
                bufferedWriter.write(texto.get(i).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.write(mensagem);
            bufferedReader.close();
            bufferedWriter.close();
        } catch (FileNotFoundException ex) {
            try {
                arquivo.createNewFile();
                escreverLog(mensagem);
            } catch (IOException ex1) {
                System.exit(0);
            }
        }catch(IOException er){
            System.exit(0);
        }
        
        
    }
}