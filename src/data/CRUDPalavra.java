package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class CRUDPalavra {

	private static Statement state;
	private static ResultSet resultset;
	private static Conexao conex = new Conexao();
	
	public static ArrayList<String[]> selectPalavras() {
		String consulta = "SELECT * FROM tbpalavra";

		conex.conectar();
		ArrayList<String[]> str = new ArrayList<String[]>();
		
		try {
			state = (Statement) Conexao.con.createStatement();
			resultset = state.executeQuery(consulta);
			
			while(resultset.next()) {
				String linha[] = new String[4];
				linha[0] = resultset.getString(1);
				linha[1] = resultset.getString(2);
				linha[2] = resultset.getString(3);
				linha[3] = resultset.getString(4);
				str.add(linha);
			}
			
		} catch (SQLException erro) {
			System.out.println("Não foi possível realizar a consulta");
		}

		conex.desconectar();
		return str;
	}
	
	
	
}
