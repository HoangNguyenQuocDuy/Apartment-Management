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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/relatives")
public class RelativeController {

    @Autowired
    private IRelativeService relativeService;

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getRelatives(@RequestParam Map<String, String> params) {
        List<Relative> relatives;

        if (params.isEmpty()) {
            relatives = relativeService.getRelatives();
        } else {
            relatives = relativeService.getRelatives(params);
        }

        return ResponseEntity.ok().body(
                new ResponseObject(HttpStatus.OK.toString(), "", relatives)
        );
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createRelative(
            @RequestPart("file") MultipartFile file,
            @RequestParam Map<String, String> params) throws IOException {

        User user = userService.getUserById(Integer.parseInt(params.get("userId")));

        Relative relative = new Relative();
        relative.setFirstname(params.get("firstname"));
        relative.setLastname(params.get("lastname"));
        relative.setType(params.get("type"));
        relative.setUser(user);

        if (!file.isEmpty()) {
            relative.setFile(file);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(HttpStatus.CREATED.toString(), "Create relative successfully!",
                        relativeService.createRelative(relative))
        );
    }

    @DeleteMapping("/{relativeId}")
    public ResponseEntity<?> deleteRelative(@PathVariable int relativeId) {
        relativeService.deleteRelative(relativeId);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(HttpStatus.CREATED.toString(), "Delete relative successfully!", "")
        );
    }

    @PostMapping(path = "/{relativeId}", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<?> updateRelative(@PathVariable("relativeId") int relativeId,
                                            @RequestPart MultipartFile file, @RequestParam Map<String, String> params) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(HttpStatus.OK.toString(), "Update relative successfully!",
                        relativeService.updateRelative(relativeId, file, params
                        ))
        );
    }
}
