package com.jsu.utils;

import com.jsu.bean.Comment;
import com.jsu.bean.Goods;
import com.jsu.bean.User;
import com.jsu.dao.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Text {
    public static void main(String[] args) {
        UserDAO uDao=new UserDAO();
        uDao.updateUserSellCount(1);

    }
}
