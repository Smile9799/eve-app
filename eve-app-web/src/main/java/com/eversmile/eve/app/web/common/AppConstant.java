package com.eversmile.eve.app.web.common;

public class AppConstant {

    private AppConstant(){}

    public enum APP_ROLES {DEFAULT, ADMIN, MODERATOR}
    public enum BUDGET_TYPE {DAILY, MONTHLY, WEEKLY, ANNUAL}
    public enum GROUP_TYPE {INDIVIDUAL, JOINT}
    public static final String[] PUBLIC_RESOURCES = {"/auth/**","/assets/**"};
}
