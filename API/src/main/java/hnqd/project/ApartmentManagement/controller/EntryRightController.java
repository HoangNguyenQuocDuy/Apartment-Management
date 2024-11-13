package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.service.IEntryRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/entryRights")
public class EntryRightController {

    @Autowired
    private IEntryRightService entryRightService;

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getEntryRights(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get entry rights successfully!",
                        entryRightService.getEntryRights(params))
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getEntryRightsPaging(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get entry rights successfully!",
                        entryRightService.getEntryRightsPaging(params))
        );
    }

    @PostMapping("/")
    public ResponseEntity<?> createEntryRight(@RequestBody Map<String, Integer> body) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Create entry rights successfully!",
                        entryRightService.createEntryRight(body.get("relativeId")))
        );
    }

    @PutMapping("/{eId}")
    public ResponseEntity<?> updateEntryRight(@RequestBody Map<String, String> params,
                                                @PathVariable("eId") int pId) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Update entry right successfully!",
                        entryRightService.updateEntryRight(pId, params.get("status"))
                )
        );
    }

}
