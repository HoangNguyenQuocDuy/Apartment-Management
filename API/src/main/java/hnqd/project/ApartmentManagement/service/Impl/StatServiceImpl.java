package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.repository.IStatRepo;
import hnqd.project.ApartmentManagement.service.IStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements IStatService {

    @Autowired
    private IStatRepo statRepo;


    @Override
    public List<Object[]> getRevenueByMonth(int month, int year) {
        return statRepo.getRevenueByMonth(month, year);
    }

    @Override
    public List<Object[]> getTotalRevenueByYear(int year) {
        return statRepo.getTotalRevenueByYear(year);
    }
}
