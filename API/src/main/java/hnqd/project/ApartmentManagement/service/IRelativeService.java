package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.Relative;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IRelativeService {
    Relative createRelative(Relative relative) throws IOException;

    Relative updateRelative(Relative relative);

    List<Relative> getRelatives(Map<String, String> params);

    Relative getRelativeById(int id) throws CommonException.NotFoundException;

    void deleteRelative(int id);
}
