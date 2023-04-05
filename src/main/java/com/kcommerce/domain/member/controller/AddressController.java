package com.kcommerce.domain.member.controller;

import com.kcommerce.domain.member.dto.AddressDto;
import com.kcommerce.domain.member.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Void> createAddress(@RequestBody @Valid AddressDto addressDto,
                                              @AuthenticationPrincipal Long memberId) {
        addressService.createAddress(addressDto, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAddressList(@AuthenticationPrincipal Long memberId) {
        List<AddressDto> addressDtoList = addressService.getAddressList(memberId);
        return ResponseEntity.ok(addressDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable Long id,
                                              @RequestBody @Valid AddressDto addressDto,
                                              @AuthenticationPrincipal Long memberId) {
        addressService.updateAddress(id, addressDto, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id,
                                              @AuthenticationPrincipal Long memberId) {
        addressService.deleteAddress(id, memberId);
        return ResponseEntity.ok().build();
    }
}
