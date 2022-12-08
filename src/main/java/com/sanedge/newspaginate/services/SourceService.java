package com.sanedge.newspaginate.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sanedge.newspaginate.converter.SourceConverter;
import com.sanedge.newspaginate.domain.Source;
import com.sanedge.newspaginate.dto.source.SourceBaseDto;
import com.sanedge.newspaginate.repository.SourceRepository;
import com.sanedge.newspaginate.utils.PageSource;

@Service
public class SourceService {
    private SourceRepository sourceRepository;

    private SourceConverter sourceConverter;

    @Autowired
    public SourceService(SourceRepository sourceRepository, SourceConverter sourceConverter) {
        this.sourceRepository = sourceRepository;
        this.sourceConverter = sourceConverter;
    }

    public SourceBaseDto createSource(Source source) {
        Source source1 = sourceRepository.save(source);
        return sourceConverter.toSourceBaseDTO(source1);
    }

    public SourceBaseDto updateSource(Integer id, Source source) {
        Source sourceSelect = sourceRepository.findById(id).orElse(source);
        sourceSelect.setId(id);
        sourceSelect.setName(source.getName());
        sourceSelect.setCode(source.getCode());
        sourceSelect.setCreateAt(source.getCreateAt());
        Source sourceUpdate = sourceRepository.save(sourceSelect);
        return sourceConverter.toSourceBaseDTO(sourceUpdate);
    }

    public String deleteSource(Integer id) {
        sourceRepository.deleteById(id);
        return "delete source with id number:" + id;
    }

    public PageSource getAllSource(int page, String q) {
        Pageable pageable = PageRequest.of(page - 1, 3);
        PageSource pageCustumerSource = new PageSource();
        if (q != null) {
            Page<Source> sources = sourceRepository.findByName(q, pageable);
            pageCustumerSource.setStatus(HttpStatus.OK);
            pageCustumerSource.setPage(sources.getTotalPages());
            pageCustumerSource.setSize(sources.getSize());
            pageCustumerSource.setTotalResult(sources.getTotalElements());
            pageCustumerSource.setContent(sources.getContent().stream().map(source -> sourceConverter.toDto(source))
                    .collect(Collectors.toList()));
        } else {
            Page<Source> sources = sourceRepository.findAll(pageable);
            pageCustumerSource.setStatus(HttpStatus.OK);
            pageCustumerSource.setPage(sources.getTotalPages());
            pageCustumerSource.setSize(sources.getSize());
            pageCustumerSource.setTotalResult(sources.getTotalElements());
            pageCustumerSource.setContent(sources.getContent().stream().map(source -> sourceConverter.toDto(source))
                    .collect(Collectors.toList()));
        }

        return pageCustumerSource;
    }
}
