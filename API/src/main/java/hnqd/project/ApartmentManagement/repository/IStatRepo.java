package hnqd.project.ApartmentManagement.repository;

import java.util.List;

public interface IStatRepo {
    List<Object[]> getTotalRevenueByYear(int year);
    List<Object[]> getRevenueByMonth(int month, int year);
}
