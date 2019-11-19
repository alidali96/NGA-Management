package Database.CSP.Status;

import Const.Const;
import Database.CSP.CSPDAO;
import Database.DAO;
import Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusDAO extends CSPDAO {

    public StatusDAO() {
        super(Const.TABLE_STATUS);
    }
}
