/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtask;

import java.awt.PageAttributes;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.rsa.RSACore;
import xo.SavedGame;

/**
 *
 * @author lenovo
 */
public class DbTask {

    /**
     * @param args the command line arguments
     */
    Connection con = null;
    int i = 0;

    public DbTask() {

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("nnnnn");
            String url = "jdbc:mysql://localhost:3306/player";
            String user = "root";
            String password = "game123456";
            con
                    = DriverManager.getConnection(url, user, password);
//            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("not connected");
        }
    }

    public boolean insert(String[] params) {
        System.out.println("here in insert");
        System.out.println(params[0]);
        System.out.println(params[1]);
        String insertTableSQL = "INSERT INTO players"
                + "(username,password,nickname,points,flag) VALUES"
                + "(?,?,?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(insertTableSQL);
            pst.setString(1, params[0]);
            pst.setString(2, params[1]);
            pst.setString(3, params[2]);
            pst.setInt(4, 0);
            pst.setInt(5, 0);
            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public Vector<Player> getAll() {
        ResultSet resultSet = null;
        Vector<Player> players = new Vector<>();
        String sql = "select * from players  WHERE flag = ? ORDER BY points desc";
        try (
                PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE)) {
            pstmt.setInt(1, 1);
            resultSet = pstmt.executeQuery();
//            int i = 0;
            while (resultSet.next()) {
                Player p = new Player();
                p.setId(resultSet.getInt(1));
                p.setUsername(resultSet.getString(2));
                p.setPassword(resultSet.getString(3));
                p.setPoints(resultSet.getInt(4));
                p.setFlag(resultSet.getInt(5));
                p.setNickName(resultSet.getString(6));
//                persons.set(i, p);
                System.out.println(p.getId());
                players.add(p);
//                i++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(players);
        return players;
    }

    public Vector<Player> getDbAll() {
        ResultSet resultSet = null;
        Vector<Player> players = new Vector<>();
        String sql = "select * from players ORDER BY points desc;";
        try (
                PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE)) {
//            pstmt.setInt(1, 1);
            resultSet = pstmt.executeQuery();
//            int i = 0;
            while (resultSet.next()) {
                Player p = new Player();
                p.setId(resultSet.getInt(1));
                p.setUsername(resultSet.getString(2));
                p.setPassword(resultSet.getString(3));
                p.setPoints(resultSet.getInt(4));
                p.setFlag(resultSet.getInt(5));
                p.setNickName(resultSet.getString(6));
//                persons.set(i, p);
                System.out.println(p.getId());
                players.add(p);
//                i++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(players);
        return players;
    }

    public boolean checkUser(String[] params) {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM players WHERE username = ? and password = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, params[0]);
            ps.setString(2, params[1]);
            rs = ps.executeQuery();
            if (rs.next()) {
                checkUser = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return checkUser;
    }

    public Player getPerson(String[] params) {
        Player p = new Player();
        String sql = "SELECT * FROM players WHERE username = ? and password = ?";
        try (
                PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE)) {
            pstmt.setString(1, params[0]);
            pstmt.setString(2, params[1]);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                p.setId(resultSet.getInt(1));
                p.setUsername(resultSet.getString(2));
                p.setPassword(resultSet.getString(3));
                p.setNickName(resultSet.getString(6));
//                System.out.println(resultSet.getInt(1) + "\t"
//                        + resultSet.getString(2) + "\t"
//                        + resultSet.getInt(3) + "\t"
//                        + resultSet.getInt(4));
//                i++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public void update(int id) {
        System.out.println("updated");
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE players SET flag = ?  where id = ?");
            pst.setInt(1, 1);
            pst.setInt(2, id);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void updateOfline(int id) {
        System.out.println("updated");
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE players SET flag = ?  where id = ?");
            pst.setInt(1, 0);
            pst.setInt(2, id);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("error");
        }
    }

//    public void updatePoints(String nickName) {
//        System.out.println("updated");
//        try {
//            PreparedStatement pst = con.prepareStatement("UPDATE players set points = points + 10 where points = ANY (select points where nickName = ?);");
//            pst.setString(1, nickName);
////            pst.setInt(2, id);
//            pst.executeUpdate();
//
//        } catch (Exception e) {
//            System.out.println("error");
//        }
//    }
    public void updatePoints(String nickName) {
        System.out.println("updated");
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE players set points = points + 10 where nickname = ?");
            pst.setString(1, nickName);
//            pst.setInt(2, id);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("error");
        }
    }
    public void insertGame(int[] params, String player1, String player2) {
        System.out.println(params[0]);
        System.out.println(params[1]);
        String insertTableSQL = "INSERT INTO saved_games (player1,player2,pos1,pos2,pos3,pos4,pos5,pos6,pos7,pos8,pos9) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(insertTableSQL);
            pst.setString(1, player1);
            pst.setString(2, player2);
            pst.setInt(3, params[0]);
            pst.setInt(4, params[1]);
            pst.setInt(5, params[2]);
            pst.setInt(6, params[3]);
            pst.setInt(7, params[4]);
            pst.setInt(8, params[5]);
            pst.setInt(9, params[6]);
            pst.setInt(10, params[7]);
            pst.setInt(11, params[8]);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public SavedGame getGame(String player1 , String player2) {
        ResultSet resultSet = null;
        SavedGame game = new SavedGame();
//        Vector<Object> data = new Vector<>();
        String sql = "select * from saved_games where player1 = ? and player2 = ? limit 1";
        try (
                PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE)) {
            pstmt.setString(1, player1);
            pstmt.setString(2, player2);
            resultSet = pstmt.executeQuery();
            game.player1 = resultSet.getString(2);
            game.player2 = resultSet.getString(3);
            game.game[0] = resultSet.getInt(4);
            game.game[1] = resultSet.getInt(5);
            game.game[2] = resultSet.getInt(6);
            game.game[3] = resultSet.getInt(7);
            game.game[4] = resultSet.getInt(8);
            game.game[5] = resultSet.getInt(9);
            game.game[6] = resultSet.getInt(10);
            game.game[7] = resultSet.getInt(11);
            game.game[8] = resultSet.getInt(12);
            
//            int i = 0;
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        System.out.println(data);
//        return data;
            return game;
    }
    
     public static void main(String[] args) {
        DbTask n =new DbTask();
        n.getAll();
//         String[] params = {"nour", "nour"};
        Vector<Player>  v;
        v =n.getAll();
        
        for (Player mc :v) {
            System.out.println(mc.getNickName());
//            v.add(mc.getNickName());
        }
//         System.out.println(v);
//         System.out.println("8888888888888888888");
        
    }
}
