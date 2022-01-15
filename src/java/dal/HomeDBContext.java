/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Tebellum
 */
public class HomeDBContext extends DBContext {

       public ArrayList<Account> getAccounts() {

              ArrayList<Account> acc = new ArrayList<>();
              String sql = "SELECT * FROM `quiz_practice_db`.`account` a join `quiz_practice_db`.`account_profile` inf on inf.account_id = a.account_id";
              try {
                     PreparedStatement ps = connection.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery();
                     while (rs.next()) {
                            Account a = new Account();
                            a.setId(rs.getInt(1));
                            a.setUsername(rs.getString(2));
                            a.setPassword(rs.getString(3));
                            a.setEmail(rs.getString(5));
                            a.setPhone(rs.getString(6));
                            a.setFullname(rs.getString(7));
                            a.setAddress(rs.getString(8));

                            acc.add(a);
                     }
              } catch (SQLException ex) {
                     Logger.getLogger(HomeDBContext.class.getName()).log(Level.SEVERE, null, ex);
              }

              return acc;
       }

//       public static void main(String[] args) {
//              HomeDBContext h = new HomeDBContext();
//              for (Account b : h.getBooks()) {
//                     System.out.println(b.toString());
//              }
//       }
}
