package DAO;

import conexao.ConexaoBanco;
import classes.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caiqu
 */
public class ProdutoDAO {

    public static void adicionar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO MOVIMENTACAOENTRADA  "
                //Nomes dos campos no banco
                + "(nome, codPeca, qtdPeca, valor, status, codigoFilial)"
                + "VALUES(?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getCodigoPeca());
        stmt.setInt(3, produto.getQtdPeca());
        stmt.setDouble(4, produto.getValor());
        stmt.setBoolean(5, produto.isStatus());
        stmt.setInt(6, produto.getCodigoFilial());

        stmt.execute();
        stmt.close();
    }

    public static int maxId() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = "SELECT MAX(CODPECA)FROM PRODUTO";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        int prox=0;
        ResultSet rs = stmt.executeQuery();
        if(rs.next()==false) {
                prox = 1;
            } else {
                prox = rs.getInt(1) + 1;
            }
        return prox;
    }

}