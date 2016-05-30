package DAO;

import conexao.ConexaoBanco;
import classes.entidades.Produto;
import classes.ProdutoListagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caiqu
 */
public class ProdutoDAO {

    public static void adicionar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO PRODUTO"
                //Nomes dos campos no banco
                + "(CODIGOFILIAL, IDUSUARIO, NOME, QUANTIDADEPECA, STATUS)"
                + "VALUES(?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, produto.getCodigoFilial());
        stmt.setInt(2, produto.getCodUsuario());
        stmt.setString(3, produto.getNome());
        stmt.setInt(4, produto.getQtdPeca());
        stmt.setBoolean(5, produto.getStatus());
        stmt.execute();
        stmt.close();
    }
    
    public static void alterar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "UPDATE PRODUTO SET CODIGOFILIAL = ?, IDUSUARIO = ?, NOME = ?, QUANTIDADEPECA = ?, "
                + "STATUS = ? WHERE CODPECA = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        stmt.setInt(1, produto.getCodigoFilial());
        stmt.setInt(2, produto.getCodUsuario());
        stmt.setString(3, produto.getNome());
        stmt.setInt(4, produto.getQtdPeca());
        stmt.setBoolean(5, produto.getStatus());
        stmt.setInt(6, produto.getCodigoPeca());
        
        stmt.execute();
        stmt.close();
    }
    
    public static Produto consultarPorId(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "SELECT CODPECA, CODIGOFILIAL, IDUSUARIO, NOME, QUANTIDADEPECA, STATUS"
                + "FROM PRODUTO WHERE CODPECA = ?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        result.next();
        
        int codPeca = result.getInt("CODPECA");
        int codFilial = result.getInt("CODIGOFILIAL");
        int idUsuario = result.getInt("IDUSUARIO");
        String nome = result.getString("NOME");
        int qtdPeca = result.getInt("QUANTIDADEPECA");
        boolean status = result.getBoolean("STATUS");
        
        Produto produto = new Produto(codPeca, codFilial, idUsuario, nome, qtdPeca, status);
        
        stmt.close();
        
        return produto;
    }
    
    public static List<ProdutoListagem> listar() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "SELECT P.CODPECA, F.NOME AS FILIAL, U.NOME AS USUARIO, P.NOME AS PRODUTO, "
                + "P.QUANTIDADEPECA, P.STATUS FROM PRODUTO P INNER JOIN FILIAL F ON P.CODIGOFILIAL = "
                + " F.CODIGOFILIAL INNER JOIN USUARIO U ON P.IDUSUARIO = U.CODIGOUSUARIO";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<ProdutoListagem> retorno = new ArrayList<ProdutoListagem>();
        ResultSet result = stmt.executeQuery();
        
        while(result.next()) {
            
            int codPeca = result.getInt("CODPECA");
            String filial = result.getString("FILIAL");
            String usuario = result.getString("USUARIO");
            String nomeProduto = result.getString("PRODUTO");
            int qtdPeca = result.getInt("QUANTIDADEPECA");
            boolean status = result.getBoolean("STATUS");

            ProdutoListagem produto = new ProdutoListagem(codPeca, filial, usuario, nomeProduto, qtdPeca, status);
        
            retorno.add(produto);
        }
        
        return retorno;
    }

    public static int maxId() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = "SELECT MAX(CODPECA)FROM PRODUTO";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        int prox = 0;
        ResultSet rs = stmt.executeQuery();
        if (rs.next() == false) {
            prox = 1;
        } else {
            prox = rs.getInt(1) + 1;
        }
        return prox;
    }

}
