package com.kcommerce.domain.member.service;

import com.kcommerce.domain.member.domain.Address;
import com.kcommerce.domain.member.domain.AddressEntity;
import com.kcommerce.domain.member.domain.Member;
import com.kcommerce.domain.member.dto.AddressDto;
import com.kcommerce.domain.member.mapper.AddressMapper;
import com.kcommerce.domain.member.repository.AddressRepository;
import com.kcommerce.domain.member.repository.MemberRepository;
import com.kcommerce.global.error.exception.BusinessException;
import com.kcommerce.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public void createAddress(AddressDto.Request request, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        AddressEntity addressEntity = addressMapper.toEntity(request, member);
        addressRepository.save(addressEntity);
    }

    @Transactional(readOnly = true)
    public List<AddressDto> getAddressList(Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        List<AddressEntity> addressEntityList = addressRepository.findByMember(member);
        return addressMapper.toDtoList(addressEntityList);
    }

    public void updateAddress(Long id, AddressDto.Request request, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        AddressEntity addressEntity = addressRepository.findByIdAndMember(id, member)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS_NOT_FOUND));
        Address address = addressMapper.toVo(request);
        addressEntity.update(request.getName(), address);
    }

    public void deleteAddress(Long id, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        int result = addressRepository.deleteByIdAndMember(id, member);
        if (result == 0) {
            throw new BusinessException(ErrorCode.ADDRESS_NOT_FOUND);
        }
    }
}
