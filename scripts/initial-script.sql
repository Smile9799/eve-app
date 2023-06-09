CREATE TABLE TBL_USERS (
    ID INT,
    UUID VARCHAR(55),
    EMAIL VARCHAR(255),
    PASSWORD VARCHAR(255),
    FIRST_NAME VARCHAR(255),
    LAST_NAME VARCHAR(255),
    IS_ACTIVE BIT,
    IS_LOCKED BIT,
    DATE_JOINED TIMESTAMP,
    IMG_URL LONGTEXT,
    PRIMARY KEY (ID)
);

CREATE TABLE TBL_ROLES (
    ID INT,
    UUID VARCHAR(55),
    ROLE_NAME VARCHAR(255),
    ROLE_DESCRIPTION LONGTEXT,
    PRIMARY KEY (ID)
);

CREATE TABLE TBL_USER_ROLES (
    ROLE_ID INT,
    USER_ID INT,
    IS_ACTIVE BIT,
    FOREIGN KEY (ROLE_ID) REFERENCES TBL_ROLES (ID),
    FOREIGN KEY (USER_ID) REFERENCES TBL_USERS (ID)
);

CREATE TABLE TBL_APP_CATEGORIES (
    ID INT,
    UUID VARCHAR(55),
    CATEGORY_TYPE VARCHAR(255),
    CATEGORY_NAME VARCHAR(255),
    IS_ACTIVE BIT,
    PRIMARY KEY (ID)
);

CREATE TABLE TBL_BUDGET_TYPE (
    ID INT,
    UUID VARCHAR(55),
    BUDGET_TYPE_NAME VARCHAR(255),
    BUDGET_TYPE_DESCRIPTION LONGTEXT,
    PRIMARY KEY (ID)
);

CREATE TABLE TBL_BUDGET (
    ID INT,
    UUID VARCHAR(55),
    BUDGET_NAME VARCHAR(255),
    BUDGET_DESCRIPTION LONGTEXT,
    BUDGET_COMMENT LONGTEXT,
    BUDGET_AMOUNT_AVAILABLE DECIMAL(15, 2),
    BUDGET_AMOUNT_PROPOSED DECIMAL(15, 2),
    BUDGET_AMOUNT_PLANNED DECIMAL(15, 2),
    BUDGET_AMOUNT_ACTUAL DECIMAL(15, 2),
    BUDGET_DIFFERENCE_AMOUNT_ACTUAL DECIMAL(15, 2),
    BUDGET_DIFFERENCE_AMOUNT_PLANNED DECIMAL(15, 2),
    BUDGET_FUND_SOURCE VARCHAR(255),
    BUDGET_SUCCESSFUL BIT,
    BUDGET_TYPE_ID INT,
    BUDGET_START_DATE TIMESTAMP,
    BUDGET_END_DATE TIMESTAMP,
    BUDGET_FINANCIAL_YEAR VARCHAR(55),
    BUDGET_FINANCIAL_MONTH VARCHAR(55),
    USER_ID INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (BUDGET_TYPE_ID) REFERENCES TBL_BUDGET_TYPE (ID),
    FOREIGN KEY (USER_ID) REFERENCES TBL_USERS (ID)
);

CREATE TABLE TBL_TRANSACTION_ITEM (
    ID INT,
    UUID VARCHAR(55),
    ITEM_NAME VARCHAR(255),
    DESCRIPTION LONGTEXT,
    ITEM_PLANNED_COST DECIMAL(15, 2),
    ITEM_ACTUAL_COST DECIMAL(15, 2),
    ITEM_DIFFERENCE_AMOUNT DECIMAL(15, 2),
    ITEM_ACTUAL_DATE TIMESTAMP,
    APP_CATEGORY_ID INT,
    ITEM_SUCCESS BIT,
    BUDGET_ID INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (APP_CATEGORY_ID) REFERENCES TBL_APP_CATEGORIES (ID),
    FOREIGN KEY (BUDGET_ID) REFERENCES TBL_BUDGET (ID)
);

CREATE TABLE TBL_TODO_TASK (
    ID INT,
    UUID VARCHAR(55),
    TASK_NAME VARCHAR(255),
    TASK_DESCRIPTION LONGTEXT,
    TASK_BEGIN_DATE TIMESTAMP,
    TASK_END_DATE TIMESTAMP,
    USER_ID INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID) REFERENCES TBL_USERS (ID)
);

CREATE TABLE TBL_TODO_TASK_FINANCE (
    ID INT,
    UUID VARCHAR(55),
    NAME VARCHAR(255),
    DESCRIPTION LONGTEXT,
    PLANNED_AMOUNT DECIMAL(15, 2),
    ACTUAL_AMOUNT DECIMAL(15, 2),
    SUCCESSFUL BIT,
    ITEM_ID INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (ITEM_ID) REFERENCES TBL_TRANSACTION_ITEM (ID)
);

CREATE TABLE TBL_NOTES (
    ID INT,
    UUID VARCHAR(55),
    NOTE_NAME VARCHAR(255),
    NOTE_DESCRIPTION LONGTEXT,
    USER_ID INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID) REFERENCES TBL_USERS (ID)
);
