package cesur.dam.inventarioappg1.data;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;

public class DataSourceFactory {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        // Se prepara una vez y se reutiliza en las consultas del DAO.
        if (dataSource == null) {
            String username = System.getenv("USER");
            String password = System.getenv("PASSWORD");

            if (username == null || password == null) {
                throw new RuntimeException("Configura USER y PASSWORD en la ejecución de IntelliJ.");
            }

            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3307/ad");
            ds.setUser(username);
            ds.setPassword(password);
            dataSource = ds;
        }
        return dataSource;
    }
}
