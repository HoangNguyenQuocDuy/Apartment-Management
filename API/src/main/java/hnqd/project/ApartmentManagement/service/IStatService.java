package hnqd.project.ApartmentManagement.service;

import java.util.List;

public interface IStatService {
    List<Object[]> getRevenueByMonth(int month, int year);
    List<Object[]> getTotalRevenueByYear(int year);
}
