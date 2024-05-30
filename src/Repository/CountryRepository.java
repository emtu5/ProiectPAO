package Repository;

import Model.Country;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class CountryRepository implements ICountryRepository {
    @Override
    public void addCountry(Country country) {
        Connection conn = null;
        PreparedStatement countryStmt = null;
        try {
            conn = DbConstants.getConnection();
            String insertCountry = "INSERT INTO Country(name) VALUES(?);";
            countryStmt = conn.prepareStatement(insertCountry);
            countryStmt.setString(1, country.getName());
            countryStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert countryStmt != null;
                countryStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeCountry(Country country) {
        Connection conn = null;
        PreparedStatement countryStmt = null;
        try {
            conn = DbConstants.getConnection();
            String removeCountry = "DELETE FROM Country WHERE name = ?;";
            countryStmt = conn.prepareStatement(removeCountry);
            countryStmt.setString(1, country.getName());
            countryStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert countryStmt != null;
                countryStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Country getCountryByName(String name) {
        Connection conn = null;
        PreparedStatement countryStmt = null;
        ResultSet rs = null;
        try {
            conn = DbConstants.getConnection();
            String findCountry = "SELECT * FROM Country WHERE name = ?;";
            countryStmt = conn.prepareStatement(findCountry);
            countryStmt.setString(1, name);
            rs = countryStmt.executeQuery();
            if (rs.next()) {
                return new Country(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                countryStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public SortedSet<Country> getCountries() {
        Connection conn = null;
        PreparedStatement countryStmt = null;
        ResultSet rs = null;
        try {
            conn = DbConstants.getConnection();
            String findCountries = "SELECT * FROM Country;";
            countryStmt = conn.prepareStatement(findCountries);
            rs = countryStmt.executeQuery();
            SortedSet<Country> countries = new TreeSet<Country>();
            while (rs.next()) {
                Country country = new Country(rs.getString("name"));
                countries.add(country);
            }
            return countries;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                countryStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
