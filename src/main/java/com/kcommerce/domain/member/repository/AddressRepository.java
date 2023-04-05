package com.kcommerce.domain.member.repository;

import com.kcommerce.domain.member.domain.AddressEntity;
import com.kcommerce.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    List<AddressEntity> findByMember(Member member);

    Optional<AddressEntity> findByIdAndMember(Long id, Member member);

    int deleteByIdAndMember(Long id, Member member);
}
