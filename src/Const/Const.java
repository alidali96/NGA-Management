package Const;

public class Const {

    public static final String DB_NAME = "adalidb";
    public static final String DB_USER = "adali";
    public static final String DB_PASS = "j54czj54czhm4v1hm4v1";


    public static final int CLOSED = 0;
    public static final int OPEN = 1;

    public static final int SUCCESS = 1;
    public static final int EXIST = 0;
    public static final int FAILED = -1;
    public static final int NOT_FOUND = -2;



    public static final String TABLE_STATUS = "status";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_PRIORITY = "priority";
    public static final String TABLE_PROJECT = "project";
    public static final String TABLE_TASK = "task";

    // TABLES CATEGORY - STATUS - PRIORITY
    public static final String CSP_COLUMN_ID = "id";
    public static final String CSP_COLUMN_NAME = "name";
    public static final String CSP_COLUMN_COLOR = "color";


    // TABLE PROJECT
    public static final String PROJECT_COLUMN_ID = "id";
    public static final String PROJECT_COLUMN_TITLE = "title";
    public static final String PROJECT_COLUMN_DESCRIPTION = "description";
    public static final String PROJECT_COLUMN_STATUS = "status";
    public static final String PROJECT_COLUMN_CATEGORY = "category";
    public static final String PROJECT_COLUMN_PRIORITY = "priority";
    public static final String PROJECT_COLUMN_START_DATE = "start_date";
    public static final String PROJECT_COLUMN_DUE_DATE = "due_date";


    // TABLE TASK
    public static final String TASK_COLUMN_ID = "id";
    public static final String TASK_COLUMN_NAME = "name";
    public static final String TASK_COLUMN_DESCRIPTION = "description";
    public static final String TASK_COLUMN_PROJECT = "project";
    public static final String TASK_COLUMN_OPEN = "open";



}
