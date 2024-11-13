package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.entity.EntryRight;
import hnqd.project.ApartmentManagement.entity.Relative;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IEntryRightRepo;
import hnqd.project.ApartmentManagement.repository.IRelativeRepo;
import hnqd.project.ApartmentManagement.service.IEntryRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EntryRightServiceImpl implements IEntryRightService {

    @Autowired
    private IEntryRightRepo entryRightRepo;
    @Autowired
    private IRelativeRepo relativeRepo;

    @Override
    public EntryRight createEntryRight(int relativeId) {
        Relative relativeFind = relativeRepo.findById(relativeId).orElseThrow(() -> (
                new CommonException.NotFoundException("Relative not found with ID: " + relativeId)
        ));

        EntryRight entryRight = new EntryRight();

        entryRight.setRelative(relativeFind);
        entryRight.setCreatedAt(new Timestamp(new Date().getTime()));
        entryRight.setStatus("Pending");
        return entryRightRepo.save(entryRight);
    }

    @Override
    public List<EntryRight> getEntryRights(Map<String, String> params) {
        if (!params.get("userId").isEmpty()) {
            return entryRightRepo.findAllByRelativeUserId(Integer.parseInt(params.get("userId")));
        }
        return entryRightRepo.findAll();
    }

    @Override
    public EntryRight updateEntryRight(int eId, String status) {
        EntryRight entrySave = entryRightRepo.findById(eId).orElseThrow(() -> (
                new CommonException.NotFoundException("Entry not found with ID: " + eId)
        ));

        entrySave.setUpdatedAt(new Timestamp(new Date().getTime()));
        entrySave.setStatus(status);

        return entryRightRepo.save(entrySave);
    }

    @Override
    public Page<EntryRight> getEntryRightsPaging(Map<String, String> params) {
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        Pageable pageable = PageRequest.of(page, size);
        String status = params.getOrDefault("status", "");
        if (!status.isEmpty()) {
            return entryRightRepo.findAllByStatus(status, pageable);
        }
        return entryRightRepo.findAll(pageable);
    }
}
