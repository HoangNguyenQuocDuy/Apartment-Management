package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/stats")
@RestController
public class StatController {

    @Autowired
    private IStatService statService;

    @GetMapping("/revenue")
    public ResponseEntity<ResponseObject> getStatsRevenueByMonth(@RequestParam Map<String, String> params) {
        int month = Integer.parseInt(params.getOrDefault("month", String.valueOf(0)));

        if (month != 0) {
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                    new ResponseObject("OK", "Get stat revenue by month successfully!",
                            statService.getRevenueByMonth(Integer.parseInt(params.get("month")), Integer.parseInt(params.get("year"))))
            );

        }
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get stat revenue by year successfully!",
                        statService.getTotalRevenueByYear(Integer.parseInt(params.get("year"))))
        );
    }
}
