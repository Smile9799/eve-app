# Entities

## TBL_USERS
    - ID NUMBER
    - UUID VARCHAR(55)
    - EMAIL VARCHAR(255)
    - PASSWORD VARCHAR(255)
    - FIRST_NAME VARCHAR(255)
    - LAST_NAME VARCHAR(255)
    - IS_ACTIVE BIT
    - IS_LOCKED BIT
    - DATE_JOINED TIMESTAMP
    - IMG_URL LONGTEXT

## TBL_ROLES
    - ID NUMBER
    - UUID VARCHAR(55)
    - ROLE_NAME VARCHAR(255)
    - ROLE_DESCRIPTION LONGTEXT

## TBL_USER_ROLES
    - ROLE_ID NUMBER
    - USER_ID NUMBER
    - IS_ACTIVE BIT

## TBL_BUDGET_TYPE
    - ID NUMBER
    - UUID VARCHAR(55)
    - BUDGET_TYPE_NAME VARCHAR(255)
    - BUDGET_TYPE_DESCRIPTION LONGTEXT

## TBL_BUDGET
    - ID NUMBER
    - UUID VARCHAR(55)
    - BUDGET_NAME VARCHAR(255)
    - BUDGET_DESCRIPTION LONGTEXT
    - BUDGET_COMMENT LONGTEXT
    - BUDGET_AMOUNT_AVAILABLE DECIMAL
    - BUDGET_AMOUNT_PROPOSED DECIMAL
    - BUDGET_AMOUNT_PLANNED DECIMAL
    - BUDGET_AMOUNT_ACTUAL DECIMAL
    - BUDGET_DIFFERENCE_AMOUNT_ACTUAL DECIMAL
    - BUDGET_DIFFERENCE_AMOUNT_PLANNED DECIMAL
    - BUDGET_FUND_SOURCE VARCHAR(255)
    - BUDGET_SUCCESSFUL BIT
    - BUDGET_TYPE_ID NUMBER *FROM TBL_BUDGET_TYPE*
    - BUDGET_START_DATE TIMESTAMP
    - BUDGET_END_DATE TIMESTAMP
    - BUDGET_FINANCIAL_YEAR VARCHAR(55)
    - BUDGET_FINANCIAL_MONTH VARCHAR(55)
    - USER_ID NUMBER *FROM TBL_USERS*

## TBL_TRANSACTION_ITEM
    - ID NUMBER
    - UUID VARCHAR(55)
    - ITEM_NAME VARCHAR(255)
    - DESCRIPTION LONGTEXT
    - ITEM_PLANNED_COST DECIMAL
    - ITEM_ACTUAL_COST DECIMAL
    - ITEM_DIFFERENCE_AMOUNT DECIMAL
    - ITEM_ACTUAL_DATE TIMESTAMP
    - APP_CATEGORY_ID NUMBER *FROM TBL_APP_CATEGORIES*
    - ITEM_SUCCESS BIT
    - BUDGET_ID NUMBER *FROM TBL_BUDGET*

## TBL_TODO_TASK
    - ID NUMBER
    - UUID VARCHAR(55)
    - TASK_NAME VARCHAR(255)
    - TASK_DESCRIPTION LONGTEXT
    - TASK_BEGIN_DATE TIMESTAMP
    - TASK_END_DATE TIMESTAMP
    - USER_ID NUMBER *FROM TBL_USERS*

## TBL_TODO_TASK_FINANCE    
    - ID NUMBER
    - UUID VARCHAR(55)
    - NAME VARCHAR(255)
    - DESCRIPTION LONGTEXT
    - PLANNED_AMOUNT DECIMAL
    - ACTUAL_AMOUNT DECIMAL
    - SUCCESSFUL BIT
    - ITEM_ID NUMBER *FROM TBL_TRANSACTION_ITEM*

## TBL_NOTES
    - ID NUMBER
    - UUID VARCHAR(55)
    - NOTE_NAME VARCHAR(255)
    - NOTE_DESCRIPTION LONGTEXT
    - USER_ID NUMBER *FROM TBL_USERS*

## TBL_APP_CATEGORIES
    - ID NUMBER
    - UUID VARCHAR(55)
    - CATEGORY_TYPE VARCHAR(255)
    - CATEGORY_NAME VARCHAR(255)
    - IS_ACTIVE BIT

## TBL_NEWS

## TBL_WEATHER

## TBL_REMINDERS

## TBL_ALARMS