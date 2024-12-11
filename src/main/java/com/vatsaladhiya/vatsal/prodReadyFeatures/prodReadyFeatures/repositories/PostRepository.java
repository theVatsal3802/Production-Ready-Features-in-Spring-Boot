package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.repositories;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
