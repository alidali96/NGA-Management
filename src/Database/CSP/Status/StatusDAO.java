package Database.CSP.Status;

import Database.CSP.CSPDAO;
import Database.DAO;
import Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusDAO extends CSPDAO {

    public StatusDAO(String table) {
        super(table);
    }
}
