package br.com.ferias.dao;

import br.com.ferias.jdbc.ConnectionFactory;
import br.com.ferias.model.Cliente;
import br.com.ferias.model.WebServiceCep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClienteDAO {

    private Connection con;

    public ClienteDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void salvarCliente(Cliente cli) {
        try {
            String sql = "insert into tb_clientes(nome, rg, cpf, email, telefone,"
                    + " celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getRg());
            stmt.setString(3, cli.getCpf());
            stmt.setString(4, cli.getEmail());
            stmt.setString(5, cli.getTelefone());
            stmt.setString(6, cli.getCelular());
            stmt.setString(7, cli.getCep());
            stmt.setString(8, cli.getEndereco());
            stmt.setInt(9, cli.getNumero());
            stmt.setString(10, cli.getComplemento());
            stmt.setString(11, cli.getBairro());
            stmt.setString(12, cli.getCidade());
            stmt.setString(13, cli.getEstado());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!"
                    ,"Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu:\n" + e
                    ,"Atenção!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarCliente(Cliente cli) {
        try {
            String sql = "UPDATE tb_clientes SET nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?,"
                    + "cep=?, endereco=?, numero=?, complemento=?, bairro=?,cidade=?, estado=?"
                    + " WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getRg());
            stmt.setString(3, cli.getCpf());
            stmt.setString(4, cli.getEmail());
            stmt.setString(5, cli.getTelefone());
            stmt.setString(6, cli.getCelular());
            stmt.setString(7, cli.getCep());
            stmt.setString(8, cli.getEndereco());
            stmt.setInt(9, cli.getNumero());
            stmt.setString(10, cli.getComplemento());
            stmt.setString(11, cli.getBairro());
            stmt.setString(12, cli.getCidade());
            stmt.setString(13, cli.getEstado());
            stmt.setInt(14, cli.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alteração efetuada com sucesso!"
                    ,"Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu:\n" + e
                    ,"Atenção!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluirCliente(Cliente cli) {
        try {
            String sql = "DELETE FROM tb_clientes WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, cli.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu:\n" + e
                    ,"Atenção!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Cliente> listarClientes() {
        try {
            List<Cliente> clientes = new ArrayList<>();

            String sql = "SELECT * FROM tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setCep(rs.getString("cep"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getInt("numero"));
                cli.setComplemento(rs.getString("complemento"));
                cli.setBairro(rs.getString("bairro"));
                cli.setCidade(rs.getString("cidade"));
                cli.setEstado(rs.getString("estado"));

                clientes.add(cli);
            }

            return clientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar consulta: \n" + e
                    ,"Atenção!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<Cliente> pesquisarClientePorNome(String nome) {
        try {
            List<Cliente> clientes = new ArrayList<>();

            String sql = "SELECT * FROM tb_clientes WHERE nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setCep(rs.getString("cep"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getInt("numero"));
                cli.setComplemento(rs.getString("complemento"));
                cli.setBairro(rs.getString("bairro"));
                cli.setCidade(rs.getString("cidade"));
                cli.setEstado(rs.getString("estado"));

                clientes.add(cli);
            }

            return clientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar consulta: \n" + e
                    ,"Atenção!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public Cliente consultarCliente(String nome){
        try{
            String sql = "SELECT * FROM tb_clientes WHERE nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            
            Cliente cli = new Cliente();

            if (rs.next()) {
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setCep(rs.getString("cep"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getInt("numero"));
                cli.setComplemento(rs.getString("complemento"));
                cli.setBairro(rs.getString("bairro"));
                cli.setCidade(rs.getString("cidade"));
                cli.setEstado(rs.getString("estado"));
                
            }
            
            return cli;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar consulta: \n" + e
                    ,"Atenção!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public Cliente buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       
        Cliente cli = new Cliente();

        if (webServiceCep.wasSuccessful()) {
            cli.setEndereco(webServiceCep.getLogradouroFull());
            cli.setCidade(webServiceCep.getCidade());
            cli.setBairro(webServiceCep.getBairro());
            cli.setEstado(webServiceCep.getUf());
            return cli;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
    
}
