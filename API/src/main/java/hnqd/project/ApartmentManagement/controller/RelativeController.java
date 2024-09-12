package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.entity.Relative;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.service.IRelativeService;
import hnqd.project.ApartmentManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController("/api/relatives")
public class RelativeController {

    @Autowired
    private IRelativeService relativeService;

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getRelative(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok().body(
                new ResponseObject(HttpStatus.OK.toString(), "", relativeService.getRelatives(params))
        );
    }

    @PostMapping(path = "/", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<ResponseObject> createRelative(@RequestPart MultipartFile[] files,
                                            @RequestParam Map<String, String> params) throws IOException {
            User user = userService.getUserById(Integer.parseInt(params.get("userId")));

            Relative relative = new Relative();
            relative.setFirstname(params.get("firstname"));
            relative.setLastname(params.get("lastname"));
            relative.setType(params.get("type"));
            relative.setUserByUserId(user);

            if (files.length > 0) {
                relative.setFile(files[0]);
            }

            relativeService.createRelative(relative);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject(HttpStatus.CREATED.toString(), "Create relative successfully!",
                            relativeService.getRelatives(params))
                    );
    }

    @DeleteMapping("/{relativeId}")
    public ResponseEntity<?> deleteRelative(@PathVariable int relativeId) {
        try {
            Relative relative = relativeService.getRelativeById(relativeId);
            if (relative == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Relative not found with ID: " + relativeId);
            }

            relativeService.deleteRelative(relativeId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping(path = "/{relativeId}", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<?> updateRelative(@PathVariable("relativeId") int relativeId,
                                            @RequestPart MultipartFile[] files, @RequestParam Map<String, String> params) {
        try {
            Relative relative = relativeService.getRelativeById(relativeId);
            if (relative == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Relative not found with ID: " + relativeId);
            }

            if (files.length > 0) {
                relative.setFile(files[0]);
            }
            if (params.containsKey("firstname")) {
                relative.setFirstname(params.get("firstname"));
            }
            if (params.containsKey("lastname")) {
                relative.setLastname(params.get("lastname"));
            }
            if (params.containsKey("type")) {
                relative.setType(params.get("type"));
            }

            relativeService.createRelative(relative);

            return ResponseEntity.status(HttpStatus.OK).body(
                    "Update relative successfully!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
