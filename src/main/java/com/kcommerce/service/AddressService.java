package com.kcommerce.service;

import com.kcommerce.domain.Address;
import com.kcommerce.domain.AddressEntity;
import com.kcommerce.domain.Member;
import com.kcommerce.dto.AddressDto;
import com.kcommerce.error.exception.BusinessException;
import com.kcommerce.error.exception.ErrorCode;
import com.kcommerce.mapper.AddressMapper;
import com.kcommerce.repository.AddressRepository;
import com.kcommerce.repository.MemberRepository;
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

    public void createAddress(AddressDto addressDto, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        AddressEntity addressEntity = addressMapper.toEntity(addressDto, member);
        addressRepository.save(addressEntity);
    }

    @Transactional(readOnly = true)
    public List<AddressDto> getAddressList(Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        List<AddressEntity> addressEntityList = addressRepository.findByMember(member);
        return addressMapper.toDtoList(addressEntityList);
    }

    public void updateAddress(Long id, AddressDto addressDto, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        AddressEntity addressEntity = addressRepository.findByIdAndMember(id, member)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS_NOT_FOUND));
        Address address = addressMapper.toVo(addressDto);
        addressEntity.update(addressDto.getName(), address);
    }

    public void deleteAddress(Long id, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        int result = addressRepository.deleteByIdAndMember(id, member);
        if (result == 0) {
            throw new BusinessException(ErrorCode.ADDRESS_NOT_FOUND);
        }
    }
}