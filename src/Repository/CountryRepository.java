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
        try {
            Connection conn = DbConstants.getConnection();
            String insertCountry = "INSERT INTO Country(name) VALUES(?);";
            PreparedStatement countryStmt = conn.prepareStatement(insertCountry);
            countryStmt.setString(1, country.getName());
            countryStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCountry(Country country) {
        try {
            Connection conn = DbConstants.getConnection();
            String removeCountry = "DELETE FROM Country WHERE name = ?;";
            PreparedStatement countryStmt = conn.prepareStatement(removeCountry);
            countryStmt.setString(1, country.getName());
            countryStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Country getCountryByName(String name) {
        try {
            Connection conn = DbConstants.getConnection();
            String findCountry = "SELECT * FROM Country WHERE name = ?;";
            PreparedStatement countryStmt = conn.prepareStatement(findCountry);
            countryStmt.setString(1, name);
            ResultSet rs = countryStmt.executeQuery();
            if (rs.next()) {
                return new Country(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public SortedSet<Country> getCountries() {
        try {
            Connection conn = DbConstants.getConnection();
            String findCountries = "SELECT * FROM Country;";
            PreparedStatement countryStmt = conn.prepareStatement(findCountries);
            ResultSet rs = countryStmt.executeQuery();
            SortedSet<Country> countries = new TreeSet<Country>();
            while (rs.next()) {
                Country country = new Country(rs.getString("name"));
                countries.add(country);
            }
            return countries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
