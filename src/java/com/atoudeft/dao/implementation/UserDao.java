package com.atoudeft.dao.implementation;

import com.atoudeft.entites.User;
import com.atoudeft.dao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDao extends Dao<User> {

    public UserDao(Connection c) {
        super(c);
    }

    @Override
    public boolean create(User x) {
        PreparedStatement stm = null;       
        try {
            stm = cnx.prepareStatement("INSERT INTO user (`username`, `password`) VALUES (?, ?)");
            stm.setString(1, x.getUsername() );
            stm.setString(2, x.getPassword() );
            ResultSet r = stm.executeQuery();
            if ( r.next() ) {
                stm.close();
                return true;
            }
        } catch (SQLException exp) {
			
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {                
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(User x) {        
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("DELETE FROM user WHERE id = ?");
            stm.setInt(1, x.getId() );
            ResultSet r = stm.executeQuery();
            if ( r.next() ) {
                stm.close();
                return true;
            }
        } catch (SQLException exp) {
        
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override 
    public User read(String username) {        
        PreparedStatement stm = null;
        try {            
            stm = cnx.prepareStatement("SELECT * FROM `user` WHERE `username` = ?");
            stm.setString(1,username);
            ResultSet r = stm.executeQuery();
            if (r.next()) {
                User c = new User();
                c.setId(r.getInt("id"));
                c.setUsername(r.getString("username"));
                c.setPassword(r.getString("password"));               
                c.setEtat( r.getString("etat") );                      
                r.close();
                stm.close();
                return c;
            }
        } catch (SQLException exp) {
			
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {            
                    e.printStackTrace();
                }
            }
        }
        return null;
    }    
// U P D A T E    
    @Override
    public boolean update(User x) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("UPDATE user SET username = ?, password = ? WHERE id = ?");
            stm.setString(1, x.getUsername() );            
            stm.setString(2, x.getPassword() );
            stm.setInt(3, x.getId() );
            ResultSet r = stm.executeQuery();
            if ( r.next() ) {
                stm.close();
                return true;
            }
        } catch (SQLException exp) {
			
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {            
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    public boolean updatePassword(User x) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("UPDATE user SET password = ? WHERE id = ?");
            stm.setString(1, x.getPassword());
            stm.setInt(2, x.getId());
            int n = stm.executeUpdate();
            if (n > 0) {
                stm.close();
                return true;
            }
        } catch (SQLException exp) {
			
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {            
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    public boolean updateEtatToActif(User x) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("UPDATE `user` SET `etat` = ? WHERE `id` = ?");
            stm.setString(1, "actif");
            stm.setInt(2, x.getId());
            ResultSet r = stm.executeQuery();
            if ( r.next() ) {
                stm.close();
                return true;
            }
        } catch (SQLException exp) {
			
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {            
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    public boolean updateEtatToInactif(User x) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("UPDATE user SET etat = ? WHERE id = ?");
            stm.setString(1, "inactif");
            stm.setInt(2, x.getId());
            ResultSet r = stm.executeQuery();
            if ( r.next() ) {
                stm.close();
                return true;
            }
        } catch (SQLException exp) {
			
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {            
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
// F I N D A L L    
    @Override
    public List<User> findAll() {
        List<User> liste = new LinkedList<User>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM user");
            while (r.next()) {
                User c = new User(r.getInt("id"), r.getString("username"), 
                                r.getString("password"), r.getString("etat"));                                
                liste.add(c);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
            
        }
        return liste;
    }
    public List<User> findAllActive() {
        List<User> liste = new LinkedList<User>();
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("SELECT * FROM user WHERE etat = ?");
            stm.setString(1, "actif");
            ResultSet r = stm.executeQuery();
            while (r.next()) {
                User c = new User(r.getInt("id"), r.getString("username"), 
                                r.getString("password"), r.getString("etat"));
                liste.add(c);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
            
        }
        return liste;
    }
}