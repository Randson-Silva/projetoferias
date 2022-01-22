package br.com.ferias.jdbc;

import javax.swing.JOptionPane;

public class ConnectionTester {
    public static void main(String[] ags){
        
        try{
           new ConnectionFactory().getConnection();
           JOptionPane.showMessageDialog(null, "Conexão estabelecida!");
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro de conexão!\n"+e);
        }
    }
}
