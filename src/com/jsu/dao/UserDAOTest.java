package com.jsu.dao;

import static org.junit.Assert.*;

public class UserDAOTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void isExistEmail() {
        new UserDAO().isExistEmail("2313381768@qq.com");
    }
    @org.junit.Test
    public void findByUserID() {
    }

    @org.junit.Test
    public void findByGoodsID() {
    }

    @org.junit.Test
    public void updateUserSellCount() {
        new UserDAO().updateUserSellCount(2);
    }

    @org.junit.Test
    public void updateHeadShot() {
        new UserDAO().updateUserSellCount(5);
    }

    @org.junit.Test
    public void updateUserBaseInfo() {
    }

    @org.junit.Test
    public void save() {
        new UserDAO().save("123","321");
    }

    @org.junit.Test
    public void login() {
        new UserDAO().login("123","321");
    }
}