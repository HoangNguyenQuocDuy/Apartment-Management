package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.Relative;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IRelativeService {
    Relative createRelative(Relative relative) throws IOException;

    Relative updateRelative(Integer relativeId, MultipartFile file, Map<String, String> params) throws IOException;

    List<Relative> getRelatives(Map<String, String> params);

    List<Relative> getRelatives();

    void deleteRelative(int id);
}
