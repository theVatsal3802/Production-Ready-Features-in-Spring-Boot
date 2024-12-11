package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.controllers;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping(path = "/posts/{postId}")
    public List<PostEntity> getPostRevisionsById(@PathVariable Long postId) {
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = reader.getRevisions(PostEntity.class, postId);
        return revisions
                .stream()
                .map(revisionNumber -> reader.find(PostEntity.class, postId, revisionNumber))
                .toList();
    }
}
