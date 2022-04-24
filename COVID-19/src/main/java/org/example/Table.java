package org.example;

public class Table {
    String country;
    int totalCases;
    int totalDeath;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(int totalDeath) {
        this.totalDeath = totalDeath;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    int totalTests;
    @Override
    public String toString() {
        return String.format("Country: %-12s TotalCases: %-8d TotalDeaths: %-8d TotalTests: %-8d",this.country,this.totalCases,this.totalDeath,this.totalTests);
    }
}
